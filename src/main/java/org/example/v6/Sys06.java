package org.example.v6;

import org.example.themes.MyFlatLaf;
import org.example.v5.UtilStaticV5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sys06 extends JFrame {

    private JPanel jp,jpWest, jpEast, jpSouth, jpNorth, jpCenter;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
            jl10,jl11,jl12,jl13,jl14;

    //jpEast -> jpGridLayout(rows * cols): 1 x entityColumnCount
    private JPanel jpGridLayoutEntity;


    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton entityBtn, readMeBtn,btn3;
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

    Sys06() {
        jp = new JPanel();
        jpEast = new JPanel();
        jpWest = new JPanel();
        jpSouth = new JPanel();
        jpNorth = new JPanel();
        jpCenter = new JPanel();

        entityBtn = new JButton("Entity");
        readMeBtn = new JButton("ReadMe");
        jtf = new JTextField(28);
        jtf.setText("https://github.com/infott2t/SpringAutoCodeJPAEntity3");


        jp.setLayout(new BorderLayout()); // BorderLayout. add East, West, North, South, Center ex )add([comp],BorderLayout.EAST)

        jpNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpNorth.add(entityBtn);


        jpSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jpSouth.add(readMeBtn);
        jpSouth.add(jtf);


        jp.add(jpNorth,BorderLayout.NORTH);
        jp.add(jpSouth,BorderLayout.SOUTH);

        setVisible(true);
        setResizable(true);
        add(jp);
        setBounds(200,200,500,500);
        setTitle("v6 ERD to SpringBoot");

        entityBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new EntityEditorScreen();
            }
        });

        readMeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadMeScreen();
            }
        });
    }

    public static void main(String[] args) {
        //MyFlatLaf.setup();
        new Sys06();
    }
}
