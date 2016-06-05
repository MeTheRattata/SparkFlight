package com.mygdx.sparkflight;

import com.badlogic.gdx.math.Vector2;

public class Exit extends Entity 
{
	private Vector2 center;
	public Exit(float x, float y) 
	{
		super(x, y, 0, "exit");
		center = new Vector2(x + width / 2, y + height / 2);
	}
	
	public void act()
	{
		if(SparkFlight.plane.getHitbox().contains(center))
		{
			System.out.println("Player has reached the exit!");
		}
	}
}