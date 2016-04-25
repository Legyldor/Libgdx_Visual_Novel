package com.vn.stories;

/**
 * Created by Link2 on 28/01/2016.
 */
public class Event {

    private int id;
    private int type;
    private int idDialogue;

    public Event(int id, int type, int idDialogue) {
        this.id = id;
        this.type = type;
        this.idDialogue = idDialogue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdDialogue() {
        return idDialogue;
    }

    public void setIdDialogue(int idDialogue) {
        this.idDialogue = idDialogue;
    }
}
