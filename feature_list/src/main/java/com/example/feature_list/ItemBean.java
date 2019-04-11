package com.example.feature_list;

public class ItemBean {
    private String Title;

    public ItemBean(String Title){
        this.Title = Title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "Title='" + Title + '\'' +
                '}';
    }
}
