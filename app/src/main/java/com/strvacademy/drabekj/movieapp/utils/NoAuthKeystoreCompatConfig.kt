package com.strvacademy.drabekj.movieapp.utils

import cz.koto.keystorecompat.KeystoreCompatConfig

class NoAuthKeystoreCompatConfig : KeystoreCompatConfig() {

	override fun getUserAuthenticationRequired(): Boolean {
		return false
	}

}