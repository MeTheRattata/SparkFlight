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
	/**
	 * Setter for posX
	 */
	public void setX(float x)
	{
		posX = x;
	}
	/**
	 * Setter for posY
	 */
	public void setY(float y)
	{
		posY = y;
	}
	/**
	 * getMidPointX: a method to get the x coordinate corresponding to the middle of the plane
	 * @return the x position of the midpoint of the Entity
	 */
	public float getMidPointX()
	{
		return posX + width/2;
	}
	/**
	 * getMidPointY: a method to get the y coordinate corresponding to the middle of the plane
	 * @return the y position of the midpoint of the Entity
	 */
	public float getMidPointY()
	{
		return posY + height/2;
	}
	/**
	 * getCenter: a method to get the vector position of the midpoint of the plane
	 * @return the Vector2 (2d) corresponding to the midpoint of the Entity
	 */
	public Vector2 getCenter()
	{
		return new Vector2(getMidPointX(), getMidPointY());
	}
	/**
	 * getHitbox: a method to get the rectangular hitbox of the Entity
	 * @return the Rectangle object corresponding to the hitbox of the Entity
	 */
	public Rectangle getHitbox()
	{
		return new Rectangle(posX, posY, width, height);
	}
	/**
	 * resize: a method to resize an Entity by increasing/decreasing its height and width
	 * @param newWidth the new width of the Entity
	 * @param newHeight the new height of the Entity
	 */
	public void resize(float newWidth, float newHeight)
	{
		width = newWidth;
		height = newHeight;
	}
	/**
	 * Override of Actor's draw method
	 */
	public void draw()
	{
		SparkFlight.batch.draw(SparkFlight.assets.get(name + ".png", Texture.class), posX, posY, width, height);
	}
	/**
	 * Override of Actor's act method
	 */
	public void act()
	{
		//Blank because most default entities do not react, they are there to be reacted to
	}
}