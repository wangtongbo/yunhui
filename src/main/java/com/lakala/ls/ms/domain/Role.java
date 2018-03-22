package com.lakala.ls.ms.domain;

import java.io.Serializable;

/**
 * Created by chenjian on 16/5/16.
 */
public class Role implements Serializable{

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
