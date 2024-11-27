using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RkPpSsLzSk.Model
{
    public class Player
    {
        public string Name { get; set; }
        public int MaxInSingleTournament { get; set; }
        public int TotalPoints { get; set; }
        public int WonGames { get; set; }
        public int WonRounds { get; set; }
        public int LostRounds { get; set; }
    }
}
