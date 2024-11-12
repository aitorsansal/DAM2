using System.Windows.Input;

namespace Commands;

public class EditorCommands
{
    private static RoutedUICommand augmentSize;
    private static RoutedUICommand reduceSize;

    static EditorCommands()
    {
        augmentSize = new RoutedUICommand("Augment Size", "AugmentSize", typeof(EditorCommands));
        reduceSize = new RoutedUICommand("Reduce Size", "ReduceSize", typeof(EditorCommands));
    }
    
    public static RoutedUICommand AugmentSize => augmentSize;
    public static RoutedUICommand ReduceSize => reduceSize;
}