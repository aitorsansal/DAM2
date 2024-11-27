using System.Collections.ObjectModel;
using RkPpSsLzSk.Model;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using RkPpSsLzSk.Repositiori;

namespace RkPpSsLzSk;

public partial class ViewModel : ObservableObject
{
    public enum Screens { Config, Playing, Records }

    #region Properties
    
    [ObservableProperty] 
    Screens currentScreen;
    [ObservableProperty]
    ObservableCollection<object> recordsList;

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))] 
    private string playerName;

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private ObservableCollection<string> playerNames = ["New Player"];

    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(CreateNewPlayerCommand))]
    private object selectedNameIndex;
    
    [ObservableProperty]
    private bool onNormalMode;
    
    [ObservableProperty]
    private bool only3Rounds;
    
    #endregion

    #region Fields

    readonly Header header = new Header
    {
        Name = "Name",
        TotalPoints = "Total Points",
        MaxInSingleTournament = "Max Tournament",
        WonGames = "Won Games",
        WonRounds = "Won Rounds",
        LostRounds = "Lost Rounds"
    };

    private readonly IPlayersRepository playersRepository;
    private bool currentlyPlaying;

    #endregion

    public ViewModel()
    {
        playersRepository = Repository.OpenBDPlayers();
        RecordsList = new ObservableCollection<object>();
        OpenRecords();
        PlayerName = string.Empty;
        SelectedNameIndex = "New Player";
    }

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

    private bool CanCreateNewPlayer()
    {
        return PlayerName != string.Empty && !PlayerNames.Contains(PlayerName) && (string)SelectedNameIndex == "New Player";
    }
    private bool CanOpenRecords() => !currentlyPlaying && CurrentScreen != Screens.Records;
    private bool CanOpenConfig() => !currentlyPlaying && CurrentScreen != Screens.Config;
}