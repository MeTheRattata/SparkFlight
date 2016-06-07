package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Player extends Entity 
{
	Vector2 velocity = new Vector2(0,0);
	final float MASS = (float) 0.045;
	public static ArrayList<SourceCharge> charges;
	
	/**
	 * Constructor for a Player objecct
	 * @param x: float representing the Player's  starting x position
	 * @param y: float representing the Player's starting y position
	 * @param c: double representing the charge
	 */
	public Player(float x, float y, double c) 
	{
		super(x, y, c, "plane");
		//Size of plane 170 by 90
		charges = new ArrayList<SourceCharge>();
	}
	/**
	 * Getter for Player's mass
	 * @return MASS
	 */
	public float getMass ()
	{
		return MASS;
	}
	/**
	 * If the Player's next horizontal movement is within the game boundaries, adds velocity to the Player's 
	 * current x position. If the player's next movement is not within the game boundaries, moves 
	 * the Player to the edge of the game's corresponding x boundary and sets its velocity to 0.
	 */
	private void findNewX ()
	{
		posX = (velocity.x + posX);
		if(posX < 0)
		{
			posX = 0;
			velocity.set(0, velocity.y);
		}
		else if(posX > SparkFlight.width - width)
		{
			posX = SparkFlight.width - width;
			velocity.set(0, velocity.y);
		}
		this.setX(posX);
	}
	/**
	 * If the Player's next vertical movement is within the game boundaries, adds velocity to the Player's 
	 * current y position. If the player's next vertical movement is not within the game boundaries, moves 
	 * the Player to the edge of the game's corresponding y boundary and sets its velocity to 0.
	 */
	private void findNewY ()
	{
		posY = (velocity.y + posY);
		if(posY < 0)
		{
			posY = 0;
			velocity.set(velocity.x, 0);
		}
		else if(posY > SparkFlight.height - height)
		{
			posY = SparkFlight.height - height;
			velocity.set(velocity.x, 0);
		}
		this.setY(posY);
	}
	
	/**
	 * Act method for Player:
	 * calculates force on Player from all SourceCharge objects and adjusts player's position
	 */
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
			//scale by k * q * Q / d ^ 2
			partial = temp;
			partial.scl((float)-(charges.get(i).charge * charge) / d2);
			force.add(partial);
		}
		
		velocity.add(force.x / MASS, force.y / MASS);
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