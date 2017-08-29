package com.huabiao.aoiin.ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.picview.BitmapUtil;

import java.util.List;


/**
 * Created by Aoiin-9 on 2017/7/18.
 */

public class DenominateRotatePanLayout extends View {
    //上下文对象
    private Context context;
    //转盘数目
    private int panNum = 0;
    //
    private Paint dPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint sPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int InitAngle = 0;
    //累加度数
    private int verPanRadius;
    private int diffRadius;
    //
    private List<String> list;
    public static final int FLING_VELOCITY_DOWNSCALE = 4;
    private GestureDetectorCompat mDetector;
    private ScrollerCompat scroller;

    // 自定义的 View 组件, 一般需要实现 三个构造方法, 分别有 一个, 两个, 三个参数;
    public DenominateRotatePanLayout(Context context) {
        this(context, null);
    }

    public DenominateRotatePanLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DenominateRotatePanLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mDetector = new GestureDetectorCompat(context, new RotatePanGestureListener());
        scroller = ScrollerCompat.create(context);
        refreshPan(context);
    }

    private void refreshPan(Context context) {
        //根据list来确定盘的数目
        if (list != null && list.size() != 0) {
            panNum = list.size();
            if (360 % panNum != 0)
                InitAngle = 360 / panNum;
            verPanRadius = 360 / panNum;
            diffRadius = verPanRadius / 2;
            //两个盘的颜色
//            dPaint.setColor(Color.parseColor("#AED79B"));
//            sPaint.setColor(context.getResources().getColor(R.color.yellow_fdd400));
            dPaint.setColor(context.getResources().getColor(R.color.white));
            sPaint.setColor(context.getResources().getColor(R.color.white));
            //盘上文字大小和颜色
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(BitmapUtil.dip2px(context, 16));
            setClickable(true);
        }
    }

    /*组件宽高计算   模式简介：
     MeasureSpec.UNSPECIFIED, 未指定模式
     MeasureSpec.EXACTLY, 精准模式
      MeasureSpec.AT_MOST, 最大模式;
        */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mHeight = BitmapUtil.dip2px(context, 300);
        int mWidth = BitmapUtil.dip2px(context, 300);
//MeasureSpec.getMode(int) : 获取模式;MeasureSpec.getSize(int) : 获取大小;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //与父容器上下左右的距离
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        //宽度等于父容器的宽度减去左padding减去右padding，高度也是一样
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        int MinValue = Math.min(width, height);
        //半径等于宽高最小值除以2
        int radius = MinValue / 2;
        //创建圆弧对象
        RectF rectF = new RectF(getPaddingLeft(), getPaddingTop(), width, height);

        int angle = InitAngle;

        for (int i = 0; i < panNum; i++) {
            if (i % 2 == 0) {
                //绘制圆弧 参数介绍 : 圆弧, 开始度数, 累加度数, 是否闭合圆弧, 画笔
                canvas.drawArc(rectF, angle, verPanRadius, true, dPaint);
            } else {
                canvas.drawArc(rectF, angle, verPanRadius, true, sPaint);
            }
            angle += verPanRadius;
        }
        for (int i = 0; i < panNum; i++) {
            drawText(InitAngle, list.get(i), radius, textPaint, canvas, rectF);
            InitAngle += verPanRadius;
        }

    }


    //DrawText函数的作用很简单，就是在指定的区域内输出格式化的文本。这里是自定义的drawText方法。
    private void drawText(float startAngle, String string, int mRadius, Paint mTextPaint, Canvas mCanvas, RectF mRange) {
        Path path = new Path();
        path.addArc(mRange, startAngle, verPanRadius);
        //drawTextOnPath沿着路径绘制文本，hOffset参数指定水平偏移、vOffset指定垂直偏移
        float textWidth = mTextPaint.measureText(string);
        float hOffset = (float) (mRadius * Math.PI / panNum - textWidth / 2);
        float vOffset = mRadius / 4;
        mCanvas.drawTextOnPath(string, path, hOffset, vOffset, mTextPaint);
    }

    public void setStr(List<String> list) {
        this.list = list;
        refreshPan(context);
        this.invalidate();
    }

    //旋转一圈所需要的时间
    private static final long ONE_WHEEL_TIME = 200;


    /**
     * 开始转动
     *
     * @param pos 如果 pos = -1 则随机，如果指定某个值，则转到某个指定区域
     */
    public void startRotate(int pos) {

        int lap = (int) (Math.random() * 12) + 4;

        int angle = 0;
        if (pos < 0) {
            angle = (int) (Math.random() * 360);
        } else {
            int initPos = queryPosition();
            if (pos > initPos) {
                angle = (pos - initPos) * verPanRadius;
                lap -= 1;
                angle = 360 - angle;
            } else if (pos < initPos) {
                angle = (initPos - pos) * verPanRadius;
            } else {
            }
        }

        int increaseDegree = lap * 360 + angle;
        long time = (lap + angle / 360) * ONE_WHEEL_TIME;
        int DesRotate = increaseDegree + InitAngle;

        //  为了每次都能旋转到转盘的中间位置
        int offRotate = DesRotate % 360 % verPanRadius;
        DesRotate -= offRotate;
        DesRotate += diffRadius;

        ValueAnimator animtor = ValueAnimator.ofInt(InitAngle, DesRotate);
        animtor.setInterpolator(new AccelerateDecelerateInterpolator());
        animtor.setDuration(time);
        animtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int updateValue = (int) animation.getAnimatedValue();
                InitAngle = (updateValue % 360 + 360) % 360;
                ViewCompat.postInvalidateOnAnimation(DenominateRotatePanLayout.this);
            }
        });

        animtor.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (l != null)
                    l.endAnimation(queryPosition());
            }
        });
        animtor.start();
    }


    private int queryPosition() {
        InitAngle = (InitAngle % 360 + 360) % 360;
        int pos = InitAngle / verPanRadius;
        return calcumAngle(pos);
    }

    private int calcumAngle(int pos) {
        if (pos >= 0 && pos <= panNum / 2) {
            pos = panNum / 2 - pos;
        } else {
            pos = (panNum - pos) + panNum / 2;
        }
        return pos;
    }

    public interface AnimationEndListener {
        void endAnimation(int position);//动画结束后返回值为位置
    }

    private AnimationEndListener l;

    public void setAnimationEndListener(AnimationEndListener l) {
        this.l = l;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }


    // ====================== 手势处理 ======================================

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            ALog.i("scroller--queryPosition() = " + queryPosition());
            if (l != null)
                l.endAnimation(queryPosition());
        }
        boolean consume = mDetector.onTouchEvent(event);
        if (consume) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void setRotate(int rotation) {
        rotation = (rotation % 360 + 360) % 360;
        InitAngle = rotation;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()) {
            setRotate(scroller.getCurrY());
        }

        super.computeScroll();
    }

    private class RotatePanGestureListener extends GestureDetector.SimpleOnGestureListener {
        //手势识别，点击，双击，滑动，拖动，
        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float centerX = (DenominateRotatePanLayout.this.getLeft() + DenominateRotatePanLayout.this.getRight()) * 0.5f;
            float centerY = (DenominateRotatePanLayout.this.getTop() + DenominateRotatePanLayout.this.getBottom()) * 0.5f;

            float scrollTheta = vectorToScalarScroll(distanceX, distanceY, e2.getX() - centerX, e2.getY() -
                    centerY);
            int rotate = InitAngle -
                    (int) scrollTheta / FLING_VELOCITY_DOWNSCALE;
            setRotate(rotate);
            return true;
        }

//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            float centerX = (DenominateRotatePanLayout.this.getLeft() + DenominateRotatePanLayout.this.getRight()) * 0.5f;
//            float centerY = (DenominateRotatePanLayout.this.getTop() + DenominateRotatePanLayout.this.getBottom()) * 0.5f;
//
//            float scrollTheta = vectorToScalarScroll(velocityX, velocityY, e2.getX() - centerX, e2.getY() -
//                    centerY);
//
//            scroller.abortAnimation();
//            scroller.fling(0, InitAngle, 0, (int) scrollTheta / FLING_VELOCITY_DOWNSCALE,
//                    0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//            return true;
//        }
    }

    //  判断滑动的方向
    private float vectorToScalarScroll(float dx, float dy, float x, float y) {

        float l = (float) Math.sqrt(dx * dx + dy * dy);

        float crossX = -y;
        float crossY = x;

        float dot = (crossX * dx + crossY * dy);
        float sign = Math.signum(dot);

        return l * sign;
    }


}


