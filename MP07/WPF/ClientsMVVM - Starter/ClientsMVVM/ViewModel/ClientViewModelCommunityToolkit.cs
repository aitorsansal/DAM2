using System;
using System.Collections.ObjectModel;
using ClientsMVVM.Model;
using ClientsMVVM.Repositiori;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace ClientsMVVM.ViewModel;

public partial class ClientViewModelCommunityToolkit : ObservableObject
{
    #region Fields

    [ObservableProperty, NotifyPropertyChangedFor(nameof(CompleteName)), NotifyPropertyChangedFor(nameof(IsValid)), 
     NotifyCanExecuteChangedFor(nameof(AddClientCommand)), NotifyCanExecuteChangedFor(nameof(ConfirmClientCommand))]
    private string name;
    partial void OnNameChanged(string value)
    {
        ValidateData();
    }
    [ObservableProperty, NotifyPropertyChangedFor(nameof(CompleteName)), NotifyPropertyChangedFor(nameof(IsValid)), 
     NotifyCanExecuteChangedFor(nameof(AddClientCommand)), NotifyCanExecuteChangedFor(nameof(ConfirmClientCommand))]
    private string surname;
    partial void OnSurnameChanged(string value)
    {
        ValidateData();
    }
    [ObservableProperty, NotifyPropertyChangedFor(nameof(IsValid)), 
     NotifyCanExecuteChangedFor(nameof(AddClientCommand)), NotifyCanExecuteChangedFor(nameof(ConfirmClientCommand))]
    private string paycheck;
    partial void OnPaycheckChanged(string value)
    {
        ValidateData();
    }
    [ObservableProperty, 
     NotifyCanExecuteChangedFor(nameof(EditClientCommand)), NotifyCanExecuteChangedFor(nameof(DeleteClientCommand)), NotifyCanExecuteChangedFor(nameof(CreateClientsCommand))]
    private int position = -1;
    [ObservableProperty, NotifyCanExecuteChangedFor(nameof(AddClientCommand)), NotifyCanExecuteChangedFor(nameof(ConfirmClientCommand))]
    private bool isValid;
    [ObservableProperty, 
     NotifyCanExecuteChangedFor(nameof(AddClientCommand)), NotifyCanExecuteChangedFor(nameof(ConfirmClientCommand))]
    [NotifyCanExecuteChangedFor(nameof(DismissClientCommand)), NotifyCanExecuteChangedFor(nameof(EditClientCommand))]
    private bool editing;
    [ObservableProperty]
    private ObservableCollection<Client> clients;

    readonly IRepositoriDeClients clientsRepository = Repositori.ObreBDClients();
    #endregion
    
    public string CompleteName => Name + " " + Surname;

    public ClientViewModelCommunityToolkit()
    {
        clients = clientsRepository.Obten();
        Position = -1;
    }
    
    #region Voids

    void ValidateData()
    {
        IsValid = !string.IsNullOrEmpty(Name)
                  && !string.IsNullOrEmpty(Surname)
                  && decimal.TryParse(Paycheck, out _);
    }
    [RelayCommand (CanExecute = nameof(CanCreateClients))]
    private void CreateClients(string nClients)
    {
        if (int.TryParse(nClients, out var quantity))
        {
            clientsRepository.CreaClients(quantity);
            Clients = clientsRepository.Obten();
        }
    }
    
    [RelayCommand (CanExecute = nameof(CannAddClient))]
    private void AddClient()
    {
        string id = Guid.NewGuid().ToString();
        Client newClient = new()
        {
            Nom = Name,
            Cognom = Surname,
            Saldo = decimal.Parse(Paycheck),
            Foto = $"https://loremflickr.com/320/240/portrait,{Name}?lock={id}",
            Id = id
        };
        clientsRepository.Afegeix(newClient);
        Clients = clientsRepository.Obten();
        Name = "";
        Surname = "";
        Paycheck = "0";
    }

    
    [RelayCommand (CanExecute = nameof(CanActivateEditing))]
    private void EditClient()
    {
        Name = Clients[Position].Nom;
        Surname = Clients[Position].Cognom;
        Paycheck = Clients[Position].Saldo.ToString();
        Editing = true;
    }

    
    [RelayCommand (CanExecute = nameof(CanConfirmEditingClient))]
    private void ConfirmClient()
    {
        Client newClient = new()
        {
            Id = Clients[Position].Id,
            Nom = Name,
            Cognom = Surname,
            Saldo = decimal.Parse(Paycheck),
            Foto = Clients[Position].Foto
        };
        clientsRepository.Modifica(newClient);
        Clients = clientsRepository.Obten();
        Editing = false;
        Name = "";
        Surname = "";
        Paycheck = "0";
    }

    
    [RelayCommand (CanExecute = nameof(CanDissmissEditinClient))]
    private void DismissClient()
    {
        Editing = false;
        Name = "";
        Surname = "";
        Paycheck = "0";
    }

    [RelayCommand (CanExecute = nameof(CanDeleteClient))]
    private void DeleteClient()
    {
        clientsRepository.Esborra(Clients[Position].Id);
        Clients = clientsRepository.Obten();
    }

    private bool CanCreateClients() => Clients.Count == 0;
    private bool CannAddClient() => IsValid && !Editing;
    bool CanActivateEditing() => Position != -1 && !Editing;
    private bool CanConfirmEditingClient() => Editing && IsValid;
    private bool CanDissmissEditinClient() => Editing;
    bool CanDeleteClient() => Position != -1;

    #endregion
}