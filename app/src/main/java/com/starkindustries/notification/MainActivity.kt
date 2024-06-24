package com.starkindustries.notification
import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.starkindustries.notification.NotificationChannels.App
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
        binding.highButton.setOnClickListener()
        {
            var view = this.currentFocus
            if(view!=null)
            {
                var manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
            var notification = NotificationCompat.Builder(this,App().CHANNER_ID_1)
            notification.setContentTitle(binding.title.text.toString().trim())
            notification.setContentText(binding.content.text.toString().trim())
            notification.setSmallIcon(R.drawable.batman)
            notification.setAutoCancel(true)
            notification.setColor(Color.RED)
            notification.addAction(R.drawable.batman,"back",null)
            notification.addAction(R.drawable.batman,"play",null)
            notification.addAction(R.drawable.batman,"next",null)
            notification.setCategory(NotificationCompat.CATEGORY_MESSAGE)
            notification.setOnlyAlertOnce(true)
            notification.setPriority(NotificationCompat.PRIORITY_HIGH).build()
            var manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1,notification.build())

        }
        binding.lowButton.setOnClickListener()
        {
            var view = this.currentFocus
            if(view!=null)
            {
                var manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(view.windowToken,0)
            }
            var manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            var notification = NotificationCompat.Builder(this,App().CHANNEL_ID_2)
            notification.setContentTitle(binding.title.text.toString().trim())
            notification.setContentText(binding.content.text.toString().trim())
            notification.setSmallIcon(R.drawable.batman)
            notification.setPriority(NotificationCompat.PRIORITY_LOW).build()
            manager.notify(2,notification.build())
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}