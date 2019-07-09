package d4.notlttl.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import d4.notlttl.Notlttl;
import d4.notlttl.ui.UIMain;

public class MainScreen implements Screen {

	private SpriteBatch batch;
	public static OrthographicCamera camera;
	private UIMain ui;
	
	private float aspectRatio;
	private Viewport viewport;
	
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
	
	@Override
	public void show() {
		this.batch = new SpriteBatch();
		aspectRatio = (float)Notlttl.GAME_HEIGHT/(float)Notlttl.GAME_WIDTH;
		camera = new OrthographicCamera();
		viewport = new FillViewport(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT, camera);
		viewport.apply();
		camera.position.set(Notlttl.GAME_WIDTH/2, Notlttl.GAME_HEIGHT/2, 0);
		
		
		ui = new UIMain();
		
		sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		sprite.setSize(80, 90);
		sprite.setPosition(80, 45);
		
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			sprite.draw(batch);
		batch.end();
		
		shapeRenderer.begin(ShapeType.Line);
			for(int i = 0; i <= Notlttl.GAME_WIDTH; i+= 16 ) {
				shapeRenderer.line(new Vector2(i, 0), new Vector2(i, Notlttl.GAME_HEIGHT));
				for(int j = 0; j <= Notlttl.GAME_HEIGHT; j+= 9) {
					shapeRenderer.line(new Vector2(0, j), new Vector2(Notlttl.GAME_WIDTH, j));
				}
			}
		shapeRenderer.end();
		
//		ui.sendCoord(camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0)));
		ui.draw();
	}

	@Override
	public void resize(int width, int height) {
	    camera.viewportWidth = Notlttl.GAME_WIDTH;
	    camera.viewportHeight = Notlttl.GAME_HEIGHT;
		viewport.update(width, height);
		viewport.update(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT);
		camera.position.set(Notlttl.GAME_WIDTH/2f, Notlttl.GAME_HEIGHT/2f, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		this.batch.dispose();
	}
	
}
