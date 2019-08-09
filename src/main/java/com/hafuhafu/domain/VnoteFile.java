package com.hafuhafu.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tang
 * @date : 2019/8/8 16:25
 */
@Data
public class VnoteFile implements Serializable {
    private String attachment_folder = "";
    private List attachments = new ArrayList();
    private String created_time = DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now());
    private String modified_time = DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now());
    private String name;
    private List<String> tags = new ArrayList<>();
}
