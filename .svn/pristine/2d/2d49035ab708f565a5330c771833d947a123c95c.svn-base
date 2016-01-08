package com.lenove.agri.dialog;

import com.lenove.agri.MyApplication;
import com.lenove.agri.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetIpDialog extends Dialog {

	private TextView closeTextView; // 关闭
	private EditText ipEdit1, ipEdit2, ipEdit3, ipEdit4;
	private Button confirmButton;

	public SetIpDialog(Context context) {
		super(context, R.style.dialog_style);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_setip);
		setCanceledOnTouchOutside(false);

		initView();
	}

	/**
	 * 初始化设置Ip Dialog
	 */
	private void initView() {
		// TODO Auto-generated method stub

		String[] ips = MyApplication.share.getIp().split("\\.");

		ipEdit1 = (EditText) this.findViewById(R.id.setip_ip1);
		ipEdit1.setText(ips[0]);

		ipEdit2 = (EditText) this.findViewById(R.id.setip_ip2);
		ipEdit2.setText(ips[1]);

		ipEdit3 = (EditText) this.findViewById(R.id.setip_ip3);
		ipEdit3.setText(ips[2]);

		ipEdit4 = (EditText) this.findViewById(R.id.setip_ip4);
		ipEdit4.setText(ips[3]);

		confirmButton = (Button) this.findViewById(R.id.setip_confirm);
		confirmButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				confirm();
			}
		});

		closeTextView = (TextView) this.findViewById(R.id.setip_close);
		closeTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cancel(); // 关闭对话框
			}
		});
	}

	/**
	 * 确定按钮
	 * 
	 * @param v
	 */
	private void confirm() {

		String ip1 = ipEdit1.getText().toString().trim();
		String ip2 = ipEdit2.getText().toString().trim();
		String ip3 = ipEdit3.getText().toString().trim();
		String ip4 = ipEdit4.getText().toString().trim();

		if (TextUtils.isEmpty(ip1) || TextUtils.isEmpty(ip2)
				|| TextUtils.isEmpty(ip3) || TextUtils.isEmpty(ip4)) {
			Toast.makeText(getContext(), "对不起，请补全IP地址！", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		MyApplication.share.setIp(ip1 + "." + ip2 + "." + ip3 + "." + ip4);
		Toast.makeText(getContext(),
				"设置成功！当前IP为：" + MyApplication.share.getIp(), Toast.LENGTH_SHORT)
				.show();
		cancel();
	}

}
