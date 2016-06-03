package com.mygdx.sparkflight;

import java.awt.Rectangle;

public class Exit extends Entity 
{
	private Rectangle hitBox;
	public Exit(float x, float y, double c, String name) 
	{
		super(x, y, c, name);
		hitBox = new Rectangle(image.getWidth(),image.getWidth());
	}
	public void exitContainsPlayer (float x,float y)
	{
		if(hitBox.contains(x, y))
		{
			System.out.println("Player has reached the exit!");
		}
	}
}
