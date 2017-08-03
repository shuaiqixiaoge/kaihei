package com.base.net;

/**
 * Created by zwy on 2017/8/2.
 * package_name is com.base.net
 * 描述:SJ
 */

public class URLManager {

    //    public static final String HOST_URL = "http://192.168.1.10:8080";//测试地址
    public static final String HOST_URL = "http://see.forhour.com";
    /***********************系统***********************/
    public static final String SYS_TIME = "/index.php/api/public/getServerTime";//获取服务器时间
    public static final String SYS_BANBEN_SHENGJI = "/index.php/api/upgrade/check_version";//版本升级
    public static final String USER_LOGIN = "/index.php/api/user/login";//登陆提交
    /***********************资讯***********************/
    public static final String ZX = "/index.php/shares/shares/get_information_list";//资讯列表
    public static final String AD_URL = "/index.php/shares/public/get_ad_img";//广告页
    /***********************校股***********************/
    public static final String XG_USER_TYPE = "/index.php/shares/public/user_type";//用户类型
    public static final String XG_MAIN = "/index.php/shares/public/homepage";//主页数据----我的发行人

    public static final String XG_SEARCH = "/index.php/shares/shares/search_user";//搜索校股
    public static final String XG_BUSINESS = "/index.php/shares/shares/show_business";//买卖数据展示
    public static final String XG_TODAY_DEAL = "/index.php/shares/shares/today_deal";//当日成交-我的发行人
    public static final String XG_HISTORY_DEAL = "/index.php/shares/shares/today_deal";//历史成交-我的发行人
    public static final String XG_BUY = "/index.php/shares/shares/buy";//购买
    public static final String XG_SALE = "/index.php/shares/shares/sale";//卖出
    public static final String XG_TODAY_ENTRUST = "/index.php/shares/shares/today_entrust";//今日委托-我的发行人
    public static final String XG_HISTORY_ENTRUST = "/index.php/shares/shares/today_entrust";//历史委托-我的发行人
    public static final String XG_PURCHASE = "/index.php/shares/shares/purchase";//已购
    public static final String XG_CONFIRM_ORDER = "/index.php/shares/shares/confirm_order";//确认预约
    public static final String XG_MY_BESPEAK = "/index.php/shares/shares/my_bespeak";//我的预约-我的发行人
    public static final String XG_REVOKE_BESPEAK = "/index.php/shares/shares/revoke_bespeak";//撤销预约-我的发行人驳回
    public static final String XG_OPERATION_MYCHOICE = "/index.php/shares/shares/operation_mychoice";//添加/删除自选
    public static final String XG_DETAIL = "/index.php/shares/public/shares_detail";//校股详情-我的发行人
    public static final String XG_AGREE = "/index.php/shares/shares/apply_through";//确认结单 / 经纪人 -- 同意预约申请
    public static final String XG_GIFT_LIST = "/index.php/shares/shares/my_gift";//经纪人的礼物
    public static final String XG_GIFT_DUIHUAN = "/index.php/shares/shares/exchange";//礼物兑换
    public static final String XG_AGENT = "/index.php/shares/shares/agentpage";//经纪人主页
    public static final String XG_AGENT_GIFTS = "/index.php/shares/shares/gift_list";//经纪人-赠送礼物列表
    public static final String XG_AGENT_GIVE_GIFTS = "/index.php/shares/shares/gift_giving";//赠送礼物
    public static final String XG_INFORMATION_LIST = "/index.php/shares/shares/get_information_list";//资讯列表
    /***********************校淘***********************/
    public static final String BANNER_URL = "/index.php/api/public/get_banner";//首页轮播图
    //银行卡
    public static final String CARD_LIST_URL = "/index.php/api/user/card_lst";//获取用户的银行卡
    public static final String ADD_CARD_URL = "/index.php/api/userinfo/band_card";//添加银行卡确定提交
    public static final String ADD_CARD_NAME = "/index.php/api/user/getrealname";//添加银行卡获取真实姓名
    public static final String BANK_LIST = "/index.php/api/public/bank_lst";//所属银行列表
    public static final String DELETE_BANK = "/index.php/api/user/del_card";//删除银行卡

    //我的
    public static final String UPLOAD_URL = "/index.php/api/mine/up_avatar";//上传用户头像
    public static final String USER_URL = "/index.php/api/user/get_info";//获取用户信息
    public static final String MODIFY_LOGIN_PASSWORD_URL = "/index.php/api/mine/mod_lpwd";//修改登录密码
    public static final String MODIFY_PAY_PASSWORD_URL = "/index.php/api/mine/mod_ppwd";//修改支付密码
    public static final String PAY_PASSWORD_URL = "/index.php/api/mine/pay_pwd";//确认支付密码
    public static final String FORGET_PASSWORD = "/index.php/api/mine/mod_lpwd";//忘记登录密码
    public static final String Checksum_FORGET_PASSWORD = "/index.php/api/user/check_code";//忘记密码之检查验证码
    public static final String USER_DETAIL = "/index.php/api/user/user_detail";//用户详情
    public static final String USER_DETAIL_URL = "/index.php/Api/User/my_det";//用户详情信息
    public static final String MY_JD_URL = "/index.php/Api/User/my_jd";//我的简单
    public static final String MY_XT_URL = "/index.php/Api/User/my_xt";//我的校淘
    public static final String REMAIN_URL = "/index.php/Center/remaining";//余额查询
    public static final String BILL_URL = "/index.php/api/mine/success_lst";//我的账单 成功--我的账单
    public static final String BILL_ITEM_URL = "/index.php/api/mine/item_successdetail";//我的借款列表 成功--我的账单--详情
    public static final String BORROW_LIST_URL = "/index.php/api/mine/lst";//我的借款列表 所有--借款记录
    public static final String BORROW_ITEM_LIST_URL = "/index.php/api/mine/item_detail";//我的借款列表 所有--借款记录

    public static final String Money_URL = "/index.php/api/public/get_info";//成交金额和成交笔数
    public static final String BORROW_URL = "/index.php/api/applylist/apply";//我要借钱
    public static final String LIST_URL = "/index.php/api/loan/item_detail";//列表条目详情
    public static final String DAILI_LIST_URL = "/index.php/api/loan/lst";//代理点发布列表
    public static final String DIANZAN_URL = "/index.php/Api/Goods/click_good";//商品点赞
    public static final String DASHANG_URL = "/index.php/Api/HitAward/give_money";//校汇打赏
    public static final String DASHANG_LIST_URL = "/index.php/Api/HitAward/lst";//校汇打赏列表
    //认证 的接口
    public static final String AUTH_STEP_URL = "/index.php/api/auth/id_auth";//身份认证
    public static final String REN_ZHENG_URL = "/index.php/api/auth/auth";//认证状态接口
    public static final String AUTH_STEP2_URL = "/index.php/api/auth/img_auth";//上传照片
    public static final String HUANKUAN_URL = "/index.php/Borro/huankuan.html";//账单还款
    public static final String WX_ORDER_URL = "/index.php/api/wxpay/wx_prepay";//微信统一下单接口
    public static final String ZFB_PAY_URL = "/index.php/api/alipay/alipay_sign";//支付宝 签名接口
    //云创校汇
    public static final String GOODS_LIST_URL = "/index.php/api/goods/lst";//商品列表
    public static final String FENLEI_LIST_URL = "/index.php/Api/Goods/goods_lst";//分类下校淘列表
    public static final String FENLEI_URL = "/index.php/Api/Goods/cate_lst";//首页标题分类
    public static final String PERSON_URL = "/index.php/api/mine/mine";//我的 个人中心
    public static final String RED_MESSAGE_URL = "/index.php/api/goods/red";//首页消息未读 小红点
    public static final String SEARCH_URL = "/index.php/api/goods/search_lst";//搜索
    public static final String GOODS_LIST_CHOOSE_URL = "/index.php/api/goods/lst";//商品列表筛选
    public static final String GOODS_LIST_DETAIL_URL = "/index.php/api/goods/detail";//商品列表详情
    public static final String REPORT_URL = "/index.php/api/goods/accusation";//举报
    public static final String LEAVE_MSG_URL = "/index.php/api/message/add_msg";//留言
    public static final String LEAVE_MSG_LIST_URL = "/index.php/api/message/lst";//留言列表
    public static final String SCHOOL_CHOOSE_URL = "/index.php/api/public/get_school";//选择学校
    public static final String COMPLETE__SCHOOL_URL = "/index.php/api/auth/school_auth";//完善学校信息
    public static final String RELEASE_URL = "/index.php/api/goods/release";//发布
    public static final String UP_IMG_URL = "/index.php/api/goods/album";//商品相册上传
    public static final String UP_VIDEO_URL = "/index.php/Api/Goods/uploadMove";//上传视频
    public static final String UP_VIDEO_NEW_URL = "/index.php/Api/movie/upmovie";//上传视频新街口
    public static final String CATE_URL = "/index.php/api/goods/get_cate";//商品分类选择
    public static final String JIFEN_URL = "/index.php/api/scorerecord/lst";//我的积分列表
    public static final String INTERACTION_URL = "/index.php/api/mine/interaction_lst";//互动消息列表
    public static final String BIRTH_NICK_URL = "/index.php/api/mine/mod_user";//生日 昵称
    public static final String MY_XP_URL = "/index.php/api/mine/goods_lst";//我的闲铺
    public static final String CLOSE_URL = "/index.php/api/goods/close";//我的闲铺-->关闭自己的商品
    public static final String SOLD_URL = "/index.php/Api/mine/sold";//我的闲铺-->已售出
    public static final String CHAT_LIST_URL = "/index.php/api/letter/lst";//聊天列表
    public static final String CHAT_URL = "/index.php/api/letter/add_let";//聊天走起
    public static final String JIFEN_PAGER_URL = "/index.php/api/mall/my_score";//我的积分页
    public static final String SIGN_URL = "/index.php/Api/sign/sign_info";//签约信息
    public static final String CLOUD_URL = "/index.php/api/mine/yuncoin";//元中心
    public static final String CLOUD_BAO_URL = "/index.php/api/rate/yun_bao";//云宝
    public static final String OPENCLOUD_BAO_URL = "/index.php/api/user/change_yb";//开启或关闭云宝
    public static final String CLOUD_DUIHUAN_URL = "/index.php/api/mine/change_yb";//云币兑换
    public static final String CLOUD_DETAIL_URL = "/index.php/api/mine/yuncoin_detail";//元明细
    public static final String TIXIAN_URL = "/index.php/api/mine/withdraw";//云币提现申请
    public static final String FRIEND_URL = "/index.php/api/user/user_detail";//用户详情 2条校淘 2条简单
    public static final String CONTACTS_URL = "/index.php/api/mine/up_contacts";//上传通讯录
    public static final String LAT_LNG_URL = "/index.php/api/mine/up_latlng";//上传经纬度
    public static final String DECIDE_URL = "http://192.168.1.10/index.php/Api/Public/is_related";//在校汇中判断校花是否已经关联
    //信息
    public static final String FANS_URL = "/index.php/api/friends/my_fans";//我的粉丝
    public static final String FOCUS_URL = "/index.php/api/friends/my_focus";//我的关注
    public static final String UN_FOCUS_URL = "/index.php/api/friends/un_focus";//取消关注
    public static final String ADD_FOCUS_URL = "/index.php/api/friends/focus";//加关注
    public static final String MY_SEARCH_URL = "/index.php/api/friends/search";//搜索
    public static final String FANS_SEARCH_URL = "/index.php/api/friends/search_myfans";//从我的粉丝里面搜索
    public static final String FOUCS_SEARCH_URL = "//index.php/api/friends/search_myfocus";//从我关注的里面搜索
    public static final String PHONE_URL = "/index.php/api/friends/contacts";//通讯录好友
    public static final String MAY_URL = "/index.php/api/friends/may_know";//可能认识的人
    public static final String SC_FRIEND_URL = "/index.php/api/friends/school_friends";//同校好友
    public static final String MY_FANS_URL = "/index.php/api/friends/new_fans";//我的分丝
    //简单
    public static final String TASK_SHOW = "/jd/Task/show";//任务展示
    public static final String TASK_DETAIL = "/jd/Task/detTask";//任务详情
    public static final String My_QIANG = "/jd/Task/mulinterface";//我要抢单
    public static final String MY_SELECTOR = "/jd/Task/checkuser";//发布者选择让谁接单
    public static final String FABU_FABU_CANCLE = "/jd/Task/chporder";//发布者取消任务发布
    public static final String JIEDAN_FABU_CANCLE = "/jd/Task/chgorder";//接单者取消任务发布
    public static final String MY_FABU = "/jd/Task/publish";//发布任务
    public static final String MY_RECEIVED = "/jd/Task/received";//已接订单
    public static final String MY_SENED = "/jd/Task/sended";//我的发布
    public static final String BANNER_JD_URL = "/jd/Task/lunb";//轮播图
    public static final String TRIBUNE_LIST = "/jd/forum/forum_list";//论坛列表
    public static final String BEN_XIAO = "/jd/forum/forum_local";//本校论坛
    public static final String RELEASE_QUESTION = "/jd/forum/push_question";//轮坛界面的发布问题
    public static final String QUEDETAIL = "/jd/forum/detail_question";//轮坛界面的问题详情
    public static final String JU_BAO = "/jd/forum/accusation";//举报
    public static final String CAI_NA = "/jd/forum/accept_answer";//采纳
    public static final String ANSWER_QUESTION = "/jd/forum/answer";//回答问题
    public static final String MY_LIKE = "/jd/forum/like";//喜欢的接口
    public static final String MY_NOT_LIKE = "/jd/forum/not_like";//不喜欢
    public static final String My_CANCLE_LIKE = "/jd/forum/unlike";//取消喜欢
    //分享
    public static final String SHARE1_URL = "/index.php/home/jdshare/jd_share";//任务发送成功分享
    public static final String SHARE2_URL = "/index.php/home/jdshare/jd_share2";//任务接单分享

}
