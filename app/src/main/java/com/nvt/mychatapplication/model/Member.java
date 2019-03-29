package com.nvt.mychatapplication.model;

public class Member {
    public String id;
    public String name;
    public String position;
    public long avatarId;

    public Member() {
        this.id = "";
        this.name = "-";
        this.avatarId = 0;
        this.position = "";
    }

    public Member(String id, String name, String position, long avatarId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.avatarId = avatarId;
    }
}
