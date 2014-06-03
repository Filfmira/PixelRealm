package com.example.lolitos2;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
      // setContentView(game);	//para mostrar o gameView
       //setContentView(new GameSurface(this));
        setContentView(R.layout.main);
        Button bt = (Button) findViewById(R.id.button1);
       // bt.setBackgroundColor(Color.TRANSPARENT);
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//setContentView(new GameSurface(getBaseContext()));
				//setContentView(R.layout.game_game);
				Intent intent = new Intent(getBaseContext(), GameActivity.class);
				startActivity(intent);
				//finish();
				
			}
		});
    }


}
