package com.tictactoe.fb_midterm;

public class ArticleList {
    public String articleName;
    public String imageUrl;

    public ArticleList(){

    }

    public ArticleList(String articleName, String imageUrl) {
        this.articleName = articleName;
        this.imageUrl = imageUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
