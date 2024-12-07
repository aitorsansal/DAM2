﻿using System.Windows;
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
        public enum OrdenationType { Bubble, Pancake, QuickSort }
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

            if (sortingTypeCB.SelectedItem != null)
            {
                switch ((OrdenationType)sortingTypeCB.SelectedItem)
                {
                    case OrdenationType.Bubble:
                        BubbleSort();
                        break;
                    case OrdenationType.Pancake:
                        PancakeSort();
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
            toOrderArray = new int[nElements];
            rectangles = new Rectangle[nElements];
            isCanceled = false;

            sortingTypeCB.ItemsSource = Enum.GetValues(typeof(OrdenationType));
            isElipse = (bool)IsElipseCheckBox.IsChecked!;
            Background = bgColor;

            toOrderArray = (bool)typeOfGenSwitch.IsChecked!
                ? CreateRandomArray(nElements)
                : CreateInverseSortedArray(nElements);


            double width = sortingCanvas.ActualWidth / nElements;
            double height = 0;

            for (int i = 0; i < nElements; i++)
            {
                if (!isElipse)
                {
                    height = sortingCanvas.ActualHeight * toOrderArray[i] / nElements;
                    Rectangle rect = new Rectangle
                    {
                        Stroke = new SolidColorBrush(Colors.Black),
                        Width = width,
                        Height = height,
                        StrokeThickness = (int)gruixMarcBox.Value,
                        RadiusX = (int)radiColumnsBox.Value,
                        RadiusY = (int)radiColumnsBox.Value
                    };

                    Canvas.SetLeft(rect, i * width);
                    Canvas.SetTop(rect, sortingCanvas.ActualHeight - height);

                    rectangles[i] = rect;
                    sortingCanvas.Children.Add(rect);
                }
                else
                {
                    height = sortingCanvas.ActualHeight - sortingCanvas.ActualHeight * toOrderArray[i] / nElements;
                    Rectangle ellipse = new Rectangle
                    {
                        Stroke = new SolidColorBrush(Colors.Black),
                        Width = width,
                        Height = width,
                        StrokeThickness = (int)gruixMarcBox.Value,
                        RadiusX = 100,
                        RadiusY = 100,
                    };

                    Canvas.SetLeft(ellipse, i * width);
                    Canvas.SetTop(ellipse, height);

                    rectangles[i] = ellipse;
                    sortingCanvas.Children.Add(ellipse);
                }

                ColumnsRepaint(i);
            }
        }
        private void Stop_OnClick(object sender, RoutedEventArgs e)
        {
            isCanceled = true;
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
                    if (toOrderArray[j] > toOrderArray[j + 1])
                    {
                        ChangeElements(j, j + 1);
                    }
                }
            }
        }
        #endregion

        #region QuickSort

        public void QuickSort()
        {
            Particio(toOrderArray, 0, toOrderArray.Length - 1, 0);
        }
        public int Particio(int[] numeros, int esq, int drt, int nComp)
        {
            int i, j, x;
            i = esq;
            j = drt;
            int temp;
            x = numeros[(esq + drt) / 2];
            do
            {
                while (numeros[i] < x)
                {
                    nComp++;
                    i++;
                }
                while (x < numeros[j])
                {
                    nComp++;
                    j--;
                }
                if (i <= j)
                {
                    ChangeElements(i, j);

                    i++;
                    j--;
                }
            } while (i <= j);
            if (esq < j)
                nComp = Particio(numeros, esq, j, nComp);
            if (i < drt)
                nComp = Particio(numeros, i, drt, nComp);
            return nComp;
        }

        #endregion

        #region Pancake
        public int findMax(int[] arr, int n)
        {
            int mi, i;
            for (mi = 0, i = 0; i < n; ++i)
                if (arr[i] > arr[mi])
                    mi = i;

            return mi;
        }
        public int PancakeSort(int[] arr = null, int n = 0)
        {
            if (arr == null) arr = toOrderArray;
            if(n == 0) n = nElements;
            int i = 0;
            int temp, start = 0;

            for (int curr_size = n; curr_size > 1; --curr_size)
            {
                int mi = findMax(arr, curr_size);
                if (mi != curr_size - 1)
                {
                    i = mi;
                    while (start < i)
                    {
                        ChangeElements(i, start);
                        start++;
                        i--;
                    }
                    i = 0;
                    start = 0;
                    i = curr_size - 1;
                    while (start < i)
                    {
                        ChangeElements(i, start);
                        start++;
                        i--;
                    }
                    i = 0;
                    start = 0;

                }
            }
            return 0;
        }

        #endregion

        #endregion

        #region Pintar & Animar
        private void ChangeElements(int pos1, int pos2)
        {
            
            (toOrderArray[pos1], toOrderArray[pos2]) = (toOrderArray[pos2], toOrderArray[pos1]);
            if (pos2 >= nElements) pos2 = nElements - 1;
            if (pos1 >= nElements) pos1 = nElements - 1;

            Rectangle rect1 = rectangles[pos1];
            Rectangle rect2 = rectangles[pos2];

            if (pos1 >= 0 && pos1 < toOrderArray.Length && pos2 >= 0 && pos2 < toOrderArray.Length)
            {
                rect1.Fill = changingColor;
                rect2.Fill = changingColor;

                
                WaitForMiliseconds(Convert.ToInt32(string.IsNullOrEmpty(waitTimeUpDown.Text) ? "0" : waitTimeUpDown.Text));

                SetHeight(pos1);
                SetHeight(pos2);
            }
            ColumnsRepaint(pos1);
            ColumnsRepaint(pos2);
        }

        void SetHeight(int pos)
        {
            Rectangle rect = rectangles[pos];
            if (!isElipse)
            {
                var height = sortingCanvas.ActualHeight / toOrderArray.Length * toOrderArray[pos];
                rect.Height = height;
                Canvas.SetTop(rect, sortingCanvas.ActualHeight - rect.Height);
            }
            else
            {
                var height = sortingCanvas.ActualHeight -
                             sortingCanvas.ActualHeight * toOrderArray[pos] / nElements;
                Canvas.SetTop(rect, height);
            }
        }
        private void ColumnsRepaint(int pos)
        {
            int nElem = toOrderArray.Length;
            rectangles[pos].Fill = toOrderArray[pos] == pos+1 ? correctColor : incorrectColor;

        }

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
                thread.Interrupt();
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


    }