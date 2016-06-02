package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity 
{
	//Cordinates for the center of the plane. 
	float midPointX;
	float midPointY;
	Vector2 velocity = new Vector2(0,0);
	final float MASS = (float) 0.045;
	final long K = 9_000_000_000L;
	Rectangle hitBox;
	
	public Player(float x, float y, double c, String name) 
	{
		super(x, y, c,name);
		//Size of plane 170 by 100
		midPointX = x + image.getWidth()/2;
		midPointY = y + image.getHeight()/2;
		hitBox = new Rectangle(x, y, image.getWidth(), image.getHeight());
	}
	public float getMass ()
	{
		return MASS;
	}
	/**
	 * @param entities a arraylist of 
	 */
	public void findNewVelocity (ArrayList<Entity> entities)
	{
		Vector2 displacement = new Vector2();
		for(int i = 0; i < entities.size();i++)
		{
			displacement.x = entities.get(i).positionX - positionX;
			displacement.y = entities.get(i).positionY - positionY;
			float d2 = displacement.len2(); // find square distance
			displacement.nor() // make the vector length 1
			//scale by k * q * Q / d ^ 2
			.scl((float)-(K * entities.get(i).charge * charge) / d2);
		}
		
		
		velocity.add(displacement.x / MASS, displacement.y / MASS);
		System.out.println("X velocity: " + velocity.x + "\nY velocity: " + velocity.y);
		findNewX();
		findNewY();
	}
	private void findNewX ()
	{
		
		positionX = (velocity.x + positionX);
		if(positionX < 0)
			positionX = 0;
		else if(positionX > 600)
			positionX = 600;
		this.setX(positionX);
		
	}
	private void findNewY ()
	{
		positionY = (velocity.y + positionY);
		if(positionY < 0)
			positionY = 0;
		else if(positionY > 480)
			positionY = 480;
		this.setY(positionY);
	}
	
}
