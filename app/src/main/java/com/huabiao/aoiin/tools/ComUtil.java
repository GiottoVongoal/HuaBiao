package com.huabiao.aoiin.tools;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名: ComUtil
 *
 * @author Wendy
 */
public class ComUtil {
    /**
     * md5
     *
     * @param str
     * password
     * @return
     */
    private static long sLastClickTime;
    private static String TAG = "ComUtil";

    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static boolean isStringEmpty(String str) {
        boolean isEmpty = false;
        if (null == str || str.trim().equals("") || "null".equals(str)) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * 安装apk
     */
    public static void installApk(Context mContext, String saveFileName) {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        i.setDataAndType(Uri.fromFile(
                new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS),
                        "app.apk")),
                "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

    /**
     * 判断SD卡是否存在
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 创建目录
     */
    public static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 文件是否存在
     */
    public static boolean fileIsExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static void deletefile(String fileName) {
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
    }

    public static boolean notEmpty(Object o) {
        return o != null && !"".equals(o.toString().trim()) && !"null".equalsIgnoreCase(o.toString().trim())
                && !"undefined".equalsIgnoreCase(o.toString().trim());
    }

    public static boolean empty(Object o) {
        return o == null || "".equals(o.toString().trim()) || "null".equalsIgnoreCase(o.toString().trim())
                || "undefined".equalsIgnoreCase(o.toString().trim());
    }


    public static String getHtml(String path) throws Exception {
        String html = null;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();//通过输入流获取html数据
        byte[] data = readInputStream(inStream);//得到html的二进制数据
        html = new String(data, "utf-8");
        return html;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static void isGPSOpen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gps) {
            openGPS(context);
        }
    }

    /**
     * 打开gps
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (CanceledException e) {
            e.printStackTrace();
        }
    }

    public static boolean isChineseChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 限定只能输入数字和大小写字母
    public static void setTextWatcher2(final Context context, EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable edt) {
                if (edt.length() > 0) {
                    String temp = edt.toString();
                    String tem = temp.substring(temp.length() - 1, temp.length());
                    char[] temC = tem.toCharArray();
                    int mid = temC[0];
                    if (mid >= 48 && mid <= 57) {//数字
                        return;
                    }
                    if (mid >= 65 && mid <= 90) {//大写字母
                        return;
                    }
                    if (mid > 97 && mid <= 122) {//小写字母
                        return;
                    }
                    edt.delete(temp.length() - 1, temp.length()); // 删除该非法字符
                }
            }
        });
    }

    public static boolean isMobileNum(String number) {
        Pattern p = Pattern.compile("^((1[358]))\\d{9}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - sLastClickTime;
        if (0 < timeD && timeD < 500) {
            Log.i(TAG, "连续点击");
            return true;
        }
        sLastClickTime = time;
        return false;
    }

    public static boolean isFastDoubleClickOneS() {
        long time = System.currentTimeMillis();
        long timeD = time - sLastClickTime;
        if (0 < timeD && timeD < 1000) {
            Log.i(TAG, "请不要频繁刷新");
            return true;
        }
        sLastClickTime = time;
        return false;
    }

    // 查询手机内非系统应用
    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        // 获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            // 判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            // 当前应用的版本名称
            String versionName = info.versionName;
            // 当前版本的版本号
            int versionCode = info.versionCode;
            // 当前版本的包名
            String packageNames = info.packageName;
            Log.i(TAG, "<getAppVersionName> -- versionName = " + versionName);
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "can_not_find_version_name";
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            // 当前版本的版本号
            int versionCode = info.versionCode;
            Log.i(TAG, "<getAppVersionCode> -- versionCode = " + versionCode);
            return versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 获取手机内非系统应用的图片、应用名、包名
    public void getPackageInfo(Context context) {
        PackageManager pManager = context.getPackageManager();
        List<PackageInfo> appList = getAllApps(context);
        for (int i = 0; i < appList.size(); i++) {
            PackageInfo pinfo = appList.get(i);
            // get Icon
            // shareItem.setIcon(pManager.getApplicationIcon(pinfo.applicationInfo));
            // set Application Name
            Log.i(TAG, "<getPackageInfo> -- Application Name: " + pManager.getApplicationLabel(pinfo.applicationInfo).toString());
            // shareItem.setLabel(pManager.getApplicationLabel(pinfo.applicationInfo).toString());
            // set Package Name
            // shareItem.setPackageName(pinfo.applicationInfo.packageName);
            Log.i(TAG, "<getPackageInfo> -- Package Name: " + pinfo.applicationInfo.packageName);
        }
    }

    // 判断是否已安装某个应用
    public static boolean checkInstall(Context context, String packageName) {
        boolean isInstalled = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo == null) {
                isInstalled = false;
            } else {
                isInstalled = true;
            }
        } catch (Exception e) {
            isInstalled = false;
        }
        return isInstalled;
    }

    /**
     * 获取sdCard的路径
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取根目录
        } else {
            return "/data/data/";
        }
        return sdDir.toString();
    }

    /**
     * 是否有sd
     */
    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(status)) {
            return false;
        }
        return true;
    }

    /**
     * 验证联系电话
     */
    public static boolean checkPhone(String phone) {
        if (phone.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)")) {
            return true;
        } else if (phone.matches("^[1][3,5]+\\d{9}")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证邮政编码
     */
    public static boolean checkPost(String post) {
        if (post.matches("[1-9]\\d{5}(?!\\d)")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证邮箱
     */
    public static boolean checkEmail(String email) {
        if (email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
            return true;
        } else {
            return false;
        }
    }

    /* 根据亮度值brightness（0~255）修改当前window亮度, 此时对系统亮度无任何影响。注意：参数brightness取值为0~255。
       若要使该设置只对当前Activity生效，那么可以在onResume()中先对系统的亮度进行保存（如保存到SharedPreferences中），
       然后changeAppBrightness()方法以实现每个当前屏幕的亮度调节。退出时要在onStop()方法中恢复系统的亮度。*/
    public static void changeAppBrightness(Context context, int brightness) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f; // 0~1
        }
        window.setAttributes(lp);
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String convert(float value) {
        long l1 = Math.round(value * 100); //四舍五入
        double ret = l1 / 100.0; //注意：使用 100.0 而不是 100
        return String.valueOf(ret);
    }

    //根据身份证号输出年龄
    public static int IdNOToAge(String IdNO) {
        int leh = IdNO.length();
        String dates = "";
        if (leh == 18) {
            int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2;
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }

    }

    public static void main(String[] args) {
        IdNOToAge("320623199305150021"); //320621198804303
    }

}
