package com.ubaya160420092.a160420092_ubayakuliner.util

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya160420092.a160420092_ubayakuliner.R
import java.lang.Exception

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback{
            override fun onSuccess() {
                progressBar.visibility=View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })

}
