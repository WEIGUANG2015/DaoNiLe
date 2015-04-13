package com.wg.daonile.base;

import com.wg.daonile.grim.utils.ToastUtil;
import com.wg.daonile.grim.widget.ProgressDialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 所有的FragmentActivity的基类
 * 
 * @author WEIGUANG
 * @date 2015年3月31日
 * @lastModifyDate
 * @version 1.0
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWidgets();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		switch (level) {
		case TRIM_MEMORY_UI_HIDDEN: // UI全部不可见时，是否UI资源
			releaseUIResource(); 
			break;

		default:
			break;
		}
	}
	
	/**
	 * 土司提示
	 * 
	 * @param tips
	 */
	public void showToast(String tips) {
		ToastUtil.show(tips);
	}

	/**
	 * 土司提示
	 * 
	 * @param tips
	 */
	public void showToast(CharSequence tips) {
		ToastUtil.show(tips);
	}

	/**
	 * 土司提示
	 * 
	 * @param tips
	 */
	public void showToast(int tips) {
		ToastUtil.show(tips);
	}

	private ProgressDialog mProgressDialog;

	public void showProgressDialog() {
		mProgressDialog = new ProgressDialog(this);
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
	}

	public void dissmissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 初始化界面控件
	 */
	public abstract void initWidgets();
	
	/**
	 * 释放UI资源
	 */
	public abstract void releaseUIResource();
}