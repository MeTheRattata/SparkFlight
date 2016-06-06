package com.mygdx.sparkflight;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SparkFlight extends ApplicationAdapter {
	public static SpriteBatch batch;
	//ArrayList of ALL Actors for drawing and acting purposes
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public OrthographicCamera camera;
	public static Player plane;
	private int width;
	private int height;
	public static AssetManager assets;
	public static Exit exit;
	public static int level = 1;
	public static boolean changeLevel = true;
	public static boolean reloadLevel = false;
	
	@Override
	public void create() 
	{
		assets = new AssetManager();
		assets.load("plane.png", Texture.class);
		assets.load("positive.png", Texture.class);
		assets.load("negative.png", Texture.class);
		assets.load("exit.png", Texture.class);
		assets.load("wall.png", Texture.class);
		assets.finishLoading();
		
		width = 800;
		height = 600;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		Gdx.input.setInputProcessor(new InputAdapter () {
			   public boolean touchUp (int x, int y, int pointer, int button) {
				   if(button == Buttons.LEFT) {
					   SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - 65, height - Gdx.input.getY() - 65, 1);
					   entities.add(newCharge);
					   Player.charges.add(newCharge);
					   return true;
					}
					else if(button == Buttons.RIGHT) {
						SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - 65, height - Gdx.input.getY() - 65, -1);
						entities.add(newCharge);
						Player.charges.add(newCharge);
						return true;
					}
					return false;
			   }
			});
	}

	@Override
	public void render() 
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
//		System.out.println("Plane position x: " + plane.getMidPointX());
//		System.out.println("Plane position y: " + plane.getMidPointY());
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).act();
			entities.get(i).draw();
		}
		
		if(changeLevel)
		{
			loadLevel(level);
		}
		
		if(reloadLevel)
		{
			loadLevel(level);
		}
		
		batch.end();
	}
	
	public void dispose()
	{	
		batch.dispose();
		assets.dispose();
	}
	
	public void loadLevel(int level)
	{
		changeLevel = false;
		reloadLevel = false;
		entities.clear();
		
		FileHandle file = Gdx.files.internal("level" + level);
		StringTokenizer tokens = new StringTokenizer(file.readString());
		
		while(tokens.hasMoreTokens())
		{
			String type = tokens.nextToken();
			if(type.equals("Player"))
			{
				plane = new Player(Float.parseFloat(tokens.nextToken()),
									Float.parseFloat(tokens.nextToken()),
									Float.parseFloat(tokens.nextToken()),
									tokens.nextToken());
				entities.add(plane);
			} else if(type.equals("Exit"))
			{
				exit = new Exit(Float.parseFloat(tokens.nextToken()),
								Float.parseFloat(tokens.nextToken()));
				entities.add(exit);
			} else if(type.equals("Wall"))
			{
				entities.add(new Wall(Float.parseFloat(tokens.nextToken()),
										Float.parseFloat(tokens.nextToken())));
			}
		}
	}
}
