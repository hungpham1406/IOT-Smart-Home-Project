package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Post extends AbstractModel<Post>{

    private int post_id;
    private int post_category_id;
    private String post_title;
    private String post_author;
    private String post_user;
    private Date post_date;
    private String post_image;
    private String post_content;
    private String post_tags;
    private String post_comment_count;
    private String post_status;
    private int post_views_count;

    public Post() {
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPost_category_id() {
        return post_category_id;
    }

    public void setPost_category_id(int post_category_id) {
        this.post_category_id = post_category_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_author() {
        return post_author;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }

    public String getPost_user() {
        return post_user;
    }

    public void setPost_user(String post_user) {
        this.post_user = post_user;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_tags() {
        return post_tags;
    }

    public void setPost_tags(String post_tags) {
        this.post_tags = post_tags;
    }

    public String getPost_comment_count() {
        return post_comment_count;
    }

    public void setPost_comment_count(String post_comment_count) {
        this.post_comment_count = post_comment_count;
    }

    public String getPost_status() {
        return post_status;
    }

    public void setPost_status(String post_status) {
        this.post_status = post_status;
    }

    public int getPost_views_count() {
        return post_views_count;
    }

    public void setPost_views_count(int post_views_count) {
        this.post_views_count = post_views_count;
    }

    public Post(int post_id, int post_category_id, String post_title, String post_author, String post_user, Date post_date, String post_image, String post_content, String post_tags, String post_comment_count, String post_status, int post_views_count) {
        this.post_id = post_id;
        this.post_category_id = post_category_id;
        this.post_title = post_title;
        this.post_author = post_author;
        this.post_user = post_user;
        this.post_date = post_date;
        this.post_image = post_image;
        this.post_content = post_content;
        this.post_tags = post_tags;
        this.post_comment_count = post_comment_count;
        this.post_status = post_status;
        this.post_views_count = post_views_count;
    }
}
