package com.strvacademy.drabekj.movieapp.ui.moviedetail;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.strvacademy.drabekj.movieapp.MoviesConfig;
import com.strvacademy.drabekj.movieapp.R;


/**
 * Sample activity showing how to properly enable custom fullscreen behavior.
 * <p>
 * This is the preferred way of handling fullscreen because the default fullscreen implementation
 * will cause re-buffering of the video.
 */
public class YouTubeFullscreenActivity extends YouTubeFailureRecoveryActivity implements
		View.OnClickListener,
		YouTubePlayer.OnFullscreenListener {

	private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
			? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
			: ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

	private LinearLayout baseLayout;
	private YouTubePlayerView playerView;
	private YouTubePlayer player;

	private boolean fullscreen;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fullscreen_youtube_player);
		baseLayout = (LinearLayout) findViewById(R.id.layout);
		playerView = (YouTubePlayerView) findViewById(R.id.player);

		playerView.initialize(MoviesConfig.YOUTUBE_API_KEY, this);

		doLayout();
	}


	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
										boolean wasRestored) {
		this.player = player;
		// Specify that we want to handle fullscreen behavior ourselves.
		player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
		player.setOnFullscreenListener(this);
		if(!wasRestored) {
			player.cueVideo("avP5d16wEp0");
		}
	}


	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return playerView;
	}


	@Override
	public void onClick(View v) {
		player.setFullscreen(!fullscreen);
	}


	private void doLayout() {
		LinearLayout.LayoutParams playerParams =
				(LinearLayout.LayoutParams) playerView.getLayoutParams();

		// When in fullscreen, the visibility of all other views than the player should be set to
		// GONE and the player should be laid out across the whole screen.
		playerParams.width = LayoutParams.MATCH_PARENT;
		playerParams.height = LayoutParams.MATCH_PARENT;
	}


	@Override
	public void onFullscreen(boolean isFullscreen) {
		fullscreen = isFullscreen;
		doLayout();
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		doLayout();
	}
}