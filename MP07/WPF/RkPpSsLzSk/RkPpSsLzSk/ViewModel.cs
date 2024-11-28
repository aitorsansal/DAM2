using System.Collections.ObjectModel;
using RkPpSsLzSk.Model;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using RkPpSsLzSk.Repositiori;

namespace RkPpSsLzSk;

public enum Screens { Config, Playing, Records }
public enum SelectionValues {Rock, Paper, Scissors, Lizard, Spock}
public enum Result {Win, Lose, Draw}

public partial class ViewModel : ObservableObject
{
    #region Properties
    
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(OpenConfigCommand)), NotifyCanExecuteChangedFor(nameof(OpenRecordsCommand))] 
    Screens currentScreen = Screens.Playing;
    [ObservableProperty]
    ObservableCollection<object> recordsList;

    [ObservableProperty] 
    private string playerName = "Aitor";

    [ObservableProperty]
    private string enemyName = "Enemy Testing";
    
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private ObservableCollection<string> playerNames = ["New Player"];

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private object selectedNameIndex;
    
    [ObservableProperty]
    private bool onNormalMode;
    
    [ObservableProperty]
    private bool only3Rounds;
    
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(OpenConfigCommand)), NotifyCanExecuteChangedFor(nameof(StartGameCommand)), NotifyCanExecuteChangedFor(nameof(OpenRecordsCommand))]
    private bool currentlyPlaying;

    [ObservableProperty] 
    private string currentGameRound = "Round 1";

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private string enteringPlayerName;
    
    [ObservableProperty]
    private string enemyImageSelectionPath;

    [ObservableProperty]
    private string playerImageSelectionPath;
    
    [ObservableProperty]
    private SelectionValues playerSelection = SelectionValues.Rock;
    
    [ObservableProperty]
    private SelectionValues enemySelection = SelectionValues.Rock;

    [ObservableProperty] private int currentRoundPlayerScore;
    [ObservableProperty] private int currentRoundEnemyScore;
    
    #endregion

    #region Fields

    readonly Header header = new()
    {
        Name = "Name",
        TotalPoints = "Total Points",
        MaxInSingleTournament = "Max Tournament",
        WonGames = "Won Games",
        WonRounds = "Won Rounds",
        LostRounds = "Lost Rounds"
    };

    private readonly IPlayersRepository playersRepository;
    private int quantityToWin;

    #endregion

    public ViewModel()
    {
        playersRepository = Repository.OpenBDPlayers();
        RecordsList = new ObservableCollection<object>();
        //OpenRecords();
        CurrentScreen = Screens.Playing;
        SelectedNameIndex = "New Player";
        EnteringPlayerName = string.Empty;
        PlayerImageSelectionPath = "Resources/Images/Lizard.png";
        enemyImageSelectionPath = "Resources/Images/Lizard.png";
    }

    #region Commands

    [RelayCommand (CanExecute = nameof(CanOpenRecords))]
    void OpenRecords()
    {
        RecordsList = [header];
        foreach (var player in playersRepository.Obtain())
        {
            RecordsList.Add(player);
        }
        CurrentScreen = Screens.Records;
    }

    [RelayCommand (CanExecute = nameof(CanOpenConfig))]
    void OpenConfig()
    {
        PlayerNames = ["New Player"];
        foreach (var player in playersRepository.Obtain())
        {
            PlayerNames.Add(player.Name);
        }
        CurrentScreen = Screens.Config;
    }

    [RelayCommand (CanExecute = nameof(CanCreateNewPlayer))]
    void CreateNewPlayer()
    {
        PlayerNames.Add(PlayerName);
        playersRepository.Add(new Player{ Name = PlayerName });
        PlayerName = string.Empty;
    }

    [RelayCommand (CanExecute = nameof(CanStartGame))]
    void StartGame()
    {
        CurrentlyPlaying = true;
        CurrentScreen = Screens.Playing;
        quantityToWin = Only3Rounds ? 2 : 3;
    }


    [RelayCommand]
    void OnOptionSelected(object? sender)
    {
        PlayerSelection = (SelectionValues)sender;
        PlayerImageSelectionPath = GetImagePath(PlayerSelection);
        Random random = new();
        var enumValues = Enum.GetValues(typeof(SelectionValues));
        EnemySelection = (SelectionValues)enumValues
            .GetValue(random.Next(enumValues.Length))!;
        EnemyImageSelectionPath = GetImagePath(EnemySelection);
        var result = GetResult(PlayerSelection, EnemySelection);
        switch (result)
        {
            case Result.Win:
                CurrentRoundPlayerScore++;
                break;
            case Result.Lose:
                CurrentRoundEnemyScore++;
                break;
        }
        
    }
    //&& CurrentScreen != Screens.Records
    private bool CanCreateNewPlayer() =>EnteringPlayerName != string.Empty && !PlayerNames.Contains(EnteringPlayerName) && (string)SelectedNameIndex == "New Player";
    private bool CanOpenRecords() => !CurrentlyPlaying && CurrentScreen != Screens.Records;
    private bool CanOpenConfig() => !CurrentlyPlaying && CurrentScreen != Screens.Config;
    private bool CanStartGame() => !CurrentlyPlaying;

    #endregion

    #region Voids

    static string GetImagePath(SelectionValues value)
    {
        return value switch
        {
            SelectionValues.Rock => "Resources/Images/Rock.png",
            SelectionValues.Paper => "Resources/Images/Paper.png",
            SelectionValues.Scissors => "Resources/Images/Scissors.png",
            SelectionValues.Lizard => "Resources/Images/Lizard.png",
            SelectionValues.Spock => "Resources/Images/Spock.png",
            _ => string.Empty
        };
    }

    static Result GetResult(SelectionValues player, SelectionValues enemy)
    {
        return player switch
        {
            SelectionValues.Rock => enemy switch
            {
                SelectionValues.Lizard or SelectionValues.Scissors => Result.Win,
                SelectionValues.Paper or SelectionValues.Spock => Result.Lose,
                _ => Result.Draw
            },
            SelectionValues.Paper => enemy switch
            {
                SelectionValues.Rock or SelectionValues.Spock => Result.Win,
                SelectionValues.Scissors or SelectionValues.Lizard => Result.Lose,
                _ => Result.Draw
            },
            SelectionValues.Scissors => enemy switch
            {
                SelectionValues.Paper or SelectionValues.Lizard => Result.Win,
                SelectionValues.Rock or SelectionValues.Spock => Result.Lose,
                _ => Result.Draw
            },
            SelectionValues.Lizard => enemy switch
            {
                SelectionValues.Paper or SelectionValues.Spock => Result.Win,
                SelectionValues.Rock or SelectionValues.Scissors => Result.Lose,
                _ => Result.Draw
            },
            SelectionValues.Spock => enemy switch
            {
                SelectionValues.Scissors or SelectionValues.Rock => Result.Win,
                SelectionValues.Lizard or SelectionValues.Paper => Result.Lose,
                _ => Result.Draw
            },
            _ => throw new ArgumentOutOfRangeException(nameof(player), player, null)
        };
    }
    
    #endregion

}