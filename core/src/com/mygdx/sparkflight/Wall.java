package com.mygdx.sparkflight;

import com.badlogic.gdx.math.Rectangle;

public class Wall extends Entity
{
	Rectangle hitBox;
	public Wall(float x, float y, double c, String name) 
	{
		super(x, y, c, name);
		hitBox = new Rectangle(x, y, image.getWidth(), image.getHeight());
	}
	/**
	 * 
	 * @param HitBox from player to see if player colides with a wall.
	 */
	public void wallContainsPlayer(Rectangle box)
	{
		if(box.contains(hitBox))
		{
			System.out.println("Player Loses");
		}
	}
}
