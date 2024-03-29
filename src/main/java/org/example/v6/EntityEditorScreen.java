package org.example.v6;

import org.example.v5.UtilStaticV5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntityEditorScreen extends JFrame {

    private JPanel jp,jpWest, jpEast, jpSouth, jpNorth, jpCenter;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,
            jl10,jl11,jl12,jl13,jl14;

    //jpEast -> jpGridLayout(rows * cols): 1 x entityColumnCount
    private JPanel jpGridLayoutEntity;

    private final JPanel jpFlowLayoutBtnCenter;

    private JTextField nowEntityTextField, jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8,jtf9;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton longBtn, stringBtn, dateBtn, simpleViewBtn, saveBtn, saveFlatBtn, loadBtn,btn8,btn9,btn10,btn11,btn12,btn13,btn14;
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

    String nowEntityTextFieldStr;

    static UtilStaticV6 usv; // Save input values

    public EntityEditorScreen() {

        jp = new JPanel();
        jpEast = new JPanel();
        jpWest = new JPanel();
        jpSouth = new JPanel();
        jpNorth = new JPanel();
        jpCenter = new JPanel();

        jp.setLayout(new BorderLayout()); // BorderLayout. add East, West, North, South, Center ex )add([comp],BorderLayout.EAST)

        jpWest.setLayout(new GridLayout(1,1)); // GridLayout. add rows, cols ex )add([comp],row,col)


        //jpGridLayoutEntity.setLayout(new GridLayout(entityColumnsCount,1,0,0));

        jta = new JTextArea(5,5);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(400,400));
        jta.setText("");
        jpWest.add(jsp);

        jta2 = new JTextArea();
        jsp2 = new JScrollPane(jta2);
        jsp2.setPreferredSize(new Dimension(200,400));
        jta2.setText("");
        jpEast.add(jsp2);


        longBtn = new JButton("Long");
        stringBtn = new JButton("String");
        dateBtn = new JButton("LocalDateTime");

        simpleViewBtn = new JButton("Simple View");
        jl = new JLabel("Now Entity:");
        nowEntityTextField = new JTextField(10);

        saveBtn = new JButton("Save");
        saveFlatBtn = new JButton("Save Flat");
        loadBtn = new JButton("Load");

        jpFlowLayoutBtnCenter = new JPanel();
        jpFlowLayoutBtnCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        //jpGridLayoutBtnCenter.add(Box.createHorizontalGlue());
        jpFlowLayoutBtnCenter.setSize(50,300);
        jpFlowLayoutBtnCenter.add(simpleViewBtn);
        jpFlowLayoutBtnCenter.add(Box.createRigidArea(new Dimension(0,20))); // add space  ref, https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html

        jpFlowLayoutBtnCenter.add(Box.createRigidArea(new Dimension(0,10)));
        jpFlowLayoutBtnCenter.add(saveBtn);
        jpFlowLayoutBtnCenter.add(Box.createRigidArea(new Dimension(0,10)));
        jpFlowLayoutBtnCenter.add(saveFlatBtn);
        jpFlowLayoutBtnCenter.add(Box.createRigidArea(new Dimension(0,10)));
        jpFlowLayoutBtnCenter.add(loadBtn);
        jpFlowLayoutBtnCenter.add(Box.createRigidArea(new Dimension(0,10)));
        jpFlowLayoutBtnCenter.add(jl);
        jpFlowLayoutBtnCenter.add(nowEntityTextField);

        jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));

        jpCenter.add(jpFlowLayoutBtnCenter);

        jpFlowLayoutBtns = new JPanel();
        jpFlowLayoutBtns.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpFlowLayoutBtns.add(longBtn);
        jpFlowLayoutBtns.add(stringBtn);
        jpFlowLayoutBtns.add(dateBtn);
        jpSouth.setLayout(new GridLayout(1,2));
        jpSouth.add(jpFlowLayoutBtns);


        setVisible(true);
        setResizable(true);
    jpCenter.setSize(50,300);
        jp.add(jpEast,BorderLayout.EAST);
        jp.add(jpWest,BorderLayout.WEST);
        jp.add(jpCenter,BorderLayout.CENTER);
        jp.add(jpSouth,BorderLayout.SOUTH);
        jp.add(jpNorth,BorderLayout.NORTH);

        add(jp);

        setBounds(500, 270, 765, 500);
        setTitle("Entity Editor");

        longBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("Long long\n");
                saveColumns();
            }});

        stringBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("String string\n");
                saveColumns();

            }});

        dateBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append("LocalDateTime date\n");
                saveColumns();

            }});

        simpleViewBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] foreignColStrs = null;
                String projectPath = "";
                saveColumns();
                nowEntityTextFieldStr = nowEntityTextField.getText();
                usv = new UtilStaticV6(domainStr, colStrs, colLongs, colDates,colNames, foreignColStrs, thymleafInitUrl, rootPackageStr, projectPath, nowEntityTextFieldStr);


                new EntityScreen(usv);

            }});

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jta.getText());
                String folderStr = "C:\\category\\entity";
                System.out.println(nowEntityTextField.getText());
                String textStr = jta.getText();
                textStr = "Long id;\n"+textStr+"String isDel;\nLocalDateTime modifiedDate;\nLocalDateTime createdDate;\n";

                if(nowEntityTextField.getText().equals("") || nowEntityTextField.getText().equals(null)){
                    JOptionPane.showMessageDialog(null, "Entity Name을 입력해주세요. Now Entity:");
                    LocalDateTime now = LocalDateTime.now();
                    nowEntityTextField.setText("entity " +now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss") ));
                    return;
                }
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
                File file = new File("C:\\category\\entity\\"+nowEntityTextField.getText()+".txt");
                if(file!=null){
                    try {
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter writer = new BufferedWriter(fw);
                        writer.write(textStr);
                        writer.close();
                        System.out.println("파일이 생성되었습니다.");
                    }catch(Exception e2){
                        e2.getStackTrace();
                    }
                }
            }
        });

        saveFlatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jta.getText());
                String folderStr = "C:\\category\\entity";
                System.out.println(nowEntityTextField.getText());
                if(nowEntityTextField.getText().equals("") || nowEntityTextField.getText().equals(null)){
                    JOptionPane.showMessageDialog(null, "Entity Name을 입력해주세요. Now Entity:");
                    LocalDateTime now = LocalDateTime.now();
                    nowEntityTextField.setText("entity " +now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss") ));
                    return;
                }
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
                File file = new File("C:\\category\\entity\\"+nowEntityTextField.getText()+".txt");
                if(file!=null){
                    try {
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter writer = new BufferedWriter(fw);
                        writer.write(jta.getText());
                        writer.close();
                        System.out.println("파일이 생성되었습니다.");
                    }catch(Exception e2){
                        e2.getStackTrace();
                    }
                }
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nowEntityTextField.getText().equals("") || nowEntityTextField.getText().equals(null)) {
                    JOptionPane.showMessageDialog(null, "Entity Name을 입력해주세요. Now Entity:");
                    LocalDateTime now = LocalDateTime.now();

                    return;
                } else {
                    File file = new File("C:\\category\\entity\\" + nowEntityTextField.getText() + ".txt");
                    if (file != null) {
                        try {
                            FileReader fr = new FileReader(file);
                            BufferedReader reader = new BufferedReader(fr);
                            String line;
                            String textStr = "";
                            while ((line = reader.readLine()) != null) {
                                textStr = textStr + line + "\n";
                            }
                            jta.setText(textStr);
                            reader.close();
                            System.out.println("파일이 로드되었습니다.");
                        } catch (Exception e2) {
                            e2.getStackTrace();
                        }
                    }
                }
            }
        });
    }


    private void entityTextCalc(){
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
        try {
            if (columnLongs != null || columnLongs != "" || !(columnLongs.equals(""))) {
                System.out.println(columnLongs + "  columnLongs");
                colLongs = columnLongs.split(",");
            }

            if (columnStrings != null || !columnStrings.equals("")) {
                columnStrings = columnStrings.substring(0, columnStrings.length() - 1);
                colStrs = columnStrings.split(",");
            }

            if (columnDates != null || !columnDates.equals("")) {
                columnDates = columnDates.substring(0, columnDates.length() - 1);
                colDates = columnDates.split(",");
            }
            //columnDates = columnDates.substring(0,columnDates.length()-1);

            if (columnNames != null || !columnNames.equals("")) {
                columnNames = columnNames.substring(0, columnNames.length() - 1);
                colNames = columnNames.split(",");
            }
        }catch(Exception e){
            //e.printStackTrace();
        }

        //System.out.println(colStrs.toString());
        //System.out.println(colLongs.toString());
        //System.out.println(colNames.toString());

        //for(int i=0; i< colStrs.length ;i++ ){
        //    System.out.println(colStrs[i]);
        //}
        try {
            for (int i = 0; i < colNames.length; i++) {
                System.out.print(colNames[i] + ",");
            }
        }catch(Exception e){
            //e.printStackTrace();
        }
        System.out.println();
        // usv = new UtilStaticV5(domainStr, colStrs, colLongs, colDates,colNames, foreignColStrs, thymleafInitUrl, rootPackageStr);

        //jta.setText("");
    }


}
