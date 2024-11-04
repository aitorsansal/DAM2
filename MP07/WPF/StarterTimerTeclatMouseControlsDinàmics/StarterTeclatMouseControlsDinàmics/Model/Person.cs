using System.Reflection;
using System.Windows;

namespace StarterTeclatMouseControlsDinàmics.Model;

public class Person : DependencyObject
{
    public string Name { get; set; }
    public string Surname { get; set; }

    public static readonly DependencyProperty BirthYearProperty = DependencyProperty.Register(
        "BirthYear",
        typeof(int),
        ownerType:typeof(Person),
        new PropertyMetadata(
            1900,
            OnBirthYear_Changed
            )
    );

    private static void OnBirthYear_Changed(DependencyObject d, DependencyPropertyChangedEventArgs e)
    {
        Person person = (Person)d;
        person.CoerceValue(DeathYearProperty);
        person.CoerceValue(MarriageYearProperty);
    }

    public int BirthYear
    {
        get => (int)GetValue(BirthYearProperty);
        set => SetValue(BirthYearProperty, value);
    }

    public static readonly DependencyProperty MarriageYearProperty = DependencyProperty.Register(
        nameof(MarriageYear), typeof(int), typeof(Person), new PropertyMetadata(1920, OnMarriageYear_Changed, OnMarriageYear_Coerced));

    private static void OnMarriageYear_Changed(DependencyObject d, DependencyPropertyChangedEventArgs e)
    {
        Person person = (Person)d;
        person.CoerceValue(e.Property);
    }

    private static object OnMarriageYear_Coerced(DependencyObject d, object basevalue)
    {
        Person person = (Person)d;
        int marriageYear = (int)basevalue;

        if (marriageYear < person.BirthYear)
            marriageYear = person.BirthYear;
        if(marriageYear > person.DeathYear)
            marriageYear = person.DeathYear;
        
        return marriageYear;
    }

    public int MarriageYear
    {
        get => (int)GetValue(MarriageYearProperty);
        set => SetValue(MarriageYearProperty, value);
    }

    public static readonly DependencyProperty DeathYearProperty = DependencyProperty.Register(
        nameof(DeathYear), typeof(int), typeof(Person), new PropertyMetadata(1950, OnDeathYear_Changed, OnDeathYear_Coerced));

    private static object OnDeathYear_Coerced(DependencyObject d, object basevalue)
    {
        Person person = (Person)d;
        int deathYear = (int)basevalue;
        if (person.BirthYear > deathYear)
            deathYear = person.BirthYear;
        return deathYear;
    }

    private static void OnDeathYear_Changed(DependencyObject d, DependencyPropertyChangedEventArgs e)
    {
        Person person = (Person)d;
        person.CoerceValue(e.Property);
        person.CoerceValue(MarriageYearProperty);
    }

    public int DeathYear
    {
        get => (int)GetValue(DeathYearProperty);
        set => SetValue(DeathYearProperty, value);
    }
    
    
    public override string ToString() => $"{Name} {Surname} - {BirthYear} - {MarriageYear} - {DeathYear}";
}