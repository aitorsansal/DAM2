﻿using System.Windows.Input;

namespace A1_WPF;

public class RelayCommand : ICommand
{
    private readonly Action<object> _executeAction;
    private readonly Predicate<object> _canExecuteAction;

//Constructors
    public RelayCommand(Action<object> executeAction)
    {
        _executeAction = executeAction;
        _canExecuteAction = null;
    }

    public RelayCommand(Action<object> executeAction, Predicate<object> canExecuteAction)
    {
        _executeAction = executeAction;
        _canExecuteAction = canExecuteAction;
    }

//Events
    public event EventHandler CanExecuteChanged
    {
        add { CommandManager.RequerySuggested += value; }
        remove { CommandManager.RequerySuggested -= value; }
    }

//Methods
    public bool CanExecute(object parameter)
    {
        return _canExecuteAction == null || _canExecuteAction(parameter);
    }

    public void Execute(object parameter)
    {
        _executeAction(parameter);
    }
}