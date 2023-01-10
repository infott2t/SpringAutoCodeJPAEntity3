package org.example.v3;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RepositoryImplResultScreen extends JFrame{

    private JPanel jp;
    private JLabel jl,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
    private JTextField jtf, jtf2,jtf3,jtf4,jtf5;
    private JTextArea jta,jta2,jta3,jta4;
    private JScrollPane jsp,jsp2,jsp3,jsp4;
    private JButton btn;

    public String className; //Alliance
    public String classNameSm; //alliance
    public String classNameSmFirst; //allianceToA...
    public String sqlNewPrint; // alliance.id, alliance.title, ...
    RepositoryImplResultScreen(UtilStrConvV3 usc){
        className = usc.getTableName();
        classNameSm = usc.getTnSmall();
        classNameSmFirst = className.substring(0,1).toLowerCase() + className.substring(1);
        String[] colStr = usc.getColStrs(); String[] colLong = usc.getColLongs(); String[] colDate = usc.getColDates();
        sqlNewPrint = usc.getNewsqlPrint(colLong,colStr,colDate,className,classNameSmFirst);
        jp= new JPanel();
        jl = new JLabel("@Repository: " + className+"RepositoryImpl");
        jta = new JTextArea(20,50);
        jsp = new JScrollPane(jta);
        //btn = new JButton("");
        jp.add(jl);
        jp.add(jsp);
       // jp.add(btn);
        add(jp);
        setVisible(true);
        setResizable(true);
        setTitle("Repository: " + className+"RepositoryImpl.java");
        setBounds(300,300,650,500);

        jta.setText("" +
                "import com.querydsl.core.BooleanBuilder;\n" +
                "import com.querydsl.core.types.Predicate;\n" +
                "import com.querydsl.jpa.impl.JPAQueryFactory;\n" +
                "\n" +
                "\n" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.PageImpl;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "\n" +
                "import javax.persistence.EntityManager;\n" +
                "import java.time.LocalDateTime;\n" +
                "import java.time.format.DateTimeParseException;\n" +
                "import java.util.List;\n" +
                "\n" +
                "//import static goodshop.board.alliance.QAlliance.alliance;\n" +
                "\n" +
                "import static goodshop.....Q"+className+"."+classNameSmFirst+";\n" +
                "import static org.springframework.util.StringUtils.hasText;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "public class "+className+"RepositoryImpl implements "+className+"RepositoryCustom {\n" +
                "\n" +
                "    private final JPAQueryFactory queryFactory;\n" +
                "\n" +
                "    public "+className+"RepositoryImpl(EntityManager em) {\n" +
                "        this.queryFactory = new JPAQueryFactory(em);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "   @Override\n"+
                "    public Page<"+className+"Dto> searchAllV2("+className+"SearchCondition condition, Pageable pageable) {\n" +
                "\n" +
                "        List<"+className+"Dto> content = queryFactory.\n" +
                "                select(new Q"+className+"Dto(\n" +
                sqlNewPrint +
                "                )).from("+classNameSmFirst+")\n" +
                "                .where(\n" +
                "                     //   searchAllV2Predicate(condition)\n" +
                "                )\n" +
                "                .orderBy("+classNameSmFirst+".id.desc())\n" +
                "                .offset(pageable.getOffset())\n" +
                "                .limit(pageable.getPageSize())\n" +
                "                .fetch();\n" +
                "\n" +
                "        long total = queryFactory\n" +
                "                .select("+classNameSmFirst+".count())\n" +
                "                .from("+classNameSmFirst+")\n" +
                "                .where(\n" +
                "                    //    searchAllV2Predicate(condition)\n" +
                "                )\n" +
                "                .fetch().get(0);\n" +
                "\n" +
                "        return new PageImpl<>(content, pageable, total);\n" +
                "    }\n" +
                "\n" +
                "/*\n" +
                "    private BooleanBuilder searchAllV2Predicate(ProductCategorySearchCondition condition){\n" +
                "        return new BooleanBuilder()\n" +
                "                .and(condS(condition.getField(), condition.getS()))\n" +
                "                .and(condSdate(condition.getSdate()))\n" +
                "                .and(condEdate(condition.getEdate()));\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    private Predicate condS(String field, String s){\n" +
                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                "\n" +
                "        if(hasText(field) && hasText(s)) {\n" +
                "            if(field.equals(\"all\")){\n" +
                "\n" +
                "                builder.or(alliance.userTitle.like(\"%\" + s + \"%\"));\n" +
                "                builder.or(alliance.userContent.like(\"%\" + s + \"%\"));\n" +
                "                //builder.or(alliance.isrtDate.between(sdate, edate));\n" +
                "\n" +
                "            } else if(field.equals(\"title\")) {\n" +
                "\n" +
                "                builder.or(alliance.userTitle.like(\"%\" + s + \"%\"));\n" +
                "\n" +
                "            } else if(field.equals(\"content\")) {\n" +
                "\n" +
                "                builder.or(alliance.userContent.like(\"%\" + s + \"%\"));\n" +
                "\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        return builder;\n" +
                "    }\n" +
                "\n" +
                "    private Predicate condSdate( String sdate){\n" +
                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                "\n" +
                "        if(hasText(sdate)){\n" +
                "            try {\n" +
                "                LocalDateTime localDateTime = LocalDateTime.parse(sdate + \"T00:00:00\");\n" +
                "                builder.or(alliance.isrtDate.goe(localDateTime)); // isrtDate >= sdate\n" +
                "\n" +
                "            } catch (DateTimeParseException e) {\n" +
                "            }\n" +
                "        }\n" +
                "        return builder;\n" +
                "    }\n" +
                "\n" +
                "    private Predicate condEdate( String edate){\n" +
                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                "        if(hasText(edate)) {\n" +
                "            try {\n" +
                "                LocalDateTime localDateTime = LocalDateTime.parse(edate + \"T00:00:00\");\n" +
                "                builder.or(alliance.isrtDate.loe(localDateTime)); // isrtDate <= edate\n" +
                "\n" +
                "            } catch (DateTimeParseException e) {\n" +
                "            }\n" +
                "        }\n" +
                "        return builder;\n" +
                "    }\n" +
                "*/\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+className+"ApiDto> searchFindAllDesc() {\n" +
                "        List<"+className+"ApiDto> content = queryFactory.\n" +
                "                select(Projections.constructor("+className+"ApiDto.class,\n" +
                sqlNewPrint+
                "                )).from("+classNameSmFirst+")\n" +
                "                .orderBy("+classNameSmFirst+".id.asc())\n" +
                "                .fetch();\n" +
                "\n" +
                "\n" +
                "        return content;\n" +
                "    }\n" +
                "}");

        String code = jta.getText();

        try {
            File file = new File("C:\\category\\" + className + "RepositoryImpl.java");
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
