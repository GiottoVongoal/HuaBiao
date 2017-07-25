package com.huabiao.aoiin.ui.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

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

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-24 18:35
 * @description 测试日历
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
        getDateList(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

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
