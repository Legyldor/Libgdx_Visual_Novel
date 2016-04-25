package com.vn.stories.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vn.stories.VNStories;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		// fullscreen
		//config.fullscreen = true;
		// vSync
		//config.vSyncEnabled = true;
		new LwjglApplication(new VNStories(), config);
	}
}
