package com.dzzchao.learn.io;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class RenameTask extends Thread {

    private final LinkedBlockingDeque<File> dirQueue;

    public RenameTask(LinkedBlockingDeque<File> dirQueue) {
        this.dirQueue = dirQueue;
    }

    @Override
    public void run() {
        while (true) {
            File dir;
            try {
                dir = dirQueue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                return;
            }
            if (dir == null) {
                break;
            }
            File[] files = dir.listFiles();
            if (files == null) {
                return;
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    dirQueue.add(f);
                    continue;
                }
                String name = f.getName();
                if (!FilenameUtils.isExtension(name, "bak")) {
                    continue;
                }
                String newName = FilenameUtils.getBaseName(name);
                File newFile = new File(f.getParentFile(), newName);
                if (newFile.isFile() && newFile.exists()) {
                    System.err.println(String.format("文件已存在:%s", name));
                    continue;
                }
                if (f.renameTo(newFile)) {
                    System.out.println(String.format("文件重命名成功:%s", name));
                    continue;
                }
                System.err.println(String.format("文件已存在:%s", name));
            }
        }
    }
}
