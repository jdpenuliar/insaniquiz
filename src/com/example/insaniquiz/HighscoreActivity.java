package com.example.insaniquiz;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HighscoreActivity extends Activity {

	private TextView highscore_tvHighscore;
	private Button highscore_btnHome;
	//public static String Sfilename = "MySharedString";
	//SharedPreferences SsomeData;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		/*
		SsomeData = getSharedPreferences(Sfilename, MODE_PRIVATE);
		
		SsomeData = getSharedPreferences(PlaySampleActivity.filename, 0);
		String dataReturned = SsomeData.getString("sharedString", "Couldn't load data");
		*/
		
		SharedPreferences jdSharedPreferences = getSharedPreferences("JDData", Context.MODE_PRIVATE);
		String score = jdSharedPreferences.getString("score", "0");
		
		highscore_tvHighscore = (TextView) findViewById(R.id.highscore_tvHighscore);
		highscore_tvHighscore.setText(score);
		
		/*highscore_btnHome = (Button) findViewById(R.id.highscore_btnHome);
		highscore_btnHome.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent music = new Intent (HighscoreActivity.this, MainscreenActivity.class);
				startActivity(music);
				
			}
		});*/
		
			//highscore_tvHighscore.setText(String.valueOf(HighScore));
        
	}
	@Override
	public void onBackPressed() {
	    // TODO Auto-generated method stub
	    super.onBackPressed();
	    finish();
	    startActivity(new Intent("com.example.insaniquiz.MainscreenActivity"));
	}
}
