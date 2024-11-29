using System.Collections.ObjectModel;
using Bogus;
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

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(StartGameCommand)), NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))] 
    private string playerName = "Aitor";

    [ObservableProperty]
    private string enemyName;
    
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private ObservableCollection<string> playerNames = ["New Player"];

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private object selectedName;
    
    [ObservableProperty]
    private bool onExtendedMode;
    
    [ObservableProperty]
    private bool playFor5Rounds;
    
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(OpenConfigCommand)), NotifyCanExecuteChangedFor(nameof(StartGameCommand)), NotifyCanExecuteChangedFor(nameof(OpenRecordsCommand))]
    private bool currentlyPlaying;

    [ObservableProperty] 
    private string currentGameRound;

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
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(OptionSelectedCommand))]
    private bool canSelectValue;

    [ObservableProperty]
    private ObservableCollection<EnemyResult> defeatedEnemies;
    
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
    private int currentRound;

    private Array enumValues;
    private Player playerPoints;
    private int currentTournamentWins;
    #endregion

    public ViewModel()
    {
        playersRepository = Repository.OpenBDPlayers();
        RecordsList = new ObservableCollection<object>();
        OpenConfig();
        SelectedName = "New Player";
        EnteringPlayerName = string.Empty;
        CanSelectValue = true;
        MainWindow.FinishedFightAnimation += () =>
        {
            CanSelectValue = true;
            if (CurrentRoundPlayerScore >= quantityToWin)
            {
                currentTournamentWins++;
                playerPoints.MaxInSingleTournament =
                    Math.Max(playerPoints.MaxInSingleTournament, currentTournamentWins);
                playerPoints.TotalPoints += 5;
                playerPoints.WonGames++;
                DefeatedEnemies.Add(new EnemyResult{Name = EnemyName, Score = $"{CurrentRoundPlayerScore}-{CurrentRoundEnemyScore}"});
                SavePointsToPlayer();
                StartRound();
            }
            else if(CurrentRoundEnemyScore >= quantityToWin)
            {
                SavePointsToPlayer();
                OpenRecords();
                CurrentlyPlaying = false;
            }
        };
        PlayerNames = ["New Player"];
        foreach (var player in playersRepository.Obtain())
        {
            PlayerNames.Add(player.Name);
        }
    }

    #region Commands

    [RelayCommand (CanExecute = nameof(CanOpenRecords))]
    void OpenRecords()
    {
        RecordsList = [header];
        var playersList = playersRepository.Obtain().ToList();
        playersList.Sort();
        foreach (var player in playersList)
        {
            RecordsList.Add(player);
        }
        
        CurrentScreen = Screens.Records;
    }

    [RelayCommand (CanExecute = nameof(CanOpenConfig))]
    void OpenConfig()
    {
        CurrentScreen = Screens.Config;
    }

    [RelayCommand (CanExecute = nameof(CanCreateNewPlayer))]
    void CreateNewPlayer()
    {
        PlayerNames.Add(EnteringPlayerName);
        playersRepository.Add(new Player{ Name = EnteringPlayerName });
        PlayerName = EnteringPlayerName;
        EnteringPlayerName = string.Empty;
    }

    [RelayCommand (CanExecute = nameof(CanStartGame))]
    void StartGame()
    {
        CurrentlyPlaying = true;
        CurrentScreen = Screens.Playing;
        quantityToWin = PlayFor5Rounds ? 3 : 2;
        currentRound = 0;
        enumValues = OnExtendedMode ? Enum.GetValues(typeof(SelectionValues)) : Enum.GetValues(typeof(SelectionValues)).Cast<SelectionValues>().Take(3).ToArray();
        StartRound(); 
        playerPoints = playersRepository.Obtain().FirstOrDefault(player => player.Name.Equals(PlayerName));
        currentTournamentWins = 0;
        DefeatedEnemies = new ObservableCollection<EnemyResult>();
    }


    [RelayCommand (CanExecute = nameof(CanSelectOption))]
    void OptionSelected(object? sender)
    {
        CanSelectValue = false;
        PlayerSelection = (SelectionValues)sender;
        playersRepository.ModifySelection(PlayerSelection, PlayerName);
        PlayerImageSelectionPath = GetImagePath(PlayerSelection);
        Random random = new();
        EnemySelection = (SelectionValues)enumValues
            .GetValue(random.Next(enumValues.Length))!;
        EnemyImageSelectionPath = GetImagePath(EnemySelection);
        var result = GetResult(PlayerSelection, EnemySelection);
        switch (result)
        {
            case Result.Win:
                CurrentRoundPlayerScore++;
                playerPoints.TotalPoints+=2;
                playerPoints.WonRounds++;
                break;
            case Result.Lose:
                CurrentRoundEnemyScore++;
                playerPoints.TotalPoints--;
                playerPoints.LostRounds++;
                break;
        }
        StartFightAnimationEvent?.Invoke(result);
        
    }
    
    private bool CanCreateNewPlayer() =>EnteringPlayerName != string.Empty && !PlayerNames.Contains(EnteringPlayerName) && PlayerName == "New Player";
    private bool CanOpenRecords() => !CurrentlyPlaying && CurrentScreen != Screens.Records;
    private bool CanOpenConfig() => !CurrentlyPlaying && CurrentScreen != Screens.Config;
    private bool CanStartGame() => !CurrentlyPlaying && PlayerName != "New Player";
    private bool CanSelectOption() => CanSelectValue;

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

    void StartRound()
    {
        currentRound++;
        CurrentGameRound = "Round " + currentRound;
        CurrentRoundPlayerScore = 0;
        CurrentRoundEnemyScore = 0;
        var fkr = new Faker("es");
        EnemyName = fkr.Name.FirstName();
    }
    
    void SavePointsToPlayer()
    {
        playersRepository.Modify(playerPoints, playersRepository.Obtain());
    }
    
    #endregion

    #region Delegates

    public delegate void StartFightAnimation(Result roundResult);
    public static event StartFightAnimation StartFightAnimationEvent;

    #endregion

}