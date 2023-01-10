package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HashMapInputScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String classNameSm; //alliance.


    HashMapInputScreen(UtilStrConvV3 usc){
        className = usc.getClassNameTables();
        //변수 초기화.
        className = usc.getTableName();
        classNameSm = usc.getTnSmall();
        // private Long id; ...
        String[] colStr = usc.getColStrs(); String[] colLong = usc.getColLongs(); String[] colDate = usc.getColDates();

        String printHashMap = usc.getPrintHashMap(classNameSm, colStr, colLong, colDate);

        jp= new JPanel();
        jl = new JLabel("Util: " + className+", HashMap Print");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle(": " + className+", HashMap Print");
        setBounds(300,300,650,500);
/*
        "        HashMap<String, Object> hashMap = new HashMap<>();\n" +
                "        hashMap.put(\"content\", noticeApiList);\n" +
                "        hashMap.put(\"pageable\", noticeList.getPageable());\n" +
                "        hashMap.put(\"totalElements\", noticeList.getTotalElements());\n" +
                "        hashMap.put(\"totalPages\", noticeList.getTotalPages());\n" +
                "        hashMap.put(\"size\", noticeList.getSize());");
*/
        jta.setText("" +
                "        HashMap<String, Object> hashMap = new HashMap<>();\n"
                +printHashMap
                );

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "HashMapPrint.java");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(code);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
