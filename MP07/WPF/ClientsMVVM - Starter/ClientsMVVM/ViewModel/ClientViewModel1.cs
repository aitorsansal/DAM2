using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using ClientsMVVM.Model;
using ClientsMVVM.Repositiori;

namespace ClientsMVVM.ViewModel;

public class ClientViewModel1 : INotifyPropertyChanged
{
    public int Posicio
    {
        get => posicio;
        set => posicio = value;
    }

    public bool EsValid
    {
        get => esValid;
        set => esValid = value;
    }

    public string Id
    {
        get => id;
        set => id = value;
    }

    public string Nom
    {
        get => nom;
        set => nom = value ;
    }

    public string Cognom
    {
        get => cognom;
        set => cognom = value;
    }

    public decimal Saldo
    {
        get => saldo;
        set => saldo = value;
    }

    private string id;
    private string nom;
    private string cognom;
    private decimal saldo;
    private int posicio = -1;
    private bool esValid;
    private string foto;

    public string Foto
    {
        get => foto;
        set => foto = value ?? throw new ArgumentNullException(nameof(value));
    }

    private ObservableCollection<Client> clients = new();

    public ObservableCollection<Client> Clients
    {
        get => clients;
        set => clients = value ?? throw new ArgumentNullException(nameof(value));
    }

    IRepositoriDeClients repositoriDeClients = Repositori.ObreBDClients();
    
    public event PropertyChangedEventHandler? PropertyChanged;

    protected virtual void OnPropertyChanged([CallerMemberName] string? propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    protected bool SetField<T>(ref T field, T value, [CallerMemberName] string? propertyName = null)
    {
        if (EqualityComparer<T>.Default.Equals(field, value)) return false;
        field = value;
        OnPropertyChanged(propertyName);
        return true;
    }

    public ClientViewModel1()
    {
        clients.Clear();
        repositoriDeClients.CreaClients(10);
        clients = repositoriDeClients.Obten();
    }
}