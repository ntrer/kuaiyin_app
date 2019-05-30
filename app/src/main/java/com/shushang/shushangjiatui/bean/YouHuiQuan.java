package com.shushang.shushangjiatui.bean;

import java.util.List;

public class YouHuiQuan {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"couponList":[{"couponClass":1,"couponCode":"10001","couponDesc":"满100减20","couponDetails":"[{\"Type\":\"heading\",\"Value\":\"这里输入标题\",\"FontSize\":\"small\"},{\"Type\":\"paragraph\",\"Value\":\"段落文字1<br>段落文2\",\"TextAlign\":\"left\",\"FontSize\":\"small\"},{\"Type\":\"image\",\"Value\":\"16\"},{\"Type\":\"heading\",\"Value\":\"这里输入标题2\",\"FontSize\":\"small\"},{\"Type\":\"paragraph\",\"Value\":\"段落文字2<br>段落文2\",\"TextAlign\":\"left\",\"FontSize\":\"small\"},{\"Type\":\"image\",\"Value\":\"17\"}]","couponId":"2","couponMode":2,"couponName":"满减券","couponNum":10,"couponPrice":"20","createId":1,"createTime":"2019-05-13 14:37:07.0","customerCouponCode":"","customerCouponId":0,"customerId":"1","customerName":"老和尚","del":1,"discount":0,"enable":1,"endTime":"2019-05-30 14:36:49.0","id":1,"merchantCode":"ejjz","merchantId":1,"receive":0,"receiveTime":"","startTime":"2019-05-13 14:36:45.0","unavailableDesc":"","updateId":1,"updateTime":"2019-05-13 14:37:09.0","usableRange":"商品1","usedTime":"","userd":0}],"intmaxCount":1,"intcurrentPage":1,"intpageSize":10,"intmaxPage":1}
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
         * couponList : [{"couponClass":1,"couponCode":"10001","couponDesc":"满100减20","couponDetails":"[{\"Type\":\"heading\",\"Value\":\"这里输入标题\",\"FontSize\":\"small\"},{\"Type\":\"paragraph\",\"Value\":\"段落文字1<br>段落文2\",\"TextAlign\":\"left\",\"FontSize\":\"small\"},{\"Type\":\"image\",\"Value\":\"16\"},{\"Type\":\"heading\",\"Value\":\"这里输入标题2\",\"FontSize\":\"small\"},{\"Type\":\"paragraph\",\"Value\":\"段落文字2<br>段落文2\",\"TextAlign\":\"left\",\"FontSize\":\"small\"},{\"Type\":\"image\",\"Value\":\"17\"}]","couponId":"2","couponMode":2,"couponName":"满减券","couponNum":10,"couponPrice":"20","createId":1,"createTime":"2019-05-13 14:37:07.0","customerCouponCode":"","customerCouponId":0,"customerId":"1","customerName":"老和尚","del":1,"discount":0,"enable":1,"endTime":"2019-05-30 14:36:49.0","id":1,"merchantCode":"ejjz","merchantId":1,"receive":0,"receiveTime":"","startTime":"2019-05-13 14:36:45.0","unavailableDesc":"","updateId":1,"updateTime":"2019-05-13 14:37:09.0","usableRange":"商品1","usedTime":"","userd":0}]
         * intmaxCount : 1
         * intcurrentPage : 1
         * intpageSize : 10
         * intmaxPage : 1
         */

        private int intmaxCount;
        private int intcurrentPage;
        private int intpageSize;
        private int intmaxPage;
        private List<CouponListBean> couponList;

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

        public List<CouponListBean> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListBean> couponList) {
            this.couponList = couponList;
        }

        public static class CouponListBean {
            /**
             * couponClass : 1
             * couponCode : 10001
             * couponDesc : 满100减20
             * couponDetails : [{"Type":"heading","Value":"这里输入标题","FontSize":"small"},{"Type":"paragraph","Value":"段落文字1<br>段落文2","TextAlign":"left","FontSize":"small"},{"Type":"image","Value":"16"},{"Type":"heading","Value":"这里输入标题2","FontSize":"small"},{"Type":"paragraph","Value":"段落文字2<br>段落文2","TextAlign":"left","FontSize":"small"},{"Type":"image","Value":"17"}]
             * couponId : 2
             * couponMode : 2
             * couponName : 满减券
             * couponNum : 10
             * couponPrice : 20
             * createId : 1
             * createTime : 2019-05-13 14:37:07.0
             * customerCouponCode :
             * customerCouponId : 0
             * customerId : 1
             * customerName : 老和尚
             * del : 1
             * discount : 0
             * enable : 1
             * endTime : 2019-05-30 14:36:49.0
             * id : 1
             * merchantCode : ejjz
             * merchantId : 1
             * receive : 0
             * receiveTime :
             * startTime : 2019-05-13 14:36:45.0
             * unavailableDesc :
             * updateId : 1
             * updateTime : 2019-05-13 14:37:09.0
             * usableRange : 商品1
             * usedTime :
             * userd : 0
             */

            private int couponClass;
            private String couponCode;
            private String couponDesc;
            private String couponDetails;
            private String couponId;
            private int couponMode;
            private String couponName;
            private int couponNum;
            private String couponPrice;
            private int createId;
            private String createTime;
            private String customerCouponCode;
            private int customerCouponId;
            private String customerId;
            private String customerName;
            private int del;
            private int discount;
            private int enable;
            private String endTime;
            private int id;
            private String merchantCode;
            private int merchantId;
            private int receive;
            private String receiveTime;
            private String startTime;
            private String unavailableDesc;
            private int updateId;
            private String updateTime;
            private String usableRange;
            private String usedTime;
            private int userd;

            public int getCouponClass() {
                return couponClass;
            }

            public void setCouponClass(int couponClass) {
                this.couponClass = couponClass;
            }

            public String getCouponCode() {
                return couponCode;
            }

            public void setCouponCode(String couponCode) {
                this.couponCode = couponCode;
            }

            public String getCouponDesc() {
                return couponDesc;
            }

            public void setCouponDesc(String couponDesc) {
                this.couponDesc = couponDesc;
            }

            public String getCouponDetails() {
                return couponDetails;
            }

            public void setCouponDetails(String couponDetails) {
                this.couponDetails = couponDetails;
            }

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public int getCouponMode() {
                return couponMode;
            }

            public void setCouponMode(int couponMode) {
                this.couponMode = couponMode;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public int getCouponNum() {
                return couponNum;
            }

            public void setCouponNum(int couponNum) {
                this.couponNum = couponNum;
            }

            public String getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(String couponPrice) {
                this.couponPrice = couponPrice;
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

            public String getCustomerCouponCode() {
                return customerCouponCode;
            }

            public void setCustomerCouponCode(String customerCouponCode) {
                this.customerCouponCode = customerCouponCode;
            }

            public int getCustomerCouponId() {
                return customerCouponId;
            }

            public void setCustomerCouponId(int customerCouponId) {
                this.customerCouponId = customerCouponId;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
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

            public int getReceive() {
                return receive;
            }

            public void setReceive(int receive) {
                this.receive = receive;
            }

            public String getReceiveTime() {
                return receiveTime;
            }

            public void setReceiveTime(String receiveTime) {
                this.receiveTime = receiveTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getUnavailableDesc() {
                return unavailableDesc;
            }

            public void setUnavailableDesc(String unavailableDesc) {
                this.unavailableDesc = unavailableDesc;
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

            public String getUsableRange() {
                return usableRange;
            }

            public void setUsableRange(String usableRange) {
                this.usableRange = usableRange;
            }

            public String getUsedTime() {
                return usedTime;
            }

            public void setUsedTime(String usedTime) {
                this.usedTime = usedTime;
            }

            public int getUserd() {
                return userd;
            }

            public void setUserd(int userd) {
                this.userd = userd;
            }
        }
    }
}
