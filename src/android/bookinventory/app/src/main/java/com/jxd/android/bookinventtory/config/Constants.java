package com.jxd.android.bookinventtory.config;

/**
 * Created by Administrator on 2017/9/8.
 */
public class Constants {
    public static int READ_TIMEOUT=15;
    public static int CONNECT_TIMEOUT=15;
    public static int  WRITE_TIMEOUT=15;

    public static long REALM_VERSION=1;

    /**
     * 接口地址
     */
    public static String BASE_URL ="http://192.168.1.36/bookweb/";
    /**
     * 界面显示的最多关键字数量
     */
    public static int MAX_SEARCHKEY_COUNT = 5;

    public static String Key_SearchKey="searchkey";

    public static String PREF_FILENAME="pref_config";
    public static String PREF_USER="user";

    public static String PREF_COOKIE="cookie";


    public static String   MESSAGE_TIMEOUT="链接服务器超时，请重试";
    public static String   MESSAGE_ERROR="发送错误,请重试";

    public static String TIP_WAITING="加载中...";
    public static String TIP_PROCESSING="处理中...";
    public static String TIP_LOGINNG="登录中...";

}
