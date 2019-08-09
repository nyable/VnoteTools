package com.hafuhafu.utils;

/**
 * 获取常用字符串
 *
 * @author : Tang
 * @date : 2019/8/5 11:54
 */
public class StringHelper {
    /**
     * 获取当前用户的工作目录
     */
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * 获取当前系统的换行符
     */
    public static final String LINESEPARATOR = System.getProperty("line.separator");
    /**
     * 获取当前系统的文件分隔符 "/"
     */
    public static final String FILESEPARATOR = System.getProperty("file.separator");
    /**
     * 获取当前系统的路径分隔符
     */
    public static final String PATHSEPARATOR = System.getProperty("path.separator");

    /**
     * 获取运行时Java环境版本
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");
    /**
     * 获取运行时Java安装目录
     */
    public static final String JAVA_HOME = System.getProperty("java.home");
    /**
     * 获取系统名称
     */
    public static final String SYSTEM_NAME = System.getProperty("os.name");
    /**
     * 获取系统架构
     */
    public static final String SYSTEM_ARCH = System.getProperty("os.arch");
    /**
     * 获取系统版本
     */
    public static final String SYSTEM_VERSION = System.getProperty("os.version");
}
