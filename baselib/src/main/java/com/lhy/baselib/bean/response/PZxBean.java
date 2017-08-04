package com.lhy.baselib.bean.response;

import java.util.List;

/**
 * Created by zwy on 2017/4/27.
 * package_name is com.lhy.baselib.bean.response
 * 描述:资讯首页
 */

public class PZxBean {

    private List<BannerListBean> banner_list;
    private List<InformationListBean> information_list;

    public List<BannerListBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerListBean> banner_list) {
        this.banner_list = banner_list;
    }

    public List<InformationListBean> getInformation_list() {
        return information_list;
    }

    public void setInformation_list(List<InformationListBean> information_list) {
        this.information_list = information_list;
    }

    public static class BannerListBean {
        /**
         * img_id : 23
         * img : /Public/meigui2.png
         * link :
         */

        private String img_id;
        private String img;
        private String link;

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class InformationListBean {
        /**
         * agree_num : 1
         * aid : 1
         * title : 法国大选：马克龙和勒庞将角逐总统宝座
         * title_img : http://inews.gtimg.com/newsapp_ls/0/1451366859_150120/0
         * link : http://news.qq.com/a/20170424/001947.htm
         * add_time : 2017-04-24 14:21:35
         * type : 0
         * is_agree : 0
         */

        private String agree_num;
        private String aid;
        private String title;
        private String title_img;
        private String link;
        private String add_time;
        private String type;
        private String is_agree;

        public String getAgree_num() {
            return agree_num;
        }

        public void setAgree_num(String agree_num) {
            this.agree_num = agree_num;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_img() {
            return title_img;
        }

        public void setTitle_img(String title_img) {
            this.title_img = title_img;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_agree() {
            return is_agree;
        }

        public void setIs_agree(String is_agree) {
            this.is_agree = is_agree;
        }
    }
}
