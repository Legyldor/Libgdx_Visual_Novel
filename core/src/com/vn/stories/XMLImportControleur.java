package com.vn.stories;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Link2 on 26/01/2016.
 */
public class XMLImportControleur {

    private XmlReader xmlReader;
    private FileHandle file;

    public XMLImportControleur(FileHandle file) {
        xmlReader = new XmlReader();
        this.file = file;
    }

    public List<Scene> getScene(){
        List<Scene> sceneList = new ArrayList<Scene>();
        try {

            Element element = xmlReader.parse(file);
            Array<Element> elementsScene = element.getChildrenByName("scene");
            for(int i = 0;i<elementsScene.size; i++){
                //Recuperer l'id
                int idScene = elementsScene.get(i).getInt("id");
                //Recuperer le background
                Element elementBackground = elementsScene.get(i).getChildByName("background");
                String urlBackground = elementBackground.getText();
                Texture textureBackground = new Texture(urlBackground);
                Sprite spriteBackground = new Sprite(textureBackground,0,0,textureBackground.getWidth(), textureBackground.getHeight());
                Array<Element> elementsPersonnage = elementsScene.get(i).getChildrenByName("personnage");
                List<Personnage> personnageList = new ArrayList<Personnage>();
                for(int y = 0; y<elementsPersonnage.size;y++){
                    //Recupère le personnage
                    int id = elementsPersonnage.get(y).getInt("id");
                    String nom = elementsPersonnage.get(y).get("nom");
                    int position = elementsPersonnage.get(y).getInt("position");
                    String urlPersonnage = elementsPersonnage.get(y).get("image");
                    Personnage personnage = new Personnage(urlPersonnage, position, id, nom);
                    personnageList.add(personnage);
                }
                Array<Element> elementsDialogue = elementsScene.get(i).getChildrenByName("dialogue");
                List<Dialogue> dialogueList =new ArrayList<Dialogue>();
                for (int y=0; y<elementsDialogue.size;y++ ){
                    //Recupère le dialogue
                    int id = elementsDialogue.get(y).getInt("id");
                    int idPersonnage = elementsDialogue.get(y).getInt("idPersonnage");
                    String emotion = elementsDialogue.get(y).get("emotion");
                    String texte = elementsDialogue.get(y).get("texte");
                    Dialogue dialogue = new Dialogue(id,idPersonnage,emotion, texte);
                    dialogueList.add(dialogue);
                }
                Array<Element> elementsEvent = elementsScene.get(i).getChildrenByName("event");
                List<Event> eventList =new ArrayList<Event>();
                for (int y=0; y<elementsEvent.size;y++ ){
                    //Recupère le dialogue
                    int id = elementsEvent.get(y).getInt("id");
                    int type = elementsEvent.get(y).getInt("type");
                    int idDialogue = elementsEvent.get(y).getInt("idDialogue");
                    Event event = new Event(id,type, idDialogue);
                    eventList.add(event);
                }
                Scene scene = new Scene(idScene, personnageList, dialogueList, eventList, spriteBackground);
                sceneList.add(scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sceneList;
    }

    public Event getEventBySceneAndDialogue(Scene scene, Dialogue dialogue){
        Event event = null;
        try {
            Element element = xmlReader.parse(file);
            Array<Element> elementsScene = element.getChildrenByName("scene");
            Array<Element> elementsEvent = elementsScene.get(scene.getId()).getChildrenByName("event");
            for(int i=0; i<elementsEvent.size; i++){
                if(elementsEvent.get(i).getInt("idDialogue") == dialogue.getId()) {
                    int idDialogue = elementsEvent.get(i).getInt("idDialogue");
                    int id = elementsEvent.get(i).getInt("id");
                    int type = elementsEvent.get(i).getInt("type");
                    event = new Event(id, type, idDialogue);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return event;
    }

    public Personnage getPersonnageBySceneAndDialogue(Scene scene, Dialogue dialogue){
        Personnage personnage = null;
        try {
            Element element = xmlReader.parse(file);
            Array<Element> elementsScene = element.getChildrenByName("scene");
            Array<Element> elementsPersonnage = elementsScene.get(scene.getId()).getChildrenByName("personnage");
            for(int i=0; i<elementsPersonnage.size; i++){
                if(elementsPersonnage.get(i).getInt("id") == dialogue.getIdPersonnage()) {
                    String urlTexture = elementsPersonnage.get(i).get("image");
                    int id = elementsPersonnage.get(i).getInt("id");
                    int position = elementsPersonnage.get(i).getInt("position");
                    String nom = elementsPersonnage.get(i).get("nom");
                    personnage = new Personnage(urlTexture, position, id, nom);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personnage;
    }
}
