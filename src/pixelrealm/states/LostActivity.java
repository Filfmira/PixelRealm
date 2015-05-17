package pixelrealm.states;

import pixelrealm.entities.Heroi;
import pixelrealm.logic.Statistics;

import com.example.lolitos2.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
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

public class LostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	setContentView(R.layout.activity_lost);
    	Statistics.saveHeroi(new Heroi(0,0), GameActivity.getInstance().getGame());
    	
    	Button backButton = (Button)this.findViewById(R.id.btnMenu);
  	  backButton.setOnClickListener(new OnClickListener() {
  	    @Override
  	    public void onClick(View v) {
  	      finish();
  	    }
  	  });
  	  
  	  Button bq = (Button)this.findViewById(R.id.btnQ);
  	  bq.setOnClickListener(new OnClickListener()
  	  
  	  {

			@Override
			public void onClick(View v) {
				GameActivity.getInstance().finish();
    	    	MainActivity.instance.finish();
    	    	finish();
    	      System.exit(0);
				
			}
  		  
  	  }  );
  	  
  	 Button br = (Button)this.findViewById(R.id.btnRecomecar);
  	br.setOnClickListener(new OnClickListener()
 	  
 	  {

			@Override
			public void onClick(View v) {
				GameActivity.getInstance().finish();
				Intent intent = new Intent(getBaseContext(), GameActivity.class);
				startActivity(intent);
				finish();
			}
 		  
 	  }  );

	}

}
