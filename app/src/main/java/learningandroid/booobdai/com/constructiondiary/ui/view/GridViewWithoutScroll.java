package learningandroid.booobdai.com.constructiondiary.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-11-0011 下午 03:14
 * 描    述：不能滑动的gridview
 * 修订历史：
 * ================================================
 */
public class GridViewWithoutScroll extends GridView {
    public GridViewWithoutScroll(Context paramContext) {
        super(paramContext);
    }

    public GridViewWithoutScroll(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public GridViewWithoutScroll(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
