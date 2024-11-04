using System.Collections.Generic;
using System.Windows;
using StarterTeclatMouseControlsDinàmics.Model;

namespace StarterTeclatMouseControlsDinàmics;

public partial class WndPropietatsDeDendencia : Window
{
    private List<Person> listOfPersons = new();
    public WndPropietatsDeDendencia()
    {
        InitializeComponent();
        listOfPersons.Add(new Person
        {
            Name = "Joan",
            Surname = "Petit",
            BirthYear = 1950,
            MarriageYear = 1975,
            DeathYear = 2010
        });
        listOfPersons.Add(new Person
        {
            Name = "Pere",
            Surname = "Cullera",
            BirthYear = 1900,
            MarriageYear = 1918,
            DeathYear = 1950
        });
        listOfPersons.Add(new Person
        {
            Name = "Maria",
            Surname = "Fontaneda",
            BirthYear = 2000,
            MarriageYear = 2040,
            DeathYear = 2100
        });
        listOfPersons.Add(new Person
        {
            Name = "Montse",
            Surname = "Santana",
            BirthYear = 1980,
            MarriageYear = 2002,
            DeathYear = 2080
        });
        listOfPersons.Add(new Person
        {
            Name = "Miquel",
            Surname = "Llompart",
            BirthYear = 1980,
            MarriageYear = 2000,
            DeathYear = 2010
        });
        lstPersones.ItemsSource = listOfPersons;
    }
}