package com.vn.stories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Link2 on 19/01/2016.
 */
public class Personnage extends Actor{
    private Sprite sprite;
    private Texture texture;
    private int id;
    private String nom;
    private int position;
    private String urlTexture;

    public static int LEFT = 1;
    public static int RIGHT = 2;

    public Personnage (String urlTexture, int position, int id, String nom) {
        this.urlTexture = urlTexture;
        this.texture = new Texture(urlTexture);
        this.position = position;
        this.id = id;
        this.nom = nom;
        this.creationSprite();
    }

    public void setPersonnageEmotion(String emotion){
        if(emotion.equals("default")){
            this.texture = new Texture(urlTexture);
        }else {
            this.texture = new Texture(urlTexture.replace(".", "_" + emotion + "."));
        }
        this.creationSprite();
    }

    public void creationSprite(){
        sprite = new Sprite(texture, 0, 0, texture.getWidth(), texture.getHeight());
        if(position == LEFT){
            sprite.setPosition(0, 0);
        }else if(position == RIGHT) {
            sprite.setPosition((Gdx.graphics.getWidth() / 2), 0);
            sprite.flip(true, false);
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
