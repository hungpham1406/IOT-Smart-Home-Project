package model;

public class Users extends AbstractModel<Users>{

    private int user_id;
    private String username;
    private String user_password;
    private String user_firstname;
    private String user_lastname;
    private String user_email;
    private String user_image;
    private String user_role;
    private String randSalt;
    private String token;

    public Users() {
    }

    public Users(int user_id, String username, String user_password, String user_firstname, String user_lastname, String user_email, String user_image, String user_role, String randSalt, String token) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.user_firstname = user_firstname;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.user_image = user_image;
        this.user_role = user_role;
        this.randSalt = randSalt;
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getRandSalt() {
        return randSalt;
    }

    public void setRandSalt(String randSalt) {
        this.randSalt = randSalt;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
