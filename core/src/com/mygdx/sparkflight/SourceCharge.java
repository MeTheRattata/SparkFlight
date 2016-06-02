package com.mygdx.sparkflight;

public class SourceCharge extends Entity 
{
	double positionX;
	double positionY;
	double charge;
	
	public SourceCharge(float x, float y, double c) 
	{
		super(x, y, c, "positive");
		positionX = x;
		positionY = y;
		charge = c;
		if(c < 0)
			updateImage("negative");
	}
}