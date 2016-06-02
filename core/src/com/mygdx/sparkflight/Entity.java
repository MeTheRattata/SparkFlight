package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;

public class Entity 
{
	float positionX;
	float positionY;
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
		charge = c;
		image = new Texture(name + ".png");
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
	public float getMidPointX ()
	{
		return positionX + (image.getWidth()/2);
	}
	/**
	 * @return Y
	 */
	public float getMidPointY ()
	{
		return positionY + (image.getHeight()/2);
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
		positionX = x;
	}
	public void setY(float y)
	{
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