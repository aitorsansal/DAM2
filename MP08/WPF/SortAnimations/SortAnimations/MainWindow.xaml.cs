using System.Runtime.InteropServices.ComTypes;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace SortAnimations;

    public partial class MainWindow : Window
    {
        #region Members
        public enum OrdenationType { Bubble, SelectionSort, QuickSort }
        int[] toOrderArray;
        Rectangle[] rectangles;
        int nElements;
        bool isElipse;
        bool isCanceled;
        readonly SolidColorBrush incorrectColor = new(Colors.Red);
        readonly SolidColorBrush correctColor = new(Colors.Green);
        readonly SolidColorBrush changingColor = new(Colors.Orange);
        readonly SolidColorBrush bgColor = new(Colors.Plum);
        public enum easingTypes
        {
            BackEase,
            BounceEase,
            CircleEase,
            CubicEase,
            ElasticEase,
            ExponentialEase,
            PowerEase,
            QuadraticEase,
            QuarticEase,
            QuinticEase,
            SineEase
        }
        #endregion

        #region Constructor
        public MainWindow()
        {
            toOrderArray = default;
            nElements = 0;
            
            InitializeComponent();

            sortingTypeCB.ItemsSource = Enum.GetValues(typeof(OrdenationType));
            incorrectColor = new SolidColorBrush((Color)IncorrectColorPicker.SelectedColor);
            correctColor = new SolidColorBrush((Color)CorrectColorPicker.SelectedColor);
            changingColor = new SolidColorBrush((Color)ChangingColorPicker.SelectedColor);
            bgColor = new SolidColorBrush((Color)BackgroundColorPicker.SelectedColor);
            Background = bgColor;
            easingTypeCB.ItemsSource = Enum.GetValues(typeof(easingTypes));
            easingModeCB.ItemsSource = Enum.GetValues(typeof(EasingMode));
            

        } 
        #endregion

        #region Button Click Handlers

        private void Sort_OnClick(object sender, RoutedEventArgs e)
        {
            isCanceled = false;
            if (sortingTypeCB.SelectedItem != null)
            {
                switch ((OrdenationType)sortingTypeCB.SelectedItem)
                {
                    case OrdenationType.Bubble:
                        BubbleSort();
                        break;
                    case OrdenationType.SelectionSort:
                        SelectionSort();
                        break;
                    case OrdenationType.QuickSort:
                        QuickSort();
                        break;
                }
            }

        }
        private void Generate_OnClick(object sender, RoutedEventArgs e)
        {
            sortingCanvas.Children.Clear();
            nElements = Convert.ToInt32(nElemBox.Text);
            isCanceled = false;
            toOrderArray = new int[nElements];
            rectangles = new Rectangle[nElements];
            isElipse = (bool)IsElipseCheckBox.IsChecked!;

            toOrderArray = (bool)typeOfGenSwitch.IsChecked!
                ? CreateRandomArray(nElements)
                : CreateInverseSortedArray(nElements);


            double width = sortingCanvas.ActualWidth / nElements;

            for (int i = 0; i < nElements; i++)
            {
                Rectangle rect = new Rectangle
                {
                    Stroke = new SolidColorBrush(Colors.Black),
                    Width = width,
                    StrokeThickness = (int)columnWidth.Value
                };
                Canvas.SetZIndex(rect, 1);
                Canvas.SetBottom(rect, 0);
                Canvas.SetLeft(rect, i * sortingCanvas.ActualWidth/nElements);
                
                rectangles[i] = rect;
                sortingCanvas.Children.Add(rect);
                ColumnsRepaint(i);
            }
        }
        private void Stop_OnClick(object sender, RoutedEventArgs e)
        {
            isCanceled = true;
        } 
        #endregion

        #region Drawing
        private void ChangeElements(int pos1, int pos2)
        {
            (toOrderArray[pos1], toOrderArray[pos2]) = (toOrderArray[pos2], toOrderArray[pos1]);

            Rectangle rect1 = rectangles[pos1];
            Rectangle rect2 = rectangles[pos2];

            rect1.Fill = changingColor;
            rect2.Fill = changingColor;
            Panel.SetZIndex(rect1, 50);
            Panel.SetZIndex(rect2, 50);
            ChangingAnimation(rect1, rect2);
            long waitTime = Convert.ToInt32(
                string.IsNullOrEmpty(waitTimeUpDown.Text) ? "0" : waitTimeUpDown.Text);
            waitTime -= 5;
            waitTime = waitTime < 0 ? 0 : waitTime;
            WaitForMiliseconds(waitTime);
            ColumnsRepaint(pos1);
            ColumnsRepaint(pos2);

            Panel.SetZIndex(rect1, 1);
            Panel.SetZIndex(rect2, 1);
        }

        void SetHeight(int pos)
        {
            Rectangle rect = rectangles[pos];
            if (IsElipseCheckBox.IsChecked == false)
            {
                var height = sortingCanvas.ActualHeight / toOrderArray.Length * toOrderArray[pos];
                rect.Height = height;
                rect.RadiusX = columnRadius.Value ?? 0;
                rect.RadiusY = columnRadius.Value ?? 0;
            }
            else
            {
                rect.Height = rect.Width;
                Canvas.SetTop(rect, sortingCanvas.ActualHeight - sortingCanvas.ActualHeight * toOrderArray[pos] / nElements);
                rect.RadiusX = 100;
                rect.RadiusY = 100;
            }
        }
        private void ColumnsRepaint(int pos)
        {
            rectangles[pos].Fill = toOrderArray[pos] == pos+1 ? correctColor : incorrectColor;
            rectangles[pos].StrokeThickness = columnWidth.Value ?? 0;
            SetHeight(pos);
        }

        #endregion

        #region Animations

        private void ChangingAnimation(Rectangle rect1, Rectangle rect2)
        {
            Storyboard board = new Storyboard();
            var waitingTime = waitTimeUpDown.Value ?? 0;
            DoubleAnimation rect1Anim = new DoubleAnimation
            {
                Duration = TimeSpan.FromMilliseconds(waitingTime),
                FillBehavior = FillBehavior.Stop
            };
            DoubleAnimation rect2Anim = new DoubleAnimation
            {
                Duration = TimeSpan.FromMilliseconds(waitingTime),
                FillBehavior = FillBehavior.Stop
            };
            var easingFunction = GetEasingType();
            if (easingFunction != null)
            {
                EasingMode? easeMode = (EasingMode?)easingModeCB.SelectedItem;
                if(easeMode is not null)
                    easingFunction.EasingMode = (EasingMode)easeMode;
                rect1Anim.EasingFunction = easingFunction;
                rect2Anim.EasingFunction = easingFunction;
            }
            

            

            if (IsHeightAnimationCheckBox.IsChecked ==true)
            {
                if (IsElipseCheckBox.IsChecked == true)
                {
                    rect1Anim.From = Canvas.GetTop(rect1);
                    rect1Anim.To = Canvas.GetTop(rect2);
                    
                    rect2Anim.From = Canvas.GetTop(rect2);
                    rect2Anim.To = Canvas.GetTop(rect1);
                    
                    Storyboard.SetTargetProperty(rect1Anim, new PropertyPath("(Canvas.Top)"));
                    Storyboard.SetTargetProperty(rect2Anim, new PropertyPath("(Canvas.Top)"));
                }
                else
                {
                    rect1Anim.From = rect1.Height;
                    rect1Anim.To = rect2.Height;
                    
                    rect2Anim.From = rect2.Height;
                    rect2Anim.To = rect1.Height;

                    Storyboard.SetTargetProperty(rect1Anim, new PropertyPath("Height"));
                    Storyboard.SetTargetProperty(rect2Anim, new PropertyPath("Height"));
                }
            }
            else
            {
                rect1Anim.From = Canvas.GetLeft(rect1);
                rect1Anim.To = Canvas.GetLeft(rect2);

                rect2Anim.From = Canvas.GetLeft(rect2);
                rect2Anim.To = Canvas.GetLeft(rect1);

                Storyboard.SetTargetProperty(rect1Anim, new PropertyPath("(Canvas.Left)"));
                Storyboard.SetTargetProperty(rect2Anim, new PropertyPath("(Canvas.Left)"));
            }
            Storyboard.SetTarget(rect1Anim, rect1);
            Storyboard.SetTarget(rect2Anim, rect2);
            
            board.Children.Add(rect1Anim);
            board.Children.Add(rect2Anim);
            
            board.Begin();
        }

        #endregion
        
        
        #region Sort Methods

        #region Bubble

        void BubbleSort()
        {
            for (int i = 0; i < toOrderArray.Length - 1; i++)
            {
                for (int j = 0; j < toOrderArray.Length - i - 1; j++)
                {
                    if (toOrderArray[j] > toOrderArray[j + 1] && !isCanceled)
                    {
                        ChangeElements(j, j + 1);
                    }
                }
            }
        }
        #endregion

        #region QuickSort

        void QuickSort()
        {
            Partition(0, toOrderArray.Length - 1, 0);
        } 
        int Partition(int lft, int rght, int nComp)
        {
            int i, j, x;
            i = lft;
            j = rght;
            x = toOrderArray[(lft + rght) / 2];
            do
            {
                while (toOrderArray[i] < x)
                {
                    nComp++;
                    i++;
                }
                while (x < toOrderArray[j])
                {
                    nComp++;
                    j--;
                }
                if (i <= j && !isCanceled)
                {
                    ChangeElements(i, j);

                    i++;
                    j--;
                }
            } while (i <= j && !isCanceled);

            if (!isCanceled)
            {
                if (lft < j)
                    nComp = Partition(lft, j, nComp);
                if (i < rght)
                    nComp = Partition(i, rght, nComp);
                
            }
            return nComp;
        }

        #endregion

        #region SelectionSort
        void SelectionSort()
        {
            int n = toOrderArray.Length;
            for (int i = 0; i < n - 1; i++)
            {
                int minIndex = i;
                for (int j = i + 1; j < n; j++)
                {
                    if (toOrderArray[j] < toOrderArray[minIndex])
                    {
                        minIndex = j;
                    }
                }
                if(!isCanceled)
                    ChangeElements(minIndex, i);
            }
        }

        #endregion

        #endregion


        #region Auxiliar Methods

        #region ArrayCreation

        static int[] CreateRandomArray(int nElem)
        {
            Random random = new Random();
            int[] array = CreateSortedArray(nElem);

            for (int i = nElem - 1; i > 0; i--)
            {
                var nRandom = random.Next(0, i + 1);
                (array[i], array[nRandom]) = (array[nRandom], array[i]);
            }

            return array;
        }

        static int[] CreateSortedArray(int nElem)
        {
            int[] array = new int[nElem];

            for (int i = 0; i < nElem; i++) 
                array[i] = i + 1;

            return array;
        }

        int[] CreateInverseSortedArray(int nElem)
        {
            int[] array = new int[nElem];
            for (int i = 0; i < nElem; i++) 
                array[i] = nElements - i;
            return array;
        }
        #endregion

        #region Threads
        Thread thread;
        private void WaitForMiliseconds(double milliseconds)
        {
            if (!isCanceled)
            {
                var frame = new DispatcherFrame();
                thread = new Thread((() =>
                {
                    Thread.Sleep(TimeSpan.FromMilliseconds(milliseconds));
                    frame.Continue = false;
                }));
                thread.Start();
                Dispatcher.PushFrame(frame);
            }
            else
            {
                thread?.Interrupt();
            }
        }

        static Action action;
        static void DoEvents()
        {
            action = delegate { };
            Application.Current?.Dispatcher?.Invoke(
               DispatcherPriority.Background,
               action);
        }
        protected override void OnClosed(EventArgs e)
        {
            try
            {
                Application.Current.Dispatcher.InvokeShutdown();
                thread?.Abort();
                base.OnClosed(e);
            }
            catch
            {

            }
        }
        #endregion

        #region UIMethods

        private void Expander_OnMouseDown(object sender, MouseButtonEventArgs e)
        {
            ((Expander)sender).IsExpanded = !((Expander)sender).IsExpanded;
        }

        private void ColorPicker_ColorChanged(object sender, RoutedPropertyChangedEventArgs<Color?> e)
        {
            if(CorrectColorPicker is not null)
                correctColor.Color = CorrectColorPicker.SelectedColor ?? Colors.Green;
            if(IncorrectColorPicker is not null)
                incorrectColor.Color = IncorrectColorPicker.SelectedColor ?? Colors.Red;
            if(ChangingColorPicker is not null)
                changingColor.Color = ChangingColorPicker.SelectedColor ?? Colors.Orange;
            if(BackgroundColorPicker is not null)
                bgColor.Color = BackgroundColorPicker.SelectedColor ?? Colors.Plum;
        }

        private void ResetDefaults_OnClick(object sender, RoutedEventArgs e)
        {
            correctColor.Color = Colors.Green;
            incorrectColor.Color = Colors.Red;
            changingColor.Color = Colors.Orange;
            bgColor.Color = Colors.Plum;
            if(CorrectColorPicker is not null)
                CorrectColorPicker.SelectedColor = Colors.Green;
            if(IncorrectColorPicker is not null)
                IncorrectColorPicker.SelectedColor = Colors.Red;
            if(ChangingColorPicker is not null)
                ChangingColorPicker.SelectedColor = Colors.Orange;
            if(BackgroundColorPicker is not null)
                BackgroundColorPicker.SelectedColor = Colors.Plum;
        }
        
        private void ColumnValue_OnChanged(object sender, RoutedPropertyChangedEventArgs<object> e)
        {
            var nChild = sortingCanvas?.Children.OfType<Rectangle>().Count() ?? 0;
            for (int i = 0; i < nChild; i++)
            {
                ColumnsRepaint(i);
            }
        }

        private void IsElipse_OnValueChange(object sender, RoutedEventArgs e)
        {
            var nChild = sortingCanvas?.Children.OfType<Rectangle>().Count() ?? 0;
            for (int i = 0; i < nChild; i++)
            {
                ColumnsRepaint(i);
            }
        }
        
        #endregion

        #region Animation Methods
        
        private EasingFunctionBase? GetEasingType()
        {
            return (easingTypes)easingTypeCB.SelectedItem  switch
            {
                easingTypes.BackEase => new BackEase(),
                easingTypes.BounceEase => new BounceEase(),
                easingTypes.CircleEase => new CircleEase(),
                easingTypes.CubicEase => new CubicEase(),
                easingTypes.ElasticEase => new ElasticEase(),
                easingTypes.ExponentialEase => new ExponentialEase(),
                easingTypes.PowerEase => new PowerEase(),
                easingTypes.QuadraticEase => new QuadraticEase(),
                easingTypes.QuarticEase => new QuarticEase(),
                easingTypes.SineEase => new SineEase(),
                easingTypes.QuinticEase => new QuinticEase(),
                _ => null
            };
        }

        #endregion

        #endregion


    }