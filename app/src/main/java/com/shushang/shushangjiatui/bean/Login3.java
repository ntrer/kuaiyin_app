package com.shushang.shushangjiatui.bean;

import java.util.List;

public class Login3 {


    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"id":2,"userName":"刘翔","userAccount":"liux","userPassword":"c20ad4d76fe97759aa27a0c99bff6710","cardMobileNumber":"18911647191","wxMobileNumber":"13522190192","qqNum":"568026721","eMail":null,"telPhone":null,"position":"销售","merchantId":1,"merchantName":null,"merchantCode":"ejjz","openId":null,"tokenUuid":"1234567890988122","wxNum":"1891112","headPortraitImage":13,"address":"河南省安阳市万达中心","autograph":"1","wxSpActivate":1,"appActivate":1,"openRadarCard":1,"createId":1,"updateId":1,"createTime":"2019-05-09 15:50:18.0","updateTime":"2019-05-09 15:50:21.0","enable":1,"del":1,"sysRoleList":[{"id":2,"roleNum":"00002","roleName":"店长","roleType":2,"roleDesc":"店长","parentId":1,"parentIds":",1,","sortId":2,"createId":100000000,"updateId":100000000,"createTime":"2019-05-15 14:46:26.0","updateTime":"2019-05-15 14:46:28.0","enable":1,"del":1,"visibleScope":3}],"tokenId":"5f5d1048f4b841f9b4382a4e52221068"}
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
         * id : 2
         * userName : 刘翔
         * userAccount : liux
         * userPassword : c20ad4d76fe97759aa27a0c99bff6710
         * cardMobileNumber : 18911647191
         * wxMobileNumber : 13522190192
         * qqNum : 568026721
         * eMail : null
         * telPhone : null
         * position : 销售
         * merchantId : 1
         * merchantName : null
         * merchantCode : ejjz
         * openId : null
         * tokenUuid : 1234567890988122
         * wxNum : 1891112
         * headPortraitImage : 13
         * address : 河南省安阳市万达中心
         * autograph : 1
         * wxSpActivate : 1
         * appActivate : 1
         * openRadarCard : 1
         * createId : 1
         * updateId : 1
         * createTime : 2019-05-09 15:50:18.0
         * updateTime : 2019-05-09 15:50:21.0
         * enable : 1
         * del : 1
         * sysRoleList : [{"id":2,"roleNum":"00002","roleName":"店长","roleType":2,"roleDesc":"店长","parentId":1,"parentIds":",1,","sortId":2,"createId":100000000,"updateId":100000000,"createTime":"2019-05-15 14:46:26.0","updateTime":"2019-05-15 14:46:28.0","enable":1,"del":1,"visibleScope":3}]
         * tokenId : 5f5d1048f4b841f9b4382a4e52221068
         */

        private int id;
        private String userName;
        private String userAccount;
        private String userPassword;
        private String cardMobileNumber;
        private String wxMobileNumber;
        private String qqNum;
        private Object eMail;
        private Object telPhone;
        private String position;
        private int merchantId;
        private Object merchantName;
        private String merchantCode;
        private Object openId;
        private String tokenUuid;
        private String wxNum;
        private int headPortraitImage;
        private String address;
        private String autograph;
        private int wxSpActivate;
        private int appActivate;
        private int openRadarCard;
        private int createId;
        private int updateId;
        private String createTime;
        private String updateTime;
        private int enable;
        private int del;
        private String tokenId;
        private List<SysRoleListBean> sysRoleList;

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

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getCardMobileNumber() {
            return cardMobileNumber;
        }

        public void setCardMobileNumber(String cardMobileNumber) {
            this.cardMobileNumber = cardMobileNumber;
        }

        public String getWxMobileNumber() {
            return wxMobileNumber;
        }

        public void setWxMobileNumber(String wxMobileNumber) {
            this.wxMobileNumber = wxMobileNumber;
        }

        public String getQqNum() {
            return qqNum;
        }

        public void setQqNum(String qqNum) {
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

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public Object getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(Object merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public String getTokenUuid() {
            return tokenUuid;
        }

        public void setTokenUuid(String tokenUuid) {
            this.tokenUuid = tokenUuid;
        }

        public String getWxNum() {
            return wxNum;
        }

        public void setWxNum(String wxNum) {
            this.wxNum = wxNum;
        }

        public int getHeadPortraitImage() {
            return headPortraitImage;
        }

        public void setHeadPortraitImage(int headPortraitImage) {
            this.headPortraitImage = headPortraitImage;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public int getWxSpActivate() {
            return wxSpActivate;
        }

        public void setWxSpActivate(int wxSpActivate) {
            this.wxSpActivate = wxSpActivate;
        }

        public int getAppActivate() {
            return appActivate;
        }

        public void setAppActivate(int appActivate) {
            this.appActivate = appActivate;
        }

        public int getOpenRadarCard() {
            return openRadarCard;
        }

        public void setOpenRadarCard(int openRadarCard) {
            this.openRadarCard = openRadarCard;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public int getUpdateId() {
            return updateId;
        }

        public void setUpdateId(int updateId) {
            this.updateId = updateId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }

        public List<SysRoleListBean> getSysRoleList() {
            return sysRoleList;
        }

        public void setSysRoleList(List<SysRoleListBean> sysRoleList) {
            this.sysRoleList = sysRoleList;
        }

        public static class SysRoleListBean {
            /**
             * id : 2
             * roleNum : 00002
             * roleName : 店长
             * roleType : 2
             * roleDesc : 店长
             * parentId : 1
             * parentIds : ,1,
             * sortId : 2
             * createId : 100000000
             * updateId : 100000000
             * createTime : 2019-05-15 14:46:26.0
             * updateTime : 2019-05-15 14:46:28.0
             * enable : 1
             * del : 1
             * visibleScope : 3
             */

            private int id;
            private String roleNum;
            private String roleName;
            private int roleType;
            private String roleDesc;
            private int parentId;
            private String parentIds;
            private int sortId;
            private int createId;
            private int updateId;
            private String createTime;
            private String updateTime;
            private int enable;
            private int del;
            private int visibleScope;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRoleNum() {
                return roleNum;
            }

            public void setRoleNum(String roleNum) {
                this.roleNum = roleNum;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public int getRoleType() {
                return roleType;
            }

            public void setRoleType(int roleType) {
                this.roleType = roleType;
            }

            public String getRoleDesc() {
                return roleDesc;
            }

            public void setRoleDesc(String roleDesc) {
                this.roleDesc = roleDesc;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getParentIds() {
                return parentIds;
            }

            public void setParentIds(String parentIds) {
                this.parentIds = parentIds;
            }

            public int getSortId() {
                return sortId;
            }

            public void setSortId(int sortId) {
                this.sortId = sortId;
            }

            public int getCreateId() {
                return createId;
            }

            public void setCreateId(int createId) {
                this.createId = createId;
            }

            public int getUpdateId() {
                return updateId;
            }

            public void setUpdateId(int updateId) {
                this.updateId = updateId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public int getVisibleScope() {
                return visibleScope;
            }

            public void setVisibleScope(int visibleScope) {
                this.visibleScope = visibleScope;
            }
        }
    }
}
