package com.t2cn.tab;

import android.app.Application;

public class appl extends Application {
	coreBean core = new coreBean();
	public coreBean getCore() {
		return core;
	}
	public void setCore(coreBean core) {
		this.core = core;
	}
}