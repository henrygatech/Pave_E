package com.t2cn.tab;
import java.io.File;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/**
 * 实现对表的创建、更新、变更列名操作
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	public static final String TB_NAME = "CORE";
	
	
	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	

	/**
	 * 创建新表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				TB_NAME + "(" +
				coreBean.FIELDID + " varchar not null," + 
				coreBean.LOCATION_ID + " varchar not null," + 
				coreBean.COREID + " varchar primary key," + 
				coreBean.ORGANIZATION + " varchar," + 
				coreBean.SAMPLER + " date," + 
				coreBean.CORE_DATE + " varchar," + 
				coreBean.ROAD_DESCRIPTION + " varchar," + 
				coreBean.DIRECTION + " varchar," + 
				coreBean.NO_OF_LANE + " integer," + 
				coreBean.LONGITUTE + " number," + 
				coreBean.LATITUTE + " number," + 
				coreBean.RETRIEVED_FROM + " integer," + 
				coreBean.LANE_LOCATION + " integer," + 
				coreBean.MATERIAL + " integer," + 
				coreBean.DIAMETER + " integer," + 
				coreBean.LENGTH + " number," + 
				coreBean.UNDERLYING_MATERIAL + " integer," + 
				coreBean.PCCTHICKNESS + " number," + 
				coreBean.CRACKING_TYPE + " integer," + 
				coreBean.CRACKING_DIRECTION + " integer," + 
				coreBean.CORE_LEVEL + " integer," + 
				coreBean.MAXDEPTH + " number," + 
				coreBean.CORECONDITION + " varchar," + 
				coreBean.COMMENTS + " varchar" + 
				")"
				
				);
	}
	
	public void save(coreBean core, SQLiteDatabase db)
	{
		String sql = "insert into core(fieldid,location_id,coreid,organization,sampler,core_date,road_description,direction,no_of_lane,longitute,latitute,retrieved_from,lane_location,material,diameter,length,underlying_material,pccthickness,cracking_type,cracking_direction,core_level,maxdepth,corecondition,comments) values(?,?,null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		db.execSQL(sql, new Object[]{
				core.getFieldid(),core.getLocation_id(),core.getOrgaization(),core.getSampler(),core.getDate(),core.getRoad_description(),core.getDirection(),core.getNo_of_lane(),core.getLongitute(),core.getLatitute(),core.getRetrieved_from(),core.getLane_location(),core.getMaterial(),core.getDiameter(),core.getLength(),core.getUnderlying_material(),core.getPccthickness(),core.getCracking_type(),core.getCracking_direction(),core.getCore_level(),core.getMaxdepth(),core.getCorecondition(),core.getComments()
		});
		//System.out.println("core.getPi_id() "+core.getFieldid());
		System.out.println("core.getLatitude() "+core.getLongitute());
		//System.out.println("core.getRoad() "+core.getLatitute());
		Log.v("Test Saving", sql);
	}
	
	public void saveLoc(coreBean core, SQLiteDatabase db)
	{
		String sql = "insert into core(fieldid,location_id,coreid,organization,sampler,core_date,road_description,direction,no_of_lane,longitute,latitute,retrieved_from,lane_location,material,diameter,length,underlying_material,pccthickness,cracking_type,cracking_direction,core_level,maxdepth,corecondition,comments) values(?,?,null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		db.execSQL(sql, new Object[]{
				core.getFieldid(),core.getLocation_id(),core.getOrgaization(),core.getSampler(),core.getDate(),core.getRoad_description(),core.getDirection(),core.getNo_of_lane(),core.getLongitute(),core.getLatitute(),core.getRetrieved_from(),core.getLane_location(),core.getMaterial(),core.getDiameter(),core.getLength(),core.getUnderlying_material(),core.getPccthickness(),core.getCracking_type(),core.getCracking_direction(),core.getCore_level(),core.getMaxdepth(),core.getCorecondition(),core.getComments()
		});
		//System.out.println("core.getPi_id() "+core.getFieldid());
		System.out.println("core.getLatitude() "+core.getLongitute());
		//System.out.println("core.getRoad() "+core.getLatitute());
		Log.v("Test Saving", sql);
	}
	
	public  SQLiteDatabase readAccess()
	{
		File name = new File("/storage/sdcard0/access.db");
		SQLiteDatabase access =  SQLiteDatabase.openOrCreateDatabase(name, null);
		if (access==null)
			{
				Log.v("tag", "is null");
				return null;
			}
			
		else 
			{
				Log.v("tag", "is not null");
				return access;
			}
	}
	
	public  Cursor queryAllCounty(SQLiteDatabase db) {
		// 0 longitute    1 latitute    2 sampler 3 date
		String sql = "select CountyName from tblCounty";
		
         Cursor cur=db.query("tblCounty", new String[] {"CountyName"}, null, null, null,null, null);                       
         return cur;
	}
	
	public static Cursor queryCountyRoute(SQLiteDatabase db, String county) {
		// 0 longitute    1 latitute    2 sampler 3 date
		Cursor cur = db.rawQuery("select RouteNo from tblRoute where CountyName = '"+county+"'", null);
	
		Log.v("tag","county: "+county);
		//Log.v("tag","sql: "+sql);
        return cur;
	}
	
	public static Cursor queryCountyRouteSuffix(SQLiteDatabase db, String county,String Route) {
		// 0 longitute    1 latitute    2 sampler 3 date
		Cursor cur = db.rawQuery("select RouteSuffix from tblRoute where CountyName = '"+county+"'"+"and RouteNo = '"+Route+"'", null);
	
		Log.v("tag","county: "+county);
		Log.v("tag","RouteNo: "+Route);
		//Log.v("tag","sql: "+sql);
        return cur;
	}
	
	
	/**
	 * 当检测与前一次创建数据库版本不一样时，先删除表再创建新表
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
		onCreate(db);
	}
	
	/**
	 * 变更列名
	 * @param db
	 * @param oldColumn
	 * @param newColumn
	 * @param typeColumn
	 */
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){
		try{
			db.execSQL("ALTER TABLE " +
					TB_NAME + " CHANGE " +
					oldColumn + " "+ newColumn +
					" " + typeColumn
			);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// 返回一个List,List中的对象是以sql中的列的小写形式为key的Map.(本例中 _id,name,age为key)
	public Cursor queryAllLocation(SQLiteDatabase db) {
		// 0 longitute    1 latitute    2 sampler 3 date
		String sql = "select longitute,latitute,sampler,core_date from core";
		
         Cursor cur=db.query("core", new String[] {"longitute","latitute","sampler","core_date"}, null, null, null,null, null);                       
         return cur;
	}

}
