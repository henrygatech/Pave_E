package com.t2cn.tab;

import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity{

	private ListView lv = null;
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static String DB_NAME = "CORE.db";
	private static int DB_VERSION = 4;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locations);

		AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.locations);

		
		// 添加
		Button btnAdd = (Button) findViewById(R.id.buttonOK);
		btnAdd.setOnClickListener(listener);
		
/*		// 显示前3条数据
		Button btnShow = (Button) findViewById(R.id.btnShow);
		btnShow.setOnClickListener(listener);
		// 删除数据
		Button btnClear = (Button) findViewById(R.id.btnClear);
		btnClear.setOnClickListener(listener);*/
		
		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
		
		/* 创建表 */
		db = dbHelper.getWritableDatabase();	//调用SQLiteHelper.OnCreate()   
		
		lv = (ListView) findViewById(R.id.locationlist);
		// 设置在条目上单击监听器
		lv.setOnItemClickListener(itemListener);
		// 设置长按事件
		//registerForContextMenu(lv);
		// 显示所有数据
		showData();
	}
	
	// 初始化数据
	OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
            Intent intent = new Intent();
            intent.setClass(LocationActivity.this, TabActivity01.class);
            startActivity(intent);
		}
	};
	
	
	// 条目上单击处理方法.
	OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// 这里的view是我们在list.xml中定义的LinearLayout对象.
			// 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下:
			TextView locId = (TextView) view.findViewById(R.id.locationID);
            Intent intent = new Intent();
            intent.setClass(LocationActivity.this, TabActivity01.class);
            startActivity(intent);
			//TextView locCounty = (TextView) view.findViewById(R.id.county);
			//TextView stuAge = (TextView) view.findViewById(R.id.ageTo);

			//toastShow("locationID:" + locId.getText().toString() + "; countyNo:"
			//		+ locCounty.getText().toString());
		}
	};
	
	// 封装Toast,一方面调用简单,另一方面调整显示时间只要改此一个地方即可.
	public void toastShow(String text) {
		Toast.makeText(LocationActivity.this, text, 1000).show();
	}
	
	private void showData() {
		
		Cursor cur = dbHelper.queryAllLocation(db);
        startManagingCursor(cur);       

        if (cur != null)
        {
              ListAdapter la = new SimpleCursorAdapter(this,
              R.layout.list, cur, new String[] {"_id"/*, "ROAD","CORE_NO"*/}, new int[] {R.id.locationID/*, R.id.county,R.id.core_no*/});
              lv.setAdapter(la);
        }
        
        
	}
	
}
