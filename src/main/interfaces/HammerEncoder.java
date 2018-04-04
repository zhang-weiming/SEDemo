package main.interfaces;

import main.entities.Hammer;

import java.io.*;

public class HammerEncoder {
    // 用HTML表格封装对象属性值
    public static void toHTML(Hammer[] hammers, File file) throws IOException {
        String html =
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <title>Hammers</title>\n" +
                "        <link rel=\"stylesheet\" href=\"https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <table class=\"table\">\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th scope=\"col\">#</th>\n" +
                "                    <th scope=\"col\">Id</th>\n" +
                "                    <th scope=\"col\">Name</th>\n" +
                "                    <th scope=\"col\">Classification</th>\n" +
                "                    <th scope=\"col\">Weight(Kg)</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "                %s\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </body>\n" +
                "</html>\n";
        String tr = "<tr>\n" +
                "      <th scope=\"row\">%d</th>\n" +
                "      <td>%s</td>\n" +
                "      <td>%s</td>\n" +
                "      <td>%s</td>\n" +
                "      <td>%f</td>\n" +
                "    </tr>\n";

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
