package com.cloud.study.netty.testInuputStream;

import java.io.*;

/**
 * Created by Hw on 2019/10/29 10:48
 */
public class TestInputStream {

    public static void main(String[] args) throws IOException {
        File file = new File("F:\\汇报\\无锡两次数据\\第二次数据\\newFile.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
        String s = "";
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
        }
    }
}
