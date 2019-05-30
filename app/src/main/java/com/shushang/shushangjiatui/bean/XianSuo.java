package com.shushang.shushangjiatui.bean;

import java.util.List;

public class XianSuo {


    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"customerClueList":[{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-17 17:37:40","customerId":3,"del":1,"firstVisitTime":"2019-05-16 17:11:58","headPortraitImage":1,"id":5,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 17:11:58","latelyMonthVisitNum":0,"mobileNumber":"18911647193","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","rorwardCustomerId":1,"rorwardUserId":1,"source":1,"sourceDesc":"来自客户主动搜索","stopDuration":300,"updateTime":"2019-05-17 17:37:40","userId":2,"userName":"老和尚3","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":4,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":2,"id":6,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647194","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","rorwardCustomerId":1,"rorwardUserId":1,"source":2,"sourceDesc":"来自客户名片扫码","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚4","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":5,"del":1,"firstVisitTime":"2019-05-16 17:11:58","headPortraitImage":13,"id":7,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 17:11:58","latelyMonthVisitNum":0,"mobileNumber":"18911647195","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc5","rorwardCustomerId":1,"rorwardUserId":1,"source":3,"sourceDesc":"来自活动分发","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚5","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":6,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":11,"id":8,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647196","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","rorwardCustomerId":1,"rorwardUserId":1,"source":4,"sourceDesc":"来自分享文章","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚6","visitBcardNum":1},{"authMobile":0,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":7,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":12,"id":9,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647197","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","rorwardCustomerId":1,"rorwardUserId":1,"source":5,"sourceDesc":"来自* 就像避免采到花的老虎*转发","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚7","visitBcardNum":1}],"intmaxCount":5,"intcurrentPage":1,"intpageSize":10,"intmaxPage":1}
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
         * customerClueList : [{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-17 17:37:40","customerId":3,"del":1,"firstVisitTime":"2019-05-16 17:11:58","headPortraitImage":1,"id":5,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 17:11:58","latelyMonthVisitNum":0,"mobileNumber":"18911647193","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","rorwardCustomerId":1,"rorwardUserId":1,"source":1,"sourceDesc":"来自客户主动搜索","stopDuration":300,"updateTime":"2019-05-17 17:37:40","userId":2,"userName":"老和尚3","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":4,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":2,"id":6,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647194","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","rorwardCustomerId":1,"rorwardUserId":1,"source":2,"sourceDesc":"来自客户名片扫码","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚4","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":5,"del":1,"firstVisitTime":"2019-05-16 17:11:58","headPortraitImage":13,"id":7,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 17:11:58","latelyMonthVisitNum":0,"mobileNumber":"18911647195","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc5","rorwardCustomerId":1,"rorwardUserId":1,"source":3,"sourceDesc":"来自活动分发","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚5","visitBcardNum":1},{"authMobile":1,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":6,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":11,"id":8,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647196","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","rorwardCustomerId":1,"rorwardUserId":1,"source":4,"sourceDesc":"来自分享文章","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚6","visitBcardNum":1},{"authMobile":0,"browsePageNum":0,"createTime":"2019-05-20 17:11:22","customerId":7,"del":1,"firstVisitTime":"2019-05-16 18:10:59","headPortraitImage":12,"id":9,"intentionCustomer":1,"intentionality":4.8,"isExchangeMobileNum":0,"lastVisitTime":"2019-05-16 18:10:59","latelyMonthVisitNum":0,"mobileNumber":"18911647197","openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","rorwardCustomerId":1,"rorwardUserId":1,"source":5,"sourceDesc":"来自* 就像避免采到花的老虎*转发","stopDuration":300,"updateTime":"2019-05-20 17:11:22","userId":2,"userName":"老和尚7","visitBcardNum":1}]
         * intmaxCount : 5
         * intcurrentPage : 1
         * intpageSize : 10
         * intmaxPage : 1
         */

        private int intmaxCount;
        private int intcurrentPage;
        private int intpageSize;
        private int intmaxPage;
        private List<CustomerClueListBean> customerClueList;

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

        public List<CustomerClueListBean> getCustomerClueList() {
            return customerClueList;
        }

        public void setCustomerClueList(List<CustomerClueListBean> customerClueList) {
            this.customerClueList = customerClueList;
        }

        public static class CustomerClueListBean {
            /**
             * authMobile : 1
             * browsePageNum : 0
             * createTime : 2019-05-17 17:37:40
             * customerId : 3
             * del : 1
             * firstVisitTime : 2019-05-16 17:11:58
             * headPortraitImage : 1
             * id : 5
             * intentionCustomer : 1
             * intentionality : 4.8
             * isExchangeMobileNum : 0
             * lastVisitTime : 2019-05-16 17:11:58
             * latelyMonthVisitNum : 0
             * mobileNumber : 18911647193
             * openId : ouUnI5SeHmSVW15DbmQl3P_IiXr3
             * rorwardCustomerId : 1
             * rorwardUserId : 1
             * source : 1
             * sourceDesc : 来自客户主动搜索
             * stopDuration : 300
             * updateTime : 2019-05-17 17:37:40
             * userId : 2
             * userName : 老和尚3
             * visitBcardNum : 1
             */

            private int authMobile;
            private int browsePageNum;
            private String createTime;
            private int customerId;
            private int del;
            private String firstVisitTime;
            private int headPortraitImage;
            private int id;
            private int intentionCustomer;
            private double intentionality;
            private int isExchangeMobileNum;
            private String lastVisitTime;
            private int latelyMonthVisitNum;
            private String mobileNumber;
            private String openId;
            private int rorwardCustomerId;
            private int rorwardUserId;
            private int source;
            private String sourceDesc;
            private int stopDuration;
            private String updateTime;
            private int userId;
            private String userName;
            private int visitBcardNum;

            public int getAuthMobile() {
                return authMobile;
            }

            public void setAuthMobile(int authMobile) {
                this.authMobile = authMobile;
            }

            public int getBrowsePageNum() {
                return browsePageNum;
            }

            public void setBrowsePageNum(int browsePageNum) {
                this.browsePageNum = browsePageNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public String getFirstVisitTime() {
                return firstVisitTime;
            }

            public void setFirstVisitTime(String firstVisitTime) {
                this.firstVisitTime = firstVisitTime;
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

            public int getIntentionCustomer() {
                return intentionCustomer;
            }

            public void setIntentionCustomer(int intentionCustomer) {
                this.intentionCustomer = intentionCustomer;
            }

            public double getIntentionality() {
                return intentionality;
            }

            public void setIntentionality(double intentionality) {
                this.intentionality = intentionality;
            }

            public int getIsExchangeMobileNum() {
                return isExchangeMobileNum;
            }

            public void setIsExchangeMobileNum(int isExchangeMobileNum) {
                this.isExchangeMobileNum = isExchangeMobileNum;
            }

            public String getLastVisitTime() {
                return lastVisitTime;
            }

            public void setLastVisitTime(String lastVisitTime) {
                this.lastVisitTime = lastVisitTime;
            }

            public int getLatelyMonthVisitNum() {
                return latelyMonthVisitNum;
            }

            public void setLatelyMonthVisitNum(int latelyMonthVisitNum) {
                this.latelyMonthVisitNum = latelyMonthVisitNum;
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

            public int getRorwardCustomerId() {
                return rorwardCustomerId;
            }

            public void setRorwardCustomerId(int rorwardCustomerId) {
                this.rorwardCustomerId = rorwardCustomerId;
            }

            public int getRorwardUserId() {
                return rorwardUserId;
            }

            public void setRorwardUserId(int rorwardUserId) {
                this.rorwardUserId = rorwardUserId;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public String getSourceDesc() {
                return sourceDesc;
            }

            public void setSourceDesc(String sourceDesc) {
                this.sourceDesc = sourceDesc;
            }

            public int getStopDuration() {
                return stopDuration;
            }

            public void setStopDuration(int stopDuration) {
                this.stopDuration = stopDuration;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
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

            public int getVisitBcardNum() {
                return visitBcardNum;
            }

            public void setVisitBcardNum(int visitBcardNum) {
                this.visitBcardNum = visitBcardNum;
            }
        }
    }
}
