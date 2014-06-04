package com.example.lolitos2;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PauseActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
    	  setContentView(R.layout.game_pause);
    	  
    	  Button backButton = (Button)this.findViewById(R.id.btnPausa);
    	  backButton.setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	      finish();
    	    }
    	  });
	}
	
}
