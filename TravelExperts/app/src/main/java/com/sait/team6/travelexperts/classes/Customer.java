package com.sait.team6.travelexperts.classes;

/*
 * Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Joel D'Arnot
 * Date: March/23/2015
 *
 */

/* customer tables:
 * CUSTOMERID
 * CUSTFIRSTNAME
 * CUSTLASTNAME
 * CUSTCITY
 * CUSTPOSTAL
 * CUSTCOUNTRY
 * CUSTHOMEPHONE
 * CUSTBUSPHONE
 * CUSTEMAIL
 */

// Customer class with listed class variables
public class Customer {
    private int customerid;
    private String custfirstname;
    private String custlastname;
    private String custcity;
    private String custpostal;
    private String custcountry;
    private String custhomephone;
    private String custbusphone;
    private String custemail;

    // constructor accepting all fields as arguments
    public Customer(int customerid, String custfirstname, String custlastname, String custcity, String custpostal, String custcountry, String custhomephone, String custbusphone, String custemail) {
        this.customerid = customerid;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custcity = custcity;
        this.custpostal = custpostal;
        this.custcountry = custcountry;
        this.custhomephone = custhomephone;
        this.custbusphone = custbusphone;
        this.custemail = custemail;
    }
    // getters and setters
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getCustfirstname() {
        return custfirstname;
    }

    public void setCustfirstname(String custfirstname) {
        this.custfirstname = custfirstname;
    }

    public String getCustlastname() {
        return custlastname;
    }

    public void setCustlastname(String custlastname) {
        this.custlastname = custlastname;
    }

    public String getCustcity() {
        return custcity;
    }

    public void setCustcity(String custcity) {
        this.custcity = custcity;
    }

    public String getCustpostal() {
        return custpostal;
    }

    public void setCustpostal(String custpostal) {
        this.custpostal = custpostal;
    }

    public String getCustcountry() {
        return custcountry;
    }

    public void setCustcountry(String custcountry) {
        this.custcountry = custcountry;
    }

    public String getCusthomephone() {
        return custhomephone;
    }

    public void setCusthomephone(String custhomephone) {
        this.custhomephone = custhomephone;
    }

    public String getCustbusphone() {
        return custbusphone;
    }

    public void setCustbusphone(String custbusphone) {
        this.custbusphone = custbusphone;
    }

    public String getCustemail() {
        return custemail;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }
}
