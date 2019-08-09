package com;

import com.hafuhafu.domain.FileAttr;
import com.hafuhafu.domain.SubDirectory;
import com.hafuhafu.domain.Vnote;
import com.hafuhafu.domain.VnoteFile;
import com.hafuhafu.utils.JsonUtils;
import com.hafuhafu.utils.StringHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入md文件信息到Vnote的配置文件
 *
 * @author : Tang
 * @date : 2019/8/8 16:30
 */
public class AppStart {
    /**
     * 记录Vnote文件关系的Json配置文件
     */
    public static final String JSON_FILE_NAME = "_vnote.json";
    /**
     * md文件个数
     */
    private static int fileno;
    /**
     * 目录个数
     */
    private static int dirno;

    /**
     * 1.获取根目录情况,分别获取以.md结尾的文件和文件夹
     * 2.根据.md结尾的文件集合 修改_vote.json
     * 3.根据文件夹集合修改_vote.json
     * 4.修改完毕后进入文件夹集合进行相同操作
     */
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        File root = new File(StringHelper.PROJECT_PATH);
        try {
            read(root);
        } catch (IOException e) {
            System.out.println("执行时发生异常:" + e);
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("共添加" + dirno + "个文件夹," + fileno + "个md文件");
            System.out.println("Total:" + (endTime - beginTime) / 1000 + "ms");
        }

    }

    /**
     * 获取文件的创建时间和最后修改时间
     *
     * @param file 文件
     * @return 包含创建时间和最后修改时间的对象
     * @throws IOException
     */
    private static FileAttr getFileAttr(File file) throws IOException {
        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(Paths.get(file.toURI()), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes fileAttributes = basicFileAttributeView.readAttributes();
        String createdTime = DateTimeFormatter.ISO_INSTANT.format(fileAttributes.creationTime().toInstant());
        String modifiedTime = DateTimeFormatter.ISO_INSTANT.format(fileAttributes.lastModifiedTime().toInstant());
        return new FileAttr(createdTime, modifiedTime);
    }

    private static void read(File root) throws IOException {
        if (root.isDirectory()) {
            //如果是目录才运行
            System.out.println("开始添加文件夹:" + root.getName());
            //获取子目录
            File[] dirFiles = root.listFiles(File::isDirectory);
            dirno += dirFiles.length;
            //获取该目录下的md文件
            File[] mdFiles = root.listFiles((dir, name) -> name.contains(".md") || name.contains(".mkd") || name.contains(".markdown"));
            fileno += mdFiles.length;
            Vnote vnote = new Vnote();
            FileAttr rootAttr = getFileAttr(root);
            vnote.setCreated_time(rootAttr.getCreated_time());
            List<VnoteFile> vnoteFileList = new ArrayList<>();
            for (File mdFile : mdFiles) {
                String fileName = mdFile.getName();
                System.out.println("添加md文件:" + fileName);
                VnoteFile vnoteFile = new VnoteFile();
                vnoteFile.setName(fileName);
                FileAttr attr = getFileAttr(mdFile);
                vnoteFile.setCreated_time(attr.getCreated_time());
                vnoteFile.setModified_time(attr.getModified_time());
                vnoteFileList.add(vnoteFile);
            }
            vnote.setFiles(vnoteFileList);
            vnote.setCreated_time(DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now()));
            List<SubDirectory> subDirectoryList = new ArrayList<>();
            for (File dirFile : dirFiles) {
                subDirectoryList.add(new SubDirectory(dirFile.getName()));
                //为目录建立文件夹
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                }
                FileUtils.write(new File(dirFile, JSON_FILE_NAME), JsonUtils.objectToJson(new Vnote()), StandardCharsets.UTF_8);
                //递归
                read(dirFile);
            }
            vnote.setSub_directories(subDirectoryList);
            String json = JsonUtils.objectToJson(vnote);
            FileUtils.write(new File(root, JSON_FILE_NAME), json, StandardCharsets.UTF_8);
        } else {
            System.out.println("非目录,不可执行");
        }
    }
}

