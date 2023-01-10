package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ApiDtoResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String classNameSm; //alliance
    public String variablePrint; // private Long id; ... private String str1; ...
    public String constPrint; // Long id, String str, ...
    public String constInPrint; // this.id = id, this.str1 = str1, ...

    public String builderPrint;
    ApiDtoResultScreen(UtilStrConvV3 usc){

        //변수 초기화.
        className = usc.getTableName();
        classNameSm = usc.getTnSmall();
        // private Long id; ...
        String[] colStr = usc.getColStrs(); String[] colLong = usc.getColLongs(); String[] colDate = usc.getColDates();
        variablePrint = usc.getVariablesPrint(colStr, colLong, colDate);
        constPrint = usc.getConstPrint(colStr,colLong,colDate);
        constInPrint = usc.getConstInPrint(colStr,colLong,colDate);
        builderPrint = usc.getBuilderPrint(colStr,colLong,colDate);
        jp= new JPanel();
        jl = new JLabel("@Data: " + className+"ApiDto");
        jta = new JTextArea(15,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Dto: " + className+"ApiDto.java");
        setBounds(300,300,650,500);

        jta.setText("" +
                "import com.querydsl.core.annotations.QueryProjection;\n" +
                "import lombok.Data;\n" +
                "\n" +
                "@Data\n" +
                "\n"+
                "public class "+className+"ApiDto {\n" +
                ""+variablePrint+
                "\n" +
                "    @QueryProjection\n" +
                "    public "+className+"ApiDto("+constPrint+") {\n" +
                ""+constInPrint+"\n"+
                "    }\n" +
                "/*Builder,   "+builderPrint+"*/"+
                "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "ApiDto.java");
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
