using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics.Eventing.Reader;
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
        XDocument doc = XDocument.Load(FILE);
        List<int> years = doc.Descendants("year")
            .Select(year => int.Parse(year.Value))
            .Distinct()
            .ToList();
        return years;
    }

    public Statistics GetSalesByYear(int year)
    {
        if(!GetDistinctYears().Contains(year)) throw new ArgumentException("Year is not in the possible year");
        Statistics statistics = new Statistics();
        statistics.Year = year;
        statistics.Month = Months.All.ToString();
        XDocument doc = XDocument.Load(FILE);
        var possibleElements = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString()).ToArray();
        statistics.AmountNews = possibleElements
            .Where(d => long.TryParse(d.Element("new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("new")!.Value));
        statistics.AmountUsed = possibleElements
            .Where(d => long.TryParse(d.Element("used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("used")!.Value));
        statistics.TotalNews = possibleElements
            .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_new")!.Value));
        statistics.TotalUsed = possibleElements
            .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_used")!.Value));
        
        return statistics;
    }

    public Statistics GetSalesByMonth(string month)
    {
        Statistics statistics = new Statistics();
        statistics.Month = month;
        statistics.Year = 0;
        XDocument doc = XDocument.Load(FILE);
        var possibleElements = doc.Descendants("row")
            .Where(d => d.Element("month")?.Value == month).ToArray();
        statistics.AmountNews = possibleElements
            .Where(d => long.TryParse(d.Element("new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("new")!.Value));
        statistics.AmountUsed = possibleElements
            .Where(d => long.TryParse(d.Element("used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("used")!.Value));
        statistics.TotalNews = possibleElements
            .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_new")!.Value));
        statistics.TotalUsed = possibleElements
            .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_used")!.Value));

        return statistics;
    }
    
    public List<Statistics> GetSalesMonthByMonth(int year)
    {
        if(!GetDistinctYears().Contains(year)) throw new ArgumentException("Year is not in the possible year");
        List<string> months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];

        XDocument doc = XDocument.Load(FILE);
        var elementsOfYear = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString()).ToArray();

        return months.Select(month => new Statistics()
            {
                Year = year,
                Month = month,
                AmountNews = elementsOfYear
                    .Where(d => d.Element("month")?.Value == month)
                    .Where(d => long.TryParse(d.Element("new")?.Value, out _))
                    .Select(d => long.Parse(d.Element("new")!.Value))
                    .FirstOrDefault(),
                AmountUsed = elementsOfYear
                    .Where(d => d.Element("month")?.Value == month)
                    .Where(d => long.TryParse(d.Element("used")?.Value, out _))
                    .Select(d => long.Parse(d.Element("used")!.Value))
                    .FirstOrDefault(),
                TotalNews = elementsOfYear
                    .Where(d => d.Element("month")?.Value == month)
                    .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
                    .Select(d => long.Parse(d.Element("total_sales_new")!.Value))
                    .FirstOrDefault(),
                TotalUsed = elementsOfYear
                    .Where(d => d.Element("month")?.Value == month)
                    .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
                    .Select(d => long.Parse(d.Element("total_sales_used")!.Value))
                    .FirstOrDefault()
            })
            .ToList();

    }


    public Statistics GetSalesByYearAndMonth(int year, string month)
    {
        var doc = XDocument.Load(FILE);
        var selectedElement = doc
            .Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString()).FirstOrDefault(d => d.Element("month")?.Value == month);
        return new Statistics()
        {
            Year = year,
            Month = month,
            AmountNews = long.TryParse(d.E
                .Where(d => long.TryParse(d.Element("new")?.Value, out _))
                .Select(d => long.Parse(d.Element("new")!.Value))
                .FirstOrDefault(),
            AmountUsed = doc.Descendants("row")
                .Where(d => d.Element("year")?.Value == year.ToString())
                .Where(d => d.Element("month")?.Value == month)
                .Where(d => long.TryParse(d.Element("used")?.Value, out _))
                .Select(d => long.Parse(d.Element("used")!.Value))
                .FirstOrDefault(),
            TotalNews = doc.Descendants("row")
                .Where(d => d.Element("year")?.Value == year.ToString())
                .Where(d => d.Element("month")?.Value == month)
                .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
                .Select(d => long.Parse(d.Element("total_sales_new")!.Value))
                .FirstOrDefault(),
            TotalUsed = doc.Descendants("row")
                .Where(d => d.Element("year")?.Value == year.ToString())
                .Where(d => d.Element("month")?.Value == month)
                .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
                .Select(d => long.Parse(d.Element("total_sales_used")!.Value))
                .FirstOrDefault()
        };
    }

    public bool UpdateStatistics(Statistics oneStatistics)
    {
        var doc = XDocument.Load(FILE);
        var selectedNode = doc
            .Descendants("row")
            .Where(d => d.Element("year")?.Value == oneStatistics.Year.ToString())
            .FirstOrDefault(d => d.Element("month")?.Value == oneStatistics.Month);
        if (selectedNode is null) return false;
        
        try
        {
            selectedNode.Descendants("new").FirstOrDefault()!.SetValue(oneStatistics.AmountNews.ToString());
            selectedNode.Descendants("used").FirstOrDefault()!.SetValue(oneStatistics.AmountUsed.ToString());
            selectedNode.Descendants("total_sales_new").FirstOrDefault()!.SetValue(oneStatistics.TotalNews.ToString());
            selectedNode.Descendants("total_sales_used").FirstOrDefault()!.SetValue(oneStatistics.TotalUsed.ToString());
            doc.Save(FILE);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
