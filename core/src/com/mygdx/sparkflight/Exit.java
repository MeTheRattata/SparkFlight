package com.mygdx.sparkflight;


public class Exit extends Entity 
{
	public Exit(float x, float y) 
	{
		super(x, y, 0, "exit2");
	}
	
	public void act()
	{
		if(SparkFlight.exit.getHitbox().overlaps(SparkFlight.plane.getHitbox()))
		{
			SparkFlight.level++;
			if(SparkFlight.level == 16)
			{
				SparkFlight.gameState = 6;
			} else
			{
				SparkFlight.changeLevel = true;
			}
		}
	}
}