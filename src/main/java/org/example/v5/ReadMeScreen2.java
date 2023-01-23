package org.example.v5;

import javax.swing.*;

public class ReadMeScreen2 extends JFrame {
    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public ReadMeScreen2(){
        jp = new JPanel();

        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);

        jp.add(jsp);

        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("ReadMe");
        setBounds(300,300,650,500);

        jta.setText("" +
                "Read Me file for this project.\n" +
                "This file is generated by the program.\n\n" +
                "* This program, Entity use Long, String, LocalDateTime.\n" +
                "* 이 프로그램에서, 엔티티는 Long형, String형, LocalDateTime형을 사용할 수 있습니다.(Korean language)\n\n"+
                "* Entity, isDel, reateDate and modifiedDate are need writing.\n" +
                "* 엔티티의 isDel, createdDate와 modifiedDate는 적어줘야합니다.\n"+
                "\n"+
                "Long id;\n" +
                "// Writing ..... Columns~.\n" +
                "// .........\n"+
                        "String isDel;\n" +
                        "LocalDateTime modifiedDate\n" +
                        "LocalDateTime createdDate;\n\n"+
                "* Entity Long id, is not changed. Fisrt writing on. Cause, Html code, in using 'id'.\n" +
                "* 엔티티의 Long id는 변경할 수 없고, 맨위에 적어주세요. Html 코드에서 'id'로 사용하고 있습니다.(Korean language)\n"+
                "\n"+
                "* Generate file, need package. or, root package writing. ex) org.example.projectname\n"+
                "* 생성된 파일은 package가 필요합니다. 또는, root package를 적어주세요. ex) org.example.projectname \n\n"+
                "* Generate file, Impl, need QClass import. ex) import static org.example...domain.entity.QEntity.entity;\n"+
                "* 생성된 파일, Impl파일은 QClass import가 필요합니다. ex) import static org.example...domain.entity.QEntity.entity;\n\n"+
                "* Root index.html file, need after editing. Entitys table links for CRUD. ex) <p><a th:href=@{[url]}>Table [domain name] move.</a></p>\n"+
                "* 맨처음, index.html파일은 편집이 필요합니다. 엔티티들의 이동 링크를 적어주세요. 예) <p><a th:href=@{[url]}>테이블 [domain name] 로 이동.</a></p>\n"+
                "\n"+
                "* package org.example.v5.Sys05.java file, there I writing generating files, and use way.\n"+
                "* org.example.v5.Sys05.java 파일에, 주석으로 생성된 파일들을 사용하는 방법을 적어놨습니다.\n"+
                "\n"+
                "The rest of the fields are generated by the user.\n" +
                "\n"+
                "The program generates the following files:\n" +
                " c:/category/ ... Making file.\n"+
                "\n"+
                "I hope good and easy making programming code.\n" +
                "Thank you.\n" +
                "And How about this. Church. Jesus Christ. Calling. Let's go there.\nI like this writing.\n"+
                "Social resting place. Thank you.\n"+
                "\n"+
                "Github, https://github.com/infott2t/SpringAutoCodeJPAEntity3 , This program.\n and, \n"+
                "Model, reference, \n"+
                "https://github.com/infott2t/springboot-querydsl-ex\n"+
                " (WebPrograming SpringBoot + QueryDSL)" );



    }


}
