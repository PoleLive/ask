package cn.lawliex.ask;

/**
 * Created by Terence on 2016/12/28.
 */

public class UrlContract {
    //服务器地址
    public static final String SERVER_ADDRESS = "http://10.21.27.19:8080";
    //path
    public static final String LOGIN_PATH = "login";
    public static final String REGISTER_PATH = "register";
    public static final String TEST_LOGINED = "test";
    public static final String QUESTION_LIST = "question/list";
    public static final String QUESTION_ADD = "question/add";
    public static final String QUESTION_DETAIL = "question/detail";
    public static final String ANSWER_ADD = "answer/add";
    public static final String ANSWER_LIST = "answer/list";
    public static final String ANSWER_DETAIL = "answer/detail";
    public static final String MESSAGE_ADD = "message/add";
    public static final String MESSAGE_DETAIL = "message/detail";
    public static final String MESSAGE_LIST = "message/list";
    public static final String LIKE_LIKE = "like/like";
    public static final String FOLLOW = "follow/follow";
    public static final String UNFOLLOWER = "follow/unfollow";
    //只做判断
    public static final String ISFOLLOWER = "follow/isfollower";
    //判断并改变状态
    public static final String ISFOLLOWERED = "follow/isfollowered";

}
