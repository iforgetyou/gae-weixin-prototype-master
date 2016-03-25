package com.zy17.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.mortbay.util.ajax.JSON;

import com.google.appengine.api.datastore.DataTypeUtils;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.sun.xml.bind.v2.model.annotation.FieldLocatable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础实体类,只存储基础类型,和google支持的类型
 */
@Slf4j
@Getter
@Setter
public class BaseEntity implements Serializable {
    private long ID;
    private Date createdAt = new Date();
    private Date updatedAt;

    public BaseEntity() {
    }

    public Entity genEntity() throws IllegalAccessException {
        Entity entity = new Entity(this.getClass().getSimpleName());
        Field[] fields = this.getClass().getDeclaredFields();

        if (this.getClass().getSuperclass() != null) {
            // 获取父类字段, todo 暂时未处理多层继承关系
            fields = (Field[]) ArrayUtils.addAll(fields, this.getClass().getSuperclass().getDeclaredFields());
        }

        for (Field field : fields) {
            if (field.getName().equals("ID")) {
                continue;
            }

            field.setAccessible(true);
            Object value = field.get(this);

            if (value == null) {
                // 跳过空字段
                continue;
            }
            if (Modifier.isFinal(field.getModifiers())) {
                // 跳过final类型
                continue;
            }
            // 数据库支持的或者原始字段放入
            if (field.getType().isPrimitive() || DataTypeUtils.isSupportedType(field.getType())) {
                entity.setProperty(field.getName(), value);
            }
        }
        return entity;
    }

    private EmbeddedEntity genEmbeddedEntity(BaseEntity baseEntity) {
        return null;
    }

    public static <T extends BaseEntity> T parseEntity(Map<String, Object> properties, long id, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            if (clazz.getSuperclass() != null) {
                // 获取父类字段, todo 暂时未处理多层继承关系
                fields = (Field[]) ArrayUtils.addAll(fields, clazz.getDeclaredFields());
            }

            for (Field field : fields) {
                Object value = properties.get(field.getName());
                if (value == null) {
                    continue;
                }
                field.setAccessible(true);

                if (Modifier.isFinal(field.getModifiers())) {
                    // 跳过final类型
                    continue;
                }
                if (field.getType().isPrimitive() || DataTypeUtils.isSupportedType(field.getType())) {
                    // 支持的类型处理
                    field.set(t, value);
                }
            }
            t.setID(id);
        } catch (Exception e) {
            log.error("parse entity error", e);
        }

        return t;
    }

    @Override
    public String toString() {
        return JSON.toString(this);
    }
}
