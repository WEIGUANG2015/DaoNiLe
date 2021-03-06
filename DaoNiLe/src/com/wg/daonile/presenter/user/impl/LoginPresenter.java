package com.wg.daonile.presenter.user.impl;

import com.wg.daonile.activity.MainFragmentActivity;
import com.wg.daonile.eventbus.event.EfinishSelf;
import com.wg.daonile.model.user.ILoginInteractor;
import com.wg.daonile.model.user.ILoginRequestCallback;
import com.wg.daonile.model.user.impl.LoginInteractor;
import com.wg.daonile.presenter.user.ILoginPresenter;
import com.wg.daonile.view.user.ILoginView;

/**
 * 登录presenter
 * 
 * @author ke.wei.quan
 * @date 2015年4月1日
 *
 */
public class LoginPresenter implements ILoginPresenter, ILoginRequestCallback {
	private ILoginView mLoginView;
	private ILoginInteractor mILoginInteractor;

	public LoginPresenter(ILoginView loginView) {
		mLoginView = loginView;
		mILoginInteractor = new LoginInteractor();
	}

	@Override
	public void onLoginClickListener(String userKey, String password) {
		mLoginView.showProgressDialog();
		mILoginInteractor.onUserLoginListener(userKey, password, this);
	}

	@Override
	public void onLoginFail(String tips) {
		mLoginView.dissmissProgressDialog();
		mLoginView.showToast(tips);
	}

	@Override
	public void onLoginSuccess() {
		mLoginView.dissmissProgressDialog();
		mLoginView.publishFinishGuideViewEvent(new EfinishSelf());
		mLoginView.goToActivity(MainFragmentActivity.class);
		mLoginView.finish();
	}

	@Override
	public void onGoBackClickListener() {
		mLoginView.onGoBackClickListener();
	}
}
