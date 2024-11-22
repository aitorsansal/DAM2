using System.ComponentModel.DataAnnotations;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks.Dataflow;
using System.Windows;
using System.Windows.Automation.Peers;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace Poker;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    private int coinsPlayed;
    private int internalCoinsPlayed;
    private int quantityOfCoins;
    private int wonCoins;
    private int doublesPlayed;
    private int coinsPlayedOnDoubles;
    private Deck deck;
    private Ma currHand;
    private Ma doublesHand;
    private List<int> cardsToChange;
    private bool changedCards;
    private bool playedCards;
    private bool isPlaying;
    private bool isOnBonusRound;
    private Card crupierCard;
    private bool wonSomething;
    private int timesPlayedDoubles;

    public static readonly DependencyProperty PlayerCoinsProperty = DependencyProperty.Register(
        nameof(PlayerCoins), typeof(string), typeof(MainWindow), new PropertyMetadata(default(string)));

    public string PlayerCoins
    {
        get => (string)GetValue(PlayerCoinsProperty);
        set => SetValue(PlayerCoinsProperty, value);
    }
    
    private static readonly Dictionary<string, int[]> PayoutTable = new()
    {
        { "Escala real de color", [250, 500, 750, 1000, 4000] },
        { "Escala de color", [50, 100, 150, 200, 250] },
        { "Pòquer", [25, 50, 75, 100, 125] },
        { "Full", [9, 18, 27, 36, 45] },
        { "Color", [6, 12, 18, 24, 30] },
        { "Escala", [4, 8, 12, 16, 20] },
        { "Trio", [3, 6, 9, 12, 15] },
        { "Doble parella", [2, 4, 6, 8, 10] },
        { "Jacks or més", [1, 2, 3, 4, 5] }
    };
    
    public MainWindow()
    {
        InitializeComponent();
    
        CreateCommands();
        SetNewCoins(1000);
        deck = new Deck();
        currHand = new Ma();
        cardsToChange = new List<int>();
        DataContext = this;
    }

    void CreateCommands()
    {
        CommandBinding changeCardsBinding = new CommandBinding(PokerCommands.ChangeCards);
        changeCardsBinding.Executed += ChangeCardsBindingOnExecuted;
        changeCardsBinding.CanExecute += (_, e) => { e.CanExecute = currHand.Count > 0 && !playedCards && cardsToChange.Count > 0; };
        CommandBindings.Add(changeCardsBinding);
        
        CommandBinding addCoinsBinding = new CommandBinding(PokerCommands.AddCoins);
        addCoinsBinding.Executed += AddCoinsBindingOnExecuted;
        addCoinsBinding.CanExecute += (_, e) => { e.CanExecute = !isPlaying && int.Parse((string)e.Parameter) <= quantityOfCoins; };
        CommandBindings.Add(addCoinsBinding);

        CommandBinding startBinding = new CommandBinding(PokerCommands.StartGame);
        startBinding.Executed += StartBindingOnExecuted;
        startBinding.CanExecute += (_, e) => {e.CanExecute = coinsPlayed > 0; };
        CommandBindings.Add(startBinding);
        
        CommandBinding sortCardsBinding = new CommandBinding(PokerCommands.SortCards);
        sortCardsBinding.Executed += SortCardsBindingOnExecuted;
        sortCardsBinding.CanExecute += (_, e) => { e.CanExecute = currHand.Count > 0 && !isOnBonusRound; };
        CommandBindings.Add(sortCardsBinding);
        
        CommandBinding playCardsBinding = new CommandBinding(PokerCommands.PlayCards);
        playCardsBinding.Executed += PlayCardsBindingOnExecuted;
        playCardsBinding.CanExecute += (_, e) => { e.CanExecute = !playedCards && currHand.Count > 0; };
        CommandBindings.Add(playCardsBinding);

        CommandBinding playForDoubleBinding = new CommandBinding(PokerCommands.PlayForDouble);
        playForDoubleBinding.Executed += PlayForDoubleBindingOnExecuted;
        playForDoubleBinding.CanExecute += (_, e) => { e.CanExecute = wonSomething; };
        CommandBindings.Add(playForDoubleBinding);
    }
    
    private void PlayForDoubleBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        brdrWinningText.Visibility = Visibility.Hidden;
        isPlaying = false;
        wonSomething = false;
        ChangeModeWithDoubles(true);
        ClearBoard();
        doublesHand = new Ma();
        string enteredParameter = (string)e.Parameter;
        switch (enteredParameter)
        {
            case "Double":
                coinsPlayedOnDoubles = wonCoins;
                wonCoins = 0;
                break;
            case "SemiDouble":
                coinsPlayedOnDoubles = wonCoins / 2 + wonCoins % 2;
                wonCoins -= coinsPlayedOnDoubles;
                SetNewCoins(wonCoins);
                break;
        }
        deck = new Deck();
        List<Image> backImgs = [];

        for (int i = 0; i < 5; i++)
        {
            Card c = deck.Roba();
            c.BocaAvall = true;
            doublesHand.Afegeix(c);
            Image cardImage = new Image
            {
                Source = (ImageSource)FindResource("Dors05"),
                Margin = new Thickness(0,-2500,0,0),
                Tag = c,
            };
            ScaleTransform sct = new ScaleTransform
            {
                ScaleX = 1,
                ScaleY = 1, 
            };
            cardImage.RenderTransformOrigin = new Point(0.5, 0.5);
            cardImage.RenderTransform = sct;
            cardImage.SetValue(Grid.ColumnProperty, i+1);
            grdCardsPlacement.Children.Add(cardImage);
            backImgs.Add(cardImage);
            StartCardAnimCoroutine(cardImage, new Thickness(0));
        }

        PlayDoubles(backImgs);
    }

    private async Task PlayDoubles(List<Image> cards)
    {
        await Task.Delay(TimeSpan.FromSeconds(0.4f));
        Random random = new Random();
        int selRandomCard = random.Next(1, 6);
        crupierCard = doublesHand[selRandomCard-1];
        foreach (Image img in cards)
        {
            img.MouseDown += RotateCard;
            img.MouseDown += OnDoublesPlayerClickedCard;
        }
        var selfImg = (Image)grdCardsPlacement.Children.Cast<UIElement>()
            .FirstOrDefault(x => (int)x.GetValue(Grid.ColumnProperty) == selRandomCard)!;
        MouseButtonEventArgs mouseEventArgs = new MouseButtonEventArgs(Mouse.PrimaryDevice, 0, MouseButton.Left)
        {
            RoutedEvent = MouseLeftButtonDownEvent,
            Source = selfImg
        };
        RotateCard(selfImg, mouseEventArgs);
    }

    private void OnDoublesPlayerClickedCard(object sender, MouseButtonEventArgs e)
    {
        var inBoardCards = grdCardsPlacement.Children.Cast<UIElement>();
        var boardCards = inBoardCards as UIElement[] ?? inBoardCards.ToArray();
        foreach (var card in boardCards)
        {
            card.MouseDown -= RotateCard;
            card.MouseDown -= OnDoublesPlayerClickedCard;
        }

        var cardsRotated = boardCards.Where(x => !((Card)((Image)x).Tag).BocaAvall)
            .Select(x => (int)x.GetValue(Grid.ColumnProperty));
        Card? playerCard = null;
        foreach (var card in cardsRotated)
        {
            if(!doublesHand[card-1].Equals(crupierCard))
                playerCard = doublesHand[card-1];
        }

        brdrWinningText.Visibility = Visibility.Visible;

        if (playerCard is not null && playerCard.CompareTo(crupierCard) > 0)
        {
            wonCoins = coinsPlayedOnDoubles * 2;
            txtWin.Text = $"Won {wonCoins}";
            coinsPlayedOnDoubles = 0;
            timesPlayedDoubles++;
            if (timesPlayedDoubles < 5)
            {
                wonSomething = true;
                grdDoublesButton.Visibility = Visibility.Visible;
            }
            else
            {
                DeactivateInputsForSeconds(3);
                ClearBoard();
                ChangeModeWithDoubles(false);
            }

        }
        else
        {
            coinsPlayedOnDoubles = 0;
            txtWin.Text = $"Lost";
            DeactivateInputsForSeconds(3);
            ClearBoard();
            ChangeModeWithDoubles(false);
        }

    }
    
    private void PlayCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        playedCards = true;
        bool won = true;
        if (currHand.HiHaEscalaReialDeColor){    
            txtWin.Text = "ESCALA REAL DE COLOR";
            wonCoins = GetWinnings("Escala real de color");
        }
        else if(currHand.HiHaEscalaDeColor){    
            txtWin.Text = "ESCALA DE COLOR";
            wonCoins = GetWinnings("Escala de color");
        }
        else if(currHand.HiHaPoker){    
            txtWin.Text = "POKER";
            wonCoins = GetWinnings("Poker");
        }
        else if(currHand.HiHaFull){    
            txtWin.Text = "FULL";
            wonCoins = GetWinnings("Full");
        }
        else if(currHand.HiHaColor){    
            txtWin.Text = "COLOR";
            wonCoins = GetWinnings("Color");
        }
        else if(currHand.HiHaEscala){    
            txtWin.Text = "ESCALA";
            wonCoins = GetWinnings("Escala");
        }
        else if(currHand.HiHaTrio){    
            txtWin.Text = "TRIO";
            wonCoins = GetWinnings("Trio");
        }
        else if(currHand.HiHaDobleParella){    
            txtWin.Text = "DOBLE PARELLA";
            wonCoins = GetWinnings("Doble parella");
        }
        else if(currHand.HiHaParellaMinima(Valor.Jota)){        
            txtWin.Text = "MINIMA PARELLA";
            wonCoins = GetWinnings("Jacks or més");
        }
        else {
            won = false;
        }

        if (won) {
            //todo change styles.
            grdDoublesButton.Visibility = Visibility.Visible;
            brdrWinningText.Visibility = Visibility.Visible;
            wonSomething = true;
        }
        isPlaying = false;
        ChangeBetImage();
    }
    
    private void SortCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        SortCardsAsync();
        cardsToChange.Clear();
    }

    private void ChangeCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        if (cardsToChange.Count > 0)
        {
            List<Image> toDelete = new();
            for (int i = 0; i < cardsToChange.Count; i++)
            {
                foreach (UIElement elem in grdCardsPlacement.Children)
                {
                    if (Grid.GetColumn(elem) == cardsToChange[i] + 1)
                    {
                        StartCardAnimCoroutine((Image)elem, new Thickness(0,-2500,0,0));
                        toDelete.Add((Image)elem);
                    }
                }
                var c = CreateNewCard(cardsToChange[i]+1);
                currHand[cardsToChange[i]] = c;
            }

            foreach (var img in toDelete)
            {
                grdCardsPlacement.Children.Remove(img);
            }
            cardsToChange.Clear();
            changedCards = true;
            //btnChangeCards.Content = "Play Cards";
        }
        PlayCardsBindingOnExecuted(sender, e);
    }

    private void StartBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        currHand.Clear();
        ClearBoard();
        isPlaying = true;
        deck = new Deck();
        internalCoinsPlayed = coinsPlayed;
        coinsPlayed = 0;
        changedCards = false;
        playedCards = false;
        for (int i = 0; i < 5; i++)
        {
            var c = CreateNewCard(i+1);
            currHand.Afegeix(c);
        }
    }
    
    private void AddCoinsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        if (isOnBonusRound)
        {
            ClearBoard();
        }
        brdrWinningText.Visibility = Visibility.Hidden;
        ChangeModeWithDoubles(false);
        wonSomething = false;
        timesPlayedDoubles = 0;

        grdDoublesButton.Visibility = Visibility.Collapsed;
        if(currHand.Count > 0)
            currHand.Clear();
        
        isOnBonusRound = false;
        int played = int.Parse((string)e.Parameter);
        if (coinsPlayed + played > 5)
            played = 5 - coinsPlayed;
        coinsPlayed += played;
        SetNewCoins(-played);
        ChangeBetImage();
        if (coinsPlayed == 5)
        {
            StartBindingOnExecuted(sender, e);
        }

        if (wonCoins == 0 && coinsPlayedOnDoubles == 0) return;
        SetNewCoins(wonCoins+ coinsPlayedOnDoubles);
        wonCoins = 0;
        coinsPlayedOnDoubles = 0;
    }

    private void StartCardAnimCoroutine(Image? im, Thickness targetThicnkess, float durationTime = 1f)
    {
        if (im is null) return;
            MoveCardAnimation(im, targetThicnkess, durationTime);
    }

    private void MoveCardAnimation(Image? img, Thickness targetThickness, float durationTime = 1f)
    {
        ThicknessAnimation cardAnim = new ThicknessAnimation
        {
            From = img.Margin,
            To = targetThickness,
            Duration = TimeSpan.FromSeconds(durationTime),
            EasingFunction = new QuadraticEase()
        };
        img.BeginAnimation(MarginProperty, cardAnim);
    }

    private void Card_OnClick(object sender, RoutedEventArgs e)
    {
        if(changedCards) return;
        Image clickedCard = (Image)sender;
        Card card = (Card)clickedCard.Tag;
        Thickness targetThickness;
        if (clickedCard.Margin.Top == 0)
        {
            targetThickness = new Thickness(0, -20, 0, 20);
            cardsToChange.Add((int)clickedCard.GetValue(Grid.ColumnProperty) - 1);
            card.SelfCrossImage.Visibility = Visibility.Visible;
        }
        else
        {
            targetThickness = new Thickness(0, 0, 0, 0);
            cardsToChange.Remove((int)clickedCard.GetValue(Grid.ColumnProperty) - 1);
            card.SelfCrossImage.Visibility = Visibility.Hidden;
        }

        //btnChangeCards.Content = cardsToChange.Count > 0 ? "Change Cards" : "Play Cards";
        StartCardAnimCoroutine(clickedCard, targetThickness, 0.2f);
    }

    private void ChangeBetImage()
    {
        imgTotalCoins.Source = coinsPlayed switch
        {
            0 => null,
            1 => new BitmapImage(new Uri("pack://application:,,,/Resources/Images/Coins/1 coin.png")),
            2 => new BitmapImage(new Uri("pack://application:,,,/Resources/Images/Coins/2 coins.png")),
            3 => new BitmapImage(new Uri("pack://application:,,,/Resources/Images/Coins/3 coins.png")),
            4 => new BitmapImage(new Uri("pack://application:,,,/Resources/Images/Coins/4 coins.png")),
            5 => new BitmapImage(new Uri("pack://application:,,,/Resources/Images/Coins/5 coins.png")),
            _ => null
        };
    }
    
    private Card CreateNewCard(int columnIndex)
    {
        Card c = deck.Roba();
        Image crossImage = new Image
        {
            Source = new BitmapImage(new Uri("pack://application:,,,/Resources/Images/redCross.png")),
            Stretch = Stretch.Fill,
            IsHitTestVisible = false,
            Tag = null,
            Visibility = Visibility.Hidden
        };
        c.SelfCrossImage = crossImage;
        Image? cardImage = new Image
        {
            Source = (ImageSource)FindResource(c.ImageKey),
            Margin = new Thickness(0,-500,0,520),
            Tag = c
        };
        cardImage.MouseDown += Card_OnClick;
        cardImage.SetValue(Grid.ColumnProperty, columnIndex);
        crossImage.SetValue(Grid.ColumnProperty, columnIndex);
        grdCardsPlacement.Children.Add(cardImage);
        grdCardsPlacement.Children.Add(crossImage);
        StartCardAnimCoroutine(cardImage, new Thickness(0,0,0,0));
        return c;
    }

    private void ClearBoard()
    {
        var cards = grdCardsPlacement.Children.Cast<UIElement>()
            .Where(x => (int)x.GetValue(Grid.ColumnProperty) is >= 1 and <= 5).ToList();
        foreach (var card in cards)
        {
            grdCardsPlacement.Children.Remove(card);
        }

        txtWin.Text = "";
        brdrWinningText.Visibility = Visibility.Hidden;
    }

    int GetWinnings(string result)
    {
        return PayoutTable[result][internalCoinsPlayed - 1];
    }

    private async void SortCardsAsync()
    {
        var allElements = grdCardsPlacement.Children.Cast<UIElement>()
            .Where(x => (int)x.GetValue(Grid.ColumnProperty) is >= 1 and <= 5)
            .OrderBy(x => (int)x.GetValue(Grid.ColumnProperty)).ToList();
        var cards = allElements
            .Where(x => ((Image)x).Tag is not null).ToList();
        var crosses = allElements.Except(cards).Cast<Image>().Where(x => x.Visibility == Visibility.Visible).ToList();
        foreach (var cross in crosses)
        {
            cross.Visibility = Visibility.Hidden;
        }
        MoveCardAnimation(
            (Image)cards[0],
            new Thickness(600, 0, -600, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[1],
            new Thickness(300, 0, -300, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[3],
            new Thickness(-300, 0, 300, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[4],
            new Thickness(-600, 0, 600, 0), durationTime:0.2f);
        await Task.Delay(TimeSpan.FromSeconds(0.4));
        foreach (var uiElem in cards)
        {
            MoveCardAnimation((Image)uiElem, new Thickness(0), durationTime: 0f);
            uiElem.SetValue(Grid.ColumnProperty, 3);
        }
        currHand.Ordena();
        //
        for (var i = 0; i < cards.Count; i++)
        {
            var cardToMove = cards.First(x => (Card)((Image)x).Tag == currHand[i]);
            var thickness = i switch
            {
                0 => new Thickness(-600, 0, 600, 0),
                1 => new Thickness(-300, 0, 300, 0),
                2 => new Thickness(0, 0, 0, 0),
                3 => new Thickness(300, 0, -300, 0),
                4 => new Thickness(600, 0, -600, 0),
                _ => throw new ArgumentOutOfRangeException()
            };
            MoveCardAnimation((Image)cardToMove, thickness, durationTime: 0.1f);
            await Task.Delay(TimeSpan.FromSeconds(0.1));
            cardToMove.SetValue(Grid.ColumnProperty, i+1);
            Card c = (Card)((Image)cardToMove).Tag;
            c.SelfCrossImage.SetValue(Grid.ColumnProperty, i+1);
            MoveCardAnimation((Image)cardToMove, new Thickness(0), durationTime: 0);
        }
    }

    private void RotateCard(object sender, MouseButtonEventArgs e)
    {
        Image img = (Image)sender;
        Card c = (Card)img.Tag;
        c.BocaAvall = false;
        DoubleAnimation shrinkAnimation = new DoubleAnimation
        {
            From = 1,
            To = 0,
            Duration = TimeSpan.FromSeconds(0.2),
            AutoReverse = false,
            EasingFunction = new SineEase { EasingMode = EasingMode.EaseInOut }
        };

        shrinkAnimation.Completed += (s, e) =>
        {
            img.Source = (ImageSource)FindResource(c.ImageKey);
            DoubleAnimation expandAnimation = new DoubleAnimation
            {
                From = 0,
                To = 1,
                Duration = TimeSpan.FromSeconds(0.2),
                AutoReverse = false,
                EasingFunction = new SineEase { EasingMode = EasingMode.EaseInOut }
            };

            img.RenderTransform.BeginAnimation(ScaleTransform.ScaleXProperty, expandAnimation);
        };


        img.RenderTransform.BeginAnimation(ScaleTransform.ScaleXProperty, shrinkAnimation);
        img.MouseDown -= RotateCard;
    }

    private void SetNewCoins(int modification)
    {
        quantityOfCoins += modification;
        PlayerCoins = quantityOfCoins.ToString();
    }

    private void ChangeModeWithDoubles(bool enter)
    {
        if (enter)
        {
            isOnBonusRound = true;
            grdDoublesButton.Visibility = Visibility.Collapsed;
            MovePrizes(false);
            grdCardsPlacement.SetValue(Grid.RowSpanProperty,3);
            grdCardsPlacement.SetValue(Grid.RowProperty,0);
            AnimateBackgroundColor(false);
        }
        else
        {
            MovePrizes(true);
            grdCardsPlacement.SetValue(Grid.RowSpanProperty,1);
            grdCardsPlacement.SetValue(Grid.RowProperty,2);
            AnimateBackgroundColor(true);
        }
    }

    void AnimateBackgroundColor(bool isDoubleEntering)
    {
        SolidColorBrush targetSolidColorBrush = isDoubleEntering
            ? (SolidColorBrush)new BrushConverter().ConvertFromString("#793c47")!
            : (SolidColorBrush)new BrushConverter().ConvertFromString("#4f4340")!;
        SolidColorBrush currSolidColorBrush = (SolidColorBrush)wndGame.Background;
        wndGame.Background = currSolidColorBrush;
        ColorAnimation backgroundAnimation = new ColorAnimation
        {
            From = ((SolidColorBrush)wndGame.Background).Color,
            To = targetSolidColorBrush.Color,
            Duration = new Duration(TimeSpan.FromSeconds(0.25))
        };
        currSolidColorBrush.BeginAnimation(SolidColorBrush.ColorProperty, backgroundAnimation);
    }
    
    private void MovePrizes(bool enter)
    {
        Thickness newThickness = enter ? new Thickness(0) : new Thickness(0,-1000,0,1000);
        ThicknessAnimation movePrizesAnimation = new ThicknessAnimation
        {
            From = brdrPrizes.Margin,
            To = newThickness,
            Duration = TimeSpan.FromSeconds(0.5f)
        };
        brdrPrizes.BeginAnimation(MarginProperty,movePrizesAnimation);
    }
    
    void DeactivateInputsForSeconds(int seconds)
    {
        dkPnlGeneral.IsHitTestVisible = false;
        var frame = new DispatcherFrame();
        Thread thread = new Thread(() =>
        {
            Thread.Sleep(TimeSpan.FromSeconds(seconds));
            frame.Continue = false;
        });
        thread.Start();
        Dispatcher.PushFrame(frame);
        dkPnlGeneral.IsHitTestVisible = true;
    }
}