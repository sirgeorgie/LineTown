package com.company.linetownelection.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Date;

@JmixEntity
@Table(name = "CANDIDATES")
@Entity
public class Candidates {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "BIO_LINK")
    private String bioLink;

    @Column(name = "IMAGE_LINK")
    private String imageLink;

    @Column(name = "POLICY")
    private String policy;

    @Column(name = "VOTED_COUNT",columnDefinition = "integer default 0")
    private Integer votedCount;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Transient
    @JmixProperty
    private String percentage;

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setVotedCount(Integer votedCount) {
        this.votedCount = votedCount;
    }

    public Integer getVotedCount() {
        return votedCount;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setBioLink(String bioLink) {
        this.bioLink = bioLink;
    }

    public String getBioLink() {
        return bioLink;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PostConstruct
    void init() {
        setVotedCount(0);
    }
}