package d4.notlttl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import d4.notlttl.screen.MainScreen;
import d4.notlttl.utils.Assets;

public class Notlttl extends Game {

	public static final int GAME_WIDTH = 3840;
	public static final int GAME_HEIGHT = 2160;
	private MainScreen mainScreen;
	
	private static Assets assets;
	public static TextureAtlas atlas;
	
	@Override
	public void create() {
		Notlttl.assets = new Assets("assets.atlas");
		Notlttl.atlas = Notlttl.assets.getManager().get("assets.atlas", TextureAtlas.class);
		
		this.mainScreen = new MainScreen();
		
		this.setScreen(mainScreen);
	}

	@Override
	public void dispose() {
		
	}
}
