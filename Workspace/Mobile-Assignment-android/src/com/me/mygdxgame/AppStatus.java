package com.me.mygdxgame;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AppStatus {
	private int version = Build.VERSION.SDK_INT;
	private static AppStatus instance = new AppStatus();
	static Context context;
	ConnectivityManager connectivityManager;
	NetworkInfo wifiInfo, mobileInfor;
	boolean connected = false;

	public static AppStatus getInstance(Context context) {
		AppStatus.context = context;
		return instance;
	}

	public boolean isOnline(Context context) {
		try {
			connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager
					.getActiveNetworkInfo();
			connected = networkInfo != null && networkInfo.isAvailable()
					&& networkInfo.isConnected();
			return connected;
		} catch (Exception e) {
			System.out.print("CheckConnectivity Exception: " + e.getMessage());
			Log.v("connectivity", e.toString());
		}
		return connected;
	}

	public boolean turn3GOn(Context context, boolean status) {
		try {
			if (version == Build.VERSION_CODES.FROYO) {
				Method dataConnSwitchMethod;
				Class<?> telephonyManagerClass;
				Object iTelephonyStub;
				Class<?> ITelephonyClass;

				TelephonyManager telephoneManager = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				telephonyManagerClass = Class.forName(telephoneManager
						.getClass().getName());
				Method getITelephonyMethod = telephonyManagerClass
						.getDeclaredMethod("getITelephony");
				getITelephonyMethod.setAccessible(true);
				iTelephonyStub = getITelephonyMethod.invoke(telephoneManager);
				ITelephonyClass = Class.forName(iTelephonyStub.getClass()
						.getName());

				if (status) {
					dataConnSwitchMethod = ITelephonyClass
							.getDeclaredMethod("enableDataConnectivity");
				} else {
					dataConnSwitchMethod = ITelephonyClass
							.getDeclaredMethod("disableDataConnectivity");
				}
				dataConnSwitchMethod.setAccessible(true);
				dataConnSwitchMethod.invoke(iTelephonyStub);
			} else {
				final Class<?> conmanClass = Class.forName(connectivityManager.getClass()
						.getName());
				final Field iConnectivityManagerField = conmanClass
						.getDeclaredField("mService");
				iConnectivityManagerField.setAccessible(true);
				final Object iConnectivityManager = iConnectivityManagerField
						.get(connectivityManager);
				final Class<?> iConnectivityManagerClass = Class
						.forName(iConnectivityManager.getClass().getName());
				final Method setMobileDataEnabledMethod = iConnectivityManagerClass
						.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
				setMobileDataEnabledMethod.setAccessible(true);
				setMobileDataEnabledMethod.invoke(iConnectivityManager, status);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}