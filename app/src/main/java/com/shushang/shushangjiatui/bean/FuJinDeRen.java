package com.shushang.shushangjiatui.bean;

import java.util.List;

public class FuJinDeRen {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"merchantUserList":[{"address":"河南省安阳市万达酒店","appActivate":1,"autograph":"1","cardMobileNumber":"18911647190","createId":1,"createTime":"2019-05-09 15:48:09.0","del":1,"eMail":"","enable":1,"headPortraitImage":14,"id":1,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026722","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988123","updateId":1,"updateTime":"2019-05-09 15:48:11.0","userAccount":"shenxy","userName":"申向玉","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190193","wxNum":"1891111","wxSpActivate":1},{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647192","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"headPortraitImage":12,"id":3,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026720","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988121","updateId":1,"updateTime":"2019-05-09 15:52:10.0","userAccount":"yaom","userName":"姚明","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190191","wxNum":"1891113","wxSpActivate":1},{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647193","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"headPortraitImage":11,"id":4,"merchantCode":"ycsmtwy","merchantId":2,"merchantName":"斯美特卫浴","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026719","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988119","updateId":1,"updateTime":"2019-05-10 18:05:34.0","userAccount":"bkhm","userName":"贝克汉姆","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190190","wxNum":"1891114","wxSpActivate":1},{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647194","createId":1,"createTime":"2019-05-15 15:26:21.0","del":1,"eMail":"","enable":1,"headPortraitImage":10,"id":5,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026718","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988118","updateId":1,"updateTime":"2019-05-15 15:26:23.0","userAccount":"linxr","userName":"林心如","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190189","wxNum":"1891115","wxSpActivate":1}]}
     */

    private String ret;
    private String msg;
    private Object error;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MerchantUserListBean> merchantUserList;

        public List<MerchantUserListBean> getMerchantUserList() {
            return merchantUserList;
        }

        public void setMerchantUserList(List<MerchantUserListBean> merchantUserList) {
            this.merchantUserList = merchantUserList;
        }

        public static class MerchantUserListBean {
            /**
             * address : 河南省安阳市万达酒店
             * appActivate : 1
             * autograph : 1
             * cardMobileNumber : 18911647190
             * createId : 1
             * createTime : 2019-05-09 15:48:09.0
             * del : 1
             * eMail :
             * enable : 1
             * headPortraitImage : 14
             * id : 1
             * merchantCode : ejjz
             * merchantId : 1
             * merchantName : e见家装橱柜
             * openId :
             * openRadarCard : 1
             * position : 销售
             * qqNum : 568026722
             * sysRoleList : []
             * telPhone :
             * tokenId :
             * tokenUuid : 1234567890988123
             * updateId : 1
             * updateTime : 2019-05-09 15:48:11.0
             * userAccount : shenxy
             * userName : 申向玉
             * userPassword : c20ad4d76fe97759aa27a0c99bff6710
             * wxMobileNumber : 13522190193
             * wxNum : 1891111
             * wxSpActivate : 1
             */

            private String address;
            private int appActivate;
            private String autograph;
            private String cardMobileNumber;
            private int createId;
            private String createTime;
            private int del;
            private String eMail;
            private int enable;
            private int headPortraitImage;
            private int id;
            private String merchantCode;
            private int merchantId;
            private String merchantName;
            private String openId;
            private int openRadarCard;
            private String position;
            private String qqNum;
            private String telPhone;
            private String tokenId;
            private String tokenUuid;
            private int updateId;
            private String updateTime;
            private String userAccount;
            private String userName;
            private String userPassword;
            private String wxMobileNumber;
            private String wxNum;
            private int wxSpActivate;
            private List<?> sysRoleList;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAppActivate() {
                return appActivate;
            }

            public void setAppActivate(int appActivate) {
                this.appActivate = appActivate;
            }

            public String getAutograph() {
                return autograph;
            }

            public void setAutograph(String autograph) {
                this.autograph = autograph;
            }

            public String getCardMobileNumber() {
                return cardMobileNumber;
            }

            public void setCardMobileNumber(String cardMobileNumber) {
                this.cardMobileNumber = cardMobileNumber;
            }

            public int getCreateId() {
                return createId;
            }

            public void setCreateId(int createId) {
                this.createId = createId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public String getEMail() {
                return eMail;
            }

            public void setEMail(String eMail) {
                this.eMail = eMail;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getHeadPortraitImage() {
                return headPortraitImage;
            }

            public void setHeadPortraitImage(int headPortraitImage) {
                this.headPortraitImage = headPortraitImage;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMerchantCode() {
                return merchantCode;
            }

            public void setMerchantCode(String merchantCode) {
                this.merchantCode = merchantCode;
            }

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public int getOpenRadarCard() {
                return openRadarCard;
            }

            public void setOpenRadarCard(int openRadarCard) {
                this.openRadarCard = openRadarCard;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getQqNum() {
                return qqNum;
            }

            public void setQqNum(String qqNum) {
                this.qqNum = qqNum;
            }

            public String getTelPhone() {
                return telPhone;
            }

            public void setTelPhone(String telPhone) {
                this.telPhone = telPhone;
            }

            public String getTokenId() {
                return tokenId;
            }

            public void setTokenId(String tokenId) {
                this.tokenId = tokenId;
            }

            public String getTokenUuid() {
                return tokenUuid;
            }

            public void setTokenUuid(String tokenUuid) {
                this.tokenUuid = tokenUuid;
            }

            public int getUpdateId() {
                return updateId;
            }

            public void setUpdateId(int updateId) {
                this.updateId = updateId;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserAccount() {
                return userAccount;
            }

            public void setUserAccount(String userAccount) {
                this.userAccount = userAccount;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPassword() {
                return userPassword;
            }

            public void setUserPassword(String userPassword) {
                this.userPassword = userPassword;
            }

            public String getWxMobileNumber() {
                return wxMobileNumber;
            }

            public void setWxMobileNumber(String wxMobileNumber) {
                this.wxMobileNumber = wxMobileNumber;
            }

            public String getWxNum() {
                return wxNum;
            }

            public void setWxNum(String wxNum) {
                this.wxNum = wxNum;
            }

            public int getWxSpActivate() {
                return wxSpActivate;
            }

            public void setWxSpActivate(int wxSpActivate) {
                this.wxSpActivate = wxSpActivate;
            }

            public List<?> getSysRoleList() {
                return sysRoleList;
            }

            public void setSysRoleList(List<?> sysRoleList) {
                this.sysRoleList = sysRoleList;
            }
        }
    }
}
