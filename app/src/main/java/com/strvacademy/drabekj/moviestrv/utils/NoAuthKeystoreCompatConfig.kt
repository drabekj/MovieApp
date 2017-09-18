package com.strvacademy.drabekj.moviestrv.utils

import cz.koto.keystorecompat.KeystoreCompatConfig

class NoAuthKeystoreCompatConfig : KeystoreCompatConfig() {

	override fun getUserAuthenticationRequired(): Boolean {
		return false
	}

}