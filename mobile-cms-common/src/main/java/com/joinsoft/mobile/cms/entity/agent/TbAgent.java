package com.joinsoft.mobile.cms.entity.agent;


import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.ad.TbAdStats;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-17.
 */
@Entity
@Table(name = "tb_agent")
public class TbAgent extends AutoModel {
    private String agentName;
    private String fax;
    private String post;



    private String telephone;
    private String addr;
    private String header;

    private Date  operTime;
    private String oper;
    private Integer state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private TbSectionAgent section;
    @Transient
    private Long sectionId;

    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Long getSectionId() {

        if(section==null) {
            if(sectionId==null)
                return  null;
            else
                return  sectionId;
        }
        else{
            return section.getId();
        }

    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public TbSectionAgent getSection() {
        return section;
    }

    public void setSection(TbSectionAgent section) {
        this.section = section;
    }



    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }



    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }



    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}



