package org.example.v6;

import org.example.themes.MyFlatLaf;
import org.example.v5.UtilStaticV5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sys06 extends JFrame {

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
            jl10,jl11,jl12,jl13,jl14;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn,btn2;


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
        btn = new JButton("Entity");

        jp.add(btn);

        setVisible(true);
        setResizable(true);
        add(jp);
        setBounds(200,200,500,500);
        setTitle("v6 ERD to SpringBoot");

        btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new Entity06();
            }
        });
    }

    public static void main(String[] args) {
        MyFlatLaf.setup();
        new Sys06();
    }
}
