package com.voitovich.NVisionTask.data;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@JacksonXmlRootElement(localName = "job")
public class Job {
    @Id
    @JacksonXmlProperty(isAttribute = true)
    private Long id;
    private String type;
    private String user;
    private String device;
    private Long amount;
    private Date time;

    public Job() {
        time = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }
}
