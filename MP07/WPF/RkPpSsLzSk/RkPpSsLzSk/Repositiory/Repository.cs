using RkPpSsLzSk.Dades;

namespace RkPpSsLzSk.Repositiori
{
    public static class Repository
    {
        private static IPlayersRepository? playersRepository = null;
        public static IPlayersRepository OpenBDPlayers()
        {
            if (playersRepository == null)
            {
                playersRepository = new PlayersXML();
            }
            return playersRepository;
        }
    }
}
