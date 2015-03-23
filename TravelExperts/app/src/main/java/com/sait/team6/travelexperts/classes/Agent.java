package com.sait.team6.travelexperts.classes;

import com.sait.team6.travelexperts.R;

import java.util.ArrayList;

/**
 * Created by 677958 on 3/23/2015.
 */
public class Agent {
    private int mAgentId;
    private String mAgtFirstName;
    private String mAgtMiddleInitial;
    private String mAgtLastName;
    private String mAgtBusPhone;
    private String mAgtEmail;
    private String mAgtPosition;
    private int mAgencyId;
    private String mProfilePic;

    public Agent(){}

    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName,
                 String agtBusPhone, String agtEmail, String agtPosition, int agencyId, String profile) {

        this.mAgentId = agentId;
        this.mAgtFirstName = agtFirstName;
        this.mAgtMiddleInitial = agtMiddleInitial;
        this.mAgtLastName = agtLastName;
        this.mAgtBusPhone = agtBusPhone;
        this.mAgtEmail = agtEmail;
        this.mAgtPosition = agtPosition;
        this.mAgencyId = agencyId;
        this.mProfilePic = profile;
    }

    public int getAgentId() {
        return mAgentId;
    }

    public void setAgentId(int agentId) {
        mAgentId = agentId;
    }

    public String getAgtFirstName() {
        return mAgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        mAgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return mAgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) { mAgtMiddleInitial = agtMiddleInitial; }

    public String getAgtLastName() {
        return mAgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        mAgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return mAgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        mAgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return mAgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        mAgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return mAgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        mAgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId) {
        mAgencyId = agencyId;
    }

    public int getProfileInt() {
        return getProfilePic(mProfilePic);
    }

    public static int getProfilePic(String iconString) {
        int iconId = R.mipmap.p1;

        if (iconString.equals("p1")) {
            iconId = R.mipmap.p1;
        }
        else if (iconString.equals("p2")) {
            iconId = R.mipmap.p2;
        }
        else if (iconString.equals("p3")) {
            iconId = R.mipmap.p3;
        }
        else if (iconString.equals("p4")) {
            iconId = R.mipmap.p4;
        }
        else if (iconString.equals("p5")) {
            iconId = R.mipmap.p5;
        }
        else if (iconString.equals("p6")) {
            iconId = R.mipmap.p6;
        }
        else if (iconString.equals("p7")) {
            iconId = R.mipmap.p7;
        }
        else if (iconString.equals("p8")) {
            iconId = R.mipmap.p8;
        }
        else if (iconString.equals("p9")) {
            iconId = R.mipmap.p9;
        }
        else if (iconString.equals("p10")) {
            iconId = R.mipmap.p10;
        }
        if (iconString.equals("p11")) {
            iconId = R.mipmap.p11;
        }
        else if (iconString.equals("p12")) {
            iconId = R.mipmap.p12;
        }
        else if (iconString.equals("p13")) {
            iconId = R.mipmap.p13;
        }
        else if (iconString.equals("p14")) {
            iconId = R.mipmap.p14;
        }
        else if (iconString.equals("p15")) {
            iconId = R.mipmap.p15;
        }
        else if (iconString.equals("p16")) {
            iconId = R.mipmap.p16;
        }
        return iconId;
    }

    private ArrayList<Agent> mAgentsList;

    public void addAgent(ArrayList<Agent> agentsList) {
        mAgentsList = agentsList;
    }

    public int addAgent(Agent agent) {
        mAgentsList.add(agent);
        return mAgentsList.size();
    }
}
