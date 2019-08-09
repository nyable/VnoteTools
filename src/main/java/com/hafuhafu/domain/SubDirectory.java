package com.hafuhafu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Tang
 * @date : 2019/8/9 08:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDirectory implements Serializable {
    private String name;
}
