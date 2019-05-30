package com.shushang.shushangjiatui.bean;

import java.util.List;

public class GenJInLieBiao {


    /**
     * ret : 200
     * msg : success!
     * error : null
     * data : [{"id":3,"userId":2,"userName":"刘翔","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","customerId":3,"createTime":"2019-05-23 09:33:58.0","nextFollowUpTime":"2019-05-25 00:00:00.0","followUpMode":3,"followUpText":"da","draftRecord":1,"del":1,"merchantId":null},{"id":2,"userId":2,"userName":"刘翔","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","customerId":3,"createTime":"2019-05-23 09:26:35.0","nextFollowUpTime":"2019-05-23 00:00:00.0","followUpMode":2,"followUpText":"asds","draftRecord":1,"del":1,"merchantId":null},{"id":1,"userId":2,"userName":"刘翔","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","customerId":3,"createTime":"2019-05-23 09:22:50.0","nextFollowUpTime":"2019-05-22 00:00:00.0","followUpMode":1,"followUpText":"ç\u0088±ä¸\u008aæ\u0089\u0093æ\u0089«æ\u0089\u0093æ\u0089«ç\u009a\u0084","draftRecord":0,"del":1,"merchantId":null}]
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
         * id : 3
         * userId : 2
         * userName : 刘翔
         * openId : ouUnI5SeHmSVW15DbmQl3P_IiXr3
         * customerId : 3
         * createTime : 2019-05-23 09:33:58.0
         * nextFollowUpTime : 2019-05-25 00:00:00.0
         * followUpMode : 3
         * followUpText : da
         * draftRecord : 1
         * del : 1
         * merchantId : null
         */

        private int id;
        private int userId;
        private String userName;
        private String openId;
        private int customerId;
        private String createTime;
        private String nextFollowUpTime;
        private int followUpMode;
        private String followUpText;
        private int draftRecord;
        private int del;
        private Object merchantId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getNextFollowUpTime() {
            return nextFollowUpTime;
        }

        public void setNextFollowUpTime(String nextFollowUpTime) {
            this.nextFollowUpTime = nextFollowUpTime;
        }

        public int getFollowUpMode() {
            return followUpMode;
        }

        public void setFollowUpMode(int followUpMode) {
            this.followUpMode = followUpMode;
        }

        public String getFollowUpText() {
            return followUpText;
        }

        public void setFollowUpText(String followUpText) {
            this.followUpText = followUpText;
        }

        public int getDraftRecord() {
            return draftRecord;
        }

        public void setDraftRecord(int draftRecord) {
            this.draftRecord = draftRecord;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public Object getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(Object merchantId) {
            this.merchantId = merchantId;
        }
    }
}
