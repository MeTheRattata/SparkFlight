package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;

public class Entity 
{
	double positionX;
	double positionY;
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
	public Entity (double x, double y, double c, String name)
	{
		positionX = x;
		positionY = y;
		charge = c;
		image = new Texture(name + ".png");
	}
	/**
	 * @return X
	 */
	public double getX ()
	{
		return positionX;
	}
	/**
	 * @return Y
	 */
	public double getY ()
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
	public double getMidPointX ()
	{
		return positionX + (image.getWidth()/2);
	}
	/**
	 * @return Y
	 */
	public double getMidPointY ()
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
	public void setX(double x)
	{
		positionX = x;
	}
	public void setY(double y)
	{
		positionY = y;
	}
	
	public double getWidth()
	{
		return image.getWidth();
	}
	
	public double getHeight()
	{
		return image.getHeight();
	}
}