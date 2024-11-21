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
    private int inDoublesCardsRotated = 0;
    private Card crupierCard;

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
        quantityOfCoins = 1000;
        deck = new Deck();
        currHand = new Ma();
        cardsToChange = new List<int>();
        PlayerCoins = quantityOfCoins.ToString();
        DataContext = this;
    }

    void CreateCommands()
    {
        CommandBinding changeCardsBinding = new CommandBinding(PokerCommands.ChangeCards);
        changeCardsBinding.Executed += ChangeCardsBindingOnExecuted;
        changeCardsBinding.CanExecute += (_, e) => { e.CanExecute = currHand.Count > 0 && !playedCards; };
        CommandBindings.Add(changeCardsBinding);
        
        CommandBinding addCoinsBinding = new CommandBinding(PokerCommands.AddCoins);
        addCoinsBinding.Executed += AddCoinsBindingOnExecuted;
        addCoinsBinding.CanExecute += (_, e) => { e.CanExecute = !isPlaying; };
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
        CommandBindings.Add(playForDoubleBinding);
    }

    private void PlayForDoubleBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        grdDoublesButton.Visibility = Visibility.Collapsed;
        ClearBoard();
        inDoublesCardsRotated = 0;
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
                quantityOfCoins += wonCoins;
                PlayerCoins = quantityOfCoins.ToString();
                break;
        }
        deck = new Deck();
        List<Image> backImgs = [];

        for (int i = 0; i < 5; i++)
        {
            Card c = deck.Roba();
            doublesHand.Afegeix(c);
            Image cardImage = new Image
            {
                Source = (ImageSource)FindResource("Dors05"),
                Margin = new Thickness(0,-2500,0,0),
                Tag = c.ImageKey,
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
        foreach (var card in inBoardCards)
        {
            card.MouseDown -= RotateCard;
            card.MouseDown -= OnDoublesPlayerClickedCard;
        }

        var cardsRotated = inBoardCards.Where(x => bool.TryParse(((Image)x).Tag.ToString(), out var _))
            .Select(x => (int)x.GetValue(Grid.ColumnProperty));
        Card? playerCard = null;
        foreach (var card in cardsRotated)
        {
            if(!doublesHand[card-1].Equals(crupierCard))
                playerCard = doublesHand[card-1];
        }

        if (playerCard is not null && playerCard.CompareTo(crupierCard) < 0)
        {
            wonCoins += coinsPlayedOnDoubles * 2;
            txtWin.Text = $"Won {wonCoins}";
            coinsPlayedOnDoubles = 0;
        }
        else
        {
            coinsPlayedOnDoubles = 0;
            txtWin.Text = $"Lost";
        }

        dkPnlGeneral.IsHitTestVisible = false;
        var frame = new DispatcherFrame();
        Thread thread = new Thread(() =>
        {
            Thread.Sleep(TimeSpan.FromMilliseconds(3000));
            frame.Continue = false;
        });
        thread.Start();
        Dispatcher.PushFrame(frame);
        dkPnlGeneral.IsHitTestVisible = true;
        ClearBoard();
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
            isOnBonusRound = true;
            grdDoublesButton.Visibility = Visibility.Visible;
        }
        isPlaying = false;
        SlideButtonsOut(true);
    }
    
    private void SortCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        SortCardsAsync();
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
            btnChangeCards.Content = "Play Cards";
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
        quantityOfCoins -= coinsPlayed;
        PlayerCoins = quantityOfCoins.ToString();
        coinsPlayed = 0;
        changedCards = false;
        playedCards = false;
        SlideButtonsOut();
        ChangeBetImage();
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

        isOnBonusRound = false;
        int played = int.Parse((string)e.Parameter);
        if (played > (quantityOfCoins-coinsPlayed))
            played = quantityOfCoins - coinsPlayed;
        if (coinsPlayed + played > 5)
            played = 5 - coinsPlayed;
        coinsPlayed += played;
        ChangeBetImage();
        if (coinsPlayed == 5)
        {
            StartBindingOnExecuted(sender, e);
        }

        if (wonCoins == 0 && coinsPlayedOnDoubles == 0) return;
        quantityOfCoins += wonCoins;
        quantityOfCoins += coinsPlayedOnDoubles;
        PlayerCoins = quantityOfCoins.ToString();
        wonCoins = 0;
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
        Thickness targetThickness;
        if (clickedCard.Margin.Top == 0)
        {
            targetThickness = new Thickness(0, -20, 0, 20);
            cardsToChange.Add((int)clickedCard.GetValue(Grid.ColumnProperty) - 1);
        }
        else
        {
            targetThickness = new Thickness(0, 0, 0, 0);
            cardsToChange.Remove((int)clickedCard.GetValue(Grid.ColumnProperty) - 1);
        }

        btnChangeCards.Content = cardsToChange.Count > 0 ? "Change Cards" : "Play Cards";
        StartCardAnimCoroutine(clickedCard, targetThickness, 0.2f);
    }

    private void SlideButtonsOut(bool enter = false)
    {
        ThicknessAnimation bottomSideAnimation = new ThicknessAnimation
        {
            From = enter ? new Thickness(-250) : new Thickness(0, 0, 0, 15),
            To = enter ? new Thickness(0,0,0,15) : new Thickness(0, 0, 0, -250),
            Duration = TimeSpan.FromSeconds(0.5f),
            EasingFunction = new QuadraticEase()
        };
        bottomSidePanel.BeginAnimation(MarginProperty, bottomSideAnimation);
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
        Image? cardImage = new Image
        {
            Source = (ImageSource)FindResource(c.ImageKey),
            Margin = new Thickness(0,-2500,0,0),
            Tag = c.ImageKey
        };
        cardImage.MouseDown += Card_OnClick;
        cardImage.SetValue(Grid.ColumnProperty, columnIndex);
        grdCardsPlacement.Children.Add(cardImage);
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
    }

    int GetWinnings(string result)
    {
        return PayoutTable[result][internalCoinsPlayed - 1];
    }

    private async void SortCardsAsync()
    {
        var cards = grdCardsPlacement.Children.Cast<UIElement>()
            .Where(x => (int)x.GetValue(Grid.ColumnProperty) is >= 1 and <= 5).OrderBy(x => (int)x.GetValue(Grid.ColumnProperty)).ToList();
        MoveCardAnimation(
            (Image)cards[0],
            new Thickness(570, 0, -570, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[1],
            new Thickness(285, 0, -285, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[3],
            new Thickness(-285, 0, 285, 0), durationTime:0.2f);
        MoveCardAnimation(
            (Image)cards[4],
            new Thickness(-570, 0, 570, 0), durationTime:0.2f);
        await Task.Delay(TimeSpan.FromSeconds(0.4));
        foreach (var uiElem in cards)
        {
            MoveCardAnimation((Image)uiElem, new Thickness(0), durationTime: 0f);
            uiElem.SetValue(Grid.ColumnProperty, 3);
        }
        currHand.Ordena();
        for (var i = 0; i < cards.Count; i++)
        {
            var cardToMove = cards.First(x => (string)((Image)x).Tag == currHand[i].ImageKey);
            var thickness = i switch
            {
                0 => new Thickness(-570, 0, 570, 0),
                1 => new Thickness(-285, 0, 285, 0),
                2 => new Thickness(0, 0, 0, 0),
                3 => new Thickness(285, 0, -285, 0),
                4 => new Thickness(570, 0, -570, 0),
                _ => throw new ArgumentOutOfRangeException()
            };
            MoveCardAnimation((Image)cardToMove, thickness, durationTime: 0.1f);
            await Task.Delay(TimeSpan.FromSeconds(0.1));
            cardToMove.SetValue(Grid.ColumnProperty, i+1);
            MoveCardAnimation((Image)cardToMove, new Thickness(0), durationTime: 0);
        }
    }

    private void RotateCard(object sender, MouseButtonEventArgs e)
    {
        Image img = (Image)sender;
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
            img.Source = (ImageSource)FindResource((string)img.Tag);
            DoubleAnimation expandAnimation = new DoubleAnimation
            {
                From = 0,
                To = 1,
                Duration = TimeSpan.FromSeconds(0.2),
                AutoReverse = false,
                EasingFunction = new SineEase { EasingMode = EasingMode.EaseInOut }
            };
            expandAnimation.Completed += (_, _) =>
            {
                img.Tag = true;
            };

            img.RenderTransform.BeginAnimation(ScaleTransform.ScaleXProperty, expandAnimation);
        };


        img.RenderTransform.BeginAnimation(ScaleTransform.ScaleXProperty, shrinkAnimation);
        img.MouseDown -= RotateCard;
        inDoublesCardsRotated++;
    }
    
    
}