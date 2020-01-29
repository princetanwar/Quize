package com.loveinshayari.quize;

public class QusetionModel {
    private String Qusetion,optionA,optionB,optionC,optionD,correctAns;

    public QusetionModel(String qusetion, String optionA, String optionB, String optionC, String optionD, String correctAns) {
        Qusetion = qusetion;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
    }

    public String getQusetion() {
        return Qusetion;
    }

    public void setQusetion(String qusetion) {
        Qusetion = qusetion;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
}
