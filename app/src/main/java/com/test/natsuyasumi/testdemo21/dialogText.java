package com.test.natsuyasumi.testdemo21;

import java.io.Serializable;

public class dialogText implements Serializable {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getToroad() {
        return toroadA;
    }

    public void setToroad(String toroad) {
        this.toroadA = toroad;
    }
    public String text;
    public String type;

    public String getTextA() {
        return textA;
    }

    public void setTextA(String textA) {
        this.textA = textA;
    }

    public String getTextB() {
        return textB;
    }

    public void setTextB(String textB) {
        this.textB = textB;
    }

    public String getToroadA() {
        return toroadA;
    }

    public void setToroadA(String toroadA) {
        this.toroadA = toroadA;
    }

    public String getToroadB() {
        return toroadB;
    }

    public void setToroadB(String toroadB) {
        this.toroadB = toroadB;
    }

    public String textA;
    public String textB;
    public String road;
    public String toroadA;
    public String toroadB;
    public String buttonBId;



    public String getButtonBId() {
        return buttonBId;
    }

    public void setButtonBId(String buttonBId) {
        this.buttonBId = buttonBId;
    }

    public String getButtonAId() {
        return buttonAId;
    }

    public void setButtonAId(String buttonAId) {
        this.buttonAId = buttonAId;
    }

    public String buttonAId;

}
