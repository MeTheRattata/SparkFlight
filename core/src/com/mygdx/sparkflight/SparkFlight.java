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
	private Texture planeImage;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Player plane;
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 480);
		
		plane = new Player(150,150,0.00000000009,"positive");
		
		planeImage = new Texture("plane.png");
		
		Gdx.input.setInputProcessor(new InputAdapter () {
			   public boolean touchUp (int x, int y, int pointer, int button) {
				   if(button == Buttons.LEFT) {
					      Vector2 touchPos = new Vector2();
					      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
					      entities.add(new SourceCharge(Gdx.input.getX() - 33, 480 - Gdx.input.getY() - 33, 1));
					      return true;
					}
					else if(button == Buttons.RIGHT) {
					      Vector2 touchPos = new Vector2();
					      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
					      entities.add(new SourceCharge(Gdx.input.getX() - 33, 480 - Gdx.input.getY() - 33, -1));
					      return true;
					}
					return false;
			   }
			});
	}

	@Override
	public void render () 
	{
		Gdx.gl.glClearColor(1 , 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(plane.getTexture(), (float)plane.getX(), (float)plane.getY(),(float)plane.getWidth(), (float)plane.getHeight());
		System.out.println(plane.getX());
		System.out.println(plane.getY());
		for(int i = 0; i < entities.size(); i++)
			batch.draw(entities.get(i).getTexture(), (float) entities.get(i).getX(), (float) entities.get(i).getY(), (float) entities.get(i).getWidth(), (float) entities.get(i).getHeight());
		
		batch.end();
		
		//test of player motion
		plane.findNewVelocity(entities);

	}
	
	public void dispose()
	{	
		planeImage.dispose();
		batch.dispose();
	}
}
