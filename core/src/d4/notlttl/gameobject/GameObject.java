package d4.notlttl.gameobject;

import java.awt.Dimension;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class GameObject {

	private Polygon bounds;
	private Sprite sprite;
	
	public GameObject(TextureRegion textureRegion, float x, float y, float width, float height) {
		this.sprite = new Sprite(textureRegion);
		this.sprite.setSize(width, height);
		this.sprite.setOrigin(width / 2f, height / 2f);
		this.sprite.setPosition(x, y);

		this.bounds = new Polygon(new float[] { 0f, 0f, width, 0f, width, height, 0f, height });
		this.bounds.setPosition(x, y);
		this.bounds.setOrigin(width / 2f, height / 2f);
	}
	
	public GameObject(TextureRegion textureRegion, Vector2 point, Dimension size) {
		this.sprite = new Sprite(textureRegion);
		this.sprite.setSize(size.width, size.height);
		this.sprite.setOrigin(size.width / 2f, size.height / 2f);
		this.sprite.setPosition(point.x, point.y);

		this.bounds = new Polygon(new float[] { 0f, 0f, size.width, 0f, size.width, size.height, 0f, size.height });
		this.bounds.setPosition(point.x, point.y);
		this.bounds.setOrigin(size.width / 2f, size.height / 2f);
	}
	
	public void draw(SpriteBatch batch) {
		this.sprite.setPosition(this.bounds.getX(), this.bounds.getY());
		this.sprite.setRotation(this.bounds.getRotation());
		this.sprite.draw(batch);
	}
}
