package org.example.v6;

import org.example.v5.UtilStaticV5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;

public class Entity06 extends JFrame {
    private JPanel jp,jpWest, jpEast, jpSouth, jpNorth, jpCenter;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
            jl10,jl11,jl12,jl13,jl14;

    //jpEast -> jpGridLayout(rows * cols): 1 x entityColumnCount
    private JPanel jpGridLayoutEntity;


    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn,btn2,btn3;
    private JPanel jpFlowLayoutBtns;
    //Entity Fields
    int entityColumnsCount;

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

    static UtilStaticV5 usv; // Save input values

    public Entity06() {

        jp = new JPanel();
        jpEast = new JPanel();
        jpWest = new JPanel();
        jpSouth = new JPanel();
        jpNorth = new JPanel();
        jpCenter = new JPanel();

        jp.setLayout(new BorderLayout()); // BorderLayout. add East, West, North, South, Center ex )add([comp],BorderLayout.EAST)

        jpWest.setLayout(new GridLayout(2,1)); // GridLayout. add rows, cols ex )add([comp],row,col)


        //jpGridLayoutEntity.setLayout(new GridLayout(entityColumnsCount,1,0,0));

        jta = new JTextArea(5,5);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(200,200));
        jta.setText("");
        jpWest.add(jsp);

        btn = new JButton("Long");
        btn2 = new JButton("String");
        btn3 = new JButton("LocalDateTime");
        jpFlowLayoutBtns = new JPanel();
        jpFlowLayoutBtns.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpFlowLayoutBtns.add(btn);
        jpFlowLayoutBtns.add(btn2);
        jpFlowLayoutBtns.add(btn3);
        jpSouth.setLayout(new GridLayout(1,2));
        jpSouth.add(jpFlowLayoutBtns);


        setVisible(true);
        setResizable(true);

        jp.add(jpEast,BorderLayout.EAST);
        jp.add(jpWest,BorderLayout.WEST);
        jp.add(jpSouth,BorderLayout.SOUTH);
        jp.add(jpNorth,BorderLayout.NORTH);

        add(jp);

        setBounds(300, 270, 500, 500);
        setTitle("Entity");

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("Long long\n");
                saveColumns();
            }});

        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("String string\n");
                saveColumns();

            }});

        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("LocalDateTime date\n");
                saveColumns();

            }});
    }

    private void saveColumns() {
        String line;

        BufferedReader reader = new BufferedReader(new StringReader(jta.getText()));
        columnStrings="";
        columnLongs="";
        columnDates="";
        columnNames="";
        try{
            while((line = reader.readLine())!=null){
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
        }catch(Exception e1){
            e1.printStackTrace();
        }
        if(columnLongs!=null && columnLongs.length()>0) {
            columnLongs = columnLongs.substring(0, columnLongs.length() - 1);
            colLongs = columnLongs.split(",");
        }

        if(columnStrings!=null && columnStrings.length()>0) {
            columnStrings = columnStrings.substring(0, columnStrings.length() - 1);
            colStrs = columnStrings.split(",");
        }

        if(columnDates!=null && columnDates.length()>0) {
            columnDates = columnDates.substring(0, columnDates.length() - 1);
            colDates = columnDates.split(",");
        }
        //columnDates = columnDates.substring(0,columnDates.length()-1);

        columnNames = columnNames.substring(0,columnNames.length()-1);
        colNames = columnNames.split(",");

        //System.out.println(colStrs.toString());
        //System.out.println(colLongs.toString());
        //System.out.println(colNames.toString());

        //for(int i=0; i< colStrs.length ;i++ ){
        //    System.out.println(colStrs[i]);
        //}
        for (int i = 0; i < colNames.length; i++) {
            System.out.print(colNames[i] + ",");
        }
        System.out.println();
        // usv = new UtilStaticV5(domainStr, colStrs, colLongs, colDates,colNames, foreignColStrs, thymleafInitUrl, rootPackageStr);

        //jta.setText("");
    }


}
