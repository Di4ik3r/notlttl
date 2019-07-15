package d4.notlttl.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import d4.notlttl.Notlttl;
import d4.notlttl.gameobject.GameObject;
import d4.notlttl.ui.UIMain;

public class MainScreen implements Screen, InputProcessor {

	private SpriteBatch batch;
	public static OrthographicCamera camera;
//	private UIMain ui;

	private Viewport viewport;

	private Sprite background, blank, words;
	private ShapeRenderer shapeRenderer;

	private ArrayList<ParticleEffect> particleList;

	private static float srWidthBorder, srHeightBorder, srWidthStep, srHeightStep, srScaleStep, cameraSpeed;
	
	private float slide, slideSpeed;

	private GameObject waterAnim;
	
	private Sound music;

	@Override
	public void show() {
		this.batch = new SpriteBatch();
//		aspectRatio = (float)Notlttl.GAME_HEIGHT/(float)Notlttl.GAME_WIDTH;
		MainScreen.camera = new OrthographicCamera();
		this.viewport = new FillViewport(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT, camera);
		this.viewport.apply();
		MainScreen.camera.position.set(Notlttl.GAME_WIDTH / 2, Notlttl.GAME_HEIGHT / 2, 0);

//		this.ui = new UIMain();

		float bgWidth, bgHeight;
		float bgMultiplier = 2.6f;
		bgWidth = 1534 * bgMultiplier;
		bgHeight = 1024 * bgMultiplier;
		this.background = new Sprite(new Texture(Gdx.files.internal("background.png")));
		this.background.setSize(bgWidth, bgHeight);
		this.background.setPosition(Notlttl.GAME_WIDTH/2 - bgWidth/2, Notlttl.GAME_HEIGHT/2 - bgHeight/2);
		
		this.blank = new Sprite(new Texture(Gdx.files.internal("blank.png")));
		this.blank.setSize(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT);
		this.blank.setPosition(Notlttl.GAME_WIDTH/2 - bgWidth/2, 0);
		this.slide = 0;
		this.slideSpeed = 7f;
//		
//		this.words = new Sprite(new Texture(Gdx.files.internal("words.png")));
//		this.words.setSize(bgWidth, bgHeight);
//		this.words.setPosition(Notlttl.GAME_WIDTH/2 - bgWidth/2, Notlttl.GAME_HEIGHT/2 - bgHeight/2);

		this.shapeRenderer = new ShapeRenderer();

		this.particleList = new ArrayList<ParticleEffect>();
//		this.particleList.add(this.createParticle("road.party", new Vector2(Notlttl.GAME_WIDTH/2 - Notlttl.GAME_WIDTH/2, Notlttl.GAME_HEIGHT/2-55)));
		this.particleList.add(this.createParticle("road.party", new Vector2(-500F, Notlttl.GAME_HEIGHT/2-55)));
		this.particleList.add(this.createParticle("stars.party", new Vector2(Notlttl.GAME_WIDTH/2, Notlttl.GAME_HEIGHT/2 + 500)));
		this.particleList.add(this.createParticle("P.party", new Vector2(-100f, 0)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 200)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 400)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 600)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 800)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 20f)));
//		this.particleList.add(this.createParticle("P.party", new Vector2(0f, 400f)));

//		Gdx.input.setInputProcessor(this);

		MainScreen.srWidthBorder = 1.6252f;
		MainScreen.srHeightBorder = 1f;
		MainScreen.srWidthStep = 16 * 5;
		MainScreen.srHeightStep = 9 * 5;
//		MainScreen.srScaleStep = (16f/9f)/100f-0.01f;
		MainScreen.srScaleStep = 0.0208f;
		MainScreen.cameraSpeed = 30;

		float waterWidth, waterHeight;
		float waterMultiplier = 1.1f;
		waterWidth = 1176 * waterMultiplier;
		waterHeight = 756 * waterMultiplier;
		this.waterAnim = new GameObject(Notlttl.atlas.findRegion("frame", 0),
				new Vector2(Notlttl.GAME_WIDTH / 2 - waterWidth / 2, Notlttl.GAME_HEIGHT / 2 - waterHeight / 2 - 100),
				new Vector2(waterWidth, waterHeight));
		ArrayList<TextureRegion> backgroundRegions = new ArrayList<TextureRegion>();
		for (int i = 0; i < 61; i++)
			backgroundRegions.add(Notlttl.atlas.findRegion("frame", i));
		this.waterAnim.initAnimations(backgroundRegions);
		
		music = Gdx.audio.newSound(Gdx.files.internal("music.mp3"));
		long mID = music.play(0.6f);
	}

	@Override
	public void render(float delta) {
//		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//			MainScreen.camera.translate(-MainScreen.cameraSpeed, 0);
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//			MainScreen.camera.translate(MainScreen.cameraSpeed, 0);
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			MainScreen.camera.translate(0, MainScreen.cameraSpeed);
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//			MainScreen.camera.translate(0, -MainScreen.cameraSpeed);
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
//			MainScreen.srWidthBorder -= MainScreen.srScaleStep;
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//			MainScreen.srWidthBorder += MainScreen.srScaleStep;
//		}

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
//		if(this.blank.getHeight() <= Notlttl.GAME_HEIGHT) {
//			this.blank.getBoundingRectangle().setHeight(this.blank.getBoundingRectangle().y + this.slideSpeed);
//			this.blank.setY(this.blank.getHeight() + this.slideSpeed);
		if(this.slide <= Notlttl.GAME_HEIGHT) {
			this.slide += this.slideSpeed;
			this.blank.setPosition((int)(1534/2), (int)this.slide);
		}


		MainScreen.camera.update();
		this.updateParticles();

		this.batch.setProjectionMatrix(camera.combined);
		this.batch.begin();
		
			this.particleList.get(2).draw(batch);
			this.waterAnim.draw(batch, delta);
//			this.drawParticles();
			this.background.draw(batch);
			this.particleList.get(1).draw(batch);
			this.particleList.get(0).draw(batch);
			
			this.blank.draw(batch);
		this.batch.end();

//		this.ui.sendInfo(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)),MainScreen.srWidthBorder);
//		this.ui.draw();
	}

	@Override
	public void resize(int width, int height) {
		MainScreen.camera.viewportWidth = Notlttl.GAME_WIDTH;
		MainScreen.camera.viewportHeight = Notlttl.GAME_HEIGHT;
		this.viewport.update(width, height);
		this.viewport.update(Notlttl.GAME_WIDTH, Notlttl.GAME_HEIGHT);
		MainScreen.camera.position.set(Notlttl.GAME_WIDTH / 2f, Notlttl.GAME_HEIGHT / 2f, 0);
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
		this.background.getTexture().dispose();
		this.disposeParticles();
		this.music.dispose();
		this.background.getTexture().dispose();
		this.blank.getTexture().dispose();
	}

	private ParticleEffect createParticle(String file, Vector2 point) {
		ParticleEffect particle = new ParticleEffect();
		particle.load(Gdx.files.internal(file), Gdx.files.internal(""));
		particle.getEmitters().first().setPosition(point.x, point.y);
		particle.start();
		return particle;
	}

	private void drawParticles() {
		for (ParticleEffect particle : particleList) {
			particle.draw(this.batch);
		}
	}

	private void updateParticles() {
		for (ParticleEffect particle : particleList) {
			particle.update(Gdx.graphics.getDeltaTime());
		}
	}

	private void disposeParticles() {
		for (ParticleEffect particle : particleList) {
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
//		MainScreen.camera.zoom += amount > 0 ? +0.04 : -0.04;
//		MainScreen.camera.update();
		return false;
	}
}
