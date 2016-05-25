package com.mygdx.sparkflight;

import com.badlogic.gdx.graphics.Texture;

public class Entities 
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
	public Entities (double x, double y, double c, String name)
	{
		positionX = x;
		positionY = y;
		charge = c;
		image = new Texture(name + ".png");
		System.out.println(c);
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
}
