package csc2003.prac1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import csc2003.prac1.SuperCoolFunTetris;

/*
 * Max Whitmore
 * CSC2003S
 * 11 August 2016
 *
 * Class to launch the tetris application on the desktop.
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SuperCoolFunTetris.WIDTH;
		config.height = SuperCoolFunTetris.HEIGHT;
		config.resizable = false;

		new LwjglApplication(new SuperCoolFunTetris(), config);
	}
}