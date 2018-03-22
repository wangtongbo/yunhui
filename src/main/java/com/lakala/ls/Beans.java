package com.lakala.ls;

import org.springframework.beans.BeanUtils;

/**
 * Created by chenjian on 16/7/15.
 */
public final class Beans {

    public  static  <T>T copy(Object source,Class<T> c)  {
        T t = null;
        try {
            t = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, t);
        return t;

    }
}
