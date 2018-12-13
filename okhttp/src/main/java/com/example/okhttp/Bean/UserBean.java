package com.example.okhttp.Bean;

import java.util.List;

public class UserBean {
    public String msg;
    public String code;
    public List<DataNews> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataNews> getData() {
        return data;
    }

    public void setData(List<DataNews> data) {
        this.data = data;
    }

    public class DataNews{

        private String createtime;
        private String icon;
        private String name;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
