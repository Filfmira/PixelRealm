package com.example.lolitos2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class LostActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	setContentView(R.layout.activity_lost);
    	
    	Button backButton = (Button)this.findViewById(R.id.btnMenu);
  	  backButton.setOnClickListener(new OnClickListener() {
  	    @Override
  	    public void onClick(View v) {
  	    	Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
  	      finish();
  	    }
  	  });

	}

}