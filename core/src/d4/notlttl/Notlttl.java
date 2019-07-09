package d4.notlttl;

import com.badlogic.gdx.Game;

import d4.notlttl.screen.MainScreen;

public class Notlttl extends Game {

	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;
	private MainScreen mainScreen;
	
	@Override
	public void create() {
		this.mainScreen = new MainScreen();
		
		this.setScreen(mainScreen);
	}

	@Override
	public void dispose() {
		
	}
}
