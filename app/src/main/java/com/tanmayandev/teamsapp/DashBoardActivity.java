package com.tanmayandev.teamsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashBoardActivity extends AppCompatActivity {

    EditText secretCodeBox ;
    TextView joinBtn , shareBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        secretCodeBox = findViewById(R.id.secretCode) ;
        joinBtn = findViewById(R.id.joinBtn) ;
        shareBtn = findViewById(R.id.shareBtn) ;

        URL serverURL ;

        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOption = new JitsiMeetConferenceOptions
                    .Builder().setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build() ;
            JitsiMeet.setDefaultConferenceOptions(defaultOption);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build() ;

                JitsiMeetActivity.launch(DashBoardActivity.this , options);
            }
        });




    }
}