package com.hafuhafu.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tang
 * @date : 2019/8/8 16:23
 */
@Data
public class Vnote implements Serializable {
    private String created_time = DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now());
    private List<VnoteFile> files;
    private List<SubDirectory> sub_directories = new ArrayList<>();
    private String version = "1";
//    private String recycle_bin_folder = "_v_recycle_bin";
}
