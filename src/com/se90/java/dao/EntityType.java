package com.se90.java.dao;

import com.se90.java.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/3/31 22:47
 * @description:
 * @modified By:
 */
@AllArgsConstructor
public enum EntityType {
    USER("user","./data/user.json", User.class);
    String name;
    String filePath;
    Class<?> type;


    public Class<?> getType() {
        return type;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

}
