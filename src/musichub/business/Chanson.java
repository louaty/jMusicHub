package musichub.business;

/**
 * classe publique Chanson qui hérite de la classe abstraite AbstractOeuvre et implémente l'interface IJouable et Comparable qui permet de trier des albums.
 * @param id privé, identifiant unique de l'oeuvre (hérité de la classe abstraite)
 * @param titre privé, titre de l'oeuvre (hérité de la classe abstraite)
 * @param duree privé, durée en seconde de l'oeuvre (hérité de la classe abstraite)
 * @param artiste privé, nom de l'artiste
 * @param contenu privé, contenu de la chanson
 * @param genre privé, genre de la chanson 
 * @see Genre
 * @param date privé, date de sortie de l'album
 *
 * les premières methodes sont publiques et sont des getteurs et setteurs basiques pour chacun des attributs.
 * les méthodes toString et compareTo sont précisées plus tard.
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class Chanson extends AbstractOeuvre implements IJouable, Comparable<Chanson>  {
    
    private String artiste;

    private String contenu;

    private Genre genre;

    private String date;
  
    public Chanson (int id, String titre, int duree, String artiste, String contenu, Genre genre, String date) {
        super(id, titre, duree);
        this.artiste = artiste;
        this.contenu = contenu;
        this.genre = genre;
        this.date = date;
    }
    
    public String getArtiste () {
        return artiste;
    }

    public void setArtiste (String artiste) {
        this.artiste = artiste;
    }

    public String getContenu () {
        return contenu;
    }

    public void setContenu (String contenu) {
        this.contenu = contenu;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public Genre getGenre () {
        return genre;
    }

    public void setGenre (Genre genre) {
        this.genre = genre;
    }

/**
 *  méthode toString qui transforme une chanson en chaîne de caractère.
 */
@Override //on surcharge cette méthode qui existe déjà
    public String toString() {
        return "Chanson :\n"
        + "id = " + this.getId() + "\n"
        + "titre = " + this.getTitre() + "\n"
        + "duree = " + this.getDuree() + "\n"
        + "artiste = " + this.getArtiste() + "\n"
        + "contenu = " + this.getContenu() + "\n"
        + "genre = " + this.getGenre() + "\n"
        + "date = " + this.getDate() + "\n";
        }

/**
 *  méthode compareTo qui trie deux chansons entre elles selon le genre et retourne une position dans la liste.
 */
@Override //on surcharge cette méthode qui existe déjà
    public int compareTo(Chanson chanson) {
        return this.getGenre().compareTo(chanson.getGenre()); //comparer String = ordre alphabétique
                                                              //si on met return chanson.getGenre().compareTo(this.getGenre())->on a l'inverse du tri 
    }    

}

