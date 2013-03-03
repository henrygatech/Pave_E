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

public class CoreActivity extends Activity{

	private static final String[] locSpinner=  {"loc1","loc2","loc3","loc4","other"}; 
	private static final String[] roadSpinner={"road1","road2","road3","road4","other"};  
	private static final String[] dateSpinner={"date1","date2","date3","date4","other"};  
	
	private Spinner Locationspinner;
	//private Spinner Locationspinner;  
	private Spinner Roadspinner;  
	private Spinner Datespinner;  
	
	private ArrayAdapter<String> locadapter;  
	private ArrayAdapter<String> roadadapter;  
	private ArrayAdapter<String> dateadapter;  
	
	protected void onCreate(Bundle savedInstanceState) 
	{  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.core);  
        //view = (TextView) findViewById(R.id.spinnerText);  
        Locationspinner = (Spinner) findViewById(R.id.whichLocation);  
        Roadspinner = (Spinner) findViewById(R.id.road); 
        Datespinner = (Spinner) findViewById(R.id.date); 
        
        //����ѡ������ArrayAdapter��������  
        locadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,locSpinner);  
        roadadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,roadSpinner);  
        dateadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dateSpinner);  
        //���������б�ķ��  
        locadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        roadadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        dateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        //��adapter ��ӵ�spinner��  
        Locationspinner.setAdapter(locadapter);  
        Roadspinner.setAdapter(roadadapter);  
        Datespinner.setAdapter(dateadapter);  
          
        //����¼�Spinner�¼�����    
        Locationspinner.setOnItemSelectedListener(new LocSpinnerSelectedListener());
        Roadspinner.setOnItemSelectedListener(new LocSpinnerSelectedListener());
        Datespinner.setOnItemSelectedListener(new LocSpinnerSelectedListener());
          
        //����Ĭ��ֵ  
        Locationspinner.setVisibility(View.VISIBLE);  
        Roadspinner.setVisibility(View.VISIBLE);  
        Datespinner.setVisibility(View.VISIBLE);  
        
		
	}
	
    //ʹ��������ʽ����  
    class LocSpinnerSelectedListener implements OnItemSelectedListener{  
  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            //view.setText("���Ѫ���ǣ�"+m[arg2]);  
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
        }  
    }  
    
    class RoadSpinnerSelectedListener implements OnItemSelectedListener{  
    	  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            //view.setText("���Ѫ���ǣ�"+m[arg2]);  
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
        }  
    }  
    
    class DateSpinnerSelectedListener implements OnItemSelectedListener{  
  	  
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) {  
            //view.setText("���Ѫ���ǣ�"+m[arg2]);  
        }  
  
        public void onNothingSelected(AdapterView<?> arg0) {  
        }  
    }  


}