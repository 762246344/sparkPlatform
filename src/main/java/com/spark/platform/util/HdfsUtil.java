package com.spark.platform.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.AclEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhouqi on 2017/1/3.
 */
public class HdfsUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HdfsUtil.class);

    public static Configuration conf;
    public static FileSystem fs;

    static {
        conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            fs = FileSystem.get(URI.create("hdfs://master:9000"), conf);
        } catch (Exception e) {

        }
    }

    public FileStatus getFile(String path) throws IOException {
        return fs.getFileStatus(new Path(path));
    }

    public static List<Map<String, String>> getFiles(String path) {
        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        FileStatus[] fileStatuses = new FileStatus[0];
        try {
            fileStatuses = fs.listStatus(new Path(path));
        } catch (IOException e) {
            LOGGER.warn("", e);
        }
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("fileName", fileStatus.getPath().getName());
                map.put("filePath", fileStatus.getPath().toString());
                fileList.add(map);
            }
        }
        return fileList;
    }

    public static boolean delete(String path, boolean recursive) throws IOException {
        return fs.delete(new Path(path), recursive);
    }

    public static void put(String hdfsPath, MultipartFile file) throws IOException {
        LOGGER.info("正在上传... {}", hdfsPath);
        InputStream inputStream = file.getInputStream();
        FSDataOutputStream outStream = fs.create(new Path(hdfsPath));
        byte buffer[] = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }
        inputStream.close();
        outStream.close();
    }

    public static void printDir(Path path)
            throws FileNotFoundException, IOException {
        FileStatus[] listStatus = fs.listStatus(path);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (FileStatus fileStatus : listStatus) {
            if (fileStatus.isFile()) {
                System.out.println("-" + fileStatus.getPermission() + "\t"
                        + fileStatus.getReplication() + "\t"
                        + fileStatus.getOwner() + "\t" + fileStatus.getGroup()
                        + "\t" + fileStatus.getLen() + "\t"
                        + "\t" + simpleDateFormat.format(new Date(fileStatus.getModificationTime())) + "\t"
                        + fileStatus.getPath().toString().substring(fileStatus.getPath().toString().indexOf("9000") + 4));
            }

            if (fileStatus.isDirectory()) {
                System.out.println("d" + fileStatus.getPermission() + "\t"
                        + fileStatus.getReplication() + "\t"
                        + fileStatus.getOwner() + "\t" + fileStatus.getGroup()
                        + "\t" + fileStatus.getLen() + "\t"
                        + "\t" + simpleDateFormat.format(new Date(fileStatus.getModificationTime())) + "\t"
                        + fileStatus.getPath().toString().substring(fileStatus.getPath().toString().indexOf("9000") + 4));

                printDir(fileStatus.getPath());
            }
        }
    }

    public static List<Path> getRePath(Path path) {
        List<Path> list = new ArrayList<Path>();
        FileStatus[] listStatus = new FileStatus[0];
        try {
            listStatus = fs.listStatus(path);
            for (FileStatus fileStatus : listStatus) {
                list.add(fileStatus.getPath());
                if (fileStatus.isDirectory()) {
                    list.addAll(getRePath(fileStatus.getPath()));
                }
            }
        } catch (IOException e) {

        }
        return list;
    }

    public static boolean setAcl(Path path, List<AclEntry> aclSpec) {
        try {
            for (Path path1 : getRePath(path)) {
                fs.setAcl(path1, aclSpec);
            }
        } catch (IOException e) {

        }
        return true;
    }
}
