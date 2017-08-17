package com.huabiao.aoiin.picview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.constant.FlagBase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: MediaView
 * @Description: 多媒体处理工具类
 */
@SuppressLint("SimpleDateFormat")
public class MediaView {
    private static Context ctx = null;
    private static MediaView instance = null;
    private final static String Path = FlagBase.SDCARD_FJ_PATH;

    public synchronized static MediaView getInstance(Context context) {
        ctx = context;
        if (instance == null) {
            instance = new MediaView();
        }
        return instance;
    }

    /**
     * @param folder   文件夹
     * @param fileName 文件名
     * @Title: takePhoto
     * @Description: 拍照
     * @return: void
     */
    public void takePhoto(String folder, String fileName) {
        File file = new File(Path + folder + "/");
        if (!file.exists()) {
            file.mkdirs();
        }

        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Path + folder + "/" + fileName + ".jpg")));
        photoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        photoIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, false);
        ((Activity) ctx).startActivityForResult(photoIntent, FlagBase.MEDIA_PHOTO);
    }

    /**
     * @Title: selectPhoto
     * @Description: 选照
     * @return: void
     */
    public void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        // intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) ctx).startActivityForResult(intent, FlagBase.MEDIA_SPHOTO);
    }

    /**
     * @param fileName 文件名称
     * @param flag
     * @Title: takePhoto
     * @Description: 拍照
     * @return: void
     */
    public void takePhoto(String folder,String fileName, int flag) {
        File file = new File(Path + folder + File.separator);
        if (!file.exists()) {
            file.mkdirs();
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Path + folder + File.separator + fileName + ".jpg")));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, false);
        ((Activity) ctx).startActivityForResult(intent, flag);
    }

    /**
     * @Title: selectPhoto
     * @Description: 选照
     * @return: void
     */
    public void selectPhoto(int flag) {
        Intent intent = new Intent();
        intent.setType("image/*");
        // intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) ctx).startActivityForResult(intent, flag);
    }

    /**
     * @return
     * @Title: getFolderName
     * @Description: 获取文件夹名称
     * @return: String
     */
    public String getFolderName() {
        Date mDate = new Date();
        DateFormat mDateFormat = new SimpleDateFormat("MMddHHmmss");// 时间格式
        String folderName = mDateFormat.format(mDate);
        return folderName;
    }

    /**
     * 删除一个文件
     *
     * @param file
     * @return
     */
    public boolean delFile(File file) {
        if (file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * @param data
     * @param showIv
     * @param folderName
     * @param delIv
     * @Title: setSelectPhoto
     * @Description: 设置选照
     * @return: void
     */
    public void setSelectPhoto(Intent data, ImageView showIv, String folderName, ImageView delIv) {
        Uri uri = data.getData();// 获取照片的原始地址
        String uriPath = UriUtil.getImageAbsolutePath((Activity) ctx, uri);
        if (!uriPath.endsWith(".jpg") && !uriPath.endsWith(".jpeg") && !uriPath.endsWith(".png")) {
            Toast.makeText(ctx, ctx.getString(R.string.select_image_format), Toast.LENGTH_SHORT).show();
            return;
        }
        if (uri != null && !TextUtils.isEmpty(uriPath)) {
            ContentResolver cr = ctx.getContentResolver();
            Bitmap mBitmap;
            try {
                InputStream is = cr.openInputStream(uri);
                byte[] len = MediaView.readInstream(is);
                if (len.length > 500 * 1024) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 2;
                    mBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
                } else {
                    mBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                }

                if (mBitmap != null) {
                    rotateSaveBitmap(mBitmap, folderName, folderName, showIv, uri);
                    delIv.setVisibility(View.VISIBLE);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param data
     * @param imageView
     * @param flag
     * @param folderName 文件夹名
     * @Title: setSelectPhoto
     * @Description: 设置选照
     * @return: void
     */
    public void selectPhotoSave(Intent data, ImageView imageView, int flag, String folderName, ImageView delIv) {
        Uri uri = data.getData();// 获取照片的原始地址
        String uriPath = UriUtil.getImageAbsolutePath((Activity) ctx, uri);
        if (!TextUtils.isEmpty(uriPath) && !uriPath.toLowerCase().endsWith(".jpg")
                && !uriPath.toLowerCase().endsWith(".jpeg") && !uriPath.toLowerCase().endsWith(".png")) {
            Toast.makeText(ctx, ctx.getString(R.string.select_image_format), Toast.LENGTH_SHORT).show();
            return;
        }
        if (uri != null && !TextUtils.isEmpty(uriPath)) {
            ContentResolver cr = ctx.getContentResolver();
            Bitmap mBitmap = null;
            try {
                InputStream is = cr.openInputStream(uri);
                byte[] len = readInstream(is);
                if (len.length > 5 * 1024 * 1024) {
                    Toast.makeText(ctx, ctx.getString(R.string.select_image_size), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (imageView != null) {
                    mBitmap = BitmapUtil.ratioCompressionStream(cr.openInputStream(uri), cr.openInputStream(uri));
                    if (mBitmap != null) {
                        imageView.setImageBitmap(mBitmap);
                        if (delIv != null) {
                            delIv.setVisibility(View.VISIBLE);
                        }
                    }
                }
                saveFile(uriPath, folderName, folderName + flag);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param data
     * @param imageView
     * @param flag
     * @param folderName 文件夹名
     * @Title: setSelectPhoto
     * @Description: 设置选照
     * @return: void
     */
    public void setSelectPhoto(Intent data, ImageView imageView, int flag, String folderName, ImageView delIv) {
        Uri uri = data.getData();// 获取照片的原始地址
        String uriPath = UriUtil.getImageAbsolutePath((Activity) ctx, uri);
        if (!TextUtils.isEmpty(uriPath) && !uriPath.toLowerCase().endsWith(".jpg")
                && !uriPath.toLowerCase().endsWith(".jpeg") && !uriPath.toLowerCase().endsWith(".png")) {
            Toast.makeText(ctx, ctx.getString(R.string.select_image_format), Toast.LENGTH_SHORT).show();
            return;
        }
        if (uri != null && !TextUtils.isEmpty(uriPath)) {
            ContentResolver cr = ctx.getContentResolver();
            Bitmap mBitmap = null;
            try {
                InputStream is = cr.openInputStream(uri);
                byte[] len = readInstream(is);
                if (len.length > 5 * 1024 * 1024) {
                    Toast.makeText(ctx, ctx.getString(R.string.select_image_size), Toast.LENGTH_SHORT).show();
                    return;
                }

                mBitmap = BitmapUtil.ratioCompressionStream(cr.openInputStream(uri), cr.openInputStream(uri));
                if (mBitmap != null) {
                    rotateSaveBitmap(mBitmap, folderName, folderName + flag, imageView, uri);
                    if (delIv != null) {
                        delIv.setVisibility(View.VISIBLE);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param data
     * @param imageView
     * @param flag
     * @param folderName
     * @Title: setSelectPhoto3
     * @Description: 设置选照
     * @return: void
     */
    public void setSelectPhoto3(Intent data, ImageView imageView, int flag, String folderName) {
        Uri uri = data.getData();// 获取照片的原始地址
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;
        bfOptions.inTempStorage = new byte[32 * 1024];
        try {
            String[] pojo = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation")
            Cursor cursor = ((Activity) ctx).managedQuery(uri, pojo, null, null, null);
            if (cursor != null) {
                int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(colunm_index);// 获得图片的路径
                File sFile = new File(path);
                FileInputStream fis = new FileInputStream(sFile);
                if (path.endsWith("jpg") || path.endsWith("png") || path.endsWith("jpeg")) {
                    Bitmap bitmapSel = BitmapFactory.decodeFileDescriptor(fis.getFD(), null, bfOptions);
                    saveBitmapPath(bitmapSel, folderName, folderName + flag, imageView);
                } else {
                    Toast.makeText(ctx, ctx.getString(R.string.select_image_format), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param inputStream
     * @return
     * @throws Exception
     * @Title: readInstream
     * @Description: 输入流转字节数组
     * @return: byte[]
     */
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
     * @param mImageCaptureUri
     * @param ctx
     * @return
     * @Title: setImageAngel
     * @Description: 设置图片显示角度
     * @return: Bitmap
     */
    public Bitmap setImageAngel(Uri mImageCaptureUri, Context ctx) {
        // 不管是拍照还是选择图片每张图片都有在数据中存储也存储有对应旋转角度orientation值
        // 所以我们在取出图片是把角度值取出以便能正确的显示图片,没有旋转时的效果观看
        ContentResolver cr = ctx.getContentResolver();
        Cursor cursor = cr.query(mImageCaptureUri, null, null, null, null);// 根据Uri从数据库中找
        if (cursor != null) {
            cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
            String filePath = cursor.getString(cursor.getColumnIndex("_data"));// 获取图片路
            String orientation = cursor.getString(cursor.getColumnIndex("orientation"));// 获取旋转的角度
            cursor.close();
            if (filePath != null) {
                // Bitmap bitmap = BitmapFactory.decodeFile(filePath);//
                // 根据Path读取资源图片
                Bitmap bitmap = BitmapUtil.createImageThumbnail(filePath);
                int angle = 0;
                if (orientation != null && !"".equals(orientation)) {
                    angle = Integer.parseInt(orientation);
                }
                if (angle != 0) {
                    // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                    Matrix m = new Matrix();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    m.setRotate(angle); // 旋转angle度
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
                    return bitmap;
                }
            }
        }
        return null;
    }

    /**
     * @param mImageCaptureUri
     * @param filePath
     * @param ctx
     * @return
     * @Title: setImageAngel
     * @Description: 设置图片显示角度
     * @return: Bitmap
     */
    public Bitmap setImageAngel(Uri mImageCaptureUri, String filePath, Context ctx) {
        // 不管是拍照还是选择图片每张图片都有在数据中存储也存储有对应旋转角度orientation值
        // 所以我们在取出图片是把角度值取出以便能正确的显示图片,没有旋转时的效果观看
        ContentResolver cr = ctx.getContentResolver();
        Cursor cursor = cr.query(mImageCaptureUri, null, null, null, null);// 根据Uri从数据库中找
        if (cursor != null) {
            cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
            String orientation = cursor.getString(cursor.getColumnIndex("orientation"));// 获取旋转的角度
            cursor.close();
            if (filePath != null) {
                // 根据Path读取资源图片
                Bitmap bitmap = BitmapFactory.decodeFile(filePath);

                int angle = 0;
                if (orientation != null && !"".equals(orientation)) {
                    angle = Integer.parseInt(orientation);
                }
                if (angle != 0) {
                    // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                    Matrix m = new Matrix();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    m.setRotate(angle); // 旋转angle度
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
                    saveBitmap(bitmap, filePath);
                    return bitmap;
                } else {
                    return bitmap;
                }
            }
        }
        return null;
    }

    /**
     * 压缩图片保存
     *
     * @param bitmap
     * @param filePath
     */
    public void compressionSaveBitmap(Bitmap bitmap, String filePath) {

        if (bitmap == null) {
            return;
        }
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            while (baos.toByteArray().length / 1024 > 100) {
                baos.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                options -= 10;
            }
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fos = new FileOutputStream(file);
                if (fos != null) {
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存文件
     *
     * @param fileUri
     * @param folder
     * @param fileName
     */
    public void saveFile(String fileUri, String folder, String fileName) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            String filePath = Path + folder + "/" + fileName + ".jpg";

            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(fileUri);
                fileOutputStream = new FileOutputStream(file);
                // 获取文件的总长度
                int fileLength = inputStream.available();
                // 每次上传后累加的长度
                int upLength = 0;
                // 设置每次写入1024个字节的缓冲区
                byte[] buffer = new byte[500 * 1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    // 每上传一次将数据累加起来
                    upLength += len;
                    // 将资料写入DataOutputStream中
                    fileOutputStream.write(buffer, 0, len);
                }
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存Bitmap
     *
     * @param bitmap
     * @param filePath
     */
    public void saveBitmap(Bitmap bitmap, String filePath) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            if (bitmap == null) {
                return;
            }
            FileOutputStream fos = null;
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                fos = new FileOutputStream(file);
                if (null != fos) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存并压缩图片到指定的路径下
     *
     * @param bitmap
     * @param folder
     * @param fileName
     * @param imageView
     */
    @Deprecated
    public void saveBitmapPath(Bitmap bitmap, String folder, String fileName, ImageView imageView) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            File dir = new File(Path + folder + "/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName + ".jpg");

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                if (bitmap != null) {
                    // 如果不压缩是100，表示压缩率为0
                    if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
                        try {
                            String filePath = Path + folder + "/" + fileName + ".jpg";
                            Bitmap mBitmap = BitmapUtil.createImageThumbnail(filePath);
                            imageView.setImageBitmap(mBitmap);
                            fileOutputStream.flush();
                        } catch (IOException e) {
                            file.delete();
                            e.printStackTrace();
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                file.delete();
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param bitmap
     * @param folder
     * @param fileName
     * @param imageView
     * @param uri
     * @Title: rotateSaveBitmap
     * @Description: 旋转图片保存
     * @return: void
     */
    public void rotateSaveBitmap(Bitmap bitmap, String folder, String fileName, ImageView imageView, Uri uri) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            String filePath = Path + folder + "/" + fileName + ".jpg";

            ContentResolver cr = ctx.getContentResolver();
            Cursor cursor = cr.query(uri, null, null, null, null);// 根据Uri从数据库中找
            if (cursor != null) {
                cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
                String orientation = cursor.getString(cursor.getColumnIndex("orientation"));// 获取旋转的角度
                cursor.close();
                if (!TextUtils.isEmpty(filePath)) {
                    int angle = 0;
                    if (orientation != null && !"".equals(orientation)) {
                        angle = Integer.parseInt(orientation);
                    }
                    if (angle != 0) {
                        // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                        Matrix m = new Matrix();
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        m.setRotate(angle); // 旋转angle度
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
                    }

                    File dir = new File(Path + folder + "/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir, fileName + ".jpg");
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        if (fileOutputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();

                            if (imageView != null) {
                                Bitmap mBitmap = BitmapUtil.ratioCompressionImage(filePath);
                                imageView.setImageBitmap(mBitmap);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fileOutputStream != null)
                                fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @param filePath 图片绝对路径
     * @return
     * @Title: loadImageDegree
     * @Description: 读取图片角度
     * @return: int
     */
    public int loadImageDegree(String filePath) {
        int degree = 0;
        try {
            ExifInterface mExifInterface = new ExifInterface(filePath);
            int orientation = mExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * @param mBitmap
     * @param degree
     * @return
     * @Title: rotateBitmap
     * @Description:旋转图片
     * @return: Bitmap
     */
    public Bitmap rotateBitmap(Bitmap mBitmap, int degree) {
        if (mBitmap != null) {
            Matrix mMatrix = new Matrix();
            mMatrix.postRotate(degree);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), mMatrix, true);
            return mBitmap;
        }
        return mBitmap;
    }

    /**
     * @param data
     * @param folderName 文件夹名
     * @param fileName   文件名
     * @Title: selectPhotoSave
     * @Description: 选择照片保存
     * @return: void
     */
    public Bitmap selectPhotoSave(Intent data, String folderName, String fileName) {
        Bitmap mBitmap = null;
        Uri uri = data.getData();// 获取照片的原始地址
        String uriPath = UriUtil.getImageAbsolutePath((Activity) ctx, uri);
        if (!TextUtils.isEmpty(uriPath) && !uriPath.toLowerCase().endsWith(".jpg")
                && !uriPath.toLowerCase().endsWith(".jpeg") && !uriPath.toLowerCase().endsWith(".png")) {
            Toast.makeText(ctx, ctx.getString(R.string.select_image_format), Toast.LENGTH_SHORT).show();
            return null;
        }
        if (uri != null && !TextUtils.isEmpty(uriPath)) {
            ContentResolver cr = ctx.getContentResolver();
            try {
                InputStream is = cr.openInputStream(uri);
                byte[] len = readInstream(is);
                if (len.length > 5 * 1024 * 1024) {
                    Toast.makeText(ctx, ctx.getString(R.string.select_image_size), Toast.LENGTH_SHORT).show();
                    return null;
                }
                if (len.length > 1 * 1024 * 1024) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 2;
                    mBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
                } else {
                    mBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                }
                if (mBitmap != null) {
                    mBitmap = anglePhotoSave(mBitmap, folderName, fileName, uri);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mBitmap;
    }

    /**
     * @param bitmap
     * @param folder   文件夹
     * @param fileName 文件名
     * @param uri
     * @Title: anglePhotoSave
     * @Description: 旋转图片保存
     * @return: void
     */
    public Bitmap anglePhotoSave(Bitmap bitmap, String folder, String fileName, Uri uri) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {

            String filePath = Path + folder + "/" + fileName + ".jpg";

            ContentResolver cr = ctx.getContentResolver();
            Cursor cursor = cr.query(uri, null, null, null, null);// 根据Uri从数据库中找
            if (cursor != null) {
                cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
                String orientation = cursor.getString(cursor.getColumnIndex("orientation"));// 获取旋转的角度
                cursor.close();
                if (filePath != null) {
                    int angle = 0;
                    if (orientation != null && !"".equals(orientation)) {
                        angle = Integer.parseInt(orientation);
                    }
                    if (angle != 0) {
                        // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                        Matrix m = new Matrix();
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        m.setRotate(angle); // 旋转angle度
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
                    }

                    File dir = new File(Path + folder + "/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file = new File(dir, fileName + ".jpg");
                    FileOutputStream fileOutputStream = null;

                    try {
                        fileOutputStream = new FileOutputStream(file);
                        if (null != fileOutputStream) {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            fileOutputStream.flush();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return bitmap;
    }
}
