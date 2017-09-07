package com.huabiao.aoiin.timecount;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.huabiao.aoiin.R;

public class TimeCount extends CountDownTimer {
    private Context context;
    private TextView tvGetcode;

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
    }

    public void init(Context context, TextView tvGetcode) {
        this.context = context;
        this.tvGetcode = tvGetcode;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        tvGetcode.setText("重新获取");
        tvGetcode.setClickable(true);
        tvGetcode.setTextColor(context.getResources().getColor(
                R.color.yellow_fdd400));
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        tvGetcode.setClickable(false);
        tvGetcode.setText("重新获取(" + millisUntilFinished / 1000 + "s)");
        tvGetcode.setTextColor(context.getResources().getColor(
                R.color.yellow_fdd400));
    }
}
