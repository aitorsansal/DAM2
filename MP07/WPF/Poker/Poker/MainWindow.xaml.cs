using System.ComponentModel.DataAnnotations;
using System.Text;
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

namespace Poker;

/// <summary>
/// Interaction logic for MainWindow.xaml
/// </summary>
public partial class MainWindow : Window
{
    private int quantityOfCoins;
    private int coinsPlayed;
    private int internalCoinsPlayed;
    private Deck deck;
    private Ma currHand;
    private List<int> cardsToChange;
    private bool changedCards;
    private bool playedCards;
    public MainWindow()
    {
        InitializeComponent();
    
        CreateCommands();
        quantityOfCoins = 100;
        deck = new Deck();
        currHand = new Ma();
        cardsToChange = new List<int>();
    }

    void CreateCommands()
    {
        CommandBinding changeCardsBinding = new CommandBinding(PokerCommands.ChangeCards);
        changeCardsBinding.Executed += ChangeCardsBindingOnExecuted;
        changeCardsBinding.CanExecute += (_, e) => { e.CanExecute = currHand.Count > 0 && !playedCards; };
        CommandBindings.Add(changeCardsBinding);
        
        CommandBinding addCoinsBinding = new CommandBinding(PokerCommands.AddCoins);
        addCoinsBinding.Executed += AddCoinsBindingOnExecuted;
        CommandBindings.Add(addCoinsBinding);

        CommandBinding startBinding = new CommandBinding(PokerCommands.StartGame);
        startBinding.Executed += StartBindingOnExecuted;
        startBinding.CanExecute += (_, e) => {e.CanExecute = coinsPlayed > 0; };
        CommandBindings.Add(startBinding);
        
        CommandBinding sortCardsBinding = new CommandBinding(PokerCommands.SortCards);
        sortCardsBinding.Executed += SortCardsBindingOnExecuted;
        sortCardsBinding.CanExecute += (_, e) => { e.CanExecute = currHand.Count > 0; };
        CommandBindings.Add(sortCardsBinding);
        
        CommandBinding playCardsBinding = new CommandBinding(PokerCommands.PlayCards);
        playCardsBinding.Executed += PlayCardsBindingOnExecuted;
        playCardsBinding.CanExecute += (_, e) => { e.CanExecute = !playedCards && currHand.Count > 0; };
        CommandBindings.Add(playCardsBinding);
    }

    private void PlayCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        playedCards = true;
        if (currHand.HiHaEscalaReialDeColor){    
            txtWin.Text = "ESCALA REIAL DE COLOR";
        }
        else if(currHand.HiHaEscalaDeColor){    
            txtWin.Text = "ESCALA DE COLOR";
        }
        else if(currHand.HiHaPoker){    
            txtWin.Text = "POKER";
        }
        else if(currHand.HiHaFull){    
            txtWin.Text = "FULL";
        }
        else if(currHand.HiHaColor){    
            txtWin.Text = "COLOR";
        }
        else if(currHand.HiHaEscala){    
            txtWin.Text = "ESCALA";
        }
        else if(currHand.HiHaTrio){    
            txtWin.Text = "TRIO";
        }
        else if(currHand.HiHaDobleParella){    
            txtWin.Text = "DOBLE PARELLA";
        }
        else if(currHand.HiHaParellaMinima(Valor.Jota)){        
            txtWin.Text = "MINIMA PARELLA";
        }
    }

    private void SortCardsBindingOnExecuted(object sender, ExecutedRoutedEventArgs e)
    {
        SortCardsAsync();
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
                4 => new Thickness(570, 0, -570, 0)
            };
            MoveCardAnimation((Image)cardToMove, thickness, durationTime: 0.1f);
            await Task.Delay(TimeSpan.FromSeconds(0.1));
            cardToMove.SetValue(Grid.ColumnProperty, i+1);
            MoveCardAnimation((Image)cardToMove, new Thickness(0), durationTime: 0);
        }
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
                        StartCardAnimCoroutine((Image)elem, new Thickness(0,-2500,0,0), deleteOnEnd:true);
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
        internalCoinsPlayed = coinsPlayed;
        quantityOfCoins -= coinsPlayed;
        coinsPlayed = 0;
        changedCards = false;
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
    }

    private async void StartCardAnimCoroutine(Image? im, Thickness targetThicnkess, float durationTime = 1f, bool deleteOnEnd = false)
    {
        if (im is null) return;
        await MoveCardAnimation(im, targetThicnkess, durationTime);
    }

    private async Task MoveCardAnimation(Image? img, Thickness targetThickness, float durationTime = 1f)
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

    private void SlideButtonsOut()
    {
        ThicknessAnimation bottomSideAnimation = new ThicknessAnimation
        {
            From = new Thickness(0, 0, 0, 15),
            To = new Thickness(0, 0, 0, -250),
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
}