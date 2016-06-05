package com.mygdx.sparkflight;

public class Exit extends Entity 
{
	public Exit(float x, float y) 
	{
		super(x, y, 0, "exit");
	}
	
	public void act()
	{
		if(SparkFlight.plane.getHitbox().contains(SparkFlight.exit.getCenter()))
		{
			System.out.println("Player has completed the level.");
			SparkFlight.level++;
			System.out.println(SparkFlight.level);
			SparkFlight.changeLevel = true;
		}
	}
}