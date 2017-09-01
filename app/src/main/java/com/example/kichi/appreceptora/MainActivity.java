package com.example.kichi.appreceptora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(Intent.ACTION_SEND.equals(action) && type != null){
            if("text/plain".equals(type)){
                manipularTexto(intent);
            }
        }
    }

    public void manipularTexto(Intent intent){
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if(sharedText != null){

            TextView[] txts  = new TextView[6];
            txts[0]= (TextView)findViewById(R.id.txt1);
            txts[1]= (TextView)findViewById(R.id.txt2);
            txts[2]= (TextView)findViewById(R.id.txt3);
            txts[3]= (TextView)findViewById(R.id.txt4);
            txts[4]= (TextView)findViewById(R.id.txt5);
            txts[5]= (TextView)findViewById(R.id.txt6);

            TextView[] txtps  = new TextView[6];
            txtps[0]= (TextView)findViewById(R.id.txtp1);
            txtps[1]= (TextView)findViewById(R.id.txtp2);
            txtps[2]= (TextView)findViewById(R.id.txtp3);
            txtps[3]= (TextView)findViewById(R.id.txtp4);
            txtps[4]= (TextView)findViewById(R.id.txtp5);
            txtps[5]= (TextView)findViewById(R.id.txtp6);

            TextView txtTotal;

            Gson gson = new Gson();
            ArrayList<String> obj = gson.fromJson(sharedText, ArrayList.class);

            double total = 0;
            int a=0,b=0;
            for(int x=0;x < obj.size();x++) {
                if(x%2==0){
                    txts[a].setText(obj.get(x));
                    a++;
                }else{
                    txtps[b].setText(obj.get(x));
                    b++;
                    total = Double.parseDouble(obj.get(x)) + total;
                }
            }
            txtTotal = (TextView) findViewById(R.id.TxtTotal);
            txtTotal.setText("TOTAL A PAGAR : "+String.valueOf(total));
        }
    }
}
