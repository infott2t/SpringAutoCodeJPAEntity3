package org.example.v5;

public class UtilStaticV5 {

    public static String domainStr; // entity name, small letter.
    public static String[] colStrs;  // entity column, String array.
    public static String[] colLongs; // entity column, Long array.
    public static String[] colDates; // entity column, LocalDateTime array.

    public static String[] colNames; // entity column, In orders.

    public static String[] foreignCols; // entity column, In orders.


    public static String thymleafInitUrl;  // /administer/instanceurl



    public UtilStaticV5(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String thymleafInitUrl) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.thymleafInitUrl = thymleafInitUrl;

    }

    public UtilStaticV5(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;

    }

    public UtilStaticV5(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs, String thymleafInitUrl) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;
        this.thymleafInitUrl = thymleafInitUrl;
    }

    public static String upperCaseFirstLetter(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static String lowerCaseFirstLetter(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    public String makeEntity() {

        return "" +
                "\n" +
                "import lombok.Builder;\n" +
                "import lombok.Getter;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "import lombok.Setter;\n" +

                "\n" +
                "import javax.persistence.*;\n" +
                "import java.time.LocalDateTime;\n" +

                "\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@NoArgsConstructor\n" +
                "@Entity\n" +
                "@Table(name=\""+toUpp_Upp(domainStr)+"\")\n" +
                "public class "+toUpperFirst(domainStr)+" {\n" +
                "\n" +
                "    @Id\n" +
                "    @GeneratedValue(strategy = GenerationType.AUTO)\n" +
                "    @Column(name = \""+toUpp_Upp(domainStr)+"_ID\")\n" +
                "    private Long id;\n" +
                "\n"
                + makeEntityCols()
                + makeEntityForeignCols() +
                "\n" +
                "\n"
                + makeEntityMethods() +
                "}";



    }

    private String makeEntityMethods() {
        //"    @Builder\n" +
        //        "    public RoleCOMPANY(Long id, String isDel, LocalDateTime abcDate, LocalDateTime modifiedDate, LocalDateTime createdDate, Post post) {\n" +
        //        "        this.addressStr = addressStr;\n" +
        //        "        this.phoneStr = phoneStr;\n" +
        //        "        this.coperation = coperation;\n" +
        //        "        this.createdDate = createDate;\n" +
        //        "    }\n" +
        String result ="";
        result += "    @Builder\n" +
                "    public "+toUpperFirst(domainStr)+"(Long id, ";
        for (int i = 1; i < colNames.length; i++) {
            for(int j=0; j< colLongs.length; j++){
                if(colNames[i].equals(colLongs[j])){
                    result += "Long "+colNames[i]+", ";
                }
            }
            for(int j=0; j< colStrs.length; j++){
                if(colNames[i].equals(colStrs[j])){
                    result += "String "+colNames[i]+", ";
                }
            }
            for(int j=0; j< colDates.length; j++){
                if(colNames[i].equals(colDates[j])){
                    result += "LocalDateTime "+colNames[i]+", ";
                }
            }
        }
        if(foreignCols!=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += toUpperFirst(foreignCols[i]) + " " + toLowerFirst(foreignCols[i]) + ", ";
            }
        }
        result = result.substring(0, result.length()-2);
        result += ") {\n" +
                "        this.id = id;\n";
        for(int i=1; i<colNames.length; i++){
            result += "        this."+colNames[i]+" = "+colNames[i]+";\n";
        }
        if(foreignCols!=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        this." + toLowerFirst(foreignCols[i]) + " = " + toLowerFirst(foreignCols[i]) + ";\n";
            }
        }
        result += "    }\n";
        return result;
    }

    private String makeEntityForeignCols() {
       // "    @ManyToOne(targetEntity = "+foreignCols[i]+".class, fetch = FetchType.LAZY)\n" +
       //         "    @JoinColumn(name = \""+toUpp_Upp(foreignCols[i])+"_ID\")\n" +
       //         "    private "+foreignCols[i]+" "+toUpperFirst(foreignCols[i])+";\n" +
        String result = "";
        if(foreignCols !=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += "    @ManyToOne(targetEntity = " + foreignCols[i] + ".class, fetch = FetchType.LAZY)\n" +
                        "    @JoinColumn(name = \"" + toUpp_Upp(foreignCols[i]) + "_ID\")\n" +
                        "    private " + foreignCols[i] + " " + toLowerFirst(foreignCols[i]) + ";\n\n";

            }
        }
        return result;
    }

    private String toLowerFirst(String foreignCol) {
        return foreignCol.substring(0,1).toLowerCase() + foreignCol.substring(1);
    }

    private String makeEntityCols() {
        // "    private Long String \n"+
        String result = "";
        for(int i=1; i<colNames.length; i++) {
            for(int j=0; j<colLongs.length; j++){
                if(colNames[i].equals(colLongs[j])){
                    result += "    private Long "+colNames[i]+";\n";
                }
            }
            for(int j=0; j<colStrs.length; j++){
                if(colNames[i].equals(colStrs[j])){
                    result += "    private String "+colNames[i]+";\n";
                }
            }
            for(int j=0; j<colDates.length; j++){
                if(colNames[i].equals(colDates[j])){
                    result += "    private LocalDateTime "+colNames[i]+";\n";
                }
            }
        }
        return result+"\n";
    }

    String toUpperFirst(String domainStr) {
        return domainStr.substring(0,1).toUpperCase() + domainStr.substring(1);
    }

    //roleCompany --> ROLE_COMPANY
    private String toUpp_Upp(String domainStr) {
        String result = "";
        // ex) domainStr --> roleCompany --> ROLE_COMPANY
        char c = domainStr.charAt(0);
        result = result + Character.toUpperCase(c);

        for (int i = 1; i < domainStr.length(); i++) {
            c = domainStr.charAt(i);
            if (Character.isUpperCase(c)) {
                result = result + "_" + c;                         // 1. 대문자가 나오면 _를 붙인다.
                // result = result + Character.toLowerCase(c);   // 2. 대문자를 소문자로 바꾼다.
            } else {
                result = result + Character.toUpperCase(c);   // 3. 소문자를 대문자로 바꿔서 붙여준다.
            }

        }
        return result;
    }

    public String toAllLowerCase(String domainStr) {
        String result = "";
        for (int i = 0; i < domainStr.length(); i++) {
            char c = domainStr.charAt(i);
            result = result + Character.toLowerCase(c);
        }
        return result;
    }


    public String makeApiDto() {
        String result ="";
        result ="" +
                "import com.querydsl.core.annotations.QueryProjection;\n" +
                "import lombok.Data;\n" +

                "\n" +
                "import java.time.LocalDateTime;\n" +
                "\n" +
                "@Data\n" +
                "public class "+toUpperFirst(domainStr)+"ApiDto {\n" +
                colNamesV()+
                "\n" +
                "\n" +
                "\n" +
                makeApiDtoMethods()+
                "\n" +
                " \n" +
                "\n" +
                "}";
        return result;
    }

    private String makeApiDtoMethods() {
      //  "    @QueryProjection\n" +
      //          "    public RoleCOMPANYApiDto( Long roleCompanyId, Coperation coperation, AddressStr addressStr, PhoneStr phoneStr, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {\n" +
      //          "        this.roleCompanyId = roleCompanyId;\n" +
      //          "        this.coperation = coperation;\n" +
      //          "        this.addressStr = addressStr;\n" +
      //          "        this.phoneStr = phoneStr;\n" +
      //          "        this.isDel = isDel;\n" +
      //          "        this.modifiedDate = modifiedDate;\n" +
      //          "        this.createdDate = createdDate;\n" +
      //          "    }\n" +
        String result = "";
        result += "    @QueryProjection\n" +
                "    public "+toUpperFirst(domainStr)+"ApiDto(";
        for(int i=0; i<colNames.length; i++) {
            for(int j=0; j<colLongs.length; j++){
                if(colNames[i].equals(colLongs[j])){
                    result += " Long "+colNames[i]+",";
                }
            }
            for(int j=0; j<colStrs.length; j++){
                if(colNames[i].equals(colStrs[j])){
                    result += " String "+colNames[i]+",";
                }
            }
            for(int j=0; j<colDates.length; j++){
                if(colNames[i].equals(colDates[j])){
                    result += " LocalDateTime "+colNames[i]+",";
                }
            }
        }
        if(foreignCols !=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += " " + foreignCols[i] + " " + toLowerFirst(foreignCols[i]) + ",";
            }
        }
        result = result.substring(0, result.length()-1);
        result += ") {\n";
        for(int i=0; i<colNames.length; i++) {
            result += "        this."+colNames[i]+" = "+colNames[i]+";\n";
        }
        if(foreignCols !=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        this." + toLowerFirst(foreignCols[i]) + " = " + toLowerFirst(foreignCols[i]) + ";\n";
            }
        }
        result += "    }\n";

        return result;
    }

    private String colNamesV() {
        String result = "";
        result += "    private Long "+colNames[0]+";\n";

        for(int i=1; i<colNames.length; i++){

           for(int j=0; j<colLongs.length; j++){
               if(colNames[i].equals(colLongs[j])){
                   result += "    private Long "+colNames[i]+";\n";
               }
           }
           for(int j=0; j<colStrs.length; j++){
               if(colNames[i].equals(colStrs[j])){
                   result += "    private String "+colNames[i]+";\n";
               }
           }
           for(int j=0; j<colDates.length; j++){
               if(colNames[i].equals(colDates[j])){
                   result += "    private LocalDateTime "+colNames[i]+";\n";
               }
           }
        }
        if(foreignCols !=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += "    private " + foreignCols[i] + " " + toLowerFirst(foreignCols[i]) + ";\n";
            }
        }
        return result;
    }

    public String makeRepository() {
        return "" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "import org.springframework.data.querydsl.QuerydslPredicateExecutor;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "@Repository\n" +
                "public interface "+toUpperFirst(domainStr)+"Repository extends JpaRepository<"+toUpperFirst(domainStr)+", Long>,\n" +
                "        QuerydslPredicateExecutor<"+toUpperFirst(domainStr)+">, "+toUpperFirst(domainStr)+"RepositoryCustom {\n" +
                "\n" +
                "\n" +
                "}";
    }

    public String makeRepositoryCustom() {
        return "" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface "+toUpperFirst(domainStr)+"RepositoryCustom {\n" +
                "\n" +
                "    Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV2("+toUpperFirst(domainStr)+"SearchCondition condition, Pageable pageable);\n" +
                "\n" +
                "  List<"+toUpperFirst(domainStr)+"ApiDto> searchFindAllDesc();\n" +
                "\n" +
                "\n" +
                "}";
    }

    public String makeRepositoryImpl() {
        String result = "";
        result = "" +
                "\n" +
                "import com.querydsl.core.BooleanBuilder;\n" +
                "import com.querydsl.core.types.Predicate;\n" +
                "import com.querydsl.core.types.Projections;\n" +
                "import com.querydsl.jpa.impl.JPAQueryFactory;\n" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.PageImpl;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "\n" +
                "import javax.persistence.EntityManager;\n" +
                "import java.time.LocalDateTime;\n" +
                "import java.time.format.DateTimeParseException;\n" +
                "import java.util.List;\n" +
                "\n" +
                "import static org.example.domain.....Q"+toUpperFirst(domainStr)+"."+toLowerFirst(domainStr)+";\n" +

                "import static org.springframework.util.StringUtils.hasText;\n" +
                "\n" +
                "\n" +
                "\n" +
                "public class "+toUpperFirst(domainStr)+"RepositoryImpl implements "+toUpperFirst(domainStr)+"RepositoryCustom {\n" +
                "\n" +
                "    private final JPAQueryFactory queryFactory;\n" +
                "\n" +
                "    public "+toUpperFirst(domainStr)+"RepositoryImpl(EntityManager em) {\n" +
                "        this.queryFactory = new JPAQueryFactory(em);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "   @Override\n" +
                "    public Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV2("+toUpperFirst(domainStr)+"SearchCondition condition, Pageable pageable) {\n" +
                "\n" +
                "        List<"+toUpperFirst(domainStr)+"ApiDto> content = queryFactory.\n" +
                "                select(Projections.constructor("+toUpperFirst(domainStr)+"ApiDto.class,\n" +
                queryStr()+
                "                )).from("+toLowerFirst(domainStr)+")\n" +
                queryStr2()+
                "                .where(\n" +
                "                        searchAllV2Predicate(condition)\n" +
                "                ).where("+toLowerFirst(domainStr)+".isDel.eq(\"N\"))\n" +
                "                .orderBy("+toLowerFirst(domainStr)+".id.desc())\n" +
                "                .offset(pageable.getOffset())\n" +
                "                .limit(pageable.getPageSize())\n" +
                "                .fetch();\n" +
                "\n" +
                "        long total = queryFactory\n" +
                "                .select("+toLowerFirst(domainStr)+".count())\n" +
                "                .from("+toLowerFirst(domainStr)+")\n" +
                "                .where(\n" +
                "                        searchAllV2Predicate(condition)\n" +
                "                ).where("+toLowerFirst(domainStr)+".isDel.eq(\"N\"))\n" +
                "                .fetch().get(0);\n" +
                "\n" +
                "        return new PageImpl<>(content, pageable, total);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    private BooleanBuilder searchAllV2Predicate("+toUpperFirst(domainStr)+"SearchCondition condition){\n" +
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
                "           if(field.equals(\"id\")) {\n" +
                "\n" +
                "                builder.or("+toLowerFirst(domainStr)+".id.eq(Long.valueOf(s)));\n" +
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
                "                builder.or("+toLowerFirst(domainStr)+".modifiedDate.goe(localDateTime)); // isrtDate >= sdate\n" +
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
                "                builder.or("+toLowerFirst(domainStr)+".modifiedDate.loe(localDateTime)); // isrtDate <= edate\n" +
                "\n" +
                "            } catch (DateTimeParseException e) {\n" +
                "            }\n" +
                "        }\n" +
                "        return builder;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+toUpperFirst(domainStr)+"ApiDto> searchFindAllDesc() {\n" +
                "        List<"+toUpperFirst(domainStr)+"ApiDto> content = queryFactory.\n" +
                "                select(Projections.constructor("+toUpperFirst(domainStr)+"ApiDto.class,\n" +
                queryStr()+
                "                )).from("+toLowerFirst(domainStr)+").where("+toLowerFirst(domainStr)+".isDel.eq(\"N\"))\n" +
                queryStr2()+
                "                .orderBy("+toLowerFirst(domainStr)+".id.asc())\n" +
                "                .fetch();\n" +
                "\n" +
                "\n" +
                "        return content;\n" +
                "    }\n" +
                "}";
        return result;
    }

    private String queryStr2() {
        //"                .leftJoin(roleUSER.addressStr, addressStr)\n" +
        //        "                .leftJoin(roleUSER.phoneStr, phoneStr)\n" +
        String result = "";
        if(foreignCols !=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "                .leftJoin("+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[i])+", "+toLowerFirst(foreignCols[i])+")\n";
            }
        }
        return result;
    }

    private String queryStr() {
        //"                        roleUSER.id,\n" +
        //        "                        roleUSER.addressStr,\n" +
        //        "                        roleUSER.phoneStr,\n" +
        //        "                        roleUSER.createdDate,\n" +
        //        "                        roleUSER.modifiedDate,\n" +
        //        "                        roleUSER.isDel\n" +
        String result ="";
        for(int i=0; i<colNames.length; i++){
            result += "                        "+toLowerFirst(domainStr)+"."+colNames[i]+",\n";
        }
        if(foreignCols!=null){
            for(int i=0; i<foreignCols.length; i++){
                result += "                        "+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[i])+",\n";
            }
        }
        result = result.substring(0, result.length()-2);

        return result;
    }

    public String makeService() {
        String result ="";
        result = "" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Service\n" +
                "@RequiredArgsConstructor\n" +
                "public class "+toUpperFirst(domainStr)+"Service {\n" +
                "\n" +
                "    private final "+toUpperFirst(domainStr)+"Repository "+toLowerFirst(domainStr)+"Repository;\n" +
                "    \n" +
                "    @Transactional(readOnly = true)\n" +
                "    public List<"+toUpperFirst(domainStr)+"ApiDto> searchFindAllDesc() {\n" +
                "        return  "+toLowerFirst(domainStr)+"Repository.searchFindAllDesc();\n" +
                "    }\n" +
                "\n" +
                "    @Transactional(readOnly = true)\n" +
                "    public "+toUpperFirst(domainStr)+" findById(Long id) {\n" +
                "        return "+toLowerFirst(domainStr)+"Repository.findById(id).orElseThrow();\n" +
                "    }\n" +
                "\n" +
                "    @Transactional\n" +
                "    public void save("+toUpperFirst(domainStr)+" "+toLowerFirst(domainStr)+") {\n" +
                "        "+toLowerFirst(domainStr)+"Repository.save("+toLowerFirst(domainStr)+");\n" +
                "    }\n" +
                "\n" +
                "    @Transactional(readOnly = true)\n" +
                "    public Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV2("+toUpperFirst(domainStr)+"SearchCondition condition, Pageable pageable) {\n" +
                "        return "+toLowerFirst(domainStr)+"Repository.searchAllV2(condition, pageable);\n" +
                "    }\n" +
                "}";
        return result;
    }

    public String makeRootIndex() {
        String result = "";

        result = "" +
                "<!doctype html>\n" +
                "<html lang=\"ko\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "          integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\">\n" +
                "\n" +
                "    <title>Entity Data CRUD First Instance</title>\n" +
                "    <style>\n" +
                "        .navbar-brand {\n" +
                "            font-size: 1rem;\n" +
                "        }\n" +
                "        .logo{\n" +
                "            color: black;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <h2>Entity Data CRUD First Instance</h2>\n" +
                "    <br />\n" +
                "    <br/>\n" +
                "    <p><a th:href=\"@{"+thymleafInitUrl+"/[domain]}\">Move table, "+domainStr+"</a></p>\n" +
                "<!-- Add, link...-->\n" +
                "    <!-- <p><a th:href=\"@{/administer/instanceurl/[domain]}\">Move talbe, [domain]</a></p> -->\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "<br />\n" +
                "\n" +
                "</div>\n" +
                "<div class=\"fixed-bottom\">\n" +
                "    <nav class=\"navbar navbar-expand bg-light \">\n" +
                "        <div class=\"container-fluid \">\n" +
                "            <span class=\"navbar-brand\" href=\"#\">Entity, CRUD </span>&nbsp;&nbsp;&nbsp;\n" +
                "            <a class=\"navbar-brand logo\" href=\"https://github.com/infott2t/SpringAutoCodeJPAEntity3\">@infott2t <svg style=\"margin-bottom:4px;\" xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" fill=\"currentColor\" class=\"bi bi-github\" viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z\"/>\n" +
                "            </svg></a>\n" +
                "\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<!-- Optional JavaScript; choose one of the two! -->\n" +
                "\n" +
                "<!-- Option 1: Bootstrap Bundle with Popper -->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\n" +
                "        integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\"\n" +
                "        crossorigin=\"anonymous\"></script>\n" +
                "\n" +
                "<!-- Option 2: Separate Popper and Bootstrap JS -->\n" +
                "<!--\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\" integrity=\"sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p\" crossorigin=\"anonymous\"></script>\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js\" integrity=\"sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF\" crossorigin=\"anonymous\"></script>\n" +
                "  -->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js\"></script>\n" +
                "<script>\n" +
                "<!--JQuery-->\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return result;
    }

    public String makeIndex() {
        String result ="";

        return result;
    }

    public String makeSearchCondition() {
        String result ="" +
                "import lombok.Data;\n" +
                "\n" +
                "@Data\n" +
                "public class "+toUpperFirst(domainStr)+"SearchCondition {\n" +
                "\n" +
                "    private String field;\n" +
                "    private String s;\n" +
                "\n" +
                "    private String sdate;\n" +
                "    private String edate;\n" +
                "}\n";

        return result;
    }
}
