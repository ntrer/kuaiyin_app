package com.shushang.shushangjiatui.bean;

import java.util.List;

public class XiaoShouPaiHang {


    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"UserSaleInfo":[{"addCustomerNum":0,"cardMobileNumber":"18911647190","comprehensiveNum":1,"consCustomerNum":0,"customerNum":1,"eMail":"","followUpCustomerNum":0,"headPortraitImage":14,"headPortraitImageUrl":"","id":1,"position":"销售","qqNum":"568026722","roleId":0,"roleName":0,"telPhone":"","userName":"申向玉","wxMobileNumber":"13522190193","wxNum":"1891111"},{"addCustomerNum":0,"cardMobileNumber":"18911647191","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":13,"headPortraitImageUrl":"","id":2,"position":"销售","qqNum":"568026721","roleId":0,"roleName":0,"telPhone":"","userName":"刘翔","wxMobileNumber":"13522190192","wxNum":"1891112"},{"addCustomerNum":0,"cardMobileNumber":"18911647192","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":12,"headPortraitImageUrl":"","id":3,"position":"销售","qqNum":"568026720","roleId":0,"roleName":0,"telPhone":"","userName":"姚明","wxMobileNumber":"13522190191","wxNum":"1891113"},{"addCustomerNum":0,"cardMobileNumber":"18911647194","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":10,"headPortraitImageUrl":"","id":5,"position":"销售","qqNum":"568026718","roleId":0,"roleName":0,"telPhone":"","userName":"林心如","wxMobileNumber":"13522190189","wxNum":"1891115"}],"intmaxCount":4,"intcurrentPage":1,"intpageSize":10,"intmaxPage":1}
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
         * UserSaleInfo : [{"addCustomerNum":0,"cardMobileNumber":"18911647190","comprehensiveNum":1,"consCustomerNum":0,"customerNum":1,"eMail":"","followUpCustomerNum":0,"headPortraitImage":14,"headPortraitImageUrl":"","id":1,"position":"销售","qqNum":"568026722","roleId":0,"roleName":0,"telPhone":"","userName":"申向玉","wxMobileNumber":"13522190193","wxNum":"1891111"},{"addCustomerNum":0,"cardMobileNumber":"18911647191","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":13,"headPortraitImageUrl":"","id":2,"position":"销售","qqNum":"568026721","roleId":0,"roleName":0,"telPhone":"","userName":"刘翔","wxMobileNumber":"13522190192","wxNum":"1891112"},{"addCustomerNum":0,"cardMobileNumber":"18911647192","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":12,"headPortraitImageUrl":"","id":3,"position":"销售","qqNum":"568026720","roleId":0,"roleName":0,"telPhone":"","userName":"姚明","wxMobileNumber":"13522190191","wxNum":"1891113"},{"addCustomerNum":0,"cardMobileNumber":"18911647194","comprehensiveNum":0,"consCustomerNum":0,"customerNum":0,"eMail":"","followUpCustomerNum":0,"headPortraitImage":10,"headPortraitImageUrl":"","id":5,"position":"销售","qqNum":"568026718","roleId":0,"roleName":0,"telPhone":"","userName":"林心如","wxMobileNumber":"13522190189","wxNum":"1891115"}]
         * intmaxCount : 4
         * intcurrentPage : 1
         * intpageSize : 10
         * intmaxPage : 1
         */

        private int intmaxCount;
        private int intcurrentPage;
        private int intpageSize;
        private int intmaxPage;
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
             * addCustomerNum : 0
             * cardMobileNumber : 18911647190
             * comprehensiveNum : 1
             * consCustomerNum : 0
             * customerNum : 1
             * eMail :
             * followUpCustomerNum : 0
             * headPortraitImage : 14
             * headPortraitImageUrl :
             * id : 1
             * position : 销售
             * qqNum : 568026722
             * roleId : 0
             * roleName : 0
             * telPhone :
             * userName : 申向玉
             * wxMobileNumber : 13522190193
             * wxNum : 1891111
             */

            private int addCustomerNum;
            private String cardMobileNumber;
            private int comprehensiveNum;
            private int consCustomerNum;
            private int customerNum;
            private String eMail;
            private int followUpCustomerNum;
            private int headPortraitImage;
            private String headPortraitImageUrl;
            private int id;
            private String position;
            private String qqNum;
            private int roleId;
            private int roleName;
            private String telPhone;
            private String userName;
            private String wxMobileNumber;
            private String wxNum;

            public int getAddCustomerNum() {
                return addCustomerNum;
            }

            public void setAddCustomerNum(int addCustomerNum) {
                this.addCustomerNum = addCustomerNum;
            }

            public String getCardMobileNumber() {
                return cardMobileNumber;
            }

            public void setCardMobileNumber(String cardMobileNumber) {
                this.cardMobileNumber = cardMobileNumber;
            }

            public int getComprehensiveNum() {
                return comprehensiveNum;
            }

            public void setComprehensiveNum(int comprehensiveNum) {
                this.comprehensiveNum = comprehensiveNum;
            }

            public int getConsCustomerNum() {
                return consCustomerNum;
            }

            public void setConsCustomerNum(int consCustomerNum) {
                this.consCustomerNum = consCustomerNum;
            }

            public int getCustomerNum() {
                return customerNum;
            }

            public void setCustomerNum(int customerNum) {
                this.customerNum = customerNum;
            }

            public String getEMail() {
                return eMail;
            }

            public void setEMail(String eMail) {
                this.eMail = eMail;
            }

            public int getFollowUpCustomerNum() {
                return followUpCustomerNum;
            }

            public void setFollowUpCustomerNum(int followUpCustomerNum) {
                this.followUpCustomerNum = followUpCustomerNum;
            }

            public int getHeadPortraitImage() {
                return headPortraitImage;
            }

            public void setHeadPortraitImage(int headPortraitImage) {
                this.headPortraitImage = headPortraitImage;
            }

            public String getHeadPortraitImageUrl() {
                return headPortraitImageUrl;
            }

            public void setHeadPortraitImageUrl(String headPortraitImageUrl) {
                this.headPortraitImageUrl = headPortraitImageUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public int getRoleName() {
                return roleName;
            }

            public void setRoleName(int roleName) {
                this.roleName = roleName;
            }

            public String getTelPhone() {
                return telPhone;
            }

            public void setTelPhone(String telPhone) {
                this.telPhone = telPhone;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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
        }
    }
}
