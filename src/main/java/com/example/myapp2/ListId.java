package com.example.myapp2;

import org.json.JSONObject;

public class ListId extends JSONObject {


    private int id;
    private int listId;
    private String name;


    public ListId(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public ListId() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ListId{" +
                "id=" + id +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }
}
