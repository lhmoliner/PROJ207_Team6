package com.sait.team6.travelexperts.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sait.team6.travelexperts.R;

/**
 * Created by 677958 on 3/23/2015.
 */
public class AgentActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        TextView nameLabel = (TextView) findViewById(R.id.nameLabel);
        TextView agentIdLabel = (TextView) findViewById(R.id.idLabel);
        TextView phoneLabel = (TextView) findViewById(R.id.phoneLabel);
        TextView emailLabel = (TextView) findViewById(R.id.emailLabel);
        TextView positionLabel = (TextView) findViewById(R.id.positionLabel);
        TextView agencyIdLabel = (TextView) findViewById(R.id.agencyIdLabel);
        ImageView profileImage = (ImageView) findViewById(R.id.profileImage);


        Intent intent = getIntent();

        int agentId = intent.getIntExtra("agentId", 999);
        String agtFirstName = intent.getStringExtra("agtFirstName");
        String agtMiddleInitial = intent.getStringExtra("agtMiddleInitial");
        String agtLastName = intent.getStringExtra("agtLastName");
        String agtBusPhone = intent.getStringExtra("agtBusPhone");
        String agtEmail = intent.getStringExtra("agtEmail");
        String agtPosition = intent.getStringExtra("agtPosition");
        int agencyId = intent.getIntExtra("agencyId", 1);
        int profileInt = intent.getIntExtra("agtProfile", 0);

        nameLabel.setText(agtFirstName + " " + agtMiddleInitial + " " + agtLastName);
        agentIdLabel.setText("Agent ID: " + agentId +"");
        phoneLabel.setText("Phone: " + agtBusPhone);
        emailLabel.setText("Email: " + agtEmail);
        positionLabel.setText("Position: " + agtPosition);
        agencyIdLabel.setText("Agency: "+ agencyId + "");
        profileImage.setImageResource(profileInt);
    }

    @Override
    public void onClick(View v) {
        // get the intent
        Intent intent = getIntent();

        // get the Uri for the link on future website --- no implemented yet
        String link = intent.getStringExtra("link");
        Uri viewUri = Uri.parse(link);

        // create the intent and start it
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, viewUri);
        startActivity(viewIntent);
    }
}
