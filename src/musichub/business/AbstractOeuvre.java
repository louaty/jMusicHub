package musichub.business;

/**
 * classe abstraite publique AbstractOeuvre qui parente les classes Albums, Chanson et LivreAudio.
 * @param id privé, identifiant unique de l'oeuvre
 * @param titre privé, titre de l'oeuvre
 * @param duree privé, durée en seconde de l'oeuvre
 *
 * les methodes sont publiques et sont des getteurs et setteurs basiques pour chacun des attributs.
 * 
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class AbstractOeuvre {

private int id;

private String titre;

private int duree;

public AbstractOeuvre (int id, String titre, int duree) {
    this.id = id;
    this.titre = titre;
    this.duree = duree;
}

public int getId () {
    return id;
}

public String getTitre () {
    return titre;
}

public void setTitre (String titre) {
    this.titre = titre;
}

public int getDuree () {
    return duree;
}

public void setDuree (int duree) {
    this.duree = duree;
}

}