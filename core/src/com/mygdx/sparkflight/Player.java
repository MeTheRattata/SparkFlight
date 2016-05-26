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
	public Player(double x, double y, double c, String name) 
	{
		super(x, y, c,name);
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
	public void findNewVecolity (ArrayList<Entities> entities)
	{
		ArrayList<Vector2> vectors = new ArrayList<>();
		Vector2 netForce = new Vector2();

		for(int i = 0; i < entities.size();i++)
		{
			//Applies the formula (k * q * Q) / d^2 in order to find the force from each source charge
			double netforcex = (K * fieldStrength * entities.get(i).getCharge())/(Math.pow((entities.get(i).getX() - midPointX), 2));
			double netforcey = (K * fieldStrength * entities.get(i).getCharge())/(Math.pow((entities.get(i).getY() - midPointY), 2));
			vectors.add(new Vector2((float)netforcex,(float)netforcey));
			netForce.add(vectors.get(i));
			
		}
		netForce.set((float)(netForce.x /MASS), (float)(netForce.y/MASS));
		System.out.println(netForce.x + " " + netForce.y);
		velocity.add(netForce);
		findNewX();
		findNewY();
	}
	private void findNewX ()
	{
		
		positionX = (velocity.x + positionX);
		System.out.println("New X: " + positionX);
		this.setX(positionX);
	}
	private void findNewY ()
	{
		positionY = (velocity.y + positionY);
		System.out.println("New Y: " + positionY);
		this.setY(positionY);
	}
}
