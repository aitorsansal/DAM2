﻿using System;
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
        Statistics statistics = new Statistics();
        statistics.Year = year;
        statistics.Month = Months.All.ToString();
        XDocument doc = XDocument.Load(FILE);
        statistics.AmountNews = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString())
            .Where(d => long.TryParse(d.Element("new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("new")!.Value));
        statistics.AmountUsed = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString())
            .Where(d => long.TryParse(d.Element("used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("used")!.Value));
        statistics.TotalNews = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString())
            .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_new")!.Value));
        statistics.TotalUsed = doc.Descendants("row")
            .Where(d => d.Element("year")?.Value == year.ToString())
            .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_used")!.Value));
        
        Statistics testStatistics = new Statistics() {Year = year, Month = Months.All.ToString()};
        foreach (var month in GetSalesMonthByMonth(year))
        {
            testStatistics.AmountNews += month.AmountNews;
            testStatistics.AmountUsed += month.AmountUsed;
            testStatistics.TotalNews += month.TotalNews;
            testStatistics.TotalUsed += month.TotalUsed;
        }
        
        return statistics;
    }

    public Statistics GetSalesByMonth(string month)
    {
        Statistics statistics = new Statistics();
        statistics.Month = month;
        statistics.Year = 0;
        XDocument doc = XDocument.Load(FILE);
        statistics.AmountNews = doc.Descendants("row")
            .Where(d => d.Element("month")?.Value == month)
            .Where(d => long.TryParse(d.Element("new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("new")!.Value));
        statistics.AmountUsed = doc.Descendants("row")
            .Where(d => d.Element("month")?.Value == month)
            .Where(d => long.TryParse(d.Element("used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("used")!.Value));
        statistics.TotalNews = doc.Descendants("row")
            .Where(d => d.Element("month")?.Value == month)
            .Where(d => long.TryParse(d.Element("total_sales_new")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_new")!.Value));
        statistics.TotalUsed = doc.Descendants("row")
            .Where(d => d.Element("month")?.Value == month)
            .Where(d => long.TryParse(d.Element("total_sales_used")?.Value, out _))
            .Sum(d => long.Parse(d.Element("total_sales_used")!.Value));

        return statistics;
    }
    
    public List<Statistics> GetSalesMonthByMonth(int year)
    {
        if(!GetDistinctYears().Contains(year)) throw new ArgumentException("Year is not in the possible year");
        List<string> months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];

        XDocument doc = XDocument.Load(FILE);

        return months.Select(month => new Statistics()
            {
                Year = year,
                Month = month,
                AmountNews = doc.Descendants("row")
                    .Where(d => d.Element("year")?.Value == year.ToString())
                    .Where(d => d.Element("month")?.Value == month)
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
            })
            .ToList();

    }


    public Statistics GetSalesByYearAndMonth(int year, string month)
    {
        var doc = XDocument.Load(FILE);
        return new Statistics()
        {
            Year = year,
            Month = month,
            AmountNews = doc.Descendants("row")
                .Where(d => d.Element("year")?.Value == year.ToString())
                .Where(d => d.Element("month")?.Value == month)
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
        throw new NotImplementedException();
    }
}