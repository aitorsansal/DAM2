using System.IO;

namespace A3.DAO;

public interface IDAO : IDisposable
{
    public void WriteData(string name, string nif);
    public bool IsFeasible(string name, string nif);
    string ReadNIF();
    string ReadName();
    bool Empty {  get; }
    int LengthOfName {  get; }
    int MaxLenghtOfName { get; }
    string NIF { get; set; }
    string Nom {  get; set; }
}