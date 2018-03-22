package com.lakala.ls;

/**
 * Created by chenjian on 2017/4/26.
 */
public class LsException extends Exception {

    String code;

    Object[] args;

    String defaultMessage;

    Throwable error;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public LsException(String code) {
        this(code, null, null, null);
    }

    public LsException(String code, String defaultMessage) {
        this(code, null, defaultMessage, null);
    }

    public LsException(String code, Throwable error) {
        this(code, null, null, error);
    }

    public LsException(String code, String defaultMessage, Throwable error) {
        this(code, null, defaultMessage, error);
    }

    public LsException(String code, Object[] args, Throwable error) {
        this(code, args, null, error);
    }

    public LsException(String code, Object[] args) {
        this(code, args, null, null);
    }

    public LsException(String code, Object[] args, String defaultMessage, Throwable error) {
        super(toMsg(code,defaultMessage), error);
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
        this.error = error;
    }

    static String toMsg(String code, String msg) {
        if(code==null)
            return msg;
        if(msg == null)
            return "[" + code + "]";
        else
            return "[" + code + "] " + msg;
    }
}
