package com.mygdx.sparkflight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class SparkFlight extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle plane;
	private Texture planeImage;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 480);
		
		planeImage = new Texture("plane.png");
		plane = new Rectangle();
		plane.x = 300;
		plane.y = 240;
		plane.height = 100;
		plane.width = 170;
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
		batch.draw(planeImage, plane.x, plane.y, plane.width, plane.height);
		batch.end();
	}
	
	public void dispose()
	{	
		planeImage.dispose();
		batch.dispose();
	}
}
