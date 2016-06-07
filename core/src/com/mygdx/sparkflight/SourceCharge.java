package com.mygdx.sparkflight;

public class SourceCharge extends Entity 
{
	/**
	 * Constructor for a SourceCharge object
	 * 
	 * @param x: float representing the SourceCharge's x coordinate
	 * @param y: float representing the SourceCharge's y coordinate
	 * @param c: double representing the charge
	 */
	public SourceCharge(float x, float y, double c) 
	{
		super(x, y, c, (c > 0) ? "positive" : "negative");
	}
}