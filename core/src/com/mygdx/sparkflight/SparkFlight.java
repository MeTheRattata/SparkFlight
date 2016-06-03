package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SparkFlight extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
//	private Rectangle plane;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Player plane;
	private int width;
	private int height;
	private Exit exit;
	@Override
	public void create () 
	{
		width = 800;
		height = 600;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		
		plane = new Player(150,150,0.0000000009,"plane");
		exit = new Exit (0,0,0,"plane");
		Gdx.input.setInputProcessor(new InputAdapter () 
		{
			   public boolean touchUp (int x, int y, int pointer, int button) 
			   {
				   if(button == Buttons.LEFT) 
				   {
					      Vector2 touchPos = new Vector2();
					      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
					      System.out.println("Mouse x: " + Gdx.input.getX());
					      System.out.println("Mouse y: " + Gdx.input.getY());
					      entities.add(new SourceCharge(Gdx.input.getX() - 65, height - Gdx.input.getY() - 65, 1));
					      return true;
					}
					else if(button == Buttons.RIGHT) 
					{
					      Vector2 touchPos = new Vector2();
					      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
					      System.out.println("Mouse x: " + Gdx.input.getX());
					      System.out.println("Mouse y: " + Gdx.input.getY());
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
		batch.draw(plane.getTexture(), plane.getX(), plane.getY(), plane.getWidth(), plane.getHeight());
//		System.out.println("Plane position x: " + plane.getMidPointX());
//		System.out.println("Plane position y: " + plane.getMidPointY());
		for(int i = 0; i < entities.size(); i++)
			batch.draw(entities.get(i).getTexture(), (float) entities.get(i).getX(), (float) entities.get(i).getY(), (float) entities.get(i).getWidth(), (float) entities.get(i).getHeight());
		
		batch.draw(exit.getTexture(), exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
		batch.end();
		
		//test of player motion
		plane.findNewVelocity(entities);
		exit.exitContainsPlayer(plane.getMidPointX(), plane.getMidPointY());
		
	}
	
	public void dispose()
	{	
		batch.dispose();
	}
}
