package com.twoculture.twoculture.tools;

public class MethodConst {
//	public static final String URL = "http://120.24.231.203:9000";
	public static final String URL = "http://2cultures.com:80/";
	//获取城市，国家，语言的json数据(接口:get_configure_json)
	public static final  String GET_CONFIGURE_JSON="/mobile/get_configure_json";
	//获取单人信息设置json接口
	public static final  String GET_SINGLE_CONFIGURE="/mobile/get_single_configure";
	//登录
	public static final String LOGIN    = "/mobile/login";
	//注册
	public static final String REGISTER    = "/mobile/register";
	//验证用户是否已激活
	public static final String CHECK_USER_IS_ACTIVE    = "/mobile/check_user_is_active";
	//忘记密码
	public static final String RESET_PASSWORD   = "/mobile/reset_password";
	//修改密码
	public static final String UPDATE_PASSWORD = "/mobile/update_password";
	//修改当前版本语言
	public static final String SET_USER_LOCALE = "/mobile/others/set_user_locale";
	//搜索GSG用户
	public static final String SEARCH_GSG = "/mobile/search/search_gsg";
	//重发邮件
	public static final String LOGIN_RETRY_EMAIL    = "/mobile/retry_email";
	//反馈
	public static final String FEEDBACK    = "/mobile/others/feedback";
	//反馈信息的列表
	public static final String FEEDBACKS    = "/mobile/others/feedbacks.json";
	//对其他用户浏览
	public static final String BROWSE_USER_PROFILE    = "/mobile/browse/browse_user_profile";
	//展示所有的朋友请求
	public static final String SHOW_FRIENDS_REQUEST = "/mobile/messages/show_friends_request";
	//发起朋友请求
	public static final String SEND_FRIEND_REQUEST = "/mobile/messages/send_friend_request";
	//处理朋友请求
	public static final String PROCESS_FRIEND_REQUEST = "/mobile/messages/process_friend_request";
	//取消朋友的申请
	public static final String CANCEL_FRIEND_REQUEST = "/mobile/messages/cancel_friend_request";
	//对情侣link的赞操作
	// 赞该次连接
	public static final String APPROVW_THE_LINK   = "/mobile/my/my_cultures/approve_the_link";
	//取消赞
	public static final String CANCLE_APPROVE_THE_LINK = "/mobile/my/my_cultures/cancel_approve_the_link";
	//对情侣信息的评论
	//获取评论列表
	public static final String  LINK_COMMENTS = "/mobile/my/my_cultures/link_comments";
	//创建评论
//	public static final String  LINK_COMMENTS = "/mobile/my/my_cultures/link_comments";

	//修改评论
	public static final String  UPDATE_LINK_COMMENT= "/mobile/my/my_cultures/link_comments/update_link_comment";
	//删除评论
	public static final String  DESTROY_LINK_COMMENT= "/mobile/my/my_cultures/link_comments/destroy_link_comment";
	//情侣链接消息处理
	//发送情侣链接
	public static final String  SEND_LINK_MESSAGE= "/mobile/messages/send_link_message";
	//处理情侣请求
	public static final String  PROCESS_LINK_MESSAGE= "/mobile/messages/process_link_message";
	//移除情侣链接
	public static final String  REMOVE_THE_RELATIONSHIP= "/mobile/messages/remove_the_relationship";
	//取消情侣链接
	public static final String  CANCEL_APPROVE_THE_LINK="/mobile/my/my_cultures/cancel_approve_the_link";
	//获取所有活动邀请信息
	//获取活动邀请信息列表
	public static final String  GET_EVENT_INVITES="/mobile/messages/get_event_invites";
	//删除活动邀请信息
	public static final String  DESTROY_EVENT_INVITE="/mobile/messages/destroy_event_invite";

	//获取所有消息
	public static final String  GET_ALL_MESSAGE="/mobile/messages/get_all_messages";
	//获取系统通知
	public static final String GET_SYSTEM_MESSAGE="/mobile/messages/get_system_messages";
	//删除系统信息
	public static  final String DESTROY_SYSTEM_MESSAGE="/mobile/messages/destroy_system_message";

	//我的2C
	//获取我的2c分享
	public static final String CULTURES ="/mobile/my/my_cultures/cultures";

	//对最新评论信息的操作
	//获取最新消息的列表
	public static final String READ_COMMENTS ="/mobile/my/my_cultures/read_comments";
	//清除所有未读信息
	public static final String CLEAR_ALL_UPDATE_NOTES ="/mobile/my/my_cultures/read_comments/clear_all_update_notes";
	//清除单个未读评论
	public static  final String  CLEAR_UPDATE_NOTE= "/mobile/my/my_cultures/read_comments/clear_update_note";
	//朋友
	//获取朋友列表
	public static final String FRIENDS  = "/mobile/my/friends";
	//删除朋友
	public static final String  REMOVE_FRIEND= "/mobile/my/friends/remove_friend";
	//分享的评论
	//获取评论列表get
	public static final String  SHARE_COMMENTS= "/mobile/my/my_shares/share_comments";
	//创建评论post
	public  static final String   CREATE_SHARE_COMMENTS= "/mobile/my/my_shares/share_comments";
	//修改评论
	public  static final String  UPDATE_COMMENT= "/mobile/my/my_shares/share_comments/update_comment";
	//删除评论
	public  static final  String   DESTROY_COMMENT= "/mobile/my/my_shares/share_comments/destroy_comment";

	//My Share
	//获取我的share(对于有情侣的人获取他们两个人的share) get
	public  static final String  SHARES= "/mobile/my/my_shares/shares";
	//Show share
	public  static final String  SHOW_SHARE= "/mobile/my/my_shares/shares/show_share";
	//创建分享post
	public static final String CREATE_SHARES  = "/mobile/my/my_shares/shares";
	//修改分享
	public static  final String  UPDATE_SHARE = "/mobile/my/my_shares/shares/update_share";
	///删除分享
	public static final String  DESTROY_SHARE = "/mobile/my/my_shares/shares/destroy_share";
	//上传分享的图片
	public static final String  UPDATE_SHARE_PHOTO = "/mobile/my/my_shares/shares/upload_share_photo";

	///删除上传照片
	public static final String  DESTROY_SHARE_PHOTO = "/mobile/my/my_shares/shares/destroy_share_photo";
	//赞该次分享
	public static final String  APPROVE_THE_SHARE = "/mobile/my/my_shares/shares/approve_the_share";
	//取消赞
	public static final String  CANCEL_APPROVE_THE_SHARE = "/mobile/my/my_shares/shares/cancel_approve_the_share";

	//用户基本信息接口
	//获取用户基本信息get
	public static final String  MY_PROFILE = "/mobile/my/my_profile";
	//创建用户的基本信息post
	public static final String  CREATE_MY_PROFILLE = "/mobile/my/my_profile";
	//更新个人用户档案
	public static final String  UPDATE_MY_PROFILE = "/mobile/my/my_profile/update_my_profile";
	//上传头像
	public static final String  UPLOAD_HEADER_IMAGE = "/mobile/my/my_profile/upload_header_image";
	//单身信息接口
	//获取用户单身信息get
	public static final String  SINGLE_PROFILE = "/mobile/my/single_profile";
	//创建个人信息 post
	public static final String  CREATE_PROFILE = "/mobile/my/single_profile";
	//修改个人信息
	public static final String  UPDATE_SINGLE_PROFILE = "/mobile/my/single_profile/update_single_profile";
	//上传个人照片
	public static final String  UPDATE_USER_PHOTO = "/mobile/my/single_profile/upload_user_photo";
	//删除个人照片
	public static final String  DELETE_USER_PHOTO = "/mobile/my_single_profile/delete_user_photo";
	//参加活动支付
	//创建订单以及创建支付
	public static final String  JOIN_EVEN = "/mobile/pays/join_event";
	//成为会员支付
	//创建订单以及创建支付
	public static final String  JOIN_GSG = "/mobile/pays/join_gsg";
	//使用信用卡支付
	public static final String  PAY_GSG_BY_CARD = "/mobile/pays/pay_gsg_by_card";

	//通过姓名或邮箱搜索人
	public static final String  SERACH_USERNAME_OR_EMAIL = "/mobile/search/search_by_username_or_email";

	//情侣查找
	public static final String  SEARCH_COUPLE = "/mobile/search/search_couple";
	//单身查找
	public static final String  SEARCH_SINGLE = "/mobile/search/search_single";

	//event的评论
	//获取评论列表get
	public static final String  EVENT_COMMENTS = "/mobile/we/events/event_comments";
	//创建评论post
	public static final String  CREATE_EVENT_COMMENTS = "/mobile/we/events/event_comments";
	//修改评论
	public static final String  UPDATE_EVENT_COMMTEN = "/mobile/we/events/event_comments/update_event_comment";
	//删除评论
	public static final String  DESTROY_EVENT_COMMENT = "/mobile/we/events/event_comments/destroy_event_comment";
	//收藏event
	//收藏event
	public static final String  FAVORITE_THE_EVENT = "/mobile/we/events/favorite_the_event";
	//取消收藏event
	public static final String  UNFAVORITE_THE_EVENT = "/mobile/we/events/unfavorite_the_event";

	//加入event
	//获取报名列表
	public static final String  EVENT_JOIN = "/mobile/we/events/event_join";
	//加入event
//	public static final String  EVENT_JOIN = "/mobile/we/events/event_join";

	//喜欢event
	//获取喜欢列表
	public static final String  EVENT_LIKE = "/mobile/we/events/event_like";
	//喜欢event
	public static final String  LIKE_THE_EVENT = "/mobile/we/events/event_like/like_the_event";
	//取消喜欢
	public static final String  UNLIKE_THE_EVENT = "/mobile/we/events/event_like/unlike_the_event";

	//event
	//获取events列表
	public static final String  EVENT = "/mobile/we/events/events";
	//展示event具体信息
	public static final String  SHOW_EVENT_DETAIL = "/mobile/we/events/events/show_event_detail";


	//#获取topic_category的所有种类
	public static final String  GET_TOPIC_CATEGORIES = "/mobile/get_topic_categories";

	public static final String  CREATE_TOPIC_HEADER = "/mobile/we/topics/create_topic_header";

	// 我的topic
	// 获取我的topic
	public static final String  MY_TOPICS = "/mobile/we/topics/my_topics";
	//获取所有topic的列表  GET
	public static final String  TOPICS = "/mobile/v2/topics";
	//流行话题
	public static final String  POPULAR_TOPICS = "/mobile/we/topics/popular_topics";
	//喜欢topic GET
	public static  final String LIKE_THE_TOPIC = "/mobile/we/topics/topic_likes/like_the_topic";
	//取消喜欢topic GET
	public static  final String UNLIKE_THE_TOPIC = "/mobile/we/topics/topic_likes/unlike_the_topic";
	/// 创建topic   POST
	public static final String  CREATE_TOPICS = "/mobile/we/topics/topics";
	//修改topic
	public static final String  UPDATE_TOPICS = "/mobile/we/topics/topics/update_topic";
	//删除topic
	public static final String  DESTROY_TOPICS = "/mobile/we/topics/topics/destroy_topic";
	//展示话题
	public static final String  SHOW_TOPICS_DETAIL = "/mobile/we/topics/topics/show_topic_detail";
	//我的收藏
	public static final String  MY_FAVORITES = "/mobile/we/my_favorites";
	//话题收藏
	public static final String  FAVORITE_THE_TOPIC = "/mobile/we/topics/favorite_the_topic";
	//取消话题收藏
	public static final String  UNFAVORITE_THE_TOPIC = "/mobile/we/topics/unfavorite_the_topic";
	//上传照片
	public static final String  CREATE_TOPIC_PHOTO = "/mobile/we/topics/create_topic_photo";

	public static final String  DESTROY_TOPIC_PHOTO = "/mobile/we/topics/destroy_topic_photo";

	//获取举报的原因
	public static final String  REPORT_REASONS = "/mobile/others/report_reasons";

	//举报用户
	public static final String  REPORT_ABUSE = "/mobile/others/report_abuse";

	//屏蔽某人
	public static final String  BLOCKED_USER = "/mobile/others/blocked/blocked_user";

	//解除屏蔽
	public static final String  UNBLOCKED_USER = "/mobile/others/blocked/unblocked_user";

	//获取屏蔽列表
	public static final String  BLOCKED = "/mobile/others/blocked";

	//Show topic
	public static final String  SHOW_TOPIC = "/mobile/v2/topics/";

	//topic_comment
	public static final String  TOPIC_COMMENTS = "/mobile/we/topics/topic_comments";

	//destroy comment
	public static final String DESTROY_TOPIC_COMMENT ="/mobile/we/topics/topic_comments/destroy_topic_comment";

	//topic like list
	public static final String TOPIC_LIKES ="/mobile/we/topics/topic_likes";

	//translate list
	public static final String TOPIC_TRANSLATIONS ="/mobile/v2/topics/show_topic_translates";

	//post 创建翻译
	public static final String CREATE_TOPIC_TRANSLATIONS ="/mobile/v2/translate_topics";

	//show translate
	public static final String SHOW_TOPIC_TRANSLATE ="/mobile/we/topics/topic_translations/";

	//转发到My2C
	public static final String FORWARD_TO_MY2C ="/mobile/v2/forward";

	//转发到第三方
	public static final String FORWARD_REDIRECT ="/mobile/v2/forward/forward_redirect";
}

