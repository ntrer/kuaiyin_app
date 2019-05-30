package com.shushang.shushangjiatui.bean;

public class ShuJuZongLan {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : {"cumFollowUpNum":0,"useDayNum":"已使用E家11天 | 以下数据截止至今日 14:17","cumShareNum":0,"cumVisitNum":4,"cumConsultationNum":0,"cumCustomerNum":1}
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
         * cumFollowUpNum : 0
         * useDayNum : 已使用E家11天 | 以下数据截止至今日 14:17
         * cumShareNum : 0
         * cumVisitNum : 4
         * cumConsultationNum : 0
         * cumCustomerNum : 1
         */

        private int cumFollowUpNum;
        private String useDayNum;
        private int cumShareNum;
        private int cumVisitNum;
        private int cumConsultationNum;
        private int cumCustomerNum;

        public int getCumFollowUpNum() {
            return cumFollowUpNum;
        }

        public void setCumFollowUpNum(int cumFollowUpNum) {
            this.cumFollowUpNum = cumFollowUpNum;
        }

        public String getUseDayNum() {
            return useDayNum;
        }

        public void setUseDayNum(String useDayNum) {
            this.useDayNum = useDayNum;
        }

        public int getCumShareNum() {
            return cumShareNum;
        }

        public void setCumShareNum(int cumShareNum) {
            this.cumShareNum = cumShareNum;
        }

        public int getCumVisitNum() {
            return cumVisitNum;
        }

        public void setCumVisitNum(int cumVisitNum) {
            this.cumVisitNum = cumVisitNum;
        }

        public int getCumConsultationNum() {
            return cumConsultationNum;
        }

        public void setCumConsultationNum(int cumConsultationNum) {
            this.cumConsultationNum = cumConsultationNum;
        }

        public int getCumCustomerNum() {
            return cumCustomerNum;
        }

        public void setCumCustomerNum(int cumCustomerNum) {
            this.cumCustomerNum = cumCustomerNum;
        }
    }
}
