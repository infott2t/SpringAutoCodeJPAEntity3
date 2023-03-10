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
            }else if(title.equals("RepositoryImpl2")) {
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "RepositoryImpl2.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "RepositoryImpl2.java");
            }else if(title.equals("SearchCondition")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "SearchCondition.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "SearchCondition.java");
            }else if(title.equals("SearchCondition2")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "SearchCondition2.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "SearchCondition2.java");
            } else if(title.equals("Service")) {
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "Service.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "Service.java");
            }else if(title.equals("SearchCondition")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "SearchCondition.java");
                setTitle("@"+ title + " :" + usv.toUpperFirst(domainStr) + "SearchCondition.java");
            }else if(title.equals("Index")){
                file = new File(folderStr+ "index.html");
                setTitle("@"+ title + " : /templates/firstInstance/" + usv.toLowerFirst(domainStr) + "/index.html");
            }else if(title.equals("Insert")){
                file = new File(folderStr+ "insert.html");
                setTitle("@"+ title + " : /templates/firstInstance/" + usv.toLowerFirst(domainStr) + "/insert.html");
            }else if(title.equals("Update")){
                file = new File(folderStr+ "update.html");
                setTitle("@"+ title + " : /templates/firstInstance/" + usv.toLowerFirst(domainStr) + "/update.html");
            }else if(title.equals("RootIndexController")){
                file = new File(folderStr + "InstanceUrlController.java");
                setTitle("@"+ title + " : firstinstance.controller.firstinstanceurl.InstanceUrlController.java");
            }else if(title.equals("IndexController")){
                file = new File(folderStr + "InstanceUrl"+usv.toUpperFirst(domainStr) + "Controller.java");
                setTitle("@"+ title + " : firstinstance.controller.firstinstanceurl.domain." + usv.toAllLowerCase(domainStr) + ".InstanceUrl"+usv.toUpperFirst(domainStr)+"Controller.java");
            }else if(title.equals("ApiDtoForm")){
                file = new File(folderStr + usv.toUpperFirst(domainStr) + "ApiDtoForm.java");
                setTitle("@"+ title + " : firstinstance.controller.firstinstanceurl.form."+usv.toUpperFirst(domainStr)+"ApiDtoForm.java");
            }

            if(file!=null) {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.write(contextStr);
                writer.close();
            }

                //중복파일이 발생할때... 파일이 지워지지 않아야하는 경우. 맨처음 CURD index파일. 여러 게시판으로 이동하는 링크가 있는 경우.
            File file1=null;

            if(title.equals("RootIndex")){
                file1 = new File(folderStr + "index.html");
                setTitle("@"+ title + " : /templates/firstInstance/index.html");
            }

            if(file1!=null) {
                if (!file1.exists()) {
                    file1.createNewFile();
                    FileWriter fw1 = new FileWriter(file1);
                    BufferedWriter writer1 = new BufferedWriter(fw1);
                    writer1.write(contextStr);
                    writer1.close();
                }
            }

        }catch(Exception e3 ){
            e3.printStackTrace();
        }
    }

}
