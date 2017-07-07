package learningandroid.booobdai.com.constructiondiary.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import learningandroid.booobdai.com.constructiondiary.Base.BaseActivity;
import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.dao.DiaryBeanDao;
import learningandroid.booobdai.com.constructiondiary.dao.DiaryDaoUtils;
import learningandroid.booobdai.com.constructiondiary.entity.DiaryBean;
import learningandroid.booobdai.com.constructiondiary.ui.adapter.WeatherDialogAdapter;
import learningandroid.booobdai.com.constructiondiary.ui.view.GridViewWithoutScroll;
import learningandroid.booobdai.com.constructiondiary.utils.StringUtils;

/**
 * ================================================
 * 作    者：boob
 * 版    本：1.0
 * 创建日期：2017/3/2 13:52
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class TraditionalDiaryActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.day_tv)
    AppCompatTextView dayTv;
    @BindView(R.id.project_name_edt)
    MaterialEditText projectNameEdt;
    @BindView(R.id.morning_weather_edt)
    MaterialEditText morningWeatherEdt;
    @BindView(R.id.afternoon_weather_edt)
    MaterialEditText afternoonWeatherEdt;
    @BindView(R.id.morning_temperature_edt)
    MaterialEditText morningTemperatureEdt;
    @BindView(R.id.afternoon_temperature_edt)
    MaterialEditText afternoonTemperatureEdt;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.diary_content_edt)
    MaterialEditText diaryContentEdt;
    @BindView(R.id.diary_zongjie_edt)
    MaterialEditText diaryZongjieEdt;
    @BindView(R.id.author_edt)
    MaterialEditText authorEdt;
    @BindView(R.id.add_diary_tv)
    TextView addDiaryTv;
    @BindView(R.id.update_diary_tv)
    TextView updateDiaryTv;
    private MenuItem mMenuItem;

    private DiaryBeanDao diaryBeanDao;
    private DiaryBean diaryBean;
    private String recordTime = ""; //记录时间
    private long page;//页数也是记录篇数
    private String entryName;//项目名称

    private String morningWeather;//上午天气
    private String afternoonWeather;//下午天气
    private String morningTemperature;//上午温度
    private String afternoonTemperature;//下午温度
    private String carpenterNum;//木匠
    private String bricklayerNum;//泥水
    private String gangJinNum;//钢筋
    private String backmanNum;//杂工
    private String diaryContent;//日志
    private String safetyRecord;//安全记录
    private String recorder;//记录人
    private String beginTime;//新建时间
    private String endTime;//修订时间
    private boolean isNeedCreatedDb = true;//是否需要新建数据,当是编辑页面或者保存过,只需要更新数据库,否则才需要新建
    private DiaryDaoUtils diaryDaoUtils;

    @OnClick(R.id.morning_weather_edt)
    void weatherOnClick() {
        Dialog dialog = new Dialog(this);
        View contentview = LayoutInflater.from(this).inflate(R.layout.weather_dialog, null);
        GridViewWithoutScroll gridViewWithoutScroll = (GridViewWithoutScroll) contentview.findViewById(R.id.gridview_weather);
        gridViewWithoutScroll.setAdapter(new WeatherDialogAdapter());
        dialog.setContentView(contentview);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.traditional_menu, menu);
        mMenuItem = menu.findItem(R.id.action_settings);
        mMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {//保存
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showToast("保存点击了");

                if (isNeedCreatedDb) {
                    Logger.e("");
                    initDiaryBean();

                    isNeedCreatedDb = false;
                } else {

                }

                /*Query<DiaryBean> query = getDiaryDao().getQueryBuilder().where(DiaryBeanDao.Properties.RecordTime.isNotNull()).build();
                List<DiaryBean> diaryBeens = query.list();
                Logger.e(diaryBeens.size() + "diaryBeens的长度");
                for (int i = 0; i < diaryBeens.size(); i++) {
                    Logger.e(diaryBeens.get(i).getRecordTime() + "___记录时间");
                    Logger.e(diaryBeens.get(i).getMorningTemperature() + "___早上温度");
                    Logger.e(diaryBeens.get(i).getAfternoonTemperature() + "___下午温度");
                    Logger.e(diaryBeens.get(i).getDiaryContent() + "___日记内容");
                    Logger.e(diaryBeens.get(i).getMorningWeather() + "___上午天气");
                }*/
//                DiaryBean diaryBean = getDiaryDao().getQueryBuilder().where(DiaryBeanDao.Properties.RecordTime.eq("2017年3月10日")).build().unique();
//                DiaryBean diaryBean = diaryBeanDao.queryBuilder().where(DiaryBeanDao.Properties.RecordTime.eq("2017年3月10日")).build().unique();
                if (diaryBean != null) {
                    Logger.e(diaryBean.getRecordTime());
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    //
    private void initDiaryBean() {
        page = diaryDaoUtils.queryAllDiaryBean().size();
//        page =;//页数也是记录篇数
        entryName = initStringbyEditext(projectNameEdt);
        morningWeather = initStringbyEditext(morningWeatherEdt);
        afternoonWeather = initStringbyEditext(afternoonWeatherEdt);
        morningTemperature = initStringbyEditext(morningTemperatureEdt);
        afternoonTemperature = initStringbyEditext(afternoonTemperatureEdt);
        diaryContent = initStringbyEditext(diaryContentEdt);
        safetyRecord = initStringbyEditext(diaryZongjieEdt);
        recorder = initStringbyEditext(authorEdt);
//        recordTime =;//记录时间
//        carpenterNum;//木匠
//        bricklayerNum;//泥水
//        gangJinNum;//钢筋
//        backmanNum;//杂工
//        diaryContent;//日志
//        safetyRecord;//安全记录
//        recorder;//记录人
//        beginTime;//新建时间
//        endTime;//修订时间
//        diaryBean.setPage(page);

        diaryBean = new DiaryBean(page, entryName, recordTime, morningWeather, afternoonWeather, morningTemperature, afternoonTemperature, carpenterNum, bricklayerNum, gangJinNum, backmanNum, diaryContent, safetyRecord, recorder, beginTime, endTime);
        diaryBean.setEntryName(entryName);
        diaryBean.setRecordTime(recordTime);
        diaryBean.setMorningWeather(morningWeather);
        diaryBean.setAfternoonWeather(afternoonWeather);
        diaryBean.setMorningTemperature(morningTemperature);
        diaryBean.setAfternoonTemperature(afternoonTemperature);
        diaryBean.setDiaryContent(diaryContent);
        Logger.e(diaryContent);
        diaryBean.setSafetyRecord(safetyRecord);
//        diaryBean.setCarpenterNum(carpenterNum);
//        diaryBean.setBricklayerNum(bricklayerNum);
//        diaryBean.setGangJinNum(gangJinNum);
        diaryDaoUtils.insertDiaryBean(diaryBean);
    }

    private String initStringbyEditext(MaterialEditText mEditext) {
        String string;
        if (StringUtils.isEmpty(mEditext.getText().toString())) {
            string = "";
        } else {
            string = mEditext.getText().toString();
        }
        return string;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_traditional_diary);
        initToolBar(toolbar, true, R.string.traditional_diary);
        diaryDaoUtils = new DiaryDaoUtils(this);
        dayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TraditionalDiaryActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(false);
                dpd.setCancelColor(getResources().getColor(R.color.primary));//设置取消按钮的颜色
                dpd.setOkColor(getResources().getColor(R.color.primary));//设置确定按钮的颜色
                dpd.setAccentColor(getResources().getColor(R.color.primary));//设置头部以及选中状态的颜色
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

//        diaryBeanDao = GreenDaoManager.getInstance().getSession().getDiaryBeanDao();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        recordTime = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
        dayTv.setText(recordTime);
    }


}
