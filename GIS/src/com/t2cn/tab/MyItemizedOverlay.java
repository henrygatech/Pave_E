package com.t2cn.tab;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	private MapView map;

	public MyItemizedOverlay(Drawable defaultMarker, Context context,MapView mapview) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		map = mapview;
		
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
        
		OverlayItem item = mOverlays.get(index);
		map.getController().setCenter(mOverlays.get(index).getPoint());//设置地图中心
		map.getController().setZoom(15);//设置缩放级别
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
}