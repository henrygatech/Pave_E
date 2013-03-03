package com.t2cn.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.Point;

public class MapAct extends MapActivity {

	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static String DB_NAME = "CORE.db";
	private static int DB_VERSION = 8;
	public int selected; 
	public static ActivityGroup group;
	private Drawable drawable1;
	private MyLocationOverlay mMyLocationOverlay;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
		/* 创建表 */
		db = dbHelper.getWritableDatabase();	//调用SQLiteHelper.OnCreate()   
        MainActivity parent = (MainActivity) getParent();
		LinearLayout container = parent.container;
        Bundle budle=this.getIntent().getExtras();//接受包
        GeoPoint geoPoint;
        double lat;
        double lon;
        
/*		
	Cursor cur = dbHelper.queryAllLocation(db);
        startManagingCursor(cur);       

        if (cur != null)
        {
              ListAdapter la = new SimpleCursorAdapter(this,
              R.layout.list, cur, new String[] {"_id", "ROAD","CORE_NO"}, new int[] {R.id.locationID, R.id.county,R.id.core_no});
              lv.setAdapter(la);
        }
*/

        // Create an overlay and add to map view
        
        HashMap hashmap = new HashMap();
        com.google.android.maps.MapView map = (com.google.android.maps.MapView)findViewById(R.id.map_view);//获得MapView对象
        mMyLocationOverlay = new MyLocationOverlay(this, map);
        map.getOverlays().add(mMyLocationOverlay);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Cursor cur = dbHelper.queryAllLocation(db);
        if(cur!=null)
        {
        	
        	cur.moveToFirst();
        	while (cur.isAfterLast()==false) {
				ArrayList<String> entry = new ArrayList<String>();
				String longitute = String.valueOf(cur.getDouble(1));
				String latitute = String.valueOf(cur.getDouble(0));
				String sampler = cur.getString(2);
				String date = cur.getString(3);
				entry.add(longitute);
				entry.add(latitute);
				entry.add(sampler);
				entry.add(date);
				result.add(entry);
				cur.moveToNext();
		}
        
		List<Overlay> mapOverlays = map.getOverlays();
		
		for(int i = 0; i<result.size();i++)
		{
			GeoPoint temp = new GeoPoint((int)(Double.parseDouble(result.get(i).get(0))*1E6), (int)(Double.parseDouble((result.get(i).get(1)))*1E6));
			OverlayItem overlayitem1 = new OverlayItem(temp,"Sampler: "+result.get(i).get(2),"Date: "+result.get(i).get(3));
			drawable1 = this.getResources().getDrawable(R.drawable.marker);
			MyItemizedOverlay itemizedoverlay1 = new MyItemizedOverlay(drawable1,this,map);
			itemizedoverlay1.addOverlay(overlayitem1);
			mapOverlays.add(itemizedoverlay1);
			map.invalidate();
		}
		
        }
        else
        {
        	
        }
/*		GeoPoint point1 = new GeoPoint(48827708, 2305659);
		//OverlayItem overlayitem1 = new OverlayItem(point1, "France");

		
                if(budle!=null)
        {
            String longitute=budle.getString("longitute");//根据key得到value
            String lattitute=budle.getString("lattitute");//根据key得到value
            lat = Double.parseDouble(lattitute); // latitude
            lon = Double.parseDouble(longitute); // longitude
            geoPoint = new GeoPoint((int)(lat * 1E6),(int)(lon * 1E6));
            MapController mc = map.getController();
            mc.animateTo(geoPoint);
            mc.setZoom(15);
            com.google.android.maps.Projection projection = map.getProjection();  
            //进行画布  
            List<Overlay> overlays = map.getOverlays();  
            overlays.add(new MyPostionOverlay(projection,geoPoint));  
            map.invalidate();
        }
        else
        {
        	lat = 33.77929;
        	lon = -84.399047;
        	geoPoint = new GeoPoint((int)(33.77929*1E6),(int)(-84.399047*1E6));
        	
        }*/
        
/*        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);  
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10,locationListener);     
        Criteria crit = new Criteria();
        String provider = manager.getBestProvider(crit, true);
        Location loc = manager.getLastKnownLocation(provider);
        //Log.i("LOG", "lat"+loc.getLatitude());
        //Log.i("LOG", "long"+loc.getLongitude());
        geoPoint = new GeoPoint((int)(loc.getLatitude() * 1E6),(int)(loc.getLongitude() * 1E6));
        map.getController().setCenter(geoPoint);//设置地图中心
*/      map.getController().setZoom(10);//设置缩放级别
        ZoomControls zoomControls =  (ZoomControls) map.getZoomControls();
        zoomControls.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        map.addView(zoomControls);
        map.displayZoomControls(true);
        
    }
	
    protected void onResume() {
        super.onResume();
        mMyLocationOverlay.enableMyLocation();
    }

    protected void onStop() {
        mMyLocationOverlay.disableMyLocation();
        super.onStop();
    }
    

  //创建一个事件监听器  
  private final LocationListener locationListener = new LocationListener() {     
          public void onLocationChanged(Location location) {     
          updateLocation(location);     
          }     
          public void onProviderDisabled(String provider){  
              updateLocation(null);     
              //Log.i(TAG, "Provider now is disabled..");  
          }     
          public void onProviderEnabled(String provider){  
              //Log.i(TAG, "Provider now is enabled..");  
          }     
          public void onStatusChanged(String provider, int status,Bundle extras){ }     
  };     
    
  private void updateLocation(Location location) {     
	    String latLng;      
	    if (location != null) {    
	    double lat = location.getLatitude();    
	   // Log.i(TAG, "lat:" +lat);
	    double lng = location.getLongitude();  
	    //Log.i(TAG, "log:" +lng);
	    //Log.v(TagName, "Success Get GPS.");
	    //latText.setText(String.valueOf(lat));
	    //lonText.setText(String.valueOf(lng));
	    //latLng = "Latitude:" + lat + "  Longitude:" + lng;
	    } else {     
	    latLng = "Can't access your location";   
	    }  
	    //Log.i(TAG, "The location has changed..");  
	   // Log.i(TAG, "Your Location:" +latLng);  
	}  

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {  
    	
        menu.add(0,0,0,"Add Location");  
        menu.add(0,1,1,"Add Core");  
        menu.add(0,1,2,"Exit");
        //SubMenu ad = menu.addSubMenu("添加").setIcon(android.R.drawable.ic_menu_add);  
        //SubMenu re = menu.addSubMenu("关闭").setIcon(android.R.drawable.ic_menu_close_clear_cancel);  
        //ad.add(0, ITEM_1, 0, "文件");  
        //ad.add(0, ITEM_2, 0, "图片");  
        return true;  
    }  
      
    //覆盖onOptionsItemSelected(MenuItem item)， 响应菜单选项被单击事件  
    public boolean onOptionsItemSelected(MenuItem item) {
        /** 调用一次获得选择的按钮ID*/  
        super.onOptionsItemSelected(item);  
        switch(item.getItemId())
        {
            case 0:  
               // showAbout();  
                break;  
            case 1:  
                //showIsExit();  
                break;  
        }  
        return true;  
    }  
    
    /** 
    * 主要是画图 将标记位置 
    * @author Administrator 
    * 
    */  
    public class MyPostionOverlay extends Overlay {  
      
    private Projection projection;  
    private GeoPoint geoPoint;   
    public MyPostionOverlay(Projection projection, GeoPoint geoPoint ){  
        this.projection=projection;  
        this.geoPoint=geoPoint; 
        
    }  

    
    
    public void draw(Canvas canvas, com.google.android.maps.MapView mapView, boolean shadow) {  
        super.draw(canvas, mapView, shadow);  
        //准备园图  
        Point point = new Point();  
        Paint paint = new Paint();  
        paint.setColor(Color.RED);  
        paint.setAntiAlias(true);  
        paint.setStyle(Style.FILL);  
        //经度转像素  
        projection.toPixels(geoPoint, point);  
        //将图画到上层  
        canvas.drawCircle(point.x, point.y, 6.0f, paint);   
    }  
    
    

}


}
