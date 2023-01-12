package org.example.v4.result.screen;

import org.example.v4.UtilStaticV4;
import org.example.v4.result.context.IndexHTMLContextStr;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexHTMLResultScreen extends JFrame {
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



    public IndexHTMLResultScreen(UtilStaticV4 usv) {
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
        setTitle("File: index.html | Thymleaf, /templates/"+thymleafInitUrl+"/" + domainStr + "/index.html");
        setBounds(300,300,650,500);

        IndexHTMLContextStr indexHTMLContextStr = new IndexHTMLContextStr(usv);

        jta.setText(indexHTMLContextStr.indexHTMLStr);
        String code = jta.getText();
        String[] directoryUrl = usv.thymleafInitUrl.split("/");
        String directory = "";
        for(int i=0; i< directoryUrl.length; i++){
            directory += directoryUrl[i] + "\\";
        }
        System.out.println(directory);
        try{
            String path = "C:\\category"+directory+domainStr;
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
            System.out.println(path + "\\index.html");
            File file = new File(path + "\\index.html");
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
