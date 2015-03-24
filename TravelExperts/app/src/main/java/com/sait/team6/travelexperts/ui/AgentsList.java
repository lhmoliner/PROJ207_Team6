package com.sait.team6.travelexperts.ui;

import com.sait.team6.travelexperts.classes.Agent;

import java.util.ArrayList;

/**
 * Created by 677958 on 3/23/2015.
 */
public class AgentsList {
    private ArrayList<Agent> mAgentsList;

    public AgentsList() {
        mAgentsList = new ArrayList<Agent>();
    }

    public int addAgent(Agent agent) {
        mAgentsList.add(agent);
        return mAgentsList.size();
    }
}
