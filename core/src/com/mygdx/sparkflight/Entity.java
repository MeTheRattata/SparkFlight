package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;

public class Entity 
{
	float positionX;
	float positionY;
	float midPointX;
	float midPointY;
	double charge;
	Texture image;
	
	/**
	 * Constructor for an Entity
	 * 
	 * @param x creates the X position
	 * @param y creates the Y position
	 * @param c creates the objects charge
	 * @param name used to create the object's texture
	 */
	public Entity (float x, float y, double c, String name)
	{
		positionX = x;
		positionY = y;
		image = new Texture(name + ".png");
		midPointX = x + image.getWidth() / 2;
		midPointY = y + image.getHeight() / 2;
		charge = c;
	}
	/**
	 * @return X
	 */
	public float getX ()
	{
		return positionX;
	}
	/**
	 * @return Y
	 */
	public float getY ()
	{
		return positionY;
	}
	/**
	 * @return charge
	 */
	public double getCharge ()
	{
		return charge;
	}
	public void updateImage(String newName)
	{
		image = new Texture(newName + ".png");
		System.out.println(newName);
	}
	/**
	 * @return texture
	 */
	public Texture getTexture()
	{
		return image;
	}
	public void setX(float x)
	{
		midPointX = x + image.getWidth() / 2;
		positionX = x;
	}
	public void setY(float y)
	{
		midPointY = y + image.getHeight() / 2;
		positionY = y;
	}
	
	public float getWidth()
	{
		return image.getWidth();
	}
	
	public float getHeight()
	{
		return image.getHeight();
	}
}