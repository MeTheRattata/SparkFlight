package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
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
	private ArrayList<Entities> entities = new ArrayList<Entities>();
	private Player plane;
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 480);
		
		plane = new Player(300,240,100,"plane");
		
		planeImage = new Texture("plane.png");
//		plane = new Rectangle();
//		plane.x = 300;
//		plane.y = 240;
//		plane.height = 100;
//		plane.width = 170;
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
		
		batch.draw(plane.getTexture(), (float)plane.getX(), (float)plane.getY(),(float)plane.getTexture().getWidth(), (float)plane.getTexture().getHeight());
		for(int i = 0; i < entities.size(); i++)
			batch.draw(entities.get(i).getTexture(), (float) entities.get(i).getX(), (float) entities.get(i).getY());
		
		batch.end();
		
		if(Gdx.input.isTouched(Buttons.LEFT)) {
		      Vector2 touchPos = new Vector2();
		      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		      entities.add(new SourceCharge(Gdx.input.getX() - 65, 480 - Gdx.input.getY() - 65, 1));
		   }
		else if(Gdx.input.isTouched(Buttons.RIGHT)) {
		      Vector2 touchPos = new Vector2();
		      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		      entities.add(new SourceCharge(Gdx.input.getX() - 65, 480 - Gdx.input.getY() - 65, -1));
		   }
		//test of player motion
		System.out.println("THIS IS A TEST");
		plane.findNewVecolity(entities);
	}
	
	public void dispose()
	{	
		planeImage.dispose();
		batch.dispose();
	}
}
