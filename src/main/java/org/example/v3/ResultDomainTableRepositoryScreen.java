package org.example.v3;

import javax.swing.*;

public class ResultDomainTableRepositoryScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    ResultDomainTableRepositoryScreen(UtilStrConvV3 usc){
        String className = usc.getTableName();
        jp= new JPanel();
        jl = new JLabel("@Entity class: " + className);
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Domain Repository.");
        setBounds(300,300,650,500);

        jta.setText("" +
                "");


    }
}
