package com.strvacademy.drabekj.moviestrv.utils

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import org.alfonz.utility.Logcat
import java.security.KeyPairGenerator
import java.security.KeyStore


object KeyStoreUtil {
	private val AndroidKeyStore = "AndroidKeyStore"
	private val KEY_ALIAS = "MovieAPP_TMDBAuthKey"
	private val ALGORITHM = "RSA"

	fun generateKey() {
		// Get reference to AndroidKeyStore and initialize it
		val keyStore = KeyStore.getInstance(AndroidKeyStore)
		keyStore.load(null)

		// generate key (Android API 23+)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			try {
				if (!keyStore.containsAlias(KEY_ALIAS)) {
					val spec = KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT).build()
					val generator = KeyPairGenerator.getInstance(ALGORITHM, AndroidKeyStore)
					generator.initialize(spec)

					val keyPair = generator.generateKeyPair()
				}
			} catch (e: Exception) {
				Toast.makeText(MoviesApplication.context, "Exception " + e.message + " occured", Toast.LENGTH_LONG).show()
				Logcat.e(Log.getStackTraceString(e))
			}
		}
	}

	fun logKeys() {
		val keyStore = KeyStore.getInstance(AndroidKeyStore)
		val keyAliases = arrayListOf<String>()

		try {
			val aliases = keyStore.aliases()
			while (aliases.hasMoreElements()) {
				keyAliases.add(aliases.nextElement())
			}
		} catch (e: Exception) {
		}

		for (item in keyAliases)
			Logcat.d("keyStore key: " + item)
	}
}