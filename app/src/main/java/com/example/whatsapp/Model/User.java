package com.example.whatsapp.Model;

public class User {
    String profile, username, pass,mail, userid, lastmsg;

    public User()
    {

    }

    public User( String username, String mail, String pass) {
        this.username = username;
        this.mail = mail;
        this.pass = pass;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserid() {
        return userid;
    }

    public String getUserid(String key) {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }

    public User(String profile, String username, String mail, String pass, String userid, String lastmsg) {
        this.profile = profile;
        this.username = username;
        this.mail = mail;
        this.pass = pass;
        this.userid = userid;
        this.lastmsg = lastmsg;
    }
}
