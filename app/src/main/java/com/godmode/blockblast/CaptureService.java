package com.godmode.blockblast;

import android.app.*;
import android.content.*;
import android.media.projection.*;
import android.os.*;
import android.util.Log;

public class CaptureService extends Service {
    private MediaProjection projection;
    @Override public int onStartCommand(Intent intent,int flags,int startId){
        if(Build.VERSION.SDK_INT>=26){NotificationChannel ch=new NotificationChannel("capture","Captura de tela",NotificationManager.IMPORTANCE_LOW); getSystemService(NotificationManager.class).createNotificationChannel(ch); Notification n=new Notification.Builder(this,"capture").setContentTitle("BLOCK BLAST GOD MODE").setContentText("Captura autorizada").setSmallIcon(android.R.drawable.ic_menu_view).build(); startForeground(7,n);}
        if(intent!=null){int code=intent.getIntExtra("code",0); Intent data=intent.getParcelableExtra("data"); MediaProjectionManager m=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE); if(data!=null) projection=m.getMediaProjection(code,data);}
        return START_NOT_STICKY;
    }
    @Override public void onDestroy(){if(projection!=null)projection.stop();super.onDestroy();}
    @Override public IBinder onBind(Intent i){return null;}
}
