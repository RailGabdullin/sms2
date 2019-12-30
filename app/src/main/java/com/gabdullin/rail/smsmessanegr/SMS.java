package com.gabdullin.rail.smsmessanegr;

public class SMS {
    private String from;
    private String text;
    private String date;
    private boolean mine;

    public SMS(String from, String text, String date, boolean mine) {
        this.from = from;
        this.text = text;
        this.date = date;
        this.mine = mine;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public boolean isMine() {
        return mine;
    }
}
