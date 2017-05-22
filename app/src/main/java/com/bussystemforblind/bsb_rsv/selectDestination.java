package com.bussystemforblind.bsb_rsv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.LinkedList;

public class selectDestination extends AppCompatActivity {

    BusAPI busAPI = new BusAPI();
    LinearLayout topLL;
    TextView topTV1;
    String tmp="";
    String routeId = "";
    LinkedList<String> stationList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_destination);

        /*상단바 디자인*/
        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Intent intent = getIntent();
        final String busNumber = intent.getStringExtra("busNumber");
        final String stationId = intent.getStringExtra("stationId");

        String busStopList = "";
        try {
            routeId = busAPI.getRouteId(busNumber);
            busStopList=busAPI.busStop(routeId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(busStopList.equals("")){
            busStopList = "";
        }
        topLL = (LinearLayout)findViewById(R.id.dynamicArea);

        tmp = "";
        int black = 0;
        int id=0;
        for(int i=0; i<busStopList.length(); i++){
            if(busStopList.substring(i,i+1).equals(",")==false){
                tmp = tmp+busStopList.substring(i,i+1);
            }
            else if(busStopList.substring(i,i+1).equals(",")){
                if(tmp.equals("")==false){
                    topTV1 = new TextView(selectDestination.this);

                    Context context = this;
                    float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, context.getResources().getDisplayMetrics());

                    topTV1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) px));
                    topTV1.setTextSize(30);
                    topTV1.setText(tmp);
                    topTV1.setTypeface(Typeface.MONOSPACE);
                    topTV1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    topTV1.setGravity(Gravity.CENTER_VERTICAL);
                    topTV1.setId(id);
                    stationList.add(tmp);
                    id++;
                    if(black==0){
                        topTV1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        topTV1.setTextColor(Color.parseColor("#333333"));
                        black=1;
                    }
                    else if(black==1){
                        topTV1.setBackgroundColor(Color.parseColor("#333333"));
                        topTV1.setTextColor(Color.parseColor("#ffffff"));
                        black=0;
                    }
                    topTV1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckRsv.class);
                            intent.putExtra("busNumber", busNumber);
                            intent.putExtra("Destination", stationList.get(v.getId()));
                            intent.putExtra("stationId", stationId);
                            intent.putExtra("routeId",routeId);
                            startActivity(intent);
                        }
                    });
                    topLL.addView(topTV1);
                    tmp="";
                }
            }
        }
    }
    public void onClick(View v) {
        v.setBackgroundColor(Color.parseColor("#fbe600"));
    }
}
