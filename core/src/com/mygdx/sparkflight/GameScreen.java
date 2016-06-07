package com.mygdx.sparkflight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter
{
	Stage stage;
	SpriteBatch batch;
	final float WORLD_WIDTH = 800, WORLD_HEIGHT = 600;
	Camera camera;
	BitmapFont font; 
	
	static SparkFlight spark;
	
	public GameScreen(SparkFlight flight)
	{
		//super(flight);
		spark = flight;
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		stage = new Stage(viewport, batch);
		font = new BitmapFont();
		
		new InputManager(this);
		
		stage.addActor(new Player(150,150,0.0000000009,"plane"));
		//stage.addActor(new Exit(450, 450, 0, "exit"));
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//stage.act(deltaTime);
		stage.draw();
	}
}