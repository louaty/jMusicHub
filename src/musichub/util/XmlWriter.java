package musichub.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import musichub.business.Album;
import musichub.business.Chanson;
import musichub.business.IJouable;
import musichub.business.LivreAudio;
import musichub.business.Playlist;

/**
 * classe publique XmlWriter qui écrit dans les fichiers XML.
 * 
 * @param dBuilder créateur de document
 * @param elementsFile information des éléments
 * @param albumsFile information des albums
 * @param playlistsFile information des playlists
 *
 *
 * les methodes sont expliquées plus tard dans le code.
 * 
 * @author Sarra MADAD, Nour El-Houda LOUATY.
 */

public class XmlWriter {

    DocumentBuilder dBuilder;
    String elementsFile;
    String albumsFile;
    String playlistsFile;

 /**
 * méthode publique XmlWriter fait le lien entre les fichiers xml et les attributs de la classe.
 * constructeur de la classe XmlWriter
 */      
    public XmlWriter() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        this.dBuilder = dbFactory.newDocumentBuilder();
        this.elementsFile = "files/elements.xml";
        this.albumsFile = "files/albums.xml";
        this.playlistsFile = "files/playlists.xml";
    }

 /**
 * méthode privée saveChansons qui sauvegarde les chansons saisies dans un fichier XML
 * 
 */     
    private void saveChansons(List<Chanson> chansons, Document document, Element elements) {
        Element echanson, eid, etitre, eduree, eartiste, econtenu, egenre, edate;

        for (Chanson chanson : chansons) {
            // chanson element
            echanson = document.createElement("Chanson");
            elements.appendChild(echanson);

            // id chanson element
            eid = document.createElement("id");
            eid.appendChild(document.createTextNode(Integer.toString(chanson.getId())));
            echanson.appendChild(eid);

            // titre chanson element
            etitre = document.createElement("titre");
            etitre.appendChild(document.createTextNode(chanson.getTitre()));
            echanson.appendChild(etitre);

            // duree chanson element
            eduree = document.createElement("duree");
            eduree.appendChild(document.createTextNode(Integer.toString(chanson.getDuree())));
            echanson.appendChild(eduree);

            // artiste chanson element
            eartiste = document.createElement("artiste");
            eartiste.appendChild(document.createTextNode(chanson.getArtiste()));
            echanson.appendChild(eartiste);

            // contenu chanson element
            econtenu = document.createElement("contenu");
            econtenu.appendChild(document.createTextNode(chanson.getContenu()));
            echanson.appendChild(econtenu);

            // genre chanson element
            egenre = document.createElement("genre");
            egenre.appendChild(document.createTextNode(chanson.getGenre().name()));
            echanson.appendChild(egenre);

            // date chanson element
            edate = document.createElement("date");
            edate.appendChild(document.createTextNode(chanson.getDate()));
            echanson.appendChild(edate);
        }
    }

 /**
 * méthode privée saveLivresAudios qui sauvegarde les livres audio saisis dans un fichier XML
 * 
 */  
    private void saveLivresAudios(List<LivreAudio> livresaudios, Document document, Element elements) {
        Element elivreaudio, eid, etitre, eduree, eartiste, econtenu, egenre, edate;

        for (LivreAudio livreaudio : livresaudios) {
            // livreaudio element
            elivreaudio = document.createElement("LivreAudio");
            elements.appendChild(elivreaudio);

            // id livreaudio element
            eid = document.createElement("id");
            eid.appendChild(document.createTextNode(Integer.toString(livreaudio.getId())));
            elivreaudio.appendChild(eid);

            // titre livreaudio element
            etitre = document.createElement("titre");
            etitre.appendChild(document.createTextNode(livreaudio.getTitre()));
            elivreaudio.appendChild(etitre);

            // duree livreaudio element
            eduree = document.createElement("duree");
            eduree.appendChild(document.createTextNode(Integer.toString(livreaudio.getDuree())));
            elivreaudio.appendChild(eduree);

            // auteur livreaudio element
            eartiste = document.createElement("auteur");
            eartiste.appendChild(document.createTextNode(livreaudio.getAuteur()));
            elivreaudio.appendChild(eartiste);

            // contenu livreaudio element
            econtenu = document.createElement("contenu");
            econtenu.appendChild(document.createTextNode(livreaudio.getContenu()));
            elivreaudio.appendChild(econtenu);

            // langue livreaudio element
            egenre = document.createElement("langue");
            egenre.appendChild(document.createTextNode(livreaudio.getLangue().name()));
            elivreaudio.appendChild(egenre);

            // categorie livreaudio element
            edate = document.createElement("categorie");
            edate.appendChild(document.createTextNode(livreaudio.getCategorie().name()));
            elivreaudio.appendChild(edate);
        }
    }

 /**
 * méthode privée saveElements qui sauvegarde les éléments (livres audio et chansons) saisis dans un fichier XML
 * 
 */      
    private void saveElements(List<Chanson> chansons, List<LivreAudio> livresaudios) throws Exception {
        Collections.sort(chansons);
        Collections.sort(livresaudios);

        Document document = dBuilder.newDocument();
        Element elements = document.createElement("Elements");
        document.appendChild(elements);

        this.saveChansons(chansons, document, elements);
        this.saveLivresAudios(livresaudios, document, elements);

        //configuration du fichier pour le bon format XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(this.elementsFile);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(domSource, result);
    }

 /**
 * méthode privée saveAlbums qui sauvegarde les albums saisie dans un fichier XML
 * 
 */  
    private void saveAlbums(List<Album> albums) throws Exception {
        Collections.sort(albums);

        Element ealbum, eid, etitre, eduree, eartiste, edate;

        Document document = dBuilder.newDocument();
        Element elements = document.createElement("Albums");
        document.appendChild(elements);

        for (Album album : albums) {
            // album element
            ealbum = document.createElement("Album");
            elements.appendChild(ealbum);

            // id album element
            eid = document.createElement("id");
            eid.appendChild(document.createTextNode(Integer.toString(album.getId())));
            ealbum.appendChild(eid);

            // titre album element
            etitre = document.createElement("titre");
            etitre.appendChild(document.createTextNode(album.getTitre()));
            ealbum.appendChild(etitre);

            // duree album element
            eduree = document.createElement("duree");
            eduree.appendChild(document.createTextNode(Integer.toString(album.getDuree())));
            ealbum.appendChild(eduree);

            // artiste album element
            eartiste = document.createElement("artiste");
            eartiste.appendChild(document.createTextNode(album.getArtiste()));
            ealbum.appendChild(eartiste);

            // date album element
            edate = document.createElement("date");
            edate.appendChild(document.createTextNode(album.getDate()));
            ealbum.appendChild(edate);

            // chanson album element
            this.saveChansons((List<Chanson>)album.getChanson(), document, ealbum);
        }

        //configuration du fichier pour le bon format XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(this.albumsFile);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(domSource, result);
    }

 /**
 * méthode privée savePlaylists qui sauvegarde les playlists saisie dans un fichier XML
 * 
 */      
    private void savePlaylists(List<Playlist> playlists) throws Exception {
        Collections.sort(playlists);

        Element eplaylist, eid, enom;

        Document document = dBuilder.newDocument();
        Element elements = document.createElement("Playlists");
        document.appendChild(elements);

        for (Playlist playlist : playlists) {
            // playlist element
            eplaylist = document.createElement("Playlist");
            elements.appendChild(eplaylist);

            // id playlist element
            eid = document.createElement("id");
            eid.appendChild(document.createTextNode(Integer.toString(playlist.getId())));
            eplaylist.appendChild(eid);

            // titre playlist element
            enom = document.createElement("nom");
            enom.appendChild(document.createTextNode(playlist.getNom()));
            eplaylist.appendChild(enom);

            List<Chanson> chansons = new LinkedList<Chanson> ();
            for (IJouable jouable : playlist.getListe()) {
                if (jouable instanceof Chanson) {
                    chansons.add((Chanson)jouable);
                }
            }

            List<LivreAudio> livresaudios = new LinkedList<LivreAudio> ();
            for (IJouable jouable : playlist.getListe()) {
                if (jouable instanceof LivreAudio) {
                    livresaudios.add((LivreAudio)jouable);
                }
            }

            // chansons playlist element
            this.saveChansons(chansons, document, eplaylist);

            // livresaudios playlist element
            this.saveLivresAudios(livresaudios, document, eplaylist);
        }

        //configuration du fichier pour le bon format XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(this.playlistsFile);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(domSource, result);
    }

 /**
 * méthode publique save qui sauvegarde tous les différents éléments de l'application (chansons, livres audio, albums et playlists) dans des listes de chaque élément correspondant
 * 
 */      
    public void save (List<Chanson> chansons, List<LivreAudio> livresaudios, List<Album> albums, List<Playlist> playlists)
    throws Exception {
        this.saveElements(chansons, livresaudios);
        this.saveAlbums(albums);
        this.savePlaylists(playlists);
    }
}