package com.wg.daonile.app;

import java.io.File;

import android.app.Application;
import android.os.Environment;

import com.wg.common.util.cookie.PersistentCookieStore;
import com.wg.common.util.io.CrashHandler;
import com.wg.daonile.grim.utils.ConfigFile;

/**
 * application
 * 
 * @author ke.wei.quan
 * @date 2015年3月31日
 *
 */
public class DaoNiLeApp extends Application {

	private static DaoNiLeApp sAppInstance;

	/**
	 * 获取app当前实例
	 * 
	 * @return
	 */
	public static DaoNiLeApp getAppInstance() {
		return sAppInstance;
	}

	@Override
	public void onCreate() {
		sAppInstance = this;
		initOrCreateAppFileDir();
		initCrashHandler();
		super.onCreate();
	}
	
	private volatile static PersistentCookieStore persistentCookieStore;

	/**
	 * 实例化PersistentCookieStore
	 * @return
	 */
	public static PersistentCookieStore getPersistentCookie() {
		if (persistentCookieStore == null) {
			synchronized (PersistentCookieStore.class) {
				if (persistentCookieStore == null) {
					persistentCookieStore = new PersistentCookieStore(sAppInstance);
				}
			}
		}
		return persistentCookieStore;
	}
	

	private String appRootDir;// app的根文件夹
	private String picPath;// 图片缓存文件夹
	private String logPath;// 日志打印文件夹
	private String downloadPath;// 下载文件夹

	/**
	 * 初始化app的文件夹
	 */
	private void initOrCreateAppFileDir() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			appRootDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DaoNiLe/";
		} else {
			appRootDir = getApplicationContext().getFilesDir().getAbsolutePath() + "/DaoNiLe/";
		}

		/*
		 * 目录具体路径
		 */
		picPath = appRootDir + "pic/";
		logPath = appRootDir + ".log/";
		downloadPath = appRootDir + "download/";

		/**
		 * 开始创建文件夹
		 */
		createRootDir();
		createSubDir();
	}

	/**
	 * 创建根目录
	 */
	private void createRootDir() {
		File rootFile = new File(appRootDir);
		if (!rootFile.exists() && !rootFile.isDirectory()) {
			rootFile.mkdir();
		}
	}

	/**
	 * 创建子目录
	 */
	private void createSubDir() {
		File picFile = new File(picPath);
		if (!picFile.exists() && !picFile.isDirectory()) {
			picFile.mkdir();
		}

		File logFile = new File(logPath);
		if (!logFile.exists() && !logFile.isDirectory()) {
			logFile.mkdir();
		}

		File downloadFile = new File(downloadPath);
		if (!downloadFile.exists() && !downloadFile.isDirectory()) {
			downloadFile.mkdir();
		}
	}

	/**
	 * 获取app根目录
	 * 
	 * @return
	 */
	public String getRootDir() {
		return appRootDir;
	}

	/**
	 * 获取图片缓存目录
	 * 
	 * @return
	 */
	public String getPicDir() {
		return picPath;
	}

	/**
	 * 获取日志打印目录
	 * 
	 * @return
	 */
	public String getLogDir() {
		return logPath;
	}

	/**
	 * 获取下载目录
	 * 
	 * @return
	 */
	public String getDownloadDir() {
		return downloadPath;
	}

	/**
	 * 初始化全局捕获异常
	 */
	private void initCrashHandler() {
		if (ConfigFile.isCrashhandlerOpen()) {
			CrashHandler crashHandler = CrashHandler.getInstance();
			crashHandler.init(sAppInstance, logPath);
		}
	}

}
