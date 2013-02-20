package com.t2cn.tab;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity01 extends Activity{
		
		private TextView tv;
		private static final String[] m={"1","2","3","4","other"};  
	    private TextView view ;  
	    private Spinner spinner;  
	    private ArrayAdapter<String> adapter;  
		private Cursor cursor;
		private SQLiteDatabase db;
		private SQLiteHelper dbHelper;
		private static String DB_NAME = "myCore.db";
		private static int DB_VERSION = 3;
		private EditText pi_no;
		private EditText road;
		
		//private ListAdapter listAdapter;
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        // TODO Auto-generated method stub  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.tab1);
	        //Intent intent1 = getIntent();
	        
			Button btnAdd = (Button) findViewById(R.id.savebutton);
			btnAdd.setOnClickListener(saveButtonListener);
			
	        view = (TextView) findViewById(R.id.textview);  
	        spinner = (Spinner) findViewById(R.id.core_no);  
	        //将可选内容与ArrayAdapter连接起来  
	        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);  
	        pi_no = (EditText)findViewById(R.id.pi_no1);
	        road = (EditText)findViewById(R.id.road1);
	        
	        //设置下拉列表的风格  
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	          
	        //将adapter 添加到spinner中  
	        spinner.setAdapter(adapter);
	          
	        //添加事件Spinner事件监听    
	        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());  
	          
	        //设置默认值  
	        spinner.setVisibility(View.VISIBLE);  
	        
	        pi_no.setOnFocusChangeListener(new pi_idFocusChangeListener());
	        road.setOnFocusChangeListener(new roadFocusChangeListener());
	        
	        //try{
	    		/* 初始化并创建数据库 */
	    		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
	    		/* 创建表 */
	    		db = dbHelper.getWritableDatabase();	//调用SQLiteHelper.OnCreate()       
	    		
	        	/* 查询表，得到cursor对象 */
/*	        	cursor = db.query(SQLiteHelper.TB_NAME, null, null, null, null, null,null + " DESC");
	        	cursor.moveToFirst();
	        	while(!cursor.isAfterLast() && (cursor.getString(1) != null)){    
	        		coreBean city = new coreBean();
	        		city.setId(cursor.getString(0));
	        		city.setCity(cursor.getString(1));
	        		city.setCode(cursor.getString(2));
	        		cityList.add(city);
	        		cursor.moveToNext();
	        	}*/
/*	    	}catch(IllegalArgumentException e){
	    		//当用SimpleCursorAdapter装载数据时，表ID列必须是_id，否则报错column '_id' does not exist
	    		e.printStackTrace();
	    		//当版本变更时会调用SQLiteHelper.onUpgrade()方法重建表 注：表以前数据将丢失
	    		++ DB_VERSION;
	    		dbHelper.onUpgrade(db, --DB_VERSION, DB_VERSION);
//	    		dbHelper.updateColumn(db, SQLiteHelper.ID, "_"+SQLiteHelper.ID, "integer");
	    	}*/
	          
	}
	    

	    
	    class pi_idFocusChangeListener implements OnFocusChangeListener
	    {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//Log.d(TAG, mEditText.getText().toString());
				appl a = (appl)getApplicationContext();
	            //a.getCore().setPi_id(pi_no.getText().toString());
				System.out.println("edit completed!");
			}
	    }
	    
	    class roadFocusChangeListener implements OnFocusChangeListener
	    {
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				//Log.d(TAG, mEditText.getText().toString());
				appl a = (appl)getApplicationContext();
	            //a.getCore().setRoad(road.getText().toString());
				System.out.println("edit completed!");
			}
	    }
	    
	    class SpinnerSelectedListener implements OnItemSelectedListener{  
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
	                long arg3) {  
	            view.setText("Your choice is: "+m[arg2]); 
	            appl a = (appl)getApplicationContext();
	            //a.getCore().setCore_no(arg2);
	            System.out.println("spinner choice  "+ arg2);
	            //Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show(); 
	        }  
	  
	        public void onNothingSelected(AdapterView<?> arg0) {  
	        }  
	    }
	    
	    
		OnClickListener saveButtonListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				//MainActivity.showAbout();
		       	appl b = (appl) getApplicationContext();
				try{
					dbHelper.save(b.getCore(), db);
		            System.out.println("data saved");
				}catch(Exception e){
					e.printStackTrace();
				}
				
				Intent intent = new Intent();
	            intent.setClass(TabActivity01.this, MainActivity.class);
	            startActivity(intent);
			}
		};
}