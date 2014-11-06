package com.joinsoft.mobile.cms.entity.news;

import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 14-11-5.
 */
@Entity
@Table(name = "tb_news")
public class TbNews extends AutoModel {
    private String title;
    private String content;
    private Date operTime;
    private String oper;
    private String online;
    private Long state;

    public TbNews(String title, String content, Date operTime) {
        this.title = title;
        this.content = content;
        this.operTime = operTime;
    }

    public TbNews() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "TbNews{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", oper_time=" + operTime +
                ", oper=" + oper +
                ", online=" + online +
                ", state=" + state +
                '}';
    }
}
