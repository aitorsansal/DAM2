using RkPpSsLzSk.Model;
using System.Collections.ObjectModel;

namespace RkPpSsLzSk.Repositiori
{
    public interface IPlayersRepository
    {
        bool Add(Player player);
        void Save(ObservableCollection<Player> players);
        bool Modify(Player player, ObservableCollection<Player> players);
        ObservableCollection<Player> Obtain();
        void ModifySelection(SelectionValues value, string playerName);
    }
}