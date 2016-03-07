package com.zy17.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.jdo.annotations.*;
import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class Base implements Serializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String encodedKey;

//    @Persistent
//    @Extension(vendorName="datanucleus", key="gae.pk-name", value="true")
//    private String keyName;

    @Persistent
    private Date createdAt;
    @Persistent
    private Date updatedAt;

    public Base() {
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
