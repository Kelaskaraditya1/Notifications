package com.starkindustries.notification
import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.notification.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val CHANNEL_ID:String="channelId"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        var drawable=ResourcesCompat.getDrawable(resources,R.drawable.batman,null)
        var bitmapDrawable: BitmapDrawable = drawable as BitmapDrawable
        var bitmap:Bitmap = bitmapDrawable.bitmap
        var manager:NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notification:Notification = Notification.Builder(this)
            .setLargeIcon(bitmap)
            .setSmallIcon(R.drawable.batman)
            .setSubText("From Batman")
            .setContentText("I am Batman")
            .setChannelId(CHANNEL_ID)
            .build()
        manager.createNotificationChannel(NotificationChannel(CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_HIGH))
        binding.notification.setOnClickListener()
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)==PackageManager.PERMISSION_GRANTED)
                manager.notify(101,notification)
            else
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),102)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}