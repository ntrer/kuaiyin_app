package com.shushang.shushangjiatui.bean;

import java.util.List;

public class DianPuRenYuan {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : [{"id":-1,"userName":"店铺","userAccount":null,"userPassword":null,"cardMobileNumber":null,"wxMobileNumber":null,"qqNum":null,"eMail":null,"telPhone":null,"position":null,"merchantId":null,"merchantName":null,"merchantCode":null,"openId":null,"tokenUuid":null,"wxNum":null,"headPortraitImage":null,"address":null,"autograph":null,"wxSpActivate":null,"appActivate":null,"openRadarCard":null,"createId":null,"updateId":null,"createTime":null,"updateTime":null,"enable":null,"del":null,"sysRoleList":null,"tokenId":null},{"id":5,"userName":"林心如","userAccount":"linxr","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","cardMobileNumber":"18911647194","wxMobileNumber":"13522190189","qqNum":"568026718","eMail":null,"telPhone":null,"position":"销售","merchantId":1,"merchantName":"e见家装橱柜","merchantCode":"ejjz","openId":null,"tokenUuid":"1234567890988118","wxNum":"1891115","headPortraitImage":10,"address":"河南省安阳市万达广场","autograph":"1","wxSpActivate":1,"appActivate":1,"openRadarCard":1,"createId":1,"updateId":1,"createTime":"2019-05-15 15:26:21.0","updateTime":"2019-05-15 15:26:23.0","enable":1,"del":1,"sysRoleList":null,"tokenId":null},{"id":3,"userName":"姚明","userAccount":"yaom","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","cardMobileNumber":"18911647192","wxMobileNumber":"13522190191","qqNum":"568026720","eMail":null,"telPhone":null,"position":"销售","merchantId":1,"merchantName":"e见家装橱柜","merchantCode":"ejjz","openId":null,"tokenUuid":"1234567890988121","wxNum":"1891113","headPortraitImage":12,"address":"河南省安阳市万达广场","autograph":"1","wxSpActivate":1,"appActivate":1,"openRadarCard":1,"createId":1,"updateId":1,"createTime":"2019-05-09 15:52:08.0","updateTime":"2019-05-09 15:52:10.0","enable":1,"del":1,"sysRoleList":null,"tokenId":null},{"id":2,"userName":"刘翔","userAccount":"liux","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","cardMobileNumber":"18911647191","wxMobileNumber":"13522190192","qqNum":"568026721","eMail":null,"telPhone":null,"position":"销售","merchantId":1,"merchantName":"e见家装橱柜","merchantCode":"ejjz","openId":null,"tokenUuid":"1234567890988122","wxNum":"1891112","headPortraitImage":13,"address":"河南省安阳市万达中心","autograph":"1","wxSpActivate":1,"appActivate":1,"openRadarCard":1,"createId":1,"updateId":1,"createTime":"2019-05-09 15:50:18.0","updateTime":"2019-05-09 15:50:21.0","enable":1,"del":1,"sysRoleList":null,"tokenId":null},{"id":1,"userName":"申向玉","userAccount":"shenxy","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","cardMobileNumber":"18911647190","wxMobileNumber":"13522190193","qqNum":"568026722","eMail":null,"telPhone":null,"position":"销售","merchantId":1,"merchantName":"e见家装橱柜","merchantCode":"ejjz","openId":null,"tokenUuid":"1234567890988123","wxNum":"1891111","headPortraitImage":14,"address":"河南省安阳市万达酒店","autograph":"1","wxSpActivate":1,"appActivate":1,"openRadarCard":1,"createId":1,"updateId":1,"createTime":"2019-05-09 15:48:09.0","updateTime":"2019-05-09 15:48:11.0","enable":1,"del":1,"sysRoleList":null,"tokenId":null}]
     */

    private String ret;
    private String msg;
    private Object error;
    private List<DataBean> data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : -1
         * userName : 店铺
         * userAccount : null
         * userPassword : null
         * cardMobileNumber : null
         * wxMobileNumber : null
         * qqNum : null
         * eMail : null
         * telPhone : null
         * position : null
         * merchantId : null
         * merchantName : null
         * merchantCode : null
         * openId : null
         * tokenUuid : null
         * wxNum : null
         * headPortraitImage : null
         * address : null
         * autograph : null
         * wxSpActivate : null
         * appActivate : null
         * openRadarCard : null
         * createId : null
         * updateId : null
         * createTime : null
         * updateTime : null
         * enable : null
         * del : null
         * sysRoleList : null
         * tokenId : null
         */

        private int id;
        private String userName;
        private Object userAccount;
        private Object userPassword;
        private Object cardMobileNumber;
        private Object wxMobileNumber;
        private Object qqNum;
        private Object eMail;
        private Object telPhone;
        private Object position;
        private Object merchantId;
        private Object merchantName;
        private Object merchantCode;
        private Object openId;
        private Object tokenUuid;
        private Object wxNum;
        private Object headPortraitImage;
        private Object address;
        private Object autograph;
        private Object wxSpActivate;
        private Object appActivate;
        private Object openRadarCard;
        private Object createId;
        private Object updateId;
        private Object createTime;
        private Object updateTime;
        private Object enable;
        private Object del;
        private Object sysRoleList;
        private Object tokenId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(Object userAccount) {
            this.userAccount = userAccount;
        }

        public Object getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(Object userPassword) {
            this.userPassword = userPassword;
        }

        public Object getCardMobileNumber() {
            return cardMobileNumber;
        }

        public void setCardMobileNumber(Object cardMobileNumber) {
            this.cardMobileNumber = cardMobileNumber;
        }

        public Object getWxMobileNumber() {
            return wxMobileNumber;
        }

        public void setWxMobileNumber(Object wxMobileNumber) {
            this.wxMobileNumber = wxMobileNumber;
        }

        public Object getQqNum() {
            return qqNum;
        }

        public void setQqNum(Object qqNum) {
            this.qqNum = qqNum;
        }

        public Object getEMail() {
            return eMail;
        }

        public void setEMail(Object eMail) {
            this.eMail = eMail;
        }

        public Object getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(Object telPhone) {
            this.telPhone = telPhone;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(Object merchantId) {
            this.merchantId = merchantId;
        }

        public Object getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(Object merchantName) {
            this.merchantName = merchantName;
        }

        public Object getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(Object merchantCode) {
            this.merchantCode = merchantCode;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public Object getTokenUuid() {
            return tokenUuid;
        }

        public void setTokenUuid(Object tokenUuid) {
            this.tokenUuid = tokenUuid;
        }

        public Object getWxNum() {
            return wxNum;
        }

        public void setWxNum(Object wxNum) {
            this.wxNum = wxNum;
        }

        public Object getHeadPortraitImage() {
            return headPortraitImage;
        }

        public void setHeadPortraitImage(Object headPortraitImage) {
            this.headPortraitImage = headPortraitImage;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getAutograph() {
            return autograph;
        }

        public void setAutograph(Object autograph) {
            this.autograph = autograph;
        }

        public Object getWxSpActivate() {
            return wxSpActivate;
        }

        public void setWxSpActivate(Object wxSpActivate) {
            this.wxSpActivate = wxSpActivate;
        }

        public Object getAppActivate() {
            return appActivate;
        }

        public void setAppActivate(Object appActivate) {
            this.appActivate = appActivate;
        }

        public Object getOpenRadarCard() {
            return openRadarCard;
        }

        public void setOpenRadarCard(Object openRadarCard) {
            this.openRadarCard = openRadarCard;
        }

        public Object getCreateId() {
            return createId;
        }

        public void setCreateId(Object createId) {
            this.createId = createId;
        }

        public Object getUpdateId() {
            return updateId;
        }

        public void setUpdateId(Object updateId) {
            this.updateId = updateId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getEnable() {
            return enable;
        }

        public void setEnable(Object enable) {
            this.enable = enable;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }

        public Object getSysRoleList() {
            return sysRoleList;
        }

        public void setSysRoleList(Object sysRoleList) {
            this.sysRoleList = sysRoleList;
        }

        public Object getTokenId() {
            return tokenId;
        }

        public void setTokenId(Object tokenId) {
            this.tokenId = tokenId;
        }
    }
}
