package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class SearchConditionResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance

    SearchConditionResultScreen(UtilStrConvV3 usc){
        className = usc.getClassNameTables();
        className = usc.getTableName();
        jp= new JPanel();
        jl = new JLabel("@Data: " + className+"SearchCondition");
        jta = new JTextArea(15,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Data: " + className+"SearchCondition.java");
        setBounds(300,300,650,500);

        jta.setText("" +
                "import lombok.Data;\n" +
                "\n" +
                "@Data\n" +
                "public class "+className+"SearchCondition {\n" +
                "\n" +
                "    private String field;       \n" +
                "    private String s;          \n" +
                "\n" +
                "    private String sdate;      \n" +
                "    private String edate;      \n" +
                "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "SearchCondition.java");
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
