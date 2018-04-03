package main.interfaces;

import main.entities.Hammer;

import java.io.*;

public class HammerEncoder {
    // 用HTML表格封装对象属性值
    public static void toHTML(Hammer[] hammers, File file) throws IOException {
        String html =
                "<!DOCTYPE html>" +
                "<html>" +
                    "<head><meta charset='utf-8'><title>Hammers</title></head>" +
                    "<body>" +
                        "<table border='1' cellspacing='0'>" +
                            "<tr><th></th><th>Id</th><th>Name</th><th>Class</th><th>Weight(Kg)</th></tr>" +
                            "%s" +
                        "</table>" +
                    "</body>" +
                "</html>";
        String tr = "<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%f</td></tr>";

        String trs = "";
        int cnt = 1;
        for (Hammer hammer : hammers)
            trs += String.format(tr,
                    cnt++, hammer.getId(), hammer.getName(), hammer.getClassification(), hammer.getWeight());

        BufferedWriter bufw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        bufw.write(String.format(html, trs));
        bufw.flush();
        bufw.close();
    }
//    public static void  toXML(Foo[] foos,  File file){…}//用XML表格封装对象属性值
//    public static void  toConsole(Foo[] foos){…} //输出到控制台
//    public static void  toHTML(Foo[] foos,  String  fileName) throws IOException {…}
//    public static void  toXML(Foo[] foos,  String  fileName) throws IOException {…}
}
