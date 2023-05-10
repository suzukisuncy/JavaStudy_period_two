package cn.tedu.translate;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Demo {
    public static void main(String[] args){
//        String sourceFileName = "./数据库笔记/tedu.sql";
        String sourceFileName = "课程资料/API部分笔记MD版-繁体/PART05-线程.md";
        String targetFileName = new StringBuilder(sourceFileName).insert(sourceFileName.lastIndexOf("."),"_繁体").toString();

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFileName), StandardCharsets.UTF_8));
                PrintWriter pw = new PrintWriter(targetFileName,"UTF-8");
        ){
            String line;
            while((line = br.readLine())!=null) {
                pw.println(ZhConverterUtil.convertToTraditional(line));
//            ZhConverterUtil.convertToTraditional(line);//将简体字转换为繁体字
//            ZhConverterUtil.convertToSimple(line);//将繁体字转换为简体字
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
