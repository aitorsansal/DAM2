using System.Collections.ObjectModel;
using System.IO;
using System.Reflection;
using System.Xml.Serialization;
using RkPpSsLzSk.Model;
using RkPpSsLzSk.Repositiori;

namespace RkPpSsLzSk.Dades
{
    public class PlayersXML : IPlayersRepository
    {
        const string NOM_FITXER_XML = "Players.xml";
        readonly string rutaFitxerXml = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), NOM_FITXER_XML);

        /// <summary>
        /// Desa els clients en un fitxer XML
        /// </summary>
        /// <param name="players">Dades dels clients que ha de desar</param>
        public void Save(ObservableCollection<Player> players)
        {
            using (TextWriter fitxer = new StreamWriter(rutaFitxerXml))
            {
                XmlSerializer serialitzador = new XmlSerializer(typeof(ObservableCollection<Player>));
                serialitzador.Serialize(fitxer, players);
            }
        }

        /// <summary>
        /// Obté els clients desats en un fitxer XML
        /// </summary>
        /// <returns>Les dades dels clients en format de llista observable</returns>
        public ObservableCollection<Player> Obtain()
        {
            ObservableCollection<Player> players;

            using (TextReader fitxer = new StreamReader(rutaFitxerXml, new FileStreamOptions{Mode = FileMode.OpenOrCreate} ))
            {
                if (fitxer.Peek() != -1)
                {
                    XmlSerializer serialitzador = new XmlSerializer(typeof(ObservableCollection<Player>));
                    players = (ObservableCollection<Player>)serialitzador.Deserialize(fitxer);
                }
                else
                {
                    players = new ObservableCollection<Player>();
                }
            }
            return players;
        }

        public void ModifySelection(SelectionValues value, string playerName)
        {
            // Get the list of players
            var players = Obtain();

            // Find the player by name
            Player modifPlayer = players.FirstOrDefault(p => p.Name == playerName);

            if (modifPlayer != null)
            {
                modifPlayer.Selections.First(x => x.Key == value).Value++;

                // Save the updated list back to the XML file
                Save(players);
            }
            else
            {
                // Handle case where player is not found (optional)
                Console.WriteLine("Player not found!");
            }
        }

        /// <summary>
        /// Modifica un client
        /// </summary>
        /// <param name="player">Noves dades que ha de tenir el client</param>
        /// <param name="players"></param>
        /// <returns></returns>
        public bool Modify(Player player, ObservableCollection<Player> players)
        {
            bool modified = false;
            Player modificablePlayer = players.FirstOrDefault(p => p.Name == player.Name);
            if (modificablePlayer != null)
            {
                modificablePlayer.Name = player.Name;
                modificablePlayer.WonGames = player.WonGames;
                modificablePlayer.WonRounds = player.WonRounds;
                modificablePlayer.MaxInSingleTournament = player.MaxInSingleTournament;
                modificablePlayer.LostRounds = player.LostRounds;
                modificablePlayer.TotalPoints = player.TotalPoints;
                modified = true;
            }
            Save(players);
            return modified;
        }

        /// <summary>
        /// Afegeix un client
        /// </summary>
        /// <param name="player">Dades del client a afegir</param>
        /// <returns></returns>
        public bool Add(Player player)
        {
            bool added = false;
            ObservableCollection<Player> clients = Obtain();
            Player playerModificable = clients.FirstOrDefault(clientActual => clientActual.Name == player.Name);
            if (playerModificable == null)
            {
                clients.Add(player);
                added = true;
            }
            Save(clients);
            return added;
        }
    }
    
    
    
}
