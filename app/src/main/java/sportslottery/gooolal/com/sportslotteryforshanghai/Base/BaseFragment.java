package sportslottery.gooolal.com.sportslotteryforshanghai.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import sportslottery.gooolal.com.sportslotteryforshanghai.ui.widget.LoadingPager;
import sportslottery.gooolal.com.sportslotteryforshanghai.utils.UIUtils;
import sportslottery.gooolal.com.sportslotteryforshanghai.utils.ViewUtils;
/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:55
 * 描    述：
 * 修订历史：
 * ================================================
 */

public abstract class BaseFragment extends Fragment {

	private LoadingPager mContentView;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (null == mContentView) {
			mContentView = new LoadingPager(UIUtils.getContext()) {

				@Override
				protected LoadResult load() {
					return BaseFragment.this.Load();
				}

				@Override
				protected View createLoadedView() {
					return BaseFragment.this.createLoadedView();
				}
			};
		} else {
			ViewUtils.removeSelfFromParent(mContentView);
		}
		return mContentView;
	}

	protected abstract View createLoadedView();

	protected abstract LoadingPager.LoadResult Load();

	// 展示具体的页面
	public void show() {
		if (null != mContentView) {
			mContentView.show();
		}
	}

	// 检查服务器返回的数据情况
	protected LoadingPager.LoadResult check(Object obj) {
		if (null == obj) {
			return LoadingPager.LoadResult.ERROR;
		}
		if (obj instanceof List) {
			List list = (List) obj;
			if (list.size() == 0) {
				return LoadingPager.LoadResult.EMPTY;
			}
		}
		return LoadingPager.LoadResult.SUCCESS;
	}
}
