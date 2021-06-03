package com.myteam.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TrainMan extends Game {
	
	@Override
	public void create () {
		Gdx.app.log("Game", "created");
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
