package org.example.v4;

import org.example.v4.result.screen.IndexHTMLResultScreen;
import org.example.v4.result.screen.InsertHTMLResultScreen;
import org.example.v4.result.screen.ReadMeScreen;
import org.example.v4.result.screen.UpdateHTMLResultScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;

public class Sys04 extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
    jl10,jl11,jl12,jl13,jl14;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn,btn2;

    static UtilStaticV4 usv; // Save input values


    String columnStrings;
    String columnLongs;
    String columnDates;

    String columnNames;
    String domainStr;

    String[] colStrs;
    String[] colLongs;
    String[] colDates;

    String[] colNames;

    String thymleafInitUrl;

    Sys04(){
        jp= new JPanel();
        jl = new JLabel("Entity name(small letter):");
        jl2 = new JLabel(": ");
        jtf = new JTextField(20);
        jl3 = new JLabel("Entity Copy paste. ex) Long id; String nameStr; LocalDateTime dateStr; ");
        jta = new JTextArea(5,20);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(480,200));
        jtf2 = new JTextField(33);
        jl4 = new JLabel("Starting point Entity CRUD url. Not writing entity name. ex) /administer/instanceurl");
        jtf3 = new JTextField(20);
        jtf4 = new JTextField();

        btn2 = new JButton("ReadMe");
        btn = new JButton("Extract Redundant logic...");

        jp.add(jl);
        jp.add(jl2);
        jp.add(jtf);
        jp.add(jl3);
        jp.add(jsp);
        jp.add(jl4);
        jp.add(jtf2);

        jp.add(btn2);
        jp.add(btn);
        jp.add(jtf4);
        setVisible(true);
        setResizable(true);
        add(jp);
        setBounds(300,300,500,500);
        setTitle("v4 SpringBoot. First instance, support backend CRUD");
        jtf2.setText("/administer/instanceurl");
        jtf4.setText("Github, https://github.com/infott2t/SpringAutoCodeJPAEntity3");

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadMeScreen();
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                domainStr = jtf.getText();

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

                thymleafInitUrl = jtf2.getText();


                usv = new UtilStaticV4(domainStr, colStrs, colLongs, colDates,colNames, thymleafInitUrl);

                //Make page,
                /**
                 * front-end, thyemleaf
                 *
                 *  Folder
                 *  templates/firstinstance/[domainStr]/
                 *                                      index.html,
                 *                                      insert.html,
                 *                                      update.html.
                 * */

                 new IndexHTMLResultScreen(usv);
                 new InsertHTMLResultScreen(usv);
                 new UpdateHTMLResultScreen(usv);



         //        usc = new UtilStrConvV4(tableName,valBefore, "sqlUpper");
          //       new UpperCaseSQLResultScreen(usc);
                 //usc = new UtilStrConvV4(tableName,valBefore, "Camel");
                //new ExtractVResultScreen(usc);

            }
        });
    }


    public static void main(String[] args){

        new Sys04();
    }
}
