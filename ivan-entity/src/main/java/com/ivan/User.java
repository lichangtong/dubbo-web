package com.ivan;

import java.io.Serializable;

/**
 * @author lichangtong
 * @create 2017-08-21 21:13
 **/
public class User implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -5451685937793368311L;

    private String id;
    private String nickyName;
    private String pwd;

    public User(String id, String nickyName, String pwd) {
        this.id = id;
        this.nickyName = nickyName;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickyName() {
        return nickyName;
    }

    public void setNickyName(String nickyName) {
        this.nickyName = nickyName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nickyName=" + nickyName + ", pwd=" + pwd
                + "]";
    }
}
