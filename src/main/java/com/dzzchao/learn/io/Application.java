package com.dzzchao.learn.io;

import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;

public class Application {

    public static void main(String[] args) {
        // we need a dir here.
        File dir = new File("");
        if (!dir.isDirectory()) {
            System.out.println("do nothing");
        }
        LinkedBlockingDeque<File> dirQueue = new LinkedBlockingDeque<>();
        dirQueue.add(dir);
        for (int i = 0; i < 20; i++) {
            new RenameTask(dirQueue).start();
        }
    }

}
