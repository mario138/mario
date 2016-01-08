package com.lenove.agri.fragment;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lenove.agri.MyApplication;
import com.lenove.agri.R;
import com.lenove.agri.activity.MainActivity;
import com.lenove.agri.activity.VideoActivity;
import com.lenove.agri.activity.WeatherActivity;
import com.lenove.agri.dialog.SensorSetDialog;
import com.lenove.agri.request.BaseRequest;
import com.lenove.agri.request.SetConfigRequest;
import com.lenove.agri.thread.RequestThread;
import com.lenove.agri.util.MyShareUtil;
import com.lenove.bean.Config;

public class SettingFragment extends Fragment implements OnClickListener {

	private static SettingFragment instance = null;

	private MyHandler mHandler;

	private View view;
	private TextView isNeedGuideView;
	private TextView languageView;
	private TextView controlView;
	private TextView airView;
	private TextView soilView;
	private TextView lightView;
	private TextView co2View;
	private TextView co2textview;

	private AlertDialog myAlertDialog;

	private TextView weather;

	private SettingFragment() {

	}

	public static SettingFragment getInstance() {
		if (instance == null) {
			instance = new SettingFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_setting, null);

		mHandler = new MyHandler();
		initView();
		return view;
	}

	private void initView() {
		isNeedGuideView = (TextView) view
				.findViewById(R.id.fragment_setting_isneedguide);
		isNeedGuideView.setOnClickListener(this);

		languageView = (TextView) view
				.findViewById(R.id.fragment_setting_language);
		languageView.setOnClickListener(this);

		controlView = (TextView) view
				.findViewById(R.id.fragment_setting_controlmodel);
		controlView.setOnClickListener(this);

		airView = (TextView) view.findViewById(R.id.fragment_setting_air);
		airView.setOnClickListener(this);

		soilView = (TextView) view.findViewById(R.id.fragment_setting_earth);
		soilView.setOnClickListener(this);

		lightView = (TextView) view.findViewById(R.id.fragment_setting_light);
		lightView.setOnClickListener(this);

		co2View = (TextView) view.findViewById(R.id.fragment_setting_co2);
		co2View.setOnClickListener(this);

		weather = (TextView) view.findViewById(R.id.fragemnt_setting_weather);

		co2textview = (TextView) view.findViewById(R.id.fragment_co2);
		co2textview.setOnClickListener(this);
		weather.setOnClickListener(this);
	}

	/**
	 * 开始请求返回数据
	 * 
	 * @param request
	 */
	private void startRequest(BaseRequest request) {
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.fragment_setting_isneedguide:
			showAlertDialog(1, "设置显示导航页", "是否设置导航页", "是", "否");
			break;
		case R.id.fragment_setting_language:
			Resources resources = getResources();
			Configuration configuration = resources.getConfiguration();
			DisplayMetrics displayMetrics = resources.getDisplayMetrics();
			if (configuration.locale.getCountry().equals("CN")) {
				configuration.locale = Locale.ENGLISH;

			} else {
				configuration.locale = Locale.SIMPLIFIED_CHINESE;
			}
			resources.updateConfiguration(configuration, displayMetrics);
			startActivity(new Intent(getActivity(), MainActivity.class));
			getActivity().finish();
			break;
		case R.id.fragment_setting_controlmodel:

			showAlertDialog(0, "设置控制方式", "选择控制方式", "手动", "自动");

			break;
		case R.id.fragment_setting_air:

			new SensorSetDialog(getActivity(), "空气",
					MyApplication.share.getMinAirTmp(),
					MyApplication.share.getMaxAirTmp(),
					MyApplication.share.getMinAirHum(),
					MyApplication.share.getMaxAirHum()).show();

			break;
		case R.id.fragment_setting_earth:

			new SensorSetDialog(getActivity(), "土壤",
					MyApplication.share.getMinSoilTmp(),
					MyApplication.share.getMaxSoilTmp(),
					MyApplication.share.getMinSoilHum(),
					MyApplication.share.getMaxSoilHum()).show();

			break;
		case R.id.fragment_setting_light:

			new SensorSetDialog(getActivity(), "光照",
					MyApplication.share.getMinLight(),
					MyApplication.share.getMaxLight()).show();

			break;
		case R.id.fragment_setting_co2:

			break;
		case R.id.fragemnt_setting_weather:
			startActivity(new Intent(getActivity(), WeatherActivity.class));
			break;
		case R.id.fragment_co2:
			startActivity(new Intent(getActivity(), VideoActivity.class));
			break;
		default:
			break;
		}
	}

	private void showAlertDialog(int what, String title, String message,
			String posStr, String negaStr) {
		MyOnClickListener listener = new MyOnClickListener(what);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton(negaStr, listener);
		builder.setPositiveButton(posStr, listener);
		myAlertDialog = builder.create();
		myAlertDialog.show();
	}

	private class MyOnClickListener implements DialogInterface.OnClickListener {

		private int what;

		public MyOnClickListener(int what) {
			// TODO Auto-generated constructor stub
			this.what = what;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			Config confi = null;

			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				if (what == 0) {
					confi = new Config();
					confi.setAutoControl(0);
					SetConfigRequest setRequest = new SetConfigRequest(confi);
					startRequest(setRequest);
				} else if (what == 1) {
					MyApplication.share.setShow(true);
					Toast.makeText(getActivity(), "设置成功！", Toast.LENGTH_SHORT)
							.show();
				}
				break;

			case DialogInterface.BUTTON_NEGATIVE:
				if (what == 0) {
					confi = new Config();
					confi.setAutoControl(1);
					SetConfigRequest setRequest = new SetConfigRequest(confi);
					startRequest(setRequest);
				} else if (what == 1) {
					MyApplication.share.setShow(false);
					Toast.makeText(getActivity(), "设置成功！", Toast.LENGTH_SHORT)
							.show();
				}
				break;
			}

			myAlertDialog.cancel();
		}
	}

	@SuppressLint("HandlerLeak")
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.obj != null) {
				if (msg.what == RequestThread.other_result) {
					if ((Boolean) msg.obj) {
						Toast.makeText(getActivity(), "设置成功！",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "设置失败！",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	}
}
