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
	Rectangle dog;
	private Texture dogImage;
	private Music spinningDog;
	private Vector3 touchPos;
	private World world;
	private Body body;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 480);
		
		dogImage = new Texture("dog.png");
		dog = new Rectangle();
		dog.x = 300;
		dog.y = 240;
		dog.height = 128;
		dog.width = 128;
		touchPos = new Vector3();
		spinningDog = Gdx.audio.newMusic(Gdx.files.internal("Spinning dog gif (10 minutes).mp3"));
		
		spinningDog.setLooping(true);
		spinningDog.play();
		
		//Make a new world with a gravity constant of 9.8 N
		world = new World(new Vector2(0, -98f), true);
		
		//Make a new body definition, make it a dynamic body
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        
        //Set the position of the body definition to the dog's position
        bodyDef.position.set(dog.getX(), dog.getY());
        
        //Create a new physics body
        body = world.createBody(bodyDef);
        
        //Create a new shape, set its dimensions to the dimensions of the dog
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dog.getWidth()/2, dog.getHeight()/2);
        
        //Create a fixture definition (something that knows where the collision box is, 
        //and the other physics properties of the dog, like density, mass, weight, etc)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        
        //Create a fixture with this definition
        Fixture fixture = body.createFixture(fixtureDef);
        
        shape.dispose();
	}

	@Override
	public void render () 
	{
		//Calcumalate physics things
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		
		//Set the dog's position to that of the physics body
		dog.setPosition(body.getPosition().x, body.getPosition().y);
		
		Gdx.gl.glClearColor(1 , 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		//Makes batch only show what is inside the camera's FOV
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(dogImage, dog.x, dog.y, dog.width, dog.height);
		batch.end();
		
		//Change dog's position to the position of any clicks within the window
		if(Gdx.input.isTouched()) 
		{
		      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		      camera.unproject(touchPos);
		      dog.x = touchPos.x - dog.height / 2;
		      dog.y = touchPos.y - dog.height / 2;
		}
		//Receive input from keyboard keys, change dog position
		if(Gdx.input.isKeyPressed(Keys.LEFT)) dog.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) dog.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.DOWN)) dog.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.UP)) dog.y += 200 * Gdx.graphics.getDeltaTime();
		
		if(dog.x < 0) dog.x = 0;
		if(dog.x > 600 - dog.width) dog.x = 600 - dog.width;
		if(dog.y < 0) dog.y = 0;
		if(dog.y > 480 - dog.height) dog.y = 480 - dog.height;
	}
	
	public void dispose()
	{	
		dogImage.dispose();
		spinningDog.dispose();
		batch.dispose();
	}
}
