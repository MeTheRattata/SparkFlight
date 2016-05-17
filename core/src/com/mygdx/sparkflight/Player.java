package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Player extends Entities 
{
	double positionX;
	double positionY;
	//Cordinates for the center of the plane. 
	double midPointX;
	double midPointY;
	double fieldStrength;
	double velocity = 0;
	final double WEIGHT = 0.045;
	public Player(double x, double y, double c) 
	{
		super(x, y, c,"plane");
		positionX = x;
		positionY = y;
		fieldStrength = c;
		//Size of plane 170 by 100
		midPointX = x + 85;
		midPointY = y - 50;
	}
	public double getWeight ()
	{
		return WEIGHT;
	}
	/**
	 * @param entities a arraylist of 
	 */
	public void findNetForce (ArrayList<Entities> entities)
	{
		Vector2 v = new Vector2();
		for(int i = 0; i < entities.size();i++)
		{
			
		}
	}
}
