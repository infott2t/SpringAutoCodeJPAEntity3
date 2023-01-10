package org.example.v3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;

public class Sys03 extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
    jl10,jl11,jl12,jl13,jl14;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    static UtilStrConvV3 usc;


    String columnStrings;
    String columnLongs;
    String columnDates;
    String tableName;

    String[] colStrs;
    String[] colLongs;
    String[] colDates;


    Sys03(){
        jp= new JPanel();
        jl = new JLabel("테이블 칼럼 명 입력하기. 테이블 클래스 이름, 칼럼명을 적어주세요.");
        jl2 = new JLabel("테이블 명(첫글자 대문자): ");
        jtf = new JTextField(20);
        jl3 = new JLabel("Entity class를 추출합니다. 적어주세요. Copy & Paste.");
        jta = new JTextArea(5,20);
        jsp = new JScrollPane(jta);
        jtf2 = new JTextField(32);
        jsp.setPreferredSize(new Dimension(300,200));




        btn = new JButton("엔티티 코드 생성하기");

        jp.add(jl);
        jp.add(jl2);
        jp.add(jtf);
        jp.add(jl3);
        jp.add(jsp);


        jp.add(btn);
        jp.add(jtf2);
        jtf2.setText("Github, https://github.com/infott2t/SpringAutoCodeJPAEntity2");

        setVisible(true);
        setResizable(true);
        add(jp);
        setBounds(300,300,380,700);
        setTitle("v3 Spring JPA + QueryDSL");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              String line;
            BufferedReader reader = new BufferedReader(new StringReader(jta.getText()));
            columnStrings="";
            columnLongs="";
            columnDates="";
            tableName="";
                try{
                while((line = reader.readLine())!=null){
                    System.out.println(line);
                        if(line.indexOf("Long")>=0) {
                            // System.out.println(line.indexOf("Long") );
                            String longStr = line.substring(line.indexOf("Long") + 5);
                            longStr = longStr.replace(";", "");
                            columnLongs = columnLongs + longStr + ",";


                        }
                    if(line.indexOf("String")>=0) {
                        // System.out.println(line.indexOf("Long") );
                        String strings = line.substring(line.indexOf("String") + 7);
                        strings = strings.replace(";", "");
                        columnStrings = columnStrings + strings + ",";


                    }

                    if(line.indexOf("LocalDateTime")>=0){
                        String dates = line.substring(line.indexOf("LocalDateTime") + 14);
                        dates = dates.replace(";", "");
                        columnDates = columnDates + dates + ",";



                    }

                }

              }catch(Exception ex){
                  ex.printStackTrace();
              }
                columnLongs = columnLongs.substring(0, columnLongs.length()-1);
               // columnLongs = columnLongs.substring(1);
                columnStrings = columnStrings.substring(0, columnStrings.length()-1);
                //columnStrings = columnStrings.substring(1);
                columnDates = columnDates.substring(0, columnDates.length()-1);
               // columnDates = columnDates.substring(1);
                //System.out.println(columnLongs);
                //System.out.println(columnStrings);
                //System.out.println(columnDates);

                colStrs = columnStrings.split(",");
                colLongs = columnLongs.split(",");
                colDates = columnDates.split(",");

                System.out.println(colStrs.toString());
                for(int i=0; i< colStrs.length ;i++ ){
                    System.out.println(colStrs[i]);
                }

                tableName = jtf.getText();
                columnStrings = jta.getText();
                columnLongs  = jta.getText();
                columnDates = jta.getText();

                 usc = new UtilStrConvV3(tableName,colStrs, colLongs, colDates);

               //  new DefaultResultScreen(usc);
                 new DtoResultScreen(usc);
               // new ResultEntityScreen(usc);
                new ResultSaveFormScreen(usc);
                // new ResultFormScreen(usc);
                new ResultUpdateFormScreen(usc);
                new RepositoryResultScreen(usc);

                new SearchConditionResultScreen(usc);
                new RepositoryImplResultScreen(usc);
                new ApiDtoResultScreen(usc);
                new RestControllerResultScreen(usc);
                new ServiceResultScreen(usc);
                new HashMapInputScreen(usc);
                new SetMethodResultScreen(usc);

            }
        });
    }


    public static void main(String[] args){

        new Sys03();
    }
}
