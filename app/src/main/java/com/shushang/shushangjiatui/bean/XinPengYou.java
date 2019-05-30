package com.shushang.shushangjiatui.bean;

import java.util.List;

public class XinPengYou {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"UserSaleInfo":[{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647192","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"friendRecordId":"45","headPortraitImage":12,"id":3,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026720","status":"1","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988121","updateId":1,"updateTime":"2019-05-09 15:52:10.0","userAccount":"yaom","userName":"姚明","userPassword":"96E79218965EB72C92A549DD5A330112","wxMobileNumber":"13522190191","wxNum":"1891113","wxSpActivate":1},{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647193","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"friendRecordId":"47","headPortraitImage":11,"id":4,"merchantCode":"ycsmtwy","merchantId":2,"merchantName":"斯美特卫浴","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026719","status":"0","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988119","updateId":1,"updateTime":"2019-05-10 18:05:34.0","userAccount":"bkhm","userName":"贝克汉姆","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190190","wxNum":"1891114","wxSpActivate":1},{"address":"河南省安阳市万达酒店","appActivate":1,"autograph":"1","cardMobileNumber":"18911647190","createId":1,"createTime":"2019-05-09 15:48:09.0","del":1,"eMail":"","enable":1,"friendRecordId":"49","headPortraitImage":14,"id":1,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026722","status":"0","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988123","updateId":1,"updateTime":"2019-05-09 15:48:11.0","userAccount":"shenxy","userName":"申向玉","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190193","wxNum":"1891111","wxSpActivate":1}],"intmaxCount":3,"intcurrentPage":1,"intpageSize":10,"intmaxPage":1}
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
        /**
         * UserSaleInfo : [{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647192","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"friendRecordId":"45","headPortraitImage":12,"id":3,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026720","status":"1","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988121","updateId":1,"updateTime":"2019-05-09 15:52:10.0","userAccount":"yaom","userName":"姚明","userPassword":"96E79218965EB72C92A549DD5A330112","wxMobileNumber":"13522190191","wxNum":"1891113","wxSpActivate":1},{"address":"河南省安阳市万达广场","appActivate":1,"autograph":"1","cardMobileNumber":"18911647193","createId":1,"createTime":"2019-05-09 15:52:08.0","del":1,"eMail":"","enable":1,"friendRecordId":"47","headPortraitImage":11,"id":4,"merchantCode":"ycsmtwy","merchantId":2,"merchantName":"斯美特卫浴","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026719","status":"0","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988119","updateId":1,"updateTime":"2019-05-10 18:05:34.0","userAccount":"bkhm","userName":"贝克汉姆","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190190","wxNum":"1891114","wxSpActivate":1},{"address":"河南省安阳市万达酒店","appActivate":1,"autograph":"1","cardMobileNumber":"18911647190","createId":1,"createTime":"2019-05-09 15:48:09.0","del":1,"eMail":"","enable":1,"friendRecordId":"49","headPortraitImage":14,"id":1,"merchantCode":"ejjz","merchantId":1,"merchantName":"e见家装橱柜","openId":"","openRadarCard":1,"position":"销售","qqNum":"568026722","status":"0","sysRoleList":[],"telPhone":"","tokenId":"","tokenUuid":"1234567890988123","updateId":1,"updateTime":"2019-05-09 15:48:11.0","userAccount":"shenxy","userName":"申向玉","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","wxMobileNumber":"13522190193","wxNum":"1891111","wxSpActivate":1}]
         * intmaxCount : 3
         * intcurrentPage : 1
         * intpageSize : 10
         * intmaxPage : 1
         */

        private int intmaxCount;
        private int intcurrentPage;
        private int intpageSize;
        private int intmaxPage;

        public String getIsExamine() {
            return isExamine;
        }

        public void setIsExamine(String isExamine) {
            this.isExamine = isExamine;
        }

        private String isExamine;
        private List<UserSaleInfoBean> UserSaleInfo;

        public int getIntmaxCount() {
            return intmaxCount;
        }

        public void setIntmaxCount(int intmaxCount) {
            this.intmaxCount = intmaxCount;
        }

        public int getIntcurrentPage() {
            return intcurrentPage;
        }

        public void setIntcurrentPage(int intcurrentPage) {
            this.intcurrentPage = intcurrentPage;
        }

        public int getIntpageSize() {
            return intpageSize;
        }

        public void setIntpageSize(int intpageSize) {
            this.intpageSize = intpageSize;
        }

        public int getIntmaxPage() {
            return intmaxPage;
        }

        public void setIntmaxPage(int intmaxPage) {
            this.intmaxPage = intmaxPage;
        }

        public List<UserSaleInfoBean> getUserSaleInfo() {
            return UserSaleInfo;
        }

        public void setUserSaleInfo(List<UserSaleInfoBean> UserSaleInfo) {
            this.UserSaleInfo = UserSaleInfo;
        }

        public static class UserSaleInfoBean {
            /**
             * address : 河南省安阳市万达广场
             * appActivate : 1
             * autograph : 1
             * cardMobileNumber : 18911647192
             * createId : 1
             * createTime : 2019-05-09 15:52:08.0
             * del : 1
             * eMail :
             * enable : 1
             * friendRecordId : 45
             * headPortraitImage : 12
             * id : 3
             * merchantCode : ejjz
             * merchantId : 1
             * merchantName : e见家装橱柜
             * openId :
             * openRadarCard : 1
             * position : 销售
             * qqNum : 568026720
             * status : 1
             * sysRoleList : []
             * telPhone :
             * tokenId :
             * tokenUuid : 1234567890988121
             * updateId : 1
             * updateTime : 2019-05-09 15:52:10.0
             * userAccount : yaom
             * userName : 姚明
             * userPassword : 96E79218965EB72C92A549DD5A330112
             * wxMobileNumber : 13522190191
             * wxNum : 1891113
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
            private String friendRecordId;
            private int headPortraitImage;
            private int id;
            private String merchantCode;
            private int merchantId;
            private String merchantName;
            private String openId;
            private int openRadarCard;
            private String position;
            private String qqNum;
            private String status;
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

            public String getFriendRecordId() {
                return friendRecordId;
            }

            public void setFriendRecordId(String friendRecordId) {
                this.friendRecordId = friendRecordId;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
