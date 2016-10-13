package sportslottery.gooolal.com.sportslotteryforshanghai.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-11-0011 17:02
 * 描    述：homefragment 轮播图加载图片的类
 * 修订历史：
 * ================================================
 */

public class GlideImageLoader implements ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).into(imageView);
    }
}
