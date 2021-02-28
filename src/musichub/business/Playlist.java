package musichub.business;
import java.util.Collection;

/**
 * classe publique Playlist qui implémente Comparable qui permet de trier des livres audio.
 * @param id identifiant unique de la playlist
 * @param nom privé, nom de la playlist
 * @param liste privé, liste d'éléments jouables (chansons et livres audio)
 *
 * les premières methodes sont publiques et sont des getteurs et setteurs basiques pour chacun des attributs.
 * les méthodes toString, compareTo et addElement sont précisées plus tard.
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class Playlist implements Comparable<Playlist>  {

    private int id;

    private String nom;

    private Collection<IJouable> liste;


public Playlist(int id, String nom, Collection<IJouable> liste) {
    this.id = id;
    this.nom = nom;
    this.liste = liste;
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}

public Collection<IJouable> getListe() {
    return this.liste;
}

public void setListe(Collection<IJouable> liste) {
    this.liste = liste;
}

public void add(IJouable element) {
    this.liste.add(element);
}

/**
 *  méthode toString qui transforme une chanson en chaîne de caractère.
 */
@Override //on surcharge cette méthode qui existe déjà
public String toString() {
    return "---------\n"
    + "Playlist :\n"
    + "id = " + this.getId() + "\n"
    + "nom = " + this.getNom() + "\n"
    + "liste = \n" + this.getListe() + "\n\n";
    }

/**
 *  méthode compareTo qui trie deux playlists entre elles selon leur nom et retourne une position dans la liste.
 */
@Override //on surcharge cette méthode qui existe déjà
public int compareTo(Playlist playlist) {
    return this.getNom().compareTo(playlist.getNom()); //comparer String = ordre alphabétique
}

/**
 *  méthode addElement qui ajoute un élément de type IJouable -donc soit une chanson soit un livre audio- dans la liste.
 */
public void addElement(IJouable element) {
    this.liste.add(element);
}

}
