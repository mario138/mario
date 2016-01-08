package com.lenove.agri.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenove.agri.MyApplication;
import com.lenove.agri.R;
import com.lenove.agri.request.FindPwdRequest;
import com.lenove.agri.thread.RequestThread;

public class FindPwdDialog extends Dialog {

	public static final String TAG = "FindPwdDialog";

	private EditText userNameView;
	private Button button;
	private Button canButton;
	private MyHandler mhandler;

	private ProgressDialog findDialog;

	public FindPwdDialog(Context context) {
		super(context, R.style.dialog_style);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_forgetpass);
		setCanceledOnTouchOutside(false);
		mhandler = new MyHandler();

		init();
	}

	private void init() {
		userNameView = (EditText) findViewById(R.id.forgetpass_name);
		button = (Button) findViewById(R.id.forgetpass_confirm);
		canButton = (Button) findViewById(R.id.forgetpass_cancel);
		canButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancel();
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			String userName = "";

			@Override
			public void onClick(View arg0) {
				userName = userNameView.getText().toString().trim();
				if (TextUtils.isEmpty(userName)) {
					Toast.makeText(getContext(), "用户名不能为空", Toast.LENGTH_SHORT)
							.show();
				} else {
					findPassWorld(userName);
				}
			}

		});
	}

	// 请求忘记密码
	private void findPassWorld(String userName2) {

		findDialog = new ProgressDialog(getContext());
		findDialog.setMessage("正在请求...");
		findDialog.show();

		if (MyApplication.handler == null) {
			MyApplication.handler = mhandler;
		} else {
			if (MyApplication.handler != mhandler) {
				MyApplication.handler = null;
				MyApplication.handler = mhandler;
			}
		}

		FindPwdRequest request = new FindPwdRequest(userName2);

		Log.i(TAG,
				"找回密码请求：" + request.getUrl() + ",body=" + request.requestJson());

		RequestThread thread = new RequestThread(request);
		thread.start();
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
						Toast.makeText(getContext(), "您的密码已发送至你注册的邮箱，请到邮箱确认！",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getContext(), "获取密码失败！",
								Toast.LENGTH_SHORT).show();
					}
					findDialog.cancel();
					cancel();
				}
			}
		}
	}

}
