package com.myteam.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myteam.game.TrainMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Train Man";
		config.width = 800;
		config.height = 480;
		config.fullscreen = false;
//		config.useGL20 = true;
//		config.useCPUSynch = true;
		config.forceExit = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new TrainMan(), config);
	}
}
