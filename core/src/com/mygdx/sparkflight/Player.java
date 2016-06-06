package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity 
{
	Vector2 velocity = new Vector2(0,0);
	final float MASS = (float) 0.045;
	final long K = 9_000_000_000L;
	public static ArrayList<SourceCharge> charges;
	
	public Player(float x, float y, double c, String name) 
	{
		super(x, y, c,name);
		//Size of plane 170 by 100
		charges = new ArrayList<SourceCharge>();
	}
	public float getMass ()
	{
		return MASS;
	}
	
	private void findNewX ()
	{
		posX = (velocity.x + posX);
		if(posX < 0)
		{
			posX = 0;
			velocity.set(0, velocity.y);
		}
		else if(posX > 600)
		{
			posX = 600;
			velocity.set(0, velocity.y);
		}
		this.setX(posX);
		getHitbox().setX(posX);		
	}
	private void findNewY ()
	{
		posY = (velocity.y + posY);
		if(posY < 0)
		{
			posY = 0;
			velocity.set(velocity.x, 0);
		}
		else if(posY > 480)
		{
			posY = 480;
			velocity.set(velocity.x, 0);
		}
		this.setY(posY);
		getHitbox().setY(posY);
	}
	
	public void act()
	{
		Vector2 displacement = new Vector2();
		for(int i = 0; i < charges.size();i++)
		{
			displacement.x = charges.get(i).posX - posX;
			displacement.y = charges.get(i).posY - posY;
			float d2 = displacement.len2(); // find square distance
			displacement.nor() // make the vector length 1
			//scale by k * q * Q / d ^ 2
			.scl((float)-(K * charges.get(i).charge * charge) / d2);
		}
		
		velocity.add(displacement.x / MASS, displacement.y / MASS);
//		System.out.println("X velocity: " + velocity.x + "\nY velocity: " + velocity.y);
		float oldX = posX;
		float oldY = posY;
		findNewX();
		findNewY();
		for(int x = 0; x < SparkFlight.entities.size(); x++)
		{
			if(SparkFlight.entities.get(x) instanceof Wall)
			{
				if(SparkFlight.plane.getHitbox().overlaps(SparkFlight.entities.get(x).getHitbox()))
				{
					SparkFlight.reloadLevel = true;
				}
			}
		}
	}
//	private void findNewX ()
//	{
//		
//		positionX = (velocity.x + positionX);
//		if(positionX < 0)
//			positionX = 0;
//		else if(positionX > 600)
//			positionX = 600;
//		this.setX(positionX);
//		
//	}
//	private void findNewY ()
//	{
//		positionY = (velocity.y + positionY);
//		if(positionY < 0)
//			positionY = 0;
//		else if(positionY > 480)
//			positionY = 480;
//		this.setY(positionY);
//	}
	 public Rectangle getHitBox()
	 {
		 return new Rectangle(posX, posY, width, height);
	 }
}
