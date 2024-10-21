using System.IO;

namespace A3.DAO;

public class MidSquareAccess: IDAO
{

    public bool Empty { get; set; }
    public int MaxLengthOfName { get; set; }
    public int LengthOfName { get; private set; }

    public int MaxLenghtOfName => posicionsValidades.Count - NIF.Length;
    public string NIF { get; set; } = string.Empty;
    public string Nom { get; set; } = string.Empty;
    private const int POS_NOM = 10000;
    private FileStream fs = null;
    private BinaryReader br = null;
    private BinaryWriter bw = null;
    private List<int> posicionsValidades = [];
    
    
    
    public MidSquareAccess(string seed, bool overWrite)
    {
        string fileName = seed + ".bin";
        posicionsValidades = SequenciaDePosicions(seed);
        if (overWrite)
        {
            fs = new FileStream(fileName, FileMode.Create, FileAccess.ReadWrite, FileShare.Read);
            bw = new BinaryWriter(fs);
            br = new BinaryReader(fs);
        }
        else
        {
            fs = new FileStream(fileName, FileMode.Open, FileAccess.Read, FileShare.Read);
            br = new BinaryReader(fs);
        }
    }

    private int ReadLengthOfName()
    {
        fs.Seek(POS_NOM, SeekOrigin.Begin);
        return br.ReadInt32();
    }

    private List<int> SequenciaDePosicions(string seed)
    {
        List<int> pos =
        [
            int.Parse(seed)
        ];
        string current = "";
        int value = int.Parse(seed);
        for (int i = 0; i < POS_NOM; i++)
        {
            value *= value;
            current = value.ToString();
            while (current.Length != 8)
            {
                current = "0" + current;
            }
            current = current.Substring(2, 4);
            value = int.Parse(current);
            pos.Add(value);
        }

        return pos.Distinct().ToList();
    }

    public bool IsFeasible(string name, string nif)
    {
        return posicionsValidades.Count > name.Length+nif.Length;
    }
    
    public void WriteData(string name, string nif)
    {
        fs.Seek(POS_NOM, SeekOrigin.Begin);
        bw.Write(name.Length);
        string nameNif = name + nif;
        for (int i = 0; i < nameNif.Length; i++)
        {
            fs.Seek(posicionsValidades[i], SeekOrigin.Begin);
            bw.Write(nameNif[i]);
        }
    }

    public string ReadNIF()
    {
        if (LengthOfName is 0) LengthOfName = ReadLengthOfName();
        string recreatedNif = string.Empty;
        for (int i = 0; i < 9; i++)
        {
            fs.Seek(posicionsValidades[LengthOfName+i], SeekOrigin.Begin);
            recreatedNif += br.ReadChar();
        }

        return recreatedNif;
    }

    public string ReadName()
    {
        if(LengthOfName is 0) 
            LengthOfName = ReadLengthOfName();
        string recreatedName = string.Empty;
        for (int i = 0; i < LengthOfName; i++)
        {
            fs.Seek(posicionsValidades[i], SeekOrigin.Begin);
            recreatedName += br.ReadChar();
        }

        return recreatedName;
    }
    
    public void Dispose()
    {
        if(fs is not null) fs.Dispose();
        if(bw is not null) bw.Dispose();
        if(br is not null) br.Dispose();
    }


}