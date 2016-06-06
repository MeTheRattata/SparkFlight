package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
	public void setX(float x)
	{
		posX = x;
	}
	public void setY(float y)
	{
		posY = y;
	}
	public float getMidPointX()
	{
		return posX + width/2;
	}
	public float getMidPointY()
	{
		return posY + height/2;
	}
	public Vector2 getCenter()
	{
		return new Vector2(getMidPointX(), getMidPointY());
	}
	public Rectangle getHitbox()
	{
		return new Rectangle(posX, posY, width, height);
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
	public void act()
	{
		//Blank because most default entities do not react, they are there to be reacted to
	}
}