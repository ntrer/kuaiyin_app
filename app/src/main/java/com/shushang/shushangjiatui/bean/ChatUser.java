package com.shushang.shushangjiatui.bean;

public class ChatUser {

    /**
     * ret : 200
     * msg : success
     * error : null
     * data : ydd
     * draw : null
     * recordsTotal : null
     */

    private String ret;
    private String msg;
    private Object error;
    private String data;
    private Object draw;
    private Object recordsTotal;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getDraw() {
        return draw;
    }

    public void setDraw(Object draw) {
        this.draw = draw;
    }

    public Object getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Object recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
