package learningandroid.booobdai.com.constructiondiary.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.ui.manager.ThreadManager;
import learningandroid.booobdai.com.constructiondiary.utils.UIUtils;
/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:59
 * 描    述：
 * 修订历史：
 * ================================================
 */

public abstract class LoadingPager extends FrameLayout {

	// 加载默认的状态
	private static final int START_UNLOADING = 0;
	// 加载的状态
	private static final int START_LOADING = 1;
	// 失败的状态
	private static final int START_ERROR = 3;
	// 加载空的状态
	private static final int START_EMPTY = 4;
	// 加载成功的状态
	private static final int START_SUCCESS = 5;

	private int mState;
	private View loadingView;
	private View emptyView;
	private View errorView;
	private View successView;

	public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LoadingPager(Context context) {
		super(context);
		init();
	}

	private void init() {
		// 初始化 默认的状态
		mState = START_UNLOADING;

		loadingView = createLoadingView();

		if (null != loadingView) {
			addView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		}
		errorView = createErrorView();

		if (null != errorView) {
			addView(errorView, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		}

		emptyView = createEmptyView();
		if (null != emptyView) {
			addView(emptyView, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		}

		// 显示出界面
		show();

	}

	private void showSafePagerView() {
		UIUtils.runInMainThread(new Runnable() {

			@Override
			public void run() {
				System.out.println();
				showPageView();
			}

		});
	}

	protected void showPageView() {
		if (null != loadingView) {
			loadingView.setVisibility(mState == START_LOADING
					|| mState == START_UNLOADING ? View.VISIBLE
					: View.INVISIBLE);
		}
		if (null != errorView) {
			errorView.setVisibility(mState == START_ERROR ? View.VISIBLE
					: View.INVISIBLE);
		}
		if (null != emptyView) {
			emptyView.setVisibility(mState == START_EMPTY ? View.VISIBLE
					: View.INVISIBLE);
		}

		// 成功的界面
		if (null == successView && mState == START_SUCCESS) {
			successView = createLoadedView();
			addView(successView, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		}

		if (null != successView) {
			successView.setVisibility(mState == START_SUCCESS ? View.VISIBLE
					: View.INVISIBLE);
		}
	}

	public void show() {
		if (mState == START_ERROR || mState == START_EMPTY) {
			mState = START_UNLOADING;
		}
		if (mState == START_UNLOADING) {
			mState = START_LOADING;

			ThreadManager manager = new ThreadManager();
			manager.getLongPool().execute(new TaskRunable());
		}
		/**
		 * 这个地方代码中没有写需要注意
		 */
		showSafePagerView();
	}

	/**
	 * 获取数据的接口
	 * 
	 * @author xml_tech
	 * 
	 */
	private class TaskRunable implements Runnable {

		@Override
		public void run() {
			final LoadResult result = load();
			UIUtils.runInMainThread(new Runnable() {

				@Override
				public void run() {
					mState = result.getValue();
					showPageView();
				}
			});
		}

	}

	public enum LoadResult {
		ERROR(3), EMPTY(4), SUCCESS(5);

		int value;

		LoadResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	protected abstract View createLoadedView();

	protected abstract LoadResult load();

	// 空的界面
	private View createEmptyView() {
		return UIUtils.inflate(R.layout.loading_page_empty);
	}

	// 错误界面
	private View createErrorView() {
		return UIUtils.inflate(R.layout.loading_page_error);
	}

	// 加载界面
	private View createLoadingView() {
		return UIUtils.inflate(R.layout.loading_page_loading);
	}

}
