package com.mygdx.sparkflight;

import com.badlogic.gdx.math.Rectangle;

public class Exit extends Entity 
{
	private Rectangle hitBox;
	public Exit(float x, float y, double c, String name) 
	{
		super(x, y, c, name);
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public void act()
	{
		if(hitBox.contains(SparkFlight.plane.getCenter()))
		{
			System.out.println("Player has reached the exit!");
		}
	}
}