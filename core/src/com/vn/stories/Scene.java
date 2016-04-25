package com.vn.stories;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

/**
 * Created by Link2 on 27/01/2016.
 */
public class Scene {
    private int id;
    private List<Personnage> personnageList;
    private List<Dialogue> dialogueList;
    private List<Event> eventList;
    private Sprite background;
    private int indiceDialogue = 0;

    public Scene(int id, List<Personnage> personnageList, List<Dialogue> dialogueList, List<Event> eventList, Sprite background) {
        this.personnageList = personnageList;
        this.dialogueList = dialogueList;
        this.background = background;
        this.id = id;
        this.eventList = eventList;
    }

    public List<Personnage> getPersonnageList() {
        return personnageList;
    }

    public void setPersonnageList(List<Personnage> personnageList) {
        this.personnageList = personnageList;
    }

    public void setPersonnageEmotion(int id, String emotion){
        for(int i =0;i<this.personnageList.size();i++){
            if(this.personnageList.get(i).getId() == id){
                this.personnageList.get(i).setPersonnageEmotion(emotion);
            }
        }
    }

    public List<Dialogue> getDialogueList() {
        return dialogueList;
    }

    public void setDialogueList(List<Dialogue> dialogueList) {
        this.dialogueList = dialogueList;
    }

    public Sprite getBackground() {
        return background;
    }

    public void setBackground(Sprite background) {
        this.background = background;
    }

    public int getIndiceDialogue() {
        return indiceDialogue;
    }

    public void setIndiceDialogue(int indiceDialogue) {
        this.indiceDialogue = indiceDialogue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
