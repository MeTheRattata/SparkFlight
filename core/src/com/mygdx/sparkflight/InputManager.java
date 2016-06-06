package com.mygdx.sparkflight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Buttons;

public class InputManager 
{
	GameScreen screen;
	
	public InputManager(GameScreen newScreen)
	{
		screen = newScreen;
		
		InputAdapter inListen = new InputAdapter()
		{
			public boolean touchUp (int x, int y, int pointer, int button) {
			   if(button == Buttons.LEFT) 
			   {
				   SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - 65, screen.WORLD_HEIGHT - Gdx.input.getY() - 65, 1);
				   screen.stage.addActor(newCharge);
				   Player.charges.add(newCharge);
				   return true;
				}
				else if(button == Buttons.RIGHT) 
				{
					SourceCharge newCharge = new SourceCharge(Gdx.input.getX() - 65, screen.WORLD_HEIGHT - Gdx.input.getY() - 65, -1);
					screen.stage.addActor(newCharge);
					Player.charges.add(newCharge);
					return true;
				}
				return false;
			}
		};
		Gdx.input.setInputProcessor(inListen);
	}
}