package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ResultFormScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance

    ResultFormScreen(UtilStrConvV3 usc){
        String className = usc.getClassNameTables();
        className = usc.getTableName();
        jp= new JPanel();
        jl = new JLabel("@Data: " + className+"Form");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Form: " + className+"Form.java");
        setBounds(300,300,650,500);

        //변수 이름 초기화...
        String [] colLong = usc.getColLongs(); //Long형 칼럼. 0번이 primary key.

        String privateColLongPrint="";  // private Long ...
        String colLongPrintComma=""; // Long var1, Long var2, Long var3, ...의 형태.
        String colLongPrintThis="";    // this.custNo = custNo;
        String colLongPrintCommaUpdate = ""; //primary key인 배열0값을 제외한 Long var2, ...의 형태. 업데이트 메소드에 쓰임.
        for(int i=0; i< colLong.length; i++){

            colLongPrintComma = "Long " + colLong[i] + ", \n" + colLongPrintComma;
            colLongPrintThis = "this."+colLong[i]+" = "+colLong[i]+";\n" + colLongPrintThis;
        }
        String privateColLongPrint0 = "private Long id;\n";
        for(int i=1; i< colLong.length; i++){
            privateColLongPrint = "private Long "+colLong[i]+";\n" + privateColLongPrint;
        }
        colLongPrintCommaUpdate = colLongPrintComma;
        colLongPrintComma = "Long "+colLong[0] +", "+ colLongPrintComma;
        colLongPrintThis = "this."+colLong[0]+" = "+colLong[0]+";\n" + colLongPrintThis;

        String [] colStr = usc.getColStrs();   // String형 칼럼.
        String privateColStrPrint="";
        String colStringPrintComma=""; // String var1, String var2, String var3, ...의 형태.
        String colStringPrintThis="";
        for(int i=0; i< colStr.length; i++) {
            privateColStrPrint = "private String "+colStr[i]+";\n" + privateColStrPrint;
            colStringPrintComma = "String " + colStr[i] + ", \n" + colStringPrintComma;
            colStringPrintThis = "this."+colStr[i]+" = "+colStr[i]+";\n" + colStringPrintThis;
        }

        String [] colDate = usc.getColDates(); //Date형 칼럼.
        String privateColDatePrint = "";
        String colDatePrintComma=""; // LocalDateTime var1, LocalDateTime var2, LocalDateTime var3, ...의 형태.
        String colDatePrintThis="";
        for(int i=0; i< colDate.length; i++) {
            privateColDatePrint = "private LocalDateTime "+colDate[i]+";\n" + privateColDatePrint;
            colDatePrintComma = "LocalDateTime " + colDate[i] + ", \n" + colDatePrintComma;
            colDatePrintThis = "this."+colDate[i]+" = "+colDate[i]+";\n" + colDatePrintThis;
        }

        String colPrintComma =  colLongPrintComma+ colStringPrintComma+ colDatePrintComma;
        String colPirntCommaUpdate = colLongPrintCommaUpdate +colStringPrintComma+colDatePrintComma;
        // 마지막 문자, ',' 콤마 제거.
        colPrintComma = colPrintComma.trim().substring(0,colPrintComma.trim().length()-1);
        colPirntCommaUpdate = colPirntCommaUpdate.trim().substring(0,colPirntCommaUpdate.trim().length()-1);

        String privateColPrint =  privateColLongPrint+"\n"+ privateColStrPrint+"\n"+privateColDatePrint+ "\n";
        System.out.println("String, colPrintComma, ... : "+colPrintComma);
        System.out.println("클래스 이름, ... : "+ usc.getClassNameTables());
        System.out.println("소문자 클래스 이름, 생성자 사용, ...: " +usc.getTnSmall());
        System.out.println("변수 이름, private Long id, ...: " + privateColPrint);

        jta.setText("" +
                "" +
                "\n" +
                "import lombok.Data;\n" +
                "import lombok.Getter;\n" +
                "import lombok.Setter;\n" +
                "\n" +
                "import javax.validation.constraints.*;\n" +
                "\n" +
                "/**\n" +
                " * 실무에서는 등록폼 객체와 수정폼 객체를 별도로 만들어 사용한다.\n" +
                " * groups 는 복잡하고 잘 사용하지 않는다.\n" +
                " */\n" +
                "@Data\n" +
                "public class "+className+"Form {\n" +
                "\n" +
                "    @NotNull(groups = {UpdateCheck.class})\n" +
                        "      "+privateColLongPrint0+"\n"+
                        "      "+privateColLongPrint+"\n"+
                        "     "+privateColStrPrint +"\n"+
                        "     "+privateColDatePrint+"\n" +
                "\n/**예시\n" +
                " *     @NotNull(groups = {UpdateCheck.class})\n" +
                " *     private Long id;\n" +
                " *\n" +
                " *     @NotEmpty(groups = {SaveCheck.class, UpdateCheck.class}, message = \"제목은 필수 입력입니다.\")\n" +
                " *     private String title;\n" +
                " *\n" +
                " *     @NotEmpty(groups = {SaveCheck.class, UpdateCheck.class}, message = \"내용은 필수 입력입니다.\")\n" +
                " *     private String content;\n" +
                " *\n" +
                " *     private Long memberId;\n" +
                " * */" +
                "//    @AssertFalse : false 값만 통과 가능\n" +
                "//    @AssertTrue : true 값만 통과 가능\n" +
                "//    @DecimalMax(value=) : 지정된 값 이하의 실수만 통과 가능\n" +
                "//    @DecimalMin(value=) : 지정된 값 이상의 실수만 통과 가능\n" +
                "//    @Digits(integer=,fraction=) : 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 통과 가능\n" +
                "//    @Email\n" +
                "//    @Future : 대상 날짜가 현재보다 미래일 경우만 통과 가능\n" +
                "//    @Past : 대상 날짜가 현재보다 과거일 경우만 통과 가능\n" +
                "//    @Max(value) : 지정된 값보다 아래일 경우만 통과 가능\n" +
                "//    @Min(value) : 지정된 값보다 이상일 경우만 통과 가능\n" +
                "//    @Negative // 음수만 허용\n" +
                "//    @NegativeOrZero // 음수와 0만 허용\n" +
                "//    @NotNull : null 값이 아닐 경우만 통과 가능\n" +
                "//    @NotEmpty : null, \"\" 이 아닌 경우\n" +
                "//    @NotBlank : null, \"\", \" \" 이 아닌 경우\n" +
                "//    @Null : null일 경우만 통과 가능\n" +
                "//    @Pattern(regex=, flag=, message=) : 해당 정규식을 만족할 경우만 통과 가능\n" +
                "//    @Positive // 양수만 허용\n" +
                "//    @PositiveOrZero // 양수와 0만 허용\n" +
                "//    @Size(min=, max=) : 문자열 또는 배열이 지정된 값 사이일 경우 통과 가능\n" +
                "}\n");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "Form.java");
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
