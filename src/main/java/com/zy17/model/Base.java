package com.zy17.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.jdo.annotations.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.google.appengine.api.datastore.Entity;

@Slf4j
@Data
public class Base implements Serializable {
    private Date createdAt = new Date();
    private Date updatedAt;

    public Base() {
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

    public Entity genEntity() throws IllegalAccessException {
        Entity entity = new Entity(this.getClass().getSimpleName());
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(this);
            entity.setProperty(field.getName(), value);
        }
        return entity;
    }
}
