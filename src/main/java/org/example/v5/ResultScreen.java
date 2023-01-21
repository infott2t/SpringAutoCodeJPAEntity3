package org.example.v5;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResultScreen extends JFrame {

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public UtilStaticV5 usv;
    public ResultScreen(String title, String contextStr, String folderStr, String domainStr, UtilStaticV5 usv) {

        this.usv = usv;
        jp= new JPanel();
        //jl = new JLabel("@"+ title + " :" + usv.toUpperFirst(domainStr));
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);

        //jp.add(jl);
        jp.add(jsp);
        add(jp);
        setVisible(true);
        setResizable(true);

        setBounds(300,300,600,400);
        jta.setText(contextStr);

        try{

            File folder = new File(folderStr);

            if(!folder.exists()) {
                try {
                    Files.createDirectories(Paths.get(folderStr));
                    System.out.println("폴더가 생성되었습니다.");
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            } else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
            }
            System.out.println("path, " + folderStr);
            File file = null;
            if(title.equals("Entity")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + ".java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + ".java");
            }else if(title.equals("ApiDto")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "ApiDto.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "ApiDto.java");
            }else if(title.equals("Repository")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "Repository.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "Repository.java");
            }else if(title.equals("RepositoryCustom")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "RepositoryCustom.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "RepositoryCustom.java");
            }else if(title.equals("RepositoryImpl")) {
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "RepositoryImpl.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "RepositoryImpl.java");
            }else if(title.equals("SearchCondition")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "SearchCondition.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "SearchCondition.java");
            }else if(title.equals("Service")) {
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "Service.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "Service.java");
            }else if(title.equals("SearchCondition")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "SearchCondition.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "SearchCondition.java");
            }else if(title.equals("RootIndex")){
                file = new File(folderStr + "index.html");
                setTitle("@"+ title + " : /templates/firstInstance/index.html");
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(contextStr);
            writer.close();
        }catch(Exception e3 ){
            e3.printStackTrace();
        }
    }

}
