package sportslottery.gooolal.com.sportslotteryforshanghai.urlmanager;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 15:22
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class UrlManager {
    boolean flag = false;//当flag为true时，为正式地址，为false时，为测试地址
    private static  String HostUrl;
    public static final String TESTURl = "测试地址/";//app测试网址
    public static final String RELEASEURL = "正式地址";//app正式网址
    public static final String TokenURL = "http://vpn.gooooal.com/cpapi/getToken";//token获取地址
    public static final String DownLoadURL = "http://vpn.gooooal.com/cpweb/download/app.apk";//新版apk下载地址

}
