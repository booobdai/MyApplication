package sportslottery.gooolal.com.sportslotteryforshanghai.Base;

import java.util.HashMap;

import sportslottery.gooolal.com.sportslotteryforshanghai.ui.fragment.HomeFragment;

public class FragmentFactory {

	// 首页
	private static final int TAB_HOME = 0;
	// 资讯
	private static final int TAB_APP = 1;
	// 彩种
	private static final int TAB_GAME = 2;
	// 赛事
	private static final int TAB_SUBJECT = 3;
	// 用户
	private static final int TAB_RECOMMENTED = 4;


	private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position) {
//		 BaseFragment fragment = null;
//		 从缓存中取出
		BaseFragment fragment = mFragments.get(position);
		if (null == fragment) {
			switch (position) {
			case TAB_HOME:
				fragment = new HomeFragment();
				break;
			case TAB_APP:
				fragment = new HomeFragment();
				break;
			case TAB_GAME:
				fragment = new HomeFragment();
				break;
			case TAB_SUBJECT:
				fragment = new HomeFragment();
				break;
			case TAB_RECOMMENTED:
				fragment = new HomeFragment();
				break;
			default:
				break;
			}
			// 把frament加入到缓存中
			mFragments.put(position, fragment);
		}
		return fragment;
	}
}
