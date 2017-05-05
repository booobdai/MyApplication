package learningandroid.booobdai.com.constructiondiary.ui.view.MarqueeView;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-10-0010 10:26
 * 描    述：跑马灯的适配器
 * 修订历史：
 * ================================================
 */

public abstract class MarqueeViewAdapter<T> {

    private List<T> mDatas;
    private OnDataChangeListener listener;

    public MarqueeViewAdapter(List<T> datas) {
        mDatas = datas;
    }

    public MarqueeViewAdapter(T[] datas) {
        mDatas = new ArrayList<>(Arrays.asList(datas));
    }

    public void notifyDataSetChanged() {

        if (listener != null) {
            listener.dataChange();
        }

    }

    void setOnDataChangeListener(OnDataChangeListener listener) {

        this.listener = listener;

    }

    interface OnDataChangeListener {

        void dataChange();
    }

    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public abstract View getView(MarqueeView parent, int position, T t);

}
