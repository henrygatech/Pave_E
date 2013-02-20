package com.t2cn.tab;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/**
 * ʵ�ֶԱ�Ĵ��������¡������������
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	public static final String TB_NAME = "CORE";

	public SQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	/**
	 * �����±�
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
				TB_NAME + "(" +
				coreBean.FIELDID + " varchar not null," + 
				coreBean.LOCATION_ID + " varchar not null," + 
				coreBean.COREID + " varchar not null," + 
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
		String sql = "insert into core(fieldid,location_id,coreid,organization,sampler,core_date,road_description,direction,no_of_lane,longitute,latitute,retrieved_from,lane_location,material,diameter,length,underlying_material,pccthickness,cracking_type,cracking_direction,core_level,maxdepth,corecondition,comments) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		db.execSQL(sql, new Object[]{
				core.getFieldid(),core.getLocation_id(),core.getCoreid(),core.getOrgaization(),core.getSampler(),core.getDate(),core.getRoad_description(),core.getDirection(),core.getNo_of_lane(),core.getLongitute(),core.getLatitute(),core.getRetrieved_from(),core.getLane_location(),core.getMaterial(),core.getDiameter(),core.getLength(),core.getUnderlying_material(),core.getPccthickness(),core.getCracking_type(),core.getCracking_direction(),core.getCore_level(),core.getMaxdepth(),core.getCorecondition(),core.getComments()
		});
		//System.out.println("core.getPi_id() "+core.getFieldid());
		System.out.println("core.getLatitude() "+core.getLongitute());
		//System.out.println("core.getRoad() "+core.getLatitute());
		Log.v("Test Saving", sql);
	}
	
	
	/**
	 * �������ǰһ�δ������ݿ�汾��һ��ʱ����ɾ�����ٴ����±�
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
		onCreate(db);
	}
	
	/**
	 * �������
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
	
	// ����һ��List,List�еĶ�������sql�е��е�Сд��ʽΪkey��Map.(������ _id,name,ageΪkey)
	public Cursor queryAllLocation(SQLiteDatabase db) {
		String sql = "select longitute,latitute from core";
		
         Cursor cur=db.query("core", new String[] {"longitute","latitute"}, null, null, null,null, null);                       
         return cur;
	}

}
