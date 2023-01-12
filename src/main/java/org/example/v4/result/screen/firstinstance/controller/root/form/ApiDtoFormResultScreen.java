package org.example.v4.result.screen.firstinstance.controller.root.form;

import org.example.v4.UtilStaticV4;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiDtoFormResultScreen extends JFrame{

    public String domainStr; // entity name, small letter.
    public String[] colStrs;  // entity column, String array.
    public String[] colLongs; // entity column, Long array.
    public String[] colDates; // entity column, LocalDateTime array.

    public String[] colNames; // entity column, In orders.
    public String[] uppColNames; // entity column, In orders. FirstLetter is UpperCase.
    public String uppDomainStr; //First letter Upper case.

    public String thymleafInitUrl;

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public ApiDtoFormResultScreen(UtilStaticV4 usv){
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.colNames = usv.colNames;

        this.uppDomainStr = usv.upperCaseFirstLetter(domainStr);

        this.uppColNames = new String[colNames.length];
        for(int i=0; i<colNames.length; i++){
            this.uppColNames[i] = usv.upperCaseFirstLetter(colNames[i]);
        }

        jp = new JPanel();

        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);

        jp.add(jsp);

        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("File: "+uppDomainStr+"FormApiDto.java | /firstinstance/controller/firstinstanceurl/form/"+uppDomainStr+"FormApiDto.java");
        setBounds(300,300,650,500);

        jta.setText("" +
                "import lombok.Data;\n" +
                "\n" +
                "import java.time.LocalDateTime;\n" +
                "\n" +
                "@Data\n" +
                "public class "+uppDomainStr+"ApiDtoForm {\n" +
                "\n" + columnStrings() +
                /*
                "    private Long id;\n" +
                "    private Long addressStrId;\n" +
                "    private Long phoneStrId;\n" + */

                "    private LocalDateTime createdDate;\n" +
                "    private LocalDateTime modifiedDate;\n" +
                "}\n");
        String code = jta.getText();

        try{
            String path = "C:\\category\\firstinstance\\controller\\firstinstanceurl\\form\\";

            File folder = new File(path);

            if(!folder.exists()) {
                try {
                    Files.createDirectories(Paths.get(path));
                    System.out.println("폴더가 생성되었습니다.");
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
            }
            System.out.println("path, " + path);
            System.out.println(path + "\\"+uppDomainStr+"FormApiDto.java");
            File file = new File(path + "\\"+uppDomainStr+"FormApiDto.java");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(code);
            writer.close();
        }catch(Exception e ){
            e.printStackTrace();
        }
    }



    private String columnStrings() {
        String result = "";
        for(int i=0; i<colNames.length; i++){
            String attr ="";
            for (int j = 0; j < colLongs.length; j++) {
                if(colNames[i].equals(colLongs[j])){
                    attr = "Long";
                }
            }
            for (int k = 0; k < colStrs.length; k++) {
                if(colNames[i].equals(colStrs[k])){
                    attr = "String";
                }
            }
            for (int l = 0; l < colDates.length; l++) {
                if(colNames[i].equals(colDates[l])){
                    attr = "LocalDateTime";
                }
            }
            result += "    private " + attr + " " + colNames[i] + ";\n";
        }
        return result;
    }
}
