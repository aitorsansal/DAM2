using System;
using System.Collections.ObjectModel;
using System.Windows.Input;
using ClientsMVVM.Model;
using ClientsMVVM.Repositiori;

namespace ClientsMVVM.ViewModel;

public class ClientViewModel : ObservableBase
{
    #region Fields

    private string name;
    private string surname;
    private string photo;
    private int position = -1;
    private string paycheck;
    private bool isValid;
    private bool editing;
    private ObservableCollection<Client> clients = new();
    IRepositoriDeClients clientsRepository = Repositori.ObreBDClients();
    #endregion
    
    #region Properties
    public string Name
    {
        get => name;
        set
        {
            SetProperty(ref name, value);
            NotifyPropertyChanged(nameof(CompleteName));
            ValidateData();
        }
    }

    public string Surname
    {
        get => surname;
        set
        {
            SetProperty(ref surname, value);
            NotifyPropertyChanged(nameof(CompleteName));
            ValidateData();
        }
    }

    public bool IsValid
    {
        get => isValid;
        set => SetProperty(ref isValid, value);
    }

    public bool Editing
    {
        get => editing;
        set => SetProperty(ref editing, value);
    }

    public ObservableCollection<Client> Clients
    {
        get => clients;
        set => SetProperty(ref clients, value);
    }

    public string Paycheck
    {
        get => paycheck;
        set
        {
            SetProperty(ref paycheck, value);
            ValidateData();
        }
    }

    public int Position
    {
        get => position;
        set => SetProperty(ref position, value);
    }
    public string CompleteName => Name + " " + Surname;

    #endregion

    #region Command Properties

    public ICommand CreateClientsCommand { get; set; }
    public ICommand AddClientCommand { get; set; }
    public ICommand EditClientCommand { get; set; }
    public ICommand ConfirmClientCommand { get; set; }
    public ICommand DismissClientCommand { get; set; }
    public ICommand DeleteClientCommand { get; set; }
    

    #endregion

    void ValidateData()
    {
        IsValid = !string.IsNullOrEmpty(Name)
                  && !string.IsNullOrEmpty(Surname)
                  && decimal.TryParse(Paycheck, out _);
    }


    public ClientViewModel()
    {
        clients = clientsRepository.Obten();
        Position = -1;

        #region Commands

        CreateClientsCommand = new RelayCommand<string>(CreateClients, _ => Clients.Count == 0);
        AddClientCommand = new RelayCommand(AddClient, _ => IsValid && !Editing);
        EditClientCommand = new RelayCommand(ActivateEditing, _ => !Editing && Position != -1);
        ConfirmClientCommand = new RelayCommand(ConfirmEditingClient, _ => Editing && IsValid);
        DismissClientCommand = new RelayCommand(DismissEditingClient, _ => Editing);
        DeleteClientCommand = new RelayCommand(DeleteClient, _ => Position != -1);

        #endregion
    }

    private void CreateClients(string nClients)
    {
        if (int.TryParse(nClients, out var clients))
        {
            clientsRepository.CreaClients(clients);
            Clients = clientsRepository.Obten();
        }
    }
    private void AddClient(object obj)
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
    private void ActivateEditing(object obj)
    {
        Name = Clients[Position].Nom;
        Surname = Clients[Position].Cognom;
        Paycheck = Clients[Position].Saldo.ToString();
        Editing = true;
    }
    private void ConfirmEditingClient(object obj)
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
    private void DismissEditingClient(object obj)
    {
        Editing = false;
        Name = "";
        Surname = "";
        Paycheck = "0";
    }
    private void DeleteClient(object obj)
    {
        clientsRepository.Esborra(Clients[Position].Id);
        Clients = clientsRepository.Obten();
    }
}