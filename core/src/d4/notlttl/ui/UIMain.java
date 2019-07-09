package d4.notlttl.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import d4.notlttl.screen.MainScreen;

public class UIMain {

	private Stage stage;
	private Skin skin;
	private BitmapFont font;

	private Label labelX, labelY;

	public UIMain() {
		this.stage = new Stage(new StretchViewport(1366, 768));
		Gdx.input.setInputProcessor(stage);

		this.skin = new Skin(Gdx.files.internal("skin.json"));
		font = new BitmapFont(Gdx.files.internal("font.fnt"));

		labelX = createLabel("", 0.5f, new Vector2(0f, 30f));
		labelY = createLabel("", 0.5f, new Vector2(0f, 70f));
	}

	public void draw() {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		stage.act();
		stage.draw();

//		labelX.setText(Gdx.input.getX());
//		labelY.setText(Gdx.input.getY());
	}

	public void dispose() {
		stage.dispose();
	}

	// ************************************************** OWN FUCNTIONS
	// **************************************************

	private Label createLabel(String text, float fontScale, Vector2 point) {
		Label label = new Label(text, this.skin.get("default", LabelStyle.class));
		label.setPosition(point.x, point.y);
		label.setFontScale(fontScale);

		this.stage.addActor(label);

		return label;
	}

	private TextButton createButton(String text, float x, float y) {
		int lr = 30;
		int ud = 0;
		float fontScale = 0.45f;

		TextButtonStyle btnStyle = new TextButtonStyle();
		btnStyle.font = font;
		btnStyle.up = skin.getDrawable("cell");
		btnStyle.down = skin.getDrawable("cellMouseOnBlocked");
		btnStyle.over = skin.getDrawable("cellMouseOn");
		btnStyle.pressedOffsetX = 1;
		btnStyle.pressedOffsetY = -1;

		TextButton btn = new TextButton(text, btnStyle);
		btn.pad(ud, lr, ud, lr);
		btn.padBottom(ud + 7);
		btn.getLabel().setFontScale(fontScale);

		Table table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setPosition(x, y);
		table.add(btn).height(60);
		btn.setWidth(300);
		this.stage.addActor(table);

		return btn;
	}

	public void sendCoord(Vector3 vector) {
		labelX.setText(Float.toString(vector.x));
		labelY.setText(Float.toString(vector.y));

	}
}
