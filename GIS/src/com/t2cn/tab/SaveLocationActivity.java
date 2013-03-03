package com.t2cn.tab;

import java.util.ArrayList;

import com.t2cn.tab.GPSActivity.LocSpinnerSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SaveLocationActivity extends Activity{
	private String county;// county
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static String DB_NAME_CORE = "CORE.db";
	private static String DB_NAME_ACCESS = "access1.db";
	private static int DB_VERSION = 8;

	private Spinner Countyspinner;
	private static  String[] CountySpinner; 
	private ArrayAdapter<String> Countyadapter;
	
	private Spinner RouteTypespinner;
	private static  String[] RouteTypeSpinner={"state route", "county road", "city street", "col road", "unofficial road", "ramp",
		"private road", "public road", "collector-distributor"}; 
	private ArrayAdapter<String> RouteTypeadapter;
	
	private Spinner RouteSuffixspinner;
	private static  String[] RouteSuffixSpinner; 
	private ArrayAdapter<String> RouteSuffixadapter;
	
	private Spinner RouteNospinner;
	private static  String[] RouteNoSpinner; 
	private ArrayAdapter<String> RouteNoadapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlocation);
		dbHelper = new SQLiteHelper(this, DB_NAME_ACCESS, null, DB_VERSION);
		/* 创建表 */

		db = dbHelper.getWritableDatabase();	//调用SQLiteHelper.OnCreate() 
		Button SaveLoc = (Button) findViewById(R.id.saveLocation);
		SaveLoc.setOnClickListener(saveLocListener);
		//SQLiteDatabase access =  dbHelper.readAccess();
		//queryAllCounty(SQLiteDatabase db)
		Cursor cur = dbHelper.queryAllCounty(db);
		CountySpinner = new String[cur.getCount()];
		Log.v("tag",cur.getCount()+"");
		
		if(cur!=null)
        {
        	
        	cur.moveToFirst();
        	int count = 0;
        	while (cur.isAfterLast()==false) {
        		CountySpinner[count++] = cur.getString(0);
        		cur.moveToNext();
        		
        	}
		}
		
		
		Countyadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,CountySpinner);  
		Countyspinner = (Spinner) findViewById(R.id.whichCounty);
		Countyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		Countyspinner.setAdapter(Countyadapter);  
		Countyspinner.setOnItemSelectedListener(new CountySpinnerSelectedListener());
		Countyspinner.setVisibility(View.VISIBLE);  
		
		RouteTypeadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,RouteTypeSpinner);  
		RouteTypespinner = (Spinner) findViewById(R.id.routeType);
		RouteTypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		RouteTypespinner.setAdapter(RouteTypeadapter);  
		RouteTypespinner.setOnItemSelectedListener(new CountySpinnerSelectedListener());
		RouteTypespinner.setVisibility(View.VISIBLE);  
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
	
	//使用数组形式操作  
	class CountySpinnerSelectedListener implements OnItemSelectedListener{  

	  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
	          long arg3) {  
	      //view.setText("你的血型是："+m[arg2]);  
		  county = CountySpinner[arg2];
		  Cursor cur = dbHelper.queryCountyRoute(db,CountySpinner[arg2]);
		  Log.v("tag", cur.getCount()+"");
		  RouteNoSpinner = new String[cur.getCount()];
		  
			if(cur!=null)
	        {
	        	
	        	cur.moveToFirst();
	        	int count = 0;
	        	while (cur.isAfterLast()==false) {
	        		RouteNoSpinner[count++] = cur.getString(0);
	        		cur.moveToNext();
	        		
	        	}
			}
			
			RouteNoadapter = new ArrayAdapter<String>(SaveLocationActivity.this,android.R.layout.simple_spinner_item,RouteNoSpinner);  
			RouteNospinner = (Spinner) findViewById(R.id.Rt_No);
			RouteNoadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
			RouteNospinner.setAdapter(RouteNoadapter);  
			RouteNospinner.setOnItemSelectedListener(new RouteTypeSpinnerSelectedListener());
			RouteNospinner.setVisibility(View.VISIBLE);  
		  
	  }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}  
	  
}
	
	
	//使用数组形式操作  
		class RouteTypeSpinnerSelectedListener implements OnItemSelectedListener{  

		  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
		          long arg3) {  
		      //view.setText("你的血型是："+m[arg2]);  
			  //core.setRoad(roadSpinner[arg2]); 
			  //arg0.getParent()
			  
			  Cursor cur = dbHelper.queryCountyRouteSuffix(db,county,RouteNoSpinner[arg2]);
			  Log.v("tag", cur.getCount()+"");
			  RouteSuffixSpinner = new String[cur.getCount()];
			  
				if(cur!=null)
		        {
		        	cur.moveToFirst();
		        	int count = 0;
		        	while (cur.isAfterLast()==false) {
		        		RouteSuffixSpinner[count++] = cur.getString(0);
		        		cur.moveToNext();
		        		
		        	}
				}
				
				RouteSuffixadapter = new ArrayAdapter<String>(SaveLocationActivity.this,android.R.layout.simple_spinner_item,RouteSuffixSpinner);  
				RouteSuffixspinner = (Spinner) findViewById(R.id.Rt_Suffix);
				RouteSuffixadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
				RouteSuffixspinner.setAdapter(RouteSuffixadapter);  
				RouteSuffixspinner.setOnItemSelectedListener(new RouteSuffixSpinnerSelectedListener());
				RouteSuffixspinner.setVisibility(View.VISIBLE);  

			  
		  }

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}  
		  
	}
		
		
		//使用数组形式操作  
		class RouteSuffixSpinnerSelectedListener implements OnItemSelectedListener{  

		  public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
		          long arg3) {  
		      //view.setText("你的血型是："+m[arg2]);  
			  //core.setRoad(roadSpinner[arg2]); 
			  //arg0.getParent()

			  
		  }

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}  
		  
	}
		
	
}