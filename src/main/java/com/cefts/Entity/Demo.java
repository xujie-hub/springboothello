package com.cefts.Entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Demo {
    private int id;//主键
    private String name;//名字

    //对创建时间进行格式化处理
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    //对备注字段不进行序列化
    @JSONField(serialize = false)
    private String remark;//备注

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
