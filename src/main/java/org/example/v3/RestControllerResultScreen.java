package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RestControllerResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String methodName; //alliance

    RestControllerResultScreen(UtilStrConvV3 usc){
        className = usc.getTableName();
        methodName = className.substring(0,1).toLowerCase()+className.substring(1);
        jp= new JPanel();
        jl = new JLabel("@RestController: " + className+"RestController");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("RestController: " + className+"RestController");
        setBounds(300,300,650,500);

        jta.setText("" +
                "\n" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import org.json.simple.JSONArray;\n" +
                "import org.json.simple.JSONObject;\n" +
                "\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "\n" +
                "@RestController\n" +
                "@RequiredArgsConstructor\n" +
                "public class "+className+"RestController {\n" +
                "\n" +
                "    private final "+className+"Service "+methodName+"Service;\n" +
                "\n" +
                "    @GetMapping(value=\"/api/v3/"+methodName+"\")\n" +
                "    public List<"+className+"ApiDto> "+methodName+"All(){\n" +
                "\n" +
                "\n" +
                "        List<"+className+"ApiDto> dto  = "+methodName+"Service.searchFindAllDesc();\n" +
                "\n" +
                ".\n" +
                "        JSONArray jsonArr = new JSONArray();\n" +
                "        JSONObject obj = new JSONObject();\n" +
                "        obj.put(\"data\", dto);\n" +
                "        jsonArr.add(obj);\n" +
                "        return jsonArr;\n" +
                "    }\n" +
                "\n" +
                "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "RestController.java");
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
