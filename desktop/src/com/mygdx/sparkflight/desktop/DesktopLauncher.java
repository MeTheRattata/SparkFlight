package com.mygdx.sparkflight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.sparkflight.SparkFlight;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Spark Puzzle";
		config.width = 800;
		config.height = 600;
		try
		{
			new LwjglApplication(new SparkFlight(), config);
		} catch (Throwable t)
		{
			t.printStackTrace();
		}
	}
}