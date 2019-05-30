package com.shushang.shushangjiatui.bean;

public class KeHuXinXi {

    /**
     * ret : 200
     * msg : success!
     * error : null
     * data : {"id":3,"userName":"老和尚3","mobileNumber":"18911647193","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","userId":2,"merchantId":1,"tokenUuid":null,"wxNickName":"老和尚3","wxNum":null,"localHeadPortraitImage":1,"wxHeadPortraitImageUrl":null,"brthdayDate":"2019-05-01","source":null,"cellAddress":null,"intentionalProduct":null,"decorationStyle":null,"sex":1,"createTime":"2019-05-22 15:45:57","updateTime":"2019-05-20 17:02:27","enable":1,"del":1,"lastVisitTime":null,"visitNum":null,"sourceDesc":null,"signingNum":null,"birthdayNum":null}
     */

    private String ret;
    private String msg;
    private Object error;
    private KeHuXinXi.DataBean data;

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

    public KeHuXinXi.DataBean getData() {
        return data;
    }

    public void setData(KeHuXinXi.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * userName : 老和尚3
         * mobileNumber : 18911647193
         * openId : ouUnI5SeHmSVW15DbmQl3P_IiXr3
         * userId : 2
         * merchantId : 1
         * tokenUuid : null
         * wxNickName : 老和尚3
         * wxNum : null
         * localHeadPortraitImage : 1
         * wxHeadPortraitImageUrl : null
         * brthdayDate : 2019-05-01
         * source : null
         * cellAddress : null
         * intentionalProduct : null
         * decorationStyle : null
         * sex : 1
         * createTime : 2019-05-22 15:45:57
         * updateTime : 2019-05-20 17:02:27
         * enable : 1
         * del : 1
         * lastVisitTime : null
         * visitNum : null
         * sourceDesc : null
         * signingNum : null
         * birthdayNum : null
         */

        private int id;
        private String userName;
        private String mobileNumber;
        private String openId;
        private int userId;
        private int merchantId;
        private Object tokenUuid;
        private String wxNickName;
        private Object wxNum;
        private int localHeadPortraitImage;
        private Object wxHeadPortraitImageUrl;
        private String brthdayDate;
        private Object source;
        private Object cellAddress;
        private Object intentionalProduct;
        private Object decorationStyle;
        private int sex;
        private String createTime;
        private String updateTime;
        private int enable;
        private int del;
        private Object lastVisitTime;
        private Object visitNum;
        private Object sourceDesc;
        private Object signingNum;
        private Object birthdayNum;

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

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public Object getTokenUuid() {
            return tokenUuid;
        }

        public void setTokenUuid(Object tokenUuid) {
            this.tokenUuid = tokenUuid;
        }

        public String getWxNickName() {
            return wxNickName;
        }

        public void setWxNickName(String wxNickName) {
            this.wxNickName = wxNickName;
        }

        public Object getWxNum() {
            return wxNum;
        }

        public void setWxNum(Object wxNum) {
            this.wxNum = wxNum;
        }

        public int getLocalHeadPortraitImage() {
            return localHeadPortraitImage;
        }

        public void setLocalHeadPortraitImage(int localHeadPortraitImage) {
            this.localHeadPortraitImage = localHeadPortraitImage;
        }

        public Object getWxHeadPortraitImageUrl() {
            return wxHeadPortraitImageUrl;
        }

        public void setWxHeadPortraitImageUrl(Object wxHeadPortraitImageUrl) {
            this.wxHeadPortraitImageUrl = wxHeadPortraitImageUrl;
        }

        public String getBrthdayDate() {
            return brthdayDate;
        }

        public void setBrthdayDate(String brthdayDate) {
            this.brthdayDate = brthdayDate;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public Object getCellAddress() {
            return cellAddress;
        }

        public void setCellAddress(Object cellAddress) {
            this.cellAddress = cellAddress;
        }

        public Object getIntentionalProduct() {
            return intentionalProduct;
        }

        public void setIntentionalProduct(Object intentionalProduct) {
            this.intentionalProduct = intentionalProduct;
        }

        public Object getDecorationStyle() {
            return decorationStyle;
        }

        public void setDecorationStyle(Object decorationStyle) {
            this.decorationStyle = decorationStyle;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public Object getLastVisitTime() {
            return lastVisitTime;
        }

        public void setLastVisitTime(Object lastVisitTime) {
            this.lastVisitTime = lastVisitTime;
        }

        public Object getVisitNum() {
            return visitNum;
        }

        public void setVisitNum(Object visitNum) {
            this.visitNum = visitNum;
        }

        public Object getSourceDesc() {
            return sourceDesc;
        }

        public void setSourceDesc(Object sourceDesc) {
            this.sourceDesc = sourceDesc;
        }

        public Object getSigningNum() {
            return signingNum;
        }

        public void setSigningNum(Object signingNum) {
            this.signingNum = signingNum;
        }

        public Object getBirthdayNum() {
            return birthdayNum;
        }

        public void setBirthdayNum(Object birthdayNum) {
            this.birthdayNum = birthdayNum;
        }
    }
}
