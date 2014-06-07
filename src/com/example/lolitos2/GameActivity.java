package com.example.lolitos2;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity{

	private static GameActivity instance;
	private GameSurface game;
	MediaPlayer gem;
	MediaPlayer start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	setInstance(this);
    	gem=MediaPlayer.create(this, R.raw.gem);
    	try {
			gem.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	start=MediaPlayer.create(this, R.raw.start);

    	//setContentView(R.layout.game_game);
    	setGame(new GameSurface(this));
    	//setContentView(R.layout.game_game);
		setContentView(getGame());
	}
	
	
	
	public GameSurface getGame() {
		return game;
	}
	public void setGame(GameSurface game) {
		this.game = game;
	}
	public static GameActivity getInstance() {
		return instance;
	}
	public static void setInstance(GameActivity instance) {
		GameActivity.instance = instance;
	}
}
