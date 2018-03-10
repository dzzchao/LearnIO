package com.dzzchao;

import java.io.File;

public class RenameFile {
    public static void main(String[] args) {

        String dir = "D:\\AndroidStudioProjects";
        File file = new File(dir);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        File[] files = file.listFiles();
        RenameFile main = new RenameFile();
        main.searchFile(files);
        /*
           1. 遍历出所有文件目录
           2. 只要是目录就进入子目录
           3. 子目录如果有目录在继续遍历出所有目录
         */

    }

    /**
     * 递归
     * @param files
     */
    private void searchFile(File[] files) {
        for (File file : files) {
            if(file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                File[] files1 = file.listFiles();
                for (File file1 : files1) {
                    if(file1.isFile()) {
                        String name = file1.getAbsolutePath();
                        if(name.endsWith(".bak")) {
                            System.out.println(name);
                            String substring = name.substring(0, name.length() - 4);
                            System.out.println(substring);
                            boolean isRename = file1.renameTo(new File(substring));
                            if(isRename) {
                                System.out.println("YYYYYYYYYYYYYes!!!!!!!!!");
                            } else {
                                System.out.println("wrong");
                            }
                        }
                    }
                }
                searchFile(files1);
            }
        }
    }
}