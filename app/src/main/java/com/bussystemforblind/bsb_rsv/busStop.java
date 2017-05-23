package com.bussystemforblind.bsb_rsv;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class busStop extends AppCompatActivity {
    LinearLayout linearLayout;
    String busNumber, stationId, routeId, dtnStation, busId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);

        try {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Toast toast2 = Toast.makeText(getApplicationContext(), "버스요금 결제가 완료되었습니다.", Toast.LENGTH_LONG);
        toast2.show();

        Intent intent = getIntent();
        busNumber = intent.getStringExtra("busNumber");
        stationId = intent.getStringExtra("stationId");
        routeId = intent.getStringExtra("routeId");
        dtnStation = intent.getStringExtra("Destination");
        busId = intent.getStringExtra("busId");

        /*
        목적지 선택시 목적지의 정류장 순번을 받아온다.
        routeId 와 busId로 현재 버스의 정류장 순번을 알아낸다.
        목적지 정류장 순번과 현재 버스가 위치한 정류장 순번을 비교하여 목적지까지 몇정류장 남았는지 계산->5초
        전정류장위치시 서버에 전달
        해당 정류장에 정차시 서버에 하차 메세지 전달
        */

        linearLayout = (LinearLayout)findViewById(R.id.activity_bus_stop);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), busStop2.class);
                startActivity(intent1);
            }
        });
    }
}
