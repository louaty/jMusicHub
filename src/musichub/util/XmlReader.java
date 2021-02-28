package musichub.util;

import musichub.business.Chanson;
import musichub.business.Genre;
import musichub.business.IJouable;
import musichub.business.LivreAudio;
import musichub.business.Langue;
import musichub.business.Categorie;
import musichub.business.Album;
import musichub.business.Playlist;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * classe publique XmlReader qui lie les fichiers XML.
 *
 * les methodes sont publiques et sont expliquées plus tard dans le code.
 * 
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */


public class XmlReader  {

    //DocumentBuilder dBuilder;

    //public XmlReader() throws Exception {
    //    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    //    this.dBuilder = dbFactory.newDocumentBuilder();
    //} --> c'est le constructeur, pas obligatoire car fait dans les getChanson etc

 /**
 * méthode publique extractChanson qui extrait les chansons des fichiers XML en les prenant en paramètre en tant que listes de noeuds et les transforme en liste de chansons.
 * @return elle retourne donc une liste de chansons -chansons que l'on aura construit avec le constructeur et les éléments récupérés par TagName- et les trie avec sort().
 * 
 */       
    public List<Chanson> extractChanson(NodeList nList) {
        String id;
        String titre;
        String duree;
        String artiste;
        String contenu;
        String genre;
        String date;
        Chanson chanson;
        List<Chanson> chansons = new LinkedList<Chanson>();

        //on boucle pour toute la taille du fichier xml donc la liste de noeuds d'information
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            id = eElement.getElementsByTagName("id").item(0).getTextContent();
            titre = eElement.getElementsByTagName("titre").item(0).getTextContent();
            duree = eElement.getElementsByTagName("duree").item(0).getTextContent();
            artiste = eElement.getElementsByTagName("artiste").item(0).getTextContent();
            contenu = eElement.getElementsByTagName("contenu").item(0).getTextContent();
            genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
            date = eElement.getElementsByTagName("date").item(0).getTextContent();

            chanson = new Chanson(Integer.parseInt(id), titre, Integer.parseInt(duree), artiste, contenu, Genre.valueOf(genre), date);
            chansons.add(chanson);
        }
        Collections.sort(chansons);
        return chansons;
    }

 /**
 * méthode publique extractLivreAudio qui extrait les livres audio des fichiers XML en les prenant en paramètre en tant que listes de noeuds et les transforme en liste de livres audio.
 * @return elle retourne donc une liste de livres audio -livres que l'on aura construit avec le constructeur et les éléments récupérés par TagName- et les trie avec sort().
 * 
 */  
    public List<LivreAudio> extractLivreAudio(NodeList nList) {
        String id;
        String titre;
        String duree;
        String auteur;
        String contenu;
        String langue;
        String categorie;
        LivreAudio livreaudio;
        List<LivreAudio> livresaudio = new LinkedList<LivreAudio>();

        //on boucle pour toute la taille du fichier xml donc la liste de noeuds d'information
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                id = eElement.getElementsByTagName("id").item(0).getTextContent();
                titre = eElement.getElementsByTagName("titre").item(0).getTextContent();
                duree = eElement.getElementsByTagName("duree").item(0).getTextContent();
                auteur = eElement.getElementsByTagName("auteur").item(0).getTextContent();
                contenu = eElement.getElementsByTagName("contenu").item(0).getTextContent();
                langue = eElement.getElementsByTagName("langue").item(0).getTextContent();
                categorie = eElement.getElementsByTagName("categorie").item(0).getTextContent();

                livreaudio = new LivreAudio(Integer.parseInt(id), titre, Integer.parseInt(duree), auteur, contenu, Langue.valueOf(langue), Categorie.valueOf(categorie));
                livresaudio.add(livreaudio);
            }
        }
        Collections.sort(livresaudio);
        return livresaudio;
    }

/**
 * méthode publique getChanson qui permet d'extraire les chansons du fichier elements.xml.
 * Elle fait appel à la méthode extractChansons pour transformer les noeuds d'information du fichier en attribut d'une chanson.
 * @return elle retourne donc une liste de chansons.
 * 
 */     
    public List<Chanson> getChansons() throws Exception {
        List<Chanson> liste = null; //pour que la méthode sort fonctionne on change en List au lieu de Collection<Chanson>, il faut donc changer cela partout !

        File inputFile = new File("files/elements.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Chanson");
        liste = this.extractChanson(nList);

        //Collections.sort(liste)-> pas besoin car déjà dans le extractChanson
        return liste;   
        
    }

 /**
 * méthode publique getLivreAudio qui permet d'extraire les livres audio du fichier elements.xml.
 * Elle fait appel à la méthode extractLivresAudio pour transformer les noeuds d'information du fichier en attribut d'un livre audio.
 * @return elle retourne donc une liste de livres audio.
 * 
 */      
    public List<LivreAudio> getLivresAudio() throws Exception {
        List<LivreAudio> listelv = null;
 
        File inputFile = new File("files/elements.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("LivreAudio");
        listelv = this.extractLivreAudio(nList);

        //Collections.sort(listelv)-> pas besoin car déjà dans le extractLivreAudio
        return listelv;
    }

 /**
 * méthode publique getAlbums qui permet d'extraire les albums du fichier albums.xml.
 * Elle transforme les noeuds d'information du fichier en attribut d'un album.
 * @return elle retourne donc une liste d'albums et les trie avec sort().
 * 
 */ 
    public List<Album> getAlbums() throws Exception {
        String id;
        String titre;
        String duree;
        String artiste;
        String date;
        List<Chanson> listeChansons = new LinkedList<Chanson>();
        List<Album> listeAlbums = new LinkedList<Album>();

        
            File inputFile = new File("files/albums.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Album");

            //on boucle pour toute la taille du fichier xml donc la liste de noeuds d'information
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    titre = eElement.getElementsByTagName("titre").item(0).getTextContent();
                    duree = eElement.getElementsByTagName("duree").item(0).getTextContent();
                    NodeList nListeChanson = eElement.getElementsByTagName("Chanson");
                    listeChansons = this.extractChanson(nListeChanson);
                    artiste = eElement.getElementsByTagName("artiste").item(0).getTextContent();
                    date = eElement.getElementsByTagName("date").item(0).getTextContent();

                    Album album = new Album(Integer.parseInt(id), titre, Integer.parseInt(duree), listeChansons,
                            artiste, date);
                    listeAlbums.add(album);

                }
            }
        Collections.sort(listeAlbums);
        return listeAlbums;
    }

/**
 * méthode publique getPlaylists qui permet d'extraire les playlists du fichier playlists.xml.
 * Elle transforme les noeuds d'information du fichier en attribut d'une playlist.
 * @return elle retourne donc une liste de playlists et les trie avec sort().
 * 
 */    
    public List<Playlist> getPlaylists() throws Exception {
        String id;
        String nom;
        List<Chanson> chansons = null;
        List<LivreAudio> livresaudio = null;
        List<Playlist> listePlaylists = new LinkedList<Playlist>();

        File inputFile = new File("files/playlists.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Playlist");

        //on boucle pour toute la taille du fichier xml donc la liste de noeuds d'information
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    nom = eElement.getElementsByTagName("nom").item(0).getTextContent();
                    chansons = this.extractChanson(eElement.getElementsByTagName("Chanson"));
                    livresaudio = this.extractLivreAudio(eElement.getElementsByTagName("LivreAudio"));
                    
                    Collection<IJouable> listeElements = new LinkedList<IJouable>();//on garde IJouable car on veut y ajouter des chansons ET des lv 
                    listeElements.addAll(chansons);
                    listeElements.addAll(livresaudio);

                    Playlist playlist = new Playlist(Integer.parseInt(id), nom, listeElements);
                    listePlaylists.add(playlist);
                }
            }
        Collections.sort(listePlaylists);
        return listePlaylists;
    }
}
