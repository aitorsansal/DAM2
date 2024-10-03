using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using System.Xml.XPath;
using A2;

public class XmlManagerImplementation : IXMLManager
{
    const string FILE = "CarsSoldInMaryland.xml";
    public List<int> GetDistinctYears()
    {
        return GetYears().Distinct().ToList();
    }

    private List<int> GetYears()
    {
        XDocument doc = XDocument.Load(FILE);
        List<int> years = doc.Descendants("year")
            .Select(year => int.Parse(year.Value))
            .ToList();
        return years;
    }

    public Statistics GetSalesByYear(int year)
    {
        if(!GetDistinctYears().Contains(year)) throw new ArgumentException("Year is not in the possible year");
    }

    public List<Statistics> GetSalesMonthByMonth(int year)
    {
        throw new NotImplementedException();
    }

    public Statistics GetSalesByMonth(int month)
    {
        throw new NotImplementedException();
    }

    public Statistics GetSalesByYearAndMonth(int year, int month)
    {
        throw new NotImplementedException();
    }

    public bool UpdateStatistics(Statistics oneStatistics)
    {
        throw new NotImplementedException();
    }
}
