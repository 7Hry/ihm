package com.godmode.blockblast;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.*;
import android.provider.Settings;
import android.view.*;
import android.widget.*;

public class OverlayService extends Service {
    WindowManager wm; View panel;
    @Override public void onCreate(){super.onCreate(); if(!Settings.canDrawOverlays(this)){stopSelf();return;}
        wm=(WindowManager)getSystemService(WINDOW_SERVICE);
        TextView t=new TextView(this);t.setText("  GOD MODE  ");t.setTextColor(0xffffffff);t.setBackgroundColor(0xdd176b38);t.setPadding(18,12,18,12);
        int type=Build.VERSION.SDK_INT>=26?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:WindowManager.LayoutParams.TYPE_PHONE;
        WindowManager.LayoutParams p=new WindowManager.LayoutParams(-2,-2,type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,PixelFormat.TRANSLUCENT);
        p.gravity=Gravity.TOP|Gravity.START;p.x=30;p.y=180;
        t.setOnTouchListener(new View.OnTouchListener(){int sx,sy;float tx,ty;public boolean onTouch(View v,android.view.MotionEvent e){if(e.getAction()==0){sx=p.x;sy=p.y;tx=e.getRawX();ty=e.getRawY();return true;}if(e.getAction()==2){p.x=sx+(int)(e.getRawX()-tx);p.y=sy+(int)(e.getRawY()-ty);wm.updateViewLayout(t,p);return true;}return true;}});
        panel=t;wm.addView(panel,p);
    }
    @Override public void onDestroy(){if(wm!=null&&panel!=null)wm.removeView(panel);super.onDestroy();}
    @Override public IBinder onBind(Intent i){return null;}
}
