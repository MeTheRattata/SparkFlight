package com.mygdx.sparkflight;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity 
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
	Rectangle hitBox;
	
	public Player(double x, double y, double c, String name) 
	{
		super(x, y, c,name);
		positionX = x;
		positionY = y;
		fieldStrength = c;
		//Size of plane 170 by 100
		midPointX = x + 85;
		midPointY = y - 50;
		hitBox = new Rectangle();
		hitBox.x = (float) x;
		hitBox.y = (float) y;
		hitBox.height = 100;
		hitBox.width = 170;
	}
	public double getMass ()
	{
		return MASS;
	}
	/**
	 * @param entities a arraylist of 
	 */
	public void findNewVelocity (ArrayList<Entity> entities)
	{
		ArrayList<Vector2> vectors = new ArrayList<>();
		Vector2 netForce = new Vector2();
		//Direction determines which way the force pushes the plane
		int directionX = 1;
		int directionY = 1;
		for(int i = 0; i < entities.size();i++)
		{
			//Applies the formula (k * q * Q) / d^2 in order to find the force from each source charge
			if(entities.get(i).getMidPointX() - midPointX > 0 && entities.get(i).getCharge() > 0)
			{
				directionX = -1;
			}
			if(entities.get(i).getMidPointY() - midPointY > 0 && entities.get(i).getCharge() > 0)
			{
				directionY = -1;
			}
			if(entities.get(i).getMidPointX() - midPointX < 0 && entities.get(i).getCharge() < 0)
			{
				directionX = -1;
			}
			if(entities.get(i).getMidPointY() - midPointY < 0 && entities.get(i).getCharge() < 0)
			{
				directionY = -1;
			}
			double diagonalDistance = Math.sqrt(Math.pow(entities.get(i).getMidPointX() - midPointX, 2) + Math.pow(entities.get(i).getMidPointY() - midPointY, 2));
			double diagonalForce = Math.abs(K * fieldStrength * entities.get(i).getCharge()) / diagonalDistance;
			System.out.println("charge midpoint x: " + entities.get(i).getMidPointX());
			System.out.println("charge midpoint y: " + entities.get(i).getMidPointY());
			double angle = Math.atan2(entities.get(i).getMidPointY() - midPointY, entities.get(i).getMidPointX() - midPointX);
			System.out.println("Angle: " + Math.toDegrees(angle));
			
			directionX = 1;
			directionY = 1;
		}
		netForce.set((float)(netForce.x /MASS), (float)(netForce.y/MASS));
//		System.out.println(netForce.x + " " + netForce.y);
		velocity.add(netForce);
//		System.out.println(velocity.x + " " + velocity.y);
		findNewX();
		findNewY();
	}
	private void findNewX ()
	{
		
		positionX = (velocity.x + positionX);
		if(positionX < 0)
			positionX = 0;
		else if(positionX > 600)
			positionX = 500;
		this.setX(positionX);
		
	}
	private void findNewY ()
	{
		positionY = (velocity.y + positionY);
		if(positionY < 0)
			positionY = 100;
		else if(positionY > 480)
			positionY = 480;
		this.setY(positionY);
	}
}
