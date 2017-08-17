package com.huabiao.aoiin.model;

import android.content.Context;

import com.huabiao.aoiin.bean.CreatNameBean;
import com.huabiao.aoiin.bean.CreatNameBean.RecommendnamelistBean;
import com.huabiao.aoiin.bean.LineChartBean;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.huabiao.aoiin.model.GetJsonToName.getJson;

/**
 * Created by Aoiin-9 on 2017/8/1.
 */

public class AnalysisJson {

    public static void getDenominateName(Context context, final InterfaceManager.CallBackCommon callback) {
        String jsonString = getJson(context, "getcreatnamelist.json");//解析第一层object
        List<RecommendnamelistBean> list = new ArrayList<>();
        CreatNameBean bean = new CreatNameBean();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("recommendnamelist");//解析recommendnamelist里面有4个object
            for (int i = 0; i < jsonArray.length(); i++) {
                RecommendnamelistBean recommendname = new RecommendnamelistBean();
                JSONObject json = jsonArray.getJSONObject(i);
                recommendname.setMeans(json.getString("means"));//取出和进去之后第一层数组相同深度的数据

                JSONObject oj = json.getJSONObject("linechart");
                //取linechart数据
                LineChartBean lineChartBean = new LineChartBean();
                lineChartBean.setClassificationid(oj.getString("classificationid"));
                lineChartBean.setTrademarkclassification(oj.getString("trademarkclassification"));
                lineChartBean.setTradename(oj.getString("tradename"));

                //取Xaxistag数组
                List<String> s1 = new ArrayList<>();
                JSONArray a1 = oj.getJSONArray("Xaxistag");
//                ALog.i("a1 : " + a1.toString() + "   a1.size : " + a1.length());
                for (int m = 0; m < a1.length(); m++) {
                    s1.add(a1.getString(m));
                }
                lineChartBean.setXaxistag(s1);

                //取lines数组
                JSONArray a2 = oj.getJSONArray("lines");
                List<LineChartBean.LinesBean> linesList = new ArrayList<>();
                for (int k = 0; k < a2.length(); k++) {
                    LineChartBean.LinesBean linesBean = new LineChartBean.LinesBean();
                    JSONObject jsobject = a2.getJSONObject(k);
                    //lines Object里面的三个值
                    linesBean.setLinecolor(jsobject.getString("linecolor"));
                    linesBean.setLinename(jsobject.getString("linename"));

                    //取linevalue数组
                    List<Integer> intList = new ArrayList<>();
                    JSONArray array = jsobject.getJSONArray("linevalue");
                    for (int j = 0; j < array.length(); j++) {
                        intList.add(array.getInt(j));
                    }
                    linesBean.setLinevalue(intList);
                    linesList.add(linesBean);
                }
                lineChartBean.setLines(linesList);
                recommendname.setLinechart(lineChartBean);
                list.add(recommendname);
                bean.setRecommendnamelist(list);
            }
            callback.getCallBackCommon(bean);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}