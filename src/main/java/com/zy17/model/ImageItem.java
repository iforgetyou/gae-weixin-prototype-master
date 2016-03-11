package com.zy17.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: cat
 * Date: 14-7-19
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */

@Data
public class ImageItem extends Base {
    private String openId;
    private String mediaId;
    private String picUrl;
    private String textTag;
}
