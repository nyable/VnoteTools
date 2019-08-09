package com.hafuhafu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Tang
 * @date : 2019/8/9 10:46
 */
@Data
@AllArgsConstructor
public class FileAttr implements Serializable {
    private String created_time;
    private String modified_time;

}
