namespace A2;

public interface IXMLManager
{
    public List<int> GetDistinctYears();
    public Statistics GetSalesByYear(int year);
    public List<Statistics> GetSalesMonthByMonth(int year);
    public Statistics GetSalesByMonth(int month);
    public Statistics GetSalesByYearAndMonth(int year, int month);
    public bool UpdateStatistics(Statistics oneStatistics);
}