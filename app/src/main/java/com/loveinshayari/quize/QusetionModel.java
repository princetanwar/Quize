package com.loveinshayari.quize;

public class QusetionModel {
    private String qus,a,b,c,d,ans;
    private int setNo;

    public QusetionModel() {
        // for firebase
    }

    public QusetionModel(String qus, String a, String b, String c, String d, String ans, int setNo) {
        this.qus = qus;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.ans = ans;
        this.setNo = setNo;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }
}
