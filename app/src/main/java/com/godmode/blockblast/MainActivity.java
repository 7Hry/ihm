package com.godmode.blockblast;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.*;
import android.graphics.Color;

public class MainActivity extends Activity {
    private static final int REQ_CAPTURE = 44;
    private TextView status;
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout root = new LinearLayout(this); root.setOrientation(1); root.setPadding(24,32,24,24);
        root.setBackgroundColor(Color.rgb(18,22,32));
        TextView title = new TextView(this); title.setText("BLOCK BLAST\nGOD MODE SUPREMO"); title.setTextColor(Color.WHITE); title.setTextSize(25); title.setGravity(Gravity.CENTER); title.setPadding(0,0,0,20); root.addView(title);
        TextView info = new TextView(this); info.setText("Assistente de jogadas • Galaxy A16 / Android 16"); info.setTextColor(Color.LTGRAY); info.setGravity(Gravity.CENTER); root.addView(info);
        Button overlay = button("1. Ativar permissão de sobreposição"); root.addView(overlay);
        overlay.setOnClickListener(v -> { if (!Settings.canDrawOverlays(this)) startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()))); else startService(new Intent(this,OverlayService.class)); });
        Button capture = button("2. Autorizar captura de tela"); root.addView(capture);
        capture.setOnClickListener(v -> { MediaProjectionManager m=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE); startActivityForResult(m.createScreenCaptureIntent(),REQ_CAPTURE); });
        Button solver = button("3. Abrir solucionador de tabuleiro"); root.addView(solver);
        solver.setOnClickListener(v -> startActivity(new Intent(this,SolverActivity.class)));
        status = new TextView(this); status.setTextColor(Color.YELLOW); status.setPadding(0,24,0,0); status.setText("Estado: configure as permissões. A leitura visual automática ainda não está integrada."); root.addView(status);
        setContentView(root);
    }
    private Button button(String s){ Button b=new Button(this); b.setText(s); return b; }
    @Override protected void onActivityResult(int r,int c,Intent d){ super.onActivityResult(r,c,d); if(r==REQ_CAPTURE && c==RESULT_OK && d!=null){ Intent i=new Intent(this,CaptureService.class); i.putExtra("code",c); i.putExtra("data",d); startForegroundService(i); status.setText("Captura autorizada. O reconhecimento automático ainda requer calibração."); } }
}
