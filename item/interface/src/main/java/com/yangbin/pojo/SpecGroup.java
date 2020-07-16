package com.yangbin.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/11/011
 * @Time 11:15
 */
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private String name;
    @Transient  //如果表中没有该字段 则忽略
    private List<SpecParam> params;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecParam> getParams() {
        return params;
    }
    public void setParams(List<SpecParam> params) {
        this.params = params;
    }
}

