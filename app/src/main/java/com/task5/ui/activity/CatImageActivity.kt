package com.task5.ui.activity

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.task5.R
import com.task5.constants.IntentConstants
import com.task5.databinding.ActivityImageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_image.*
import javax.inject.Inject


@AndroidEntryPoint
class CatImageActivity : AppCompatActivity() {

    @field:[Inject]
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        supportPostponeEnterTransition()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(IntentConstants.CAT_EXTRA_URL)) {
            intent.getStringExtra(IntentConstants.CAT_EXTRA_URL)?.let { loadInto(it) }
        }
        tv_pinch.setOnClickListener {
            if (intent.getStringExtra(IntentConstants.CAT_EXTRA_URL) != null) {
                if (isWritePermissionGranted()) {
                    downloadImage(intent.getStringExtra(IntentConstants.CAT_EXTRA_URL)!!, this)
                    Toast.makeText(this,"This Picture Save To Gallery",Toast.LENGTH_LONG).show()
                } else {
                    askPermissions()
                }
            }
        }

    }


    private fun loadInto(url: String) {
        if (url.startsWith("http")) {
            imgViewCat.visibility = View.VISIBLE
            glide.load(url)
                .placeholder(R.drawable.ic_cat_half_transparent)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        p0: Drawable?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: DataSource?,
                        p4: Boolean
                    ): Boolean {
                        scheduleStartPostponedTransition(imgViewCat);
                        return false
                    }
                })
                .into(imgViewCat)
        } else {
            imgViewCat.visibility = View.GONE
            glide.clear(imgViewCat)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        supportFinishAfterTransition()
        onBackPressed()
        return true
    }

    private fun scheduleStartPostponedTransition(sharedElement: View) {
        sharedElement.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    sharedElement.viewTreeObserver.removeOnPreDrawListener(this)
                    startPostponedEnterTransition()
                    return true
                }
            })
    }

    private fun downloadImage(url: String, context: Context) {
        val filename = url.substringAfterLast("/")
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(filename)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
            .enqueue(request)
    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE),
            100
        )
    }

    private fun isWritePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}





