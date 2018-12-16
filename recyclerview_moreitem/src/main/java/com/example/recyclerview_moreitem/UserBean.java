package com.example.recyclerview_moreitem;

public class UserBean {
    private int type;
    private String title;
    private int image;

    /*public UserBean(int type, String title, int image) {
        this.type = type;
        this.title = title;
        this.image = image;
    }
*/
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
