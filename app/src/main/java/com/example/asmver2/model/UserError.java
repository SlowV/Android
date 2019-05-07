package com.example.asmver2.model;

public class UserError {
    private boolean isError;
    private String msg;

    public UserError(boolean isError, String msg) {
        this.isError = isError;
        this.msg = msg;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
