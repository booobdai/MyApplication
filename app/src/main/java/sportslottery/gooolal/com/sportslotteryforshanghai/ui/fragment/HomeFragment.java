package sportslottery.gooolal.com.sportslotteryforshanghai.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import sportslottery.gooolal.com.sportslotteryforshanghai.Base.BaseFragment;
import sportslottery.gooolal.com.sportslotteryforshanghai.R;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.adapter.GlideImageLoader;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.adapter.HomeGridViewAdapter;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.view.GridViewWithoutScroll;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.view.MarqueeView.MarqueeView;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.view.MarqueeView.MarqueeViewAdapter;
import sportslottery.gooolal.com.sportslotteryforshanghai.ui.widget.LoadingPager;
import sportslottery.gooolal.com.sportslotteryforshanghai.utils.UIUtils;
/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:53
 * 描    述：app的主页的homeframgent
 * 修订历史：
 * ================================================
 */

public class HomeFragment extends BaseFragment {

    //    @BindView(R.id.home_viewpager)
//    LoopViewPager homeViewpager;
    @BindView(R.id.home_banner)
    Banner homebanNer;
    @BindView(R.id.home_laba_im)
    ImageView homeLabaIm;
    @BindView(R.id.homenotice_tv)
    MarqueeView homenoticeTv;
    @BindView(R.id.home_gridview)
    GridViewWithoutScroll homeGridview;
    @BindView(R.id.main_total_bisai)
    TextView mainTotalBisai;
    @BindView(R.id.main_qici_tv)
    TextView mainQiciTv;
    @BindView(R.id.home_spf_ll)
    LinearLayout homeSpfLl;
    @BindView(R.id.home_2x1_ll)
    LinearLayout home2x1Ll;
    @BindView(R.id.home_hhgg_ll)
    LinearLayout homeHhggLl;
    @BindView(R.id.homecontent_ll)
    LinearLayout homecontentLl;
    @BindView(R.id.home_scr)
    ScrollView homeScr;
    @BindView(R.id.mtext)
    TextView mtext;
    private List<String> mList = new ArrayList();
    private List<String> mNotices = new ArrayList();
    private List<String> mPictureUrl = new ArrayList();
    private HomeGridViewAdapter mGridviewAdapter;


    @Override
    protected View createLoadedView() {
        View mView = UIUtils.inflate(R.layout.home_fragment);
        ButterKnife.bind(this, mView);
        final Calendar mCalendar = Calendar.getInstance();
        long time = System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        long mHour = mCalendar.get(Calendar.HOUR);
        long mMinuts = mCalendar.get(Calendar.MINUTE);
        long mSeconds = mCalendar.get(Calendar.SECOND);

        String[] mTitle = this.getResources().getStringArray(R.array.homegridviewitem);//gridview的标题栏
        String[] mViewpagerTitle = this.getResources().getStringArray(R.array.viewpagertitle);//gridview的标题栏
        mGridviewAdapter = new HomeGridViewAdapter(getActivity(), mTitle);
        homeGridview.setAdapter(mGridviewAdapter);

        mtext.setText(mHour + "时" + mMinuts + "分" + mSeconds + "秒");

        MarqueeViewAdapter marqueeViewAdapter = new MarqueeViewAdapter<String>(mNotices) {
            @Override
            public View getView(MarqueeView parent, int position, String s) {
                final View view = LayoutInflater.from(getActivity()).inflate(R.layout.marqueeview_item, homenoticeTv, false);

                TextView textView = (TextView) view.findViewById(R.id.textView);
                textView.setText(mNotices.get(position));
                return view;
            }
        };
        homenoticeTv.setAdapter(marqueeViewAdapter);//设置适配器
        homebanNer.setImageLoader(new GlideImageLoader());
        homebanNer.setImages(mPictureUrl);
        //设置banner样式
        homebanNer.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置banner动画效果
        homebanNer.setBannerAnimation(Transformer.Stack);
        //设置标题集合（当banner样式有显示title时）
        homebanNer.setBannerTitles(Arrays.asList(mViewpagerTitle));
        //设置自动轮播，默认为true
        homebanNer.isAutoPlay(true);
        //设置轮播时间
        homebanNer.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        homebanNer.setIndicatorGravity(BannerConfig.CENTER);
        homebanNer.start();
        return mView;
    }

    @Override
    protected LoadingPager.LoadResult Load() {
        OkGo.get("http://pic.yesky.com/uploadImages/2014/064/OH4H8VH65DW9.jpg").execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Logger.e("wangluoqingqiuchenggong");
            }
        });
        mList.add("1");
        mPictureUrl.add("http://pic.yesky.com/uploadImages/2014/064/OH4H8VH65DW9.jpg");
        mPictureUrl.add("http://image.tianjimedia.com/uploadImages/2014/214/29/OI6DL137T6Y5.jpg");
        mPictureUrl.add("http://image.tianjimedia.com/uploadImages/2015/141/13/52OXZR221OQ3_1000x500.jpg");
        mPictureUrl.add("http://image.tianjimedia.com/uploadImages/2014/064/200I485U607W.jpg");
        mPictureUrl.add("http://img1.3lian.com/img013/v1/32/d/110.jpg");
        mNotices.add("支付宝明起提现收费 部分用户或回流银行 也有其他方法");
        mNotices.add("三星在中国大陆召回19万台Note7手机 可全额退款");
        mNotices.add("王宝强离婚案10月18号“过堂” 双方或现身");
        for (int i = 0; i < 1000000; i++) {
            mList.add("1");
        }
        return check(mList);
    }

    @Override
    public void onResume() {
        super.onResume();
        //开始轮播
        if (null != homebanNer) {
            homebanNer.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止轮播
        homebanNer.stopAutoPlay();
    }
}
