package org.example.v4.result.screen.templates;

import org.example.v4.UtilStaticV4;
import org.example.v4.result.context.InsertHTMLContextStr;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InsertHTMLResultScreen extends JFrame {
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

    public InsertHTMLResultScreen(UtilStaticV4 usv) {
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
        setTitle("File: insert.html | Thymleaf, /templates/"+thymleafInitUrl+"/" + domainStr + "/insert.html");
        setBounds(300,300,650,500);

        InsertHTMLContextStr insertHTMLContextStr = new InsertHTMLContextStr(usv);

        jta.setText(insertHTMLContextStr.insertHTMLStr);

        String code = jta.getText();
        String[] directoryUrl = usv.thymleafInitUrl.split("/");
        String directory = "firstinstance\\"+domainStr;

        System.out.println(directory);
        directory = "\\templates\\"+directory;
        try{
            String path = "C:\\category"+directory;
            System.out.println("directory, " + path);
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
            System.out.println(path + "insert.html");
            File file = new File(path + "\\insert.html");
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
