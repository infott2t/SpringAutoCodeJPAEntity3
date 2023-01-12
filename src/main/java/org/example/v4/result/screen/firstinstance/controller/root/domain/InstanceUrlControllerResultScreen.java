package org.example.v4.result.screen.firstinstance.controller.root.domain;

import org.example.v4.UtilStaticV4;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InstanceUrlControllerResultScreen extends JFrame {
    public String domainStr; // entity name, small letter.
    public String[] colStrs;  // entity column, String array.
    public String[] colLongs; // entity column, Long array.
    public String[] colDates; // entity column, LocalDateTime array.
    public String[] colNames; // entity column, In orders.
    public String[] uppColNames; // entity column, In orders. FirstLetter is UpperCase.

    public String thymleafInitUrl;  // /administer/instanceurl


    //After making variable.
    public String uppDomainStr; //First letter Upper case.
    public String thymleafInitUrlDomainStr; // /administer/instanceurl/[domainStr]

    public String thymleafViewPage; // firstinstance/[domainStr]

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public InstanceUrlControllerResultScreen(UtilStaticV4 usv){
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.colNames = usv.colNames;
        this.thymleafInitUrl = usv.thymleafInitUrl;
        this.thymleafInitUrlDomainStr = thymleafInitUrl + "/" + domainStr;

        this.thymleafViewPage = "firstinstance/" + domainStr;
        this.uppDomainStr = usv.upperCaseFirstLetter(domainStr);

        this.uppColNames = new String[colNames.length];
        for(int i=0; i<colNames.length; i++){
            this.uppColNames[i] = usv.upperCaseFirstLetter(colNames[i]);
        }

        jp = new JPanel();

        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);

        jp.add(jsp);

        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("File: InstanceUrl"+uppDomainStr+"Controller.java | /firstinstance/controller/firstinstanceurl/domain/"+domainStr+"/InstanceUrl"+uppDomainStr+"Controller.java");
        setBounds(300,300,650,500);

        jta.setText("" +
                "@RequiredArgsConstructor\n" +
                "@Controller\n" +
                "public class InstanceUrl"+uppDomainStr+"Controller {\n" +
                "\n" +
                "    private final "+uppDomainStr+"Service "+domainStr+"Service;\n" +
                " \n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrlDomainStr+"\")\n" +
                "    public String index(Model model, "+uppDomainStr+"SearchCondition condition,\n" +
                "                        @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                        @PageableDefault(size= 10)Pageable pageable) throws Exception {\n" +
                "\n" +
                "        Page<"+uppDomainStr+"ApiDto> boards = "+domainStr+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        return \""+thymleafViewPage+"/index\";\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrlDomainStr+"/insert\")\n" +
                "    public String insert(Model model, "+uppDomainStr+"SearchCondition condition,\n" +
                "                         @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                         @PageableDefault(size= 10)Pageable pageable) throws Exception{\n" +
                "\n" +
                "        Page<"+uppDomainStr+"ApiDto> boards = "+domainStr+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        "+uppDomainStr+"ApiDtoForm userForm = new "+uppDomainStr+"ApiDtoForm();\n" +
                "        model.addAttribute(\"userForm\",userForm);\n" +
                "\n" +
                "        return \""+thymleafViewPage+"/insert\";\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\""+thymleafInitUrlDomainStr+"/insert_2\")\n" +
                "    public String insert_2(Model model, "+uppDomainStr+"ApiDtoForm userForm){\n" +
                "\n" +
                "        "+uppDomainStr+" "+domainStr+" = null;\n" +
                "\n" +
                "\n" +
                "        try {\n" +
                "            "+domainStr+" = new "+uppDomainStr+"();\n" +
                "            /**\n" +
                "             * Setter use ....\n" +
                "             * */\n"
                +setterString()+
                "            "+domainStr+".setCreatedDate(LocalDateTime.now());\n" +
                "            "+domainStr+".setIsDel(\"N\");\n" +
                "\n" +
                "            "+domainStr+"Service.save("+domainStr+");\n" +
                "\n" +
                "        } catch (Exception e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }\n" +
                "        return \"redirect:"+thymleafInitUrlDomainStr+"/insert\";}\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrlDomainStr+"/delete\")\n" +
                "    public String delete(@RequestParam(value=\"id\")Long id, Model model) {\n" +
                "\n" +
                "        "+uppDomainStr+" user = null;\n" +
                "        try {\n" +
                "             user = "+domainStr+"Service.findById(id);\n" +
                "        } catch (Exception e) {\n" +
                "            return \"redirect:"+thymleafInitUrlDomainStr+"/\";\n" +
                "        }\n" +
                "\n" +
                "        user.setIsDel(\"Y\");\n" +
                "        "+domainStr+"Service.save(user);\n" +
                "\n" +
                "\n" +
                "        return \"redirect:"+thymleafInitUrlDomainStr+"/\";\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrlDomainStr+"/update\")\n" +
                "    public String update(Model model, @RequestParam(value=\"id\")Long id, "+uppDomainStr+"SearchCondition condition,\n" +
                "                         @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                         @PageableDefault(size= 10)Pageable pageable) throws Exception{\n" +
                "        \n" +
                "        Page<"+uppDomainStr+"ApiDto> boards = "+domainStr+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        "+uppDomainStr+"ApiDtoForm userForm = new "+uppDomainStr+"ApiDtoForm();\n" +
                "        "+uppDomainStr+" "+domainStr+" = null;\n" +
                "\n" +
                "        try {\n" +
                "            "+domainStr+" = "+domainStr+"Service.findById(id);\n" +
                "        }catch(Exception e){\n" +
                "            return \"redirect:"+thymleafInitUrlDomainStr+"/insert\";\n" +
                "        }\n" +
                "        /**\n" +
                "         * Setter Method use ....\n" +
                "         * */\n" +
                "        \n" + setterString2() +
                //"        userForm.setId("+domainStr+".getId());\n" +
                "        \n" +
                "        userForm.setCreatedDate("+domainStr+".getCreatedDate());\n" +
                "        userForm.setModifiedDate("+domainStr+".getModifiedDate());\n" +
                "\n" +
                "        model.addAttribute(\"userForm\",userForm);\n" +
                "        return \""+thymleafViewPage+"/update\";\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\""+thymleafInitUrlDomainStr+"/update_2\")\n" +
                "    public String update_2(Model model, @RequestParam(value=\"id\")Long id,"+uppDomainStr+"ApiDtoForm userForm, "+uppDomainStr+"SearchCondition condition,\n" +
                "                           @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                           Pageable pageable) throws Exception {\n" +
                "\n" +
                "\n" +
                "        "+uppDomainStr+" "+domainStr+" = null;\n" +
                "        \n" +
                "        try{\n" +
                "            "+domainStr+" = "+domainStr+"Service.findById(id);\n" +
                "        }catch(Exception e){\n" +
                "            return \"redirect:"+thymleafInitUrlDomainStr+"/insert\";\n" +
                "        }\n" +
                "\n" +
                "        /**\n" +
                "         * Setter Method use ....\n" +
                "         * */\n" +
                "\n" +setterString()+
                "        "+domainStr+".setModifiedDate(LocalDateTime.now());\n" +
                "\n" +
                "        "+domainStr+"Service.save("+domainStr+");\n" +
                "        \n" +
                "\n" +
                "        return \"redirect:"+thymleafInitUrlDomainStr+"/insert\";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n");

        String code = jta.getText();

        try{
            String path = "C:\\category\\firstinstance\\controller\\firstinstanceurl\\domain\\"+domainStr+"\\";

            File folder = new File(path);

            if(!folder.exists()) {
                try {
                    Files.createDirectories(Paths.get(path));
                    System.out.println("폴더가 생성되었습니다.");
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
            }
            System.out.println("path, " + path);
            System.out.println(path + "\\InstanceUrl"+uppDomainStr+"Controller.java");
            File file = new File(path + "\\InstanceUrl"+uppDomainStr+"Controller.java");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(code);
            writer.close();
        }catch(Exception e ){
            e.printStackTrace();
        }

    }

    private String setterString2() {
        // "        userForm.setId("+domainStr+".getId());\n"
        String setterString = "";
        for (int i = 0; i <colNames.length  ; i++) {
            setterString += "        userForm.set"+uppColNames[i]+"("+domainStr+".get"+uppColNames[i]+"());\n";
        }
        return setterString;
    }

    private String setterString() {
        //    "            "+domainStr+".setColumns(userForm.);\n"
        String result="";
        for(int i=1; i<colNames.length;i++){

            result += "        "+domainStr+".set"+uppColNames[i]+"(userForm.get"+uppColNames[i]+"());\n";
        }
        return result;
    }
}
