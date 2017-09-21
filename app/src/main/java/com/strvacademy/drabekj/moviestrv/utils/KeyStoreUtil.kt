package com.strvacademy.drabekj.moviestrv.utils

import android.util.Log
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import cz.koto.keystorecompat.KeystoreCompat
import org.alfonz.utility.Logcat


object KeyStoreUtil {

	init {
		KeystoreCompat.overrideConfig(NoAuthKeystoreCompatConfig())
	}

	fun storeSecret(secret: String) {
		if (KeystoreCompat.isKeystoreCompatAvailable() && KeystoreCompat.isSecurityEnabled()) {
			KeystoreCompat.storeSecret(
					secret,
					{
						Logcat.e("Store secret failed!", it)
					},
					{
						Logcat.d("Secret stored.")
					})
		}
	}

	/**
	 * Initializes sessionID from encrypted value if available.
	 */
	fun loadSecret() {
		if (KeystoreCompat.hasSecretLoadable()) {
			KeystoreCompat.loadSecretAsString(
					{
						secret ->
						Logcat.d("secret loaded successfully")
						MoviesApplication.sessionID = secret
					},
					{
						exception ->
						Logcat.e("secret load failed: " + Log.getStackTraceString(exception))
					},
					false)
		}
	}

	/**
	 * Removes from file and sessionID key from variable
	 */
	fun clearSecret() {
		if (KeyStoreUtil.hasSecretLoadable()) {
			KeystoreCompat.clearCredentials()
			MoviesApplication.sessionID = null
			Logcat.d("secret cleared successfully")
		}
	}

	fun hasSecretLoadable(): Boolean {
		return KeystoreCompat.hasSecretLoadable()
	}
}