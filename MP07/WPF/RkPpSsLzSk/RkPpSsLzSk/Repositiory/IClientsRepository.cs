using RkPpSsLzSk.Model;
using System.Collections.ObjectModel;

namespace RkPpSsLzSk.Repositiori
{
    public interface IPlayersRepository
    {
        bool Add(Player player);
        void Save(ObservableCollection<Player> clients);
        bool Modify(Player player);
        ObservableCollection<Player> Obtain();
    }
}