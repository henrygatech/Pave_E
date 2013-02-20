package com.t2cn.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class SaveLocationActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlocation);
		ImageButton SaveLoc = (ImageButton) findViewById(R.id.saveLocation);
		SaveLoc.setOnClickListener(saveLocListener);
		
	}
	
	
	OnClickListener saveLocListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
	     Intent intent = new Intent();
	     intent.setClass(SaveLocationActivity.this, LocationActivity.class);
	     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	     
	     Window w = MainActivity.group.getLocalActivityManager()
					.startActivity("MainActivity",intent);
		 View view = w.getDecorView();
		 //把View添加大ActivityGroup中
		 MainActivity.group.setContentView(view);
	     //Window subActivity = group.getLocalActivityManager().startActivity(tag,intent); 
	   

		}
	};
	
}
