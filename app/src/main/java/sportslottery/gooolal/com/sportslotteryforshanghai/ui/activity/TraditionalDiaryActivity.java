package sportslottery.gooolal.com.sportslotteryforshanghai.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sportslottery.gooolal.com.sportslotteryforshanghai.Base.BaseActivity;
import sportslottery.gooolal.com.sportslotteryforshanghai.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_traditional_diary);
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
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日";
        dayTv.setText(date);
    }
}
