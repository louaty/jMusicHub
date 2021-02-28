package musichub.business;

/**
 * classe publique LivreAudio qui hérite de la classe abstraite AbstractOeuvre et implémente l'interface IJouable et Comparable qui permet de trier des livres audio.
 * @param id identifiant unique de l'oeuvre (hérité de la classe abstraite)
 * @param titre titre de l'oeuvre (hérité de la classe abstraite)
 * @param duree durée en seconde de l'oeuvre (hérité de la classe abstraite)
 * @param auteur privé, nom de l'auteur
 * @param contenu privé, contenu de la chanson
 * @param langue privé, langue du livre audio 
 * @see Langue
 * @param categorie privé, catégorie du livre audio
 * @see Categorie
 *
 * les premières methodes sont publiques et sont des getteurs et setteurs basiques pour chacun des attributs.
 * les méthodes toString et compareTo sont précisées plus tard.
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class LivreAudio extends AbstractOeuvre implements IJouable, Comparable<LivreAudio> {
    
    private String auteur;

    private String contenu;

    private Langue langue;

    private Categorie categorie;

    public LivreAudio (int id, String titre, int duree, String auteur, String contenu, Langue langue, Categorie categorie) {
        super(id, titre, duree);
        this.auteur = auteur;
        this.contenu = contenu;
        this.langue = langue;
        this.categorie = categorie;
    }

    public String getAuteur () {
        return auteur;
    }

    public void setAuteur (String auteur) {
        this.auteur = auteur;
    }  

    public String getContenu () {
        return contenu;
    }

    public void setContenu (String contenu) {
        this.contenu = contenu;
    }

    public Langue getLangue () {
        return langue;
    }

    public void setLangue (Langue langue) {
        this.langue = langue;
    }

    public Categorie getCategorie () {
        return categorie;
    }

    public void setCategorie (Categorie categorie) {
        this.categorie = categorie;
    } 

/**
 *  méthode toString qui transforme un livre audio en chaîne de caractère.
 */
@Override //on surcharge cette méthode qui existe déjà
    public String toString() {
        return "---------\n"
        + "LivreAudio :\n"
        + "id = " + this.getId() + "\n"
        + "titre = " + this.getTitre() + "\n"
        + "duree = " + this.getDuree() + "\n"
        + "auteur = " + this.getAuteur() + "\n"
        + "contenu = " + this.getContenu() + "\n"
        + "langue = " + this.getLangue() + "\n"
        + "categorie = " + this.getCategorie() + "\n";
        }
        
/**
 *  méthode compareTo qui trie deux livres audio entre eux selon l'auteur et retourne une position dans la liste.
 */
@Override //on surcharge cette méthode qui existe déjà
    public int compareTo(LivreAudio livreaudio) {
        return this.getAuteur().compareTo(livreaudio.getAuteur()); //comparer String = ordre alphabétique
    }
}
