package com.huabiao.aoiin.picview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.blankj.ALog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BitmapUtil {

	/*
     * 从资源中获取Bitmap Resources res = getResources(); Bitmap bmp =
	 * BitmapFactory.decodeResource(res, R.mipmap.icon);
	 */

    // 图片转字节数组
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        if (null == bm) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 50, baos);
        return baos.toByteArray();
    }

    // byte[] → Bitmap
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    // 删除图片
    public static void deleteFile(String signPath) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(signPath);
            if (file.exists()) {
                file.delete();
                ALog.i("", "删除完成");
            } else {
                ALog.i("", "文件未找到[" + signPath + "]");
            }
        } else {
            ALog.i("", "SD卡不存在");
        }
    }

    // 图片旋转
    public static Bitmap adjustPhotoRotation(Bitmap bm,
                                             final int orientationDegree) {
        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2,
                (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(),
                Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);

        return bm1;
    }

    // 保存图片
    public static String createFile(Bitmap mSignBitmap) {
        ByteArrayOutputStream baos = null;
        String _path = null;
        try {
            String sign_dir = Environment.getExternalStorageDirectory()
                    + File.separator;
            _path = sign_dir + System.currentTimeMillis() + ".jpg";
            baos = new ByteArrayOutputStream();
            mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] photoBytes = baos.toByteArray();
            if (photoBytes != null) {
                new FileOutputStream(new File(_path)).write(photoBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _path;
    }

    /**
     * 压缩图片
     *
     * @param image
     * @param quality
     * @return
     */
    public static Bitmap compressImageQuality(Bitmap image, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.reset();// 重置baos即清空baos
        image.compress(Bitmap.CompressFormat.JPEG, quality, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(bais, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    // 尺寸压缩
    public static Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
        if (bm.getHeight() > bm.getWidth()) {
            bm = adjustPhotoRotation(bm, 90);
        }
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
                true);
        return newbm;

    }

    public static Bitmap getBitmap(Bitmap image, double widthPixels,
                                   double heightPixels) {
        // if(image.getHeight()>image.getWidth()){
        // image = adjustPhotoRotation(image, 270);
        // // return adjustPhotoRotation(image, 270);
        // }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(bais, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int heightRatio = (int) Math.ceil(newOpts.outHeight / (heightPixels));
        int widthRatio = (int) Math.ceil(newOpts.outWidth / widthPixels);
        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                newOpts.inSampleSize = heightRatio;
            } else {
                newOpts.inSampleSize = widthRatio;
            }
        }
        Log.i("", "getBitmap(Bitmap image...)" + newOpts.inSampleSize);
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bais = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(bais, null, newOpts);
        return bitmap;
    }

    // 不加载到内存压缩图片
    public static Bitmap getBitmap(String imageFilePath, int widthPixels,
                                   int heightPixels) {
        int displayWidth = widthPixels;
        int displayHeight = heightPixels / 2;
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight
                / displayHeight);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth
                / displayWidth);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }
        Log.i("", "getBitmap(String imageFilePath...)"
                + bmpFactoryOptions.inSampleSize);
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
        return bmp;
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        Log.i("", "zoomImage(Bitmap image...)" + scaleWidth);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e("TTTTTTTTActivity", "failed getViewBitmap(" + v + ")",
                    new RuntimeException());
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.e("px2dip", "pxValue:" + pxValue + "scale:" + scale);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将彩色图转换为黑白图
     *
     * @param
     * @return 返回转换好的位图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565);

        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, width,
                height);
        return resizeBmp;
    }

    public static byte[] saveBmpToJbg(Bitmap bitmap) {
        /*
         * // 位图大小 int nBmpWidth = bitmap.getWidth(); int nBmpHeight =
		 * bitmap.getHeight(); Log.i("bitmap", "width:"
		 * +nBmpWidth+"height"+nBmpHeight); // 图像数据大小 int mBmpWidth = 0; int
		 * mBmpHeight = nBmpHeight; if (nBmpWidth %8 != 0) { mBmpWidth += 1; }
		 * mBmpWidth += (nBmpWidth /8); mBmpWidth += (mBmpWidth %4);
		 *
		 *
		 * byte[] bmpData = getBitmapData(bitmap);
		 * Log.i("BitmapUtil压缩前",62+bmpData.length+"B"); //jbigUtils jbig = new
		 * jbigUtils(); byte[] jbigBuffer = new byte[10000]; int jbigBufferSize
		 * = jbig.jEncoder(mBmpWidth *8, mBmpHeight, 1, bmpData, jbigBuffer);
		 * byte[] jData = new byte[jbigBufferSize]; System.arraycopy(jbigBuffer,
		 * 0, jData, 0, jbigBufferSize); jbigBuffer = null;
		 * Log.i("BitmapUtil压缩后",jData.length+"B"); return jData;
		 */
        return null;

    }

    public static Bitmap getBmpFromJbg(byte[] jbigData, int nBmpWidth,
                                       int nBmpHeight) {
        /*
         * Bitmap bitmap = null; byte[] bData = new byte[100000]; jbigUtils jbig
		 * = new jbigUtils(); Log.i("BitmapUtil解压前",jbigData.length+"B"); int
		 * jBmpSize = jbig.jDecoder(jbigData.length, jbigData, bData);
		 * Log.i("BitmapUtil解压后",62+jBmpSize+"B"); byte[] bmpData = new
		 * byte[jBmpSize+62];
		 * System.arraycopy(getBmpHeader(nBmpWidth,nBmpHeight),0,bmpData,0,62);
		 * System.arraycopy(bData, 0, bmpData,62, jBmpSize); bitmap =
		 * bytes2Bimap(bmpData); Log.i("BitmapUtil","bitmap:" +
		 * bitmap+"("+bitmap.getWidth()+","+bitmap.getHeight()+")"); return
		 * bitmap;
		 */
        return null;
    }

    public static byte[] getBmpHeader(int nBmpWidth, int nBmpHeight) {
        int mBmpWidth = 0;
        int mBmpHeight = nBmpHeight;
        if (nBmpWidth % 8 != 0) {
            mBmpWidth += 1;
        }
        mBmpWidth += (nBmpWidth / 8);
        mBmpWidth += (mBmpWidth % 4);
        int bufferSize = (mBmpWidth * mBmpHeight) + 2;
        byte[] bmpHeaderInfo = new byte[62];
        try {
            // 存储文件名
            byte[] bmpHeader = bmpHeaderInfo;
            int pos = 0;
            // bmp文件头
            int bfType = 0x4d42;
            long bfSize = 14 + 48 + bufferSize;
            int bfReserved1 = 0;
            int bfReserved2 = 0;
            long bfOffBits = 14 + 48;
            // 保存bmp文件头
            writeWord(bmpHeader, pos, bfType);
            pos += 2;

            writeDword(bmpHeader, pos, bfSize);
            pos += 4;

            writeWord(bmpHeader, pos, bfReserved1);
            pos += 2;

            writeWord(bmpHeader, pos, bfReserved2);
            pos += 2;

            writeDword(bmpHeader, pos, bfOffBits);
            pos += 4;
            // bmp信息头
            long biSize = 40L;
            long biWidth = nBmpWidth;
            long biHeight = nBmpHeight;
            int biPlanes = 1;
            int biBitCount = 1;
            long biCompression = 0L;
            long biSizeImage = bufferSize;
            long biXpelsPerMeter = 2834L;
            long biYPelsPerMeter = 2834L;
            long biClrUsed = 0L;
            long biClrImportant = 0L;
            long color0 = 16777215L;
            long color1 = 0L;
            // 保存bmp信息头
            writeDword(bmpHeader, pos, biSize);
            pos += 4;
            writeLong(bmpHeader, pos, biWidth);
            pos += 4;
            writeLong(bmpHeader, pos, biHeight);
            pos += 4;
            writeWord(bmpHeader, pos, biPlanes);
            pos += 2;
            writeWord(bmpHeader, pos, biBitCount);
            pos += 2;
            writeDword(bmpHeader, pos, biCompression);
            pos += 4;
            writeDword(bmpHeader, pos, biSizeImage);
            pos += 4;
            writeLong(bmpHeader, pos, biXpelsPerMeter);
            pos += 4;
            writeLong(bmpHeader, pos, biYPelsPerMeter);
            pos += 4;
            writeDword(bmpHeader, pos, biClrUsed);
            pos += 4;
            writeDword(bmpHeader, pos, biClrImportant);
            pos += 4;
            writeDword(bmpHeader, pos, color1);
            pos += 4;
            writeDword(bmpHeader, pos, color0);
            pos += 4;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmpHeaderInfo;
    }

    public static byte[] getBitmapData(Bitmap bitmap) {
        if (bitmap == null)
            return null;
        // 位图大小
        int nBmpWidth = bitmap.getWidth();
        int nBmpHeight = bitmap.getHeight();
        Log.i("bitmap", "width:" + nBmpWidth + "height" + nBmpHeight);
        // 图像数据大小
        int mBmpWidth = 0;
        int mBmpHeight = nBmpHeight;
        if (nBmpWidth % 8 != 0) {
            mBmpWidth += 1;
        }
        mBmpWidth += (nBmpWidth / 8);
        mBmpWidth += (mBmpWidth % 4);
        int bufferSize = (mBmpWidth * mBmpHeight) + 2;
        // 像素扫描
        byte bmpData[] = new byte[bufferSize];
        for (int i = 0; i < bufferSize; ++i) {
            bmpData[i] = 0x00;
        }
        int wWidth = mBmpWidth;
        for (int nCol = 0, nRealCol = nBmpHeight - 1; nCol < nBmpHeight; ++nCol, --nRealCol) {
            int wByteIdex = 0;
            int nByte = 0;
            for (int wRow = 0; wRow < nBmpWidth; wRow++) {
                int clr = bitmap.getPixel(wRow, nCol);
                int red = Color.red(clr);
                int blue = Color.blue(clr);
                int green = Color.green(clr);

                if (red == 0 && blue == 0 && green == 0) {
                    bmpData[nRealCol * wWidth + wByteIdex] = (byte) (bmpData[nRealCol
                            * wWidth + wByteIdex] << 1 & 0xff);
                } else {
                    bmpData[nRealCol * wWidth + wByteIdex] = (byte) (((bmpData[nRealCol
                            * wWidth + wByteIdex] << 1) | 0x01) & 0xff);
                }
                nByte++;
                if (nByte == 8) {
                    nByte = 0;
                    wByteIdex++;
                }

            }

            if (nByte != 0) {
                for (; nByte < 8; ++nByte) {
                    bmpData[nRealCol * wWidth + wByteIdex] = (byte) (bmpData[nRealCol
                            * wWidth + wByteIdex] << 1 & 0xff);
                }
            }
        }
        return bmpData;
    }

    /**
     * 将Bitmap存为 .bmp格式图片
     *
     * @param bitmap
     */
    public static byte[] savaBitmapToBmp(Bitmap bitmap, String fileName) {
        if (bitmap == null)
            return null;
        Log.i("bitmap", "savaBitmapToBmp::width:" + bitmap.getWidth()
                + "height" + bitmap.getHeight());
        byte[] bmpHeader = getBmpHeader(bitmap.getWidth(), bitmap.getHeight());
        byte bmpData[] = getBitmapData(bitmap);
        byte[] bmp = new byte[bmpHeader.length + bmpData.length];
        try {
            Log.i("bitmap", "fileName:" + fileName);
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileos = new FileOutputStream(fileName);
            fileos.write(bmpHeader);
            fileos.write(bmpData);
            fileos.flush();
            fileos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    protected static void writeWord(byte[] data, int pos, int value)
            throws IOException {
        byte[] b = new byte[2];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        System.arraycopy(b, 0, data, pos, 2);
    }

    protected static void writeDword(byte[] data, int pos, long value)
            throws IOException {
        byte[] b = new byte[4];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        b[2] = (byte) (value >> 16 & 0xff);
        b[3] = (byte) (value >> 24 & 0xff);

        System.arraycopy(b, 0, data, pos, 4);
    }

    protected static void writeLong(byte[] data, int pos, long value)
            throws IOException {
        byte[] b = new byte[4];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        b[2] = (byte) (value >> 16 & 0xff);
        b[3] = (byte) (value >> 24 & 0xff);
        System.arraycopy(b, 0, data, pos, 4);
    }

    // ***********************************
    private void writeByte(FileOutputStream stream, int value)
            throws IOException {
        byte[] b = new byte[1];
        b[0] = (byte) (value & 0xff);
        stream.write(b);
    }

    private void writeWord(FileOutputStream stream, int value)
            throws IOException {
        byte[] b = new byte[2];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        stream.write(b);
    }

    private void writeDword(FileOutputStream stream, long value)
            throws IOException {
        byte[] b = new byte[4];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        b[2] = (byte) (value >> 16 & 0xff);
        b[3] = (byte) (value >> 24 & 0xff);
        stream.write(b);
    }

    private void writeLong(FileOutputStream stream, long value)
            throws IOException {
        byte[] b = new byte[4];
        b[0] = (byte) (value & 0xff);
        b[1] = (byte) (value >> 8 & 0xff);
        b[2] = (byte) (value >> 16 & 0xff);
        b[3] = (byte) (value >> 24 & 0xff);
        stream.write(b);
    }

    // 从画板图像生成用于压缩的数据，与save1bBmpx生成的数据相反，否则上送的数据、用压缩数据解压出来的图片都是反的
    public void save1bBmpFile(Bitmap bitmap, String filename) {
        if (bitmap == null)
            return;
        // 位图大小
        int nBmpWidth = bitmap.getWidth();
        int nBmpHeight = bitmap.getHeight();
        // 图像数据大小
        int wid = nBmpWidth % 32;
        if (wid > 0) {
            wid = 32 - nBmpWidth % 32;
        }
        wid += nBmpWidth;// 宽度必须是4的倍数，位图数据是以4个字节为一组来处理的。

        int bufferSize = nBmpHeight * wid / 8;
        try {
            // 存储文件名
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            // file.deleteOnExit();
            FileOutputStream fileos = new FileOutputStream(filename);
            // bmp文件头
            int bfType = 0x4d42;
            long bfSize = 14 + 40 + 2 * 4 + bufferSize;
            int bfReserved1 = 0;
            int bfReserved2 = 0;
            long bfOffBits = 14 + 40 + 2 * 4;
            // 保存bmp文件头
            writeWord(fileos, bfType);
            writeDword(fileos, bfSize);
            writeWord(fileos, bfReserved1);
            writeWord(fileos, bfReserved2);
            writeDword(fileos, bfOffBits);
            // bmp信息头
            long biSize = 40L;
            long biWidth = nBmpWidth;
            long biHeight = nBmpHeight;
            int biPlanes = 1;
            int biBitCount = 1;
            long biCompression = 0L;
            long biSizeImage = bufferSize;
            long biXpelsPerMeter = 0L;
            long biYPelsPerMeter = 0L;
            long biClrUsed = 0L;
            long biClrImportant = 2L;
            // 保存bmp信息头
            writeDword(fileos, biSize);
            writeLong(fileos, biWidth);
            writeLong(fileos, biHeight);
            writeWord(fileos, biPlanes);
            writeWord(fileos, biBitCount);
            writeDword(fileos, biCompression);
            writeDword(fileos, biSizeImage);
            writeLong(fileos, biXpelsPerMeter);
            writeLong(fileos, biYPelsPerMeter);
            writeDword(fileos, biClrUsed);
            writeDword(fileos, biClrImportant);

            // 调色板
            byte rgbBlue0 = 0x0;
            byte rgbGreen0 = 0x0;
            byte rgbRed0 = 0x0;
            byte rgbReserved0 = 0x0;
            byte rgbBlue1 = (byte) 0xff;
            byte rgbGreen1 = (byte) 0xff;
            byte rgbRed1 = (byte) 0xff;
            byte rgbReserved1 = (byte) 0x00;
            // 保存调色板
            writeByte(fileos, rgbBlue0);
            writeByte(fileos, rgbGreen0);
            writeByte(fileos, rgbRed0);
            writeByte(fileos, rgbReserved0);
            writeByte(fileos, rgbBlue1);
            writeByte(fileos, rgbGreen1);
            writeByte(fileos, rgbRed1);
            writeByte(fileos, rgbReserved1);

            // 像素扫描
            byte bmpData[] = new byte[bufferSize];
            int wWidth = (nBmpWidth * 3 + nBmpWidth % 4);
            int index = 0;
            for (int nCol = 0; nCol < nBmpHeight; ++nCol) {
                for (int wRow = 0; wRow < nBmpWidth; ) {
                    int clr;
                    clr = bitmap.getPixel(wRow++, nCol);
                    int color0 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color1 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color2 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color3 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color4 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color5 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color6 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    clr = bitmap.getPixel(wRow, nCol);
                    int color7 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 255 ^ 1;

                    // wRow++必须写在下面这句
                    bmpData[nCol * (wid / 8) + wRow++ / 8] = (byte) ((color0 << 7)
                            | (color1 << 6)
                            | (color2 << 5)
                            | (color3 << 4)
                            | (color4 << 3) | (color5 << 2) | (color6 << 1) | (color7));

                }
            }

            fileos.write(bmpData);
            fileos.flush();
            fileos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从画板直接生成bmp图像
    public void save1bBmpxFile(Bitmap bitmap, String filename) {
        if (bitmap == null)
            return;
        // 位图大小
        int nBmpWidth = bitmap.getWidth();
        int nBmpHeight = bitmap.getHeight();
        // 图像数据大小
        int wid = nBmpWidth % 32;
        if (wid > 0) {
            wid = 32 - nBmpWidth % 32;
        }
        wid += nBmpWidth;// 宽度必须是4的倍数，位图数据是以4个字节为一组来处理的。

        int bufferSize = nBmpHeight * wid / 8;
        try {
            // 存储文件名
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileos = new FileOutputStream(filename);
            // bmp文件头
            int bfType = 0x4d42;
            long bfSize = 14 + 40 + 2 * 4 + bufferSize;
            int bfReserved1 = 0;
            int bfReserved2 = 0;
            long bfOffBits = 14 + 40 + 2 * 4;
            // 保存bmp文件头
            writeWord(fileos, bfType);
            writeDword(fileos, bfSize);
            writeWord(fileos, bfReserved1);
            writeWord(fileos, bfReserved2);
            writeDword(fileos, bfOffBits);
            // bmp信息头
            long biSize = 40L;
            long biWidth = nBmpWidth;
            long biHeight = nBmpHeight;
            int biPlanes = 1;
            int biBitCount = 1;
            long biCompression = 0L;
            long biSizeImage = bufferSize;
            long biXpelsPerMeter = 0L;
            long biYPelsPerMeter = 0L;
            long biClrUsed = 0L;
            long biClrImportant = 2L;
            // 保存bmp信息头
            writeDword(fileos, biSize);
            writeLong(fileos, biWidth);
            writeLong(fileos, biHeight);
            writeWord(fileos, biPlanes);
            writeWord(fileos, biBitCount);
            writeDword(fileos, biCompression);
            writeDword(fileos, biSizeImage);
            writeLong(fileos, biXpelsPerMeter);
            writeLong(fileos, biYPelsPerMeter);
            writeDword(fileos, biClrUsed);
            writeDword(fileos, biClrImportant);

            // 调色板
            byte rgbBlue0 = 0x0;
            byte rgbGreen0 = 0x0;
            byte rgbRed0 = 0x0;
            byte rgbReserved0 = 0x0;
            byte rgbBlue1 = (byte) 0xff;
            byte rgbGreen1 = (byte) 0xff;
            byte rgbRed1 = (byte) 0xff;
            byte rgbReserved1 = (byte) 0x00;
            // 保存调色板
            writeByte(fileos, rgbBlue0);
            writeByte(fileos, rgbGreen0);
            writeByte(fileos, rgbRed0);
            writeByte(fileos, rgbReserved0);
            writeByte(fileos, rgbBlue1);
            writeByte(fileos, rgbGreen1);
            writeByte(fileos, rgbRed1);
            writeByte(fileos, rgbReserved1);

            // 像素扫描
            byte bmpData[] = new byte[bufferSize];
            int wWidth = (nBmpWidth * 3 + nBmpWidth % 4);
            int index = 0;
            for (int nCol = 0, nRealCol = nBmpHeight - 1; nCol < nBmpHeight; ++nCol, --nRealCol) {
                for (int wRow = 0; wRow < nBmpWidth; ) {
                    int clr;
                    clr = bitmap.getPixel(wRow++, nCol);
                    int color0 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color1 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color2 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color3 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color4 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color5 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow++, nCol);
                    int color6 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    clr = bitmap.getPixel(wRow, nCol);
                    int color7 = (Color.blue(clr) + Color.green(clr) + Color
                            .red(clr)) / 3 / 200;

                    // wRow++必须写在下面这句
                    bmpData[nRealCol * (wid / 8) + wRow++ / 8] = (byte) ((color0 << 7)
                            | (color1 << 6)
                            | (color2 << 5)
                            | (color3 << 4)
                            | (color4 << 3) | (color5 << 2) | (color6 << 1) | (color7));

                }
            }

            fileos.write(bmpData);
            fileos.flush();
            fileos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取压缩图片Bitmap
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds设置为true，可以不把图片读到内存中,但依然可以计算出图片的大小
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        // inSampleSize就是缩放值。 inSampleSize为1表示宽度和高度不缩放，为2表示压缩后的宽度与高度为原来的1/2
        options.inSampleSize = calculateInSampleSize(options, 480, 480);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // heightRatio是图片原始高度与压缩后高度的倍数
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            // widthRatio是图片原始宽度与压缩后宽度的倍数
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap loadLocalImage(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Bitmap loadLocalImage(String filePath) throws Exception {
        InputStream is = new FileInputStream(filePath);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 4;
        BitmapFactory.decodeStream(is, null, opt);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public static Bitmap createImageThumbnail(InputStream is, String url) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPurgeable = true;
        BitmapFactory.decodeStream(is, null, opts);

        opts.inSampleSize = computeSampleSize(opts, -1, 480 * 800);
        opts.inJustDecodeBounds = false;
        opts.inPurgeable = true;

        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream, null, opts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap createImageThumbnail(String filePath) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);

        opts.inSampleSize = computeSampleSize(opts, -1, 480 * 800);
        opts.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(filePath, opts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap createImageThumbnailForCard(String filePath) {
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);

        //opts.inSampleSize = computeSampleSize(opts, -1, 480 * 800);
        opts.inSampleSize = 2;
        opts.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(filePath, opts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 480 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * Bitmap to Drawable
     *
     * @param bitmap
     * @param mcontext
     * @return
     */
    public static Drawable bitmapToDrawble(Bitmap bitmap, Context mcontext) {
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);
        return drawable;
    }

    public static byte[] readInstream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        baos.close();
        inputStream.close();
        return baos.toByteArray();
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 质量压缩
     *
     * @param imageBitmap
     * @return
     */
    private static Bitmap qualityCompressionImage(Bitmap imageBitmap) {
        if (imageBitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(bais, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     *
     * @param filePath
     * @return
     */
    public static Bitmap ratioCompressionImage(String filePath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int realWidth = newOpts.outWidth;
        int realHeight = newOpts.outHeight;
        //现在主流手机比较多是1280*960分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为1280f
        float ww = 960f;//这里设置宽度为960f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int sacle = 1;//sacle=1表示不缩放
        if (realWidth > realHeight && realWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outWidth / ww);
        } else if (realWidth < realHeight && realHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outHeight / hh);
        }
        if (sacle <= 0)
            sacle = 1;
        newOpts.inSampleSize = sacle;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(filePath, newOpts);
        return qualityCompressionImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 图片按比例大小压缩方法（根据Bitmap图片压缩）
     *
     * @param imageBitmap
     * @return
     */
    public static Bitmap ratioCompressionBitmap(Bitmap imageBitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeStream(bais, null, newOpts);

        newOpts.inJustDecodeBounds = false;
        int realWidth = newOpts.outWidth;
        int realHeight = newOpts.outHeight;
        //现在主流手机比较多是1280*960分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为1280f
        float ww = 960f;//这里设置宽度为960f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int sacle = 1;//be=1表示不缩放
        if (realWidth > realHeight && realWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outWidth / ww);
        } else if (realWidth < realHeight && realHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outHeight / hh);
        }
        if (sacle <= 0)
            sacle = 1;
        newOpts.inSampleSize = sacle;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bais = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(bais, null, newOpts);
        return qualityCompressionImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 图片按比例大小压缩方法（根据IO获取图片并压缩）
     *
     * @param inputStream
     * @param inStream
     * @return
     */
    public static Bitmap ratioCompressionStream(InputStream inputStream, InputStream inStream) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, newOpts);

        newOpts.inJustDecodeBounds = false;
        int realWidth = newOpts.outWidth;
        int realHeight = newOpts.outHeight;
        //现在主流手机比较多是1280*960分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为1280f
        float ww = 960f;//这里设置宽度为960f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int sacle = 1;//sacle=1表示不缩放
        if (realWidth > realHeight && realWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outWidth / ww);
        } else if (realWidth < realHeight && realHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outHeight / hh);
        }
        if (sacle <= 0)
            sacle = 1;
        newOpts.inSampleSize = sacle;//设置缩放比例

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeStream(inStream, null, newOpts);
        return qualityCompressionImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    /**
     * @param filePath
     * @return
     */
    public static Bitmap decodeBitmap(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Config.RGB_565;
        // 通过这个bitmap获取图片的宽和高&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        if (bitmap == null) {
            ALog.i("OperatingPicture", "bitmap为空");
        }
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        ALog.i("OperatingPicture", "真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0) {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        bitmap = BitmapFactory.decodeFile(filePath, options);
        int scaleWidth = bitmap.getWidth();
        int scaleHeight = bitmap.getHeight();
        ALog.i("OperatingPicture", "缩略图片高度：" + scaleHeight + "宽度:" + scaleWidth);
        return bitmap;
    }

    /**
     * 压缩图片
     *
     * @param filePath
     * @param baos
     */
    public static void compressBitmap(String filePath, ByteArrayOutputStream baos) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int realWidth = newOpts.outWidth;
        int realHeight = newOpts.outHeight;
        //现在主流手机比较多是1280*960分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为800f
        float ww = 960f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int sacle = 1;//sacle=1表示不缩放
        if (realWidth > realHeight && realWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outWidth / ww);
        } else if (realWidth < realHeight && realHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            sacle = (int) (newOpts.outHeight / hh);
        }
        if (sacle <= 0)
            sacle = 1;
        newOpts.inSampleSize = sacle;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(filePath, newOpts);

        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        int options = 100;
        // 如果大于100kb则再次压缩
        while (baos.toByteArray().length / 1024 > 100) {
            // 清空baos
            baos.reset();
            options -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
    }
}
