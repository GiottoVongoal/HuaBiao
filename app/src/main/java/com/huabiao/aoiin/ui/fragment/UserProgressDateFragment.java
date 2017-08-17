package com.huabiao.aoiin.ui.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.UserProgressDateBean;
import com.huabiao.aoiin.bean.UserProgressDateBean.UserprogressdatelistBean;
import com.huabiao.aoiin.dateview.OnCalendarClickListener;
import com.huabiao.aoiin.dateview.schedule.ScheduleLayout;
import com.huabiao.aoiin.dateview.schedule.ScheduleRecyclerView;
import com.huabiao.aoiin.model.HomeModel;
import com.huabiao.aoiin.ui.adapter.UserProgressDateAdapter;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import butterknife.Bind;

import static java.lang.Integer.parseInt;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-24 18:35
 * @description 进度详情页 -- 日历
 */
public class UserProgressDateFragment extends BaseFragment {
    @Bind(R.id.user_progress_date_schedule_layout)
    ScheduleLayout slSchedule;
    @Bind(R.id.user_progress_date_srv)
    ScheduleRecyclerView rvScheduleList;

    private UserProgressDateAdapter adapder;
    private List<UserprogressdatelistBean> list;

    @Bind(R.id.user_progress_date_month_title_tv)
    TextView tvTitleMonth;
    @Bind(R.id.user_progress_date_day_title_tv)
    TextView tvTitleDay;

    private String[] mMonthText;
    private String status;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle(status);
        setBackEnable();
    }

    @Override
    public void getIntentValue() {
        super.getIntentValue();
        Bundle bundle = getActivity().getIntent().getExtras();
        String time = bundle.getString("time");
        status = bundle.getString("status");
        string2Date(time);
    }

    private void string2Date(String timeString) {
        timeString = timeString.replace("年", "-");
        timeString = timeString.replace("月", "-");
        timeString = timeString.replace("日", "-");
        // 切割年月日
        String yearStr, monthStr, dateStr;
        // 截取日期
        String[] ymd = timeString.split("-");
        // 判断日期精确度
        yearStr = ymd[0];
        monthStr = ymd.length > 1 ? ymd[1] : "";
        dateStr = ymd.length > 2 ? ymd[2] : "";
        monthStr = monthStr == "" ? Integer.toString(1) : monthStr;
        dateStr = dateStr == "" ? Integer.toString(1) : dateStr;

        int year = parseInt(yearStr.trim());
        int month = Integer.parseInt(monthStr.trim());
        int day = Integer.parseInt(dateStr.trim());

        mMonthText = getResources().getStringArray(R.array.calendar_month);
        tvTitleMonth.setText(mMonthText[month - 1]);
        initSchedule(year, month - 1, day);
    }

    private void initSchedule(int year, int month, int day) {
        slSchedule.initData(year, month, day);
        resetMainTitleDate(year, month, day);
        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                //监听获得点击的年月日
                resetMainTitleDate(year, month, day);
                ALog.i(year + "年" + (month + 1) + "月" + day + "日");
                getDateList(year, month, day);
            }

            @Override
            public void onPageChange(int year, int month, int day) {

            }
        });

        list = new ArrayList<>();
        rvScheduleList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapder = new UserProgressDateAdapter(getActivity(), list);
        rvScheduleList.setAdapter(adapder);
        getDateList(year, month, day);
    }

    private void getDateList(int year, int month, int day) {
        HomeModel.getProgressDateList(getContext(), year, month, day, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    UserProgressDateBean bean = (UserProgressDateBean) mData;
                    adapder.updateListView(bean.getUserprogressdatelist());
                }
            }
        });
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
