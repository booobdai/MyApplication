package learningandroid.booobdai.com.constructiondiary.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.aplication.BaseApplication;

/**
 * ================================================
 * 作    者：admin
 * 版    本：1.0
 * 创建日期：2017/7/7
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class WeatherDialogAdapter extends BaseAdapter {

    private int[] weatherIds = {R.mipmap.weather_sunny, R.mipmap.weather_cloudy, R.mipmap.weather_overcast, R.mipmap.weather_light_rain, R.mipmap.weather_moderate_rain, R.mipmap.weather_heavy_rain, R.mipmap.weather_torrential_rain, R.mipmap.weather_shower, R.mipmap.weather_thunder_shower, R.mipmap.weather_hail, R.mipmap.weather_fog, R.mipmap.weather_sleet, R.mipmap.weather_light_snow, R.mipmap.weather_moderate_snow, R.mipmap.weather_heavy_snow, R.mipmap.weather_force_vind};
    private String[] weatherNames = BaseApplication.getApplication().getResources().getStringArray(R.array.weather_names);

    @Override

    public int getCount() {
        return weatherIds.length;
    }

    @Override
    public Object getItem(int position) {
        return weatherIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(BaseApplication.getApplication()).inflate(R.layout.weather_gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.weatherIm.setImageResource(weatherIds[position]);
        viewHolder.weatherTv.setText(weatherNames[position]);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.weather_im)
        ImageView weatherIm;
        @BindView(R.id.weather_tv)
        TextView weatherTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
