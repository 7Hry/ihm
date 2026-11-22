package com.godmode.blockblast;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import java.util.*;

public class SolverActivity extends Activity {
    EditText board;
    TextView result;
    @Override public void onCreate(Bundle b){ super.onCreate(b);
        LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setPadding(18,24,18,18); l.setBackgroundColor(Color.rgb(18,22,32));
        TextView h=new TextView(this); h.setText("SOLUCIONADOR 8×8"); h.setTextColor(Color.WHITE); h.setTextSize(22); h.setGravity(Gravity.CENTER); l.addView(h);
        TextView help=new TextView(this); help.setTextColor(Color.LTGRAY); help.setText("Digite 8 linhas com 8 caracteres. # = ocupada, . = vazia."); l.addView(help);
        board=new EditText(this); board.setTextColor(Color.WHITE); board.setHintTextColor(Color.GRAY); board.setGravity(Gravity.TOP); board.setMinLines(8); board.setText("........\n........\n........\n........\n........\n........\n........\n........"); l.addView(board);
        Button solve=new Button(this); solve.setText("Analisar espaços"); l.addView(solve);
        result=new TextView(this); result.setTextColor(Color.GREEN); result.setTextSize(16); result.setPadding(0,18,0,0); l.addView(result);
        solve.setOnClickListener(v->analyze()); setContentView(l);
    }
    void analyze(){
        String[] rows=board.getText().toString().trim().split("\\s+");
        if(rows.length!=8){result.setText("Erro: informe exatamente 8 linhas.");return;}
        int empty=0; boolean ok=true;
        for(String s:rows){ if(s.length()!=8){ok=false;break;} for(char c:s.toCharArray()){if(c=='.')empty++;else if(c!='#')ok=false;} }
        if(!ok){result.setText("Formato inválido. Use apenas # e . (8×8).");return;}
        int[] heights=new int[8]; int[] widths=new int[8];
        for(int c=0;c<8;c++){for(int r=0;r<8;r++)if(rows[r].charAt(c)=='#')heights[c]++; }
        for(int r=0;r<8;r++)for(int c=0;c<8;c++)if(rows[r].charAt(c)=='#')widths[r]++;
        result.setText("Tabuleiro válido: 64 casas\nCasas vazias: "+empty+"\nLinhas completas agora: "+completeRows(rows)+"\nColunas completas agora: "+completeCols(rows)+"\n\nPróxima etapa: reconhecimento das 3 peças e busca de jogadas.");
    }
    int completeRows(String[] a){int n=0;for(String s:a)if(!s.contains("."))n++;return n;}
    int completeCols(String[] a){int n=0;for(int c=0;c<8;c++){boolean full=true;for(int r=0;r<8;r++)if(a[r].charAt(c)=='.')full=false;if(full)n++;}return n;}
}
