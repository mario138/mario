package com.lenove.agri.activity;

import com.lenove.agri.MyApplication;
import com.lenove.agri.request.BaseRequest;
import com.lenove.agri.server.GetSensorService;
import com.lenove.agri.thread.RequestThread;
import com.lenove.bean.Sensor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;

public abstract class BaseActivity extends Activity {

	public static final String TAG = "BaseActivity";

	protected MyApplication mAp;

	protected MyHandler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mAp = MyApplication.getInstance();
		mHandler = new MyHandler();

		if (MyApplication.handler != null && MyApplication.handler != mHandler) {
			MyApplication.handler = null;
		}
		MyApplication.handler = mHandler;

	}

	/**
	 * 开始请求数据
	 */
	protected void startRequest(BaseRequest request) {
		if (MyApplication.handler == null) {
			MyApplication.handler = mHandler;
		} else {
			if (MyApplication.handler != mHandler) {
				MyApplication.handler = null;
				MyApplication.handler = mHandler;
			}
		}
		RequestThread thread = new RequestThread(request);
		thread.start();
	}

	/**
	 * 其他请求
	 * 
	 * @param ob
	 */
	protected abstract void update(Object ob);

	/**
	 * 获取得到传感器值
	 * 
	 * @param sensor
	 */
	protected void sensorUpdate(Sensor sensor) {
	}

	@SuppressLint("HandlerLeak")
	protected class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if (msg.obj != null) {

				switch (msg.what) {
				case RequestThread.other_result:
					update(msg.obj);
					break;
				case GetSensorService.sensor_reuslt:
					sensorUpdate((Sensor) msg.obj);
					break;
				}
			} else {
				Log.w(TAG, "Hander 返回结果为 null...");
			}

		}
	}
}
