package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ResultEntityScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9
            ,jl10;

    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String classNameSm; //alliance
    public String variablePrint; // private Long id; ... private String str1; ...
    public String constPrint; // Long id, String str, ...
    public String constInPrint; // this.id = id, this.str1 = str1, ...

    ResultEntityScreen(UtilStrConvV3 usc){
        String className = usc.getClassNameTables();
        className = usc.getTableName();
        jp= new JPanel();
        jl = new JLabel("@Entity class: " + className);
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("@Entity class: " + className+".java");
        setBounds(300,300,650,500);

        //변수 초기화.
        className = usc.getClassNameTables();
        classNameSm = usc.getTnSmall();
        // private Long id; ...
        String[] colStr = usc.getColStrs(); String[] colLong = usc.getColLongs(); String[] colDate = usc.getColDates();
        variablePrint = usc.getVariablesPrint(colStr, colLong, colDate);
        constPrint = usc.getConstPrint(colStr,colLong,colDate);
        constInPrint = usc.getConstInPrint(colStr,colLong,colDate);
        //변수 이름 초기화...


        String createTable = usc.getCreateTable(colStr, colLong, colDate, usc.getTnSmall());

         jta.setText("" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                 "import lombok.Getter;\n" +
                 "import lombok.RequiredArgsConstructor;\n" +
                 "import lombok.Setter;\n" +
                 "\n" +
                 "import javax.persistence.*;\n" +
                 "import java.util.ArrayList;\n" +
                 "import java.util.List;\n" +
                 "\n" +
                 "@Entity\n" +
                 "@Getter\n" +
                 "@Setter\n" +
                 "@RequiredArgsConstructor\n" +
                 "@Table(name =\""+usc.getTableNameDB()+"\")\n" +
                 "public class "+usc.getClassNameTables()+" {\n" +
                 "\n" +
                 "    @Id\n" +
                 "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
                 "    @Column(name = \""+usc.getTnSmall()+"_id\")\n" +
                 ""+variablePrint+

                 "\n" +

                 "    // 생성 메서드\n" +
                 "    public static "+usc.getClassNameTables()+" create"+usc.getClassNameTables()+"("+usc.getClassNameTables()+" "+usc.getTnSmall()+", "+usc.getClassNameTables()+"SaveForm form) {\n" +
                 "        "+usc.getClassNameTables()+" "+usc.getTnSmall()+" = new "+usc.getClassNameTables()+"();\n" +
                 "        "+createTable +

                 "        "+usc.getTnSmall()+".set"+usc.getToOneTableNameClass()+"("+usc.getToOneTableNameMethod()+");\n " +
                 "        return "+usc.getTnSmall()+";\n" +
                 "    }\n" +
                 "\n" +
                 "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + usc.getClassNameTables() + ".java");
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
