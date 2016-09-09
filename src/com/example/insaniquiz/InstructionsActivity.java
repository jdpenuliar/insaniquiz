package com.example.insaniquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class InstructionsActivity extends Activity {
	//changed to about
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
        
        
        
	}
	@Override
	public void onBackPressed() {
	    // TODO Auto-generated method stub
	    super.onBackPressed();
	    finish();
	    startActivity(new Intent("com.example.insaniquiz.MainscreenActivity"));
	}
}
