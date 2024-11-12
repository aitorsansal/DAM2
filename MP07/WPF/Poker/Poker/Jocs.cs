using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
 
namespace Poker;

public enum Pal
{
    Trèbols, Diamants, Cors, Piques
}
public enum Valor
{
    Dos, Tres, Quatre, Cinc, Sis, Set, Vuit,
    Nou, Deu, Jota, Reina, Rei, As, Joker
}
public class Jocs : IComparable<Jocs>
{
    private Pal pal;
    private Valor valor;
    private bool bocaAvall;
    private String dors;  
 
 
    public Jocs() : this(Pal.Piques, Valor.As)
    {
    }
    public Jocs(Pal nouPal, Valor nouValor) : this(nouPal, nouValor, "Dors01")
    {
    }
    public Jocs(Pal nouPal, Valor nouValor, String clauDors)
    {
        pal = nouPal;
        valor = nouValor;
        dors = clauDors;
    }
    public Valor Valor
    {
        get { return valor; }
        set { valor = value; }
    }
    public Pal Pal
    {
        get { return pal; }
        set { pal = value; }
    }
    public bool BocaAvall
    {
        get { return bocaAvall; }
        set { bocaAvall = value; }
    }
 
    public int CompareTo(Jocs other)
    {
        if (this.Valor == other.Valor)
            return this.Pal - other.Pal;
        else
            return this.Valor - other.Valor;
    }
 
    /// <summary>
    /// Retorna la clau per obtenir el recurs d'imatge de la carta definit al diccionari de recursos
    /// Si la carta està 
    /// </summary>
    public String ClauImatge
    {
        get
        {
            String resultat = "";
            switch (Valor)
            {
                case Valor.As:
                    resultat = "A";
                    break;
                case Valor.Rei:
                    resultat = "K";
                    break;
                case Valor.Reina:
                    resultat = "Q";
                    break;
                case Valor.Jota:
                    resultat = "J";
                    break;
                default:
                    resultat = (((int)Valor + 2).ToString());
                    break;
            }
 
            switch (Pal)
            {
                case Pal.Piques:
                    resultat += "P";
                    break;
                case Pal.Cors:
                    resultat += "C";
                    break;
                case Pal.Diamants:
                    resultat += "D";
                    break;
                case Pal.Trèbols:
                    resultat += "T";
                    break;
                default:
                    resultat = (((int)Valor).ToString());
                    break;
            }
 
            if (Valor == Valor.Joker)
            {
                resultat = (Pal == Pal.Piques) || (Pal == Pal.Trèbols) ? "Joker1" : "Joker2";
            }
            return bocaAvall?dors:resultat;
        }
    }
}
 
public class Baralla : IEnumerable<Jocs>
{
 
    private Pal[] pals;
    private Valor[] valors;
    List<Jocs> cartes;
 
    public Pal[] Pals
    {
        get { return pals; }
        set
        {
            pals = value;
            CreaBaralla();
        }
    }
 
    public Valor[] Valors
    {
        get { return valors; }
        set
        {
            valors = value;
            CreaBaralla();
        }
    }
 
    /// <summary>
    /// Crea una joc de cartes complet, amb tots els pals i tots els valors, però sense comodins.
    /// </summary>
    public Baralla()
    {
        pals = new Pal[] { Pal.Piques, Pal.Cors, Pal.Diamants, Pal.Trèbols };
        valors = new Valor[] {Valor.As, Valor.Dos, Valor.Tres, Valor.Quatre, Valor.Cinc, Valor.Sis,
            Valor.Set, Valor.Vuit, Valor.Nou, Valor.Deu, Valor.Jota,
            Valor.Reina, Valor.Rei};
 
        CreaBaralla();
        Remena();
    }
 
 
    /// <summary>
    /// Mètode privat que afegeix a una baralla les cartes resultants de combinar els
    /// pals i valors que contenen els atributs "pals" i "valors".
    /// </summary>
    private void CreaBaralla()
    {
        cartes = new List<Jocs>();
        //Allibera les cartes utilitzades fins ara
        cartes.Clear();
 
        //Afegir les noves cartes
        for (int unPal = 0; unPal < pals.Length; unPal++)
        for (int unValor = 0; unValor < valors.Length; unValor++)
            cartes.Add(new Jocs(pals[unPal], valors[unValor]));
    }
 
    /// <summary>
    /// Remena les cartes contingudes a la baralla
    /// </summary>
    public void Remena()
    {
        Random rGen = new Random();
        List<Jocs> novaBaralla = new List<Jocs>();
 
        while (cartes.Count > 0)
        {
            //Tria una carta per eliminar-la.
            int aEliminar = rGen.Next(0, cartes.Count - 1);
            Jocs cartaEliminada = (Jocs)cartes[aEliminar];
            cartes.Remove(cartaEliminada);
            //Afegeix la carta eliminada a la nova baralla
            novaBaralla.Add(cartaEliminada);
        }
        //Remplaça la baralla vella per la nova amb les cartes barrejades
        cartes = novaBaralla;
    }
    /// <summary>
    /// Propietat que retorna el número de cartes que té la baralla.
    /// </summary>
    public int Count
    {
        get { return cartes.Count; }
    }
 
    /// <summary>
    /// Indexador que retorna la carta de la baralla que correspon a l'index rebut.
    /// </summary>
    public Jocs this[int index]
    {
        get
        {
            if ((index >= 0) && (index < cartes.Count))
            { return ((Jocs)cartes[index]); }
            else
            { throw new ArgumentOutOfRangeException("Índex fora de rang"); }
        }
    }
 
    /// <summary>
    /// Extreu la primera carta de la baralla i la retorna.
    /// </summary>
    /// <returns></returns>
    public Jocs Roba()
    {
        if (Count == 0)
            throw new IndexOutOfRangeException("No es pot robar d'una baralla buida");
        Jocs carta = cartes[0];
        cartes.RemoveAt(0);
        return carta;
    }
 
    /// <summary>
    /// Insereix una carta en una posició determinada de la baralla
    /// </summary>
    /// <param name="carta"></param>
    /// <param name="posicio"></param>
    public void Afegeix(Jocs carta, int posicio)
    {
        if (Count > posicio)
            throw new IndexOutOfRangeException("La posició màxima a on es pot inserir és: " + Count);
 
        cartes.Insert(posicio, carta);
    }
 
    public IEnumerator<Jocs> GetEnumerator()
    {
        return ((IEnumerable<Jocs>)cartes).GetEnumerator();
    }
 
    IEnumerator IEnumerable.GetEnumerator()
    {
        return ((IEnumerable<Jocs>)cartes).GetEnumerator();
    }
}
public class Ma : IEnumerable<Jocs>
{
 
    List<Jocs> cartes;
    List<Jocs> comodins;
 
 
    public Ma()
    {
        comodins = new List<Jocs>();
        cartes = new List<Jocs>();
    }
 
    public Ma(Jocs[] cartes)
    {
        this.cartes = new List<Jocs>();
        comodins = new List<Jocs>();
        this.cartes.AddRange(cartes);
    }
 
 
    /// <summary>
    /// Afegeix una carta la mà
    /// </summary>
    /// <param name="carta"></param>
    public void Afegeix(Jocs carta)
    {
        cartes.Add(carta);
    }
 
    /// <summary>
    /// Afegeix un rang de cartes a la mà
    /// </summary>
    /// <param name="cartes"></param>
    public void AfegeixRang(Jocs[] cartes)
    {
        this.cartes.AddRange(cartes);
    }
 
 
    public void Elimina(int posicio)
    {
        if (posicio >= Count)
        {
            throw new IndexOutOfRangeException("No es pot eliminar cap carta a la posició: " + posicio);
        }
 
        cartes.RemoveAt(posicio);
    }
 
 
 
    /// <summary>
    /// Ordena les cartes contingudes a la mà
    /// </summary>
    public void Ordena()
    {
        cartes.Sort();
    }
 
    /// <summary>
    /// Propietat que retorna el número de cartes que té la mà.
    /// </summary>
    public int Count
    {
        get { return cartes.Count; }
    }
 
    /// <summary>
    /// Indexador que retorna la carta de la baralla que correspon a l'index rebut.
    /// </summary>
    public Jocs this[int index]
    {
        get
        {
            if ((index >= 0) && (index < cartes.Count))
            { return ((Jocs)cartes[index]); }
            else
            { throw new ArgumentOutOfRangeException("Índex fora de rang"); }
        }
 
        set
        {
            if ((index >= 0) && (index < cartes.Count))
            { cartes[index] = value; }
            else
            { throw new ArgumentOutOfRangeException("Índex fora de rang"); }
        }
    }
 
 
 
    /// <summary>
    /// Obté o assigna quines cartes fan de comodí
    /// </summary>
    public Jocs[] Comodins
    {
        get { return comodins.ToArray<Jocs>(); }
        set { comodins.Clear(); comodins.AddRange(value); }
    }
 
    /// <summary>
    /// Descomposa les cartes de la ma en valors
    /// </summary>
    /// <param name="ma"></param>
    /// <returns></returns>
    private Dictionary<Valor, List<Jocs>> DescomposaValors()
    {
        Dictionary<Valor, List<Jocs>> resultat = new Dictionary<Valor, List<Jocs>>();
        foreach (Valor v in Enum.GetValues(typeof(Valor)))
        {
            resultat.Add(v, new List<Jocs>());
        }
        foreach (Jocs carta in cartes)
        {
            if (Conte(comodins, carta))
                resultat[Valor.Joker].Add(carta);
            else
                resultat[carta.Valor].Add(carta);
        }
        return resultat;
    }
 
 
    /// <summary>
    /// Descomposa les cartes de la ma en pals (no considera els comodins)
    /// </summary>
    /// <param name="ma"></param>
    /// <returns></returns>
    private Dictionary<Pal, List<Jocs>> DescomposaPals()
    {
        Dictionary<Pal, List<Jocs>> resultat = new Dictionary<Pal, List<Jocs>>();
        foreach (Pal v in Enum.GetValues(typeof(Pal)))
        {
            resultat.Add(v, new List<Jocs>());
        }
        foreach (Jocs carta in cartes)
        {
            if (!Conte(comodins, carta))
                resultat[carta.Pal].Add(carta);
        }
        return resultat;
    }
 
    #region comprovació de jugades
    public bool HiHaParella
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaParella = dic[Valor.Joker].Count > 0;
            int index = 0;
            while (!hiHaParella && index < dic.Keys.Count)
            {
                hiHaParella = dic[dic.Keys.ElementAt(index)].Count > 1;
                index++;
            }
            return hiHaParella;
        }
    }
    public bool HiHaParellaMinima(Valor v)
    {
        Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
        bool hiHaParella = dic[Valor.Joker].Count > 0;
        int index = 0;
        while (!hiHaParella && index < dic.Keys.Count)
        {
            hiHaParella = (dic.Keys.ElementAt(index) >= v) && (dic[dic.Keys.ElementAt(index)].Count > 1);
            index++;
        }
        return hiHaParella;
    }
 
    public bool HiHaDobleParella
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaDobleParella = dic[Valor.Joker].Count > 1;
            int index = 0;
            int comptaParelles = 0;
            while (!hiHaDobleParella && index < dic.Keys.Count)
            {
                if (dic[dic.Keys.ElementAt(index)].Count > 1)
                    comptaParelles++;
                hiHaDobleParella = (comptaParelles + dic[Valor.Joker].Count) > 1;
                index++;
            }
            return hiHaDobleParella;
        }
    }
 
    public bool HiHaTrio
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaTrio = dic[Valor.Joker].Count > 1;
            int index = 0;
            while (!hiHaTrio && index < dic.Keys.Count)
            {
                hiHaTrio = (dic.Keys.ElementAt(index) != Valor.Joker) && (dic[dic.Keys.ElementAt(index)].Count + dic[Valor.Joker].Count) > 2;
                index++;
            }
            return hiHaTrio;
        }
    }
 
    public bool HiHaPoker
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaPoker = dic[Valor.Joker].Count > 2;
            int index = 0;
            while (!hiHaPoker && index < dic.Keys.Count)
            {
                hiHaPoker = (dic.Keys.ElementAt(index) != Valor.Joker) && (dic[dic.Keys.ElementAt(index)].Count + dic[Valor.Joker].Count) > 3;
                index++;
            }
            return hiHaPoker;
        }
    }
 
    public bool HiHaQuatreDosos
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            return dic[Valor.Dos].Count >= 4;
        }
    }
    public bool HiHaRepoker
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaRepoker = dic[Valor.Joker].Count > 3;
            int index = 0;
            while (!hiHaRepoker && index < dic.Keys.Count)
            {
                hiHaRepoker = (dic.Keys.ElementAt(index) != Valor.Joker) && (dic[dic.Keys.ElementAt(index)].Count + dic[Valor.Joker].Count) > 4;
                index++;
            }
            return hiHaRepoker;
        }
    }
    public bool HiHaFull
    {
        get
        {
            Dictionary<Valor, List<Jocs>> dic = DescomposaValors();
            bool hiHaFull = false;
            int max1 = 0;
            int max2 = 0;
            int nComodins = dic[Valor.Joker].Count;
            foreach (Valor v in dic.Keys)
            {
                if (v != Valor.Joker)
                {
                    if (dic[v].Count > max1)
                    {
                        max2 = max1;
                        max1 = dic[v].Count;
                    }
                    else if (dic[v].Count > max2)
                    {
                        max2 = dic[v].Count;
                    }
                }
            }
            while (max2 < 2 && nComodins > 0)
            {
                max2++;
                nComodins--;
            }
            while (max1 < 3 && nComodins > 0)
            {
                max1++;
                nComodins--;
            }
            hiHaFull = (max2 >= 2) && (max1 >= 3);
            return hiHaFull;
        }
    }
 
    public bool HiHaColor
    {
        get
        {
            int nComodins = DescomposaValors()[Valor.Joker].Count;
            Dictionary<Pal, List<Jocs>> dic = DescomposaPals();
            bool hihaColor = false;
            foreach (Pal p in dic.Keys)
            {
                if (dic[p].Count + nComodins >= 5)
                    hihaColor = true;
            }
            return hihaColor;
        }
    }
 
    public bool HiHaEscala
    {
        get
        {
 
            List<Jocs> llistaCartes = new List<Jocs>();
            int nComodins = DescomposaValors()[Valor.Joker].Count;
            Dictionary<Pal, List<Jocs>> dic = DescomposaPals();
 
 
            foreach (Pal p in dic.Keys)
            {
                foreach (Jocs c in dic[p])
                    llistaCartes.Add(c);
            }
 
            llistaCartes.Sort();
            int index = 0;
            bool hiHaEscala = true;
            if (llistaCartes.Count == 0)
                return true;
            Valor v = llistaCartes[0].Valor;
            while (hiHaEscala && index < llistaCartes.Count)
            {
                while (v < llistaCartes[index].Valor && nComodins > 0)
                {
                    v++;
                    nComodins--;
                }
 
                hiHaEscala = v == llistaCartes[index].Valor;
                index++;
                v++;
            }
 
            return hiHaEscala;
        }
    }
 
    public bool HiHaEscalaDeColor
    {
        get
        {
            return HiHaEscala && HiHaColor;
        }
    }
 
    public bool HiHaEscalaReialDeColor
    {
        get
        {
            List<Jocs> llistaCartes = new List<Jocs>();
            int nComodins = DescomposaValors()[Valor.Joker].Count;
            Dictionary<Pal, List<Jocs>> dic = DescomposaPals();
 
 
            foreach (Pal p in dic.Keys)
            {
                foreach (Jocs c in dic[p])
                    llistaCartes.Add(c);
            }
 
            llistaCartes.Sort();
            int index = 0;
            bool hiHaEscala = true;
            if (llistaCartes.Count == 0)
                return true;
            Valor v = llistaCartes[0].Valor;
            while (hiHaEscala && index < llistaCartes.Count)
            {
                while (v < llistaCartes[index].Valor && nComodins > 0)
                {
                    v++;
                    nComodins--;
                }
 
                hiHaEscala = v == llistaCartes[index].Valor;
                index++;
                v++;
            }
 
            return hiHaEscala && HiHaColor && llistaCartes[0].Valor == Valor.Deu;
 
        }
    }
 
    public bool HiHaEscalaReialDeColorSenseComodins
    {
        get
        {
            List<Jocs> llistaCartes = new List<Jocs>();
            int nComodins = DescomposaValors()[Valor.Joker].Count;
            Dictionary<Pal, List<Jocs>> dic = DescomposaPals();
 
 
            foreach (Pal p in dic.Keys)
            {
                foreach (Jocs c in dic[p])
                    llistaCartes.Add(c);
            }
 
            llistaCartes.Sort();
            int index = 0;
            bool hiHaEscala = true;
            if (llistaCartes.Count == 0)
                return true;
            Valor v = llistaCartes[0].Valor;
            while (hiHaEscala && index < llistaCartes.Count)
            {
                while (v < llistaCartes[index].Valor && nComodins > 0)
                {
                    v++;
                    nComodins--;
                }
 
                hiHaEscala = v == llistaCartes[index].Valor;
                index++;
                v++;
            }
 
            return hiHaEscala && HiHaColor && llistaCartes[0].Valor == Valor.Deu && DescomposaValors()[Valor.Joker].Count == 0;
 
        }
    }
    #endregion
 
    /// <summary>
    /// Mira si una carta determinada està inclosa com a comodí
    /// </summary>
    /// <param name="comodins"></param>
    /// <param name="carta"></param>
    /// <returns></returns>
    private static bool Conte(List<Jocs> comodins, Jocs carta)
    {
        int index = 0;
        while (index < comodins.Count && carta.CompareTo(comodins[index]) != 0)
            index++;
 
        return index < comodins.Count;
    }
 
    public IEnumerator<Jocs> GetEnumerator()
    {
        return ((IEnumerable<Jocs>)cartes).GetEnumerator();
    }
 
    IEnumerator IEnumerable.GetEnumerator()
    {
        return ((IEnumerable<Jocs>)cartes).GetEnumerator();
    }
}