package com.mygdx.sparkflight;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SparkFlight extends ApplicationAdapter
{
	public static SpriteBatch batch;
	//ArrayList of ALL Actors for drawing and acting purposes
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public OrthographicCamera camera;
	public static Player plane;
	public static int width;
	public static int height;
	public static AssetManager assets;
	public static Exit exit;
	public static Wall wall;
	public static int level = 15;
	public static boolean changeLevel = true;
	public static boolean reloadLevel = false;
	public static Rectangle howToPlay;
	public static Rectangle playGame;
	public static int gameState = 1;//1=mainMenu, 2=game,
									//3=nextLevel, 4=retryLevel
									//5=howToPlay, 6=gameOver
	
	@Override
	public void create() 
	{
		//AssetManager: loads all game elements to reduce resources used as a result of each
		//Entity class having a separate Texture object
		assets = new AssetManager();
		assets.load("ball2.png", Texture.class);
		assets.load("positive.png", Texture.class);
		assets.load("negative.png", Texture.class);
		assets.load("exit2.png", Texture.class);
		assets.load("wall2.png", Texture.class);
		assets.load("nextLevel.png", Texture.class);
		assets.load("tryAgain.png", Texture.class);
		assets.load("background2.png", Texture.class);
		assets.load("title.png", Texture.class);
		assets.load("playGame.png", Texture.class);
		assets.load("howToPlay.png", Texture.class);
		assets.load("instructions.png", Texture.class);
		assets.load("names.png", Texture.class);
		assets.finishLoading();
		
		width = 800;
		height = 600;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		exit = new Exit(0, 0);
		plane = new Player(0,0,0);
		wall = new Wall(0, 0);
		howToPlay = new Rectangle(width - 600, height - 360, 400, 90);
		playGame = new Rectangle(width - 600, height - 270, 400, 90);
		render();
	}

	/**
	 * Determines what should be drawn
	 */
	public void render() 
	{
		//Determines if a change needs to be made to the game, such as going into the 
		//main menu or reloading a level.
		switch (gameState)
		{
			case 1:
				mainMenu();
				break;
			case 2:
				game();
				break;
			case 3:
				nextLevel();
				break;
			case 4:
				retryLevel();
				break;
			case 5:
				howToPlay();
				break;
		}
	}
	
	/**
	 * Loads a level from a text file
	 * @param level: number of the level to load
	 */
	public void loadLevel(int level)
	{
		changeLevel = false;
		reloadLevel = false;
		gameState = 2;
		entities.clear();
		
		FileHandle file = Gdx.files.internal("level" + level);
		StringTokenizer tokens = new StringTokenizer(file.readString());
		
		entities.add(new Entity(0, 0, 0, "background2"));
		
		//Loads in game objects as defined line by line in the level file selected
		while(tokens.hasMoreTokens())
		{
			String type = tokens.nextToken();
			if(type.equals("Player"))
			{
				plane = new Player(Float.parseFloat(tokens.nextToken()),
									Float.parseFloat(tokens.nextToken()),
									Float.parseFloat(tokens.nextToken()));
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
			} else if(type.equals("Source"))
			{
				SourceCharge charge = new SourceCharge(Float.parseFloat(tokens.nextToken()),
						Float.parseFloat(tokens.nextToken()),
						Double.parseDouble(tokens.nextToken()));
				entities.add(charge);
				Player.charges.add(charge);
			}
		}
	}
	
	public void mainMenu()
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(assets.get("title.png", Texture.class), width - 600, height - 180, 400, 90);
		batch.draw(assets.get("playGame.png", Texture.class), width - 600, height - 270, 400, 90);
		batch.draw(assets.get("howToPlay.png", Texture.class), width - 600, height - 360, 400, 90);
		batch.draw(assets.get("names.png", Texture.class), width - 600, height - 540, 400, 180);
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			   public boolean touchUp (int x, int y, int pointer, int button)
			   {
				   if(button == Buttons.LEFT)
				   {
					   Vector2 click = new Vector2(x, height - y);
					   if(playGame.contains(click))
					   {
						   loadLevel(level);
						   return true;
					   } else if(howToPlay.contains(click))
					   {
						   gameState = 5;
						   return true;
					   }
					}
					return false;
			   }
			
		});
		
		
		batch.end();
	}
	
	/**
	 * Main game loop
	 */
	public void game()
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//Only calls Player's Act method because Player is the only Entity which acts
		plane.act();
		exit.act();
		//Draws all Entity objects in the ArrayList entities
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).draw();
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			   public boolean touchUp (int x, int y, int pointer, int button)
			   {
				   if(button == Buttons.LEFT)
				   {
					   SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - assets.get("positive.png", Texture.class).getWidth() / 2, height - Gdx.input.getY() - assets.get("positive.png", Texture.class).getHeight() / 2, 1);
					   entities.add(newCharge);
					   Player.charges.add(newCharge);
					   return true;
					}
					else if(button == Buttons.RIGHT)
					{
						SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - assets.get("negative.png", Texture.class).getWidth() / 2, height - Gdx.input.getY() - assets.get("negative.png", Texture.class).getHeight() / 2, -1);
						entities.add(newCharge);
						Player.charges.add(newCharge);
						return true;
					}
					return false;
			   }
		});
		
		if(changeLevel)
		{
			 gameState = 3;
		}
		
		if(reloadLevel)
		{
			gameState = 4;
		}
		
		batch.end();
	}
	
	public void nextLevel()
	{
		changeLevel = false;
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).draw();
		}
		batch.draw(assets.get("nextLevel.png", Texture.class), width / 2, height / 2, 128, 96);
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			public boolean keyDown (int key)
			{
				if(key == Input.Keys.SPACE)
				{
					loadLevel(level);
					return true;
				}
				return false;
			}
		});
		
		batch.end();
	}
	
	public void retryLevel()
	{
		changeLevel = false;
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).draw();
		}
		batch.draw(assets.get("tryAgain.png", Texture.class), width / 2, height / 2, 128, 96);
		
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			   public boolean keyDown (int key)
			   {
				   if(key == Input.Keys.SPACE)
				   {
						   loadLevel(level);
						   return true;
				   }
					return false;
			   }
		});
		batch.end();
	}
	
	public void howToPlay()
	{
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(assets.get("instructions.png", Texture.class), 0, 0, 800, 600);
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			public boolean keyDown (int key)
			{
				if(key == Input.Keys.SPACE)
				{
					gameState = 1;
					return true;
				}
				return false;
			}
		});
		
		batch.end();
	}
	
	public void gameOver()
	{
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(assets.get("gameOver.png", Texture.class), 0, 0, 800, 600);
		
		Gdx.input.setInputProcessor(new InputAdapter ()
		{
			public boolean keyDown (int key)
			{
				if(key == Input.Keys.SPACE)
				{
					gameState = 1;
					return true;
				}
				return false;
			}
		});
		
		batch.end();
	}

	/**
	 * Disposes of drawing elements after the game is shut down
	 */
	public void dispose()
	{	
		batch.dispose();
		assets.dispose();
	}
}
