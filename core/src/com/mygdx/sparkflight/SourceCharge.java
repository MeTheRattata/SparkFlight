package com.mygdx.sparkflight;

public class SourceCharge extends Entities 
{
	double positionX;
	double positionY;
	double charge;
	
	public SourceCharge(double x, double y, double c) 
	{
		super(x, y, c, c > 0 ? "positive" : "negative");
		positionX = x;
		positionY = y;
		charge = c;
	}
}