package com.example.lolitos2;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity{

	static GameActivity instance;
	GameSurface game;
	MediaPlayer gem;
	MediaPlayer start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	instance=this;
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
    	game= new GameSurface(this);
    	//setContentView(R.layout.game_game);
		setContentView(game);
	}
}
