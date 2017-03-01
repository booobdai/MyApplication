package sportslottery.gooolal.com.sportslotteryforshanghai.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

	protected BaseFragmentActivity mActivity;

	protected abstract void initView(View view, Bundle savedInstanceState);

	//获取布局文件ID
	protected abstract int getLayoutId();

	//获取宿主Activity
	protected BaseFragmentActivity getHoldingActivity() {
		return mActivity;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mActivity = (BaseFragmentActivity) activity;
	}

	//添加fragment
	protected void addFragment(BaseFragment fragment) {
		if (null != fragment) {
			getHoldingActivity().addFragment(fragment);
		}
	}

	//移除fragment
	protected void removeFragment() {
		getHoldingActivity().removeFragment();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		initView(view, savedInstanceState);
		return view;
	}
}
