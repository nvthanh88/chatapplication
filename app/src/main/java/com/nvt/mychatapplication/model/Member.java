package com.nvt.mychatapplication.model;

public class Member {
    public String id;
    public String name;
    public String position;
    public long avatarId;
    public boolean isChoose;

    public Member() {
        this.id = "";
        this.name = "-";
        this.avatarId = 0;
        this.position = "";
        isChoose = false;
    }
    public Member(String id, String name, String position, long avatarId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.avatarId = avatarId;
        this.isChoose = false;
    }

    public Member(String id, String name, String position, long avatarId, boolean isChoose) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.avatarId = avatarId;
        this.isChoose = isChoose;
    }

    public void setChoose(boolean isChoose){
        this.isChoose = isChoose;
    }
}
