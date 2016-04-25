package com.vn.stories;

/**
 * Created by Link2 on 27/01/2016.
 */
public class Dialogue {
    private int id;
    private int idPersonnage;
    private String emotion;
    private String texte;
    public static String NO_EMOTION = "default";

    public Dialogue(int id, int idPersonnage, String emotion, String texte) {
        this.id = id;
        this.idPersonnage = idPersonnage;
        this.emotion = emotion;
        this.texte = texte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersonnage() {
        return idPersonnage;
    }

    public void setIdPersonnage(int idPersonnage) {
        this.idPersonnage = idPersonnage;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
