package com.huabiao.aoiin.ui.fragment;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.dateview.OnCalendarClickListener;
import com.huabiao.aoiin.dateview.schedule.ScheduleLayout;
import com.huabiao.aoiin.dateview.schedule.ScheduleRecyclerView;
import com.huabiao.aoiin.ui.adapter.SettingRecyclerViewAdapder;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-24 18:35
 * @description 测试日历
 */
public class UserProgressDateFragment extends BaseFragment {
    @Bind(R.id.schedule_layout)
    ScheduleLayout slSchedule;
    @Bind(R.id.rvScheduleList)
    ScheduleRecyclerView rvScheduleList;

    private SettingRecyclerViewAdapder adapder;
    private String[] text = {"我的收藏", "我的足记", "我的消息", "设置", "我的收藏", "我的足记", "我的消息", "设置", "我的收藏", "我的足记", "我的消息", "设置"};

    @Bind(R.id.tvTitleMonth)
    TextView tvTitleMonth;
    @Bind(R.id.tvTitleDay)
    TextView tvTitleDay;

    private String[] mMonthText;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("进度");
        setBackEnable();

        mMonthText = getResources().getStringArray(R.array.calendar_month);
        tvTitleMonth.setText(mMonthText[Calendar.getInstance().get(Calendar.MONTH)]);
        Calendar calendar = Calendar.getInstance();
        resetMainTitleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                //监听获得点击的年月日
                resetMainTitleDate(year, month, day);
                showToast(year + "年" + (month + 1) + "月" + day + "日");
            }

            @Override
            public void onPageChange(int year, int month, int day) {

            }
        });
        slSchedule.getMonthCalendar().setTodayToView();

        rvScheduleList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapder = new SettingRecyclerViewAdapder(getActivity(), text);
        rvScheduleList.setAdapter(adapder);
    }

    public void resetMainTitleDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        if (year == calendar.get(Calendar.YEAR) &&
                month == calendar.get(Calendar.MONTH) &&
                day == calendar.get(Calendar.DAY_OF_MONTH)) {
            tvTitleMonth.setText(mMonthText[month]);
            tvTitleDay.setText("今天");
        } else {
            if (year == calendar.get(Calendar.YEAR)) {
                tvTitleMonth.setText(mMonthText[month]);
            } else {
                tvTitleMonth.setText(String.format("%s%s", String.format(getString(R.string.calendar_year), year),
                        mMonthText[month]));
            }
            tvTitleDay.setText(String.format(getString(R.string.calendar_day), day));
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.user_progress_date_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
