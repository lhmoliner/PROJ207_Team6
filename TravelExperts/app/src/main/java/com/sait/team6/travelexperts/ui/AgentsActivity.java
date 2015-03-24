package com.sait.team6.travelexperts.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sait.team6.travelexperts.R;
import com.sait.team6.travelexperts.classes.Agent;
import com.sait.team6.travelexperts.databases.AgentDB;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 677958 on 3/23/2015.
 */
public class AgentsActivity extends Activity
        implements AdapterView.OnItemClickListener {

    private ArrayList<Agent> agentsList;
    private AgentDB agentDB;

    private TextView titleTextView;
    private ListView itemsListView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);

        titleTextView = (TextView) findViewById(R.id.positionLabel);
        itemsListView = (ListView) findViewById(R.id.agentsListView);
        itemsListView.setOnItemClickListener(this);

        new DownloadFeed().execute();
    }

    private AgentsList loadData(){
        AgentsList agentsList1 = new AgentsList();
        agentDB = new AgentDB(AgentsActivity.this);

        ArrayList <Agent> agentList = agentDB.getAgents();
        for (Agent agt : agentList){
            agentsList1.addAgent(agt);
        }
        return agentsList1;
    }


    class DownloadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            loadData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            new ReadFeed().execute();
        }
    }

    class ReadFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            agentsList = agentDB.getAgents();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            AgentsActivity.this.updateDisplay();
        }
    }

    public void updateDisplay()
    {
        if (agentsList == null) {
            titleTextView.setText("Unable to get Agents information");
            return;
        }

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for (Agent item : agentsList) { //items before
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("AgtName", item.getAgtFirstName() + " "
                    + item.getAgtMiddleInitial() + " "
                    + item.getAgtLastName());
            map.put("AgtPosition", item.getAgtPosition());
            map.put("AgtProfile", item.getProfileInt()+"");
            data.add(map);
        }

        int resource = R.layout.agent_listview;
        String[] from = {"AgtName", "AgtPosition", "AgtProfile"};
        int[] to = {R.id.agentNameLabel, R.id.positionLabel, R.id.profileImage};

        SimpleAdapter adapter =
                new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Agent agent = agentDB.getAgent(position);
        Intent intent = new Intent(this, AgentActivity.class);

        intent.putExtra("agentId", agent.getAgentId());
        intent.putExtra("agtFirstName", agent.getAgtFirstName());
        intent.putExtra("agtMiddleInitial", agent.getAgtMiddleInitial());
        intent.putExtra("agtLastName", agent.getAgtLastName());
        intent.putExtra("agtBusPhone", agent.getAgtBusPhone());
        intent.putExtra("agtEmail", agent.getAgtEmail());
        intent.putExtra("agtPosition", agent.getAgtPosition());
        intent.putExtra("agencyId", agent.getAgencyId());
        intent.putExtra("agtProfile", agent.getProfileInt());
        intent.putExtra("agtProfile", agent.getProfileInt());

        this.startActivity(intent);
    }

}
