package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SparkFlight extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static ArrayList<SourceCharge> entities = new ArrayList<SourceCharge>();
	public OrthographicCamera camera;
	private Player plane;
	private int width;
	private int height;
	public static AssetManager assets;
	public Exit exit;
	
	@Override
	public void create () 
	{
		assets = new AssetManager();
		assets.load("exit.png", Texture.class);
		assets.load("plane.png", Texture.class);
		assets.load("positive.png", Texture.class);
		assets.load("negative.png", Texture.class);
		assets.load("exit.png", Texture.class);
		assets.finishLoading();
		
		width = 800;
		height = 600;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		exit = new Exit(450, 450, 0, "exit");
		plane = new Player(150,150,0.0000000009,"plane");
		

		Gdx.input.setInputProcessor(new InputAdapter () {
			   public boolean touchUp (int x, int y, int pointer, int button) {
				   if(button == Buttons.LEFT) {
					      entities.add(new SourceCharge(Gdx.input.getX() - 65, height - Gdx.input.getY() - 65, 1));
					      return true;
					}
					else if(button == Buttons.RIGHT) {
					      entities.add(new SourceCharge(Gdx.input.getX() - 65, height - Gdx.input.getY() - 65, -1));
					      return true;
					}
					return false;
			   }
			});
	}

	@Override
	public void render () 
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		plane.draw();
//		System.out.println("Plane position x: " + plane.getMidPointX());
//		System.out.println("Plane position y: " + plane.getMidPointY());
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).draw();
			/*if(entities.get(i).charge > 0)
				batch.draw(assets.get("positive.png", Texture.class), (float) entities.get(i).getX(), 
				(float) entities.get(i).getY(), (float) entities.get(i).getWidth(), (float) entities.get(i).getHeight());
			else if(entities.get(i).charge < 0)
				batch.draw(assets.get("negative.png", Texture.class), (float) entities.get(i).getX(), 
				(float) entities.get(i).getY(), (float) entities.get(i).getWidth(), (float) entities.get(i).getHeight());*/
		}
		exit.draw();

		batch.end();
		
		//test of player motion
		exit.exitContainsPlayer(plane.getCenter());
		plane.findNewVelocity(entities);
		//exit.exitContainsPlayer(plane.getMidPointX(), plane.getMidPointY());
		
	}
	
	public void dispose()
	{	
		batch.dispose();
		assets.dispose();
	}
}
