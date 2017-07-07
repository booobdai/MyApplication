package learningandroid.booobdai.com.constructiondiary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learningandroid.booobdai.com.constructiondiary.R;
import learningandroid.booobdai.com.constructiondiary.entity.DiaryBean;

/**
 * ================================================
 * 作    者：admin
 * 版    本：1.0
 * 创建日期：2017/5/8
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class MainAcAdapter extends RecyclerView.Adapter<MainAcAdapter.MainViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<DiaryBean> diaryBeanList;

    public MainAcAdapter(Context mContext,List<DiaryBean> diaryBeanList) {
        this.diaryBeanList=diaryBeanList;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(mLayoutInflater.inflate(R.layout.main_recv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.title.setText("振奋人心！\n");
            holder.content.setText(
                    "刚刚，微信支付正式宣布：进军美国！\n" +
                            "\n" +
                            "这是中国企业再一次让美国人胆颤心惊！\n" +
                            "\n" +
                            "2017年5月4日，微信支付宣布携手CITCON正式进军美国，通过微信支付，在美国的衣食住行均可直接用人民币结算。据报道，与旅游相关的酒店，乐园，码头，餐厅，小吃店，娱乐场所等，买单时使用微信支付，都是妥妥的了。\n" +
                            "\n" +
                            "据悉，10年签证等宽松的美国签证政策，以及中美航线的不断增加，为微信支付在美国的推出提供了条件。\n" +
                            "\n" +
                            "当然，微信支付进军美国之后，赴美人群就可以在美国享受无现金支付的便利了。通过微信支付，在美国的衣食住行均可直接用人民币结算。");
        } else {
            holder.title.setText("雷军：小米如果活不下去 米粉会心甘情愿捐款！\n");
            holder.content.setText("日前，小米科技CEO雷军又一次出现在武汉，除了签署120亿元的战略合作协议之外。还在武汉上了一堂“创业课”。\n" +
                    "\n" +
                    "在这场交流会中，不时的讲到动情之处，中间雷军哽咽了几次，原话是：“万一哪天小米活不下去了，我们就发起募捐，我相信米粉朋友们一定会给我们捐款的，我相信他们会心甘情愿的。”\n" +
                    "\n" +
                    "“因为我们是真正在全心全意为米粉服务，为了给他们提供最酷的产品，为了能以最厚道的价格让米粉们都买得起，七年来我每天都过得非常焦虑，我付出了全部的热情。”");
        }
    }

    @Override
    public int getItemCount() {
        if (diaryBeanList==null){
            return 0;
        }
        return diaryBeanList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.title)
        TextView title;

        MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
