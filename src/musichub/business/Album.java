package musichub.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * classe publique Album qui hérite de la classe abstraite AbstractOeuvre et implémente l'interface Comparable qui permet de trier des albums.
 * @param id privé, identifiant unique de l'oeuvre (hérité de la classe abstraite)
 * @param titre privé, titre de l'oeuvre (hérité de la classe abstraite)
 * @param duree privé, durée en seconde de l'oeuvre (hérité de la classe abstraite)
 * @param chanson privé, collection/liste de chanson
 * @param artiste privé, nom de l'artiste
 * @param date privé, date de sortie de l'album
 *
 * les premières methodes sont publiques et sont des getteurs et setteurs basiques pour chacun des attributs.
 * les méthodes toString, compareTo et addChanson sont précisées plus tard.
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class Album extends AbstractOeuvre implements Comparable<Album> {

    private Collection<Chanson> chanson;
    private String artiste;
    private String date;

    public Album(int id, String titre, int duree, Collection<Chanson> chanson, String artiste, String date) {
        super(id, titre, duree);
        this.chanson = chanson;
        this.artiste = artiste;
        this.date = date;
    }

    public Collection<Chanson> getChanson() {
        return chanson;
    }

    public void setChanson(Collection<Chanson> chanson) {
        this.chanson = chanson;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
/**
 *  méthode toString qui transforme un album en chaîne de caractère.
 */
@Override //on surcharge cette méthode qui existe déjà
    public String toString() {
        return "Album :\n" + "id = " + this.getId() + "\n" + "titre = " + this.getTitre() + "\n" + "duree = "
                + this.getDuree() + "\n" + "chanson = " + this.getChanson() + "\n" + "artiste = " + this.getArtiste()
                + "\n" + "date = " + this.getDate() + "\n\n";
    }

/**
 *  méthode compareTo qui trie deux albums entre eux selon leur date de sortie et retourne une position dans la liste.
 */
@Override //on surcharge cette méthode qui existe déjà
    public int compareTo(Album album) {
        int diff = 0;
        Date date1 = null;
        Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date1 = sdf.parse(this.getDate());
        } catch (ParseException e) {
            return 1;
        }

        try {
            date2 = sdf.parse(album.getDate());
        } catch (ParseException e) {
            return -1; //on met le mauvais en bas de la liste 
        }

        diff = date2.compareTo(date1);

        return diff;
    }

/**
 * méthode addChanson qui ajoute une chanson à notre collection de chansons.
 * elle prend en paramètre la chanson que l'on souhaite ajouter à notre liste.
 */
    public void addChanson (Chanson chanson) {
        this.chanson.add(chanson);
    }
}
