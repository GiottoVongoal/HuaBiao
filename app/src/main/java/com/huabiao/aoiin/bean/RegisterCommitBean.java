package com.huabiao.aoiin.bean;

import com.blankj.ALog;
import com.huabiao.aoiin.picview.BitmapUtil;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.utils.ToastUtils;

import java.io.File;
import java.util.List;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.bean
 * @date 2017-08-03 16:54
 * @description 注册提交的所有数据
 */
public class RegisterCommitBean {
    private static RegisterCommitBean instance;

    private RegisterCommitBean() {
    }

    public static RegisterCommitBean getInstance() {
        if (instance == null) {
            synchronized (RegisterCommitBean.class) {
                if (instance == null) {
                    instance = new RegisterCommitBean();
                }
            }
        }
        return instance;
    }

    private List<ClassificationBean> claList;//可注册类别
    private String username;//客户姓名
    private String userphone;//客户电话
    //    private String contractSelectAddress;//合同地址
    private String contractAddress;//合同地址
    private String code;//邮政编码

    //公有
//    private String personName;//申请人姓名
//    private String collectAddress;//选择行政区
    //私有
//    private String legalPersonName;//法人姓名(法人1/个体2)
//    private String certificatesID;//身份证件文件号码(自然人3)
    //申请人类型标识
    private int personType = -1;

//    private String logoImg;//商标图样
//    private String proxyImg;//委托书
//    private String businessLicenceImg;//营业执照

    private int serviceMode = -1;//服务方式

    //判断所有数据是否为空
    public boolean isNull() {
        if (claList == null || claList.size() == 0) {
            ToastUtils.getInstance().showToast("请选择分类");
            return true;
        }
        if (personType == -1) {
            ToastUtils.getInstance().showToast("请选择申请人类型");
            return true;
        }
        if (StringUtil.isEmpty(username)) {
            ToastUtils.getInstance().showToast("请输入申请人姓名");
            return true;
        }
        if (StringUtil.isEmpty(userphone)) {
            ToastUtils.getInstance().showToast("请输入联系电话");
            return true;
        }
//        if (StringUtil.isEmpty(contractSelectAddress)) {
//            ToastUtils.getInstance().showToast("请选择合同地址行政区划");
//            return true;
//        }
        if (StringUtil.isEmpty(contractAddress)) {
            ToastUtils.getInstance().showToast("请输入合同地址");
            return true;
        }
        if (StringUtil.isEmpty(code)) {
            ToastUtils.getInstance().showToast("请输入邮政编码");
            return true;
        }

//        if (StringUtil.isEmpty(personName)) {
//            ToastUtils.getInstance().showToast("请输入申请人姓名");
//            return true;
//        }
//        if ((personType == 1 || personType == 2) && StringUtil.isEmpty(legalPersonName)) {
//            ToastUtils.getInstance().showToast("请输入法人姓名");
//            return true;
//        }
//        if (StringUtil.isEmpty(collectAddress)) {
//            ToastUtils.getInstance().showToast("请选择申请人地址行政区划");
//            return true;
//        }
//        if (personType == 3 && StringUtil.isEmpty(certificatesID)) {
//            ToastUtils.getInstance().showToast("请输入身份证号码");
//            return true;
//        }
//        if (!(new File(logoImg).exists())) {
//            ToastUtils.getInstance().showToast("请上传商标图样");
//            return true;
//        }
//        if (!(new File(proxyImg).exists())) {
//            ToastUtils.getInstance().showToast("请上传委托书");
//            return true;
//        }
//        if (!(new File(businessLicenceImg).exists())) {
//            ToastUtils.getInstance().showToast("请上传营业执照");
//            return true;
//        }
        if (serviceMode == -1) {
            ToastUtils.getInstance().showToast("请选择服务方式");
            return true;
        }
        return false;
    }

    //所有值清空
    public void emptyBean() {
        if (claList != null)
            claList.clear();
        username = "";
        userphone = "";
//        contractSelectAddress = "";
        contractAddress = "";
        code = "";
        personType = -1;
//        personName = "";
//        legalPersonName = "";
//        collectAddress = "";
//        certificatesID = "";
//        BitmapUtil.deleteFile(logoImg);
//        BitmapUtil.deleteFile(proxyImg);
//        BitmapUtil.deleteFile(businessLicenceImg);
//        logoImg = "";
//        proxyImg = "";
//        businessLicenceImg = "";
        serviceMode = -1;
    }

    public List<ClassificationBean> getClaList() {
        return claList;
    }

    public void setClaList(List<ClassificationBean> claList) {
        this.claList = claList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public int getServiceMode() {
        return serviceMode;
    }

    public void setServiceMode(int serviceMode) {
        this.serviceMode = serviceMode;
    }

    @Override
    public String toString() {
        return "RegisterCommitBean{" +
                "claList=" + claList +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                ", code='" + code + '\'' +
                ", personType=" + personType +
                ", serviceMode=" + serviceMode +
                '}';
    }
}
