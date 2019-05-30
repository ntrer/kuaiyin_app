package com.shushang.shushangjiatui.bean;

import java.util.List;

public class LeiDa {


    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"radarRecordList":[{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":12,"id":38,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":11,"id":33,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":5,"customerName":"老和尚5","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":13,"id":28,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc5","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚5分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":4,"customerName":"老和尚4","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":2,"id":23,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚4分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":3,"customerName":"老和尚3","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":1,"id":18,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚3分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":12,"id":37,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":11,"id":32,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":4,"customerName":"老和尚4","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":2,"id":22,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚4查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 20:11:58","headPortraitImage":12,"id":36,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":5,"radarDictDesc":"查看了活动","startTime":"2019-05-16 20:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7查看了活动","title":"点击活动","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 20:11:58","headPortraitImage":11,"id":31,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":5,"radarDictDesc":"查看了活动","startTime":"2019-05-16 20:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6查看了活动","title":"点击活动","userId":2,"wxNickName":""}],"intmaxCount":25,"intcurrentPage":1,"intpageSize":10,"intmaxPage":3}
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
         * radarRecordList : [{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":12,"id":38,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":11,"id":33,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":5,"customerName":"老和尚5","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":13,"id":28,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc5","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚5分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":4,"customerName":"老和尚4","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":2,"id":23,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚4分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":3,"customerName":"老和尚3","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":1,"id":18,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXr3","radarDectId":7,"radarDictDesc":"分享你的名片","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚3分享你的名片","title":"分享名片","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":12,"id":37,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":11,"id":32,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":4,"customerName":"老和尚4","del":0,"desc":"1","endTime":"2019-05-16 21:11:58","headPortraitImage":2,"id":22,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc4","radarDectId":6,"radarDictDesc":"查看了爆款","startTime":"2019-05-16 21:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚4查看了爆款","title":"查看爆款","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":7,"customerName":"老和尚7","del":0,"desc":"1","endTime":"2019-05-16 20:11:58","headPortraitImage":12,"id":36,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc7","radarDectId":5,"radarDictDesc":"查看了活动","startTime":"2019-05-16 20:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚7查看了活动","title":"点击活动","userId":2,"wxNickName":""},{"customerHeadImg":0,"customerId":6,"customerName":"老和尚6","del":0,"desc":"1","endTime":"2019-05-16 20:11:58","headPortraitImage":11,"id":31,"merchantId":0,"openId":"ouUnI5SeHmSVW15DbmQl3P_IiXrc6","radarDectId":5,"radarDictDesc":"查看了活动","startTime":"2019-05-16 20:10:58","stopTime":60,"stopTimeStr":"停留时长01分00秒","textDesc":"老和尚6查看了活动","title":"点击活动","userId":2,"wxNickName":""}]
         * intmaxCount : 25
         * intcurrentPage : 1
         * intpageSize : 10
         * intmaxPage : 3
         */

        private int intmaxCount;
        private int intcurrentPage;
        private int intpageSize;
        private int intmaxPage;
        private List<RadarRecordListBean> radarRecordList;

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

        public List<RadarRecordListBean> getRadarRecordList() {
            return radarRecordList;
        }

        public void setRadarRecordList(List<RadarRecordListBean> radarRecordList) {
            this.radarRecordList = radarRecordList;
        }

        public static class RadarRecordListBean {
            /**
             * customerHeadImg : 0
             * customerId : 7
             * customerName : 老和尚7
             * del : 0
             * desc : 1
             * endTime : 2019-05-16 21:11:58
             * headPortraitImage : 12
             * id : 38
             * merchantId : 0
             * openId : ouUnI5SeHmSVW15DbmQl3P_IiXrc7
             * radarDectId : 7
             * radarDictDesc : 分享你的名片
             * startTime : 2019-05-16 21:10:58
             * stopTime : 60
             * stopTimeStr : 停留时长01分00秒
             * textDesc : 老和尚7分享你的名片
             * title : 分享名片
             * userId : 2
             * wxNickName :
             */

            private int customerHeadImg;
            private int customerId;
            private String customerName;
            private int del;
            private String desc;
            private String endTime;
            private int headPortraitImage;
            private int id;
            private int merchantId;
            private String openId;
            private int radarDectId;
            private String radarDictDesc;
            private String startTime;
            private int stopTime;
            private String stopTimeStr;
            private String textDesc;
            private String title;
            private int userId;
            private String wxNickName;

            public int getCustomerHeadImg() {
                return customerHeadImg;
            }

            public void setCustomerHeadImg(int customerHeadImg) {
                this.customerHeadImg = customerHeadImg;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
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

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public int getRadarDectId() {
                return radarDectId;
            }

            public void setRadarDectId(int radarDectId) {
                this.radarDectId = radarDectId;
            }

            public String getRadarDictDesc() {
                return radarDictDesc;
            }

            public void setRadarDictDesc(String radarDictDesc) {
                this.radarDictDesc = radarDictDesc;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getStopTime() {
                return stopTime;
            }

            public void setStopTime(int stopTime) {
                this.stopTime = stopTime;
            }

            public String getStopTimeStr() {
                return stopTimeStr;
            }

            public void setStopTimeStr(String stopTimeStr) {
                this.stopTimeStr = stopTimeStr;
            }

            public String getTextDesc() {
                return textDesc;
            }

            public void setTextDesc(String textDesc) {
                this.textDesc = textDesc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getWxNickName() {
                return wxNickName;
            }

            public void setWxNickName(String wxNickName) {
                this.wxNickName = wxNickName;
            }
        }
    }
}
