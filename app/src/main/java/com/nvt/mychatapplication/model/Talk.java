package com.nvt.mychatapplication.model;

public class Talk {
    public String time;
    public String chatName;
    public long avatarId;

    public Talk() {
        this.time = "";
        this.chatName = "-";
        this.avatarId = 0;
    }

    public Talk(String time, String chatName, long avatarId) {
        this.time = time;
        this.chatName = chatName;
        this.avatarId = avatarId;
    }
}
