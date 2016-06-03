package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor
{
	float posX;
	float posY;
	float width;
	float height;
	double charge;
	String name;
	
	/**
	 * Constructor for an Entity
	 * 
	 * @param x creates the X pos
	 * @param y creates the Y pos
	 * @param c creates the objects charge
	 * @param name used to create the object's texture
	 */
	public Entity (float x, float y, double c, String n)
	{
		posX = x;
		posY = y;
		charge = c;
		name = n;
		width = SparkFlight.assets.get(name + ".png", Texture.class).getWidth();
		height = SparkFlight.assets.get(name + ".png", Texture.class).getHeight();
	}
	public float getMidPointX()
	{
		return posX + width/2;
	}
	/**
	 * @return Y
	 */
	public float getMidPointY()
	{
		return posY + height/2;
	}
	public void resize(float newWidth, float newHeight)
	{
		width = newWidth;
		height = newHeight;
	}
	
	public void draw()
	{
		SparkFlight.batch.draw(SparkFlight.assets.get(name + ".png", Texture.class), posX, posY, width, height);
	}
}