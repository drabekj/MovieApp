package com.strvacademy.drabekj.moviestrv.utils

import android.util.Log
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

	fun loadSecret() {
		if (KeystoreCompat.hasSecretLoadable()) {
			KeystoreCompat.loadSecretAsString(
					{
						secret -> Logcat.d("secret loaded successfully")
					},
					{
						exception ->
						Logcat.e("secret load failed: " + Log.getStackTraceString(exception))
					},
					false)
		} else
			throw IllegalAccessException("No loadable secret stored")
	}
}