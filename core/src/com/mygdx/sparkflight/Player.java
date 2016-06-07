package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Player extends Entity 
{
	Vector2 velocity = new Vector2(0,0);
	final float MASS = (float) 0.045;
	public static ArrayList<SourceCharge> charges;
	
	public Player(float x, float y, double c, String name) 
	{
		super(x, y, c,name);
		//Size of plane 170 by 90
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
	}
	
	public void act()
	{
		Vector2 force = new Vector2();
		Vector2 temp = new Vector2();
		Vector2 partial = new Vector2();
		for(int i = 0; i < charges.size();i++)
		{
			temp.x = charges.get(i).getMidPointX() - getMidPointX();
			temp.y = charges.get(i).getMidPointY() - getMidPointY();
			float d2 = temp.len2(); // find square distance
			temp.x /= temp.len();
			temp.y /= temp.len();
			System.out.println("Force before: " + temp);
			//scale by k * q * Q / d ^ 2
			partial = temp;
			partial.scl((float)-(charges.get(i).charge * charge) / d2);
			System.out.println("Part to be added: " + partial);
			force.add(partial);
			System.out.println("Force after: " + force);
		}
		
		velocity.add(force.x / MASS, force.y / MASS);
//		System.out.println("X velocity: " + velocity.x + "\nY velocity: " + velocity.y);
		findNewX();
		findNewY();
		//checks for collision with wall and reloads the level if it is
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
}