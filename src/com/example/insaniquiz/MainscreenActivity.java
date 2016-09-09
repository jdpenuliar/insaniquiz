package com.example.insaniquiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainscreenActivity extends Activity{

	public Button Mainscreen_btnPlay;//must declare
	public Button Mainscreen_btnHighScore;//must declare
	public Button Mainscreen_btnInstructions;//must declare
	public Button Mainscreen_btnQuit;//must declare
	public Button Mainscreen_btnPlaySample;
	public MediaPlayer mp;
	MediaPlayer mpButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		
		mpButton = MediaPlayer.create(this,R.raw.btn);
		mp = MediaPlayer.create(MainscreenActivity.this, R.raw.mainbg);
		mp.start();
		mp.setLooping(true);
		
		
		Mainscreen_btnHighScore = (Button) findViewById(R.id.Mainscreen_btnHighScore);
        		Mainscreen_btnHighScore.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		//TODO Auo-generate method sub
        		mpButton.start();
           		startActivity(new Intent("com.example.insaniquiz.HighscoreActivity"));
           		//finish();
        	}
        });	
		
		
		
		Mainscreen_btnInstructions = (Button) findViewById(R.id.Mainscreen_btnInstructions);
		Mainscreen_btnInstructions.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		//TODO Auo-generate method sub
        		mpButton.start();
        		startActivity(new Intent("com.example.insaniquiz.InstructionsActivity"));
        		finish();
        	}
        });
		
		Mainscreen_btnQuit = (Button) findViewById(R.id.Mainscreen_btnQuit);
		Mainscreen_btnQuit.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		//TODO Auto-generate method sub
        		mpButton.start();
        		finish();
        		System.exit(0);
        	}
        });
		
        
		Mainscreen_btnPlaySample = (Button) findViewById(R.id.Mainscreen_btnPlaySample);
		Mainscreen_btnPlaySample.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		//TODO Auo-generate method sub
        		mpButton.start();
        		startActivity(new Intent("com.example.insaniquiz.PlaySampleActivity"));
        		finish();
        	}
        });
		
		
        
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(mp!=null){
			mp.stop();
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		super.onDestroy();
		
		if(mp!=null){
			mp.pause();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mp!=null){
			if(mp.isPlaying()==false){
				mp.start();
			}
		}
	}
		public boolean onCreateOptionsMenu(Menu menu){
			super.onCreateOptionsMenu(menu);
			MenuInflater awesome = getMenuInflater();
			awesome.inflate(R.menu.menu, menu);
			return true;
		}
		
		public boolean onOptionsItemSelected(MenuItem item){
			switch(item.getItemId()){
			case R.id.menuSweet:
				startActivity(new Intent("com.example.insaniquiz.MenuActivity"));
				return true;
			case R.id.menuToast:
				//set next time
				Toast display = Toast.makeText(this, "asdf", Toast.LENGTH_SHORT);
				display.show();
				return true;
			}
			return false;
		}

	
		
	}
