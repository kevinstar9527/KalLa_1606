package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LiveFragment extends BaseFragment {

    private TextView iv_Text;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover_live;
    }

    @Override
    protected void initViews() {
        iv_Text = (TextView) root.findViewById(R.id.iv_text);
        String text = "注册表scaling中数字3和4各代表什么?那个对电脑有好处?";
        /*生成一个可以加工的字符串*/
        SpannableString spannableString = new SpannableString(text);
        //可以设置夜色背景大小图片下划线横线，样式
        BackgroundColorSpan bgSpan = new BackgroundColorSpan(Color.YELLOW);
        /*要指定哪各个字符*/
        spannableString.setSpan(bgSpan,0,2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(18);
        spannableString.setSpan(sizeSpan,0,2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        //颜色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan,0,2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        iv_Text.setText(spannableString);
        //设置图片
        SpannableString spannableString1 = new SpannableString(" ");
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(drawable,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        iv_Text.setText(spannableString1);
        iv_Text.append(spannableString);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }
}
