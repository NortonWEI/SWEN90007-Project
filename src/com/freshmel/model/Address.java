package com.freshmel.model;

/**
 * a Address class contain the basic information of address
 * */
public class Address {
    private String line1;
    private String line2;
    private String line3;
    private String suburb;
    private String state;
    private String postCode;


    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString(){
        return line1 + ", " + line2 + ", " + line3 + "\n" + suburb + ", " + state + ", " + postCode;
    }
}
