package com.example.insaniquiz;

import android.media.MediaPlayer;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class SplashActivity extends Activity {

AnimationDrawable AniFrame;
	
public MediaPlayer mp;

	private ImageView MyImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		MyImageView = (ImageView) findViewById (R.id.MyImageView);
		MyImageView.setBackgroundResource(R.drawable.loading);
		AniFrame =(AnimationDrawable) MyImageView.getBackground();
	
		
		Thread logotimer = new Thread(){
			public void run(){
				
				try{
					mp = MediaPlayer.create(SplashActivity.this, R.raw.droid);
					mp.start();
					int timer;
					for(timer=0;timer<=4000;timer=timer+100)
					{
						sleep(100);
						AniFrame.start();
					}
					
					startActivity(new Intent("com.example.insaniquiz.MainscreenActivity"));
				}
				catch(InterruptedException e){
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				finally{
					finish();
				}
			
			}
		
		};
		
		logotimer.start();
 
	
	}
		
	}

