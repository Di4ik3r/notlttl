package d4.notlttl.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
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

public class MainScreen implements Screen, InputProcessor {

	private SpriteBatch batch;
	public static OrthographicCamera camera;
	private UIMain ui;
	
	private Viewport viewport;
	
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
	
	private ArrayList<ParticleEffect> particleList;
	
	private static float srWidthBorder, srHeightBorder, srWidthStep, srHeightStep, srScaleStep;
	
	@Override
	public void show() {
		this.batch = new SpriteBatch();
//		aspectRatio = (float)Notlttl.GAME_HEIGHT/(float)Notlttl.GAME_WIDTH;
		MainScreen.camera = new OrthographicCamera();
		this.viewport = new FillViewport(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT, camera);
		this.viewport.apply();
		MainScreen.camera.position.set(Notlttl.GAME_WIDTH/2, Notlttl.GAME_HEIGHT/2, 0);
		
		
		this.ui = new UIMain();
		
		this.sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
		this.sprite.setSize(80, 90);
		this.sprite.setPosition(80, 45);
		
		this.shapeRenderer = new ShapeRenderer();
		
		this.particleList = new ArrayList<ParticleEffect>();
		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 0f)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 20f)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 400f)));
		
		Gdx.input.setInputProcessor(this);
		
		MainScreen.srWidthBorder = 1.75f;
		MainScreen.srHeightBorder = 1f;
		MainScreen.srWidthStep = 16*5;
		MainScreen.srHeightStep = 9*5;
		MainScreen.srScaleStep = (9f/16f)/32f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			MainScreen.camera.translate(-10, 0);
		} 
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			MainScreen.camera.translate(10, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			MainScreen.camera.translate(0, 10);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			MainScreen.camera.translate(0, -10);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			MainScreen.srWidthBorder -= MainScreen.srScaleStep;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			MainScreen.srWidthBorder += MainScreen.srScaleStep;
		}

		
		this.shapeRenderer.setProjectionMatrix(camera.combined);
		this.shapeRenderer.begin(ShapeType.Line);
			for(int i = 0; i <= Notlttl.GAME_WIDTH * MainScreen.srWidthBorder; i+= MainScreen.srWidthStep ) {
				shapeRenderer.setColor(i <= Notlttl.GAME_WIDTH ? new Color(1f, 1f, 1f, 0f) : new Color(1f, 0f, 0f, 0f));
				this.shapeRenderer.line(new Vector2(i, 0), new Vector2(i, Notlttl.GAME_HEIGHT));
				
				for(int j = 0; j <= Notlttl.GAME_HEIGHT * MainScreen.srHeightBorder; j+= MainScreen.srHeightStep) {
					shapeRenderer.setColor(j <= Notlttl.GAME_HEIGHT ? new Color(1f, 1f, 1f, 0f) : new Color(1f, 0f, 0f, 0f));
					this.shapeRenderer.line(new Vector2(0, j), new Vector2(Notlttl.GAME_WIDTH, j));
					
					shapeRenderer.setColor(new Color(1f, 0f, 0f, 0f));
					this.shapeRenderer.line(new Vector2(Notlttl.GAME_WIDTH, j), new Vector2(Notlttl.GAME_WIDTH * MainScreen.srWidthBorder, j));
				}
			}
		this.shapeRenderer.end();
		
		MainScreen.camera.update();
		this.updateParticles();
		
		this.batch.setProjectionMatrix(camera.combined);
		
		this.batch.begin();
			this.drawParticles();
			this.sprite.draw(batch);
		this.batch.end();
		
		
		this.ui.sendInfo(camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0)), MainScreen.srWidthBorder);
		this.ui.draw();
	}

	@Override
	public void resize(int width, int height) {
		MainScreen.camera.viewportWidth = Notlttl.GAME_WIDTH;
		MainScreen.camera.viewportHeight = Notlttl.GAME_HEIGHT;
		this.viewport.update(width, height);
		this.viewport.update(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT);
		MainScreen.camera.position.set(Notlttl.GAME_WIDTH/2f, Notlttl.GAME_HEIGHT/2f, 0);
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
		this.sprite.getTexture().dispose();
		this.disposeParticles();
	}
	
	
	
	
	
	private ParticleEffect createParticle(String file, Vector2 point) {
		ParticleEffect particle = new ParticleEffect();
		particle.load(Gdx.files.internal(file), Gdx.files.internal(""));
		particle.getEmitters().first().setPosition(point.x, point.y);
		particle.start();
		return particle;
	}
	
	private void drawParticles() {
		for(ParticleEffect particle: particleList) {
			particle.draw(this.batch);
		}
	}
	
	private void updateParticles() {
		for(ParticleEffect particle: particleList) {
			particle.update(Gdx.graphics.getDeltaTime());
		}
	}
	
	private void disposeParticles() {
		for(ParticleEffect particle : particleList) {
			particle.dispose();
		}
		
		particleList = null;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		MainScreen.camera.zoom += amount > 0 ? +0.04f : -0.04f;
//		MainScreen.camera.zoom -= 0.01f;
		MainScreen.camera.update();
		
		return false;
	}
}
