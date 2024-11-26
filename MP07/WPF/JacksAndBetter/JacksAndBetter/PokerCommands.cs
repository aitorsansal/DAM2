using System.Windows.Input;

namespace JacksAndBetter;

public class PokerCommands
{
    public static RoutedUICommand ChangeCards {get;}
    public static RoutedUICommand AddCoins { get; }
    public static RoutedUICommand StartGame { get; }
    public static RoutedUICommand SortCards { get; }
    public static RoutedUICommand PlayCards { get; }
    public static RoutedUICommand PlayForDouble { get; }

    static PokerCommands()
    {
        ChangeCards = new RoutedUICommand("Get Card", "GetCard", typeof(PokerCommands));
        AddCoins = new RoutedUICommand("Add Coins", "AddCoins", typeof(PokerCommands));
        StartGame = new RoutedUICommand("Start Game", "StartGame", typeof(PokerCommands));
        SortCards = new RoutedUICommand("Sort Cards", "SortCards", typeof(PokerCommands));
        PlayCards = new RoutedUICommand("Play Cards", "PlayCards", typeof(PokerCommands));
        PlayForDouble = new RoutedUICommand("Play for Double", "PlayForDouble", typeof(PokerCommands));
    }
}