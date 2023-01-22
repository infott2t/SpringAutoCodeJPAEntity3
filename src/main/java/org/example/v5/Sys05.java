package org.example.v5;

import org.example.themes.MyFlatLaf;
import org.example.v4.UtilStaticV4;
import org.example.v4.result.screen.ReadMeScreen;
import org.example.v4.result.screen.firstinstance.controller.root.RootIndexControllerResultScreen;
import org.example.v4.result.screen.firstinstance.controller.root.domain.InstanceUrlControllerResultScreen;
import org.example.v4.result.screen.firstinstance.controller.root.form.ApiDtoFormResultScreen;
import org.example.v4.result.screen.templates.IndexHTMLResultScreen;
import org.example.v4.result.screen.templates.InsertHTMLResultScreen;
import org.example.v4.result.screen.templates.UpdateHTMLResultScreen;
import org.example.v4.result.screen.templates.root.RootIndexResultScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sys05 extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
    jl10,jl11,jl12,jl13,jl14;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn,btn2;

    static UtilStaticV5 usv; // Save input values


    String columnStrings;
    String columnLongs;
    String columnDates;

    String columnNames;
    String domainStr;

    String foreignColStr;

    String[] colStrs;
    String[] colLongs;
    String[] colDates;

    String[] colNames;

    String thymleafInitUrl;
    String rootPackageStr;

    Sys05()throws Exception{
        jp= new JPanel();
        jl = new JLabel("Entity name(CamelCase):");
        jl2 = new JLabel(": ");
        jtf = new JTextField(20);
        jl3 = new JLabel("Entity Copy paste. ex) Long id; String nameStr; LocalDateTime dateStr; ");
        jta = new JTextArea(5,10);
        jta2 = new JTextArea(5, 10);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(480,100));
        jsp2 = new JScrollPane(jta2);
        jsp2.setPreferredSize(new Dimension(480,100));
        jtf2 = new JTextField(27);

        jl4 = new JLabel("Spring Mapping. foreign key. (Option) ex) UserOne userOne; Post post; --> UserOne,Post");
        //jtf3 = new JTextField(20);
        jl5 = new JLabel("Starting Controller url: ");
        jtf4 = new JTextField();
        jl6 = new JLabel("Root package (Option) ex) com.example.projectname");
        jtf5 = new JTextField(40);
        btn2 = new JButton("ReadMe");
        btn = new JButton("Extract Redundant logic...");

        jp.add(jl);
        jp.add(jl2);
        jp.add(jtf);
        jp.add(jl3);
        jp.add(jsp);
        jp.add(jl4);
        jp.add(jsp2);
        jp.add(jl5);
        jp.add(jtf2);
        jp.add(jl6);
        jp.add(jtf5);

        jp.add(btn2);
        jp.add(btn);
        jp.add(jtf4);
        setVisible(true);
        setResizable(true);
        add(jp);
        setBounds(300,300,500,500);
        setTitle("v5 SpringBoot JPA + QueryDSL. support many redundant files.");
        jtf2.setText("/administer/instanceurl");
        //jtf5.setText("com.example.projectname");
        jtf4.setText("Github, https://github.com/infott2t/SpringAutoCodeJPAEntity3");

        jta.setText("Long id;\n\nString isDel;\nLocalDateTime modifiedDate;\nLocalDateTime createdDate;");;
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadMeScreen2();
            }
        });
        btn.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {

                domainStr = jtf.getText();
                rootPackageStr = jtf5.getText();

                System.out.println("rootPackageStr = " + rootPackageStr);
                if(rootPackageStr.equals("")){
                    rootPackageStr = null;
                    System.out.println("rootPackageStr = " + rootPackageStr);
                }

                String line;
                BufferedReader reader = new BufferedReader(new StringReader(jta.getText()));
                columnStrings="";
                columnLongs="";
                columnDates="";
                columnNames="";
                try{
                    while((line = reader.readLine())!=null){
                        System.out.println(line);
                        if(line.indexOf("Long")>=0) {
                            // System.out.println(line.indexOf("Long") );
                            String longStr = line.substring(line.indexOf("Long") + 5);
                            longStr = longStr.replace(";", "");
                            columnLongs = columnLongs + longStr + ",";
                            columnNames = columnNames + longStr + ",";
                        }
                        if(line.indexOf("String")>=0) {
                            // System.out.println(line.indexOf("Long") );
                            String strings = line.substring(line.indexOf("String") + 7);
                            strings = strings.replace(";", "");
                            columnStrings = columnStrings + strings + ",";
                            columnNames = columnNames + strings + ",";
                        }

                        if(line.indexOf("LocalDateTime")>=0){
                            String dates = line.substring(line.indexOf("LocalDateTime") + 14);
                            dates = dates.replace(";", "");
                            columnDates = columnDates + dates + ",";
                            columnNames = columnNames + dates + ",";
                        }

                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                if(columnLongs!=null && columnLongs.length()>0) {
                    columnLongs = columnLongs.substring(0, columnLongs.length() - 1);
                }

                if(columnStrings!=null && columnStrings.length()>0) {
                    columnStrings = columnStrings.substring(0, columnStrings.length() - 1);
                }

                if(columnDates!=null && columnDates.length()>0) {
                    columnDates = columnDates.substring(0, columnDates.length() - 1);
                }
                //columnDates = columnDates.substring(0,columnDates.length()-1);

                columnNames = columnNames.substring(0,columnNames.length()-1);

                colStrs = columnStrings.split(",");
                colLongs = columnLongs.split(",");
                colDates = columnDates.split(",");
                colNames = columnNames.split(",");

                System.out.println(colStrs.toString());
                System.out.println(colLongs.toString());
                System.out.println(colNames.toString());

                for(int i=0; i< colStrs.length ;i++ ){
                    System.out.println(colStrs[i]);
                }
                for (int i = 0; i < colNames.length; i++) {
                    System.out.println(colNames[i]);
                }
                //thymleafInitUrl = jtf2.getText();
                String foreignColStrs[] = null;

                try{
                if(jta2.getText()!=null && jta2.getText().length()>0) {
                    foreignColStr = jta2.getText();
                    foreignColStrs = foreignColStr.split(",");

                    for(int i=0; i< foreignColStrs.length ;i++ ){
                        foreignColStrs[i] = foreignColStrs[i].trim();
                    }
                }
                }catch(Exception e1){
                    e1.printStackTrace();
                    }

                thymleafInitUrl = jtf2.getText();
                usv = new UtilStaticV5(domainStr, colStrs, colLongs, colDates,colNames, foreignColStrs, thymleafInitUrl, rootPackageStr);


                /**
                 *  Back-end, SpringBoot JPA, QueryDSL
                 *
                 *  Make Entity, ApiDto, Repository, RepositoryCustom, RepositoryImpl, Service.
                 * **/

                //folder make. c:/category/[domainStr]-[MMdd-HHmmss]/
                LocalDateTime now = LocalDateTime.now();
                String dateStr = now.format(DateTimeFormatter.ofPattern("MMdd-HHmmss"));
                String folderStr = "c:\\category\\"+usv.toLowerFirst(domainStr)+"-"+dateStr;

                //Make folder Back-end. c:/category/print-[MMdd-HHmmss]/[domainStr]/
                String folderStrBackend = folderStr +"\\"+usv.toAllLowerCase(domainStr)+"\\";

                String entityStr = usv.makeEntity();
                new ResultScreen("Entity", entityStr, folderStrBackend, domainStr, usv);

                String apiDtoStr = usv.makeApiDto();
                new ResultScreen("ApiDto", apiDtoStr, folderStrBackend, domainStr, usv);

                String repositoryStr = usv.makeRepository();
                new ResultScreen("Repository", repositoryStr, folderStrBackend, domainStr, usv);

                String repositoryCustomStr = usv.makeRepositoryCustom();
                new ResultScreen("RepositoryCustom", repositoryCustomStr, folderStrBackend, domainStr, usv);

                String repositoryImplStr = usv.makeRepositoryImpl();
                new ResultScreen("RepositoryImpl", repositoryImplStr, folderStrBackend, domainStr, usv);

                String serviceStr = usv.makeService();
                new ResultScreen("Service", serviceStr, folderStrBackend, domainStr, usv);

                //SearchCondition
                String searchConditionStr = usv.makeSearchCondition();
                new ResultScreen("SearchCondition", searchConditionStr, folderStrBackend, domainStr, usv);
                //Make page,
                /**
                 * Front-end, Thyemleaf Files.   4 Files.
                 *
                 *  Folder
                 *  templates/firstInstance/
                 *                         index.html                 RootIndexResultScreen(usv)
                 *
                 *  templates/firstInstance/[domainStr]/
                 *                                      index.html,   IndexHTMLResultScreen(usv)
                 *                                      insert.html,  InsertHTMLResultScreen(usv)
                 *                                      update.html.  UpdateHTMLResultScreen(usv)
                 * */

                //Make folder Front-end. c:/category/[domainStr]-[MMdd-HHmmss]/firstInstance/[domainStr]/

                 String folderStrFrontend = folderStr +"\\templates\\firstinstance\\";
                 //String folderStrFrontend = folderStr +"\\templates\\firstinstance\\"+usv.toAllLowerCase(domainStr)+"\\";

                 String rootIndexStr = usv.makeRootIndex();
                 new ResultScreen("RootIndex", rootIndexStr, folderStrFrontend, domainStr, usv);

                 String indexStr = usv.makeIndex();
                 folderStrFrontend = folderStrFrontend + "\\"+usv.toAllLowerCase(domainStr)+"\\";
                 new ResultScreen("Index", indexStr, folderStrFrontend, domainStr, usv);

                 String insertStr = usv.makeInsert();
                 new ResultScreen("Insert", insertStr, folderStrFrontend, domainStr, usv);

                 String updateStr = usv.makeUpdate();
                 new ResultScreen("Update", updateStr, folderStrFrontend, domainStr, usv);


                 /**
                  * URL Controller and Dto, ApiDtoForm. Files.  3 Files.
                  *
                  *
                  *
                  *  ROOT URL controller.
                  *  src.main.java.org.example.firstinstanceurl.domain
                  *
                  *                                                                InstanceUrlController.java, RootIndexControllerResultScreen(usv)
                  *  Entity CRUD URL controller.
                  *  src.main.java.org.example.firstinstanceurl.domain.[domainStr]
                  *
                  *                                                               InstanceUrl[domainStr]Controller.java, InstanceUrlControllerResultScreen(usv)
                  *  Entity Form Dto.
                  *  src.main.java.org.example.firstinstanceurl.form
                  *                                                               [domainStr]ApiDtoForm.java, ApiDtoFormResultScreen(usv)
                  * **/
                //Make folder Front-end-controller. c:/category/[domainStr]-[MMdd-HHmmss]/firstInstance/[domainStr]/

                String folderStrFrontendCont = folderStr +"\\firstinstance\\controller\\firstinstanceurl\\";
                String folderStrFrontendContDomain = folderStrFrontendCont +"domain\\"+ usv.toAllLowerCase(domainStr)+"\\";
                String folderStrFrontendContForm = folderStrFrontendCont +"form\\";


                String rootIndexControllerStr = usv.makeRootIndexController();
                new ResultScreen("RootIndexController", rootIndexControllerStr, folderStrFrontendCont, domainStr, usv);
                 //new RootIndexControllerResultScreen(usv);
                String indexControllerStr = usv.makeIndexController();
                new ResultScreen("IndexController", indexControllerStr, folderStrFrontendContDomain, domainStr, usv);
                 //new InstanceUrlControllerResultScreen(usv);
                String apiDtoFormStr = usv.makeApiDtoForm();
                new ResultScreen("ApiDtoForm", apiDtoFormStr, folderStrFrontendContForm, domainStr, usv);
                 //new ApiDtoFormResultScreen(usv);


         //        usc = new UtilStrConvV4(tableName,valBefore, "sqlUpper");
          //       new UpperCaseSQLResultScreen(usc);
                 //usc = new UtilStrConvV4(tableName,valBefore, "Camel");
                //new ExtractVResultScreen(usc);

            }
        });
    }


    public static void main(String[] args) throws Exception {
        MyFlatLaf.setup();
        new Sys05();
    }
}
