package com.shushang.shushangjiatui.bean;

import java.util.List;

public class JIaDou {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"customerFollowUpList":[{"createTime":"2019-05-10 18:14:15.0","headImage":"11","merchantId":"1","merchantName":"e见家装橱柜","repay":"0","sumBean":"0","userId":"4","userName":"贝克汉姆"}]}
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
        private List<CustomerFollowUpListBean> customerFollowUpList;

        public List<CustomerFollowUpListBean> getCustomerFollowUpList() {
            return customerFollowUpList;
        }

        public void setCustomerFollowUpList(List<CustomerFollowUpListBean> customerFollowUpList) {
            this.customerFollowUpList = customerFollowUpList;
        }

        public static class CustomerFollowUpListBean {
            /**
             * createTime : 2019-05-10 18:14:15.0
             * headImage : 11
             * merchantId : 1
             * merchantName : e见家装橱柜
             * repay : 0
             * sumBean : 0
             * userId : 4
             * userName : 贝克汉姆
             */

            private String createTime;
            private String headImage;
            private String merchantId;
            private String merchantName;
            private String repay;
            private String sumBean;
            private String userId;
            private String userName;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public String getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(String merchantId) {
                this.merchantId = merchantId;
            }

            public String getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(String merchantName) {
                this.merchantName = merchantName;
            }

            public String getRepay() {
                return repay;
            }

            public void setRepay(String repay) {
                this.repay = repay;
            }

            public String getSumBean() {
                return sumBean;
            }

            public void setSumBean(String sumBean) {
                this.sumBean = sumBean;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
