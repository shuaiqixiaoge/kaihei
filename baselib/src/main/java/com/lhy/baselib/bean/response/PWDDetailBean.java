package com.lhy.baselib.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class PWDDetailBean {

    /**
     * id : 2
     * user_name : 18811458677
     * nick_name : .蒾夨.°
     * balance : 9492135.00
     * avatar : /Public/Uploads/header/2017-04-26/14931909775209883.jpg
     * sex : 男
     * money : 500023.72
     * cover_img : /Public/Uploads/cover_img/2017-05-03/14938047725878469.png
     * score : 16
     * experience : 0
     * grade : 0
     * pid : 7504
     * vip_grade : 0
     * tags : []
     */

    private String id;
    private String user_name;
    private String nick_name;
    private String balance;
    private String avatar;
    private String sex;
    private String money;
    private String cover_img;
    private String score;
    private int experience;
    private int grade;
    private String pid;
    private int vip_grade;
    private List<?> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getVip_grade() {
        return vip_grade;
    }

    public void setVip_grade(int vip_grade) {
        this.vip_grade = vip_grade;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }


}
