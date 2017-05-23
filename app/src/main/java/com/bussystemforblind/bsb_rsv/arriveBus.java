package com.bussystemforblind.bsb_rsv;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class arriveBus extends AppCompatActivity {

    String busNumber, stationId, routeId, dtnStation, busId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_bus);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyLog().build());

        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Intent intent = getIntent();
        busNumber = intent.getStringExtra("busNumber");
        stationId = intent.getStringExtra("stationId");
        routeId = intent.getStringExtra("routeId");
        dtnStation = intent.getStringExtra("Destination");
        busId = intent.getStringExtra("busId");



        /*비콘으로 버스와 나의 거리를 측정하여 탑승여부 확인*/

        /*서버에 결제정보 전송*/
        SocketManager manager = SocketManager.getManager();
        //manager.sendMsg("2-"+busId+"-"+stationId);
        manager.sendMsg("8-T-0101045445715584-05/21-56712");
        String msg = manager.getMsg();
        Log.d("MSG",msg);

        /*결제정보 전송 확인후 다음 페이지로 이동*/
        if(msg.equals("1")){
            /*
            Toast toast2 = Toast.makeText(getApplicationContext(), "버스요금 결제가 완료되었습니다.", Toast.LENGTH_LONG);
            toast2.show();
            */
            Intent intent1 = new Intent(getApplicationContext(), busStop.class);
            intent1.putExtra("busNumber", busNumber);
            intent1.putExtra("Destination", dtnStation);
            intent1.putExtra("stationId", stationId);
            intent1.putExtra("routeId",routeId);
            intent1.putExtra("busId", busId);
            startActivity(intent1);
        }
    }
}
