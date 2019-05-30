package com.shushang.shushangjiatui.bean;

public class GeRen {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"MerchantUser":{"cardMobileNumber":"18911647191","couponNum":0,"draftFollowUpNum":4,"eMail":"","goldCoin":1,"goodFriendNum":2,"goodFriendReqNum":0,"headPortraitImage":13,"headPortraitImageUrl":"","id":2,"merchantId":1,"merchantName":"e见家装橱柜","position":"销售","qqNum":"568026721","telPhone":"","userName":"刘翔","wxMobileNumber":"13522190192","wxNum":"1891112"}}
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
         * MerchantUser : {"cardMobileNumber":"18911647191","couponNum":0,"draftFollowUpNum":4,"eMail":"","goldCoin":1,"goodFriendNum":2,"goodFriendReqNum":0,"headPortraitImage":13,"headPortraitImageUrl":"","id":2,"merchantId":1,"merchantName":"e见家装橱柜","position":"销售","qqNum":"568026721","telPhone":"","userName":"刘翔","wxMobileNumber":"13522190192","wxNum":"1891112"}
         */

        private MerchantUserBean MerchantUser;

        public MerchantUserBean getMerchantUser() {
            return MerchantUser;
        }

        public void setMerchantUser(MerchantUserBean MerchantUser) {
            this.MerchantUser = MerchantUser;
        }

        public static class MerchantUserBean {
            /**
             * cardMobileNumber : 18911647191
             * couponNum : 0
             * draftFollowUpNum : 4
             * eMail :
             * goldCoin : 1
             * goodFriendNum : 2
             * goodFriendReqNum : 0
             * headPortraitImage : 13
             * headPortraitImageUrl :
             * id : 2
             * merchantId : 1
             * merchantName : e见家装橱柜
             * position : 销售
             * qqNum : 568026721
             * telPhone :
             * userName : 刘翔
             * wxMobileNumber : 13522190192
             * wxNum : 1891112
             */

            private String cardMobileNumber;
            private int couponNum;
            private int draftFollowUpNum;
            private String eMail;
            private int goldCoin;
            private int goodFriendNum;
            private int goodFriendReqNum;
            private int headPortraitImage;
            private String headPortraitImageUrl;
            private int id;
            private int merchantId;
            private String merchantName;
            private String position;
            private String qqNum;
            private String telPhone;
            private String userName;
            private String wxMobileNumber;
            private String wxNum;

            public String getCardMobileNumber() {
                return cardMobileNumber;
            }

            public void setCardMobileNumber(String cardMobileNumber) {
                this.cardMobileNumber = cardMobileNumber;
            }

            public int getCouponNum() {
                return couponNum;
            }

            public void setCouponNum(int couponNum) {
                this.couponNum = couponNum;
            }

            public int getDraftFollowUpNum() {
                return draftFollowUpNum;
            }

            public void setDraftFollowUpNum(int draftFollowUpNum) {
                this.draftFollowUpNum = draftFollowUpNum;
            }

            public String getEMail() {
                return eMail;
            }

            public void setEMail(String eMail) {
                this.eMail = eMail;
            }

            public int getGoldCoin() {
                return goldCoin;
            }

            public void setGoldCoin(int goldCoin) {
                this.goldCoin = goldCoin;
            }

            public int getGoodFriendNum() {
                return goodFriendNum;
            }

            public void setGoodFriendNum(int goodFriendNum) {
                this.goodFriendNum = goodFriendNum;
            }

            public int getGoodFriendReqNum() {
                return goodFriendReqNum;
            }

            public void setGoodFriendReqNum(int goodFriendReqNum) {
                this.goodFriendReqNum = goodFriendReqNum;
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
