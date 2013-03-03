package com.t2cn.tab;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActivityGroup{
	public int selected;
	public LinearLayout container;
	public GridView gv;
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
    private static final int ITEM_1 = Menu.FIRST;  
    private static final int ITEM_2 = Menu.FIRST+1;  
	private static String DB_NAME = "CORE.db";
	private static int DB_VERSION = 8;
	
	public static ActivityGroup group;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		DBManager dbm = new DBManager(this);
		dbm.openDatabase();
		dbm.closeDatabase();
		
		group = this;
		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
		/* ������ */
		
		db = dbHelper.getWritableDatabase();	//����SQLiteHelper.OnCreate() 
		//dbHelper.onCreate(db);
		container = (LinearLayout)findViewById(R.id.center);
		ArrayList<HashMap<String, Object>> menu_data = new ArrayList<HashMap<String,Object>>();
		int[] images = { android.R.drawable.ic_menu_directions, android.R.drawable.ic_menu_edit,android.R.drawable.ic_menu_mylocation };
		String[] menu_texts = { "Map", "Core","Location" };
		for(int i=0;i<images.length;i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("menu_image", images[i]);
			map.put("menu_text", menu_texts[i]);
			menu_data.add(map);
		}
   
		GridView gv = (GridView)findViewById(R.id.grid_view);
		SimpleAdapter adapter = new SimpleAdapter(this, menu_data,
				R.layout.item, new String[] { "menu_image", "menu_text" },
				new int[] { R.id.item_iamge, R.id.item_text });
		gv.setAdapter(adapter);
		switchActivity(selected);
		gv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(selected == position){
					return;
				}
				selected = position;
				switchActivity(selected);
			}
		});
	}
	
    //����onCreateOptionsMenu(Menu menu)����������Ӳ˵���  
    public boolean onCreateOptionsMenu(Menu menu) {  
    	
        menu.add(0,0,0,"Add Location");
        menu.add(0,1,1,"Add Core");
        menu.add(0,1,2,"exit");
        //SubMenu ad = menu.addSubMenu("���").setIcon(android.R.drawable.ic_menu_add);  
        //SubMenu re = menu.addSubMenu("�ر�").setIcon(android.R.drawable.ic_menu_close_clear_cancel);  
        //ad.add(0, ITEM_1, 0, "�ļ�");  
        //ad.add(0, ITEM_2, 0, "ͼƬ");  
        return super.onCreateOptionsMenu(menu);  
    }  
      
   //����onOptionsItemSelected(MenuItem item)�� ��Ӧ�˵�ѡ������¼�  
    public boolean onOptionsItemSelected(MenuItem item) {
        /** ����һ�λ��ѡ��İ�ťID*/  
        super.onOptionsItemSelected(item);  
        switch(item.getItemId())
        {
            case 0:
                showAbout();  
                break;  
            case 1:  
                showIsExit();  
                break;  
        }  
        return true;  
    } 
    
    /** �������ڶԻ���*/  
    public void showAbout(){  
       	appl b = (appl) getApplicationContext();
		try{
			dbHelper.save(b.getCore(), db);
            System.out.println("data saved");
		}catch(Exception e){
			e.printStackTrace();
		}
    }  
    
    /** �����˳��Ի���*/  
    public void showIsExit(){  
        /** ����һ�������Ի���������ñ���,��Ϣ��,ȷ����ȡ����ť�¼�*/  
        new AlertDialog.Builder(this).setTitle("cancel").setMessage("exit_text").setNegativeButton("exit_cancle", new DialogInterface.OnClickListener(){  
            public void onClick(DialogInterface dialog, int which) {  
            }    
        }).setPositiveButton("enter", new DialogInterface.OnClickListener(){  
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                /** �˳�����*/  
                finish();  
            }  
        }).show();  
    }  
	
	
	public void switchActivity(int selected){
		
		Intent intent = null;
		String tag = "";
		
		if(selected==0){
			intent = new Intent(MainActivity.this, MapAct.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			tag = "MapActivity";

		}else if(selected==1){
			intent = new Intent(MainActivity.this,GPSActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			tag = "GPSActivity";
		}else if(selected==2){
			intent = new Intent(MainActivity.this, SaveLocationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			tag = "SaveLocationActivity";
		}
		
		 Window subActivity = group.getLocalActivityManager().startActivity(tag,intent); 
		 container.removeAllViews();
         //�������View  
         container.addView(subActivity.getDecorView(),  
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);  
	}
	
/*	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		//�Ѻ����¼�������Activity����
		group.getLocalActivityManager()
			.getCurrentActivity().onBackPressed();
	}
*/

 /*   *//**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * *//*
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        
        switch (item.getItemId())
        {
        case R.id.menu_bookmark:
        	// Single menu item is selected do something
        	// Ex: launching new activity/screen or show alert message
            Toast.makeText(MainActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_save:
        	Toast.makeText(MainActivity.this, "Save is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_search:
        	Toast.makeText(MainActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_share:
        	Toast.makeText(MainActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_delete:
        	Toast.makeText(MainActivity.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_preferences:
        	Toast.makeText(MainActivity.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }*/
    
	
}