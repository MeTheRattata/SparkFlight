package com.mygdx.sparkflight;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Exit extends Entity 
{
	private Rectangle hitBox;
	public Exit(float x, float y, double c, String name) 
	{
		super(x, y, c, name);
		hitBox = new Rectangle(x, y, width, height);
	}
	public void exitContainsPlayer (Vector2 center)
	{
		if(hitBox.contains(center))
		{
			System.out.println("Player has reached the exit!");
		}
	}
}
