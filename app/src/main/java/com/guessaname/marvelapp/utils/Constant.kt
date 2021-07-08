package com.guessaname.marvelapp.utils

import android.content.Context
import android.net.ConnectivityManager
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


class Constant {

    companion object {
        const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        const val AVENGERS_URL = "http://gateway.marvel.com/v1/public/events/29/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "6599151f190582a1330bf1ca426e61c9"
        const val PRIVATE_KEY = "98b0a1e29a8a13c70d410ed685373518c17c38a5"
        const val limit = "100"

        //moro KEY: 0c85fd3d8fbdce778dd8ee7010462f74
        //moro PRIVATE_KEY: bc8152c31d041304a01ff50d857af920d964b665

        //davide KEY: 6599151f190582a1330bf1ca426e61c9
        //davide PRIVATE_KEY: 98b0a1e29a8a13c70d410ed685373518c17c38a5

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
        }
    }


}