package learningandroid.booobdai.com.constructiondiary.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import learningandroid.booobdai.com.constructiondiary.Base.BaseActivity;
import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.ui.MainActivity;
import me.wangyuwei.particleview.ParticleView;


/**
 * ================================================
 * 作    者：admin
 * 版    本：1.0
 * 创建日期：2017/5/5
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.pv_1)
    ParticleView pv1;
    @BindView(R.id.pv_2)
    ParticleView pv2;
    @BindView(R.id.pv_3)
    ParticleView pv3;
    @BindView(R.id.pv_4)
    ParticleView pv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        pv1.startAnim();
        pv1.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

       /* pv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                pv1.startAnim();
                pv2.startAnim();
                pv3.startAnim();
                pv4.startAnim();
            }
        }, 200);*/
    }
}
