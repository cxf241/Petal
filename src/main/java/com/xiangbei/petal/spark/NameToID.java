package com.xiangbei.petal.spark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @ClassName: NameToID
 * @Description:将爬虫爬到的用户电影评分信息处理，把每个用户对应到一个用户ID
 * @author: tyrion
 * @date: 2018年7月13日 下午8:07:56
 */
public class NameToID {

    public static void main(String[] args) throws Exception {

        //读取文件路径
        BufferedReader in = new BufferedReader(new FileReader("ml-100k/data.txt"));
        String line; // 一行数据

        ArrayList arr = new ArrayList();
        int j = 0;
        // 逐行读取，并将每个数组放入到数组中
        while ((line = in.readLine()) != null) {
            String[] temp = line.split("\t");
            //取出用户ID
            arr.add(temp[1]);
            j++;
        }
        in.close();

        //利用Hashset删除相同数据
        HashSet h = new HashSet(arr);
        arr.clear();
        arr.addAll(h);

        // 显示读取出的数组
        File file = new File("out.txt");  //存放数组数据的文件
        FileWriter out = new FileWriter(file);  //文件写入流

        //将数组中的数据写入到文件中。
        int n = 1;
        for(int i=0;i<arr.size();i++){
            out.write(n+"\t");
            out.write(arr.get(i)+"\t\n");
            n++;
        }
        out.close();
        System.out.println("finished");
    }
}
