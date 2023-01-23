package org.example.v6;

import org.example.themes.MyFlatLaf;
import org.example.v5.UtilStaticV5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityScreen extends JFrame {

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

    EntityScreen() {
        jp = new JPanel();
        jpEast = new JPanel();
        jpWest = new JPanel();
        jpSouth = new JPanel();
        jpNorth = new JPanel();
        jpCenter = new JPanel();

        btn = new JButton("Entity");
        jtf = new JTextField(28);
        //jtf.setText("https://github.com/infott2t/SpringAutoCodeJPAEntity3");

        jta = new JTextArea(5,5);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(250,300));
        jta.setText("");

        //jp.setLayout(new BorderLayout()); // BorderLayout. add East, West, North, South, Center ex )add([comp],BorderLayout.EAST)




        jp.add(jsp);
        //jp.add(jpNorth,BorderLayout.NORTH);
        //jp.add(jpSouth,BorderLayout.SOUTH);

        setVisible(true);
        setResizable(true);
        add(jsp);
        setBounds(150,400,250,300);
        setTitle("[Entity]");

        btn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new EntityEditorScreen();
            }
        });
    }

    public static void main(String[] args) {
        MyFlatLaf.setup();
        new EntityScreen();
    }
}
