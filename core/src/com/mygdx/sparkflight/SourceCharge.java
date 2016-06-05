package com.mygdx.sparkflight;

public class SourceCharge extends Entity 
{
	public SourceCharge(float x, float y, double c) 
	{
		super(x, y, c, (c > 0) ? "positive" : "negative");
	}
}