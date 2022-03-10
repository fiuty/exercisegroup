package classloader;

import java.util.ArrayList;

/**
 * 通过Jprofiler，分析oom，dump hprof文件分析定位问题，
 * @author zhengdayue
 */
public class OomTest {

    byte[] array = new byte[1024 * 1024];

    public static void main(String[] args) {

        ArrayList<OomTest> list = new ArrayList<>(8);
        try {
            while (true) {
                list.add(new OomTest());//通过dump下来的文件分析出问题所在，首先是最大对象list集合占用内存最大
                // 然后分析线程最后定位到17行
            }
        } catch (Exception e) {
            //捕获不到
            System.out.println(e.getMessage());
        } catch (Error error) {
            System.out.println("捕获Erro：" + error.getMessage());
        }
    }
}
