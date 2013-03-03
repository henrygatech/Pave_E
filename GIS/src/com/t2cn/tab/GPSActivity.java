package com.t2cn.tab;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

//继承实现接口LocationListener
//系统将自动生成接口待实现方法onLocationChanged，onStatusChanged，onProviderEnabled，onProviderDisabled
//程序在启动的时候将获取经纬度
//记得在onResume和onPause的时候需要实现相应的事件
//onPause要取消GPS的更新事件注册。removeUpdates
//onResume要requestLocationUpdates。
public class GPSActivity extends Activity implements LocationListener {
	/** Called when the activity is first created. */

	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static String DB_NAME = "CORE.db";
	private static int DB_VERSION = 8;
	private TextView latText;
	private TextView lonText;
	private EditText roadDescription;
	private Spinner Locationspinner;
	private static final String[] locSpinner = { "loc1", "loc2", "loc3",
			"loc4", "other" };
	private ArrayAdapter<String> locadapter;
	private Spinner Roadspinner;
	private static final String[] roadSpinner = { "road1", "road2", "road3",
			"road4", "other" };
	private ArrayAdapter<String> roadadapter;
	private Spinner Datespinner;
	private String[] dateSpinner;
	private ArrayAdapter<String> dateadapter;
	private boolean dircreateflag;
	private Spinner Samplerspinner;
	private static final String[] samplerSpinner = { "Zhen Wang",
			"Zhaohua Wang", "John Smith", "other" };;
	private ArrayAdapter<String> sampleradapter;
	private Spinner Directionspinner;
	private static final String[] directionSpinner = { "North", "South",
			"West", "East" };
	private ArrayAdapter<String> directionadapter;
	private Spinner RetrieveFromspinner;
	private static final String[] retrieveFromSpinner = { "TravelLane",
			"TurnLane", "InsideShoulder", "OutsideShoulder", "Ramp", "Shoulder" };
	private ArrayAdapter<String> retrieveFromadapter;
	private Spinner LaneLocationspinner;
	private static final String[] laneLocationSpinner = { "Outside Left",
			"Left Wheelpath", "Between Wheelpath", "Right Wheelpath",
			"Outside Right" };
	private ArrayAdapter<String> laneLocationadapter;
	private EditText Diameter;
	private EditText maxDepth;
	private EditText PCCThickness;
	private Spinner Materialspinner;
	private static final String[] materialSpinner = { "ASPHALT", "CONCRETE",
			"ACCOVERPCC", "PCCOVERACC" };
	private ArrayAdapter<String> materialadapter;
	private Spinner UnderlyingMaterialspinner;
	private static final String[] underlyingmaterialSpinner = {
			"Graded Aggregate Base", "Solid Cement Base", "Soil",
			"PCC (Concrete)", "Lime Rock", "Soil Aggregate Base",
			"Soil Cement", "Asphalt Sand Mix", "Sand Cement Base", "Unknown" };
	private ArrayAdapter<String> underlyingmaterialadapter;
	private Spinner CrackTypespinner;
	private static final String[] crackTypeSpinner = { "Rutting",
			"Edge Distress", "Raveling", "Bleeding", "Corrugation",
			"Loss of Section", "Load Cracking", "Block Cracking",
			"Reflective Cracking", "Patches & Potholes", "None" };
	private ArrayAdapter<String> crackTypeadapter;
	private Spinner CrackDirectionspinner;
	private static final String[] crackDirectionspinner = { "Top to Bottom",
			"Bottom to Top", "Direction Undetermined", "None" };
	private ArrayAdapter<String> crackDirectionadapter;
	private EditText coreCondition;
	private EditText comments;
	private ImageView iv_image;
	private Button bt_camera;
	private boolean isSaved;
	private Bitmap photo;
	private File file;
	private String saveDir;
	private coreBean core;
	private static final String TAG = "LOCATION DEMO";
	// private LocationManager location;
	private String provider;
	private LocationManager manager;
	private Location loc;
	private final String TagName = "com.t2cn.tab.GPSActivity";
	private String strsec;
	private ImageView img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.core);
		ImageButton AddLoc = (ImageButton) findViewById(R.id.addLocation);
		core = new coreBean();
		dbHelper = new SQLiteHelper(this, DB_NAME, null, DB_VERSION);
		isSaved = true;
		/* 创建表 */
		/*
		 * saveDir = Environment.getExternalStorageDirectory() .getPath() +
		 * "/temp_image/"; File savePath = new File(saveDir); //if
		 * (!savePath.exists()) savePath.mkdirs();
		 * 
		 * Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		 * 
		 * SimpleDateFormat formatter1 = new SimpleDateFormat ("MMddyyyyHH");
		 * String str1 = formatter1.format(curDate); String str2 = "temp";
		 */
		// Environment.getExternalStorageDirectory().getPath() + "/temp_image"
		// Environment.getExternalStorageDirectory().getAbsolutePath()

		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy ");

		String str = formatter.format(curDate);
		Log.v("TIME", str);
		dateSpinner = new String[1];
		dateSpinner[0] = str;
		// saveDir = Environment.getExternalStorageDirectory().getPath() +
		// "/temp_image";

		db = dbHelper.getWritableDatabase(); // 调用SQLiteHelper.OnCreate()
		locadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locSpinner);
		Locationspinner = (Spinner) findViewById(R.id.whichLocation);
		locadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Locationspinner.setAdapter(locadapter);
		Locationspinner
				.setOnItemSelectedListener(new LocSpinnerSelectedListener());
		Locationspinner.setVisibility(View.VISIBLE);

		roadadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, roadSpinner);
		Roadspinner = (Spinner) findViewById(R.id.road);
		roadadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Roadspinner.setAdapter(roadadapter);
		Roadspinner
				.setOnItemSelectedListener(new RoadSpinnerSelectedListener());
		Roadspinner.setVisibility(View.VISIBLE);

		dateadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, dateSpinner);
		Datespinner = (Spinner) findViewById(R.id.date);
		dateadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Datespinner.setAdapter(dateadapter);
		Datespinner
				.setOnItemSelectedListener(new DateSpinnerSelectedListener());
		Datespinner.setVisibility(View.VISIBLE);

		roadDescription = (EditText) findViewById(R.id.roadDescription);

		sampleradapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, samplerSpinner);
		Samplerspinner = (Spinner) findViewById(R.id.whichsampler);
		sampleradapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Samplerspinner.setAdapter(sampleradapter);
		Samplerspinner
				.setOnItemSelectedListener(new SamplerSpinnerSelectedListener());
		Samplerspinner.setVisibility(View.VISIBLE);

		directionadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, directionSpinner);
		Directionspinner = (Spinner) findViewById(R.id.whichDirection);
		directionadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Directionspinner.setAdapter(directionadapter);
		Directionspinner
				.setOnItemSelectedListener(new DirectionSpinnerSelectedListener());
		Directionspinner.setVisibility(View.VISIBLE);

		retrieveFromadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, retrieveFromSpinner);
		RetrieveFromspinner = (Spinner) findViewById(R.id.retrieveFrom);
		retrieveFromadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		RetrieveFromspinner.setAdapter(retrieveFromadapter);
		RetrieveFromspinner
				.setOnItemSelectedListener(new RetriveFromSpinnerSelectedListener());
		RetrieveFromspinner.setVisibility(View.VISIBLE);

		laneLocationadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, retrieveFromSpinner);
		LaneLocationspinner = (Spinner) findViewById(R.id.laneLocation);
		laneLocationadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		LaneLocationspinner.setAdapter(retrieveFromadapter);
		LaneLocationspinner
				.setOnItemSelectedListener(new LaneLocationSpinnerSelectedListener());
		LaneLocationspinner.setVisibility(View.VISIBLE);

		roadDescription = (EditText) findViewById(R.id.roadDescription);
		Diameter = (EditText) findViewById(R.id.Diameter);
		maxDepth = (EditText) findViewById(R.id.MaxDepth);
		PCCThickness = (EditText) findViewById(R.id.PCCThickness);

		materialadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, materialSpinner);
		Materialspinner = (Spinner) findViewById(R.id.Material);
		materialadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Materialspinner.setAdapter(materialadapter);
		Materialspinner
				.setOnItemSelectedListener(new MaterialSpinnerSelectedListener());
		Materialspinner.setVisibility(View.VISIBLE);

		underlyingmaterialadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, underlyingmaterialSpinner);
		UnderlyingMaterialspinner = (Spinner) findViewById(R.id.underlyingMaterial);
		underlyingmaterialadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		UnderlyingMaterialspinner.setAdapter(underlyingmaterialadapter);
		UnderlyingMaterialspinner
				.setOnItemSelectedListener(new UnderlyingSpinnerSelectedListener());
		UnderlyingMaterialspinner.setVisibility(View.VISIBLE);

		crackTypeadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, crackTypeSpinner);
		CrackTypespinner = (Spinner) findViewById(R.id.crackType);
		crackTypeadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		CrackTypespinner.setAdapter(crackTypeadapter);
		CrackTypespinner
				.setOnItemSelectedListener(new CrackTypeSpinnerSelectedListener());
		CrackTypespinner.setVisibility(View.VISIBLE);

		crackDirectionadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, crackDirectionspinner);
		CrackDirectionspinner = (Spinner) findViewById(R.id.crackDirection);
		crackDirectionadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		CrackDirectionspinner.setAdapter(crackDirectionadapter);
		CrackDirectionspinner
				.setOnItemSelectedListener(new CrackDirectionSpinnerSelectedListener());
		CrackDirectionspinner.setVisibility(View.VISIBLE);

		// coreCondition = (EditText)findViewById(R.id.coreCondition);
		// comments = (EditText)findViewById(R.id.comments);

		latText = (TextView) findViewById(R.id.lattitute);
		lonText = (TextView) findViewById(R.id.longitute);
		AddLoc.setOnClickListener(addLocListener);
		iv_image = (ImageView) findViewById(R.id.iv_image);
		Button btnAdd = (Button) findViewById(R.id.addCore);
		btnAdd.setOnClickListener(addCoreListener);
		
		/*
		 * this block is to be uncommented
		 * 
		 * 
		 * 
		 * location =
		 * (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		 * 
		 * location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,
		 * 10,locationListener); Criteria crit = new Criteria(); provider =
		 * location.getBestProvider(crit, false); loc =
		 * location.getLastKnownLocation(provider);
		 * 
		 * if(loc != null) { Log.v(TagName, "Success Get GPS.");
		 * latText.setText(String.valueOf((double)loc.getLatitude()));
		 * lonText.setText(String.valueOf((double)loc.getLongitude())); } else {
		 * Log.v(TagName, "Failed to Get GPS."); latText.setText("");
		 * lonText.setText(""); }
		 */

		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10,
				locationListener);
		Criteria crit = new Criteria();
		provider = manager.getBestProvider(crit, false);
		loc = manager.getLastKnownLocation(provider);
		// Location location =
		// manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		// 第一次获得设备的位置
		updateLocation(loc);
		// 重要函数，监听数据测试
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10,
				locationListener);

		 iv_image = (ImageView) findViewById(R.id.iv_image1);
		 LayoutInflater inflater = LayoutInflater.from(GPSActivity.this);
		 final View imgEntryView = inflater.inflate(R.layout.dialog_photo, null); // 加载自定义的布局文件
		 img = (ImageView)imgEntryView.findViewById(R.id.large_image);
		 iv_image.setOnClickListener(new OnClickListener() {
		 public void onClick(View paramView) {
		 
			 
			 final AlertDialog dialog = new AlertDialog.Builder(GPSActivity.this).create();
			 
			 
			 //imageDownloader.download("图片地址",img); // 这个是加载网络图片的，可以是自己的图片设置方法
			 dialog.setView(imgEntryView); // 自定义dialog
			 dialog.show();
			 // 点击布局文件（也可以理解为点击大图）后关闭dialog，这里的dialog不需要按钮
			 imgEntryView.setOnClickListener(new OnClickListener() {
			 public void onClick(View paramView) {
			 dialog.cancel();
			 }
			 });
		 }
		 });
		 
		bt_camera = (Button) findViewById(R.id.bt_camera);

		bt_camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				destoryImage();
				String state = Environment.getExternalStorageState();
				
				SimpleDateFormat formattersec = new SimpleDateFormat(
						"MM_dd_yyyy_HH_MM_SS");
				Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
				// = Environment.getExternalStorageDirectory()
				// .getPath() + "/temp_image";
				// String str = formatter.format(curDate);、6
				String strsec = formattersec.format(curDate);
				
				if(isSaved == true)
				{
					int number = (int) (Math.random()*9000 + 1000);
					SimpleDateFormat formatter = new SimpleDateFormat ("MM-dd-yyyy-HH-MM"+number);
					saveDir = Environment.getExternalStorageDirectory().getPath()+"/"+formatter.format(curDate);
					isSaved = false;
					new File(saveDir).mkdirs();
				}
				
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					Log.v("SDCARD", saveDir);
					file = new File(saveDir, strsec+ ".jpg");
					file.delete();
					if (!file.exists()) {
						try {
							file.createNewFile();
							
						} catch (IOException e) {
							e.printStackTrace();
							Toast.makeText(GPSActivity.this, "照片创建失败!",
									Toast.LENGTH_LONG).show();
							return;
						}
					}
					Intent intent = new Intent(
							"android.media.action.IMAGE_CAPTURE");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					startActivityForResult(intent, 1);
				} else {
					Toast.makeText(GPSActivity.this, "sdcard无效或没有插入!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	/*
	 * @Override public void onClick(View v) { destoryImage(); //impleDateFormat
	 * formatter = new SimpleDateFormat ("MM-dd-yyyy-HH-MM"); SimpleDateFormat
	 * formattersec = new SimpleDateFormat ("MM_dd_yyyy_HH_MM_SS"); Date curDate
	 * = new Date(System.currentTimeMillis());//获取当前时间 //=
	 * Environment.getExternalStorageDirectory() //.getPath() + "/temp_image";
	 * //String str = formatter.format(curDate); String strsec =
	 * formattersec.format(curDate); if(dircreateflag==true) { SimpleDateFormat
	 * formatter = new SimpleDateFormat ("MMddyyyyHHMM"); String str =
	 * formatter.format(curDate);
	 * 
	 * saveDir = Environment.getExternalStorageDirectory().getAbsolutePath() +
	 * "/"+str;
	 * 
	 * 
	 * Log.v("Path",saveDir);
	 * //Environment.getExternalStorageDirectory().getAbsolutePath() File
	 * savePath = new File(saveDir); if (!savePath.exists()) {
	 * savePath.mkdirs(); //} dircreateflag = false; } String saveDir1 =
	 * Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+"temp";
	 * String strsec1 = "sec"; String state =
	 * Environment.getExternalStorageState(); if
	 * (state.equals(Environment.MEDIA_MOUNTED)) { file = new File(saveDir1,
	 * strsec1+".jpg"); //file.delete(); if (!file.exists()) { try { File
	 * savePath = new File(saveDir); if (!savePath.exists()) {
	 * savePath.mkdirs(); //} file.createNewFile();
	 * 
	 * } catch (IOException e) { e.printStackTrace();
	 * Toast.makeText(GPSActivity.this,
	 * "Opps, fail to save image.",Toast.LENGTH_LONG).show(); return; } } Intent
	 * intent = new Intent( "android.media.action.IMAGE_CAPTURE");
	 * intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
	 * startActivityForResult(intent, 1); } else {
	 * Toast.makeText(GPSActivity.this, "sdcard not inserted",
	 * Toast.LENGTH_SHORT).show(); } } });
	 */
	/*
	 * File savePath = new File(saveDir); if (!savePath.exists()) {
	 * savePath.mkdirs(); }
	 */

	/*
	 * @Override public void onResume() { super.onResume();
	 * manager.requestLocationUpdates(provider, 400, 1, this); }
	 * 
	 * @Override public void onPause() { super.onPause();
	 * manager.removeUpdates(this); }
	 * 
	 * @Override public void onStatusChanged(String provider, int status, Bundle
	 * extras) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			if (file != null && file.exists()) {
				BitmapFactory.Options option = new BitmapFactory.Options();
				option.inSampleSize = 2;
				photo = BitmapFactory.decodeFile(file.getPath(), option);
				iv_image.setImageBitmap(photo);
				img.setImageBitmap(photo);
				
			}
		}
	}

	@Override
	protected void onDestroy() {
		destoryImage();
		super.onDestroy();
	}

	private void destoryImage() {
		if (photo != null) {
			photo.recycle();
			photo = null;
		}
	}

	// 创建一个事件监听器
	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			updateLocation(location);
		}

		public void onProviderDisabled(String provider) {
			updateLocation(null);
			Log.i(TAG, "Provider now is disabled..");
		}

		public void onProviderEnabled(String provider) {
			Log.i(TAG, "Provider now is enabled..");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	private void updateLocation(Location location) {
		String latLng;
		if (location != null) {
			double lat = location.getLatitude();
			Log.i(TAG, "lat:" + lat);
			double lng = location.getLongitude();
			Log.i(TAG, "log:" + lng);
			Log.v(TagName, "Success Get GPS.");
			latText.setText(String.valueOf(lat));
			lonText.setText(String.valueOf(lng));
			// latLng = "Latitude:" + lat + "  Longitude:" + lng;
		} else {
			latLng = "Can't access your location";
		}
		Log.i(TAG, "The location has changed..");
		// Log.i(TAG, "Your Location:" +latLng);
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.v(TagName, "onProviderEnabled");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.v(TagName, "onProviderDisabled");
	}

	// 初始化数据
	OnClickListener addCoreListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			MainActivity parent = (MainActivity) getParent();
			LinearLayout container = parent.container;
			container.removeAllViews();
			// coreBean core = new coreBean();
			//core.setCoreid(1);
			int number = (int) (Math.random()*9000 + 1000);
			SimpleDateFormat formattersec = new SimpleDateFormat(
					"MMddyyyyHHMMSS");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			// = Environment.getExternalStorageDirectory()
			// .getPath() + "/temp_image";
			// String str = formatter.format(curDate);
			String strsec = formattersec.format(curDate);
			//core.setCoreid(0);
			core.setFieldid(strsec+number);
			//core.setLocation_id("2");
			core.setLongitute((double) Double.parseDouble(lonText.getText()
					.toString()));
			core.setLatitute((double) Double.parseDouble(latText.getText()
					.toString()));
			dbHelper.save(core, db);
			Intent intent = new Intent();
			intent.putExtra("lattitute", latText.getText().toString());
			intent.putExtra("longitute", lonText.getText().toString());
			intent.setClass(GPSActivity.this, MapAct.class);
			String tag = "MapAct";
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Window subActivity = parent.getLocalActivityManager()
					.startActivity(tag, intent);
			// 容器添加View
			container.addView(subActivity.getDecorView(),
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}
	};

	// 初始化数据
	OnClickListener addLocListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MainActivity parent = (MainActivity) getParent();
			LinearLayout container = parent.container;
			container.removeAllViews();

			Intent intent = new Intent();
			intent.putExtra("lattitute", latText.getText().toString());
			intent.putExtra("longitute", lonText.getText().toString());
			intent.setClass(GPSActivity.this, SaveLocationActivity.class);
			String tag = "SaveLocationActivity";
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Window subActivity = parent.getLocalActivityManager()
					.startActivity(tag, intent);
			// 容器添加View
			container.addView(subActivity.getDecorView(),
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		}
	};

	// 使用数组形式操作
	class LocSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setLocation_id(locSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class RoadSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);

			core.setRoad(roadSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class DateSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setDate(dateSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}

	// 使用数组形式操作
	class SamplerSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setSampler(samplerSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class DirectionSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setDirection(directionSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class RetriveFromSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setRetrieved_from(retrieveFromSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class LaneLocationSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setLane_location(laneLocationSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class MaterialSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setMaterial(materialSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class UnderlyingSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]); \
			core.setUnderlying_material(underlyingmaterialSpinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class CrackTypeSpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setCracking_type((crackTypeSpinner[arg2]));
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 使用数组形式操作
	class CrackDirectionSpinnerSelectedListener implements
			OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			core.setCracking_direction(crackDirectionspinner[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}