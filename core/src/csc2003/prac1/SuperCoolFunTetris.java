package csc2003.prac1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.ApplicationListener;

public class SuperCoolFunTetris extends com.badlogic.gdx.Game implements ApplicationListener {
	public static final int WIDTH = Board.width;
	public static final int HEIGHT = Board.height;

	private PlayTetris game;

	@Override
	public void create() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		// cool, retro font I found
		BitmapFont font = new BitmapFont(new FileHandle("computerfont.txt"), new FileHandle("computerfont.png"), false);
		font.getData().setScale(0.6f);

		game = new PlayTetris(font);
		Gdx.input.setInputProcessor(game);
	}

	@Override
	public void render() { // regularly renders
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.update();
		game.render();
	}

	@Override
	public void dispose() {
		game.dispose();
	}
}