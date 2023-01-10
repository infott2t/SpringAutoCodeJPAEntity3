package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DtoResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String[] colStr, colLong, colDate;
    public String variablePrint, constPrint, constInPrint;

    DtoResultScreen(UtilStrConvV3 usc){
        className = usc.getTableName();

        colStr = usc.getColStrs(); colLong = usc.getColLongs(); colDate = usc.getColDates();
        variablePrint = usc.getVariablesPrint(colStr, colLong, colDate);
        constPrint = usc.getConstPrint(colStr,colLong,colDate);
        constInPrint = usc.getConstInPrint(colStr,colLong,colDate);

        jp= new JPanel();
        jl = new JLabel("@Data: " + className+"ApiDto.java");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("@Data: " + className+"ApiDto.java");
        setBounds(300,300,650,500);

        jta.setText("@Data\n" +
                "//일부 값을 select하기 위해 사용하는 Dto. 값을 제거하거나 해서 사용.\n" +
                "public class "+className+"ApiDto {\n" +
                ""+variablePrint+
                "\n" +
                "    @QueryProjection\n" +
                "    public "+className+"ApiDto("+constPrint+") {\n" +
                ""+constInPrint+"\n"+
                "    }\n" +
                "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "TbApiDto.java");
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
