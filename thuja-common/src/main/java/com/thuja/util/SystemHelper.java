package com.thuja.util;

import lombok.Data;

import java.io.File;

/**
 * @author: Ansen
 * @create: 2022-01-09 15:42
 **/
@Data
public class SystemHelper {
    public static String getUploadDir(){
        String dir = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("windows")){
            dir = "D:/upload/";
        }else{
            dir = "/tmp/upload/";
        }
        new File(dir).mkdir();
        if (!dir.endsWith("/") && !dir.endsWith("\\")) {
            dir += "/";
        }
        return dir;
    }
}
