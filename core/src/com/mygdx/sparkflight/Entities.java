package com.mygdx.sparkflight;

public class Entities 
{
	double positionX;
	double positionY;
	double charge;
	/**
	 * @param x creates the X position
	 * @param y creates the Y position
	 * @param c creates the objects charge
	 */
	public Entities (double x,double y,double c)
	{
		positionX = x;
		positionY = y;
		charge = c;
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
}
