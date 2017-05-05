package learningandroid.booobdai.com.constructiondiary.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * 类名称：CommonUtils
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/5 11:36
 * 描述：TODO
 * 最近修改时间：2016/12/5 11:36
 * 修改人：Modify by liujc
 */
public class CommonUtils {
    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return "";
        }
    }

    public static String getDBPath() {
        String sdCardPath = getSDPath();
        if (TextUtils.isEmpty(sdCardPath)) {
            return "";
        } else {
            return sdCardPath + File.separator + "GreenDao"
                    + File.separator + "sqlite";
        }
    }
}
