package com.mygdx.sparkflight;

public class Exit extends Entity 
{
	public Exit(float x, float y) 
	{
		super(x, y, 0, "exit2");
	}
	
	public void act()
	{
		if(SparkFlight.plane.getHitbox().overlaps(SparkFlight.exit.getHitbox()))
		{
			SparkFlight.level++;
			SparkFlight.changeLevel = true;
		}
	}
}