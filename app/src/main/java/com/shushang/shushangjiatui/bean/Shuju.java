package com.shushang.shushangjiatui.bean;

import java.util.List;

public class Shuju {


    /**
     * ret : 200
     * msg : success
     * error : null
     * data : [{"time":"2019-05-13","count":0},{"time":"2019-05-14","count":0},{"time":"2019-05-15","count":0},{"time":"2019-05-16","count":0},{"time":"2019-05-17","count":1},{"time":"2019-05-18","count":0},{"time":"2019-05-19","count":0},{"time":"2019-05-20","count":0}]
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
         * time : 2019-05-13
         * count : 0
         */

        private String time;
        private int count;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
