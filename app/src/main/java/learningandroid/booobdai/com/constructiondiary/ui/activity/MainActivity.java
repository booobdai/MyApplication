package learningandroid.booobdai.com.constructiondiary.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import learningandroid.booobdai.com.constructiondiary.Base.BaseActivity;
import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.dao.DiaryDaoUtils;
import learningandroid.booobdai.com.constructiondiary.entity.DiaryBean;
import learningandroid.booobdai.com.constructiondiary.ui.adapter.GlideImageLoader;
import learningandroid.booobdai.com.constructiondiary.ui.adapter.MainAcAdapter;
import learningandroid.booobdai.com.constructiondiary.utils.AsyncTaskUtils;
import learningandroid.booobdai.com.constructiondiary.utils.IDataCallBack;

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


    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.floating_bottom_button)
    FloatingActionButton floatingBottomButton;//写
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.main_recycleview)
    RecyclerView mainRecyclerView;
    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout pullToRefresh;
    private AccountHeader headerResult;
    private Drawer result;

    private List<DiaryBean> diaryBeanList;
    private MainAcAdapter mainAcAdapter;
    private DiaryDaoUtils diaryDaoUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        diaryDaoUtils = new DiaryDaoUtils(this);
        pullToRefresh.setColorSchemeResources(R.color.primary, R.color.color_red, R.color.color_green);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            AsyncTaskUtils.doAsync(new IDataCallBack<Object>() {
                @Override
                public void onTaskBefore() {
                    pullToRefresh.setRefreshing(true);
                    diaryBeanList = diaryDaoUtils.queryAllDiaryBean();
                }

                @Override
                public Object onTasking(Void... params) {
                    return null;
                }

                @Override
                public void onTaskAfter(Object result) {
                    pullToRefresh.setRefreshing(false);
                    if (mainAcAdapter != null) {
                        mainAcAdapter.notifyDataSetChanged();
                    } else {
                        mainAcAdapter = new MainAcAdapter(MainActivity.this, diaryBeanList);
                        mainRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));//这里用线性显示 类似于listview
                    }
                }
            });
            }
        });
        if (diaryBeanList == null)

        {
            diaryBeanList = new ArrayList<>();
        }

        diaryBeanList = diaryDaoUtils.queryAllDiaryBean();
        Logger.e(diaryBeanList.size() + "MainActivity");

        mainAcAdapter = new

                MainAcAdapter(this, diaryBeanList);

        mainRecyclerView.setLayoutManager(new

                LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(

                getString(R.string.drawer_item_collapsing_toolbar_drawer));
        mainRecyclerView.setAdapter(mainAcAdapter);
        headerResult = new

                AccountHeaderBuilder()
                .

                        withActivity(this)
                .

                        withCompactStyle(false)
                .

                        withHeaderBackground(R.drawable.header)
                .

                        withSavedInstance(savedInstanceState)
                .

                        build();

        result = new

                DrawerBuilder()
                .

                        withActivity(this)
                .

                        withAccountHeader(headerResult)
                .

                        withToolbar(toolbar)
                .

                        withFullscreen(true)
                .

                        addDrawerItems(
                                new PrimaryDrawerItem().

                                        withName(R.string.drawer_item_home).

                                        withIcon(FontAwesome.Icon.faw_home).

                                        withIdentifier(1).

                                        withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                            @Override
                                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {//点击主页的时间
                                                Intent intent = new Intent(MainActivity.this, TraditionalDiaryActivity.class);
                                                startActivity(intent);
                                                return false;
                                            }
                                        }),
                                new

                                        SwitchDrawerItem().

                                        withName(R.string.drawer_item_free_play).

                                        withCheckable(true).

                                        withIcon(FontAwesome.Icon.faw_gamepad).

                                        withOnCheckedChangeListener(new OnCheckedChangeListener() {//开关条目,监听开关
                                            @Override
                                            public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView,
                                                                         boolean isChecked) {

                                            }
                                        }),
                                new

                                        PrimaryDrawerItem().

                                        withName(R.string.drawer_item_custom).

                                        withIcon(FontAwesome.Icon.faw_eye),
                                new

                                        SectionDrawerItem().

                                        withName(R.string.drawer_item_section_header),
                                new

                                        SecondaryDrawerItem().

                                        withName(R.string.drawer_item_settings).

                                        withIcon(FontAwesome.Icon.faw_cog),
                                new

                                        SecondaryDrawerItem().

                                        withName(R.string.drawer_item_help).

                                        withIcon(FontAwesome.Icon.faw_question).

                                        withEnabled(true),
                                new

                                        SecondaryDrawerItem().

                                        withName(R.string.drawer_item_open_source).

                                        withIcon(FontAwesome.Icon.faw_github),
                                new

                                        SecondaryDrawerItem().

                                        withName(R.string.drawer_item_contact).

                                        withIcon(FontAwesome.Icon.faw_bullhorn),
                                new

                                        DividerDrawerItem()
                        )
                .

                        withSavedInstance(savedInstanceState)
                .

                        build();

        fillFab();

        loadBackdrop();

    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    private void loadBackdrop() {
//        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);//
        Logger.e("加载图片");
//        backdrop.setImageResource(R.drawable.header);
        new GlideImageLoader().displayImage(this, "http://www.sinaimg.cn/dy/slidenews/1_img/2017_17/63957_890134_720151.jpg", backdrop);
//        Picasso.with(this).load("http://www.sinaimg.cn/dy/slidenews/1_img/2017_17/63957_890134_720151.jpg").into(backdrop);
    }

    private void fillFab() {
//        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_codepen).actionBar().color(Color.WHITE));

        floatingBottomButton.setImageResource(R.mipmap.pen);
        floatingBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TraditionalDiaryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (result != null && result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                exitBy2Click();
            }
            //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, R.string.exitapp, Toast.LENGTH_SHORT).show();
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