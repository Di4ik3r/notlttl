package d4.notlttl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import d4.notlttl.Notlttl;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
//		config.width = 1280;
//		config.height = 720;
		config.width = 1920;
		config.height = 1080;
		
		config.fullscreen = true;
		
		config.foregroundFPS = 60;
		config.vSyncEnabled = false;
		
		new LwjglApplication(new Notlttl(), config);
	}
}
