package com.strvacademy.drabekj.moviestrv;

import android.app.Application;
import android.content.Context;

import org.alfonz.utility.Logcat;


public class MoviesApplication extends Application {
	private static MoviesApplication sInstance;


	public MoviesApplication() {
		sInstance = this;
	}


	public static Context getContext() {
		return sInstance;
	}


	@Override
	public void onCreate() {
		super.onCreate();

		// init logcat
		Logcat.init(MoviesConfig.LOGS, "MoviesApp");
	}
}