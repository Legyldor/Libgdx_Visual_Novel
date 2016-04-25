package com.vn.stories;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.util.List;


public class VNStories implements ApplicationListener {
	SpriteBatch batch;
	Sprite spriteBackground;
	Label label;
    Label.LabelStyle labelStyle;


	private Stage stage;
	private Table table;
	private Skin skin;
	private String texte;
	private int indiceScene =0;
	private int indice = 0;
	private boolean affichage = false;
	private List<Scene> sceneList;
    private XMLImportControleur xmlImportControleur;

    public VNStories() {

    }

    @Override
	public void create () {
        xmlImportControleur = new XMLImportControleur(Gdx.files.internal("data/storie.storie"));
		sceneList = xmlImportControleur.getScene();
		// field maxSpritesInBatch indique le nombre de sprite au CPU et peu faire gagner de la perf si bien attribué
		batch = new SpriteBatch();

        creerScene();
	}

    public void creerScene(){
        //Ajout du background
        spriteBackground = sceneList.get(indiceScene).getBackground();

        // Instance du stage
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        //Ajout des acteurs dans le stage
        for (int i = 0; i<sceneList.get(indiceScene).getPersonnageList().size();i++){
            stage.addActor(sceneList.get(indiceScene).getPersonnageList().get(i));
        }

        //Création d'une table pour l'ui du dialogue
        table = new Table(skin);
        table.setFillParent(false);
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight() / 3);
        table.setBackground(skin.getDrawable("default-window"));
        table.setSkin(skin);
        //Ajout de la table dans le stage
        stage.addActor(table);

        //Affiche les limites des elements de la table
        //table.setDebug(true); // This is optional, but enables debug lines for tables.

        //Creation d'UI
        labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        labelStyle.font = new BitmapFont(Gdx.files.internal("data/utf-font.fnt"));
        label = new Label("",labelStyle);
        label.setWrap(true);
        label.setFontScale((float) 1.5, (float) 1.5);

        table.add(label).width(table.getWidth()).height(table.getHeight());

        texte = sceneList.get(indiceScene).getDialogueList().get(sceneList.get(indiceScene).getIndiceDialogue()).getTexte();

        label.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if(affichage == true){
                    if(sceneList.get(indiceScene).getIndiceDialogue() < sceneList.get(indiceScene).getDialogueList().size()-1) {
                        sceneList.get(indiceScene).setIndiceDialogue(sceneList.get(indiceScene).getIndiceDialogue() + 1);
                        texte = sceneList.get(indiceScene).getDialogueList().get(sceneList.get(indiceScene).getIndiceDialogue()).getTexte();
                        int idperso = sceneList.get(indiceScene).getDialogueList().get(sceneList.get(indiceScene).getIndiceDialogue()).getIdPersonnage();
                        String emotion = sceneList.get(indiceScene).getDialogueList().get(sceneList.get(indiceScene).getIndiceDialogue()).getEmotion();
                        sceneList.get(indiceScene).setPersonnageEmotion(idperso, emotion);
                        creerScene();
                        label.setText("");
                        indice = 0;
                        affichage = false;
                    }else if(indiceScene < sceneList.size()-1){
                        sceneList.get(indiceScene).setIndiceDialogue(sceneList.get(indiceScene).getIndiceDialogue() + 1);
                        indiceScene++;
                        creerScene();
                        label.setText("");
                        indice = 0;
                        affichage = false;
                    }
                } else {
                    affichage = true;
                }
            }
        });
    }

	public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
	}

	@Override
    public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());

		//Defilement texte
		char[] txt = texte.toCharArray();
		if(affichage == true){
			label.setText(texte);
		} else {
			if(indice < txt.length) {
				label.setText(label.getText() + "" +txt[indice]);
				indice++;
			}else {
                affichage = true;
            }
		}

		// Go dessiner
		batch.begin();
		// Peut faire gagner des perf lors de l'affichage du background
		batch.disableBlending();
		spriteBackground.draw(batch);
		batch.enableBlending();
        stage.draw();
		// Fin du dessin
		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
