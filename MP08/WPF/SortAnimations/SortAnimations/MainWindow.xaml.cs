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
                    StrokeThickness = (int)columnWidth.Value,
                };
                
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

                
            WaitForMiliseconds(Convert.ToInt32(string.IsNullOrEmpty(waitTimeUpDown.Text) ? "0" : waitTimeUpDown.Text));

            ColumnsRepaint(pos1);
            ColumnsRepaint(pos2);
        }

        void SetHeight(int pos)
        {
            Rectangle rect = rectangles[pos];
            if (IsElipseCheckBox.IsChecked == false)
            {
                var height = sortingCanvas.ActualHeight / toOrderArray.Length * toOrderArray[pos];
                rect.Height = height;
                Canvas.SetTop(rect, sortingCanvas.ActualHeight - rect.Height);
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
            Canvas.SetLeft(rect, pos * sortingCanvas.ActualWidth/nElements);
        }
        private void ColumnsRepaint(int pos)
        {
            rectangles[pos].Fill = toOrderArray[pos] == pos+1 ? correctColor : incorrectColor;
            rectangles[pos].StrokeThickness = columnWidth.Value ?? 0;
            SetHeight(pos);
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

        #endregion

        #endregion


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
    }