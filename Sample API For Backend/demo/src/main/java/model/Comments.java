package model;

import java.sql.Date;

public class Comments extends AbstractModel<Comments> {
 /*    `comment_id` int(3) NOT NULL,
  `comment_post_id` int(3) NOT NULL,
  `comment_author` varchar(255) NOT NULL,
  `comment_email` varchar(255) NOT NULL,
  `comment_content` text NOT NULL,
            `comment_status` varchar(255) NOT NULL,
  `comment_date` date NOT NULL
    */
    private int id;
    private int comment_post_id;
    private String comment_author;
    private String comment_email;
    private String comment_content;
    private String comment_status;
    private Date comment_date;

    public Comments() {
    }

    public Comments(int id, int comment_post_id, String comment_author, String comment_email, String comment_content, String comment_status, Date comment_date) {
        this.id = id;
        this.comment_post_id = comment_post_id;
        this.comment_author = comment_author;
        this.comment_email = comment_email;
        this.comment_content = comment_content;
        this.comment_status = comment_status;
        this.comment_date = comment_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComment_post_id() {
        return comment_post_id;
    }

    public void setComment_post_id(int comment_post_id) {
        this.comment_post_id = comment_post_id;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_email() {
        return comment_email;
    }

    public void setComment_email(String comment_email) {
        this.comment_email = comment_email;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }
}
