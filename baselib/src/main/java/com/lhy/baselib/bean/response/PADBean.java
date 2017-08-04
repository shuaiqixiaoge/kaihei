package com.lhy.baselib.bean.response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class PADBean {

    /**
     * id : 25
     * link : http://www.baidu.com
     * img : /Public/Uploads/banner/20170510/5912ba9b4ff71.png
     * is_full : 1
     */

    private String id;
    private String link;
    private String img;
    private String is_full;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIs_full() {
        return is_full;
    }

    public void setIs_full(String is_full) {
        this.is_full = is_full;
    }
}
