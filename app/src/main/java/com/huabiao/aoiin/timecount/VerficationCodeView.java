package com.huabiao.aoiin.timecount;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.wedgit
 * @date 2017-06-05 11:54
 * @description 6位验证码
 */
public class VerficationCodeView extends RelativeLayout {
    private EditText editText;
    private TextView[] TextViews;
    private StringBuffer stringBuffer = new StringBuffer();
    private int count = 4;
    private String inputContent;

    public VerficationCodeView(Context context) {
        this(context, null);
    }

    public VerficationCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerficationCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TextViews = new TextView[4];
        View.inflate(context, R.layout.view_security_code, this);

        editText = (EditText) findViewById(R.id.item_edittext);
        TextViews[0] = (TextView) findViewById(R.id.item_code_iv1);
        TextViews[1] = (TextView) findViewById(R.id.item_code_iv2);
        TextViews[2] = (TextView) findViewById(R.id.item_code_iv3);
        TextViews[3] = (TextView) findViewById(R.id.item_code_iv4);

        editText.setCursorVisible(false);//将光标隐藏
        setListener();
    }

    private void setListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //重点   如果字符不为""时才进行操作
                if (!editable.toString().equals("")) {
                    if (stringBuffer.length() < 4) {
                        stringBuffer.append(editable);
                    } else {
                        //当文本长度大于4位时edittext置空
                        editText.setText("");
                        stringBuffer.delete(4, stringBuffer.length());
                        return;
                    }
                    //将文字添加到StringBuffer中
                    editText.setText("");//添加后将EditText置空  造成没有文字输入的错局
                    //  Log.e("TAG", "afterTextChanged: stringBuffer is " + stringBuffer);
                    count = stringBuffer.length();//记录stringbuffer的长度
                    if (count > 4) {
                        stringBuffer.delete(0, stringBuffer.length());
                        return;
                    }
                    inputContent = stringBuffer.toString();
                    if (stringBuffer.length() == 4) {
                        //文字长度位4  则调用完成输入的监听
                        if (inputCompleteListener != null) {
                            inputCompleteListener.inputComplete();
                        }
                    }
                    for (int i = 0; i < stringBuffer.length(); i++) {
                        TextViews[i].setText(
                                String.valueOf(inputContent.charAt(i)));
                        //设置输入内容的背景样式
                        //TextViews[i].setBackgroundResource(R.mipmap.bg_verify_press);
                    }

                }
            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (onKeyDelete()) return true;
                    return true;
                }
                return false;
            }
        });
    }


    public boolean onKeyDelete() {
        if (count == 0) {
            count = 4;
            return true;
        }
        if (stringBuffer.length() > 0 && stringBuffer.length() <= 4) {
            //删除相应位置的字符
            stringBuffer.delete((count - 1), count);
            count--;
            //   Log.e(TAG, "afterTextChanged: stringBuffer is " + stringBuffer);
            inputContent = stringBuffer.toString();
            TextViews[stringBuffer.length()].setText("");
            if (inputCompleteListener != null)
                inputCompleteListener.deleteContent(true);//有删除就通知manger

        }
        return false;
    }

    /**
     * 清空输入内容
     */
    public void clearEditText() {
        stringBuffer.delete(0, stringBuffer.length());
        inputContent = stringBuffer.toString();
        for (int i = 0; i < TextViews.length; i++) {
            TextViews[i].setText("");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void inputComplete();

        void deleteContent(boolean isDelete);
    }

    /**
     * 获取输入文本
     *
     * @return
     */
    public String getEditContent() {
        return inputContent;
    }

}
