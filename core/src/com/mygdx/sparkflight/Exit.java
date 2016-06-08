package com.mygdx.sparkflight;

import com.badlogic.gdx.math.Intersector;

public class Exit extends Entity 
{
	public Exit(float x, float y) 
	{
		super(x, y, 0, "exit2");
	}
	
	public void act()
	{
		if(Intersector.overlaps(Player.hitBox, SparkFlight.exit.getHitbox()));
		{
			System.out.println(Player.hitBox);
			System.out.println(SparkFlight.exit.getHitbox());
			SparkFlight.level++;
			if(SparkFlight.level == 16)
			{
				SparkFlight.gameState = 6;
			}
			SparkFlight.changeLevel = true;
		}
	}
}