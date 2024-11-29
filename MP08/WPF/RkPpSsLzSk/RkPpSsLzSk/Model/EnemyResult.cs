namespace RkPpSsLzSk.Model;

public class EnemyResult
{
    public string Name { get; set; }
    public string Score { get; set; }

    public override string ToString() => $"{Name} // {Score}";
}