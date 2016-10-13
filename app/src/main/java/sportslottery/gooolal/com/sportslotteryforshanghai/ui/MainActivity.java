package sportslottery.gooolal.com.sportslotteryforshanghai.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import sportslottery.gooolal.com.sportslotteryforshanghai.Base.BaseActivity;
import sportslottery.gooolal.com.sportslotteryforshanghai.Base.BaseFragment;
import sportslottery.gooolal.com.sportslotteryforshanghai.Base.FragmentFactory;
import sportslottery.gooolal.com.sportslotteryforshanghai.R;
import sportslottery.gooolal.com.sportslotteryforshanghai.entity.TabEntity;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.view.LazyViewPager;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-10-0010 下午 02:34
 * 描    述：app主activity
 * 修订历史：
 * ================================================
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.titlebar_back_ll)
    LinearLayout titlebarBackLl;
    @BindView(R.id.titlebar_tv)
    TextView titlebarTv;
    @BindView(R.id.filtrate_tv)
    ImageView filtrateTv;
    @BindView(R.id.base_titleBar)
    RelativeLayout baseTitleBar;
    @BindView(R.id.tabs)
    CommonTabLayout mCommonTabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
   /* @BindView(R.id.pager)
    LazyViewPager pager;*/

    private ViewPageAdapeter adapter;

    private String[] mTitles = new String[5];
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       /* mCommonTabLayout = (CommonTabLayout) findViewById(R.id.tabs);
        pager = (LazyViewPager) findViewById(R.id.pager);*/
        Resources res = this.getResources();

        mTitles = res.getStringArray(R.array.tab_names);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        adapter = new ViewPageAdapeter(getSupportFragmentManager());
        // 设置数据
        pager.setAdapter(adapter);

        titlebarBackLl.setVisibility(View.INVISIBLE);
        titlebarTv.setText("测试svn");
        bindSelect();
    }

    @Override
    protected void init() {

    }

    /**
     * 将viewpager和下面的tab绑定在一起
     */
    private void bindSelect() {
        mCommonTabLayout.setTabData(mTabEntities);

        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pager.setCurrentItem(position,false);
            }

            @Override
            public void onTabReselect(int position) {
                pager.setCurrentItem(position,false);
            }


        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                titlebarTv.setText(mTitles[position]);
                fragment.show();
            }
        });

    }
  /**
     * 将viewpager和下面的tab绑定在一起
     */
    /*private void bindSelect() {
        mCommonTabLayout.setTabData(mTabEntities);

        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                pager.setCurrentItem(position);
            }


        });

        pager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();
            }
        });

    }*/


    private class ViewPageAdapeter extends FragmentStatePagerAdapter {


        public ViewPageAdapeter(FragmentManager fm) {
            super(fm);
        }

        // 获取到title
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];

        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }
    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();        //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再点一次,退出雪园竞彩", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}