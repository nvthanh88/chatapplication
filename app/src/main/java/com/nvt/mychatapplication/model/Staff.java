package com.nvt.mychatapplication.model;

public class Staff {
    public String id;
    public String name;
    public String position;
    public long avatarId;

    public Staff() {
        this.id = "";
        this.name = "-";
        this.avatarId = 0;
        this.position = "";
    }

    public Staff(String id, String name,String position, long avatarId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.avatarId = avatarId;
    }
}
