using System.IO;

namespace A3.DAO;

public class MidSquareAccess: IDisposable, IDAO
{

    public bool Empty { get; set; }
    public int MaxLengthOfName { get; set; }
    public int LengthOfName { get; set; }
    public int MaxLenghtOfName => posicionsValidades.Count - NIF.Length;
    public string NIF { get; set; }
    public string Nom { get; set; }
    private const int POS_NOM = 10000;
    private FileStream fs = null;
    private BinaryReader br = null;
    private BinaryWriter bw = null;
    private string name;
    private string nif;
    private List<int> posicionsValidades = [];
    
    
    
    public MidSquareAccess(string seed, bool overWrite)
    {
        string fileName = seed + ".bin";
        if (overWrite || !File.Exists(fileName))
        {
            
        }
    }

    private int ReadLengthOfName()
    {
        fs.Seek(POS_NOM, SeekOrigin.Begin);
        return br.ReadInt32();
    }

    private List<int> SequenciaDePosicions(string seed)
    {
        posicionsValidades.Add(int.Parse(seed));
        string current = "";
        int value = int.Parse(seed);
        for (int i = 0; i < POS_NOM; i++)
        {
            value *= value;
            while (current.Length != 8)
            {
                current = "0" + current;
            }
            current = value.ToString().Substring(2, 4);
            value = int.Parse(current);
            posicionsValidades.Add(value);
        }

        return posicionsValidades;
    }

    private bool IsFeasible(string name, string nif)
    {
        return posicionsValidades.Count > LengthOfName;
    }
    
    public void WriteData(string name, string nif)
    {
        throw new NotImplementedException();
    }

    public string ReadNIF()
    {
        throw new NotImplementedException();
    }

    public string ReadName()
    {
        throw new NotImplementedException();
    }
    
    public void Dispose()
    {
        // TODO release managed resources here
    }


}