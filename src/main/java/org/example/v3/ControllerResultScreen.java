package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ControllerResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance

    ControllerResultScreen(UtilStrConvV3 usc){
        className = usc.getTableName();
        jp= new JPanel();
        jl = new JLabel("@Controller: " + className+"Controller");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Controller: " + className+"Controller");
        setBounds(300,300,650,500);

        jta.setText("" +
                "");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "Controller.java");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(code);
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
