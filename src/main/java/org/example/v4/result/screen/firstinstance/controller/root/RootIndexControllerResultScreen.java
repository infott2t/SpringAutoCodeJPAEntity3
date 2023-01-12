package org.example.v4.result.screen.firstinstance.controller.root;

import org.example.v4.UtilStaticV4;
import org.example.v4.result.context.RootIndexContextStr;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RootIndexControllerResultScreen extends JFrame {

    public String domainStr; // entity name, small letter.
    public String[] colStrs;  // entity column, String array.
    public String[] colLongs; // entity column, Long array.
    public String[] colDates; // entity column, LocalDateTime array.

    public String thymleafInitUrl;

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public RootIndexControllerResultScreen(UtilStaticV4 usv){
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.thymleafInitUrl = usv.thymleafInitUrl;

        jp = new JPanel();

        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);

        jp.add(jsp);

        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("File: InstanceUrlController.java | /firstinstance/controller/firstinstanceurl/InstanceUrlController.java");
        setBounds(300,300,650,500);



        jta.setText("" +
                "@Controller\n" +
                "public class InstanceUrlController {\n" +
                "\n" +
                "    @GetMapping(\"/administer/instanceurl\")\n" +
                "    public String index(){\n" +
                "        //firstInstance index의 처음 위치.\n" +
                "        return \"firstInstance/index\";\n" +
                "    }\n" +
                "\n" +
                "    // 개발중, 테스트용 url연결 만듬.\n" +
                "    @GetMapping(\"/\")\n" +
                "    public String index2(){\n" +
                "\n" +
                "        return \"redirect:/administer/instanceurl\";\n" +
                "    }\n" +
                "\n" +
                "}\n");
        String code = jta.getText();


        try{
            String path = "C:\\category\\firstinstance\\controller\\firstinstanceurl\\domain\\";

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
            System.out.println(path + "\\InstanceUrlController.java");
            File file = new File(path + "\\InstanceUrlController.java");
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

}

