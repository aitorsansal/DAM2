using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace RkPpSsLzSk.Model
{

    public class Player : IComparable<Player>
    {
        public string Name { get; set; }
        public int MaxInSingleTournament { get; set; }
        public int TotalPoints { get; set; }
        public int WonGames { get; set; }
        public int WonRounds { get; set; }
        public int LostRounds { get; set; }
        
        public SerializableKeyValue[] Selections { get; set; }


        public Player()
        {
            Selections =
            [
                new SerializableKeyValue() {Key = SelectionValues.Rock, Value = 0},
                new SerializableKeyValue() {Key = SelectionValues.Paper, Value = 0},
                new SerializableKeyValue() {Key = SelectionValues.Scissors, Value = 0},
                new SerializableKeyValue() {Key = SelectionValues.Lizard, Value = 0},
                new SerializableKeyValue() {Key = SelectionValues.Spock, Value = 0}
            ];
        }

        public int CompareTo(Player other)
        {
            if (other == null) return 1;

            int result = other.MaxInSingleTournament.CompareTo(MaxInSingleTournament);
            if (result != 0) return result;

            result = other.TotalPoints.CompareTo(TotalPoints);
            if (result != 0) return result;

            result = other.WonGames.CompareTo(WonGames);
            if (result != 0) return result;

            result = other.WonRounds.CompareTo(WonRounds);
            if (result != 0) return result;

            result = LostRounds.CompareTo(other.LostRounds);
            if (result != 0) return result;

            return string.Compare(Name, other.Name, StringComparison.Ordinal);
        }
    }
    

    public class SerializableKeyValue : IComparable<SerializableAttribute>
    {
        public SelectionValues Key { get; set; }
        public int Value { get; set; }

        public int CompareTo(SerializableAttribute? other)
        {
            return Value.CompareTo(Value);
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(Key);
        }
        
    }
}
