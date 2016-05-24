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
	Vector2 velocity = new Vector2(0,0);
	final double MASS = 0.045;
	final long K = 9_000_000_000L;
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
	public double getMass ()
	{
		return MASS;
	}
	/**
	 * @param entities a arraylist of 
	 */
	public void find (ArrayList<Entities> entities)
	{
		ArrayList<Vector2> vectors = new ArrayList<>();
		Vector2 netForce = new Vector2();

		for(int i = 0; i < entities.size();i++)
		{
			double netforcex = ()
//			vectors.add(new Vector2((float) ((entities.get(i).getX() - midPointX)),(float) (entities.get(i).getY() - midPointY)));
//			netForce.add(vectors.get(i));
		}
		velocity.add(netForce);
		
	}
}
