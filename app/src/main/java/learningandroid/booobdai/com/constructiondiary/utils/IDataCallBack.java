package learningandroid.booobdai.com.constructiondiary.utils;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-03-0003 13:22
 * 描    述：
 * 修订历史：
 * ================================================
 */

public interface IDataCallBack<T> {
    /**
     * 任务执行之前
     */
    void onTaskBefore();

    /**
     * 任务执行中...
     */
    T onTasking(Void... params);

    /**
     * 任务执行之后
     */
    void onTaskAfter(T result);

}
