package org.example.v5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilStaticV5 {

    public static String domainStr; // entity name, small letter.
    public static String[] colStrs;  // entity column, String array.
    public static String[] colLongs; // entity column, Long array.
    public static String[] colDates; // entity column, LocalDateTime array.

    public static String[] colNames; // entity column, In orders.

    public static String[] foreignCols; // entity column, In orders.


    public static String thymleafInitUrl;  // /administer/instanceurl

    public static String rootPackageStr;

    public static String projectPath;

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

    public UtilStaticV5(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs, String thymleafInitUrl, String rootPackageStr) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;
        this.thymleafInitUrl = thymleafInitUrl;
        this.rootPackageStr = rootPackageStr;
    }

    public UtilStaticV5(String domainStr, String[] colStrs, String[] colLongs, String[] colDates, String[] colNames, String[] foreignColStrs, String thymleafInitUrl, String rootPackageStr, String projectPath) {
        this.domainStr = domainStr;
        this.colStrs = colStrs;
        this.colLongs = colLongs;
        this.colDates = colDates;
        this.colNames = colNames;
        this.foreignCols = foreignColStrs;
        this.thymleafInitUrl = thymleafInitUrl;
        this.rootPackageStr = rootPackageStr;
        this.projectPath = projectPath;
    }

    public static String upperCaseFirstLetter(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public static String lowerCaseFirstLetter(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    public String makeEntity() {

        String importForeignStr = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                importForeignStr += "import "+rootPackageStr+".domain."+toAllLowerCase(foreignCols[i])+"."+toUpperFirst(foreignCols[i])+";\n";
            }
        }

        String result = "\n" +

                importForeignStr +
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
                "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
                "    @Column(name = \""+toUpp_Upp(domainStr)+"_ID\")\n" +
                "    private Long id;\n" +
                "\n"
                + makeEntityCols()
                + makeEntityForeignCols() +
                "\n" +
                "\n"
                + makeEntityMethods() +
                "}";
        String packageStr = null;
        if(rootPackageStr!=null){
            packageStr = "package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }

        return result;

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

    public String toLowerFirst(String foreignCol) {
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
        String importForeignStr = "";
        if(foreignCols!=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                importForeignStr += "import "+rootPackageStr+".domain." + toAllLowerCase(foreignCols[i])+"."+toUpperFirst(foreignCols[i]) + ";\n";
            }
        }
        String result ="";
        result =
                importForeignStr +
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

        String packageStr = null;
        if(rootPackageStr!=null){
            packageStr = "package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }

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
        String importStr = "import " +rootPackageStr+".domain."+toAllLowerCase(domainStr)+"."+toUpperFirst(domainStr)+";\n";
        String result =
                importStr +
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


        String packageStr = null;

        if(rootPackageStr!=null){
            packageStr = "package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }


        return result;
    }

    public String makeRepositoryCustom() {
        String result =
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface "+toUpperFirst(domainStr)+"RepositoryCustom {\n" +
                "\n" +
                "    Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV2("+toUpperFirst(domainStr)+"SearchCondition condition, Pageable pageable);\n" +
                "\n" +
                "    Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV3("+toUpperFirst(domainStr)+"SearchCondition2 condition, Pageable pageable);\n" +
                "\n" +
                "  List<"+toUpperFirst(domainStr)+"ApiDto> searchFindAllDesc();\n" +
                "\n" +
                "\n" +
                "}";

        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr = "package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }

        return result;
    }
    public String makeRepositoryImpl2(){
        String result = "";
        String importStaticQStr = "";
        if(rootPackageStr!=null) {
            importStaticQStr = "import static " + rootPackageStr + ".domain." + toAllLowerCase(domainStr) + ".Q" + toUpperFirst(domainStr) + "." + toLowerFirst(domainStr) + ";\n";
        }else{

        }
        result +="" +
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
                importStaticQStr +
                implImportStatic()+
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
                "    public Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV3("+toUpperFirst(domainStr)+"SearchCondition2 condition2, Pageable pageable) {\n" +
                "\n" +
                "        List<"+toUpperFirst(domainStr)+"ApiDto> content = queryFactory.\n" +
                "                select(Projections.constructor("+toUpperFirst(domainStr)+"ApiDto.class,\n" +
                impl2Columns()+
                "              )).from("+toLowerFirst(domainStr)+")\n" +
                impl2Columns2Foreign()+
                "                .where(\n" +
                "                        searchAllV3Predicate(condition2)\n" +
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
                "                        searchAllV3Predicate(condition2)\n" +
                "                ).where("+toLowerFirst(domainStr)+".isDel.eq(\"N\"))\n" +
                "                .fetch().get(0);\n" +
                "\n" +
                "        return new PageImpl<>(content, pageable, total);\n" +
                "    }\n" +
                "\n" +
                "    private Predicate searchAllV3Predicate("+toUpperFirst(domainStr)+"SearchCondition2 condition2) {\n" +
                "        return new BooleanBuilder()\n" +
                impl2Columns3()+
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                impl2Methods()+

                "\n" +
                impl2ConS2Mehtod()+
                "\n" +
                "    private Predicate condSdate2(String sdate) {\n" +
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
                "    private Predicate condEdate2(String edate) {\n" +
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
                "    @Override\n" +
                "    public Page<"+toUpperFirst(domainStr)+"ApiDto> searchAllV2("+toUpperFirst(domainStr)+"SearchCondition condition, Pageable pageable) {\n" +
                "\n" +
                "        List<"+toUpperFirst(domainStr)+"ApiDto> content = queryFactory.\n" +
                "                select(Projections.constructor("+toUpperFirst(domainStr)+"ApiDto.class,\n" +
                impl2Columns()+
                "              )).from("+toLowerFirst(domainStr)+")\n" +
                impl2Columns2Foreign()+
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
                "\n" +
                "    private BooleanBuilder searchAllV2Predicate("+toUpperFirst(domainStr)+"SearchCondition condition){\n" +
                "        return new BooleanBuilder()\n" +
                "                .and(condS(condition.getField(), condition.getS()))\n" +
                "                .and(condSdate(condition.getSdate()))\n" +
                "                .and(condEdate(condition.getEdate()));\n" +
                "\n" +
                "    }\n" +
                "\n" +
                impl2ConSMehtod()+
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
                impl2Columns()+
                "              )).from("+toLowerFirst(domainStr)+").where("+toLowerFirst(domainStr)+".isDel.eq(\"N\"))\n" +
                impl2Columns2Foreign()+
                "                .orderBy("+toLowerFirst(domainStr)+".id.asc())\n" +
                "                .fetch();\n" +
                "\n" +
                "\n" +
                "        return content;\n" +
                "    }\n" +
                "}";

        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr =  "" +"package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }

        return result;
    }

    private String impl2ConS2Mehtod() {
        String result = "";

        //"    private Predicate condS2(String field, String s) {\n" +
        //        "        BooleanBuilder builder = new BooleanBuilder();\n" +
        //        "\n" +
        //        "        if(hasText(field) && hasText(s)){\n" +
        //        "            if(field.equals(\"id\")){\n" +
        //        "                builder.or(serviceWork.id.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"coopName\")) {\n" +
        //        "                builder.or(serviceWork.coopName.eq(s));\n" +
        //        "            }else if(field.equals(\"coopComment\")) {\n" +
        //        "                builder.or(serviceWork.coopComment.eq(s));\n" +
        //        "            }else if(field.equals(\"coopWorkName\")) {\n" +
        //        "                builder.or(serviceWork.coopWorkName.eq(s));\n" +
        //        "            }else if(field.equals(\"wtag1\")) {\n" +
        //        "                builder.or(serviceWork.wtag1.eq(s));\n" +
        //        "            }else if(field.equals(\"wtag2\")) {\n" +
        //        "                builder.or(serviceWork.wtag2.eq(s));\n" +
        //        "            }else if(field.equals(\"startWorkDate\")) {\n" +
        //        "                builder.or(serviceWork.startWorkDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"endWorkDate\")) {\n" +
        //        "                builder.or(serviceWork.endWorkDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"searchTag1\")) {\n" +
        //        "                builder.or(serviceWork.searchTag1.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag2\")) {\n" +
        //        "                builder.or(serviceWork.searchTag2.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag3\")) {\n" +
        //        "                builder.or(serviceWork.searchTag3.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag4\")) {\n" +
        //        "                builder.or(serviceWork.searchTag4.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag5\")) {\n" +
        //        "                builder.or(serviceWork.searchTag5.eq(s));\n" +
        //        "            }else if(field.equals(\"viewCount\")) {\n" +
        //        "                builder.or(serviceWork.viewCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"likeCount\")) {\n" +
        //        "                builder.or(serviceWork.likeCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starCount\")) {\n" +
        //        "                builder.or(serviceWork.starCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starAll\")) {\n" +
        //        "                builder.or(serviceWork.starAll.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starPairMan\")) {\n" +
        //        "                builder.or(serviceWork.starPairMan.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"isDel\")) {\n" +
        //        "                builder.or(serviceWork.isDel.eq(s));\n" +
        //        "            }else if(field.equals(\"modifiedDate\")) {\n" +
        //        "                builder.or(serviceWork.modifiedDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"createdDate\")) {\n" +
        //        "                builder.or(serviceWork.createdDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"serviceWorkStrId\")) {\n" +
        //        "                builder.or(serviceWork.serviceWorkStr.id.eq(Long.valueOf(s)));\n" +
        //        "            }\n" +
        //        "        }\n" +
        //        "        return builder;\n" +
        //        "    }\n" +
        result +="    private Predicate condS2(String field, String s) {\n" +
                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                "\n"+
                "        if(hasText(field) && hasText(s)){\n";
        for (int i = 0; i < colNames.length; i++) {
            if(colLongs!=null){
                for(int j=0; j<colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".eq(Long.valueOf(s)));\n" +
                                "            }\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".like(\"%\"+s+\"%\"));\n" +
                                "            }\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j<colDates.length; j++){
                    if(colNames[i].equals(colDates[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".eq(LocalDateTime.parse(s)));\n" +
                                "            }\n";
                    }
                }
            }

        }
        if(foreignCols!=null){
            for(int j=0; j<foreignCols.length; j++){

                    result += "            if(field.equals(\""+toLowerFirst(foreignCols[j])+"Id\")){\n" +
                            "                builder.or("+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[j])+".id.eq(Long.valueOf(s)));\n" +
                            "            }\n";
                }

        }
        result += "        }\n" +
                "        return builder;\n" +
                "    }\n";

        return result;
    }
    private String impl2ConSMehtod() {
        String result = "";

        //"    private Predicate condS(String field, String s) {\n" +
        //        "        BooleanBuilder builder = new BooleanBuilder();\n" +
        //        "\n" +
        //        "        if(hasText(field) && hasText(s)){\n" +
        //        "            if(field.equals(\"id\")){\n" +
        //        "                builder.or(serviceWork.id.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"coopName\")) {\n" +
        //        "                builder.or(serviceWork.coopName.eq(s));\n" +
        //        "            }else if(field.equals(\"coopComment\")) {\n" +
        //        "                builder.or(serviceWork.coopComment.eq(s));\n" +
        //        "            }else if(field.equals(\"coopWorkName\")) {\n" +
        //        "                builder.or(serviceWork.coopWorkName.eq(s));\n" +
        //        "            }else if(field.equals(\"wtag1\")) {\n" +
        //        "                builder.or(serviceWork.wtag1.eq(s));\n" +
        //        "            }else if(field.equals(\"wtag2\")) {\n" +
        //        "                builder.or(serviceWork.wtag2.eq(s));\n" +
        //        "            }else if(field.equals(\"startWorkDate\")) {\n" +
        //        "                builder.or(serviceWork.startWorkDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"endWorkDate\")) {\n" +
        //        "                builder.or(serviceWork.endWorkDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"searchTag1\")) {\n" +
        //        "                builder.or(serviceWork.searchTag1.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag2\")) {\n" +
        //        "                builder.or(serviceWork.searchTag2.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag3\")) {\n" +
        //        "                builder.or(serviceWork.searchTag3.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag4\")) {\n" +
        //        "                builder.or(serviceWork.searchTag4.eq(s));\n" +
        //        "            }else if(field.equals(\"searchTag5\")) {\n" +
        //        "                builder.or(serviceWork.searchTag5.eq(s));\n" +
        //        "            }else if(field.equals(\"viewCount\")) {\n" +
        //        "                builder.or(serviceWork.viewCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"likeCount\")) {\n" +
        //        "                builder.or(serviceWork.likeCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starCount\")) {\n" +
        //        "                builder.or(serviceWork.starCount.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starAll\")) {\n" +
        //        "                builder.or(serviceWork.starAll.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"starPairMan\")) {\n" +
        //        "                builder.or(serviceWork.starPairMan.eq(Long.valueOf(s)));\n" +
        //        "            }else if(field.equals(\"isDel\")) {\n" +
        //        "                builder.or(serviceWork.isDel.eq(s));\n" +
        //        "            }else if(field.equals(\"modifiedDate\")) {\n" +
        //        "                builder.or(serviceWork.modifiedDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"createdDate\")) {\n" +
        //        "                builder.or(serviceWork.createdDate.eq(LocalDateTime.parse(s)));\n" +
        //        "            }else if(field.equals(\"serviceWorkStrId\")) {\n" +
        //        "                builder.or(serviceWork.serviceWorkStr.id.eq(Long.valueOf(s)));\n" +
        //        "            }\n" +
        //        "        }\n" +
        //        "        return builder;\n" +
        //        "    }\n" +
        result +="    private Predicate condS(String field, String s) {\n" +
                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                "\n"+
                "        if(hasText(field) && hasText(s)){\n";
        for (int i = 0; i < colNames.length; i++) {
            if(colLongs!=null){
                for(int j=0; j<colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".eq(Long.valueOf(s)));\n" +
                                "            }\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".like(\"%\"+s+\"%\"));\n" +
                                "            }\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j<colDates.length; j++){
                    if(colNames[i].equals(colDates[j])){
                        result += "            if(field.equals(\""+colNames[i]+"\")){\n" +
                                "                builder.or("+toLowerFirst(domainStr)+"."+colNames[i]+".eq(LocalDateTime.parse(s)));\n" +
                                "            }\n";
                    }
                }
            }

        }
        if(foreignCols!=null){
            for(int j=0; j<foreignCols.length; j++){

                    result += "            if(field.equals(\""+toLowerFirst(foreignCols[j])+"Id\")){\n" +
                            "                builder.or("+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[j])+".id.eq(Long.valueOf(s)));\n" +
                            "            }\n";

            }
        }
        result += "        }\n" +
                "        return builder;\n" +
                "    }\n";

        return result;
    }

    private String impl2Methods() {
        String result= "";
                //"    private Predicate condId(String id) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(id)) {\n" +
                //"                builder.or(serviceWork.id.eq(Long.valueOf(id)));\n" +
                //"        }\n" +
                //"\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condCoopName(String coopName) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(coopName)) {\n" +
                //"                builder.or(serviceWork.coopName.eq(coopName));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condCoopComment(String coopComment) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(coopComment)) {\n" +
                //"                builder.or(serviceWork.coopComment.eq(coopComment));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condCoopWorkName(String coopWorkName) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(coopWorkName)) {\n" +
                //"                builder.or(serviceWork.coopWorkName.eq(coopWorkName));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condWtag1(String wtag1) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(wtag1)) {\n" +
                //"                builder.or(serviceWork.wtag1.eq(wtag1));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condWtag2(String wtag2) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(wtag2)) {\n" +
                //"                builder.or(serviceWork.wtag2.eq(wtag2));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condStartWorkDate(String startWorkDate) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(startWorkDate)) {\n" +
                //"                builder.or(serviceWork.startWorkDate.eq(LocalDateTime.parse(startWorkDate)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condEndWorkDate(String endWorkDate) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"\n" +
                //"        if(hasText(endWorkDate)) {\n" +
                //"                builder.or(serviceWork.endWorkDate.eq(LocalDateTime.parse(endWorkDate)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condSearchTag1(String searchTag1) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(searchTag1)){\n" +
                //"            builder.or(serviceWork.searchTag1.eq(searchTag1));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condSearchTag2(String searchTag2) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(searchTag2)){\n" +
                //"            builder.or(serviceWork.searchTag2.eq(searchTag2));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condSearchTag3(String searchTag3) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(searchTag3)){\n" +
                //"            builder.or(serviceWork.searchTag2.eq(searchTag3));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condSearchTag4(String searchTag4) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(searchTag4)){\n" +
                //"            builder.or(serviceWork.searchTag2.eq(searchTag4));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"\n" +
                //"    private Predicate condSearchTag5(String searchTag5) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(searchTag5)){\n" +
                //"            builder.or(serviceWork.searchTag2.eq(searchTag5));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condViewCount(String viewCount) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(viewCount)){\n" +
                //"            builder.or(serviceWork.viewCount.eq(Long.valueOf(viewCount)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condLikeCount(String likeCount) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(likeCount)){\n" +
                //"            builder.or(serviceWork.likeCount.eq(Long.valueOf(likeCount)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condStarCount(String starCount) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(starCount)){\n" +
                //"            builder.or(serviceWork.starCount.eq(Long.valueOf(starCount)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condStarAll(String starAll) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(starAll)){\n" +
                //"            builder.or(serviceWork.starAll.eq(Long.valueOf(starAll)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condStarPairMan(String starPairMan) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(starPairMan)){\n" +
                //"            builder.or(serviceWork.starPairMan.eq(Long.valueOf(starPairMan)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condIsDel(String isDel) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(isDel)){\n" +
                //"            builder.or(serviceWork.isDel.eq(isDel));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condModifiedDate(String modifiedDate) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(modifiedDate)){\n" +
                //"            builder.or(serviceWork.modifiedDate.eq(LocalDateTime.parse(modifiedDate)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condCreatedDate(String createdDate) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(createdDate)){\n" +
                //"            builder.or(serviceWork.createdDate.eq(LocalDateTime.parse(createdDate)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"    private Predicate condServiceWorkStrId(String serviceWorkStrId) {\n" +
                //"        BooleanBuilder builder = new BooleanBuilder();\n" +
                //"        if(hasText(serviceWorkStrId)){\n" +
                //"            builder.or(serviceWork.serviceWorkStr.id.eq(Long.valueOf(serviceWorkStrId)));\n" +
                //"        }\n" +
                //"        return builder;\n" +
                //"    }\n" +
                //"\n"
        for (int i = 0; i < colNames.length ; i++) {
            if(colLongs!=null){
                for(int j=0; j < colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])) {
                        result += "    private Predicate cond" + toUpperFirst(colNames[i]) + "(String " + colNames[i] + ") {\n" +
                                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                                "        if(hasText(" + colNames[i] + ")){\n" +
                                "            builder.or("+toLowerFirst(domainStr)+"." + colNames[i] + ".eq(Long.valueOf(" + colNames[i] + ")));\n" +
                                "        }\n" +
                                "        return builder;\n" +
                                "    }\n" +
                                "\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j < colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])) {
                        result += "    private Predicate cond" + toUpperFirst(colNames[i]) + "(String " + colNames[i] + ") {\n" +
                                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                                "        if(hasText(" + colNames[i] + ")){\n" +
                                "            builder.or("+toLowerFirst(domainStr)+"." + colNames[i] + ".eq(" + colNames[i] + "));\n" +
                                "        }\n" +
                                "        return builder;\n" +
                                "    }\n" +
                                "\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j < colDates.length; j++){
                    if(colNames[i].equals(colDates[j])) {
                        result += "    private Predicate cond" + toUpperFirst(colNames[i]) + "(String " + colNames[i] + ") {\n" +
                                "        BooleanBuilder builder = new BooleanBuilder();\n" +
                                "        if(hasText(" + colNames[i] + ")){\n" +
                                "            builder.or("+toLowerFirst(domainStr)+"." + colNames[i] + ".eq(LocalDateTime.parse(" + colNames[i] + ")));\n" +
                                "        }\n" +
                                "        return builder;\n" +
                                "    }\n" +
                                "\n";
                    }
                }
            }

        }
        if(foreignCols!=null){
            for(int j=0; j< foreignCols.length; j++){
                    result += "    private Predicate cond" + toUpperFirst(foreignCols[j]) +"Id (String " + toLowerFirst(foreignCols[j]) + "Id) {\n" +
                            "        BooleanBuilder builder = new BooleanBuilder();\n" +
                            "        if(hasText(" + toLowerFirst(foreignCols[j]) + "Id)){\n" +
                            "            builder.or("+toLowerFirst(domainStr)+"." + toLowerFirst(foreignCols[j]) + ".id.eq(Long.valueOf(" + toLowerFirst(foreignCols[j]) + "Id)));\n" +
                            "        }\n" +
                            "        return builder;\n" +
                            "    }\n" +
                            "\n";
            }
        }
        return result;
    }

    private String impl2Columns3() {
        String result ="";
                //"                .and(condId(condition2.getId()))\n" +
                //"                .and(condCoopName(condition2.getCoopName()))\n" +
                //"                .and(condCoopComment(condition2.getCoopComment()))\n" +
                //"                .and(condCoopWorkName(condition2.getCoopWorkName()))\n" +
                //"                .and(condWtag1(condition2.getWtag1()))\n" +
                //"                .and(condWtag2(condition2.getWtag2()))\n" +
                //"                .and(condStartWorkDate(condition2.getStartWorkDate()))\n" +
                //"                .and(condEndWorkDate(condition2.getEndWorkDate()))\n" +
                //"                .and(condSearchTag1(condition2.getSearchTag1()))\n" +
                //"                .and(condSearchTag2(condition2.getSearchTag2()))\n" +
                //"                .and(condSearchTag3(condition2.getSearchTag3()))\n" +
                //"                .and(condSearchTag4(condition2.getSearchTag4()))\n" +
                //"                .and(condSearchTag5(condition2.getSearchTag5()))\n" +
                //"                .and(condViewCount(condition2.getViewCount()))\n" +
                //"                .and(condLikeCount(condition2.getLikeCount()))\n" +
                //"                .and(condStarCount(condition2.getStarCount()))\n" +
                //"                .and(condStarAll(condition2.getStarAll()))\n" +
                //"                .and(condStarPairMan(condition2.getStarPairMan()))\n" +
                //"                .and(condIsDel(condition2.getIsDel()))\n" +
                //"                .and(condModifiedDate(condition2.getModifiedDate()))\n" +
                //"                .and(condCreatedDate(condition2.getCreatedDate()))\n" +
                //"                .and(condServiceWorkStrId(condition2.getServiceWorkStrId()))\n" +
                //"                .and(condS2(condition2.getField(), condition2.getS()))\n" +
                //"                .and(condSdate2(condition2.getSdate()))\n" +
                //"                .and(condEdate2(condition2.getEdate()));\n" +
        for (int i = 0; i < colNames.length; i++) {
            if(colLongs!=null){
                for(int j=0; j<colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "                        .and(cond"+toUpperFirst(colNames[i])+"(condition2.get"+toUpperFirst(colNames[i])+"()))\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length;j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "                        .and(cond"+toUpperFirst(colNames[i])+"(condition2.get"+toUpperFirst(colNames[i])+"()))\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j<colDates.length;j++){
                    if(colNames[i].equals(colDates[j])){
                        result += "                        .and(cond"+toUpperFirst(colNames[i])+"(condition2.get"+toUpperFirst(colNames[i])+"()))\n";
                    }
                }
            }

        }

        if(foreignCols!=null){
            for(int j=0; j<foreignCols.length;j++){
                    result += "                        .and(cond"+toUpperFirst(foreignCols[j])+"Id(condition2.get"+toUpperFirst(foreignCols[j])+"Id()))\n";
            }
        }
        result +=   "                .and(condS2(condition2.getField(), condition2.getS()))\n" +
                    "                .and(condSdate2(condition2.getSdate()))\n" +
                    "                .and(condEdate2(condition2.getEdate()));\n";
        return result;
    }

    private String impl2Columns2Foreign() {
        String result = "";
        // "                .leftJoin(serviceWork.serviceWorkStr, "+toLowerFirst(domainStr)+"Str)\n" +
        if(foreignCols!=null){
                if(foreignCols!=null){
                    for (int j = 0; j < foreignCols.length; j++) {

                            result += "                        .leftJoin("+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[j])+", "+toLowerFirst(foreignCols[j])+")\n";

                    }
                }
        }
        return result;
    }

    private String impl2Columns() {
                //"                        serviceWork.id,\n" +
                //"                        serviceWork.coopName,\n" +
                //"                        serviceWork.coopComment,\n" +
                //"                        serviceWork.coopWorkName,\n" +
                //"                        serviceWork.wtag1,\n" +
                //"                        serviceWork.wtag2,\n" +
                //"                        serviceWork.startWorkDate,\n" +
                //"                        serviceWork.endWorkDate,\n" +
                //"                        serviceWork.searchTag1,\n" +
                //"                        serviceWork.searchTag2,\n" +
                //"                        serviceWork.searchTag3,\n" +
                //"                        serviceWork.searchTag4,\n" +
                //"                        serviceWork.searchTag5,\n" +
                //"                        serviceWork.viewCount   ,\n" +
                //"                        serviceWork.likeCount   ,\n" +
                //"                        serviceWork.starCount  ,\n" +
                //"                        serviceWork.starAll   ,\n" +
                //"                        serviceWork.starPairMan  ,\n" +
                //"                        serviceWork.isDel,\n" +
                //"                        serviceWork.modifiedDate,\n" +
                //"                        serviceWork.createdDate,\n" +
                //"                        serviceWork.serviceWorkStr\n" +
        String result ="";
        for(int i=0; i< colNames.length; i++){
            if(colLongs!=null){
                for(int j=0; j<colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "                        "+toLowerFirst(domainStr)+"."+colNames[i]+",\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "                        "+toLowerFirst(domainStr)+"."+colNames[i]+",\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j<colDates.length; j++){
                    if(colNames[i].equals(colDates[j])){
                        result += "                        "+toLowerFirst(domainStr)+"."+colNames[i]+",\n";
                    }
                }
            }
        }
        if(foreignCols!=null){
            for(int j=0; j<foreignCols.length; j++){
                    result += "                        "+toLowerFirst(domainStr)+"."+toLowerFirst(foreignCols[j])+",\n";
            }
        }
        result = result.substring(0, result.length()-2);
        return result;
    }

    public String makeRepositoryImpl() {
        String result = "";
        String importStaticQStr = "";
        if(rootPackageStr!=null){
            importStaticQStr = "import static " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+".Q"+toUpperFirst(domainStr)+"."+toLowerFirst(domainStr)+";\n";
        }else{

        }
        result =
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
                        importStaticQStr +
                implImportStatic()+

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

        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr =  "" +"package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }
        return result;
    }

    private String implImportStatic() {
        //  "import static org.example.domain.....Q"+toUpperFirst(domainStr)+"."+toLowerFirst(domainStr)+";\n" +
        String result = "";
        if(foreignCols!=null){
            for(int i=0;i<foreignCols.length;i++){
                result += "import static "+rootPackageStr+".domain."+toAllLowerCase(foreignCols[i])+".Q"+toUpperFirst(foreignCols[i])+"."+toLowerFirst(foreignCols[i])+";\n";
                // import static org.example.domain.user.QUser.user;
            }

        }
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
        result =
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
        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr =  "" +"package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }
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
                "    <p>Move table, <a th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\">"+domainStr+"</a></p>\n" +
                "<!-- Add, link...-->\n" +
                "    <!-- <p>Move talbe, <a th:href=\"@{/administer/instanceurl/[domain]}\">[domain]</a></p> -->\n" +
                "<br/>\n" +
                "<br/>\n" +
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
                "        .font-12{\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "        ul>li{\n" +
                "            font-size:12px;\n" +
                "        }    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <div style=\"padding-bottom:5px;\" class=\"d-flex align-items-center\">\n" +
                "    <a class=\"btn btn-sm btn-link\" style=\"padding-top:9px;\" th:href=\"@{"+thymleafInitUrl+"}\"><i class=\"bi bi-chevron-double-left fa-3x\"></i></a><a th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\">\n" +
                "    <span style=\"font-size:25px;\" >"+toUpperFirst(domainStr)+" 테이블</span></a>\n" +
                "    </div>\n" +
                "    <br/>\n" +
                "    <form name=\"search_form\" th:action=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\" method=\"get\" role=\"form\" th:object=\"${condition}\" class=\"d-flex justify-content-evenly\">\n" +
                "        <table style=\"width:670px;\" class=\"border border-5 d-flex justify-content-evenly caption-top\">\n" +
                "\n" +
                "            <tbody>\n" +
                "            <tr class=\" d-flex align-items-center\">\n" +
                "                <td class=\"font-12\" style=\"width:40px;\">키워드</td>\n" +
                "                <td class=\"font-12 d-flex align-items-center\" style=\"width:190px;\">\n" +
                "                    <select id=\"field\" name=\"field\" style=\"width:80px;\" title=\"키워드 선택\" autocomplete=\"on\" >\n" +
                //"                        <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n" +
                optionStr()+
               // "                        <option th:value=\"all\" th:selected=\"${#strings.trim(param.field) eq 'addrFull'}\">주소검색</option>\n" +
               // "                        <option th:value=\"zipcode\" th:selected=\"${#strings.trim(param.field) eq 'zipCode'}\">우편번호</option>\n" +
               // "                        <option th:value=\"addr1\" th:selected=\"${#strings.trim(param.field) eq 'addr1'}\">도로명주소</option>\n" +
               // "                        <option th:value=\"addr2\" th:selected=\"${#strings.trim(param.field) eq 'addr2'}\">상세주소</option>\n" +
                "                    </select>\n" +
                "                    &nbsp;\n" +
                "                    <input class=\"font-12\" style=\"width:110px;\" type=\"text\" title=\"키워드\" placeholder=\"키워드명 입력\" name=\"s\" th:field=\"*{s}\" autocomplete=\"on\" >\n" +
                "                </td>\n" +
                "                <td class=\"font-12\" style=\"width:70px;\">&nbsp;&nbsp;&nbsp;&nbsp;등록일자</td>\n" +
                "                <td class=\"font-12\" style=\"width:250px;\">&nbsp;\n" +
                "                    <input type=\"date\" placeholder=\"시작일\" class=\"ico_date\" name=\"sdate\"\n" +
                "                           id=\"datepicker1\" th:field=\"*{sdate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                    <span class=\"hypen\">~</span>\n" +
                "                    <input type=\"date\" placeholder=\"종료일\" class=\"ico_date\" name=\"edate\"\n" +
                "                           id=\"datepicker2\" th:field=\"*{edate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                </td>\n" +
                "                <td style=\"width:50px;\">\n" +
                "                    &nbsp;<button class=\"btn btn-success btn-sm font-12\">검색</button>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    &nbsp;<a class=\"btn btn-sm btn-primary font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert}\">쓰기</a>&nbsp;\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "\n" +
                "    </form>\n" +
                "    <br/>\n" +
                "\n" +
                "    <table class=\"table table-sm\" style=\"font-size: 12px;margin-left:auto; margin-right:auto;\">\n" +
                "        <thead>\n" +
                "        <tr>\n" +
                tableColIndex()+
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "        <tr th:each=\"board : ${boards}\">\n" +
               tableColIndex2()+
                "            <td th:text=\"${board.isDel}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update(id=${board.id})}\"  >수정</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/delete(id=${board.id})}\"  >삭제</a></td>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    <!-- 페이징 -->\n" +
                "    <div th:if=\"${!boards.isEmpty()}\">\n" +
                "        <!-- 전역 변수 선언 -->\n" +
                "        <nav\n" +
                "                th:with=\"\n" +
                "                pageNumber = ${boards.pageable.pageNumber},\n" +
                "                pageSize = ${boards.pageable.pageSize},\n" +
                "                totalPages = ${boards.totalPages},\n" +
                "                startPage = ${T(java.lang.Math).floor(pageNumber / pageSize) * pageSize + 1},\n" +
                "                tempEndPage = ${startPage + pageSize - 1},\n" +
                "                endPage = (${tempEndPage < totalPages ? tempEndPage : totalPages})\"\n" +
                "                aria-label=\"Page navigation\"\n" +
                "        >\n" +
                "            <ul class=\"pagination \" style=\"justify-content:center;\">\n" +
                "                <!-- 처음으로 이동 -->\n" +
                "                <li th:classappend=\"${pageNumber < pageSize} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=0)}\">\n" +
                "                        <span>&laquo;</span>\n" +
                "                        <span class=\"sr-only\">First</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 이전으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.first} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.first} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber - 1})}\" aria-label=\"Previous\">\n" +
                "                        <span aria-hidden=\"true\">&lt;</span>\n" +
                "                        <span class=\"sr-only\">Previous</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 특정 페이지로 이동 -->\n" +
                "                <li th:each=\"page: ${#numbers.sequence(startPage, endPage)}\" th:classappend=\"${page == pageNumber + 1} ? 'active'\" class=\"page-item\">\n" +
                "                    <a th:text=\"${page}\" class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${page - 1})}\"></a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 다음으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.last} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.last} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber + 1})}\" aria-label=\"Next\">\n" +
                "                        <span aria-hidden=\"true\">&gt;</span>\n" +
                "                        <span class=\"sr-only\">Next</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 마지막으로 이동 -->\n" +
                "                <li th:classappend=\" ${T(java.lang.Math).floor(totalPages / pageSize) * pageSize - 1 <= startPage} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${totalPages - 1})}\">\n" +
                "                        <span>&raquo;</span>\n" +
                "                        <span class=\"sr-only\">Last</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </nav>\n" +
                "    </div> <!-- /페이징 -->\n" +
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
                "    <!--JQuery-->\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return result;
    }

    private String optionStr() {
        String result ="";
        //"                        <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n"
        for(int i=0; i<colNames.length;i++){
            if(colLongs!=null){
                for(int j=0; j<colLongs.length;j++){
                    if(colLongs[j].equals(colNames[i])){
                        result += "                        <option th:value=\""+colNames[i]+"\" th:selected=\"${#strings.trim(param.field) eq '"+colNames[i]+"'}\">"+colNames[i]+"</option>\n";
                    }
                }

            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length; j++){
                    if(colStrs[j].equals(colNames[i])){
                        result += "                        <option th:value=\""+colNames[i]+"\" th:selected=\"${#strings.trim(param.field) eq '"+colNames[i]+"'}\">"+colNames[i]+"</option>\n";
                    }
                }

            }
            if(colDates!=null){
                for(int j=0; j<colDates.length; j++){
                    if(colDates[j].equals(colNames[i])){
                        result += "                        <option th:value=\""+colNames[i]+"\" th:selected=\"${#strings.trim(param.field) eq '"+colNames[i]+"'}\">"+colNames[i]+"</option>\n";
                    }
                }

            }
        }
        if(foreignCols!=null){
            for(int i=0; i<foreignCols.length; i++){
                result += "                        <option th:value=\""+toLowerFirst(foreignCols[i])+"Id\" th:selected=\"${#strings.trim(param.field) eq '"+toLowerFirst(foreignCols[i])+"Id'}\">"+toLowerFirst(foreignCols[i])+"Id</option>\n";
            }
        }
        return result;
    }

    private String tableColIndex2() {
        String result ="";
       // "            <td th:text=\"${board.id}\"></td>\n" +
       //         "            <td th:text=\"${board.zipCode}\"></td>\n" +
       //         "            <td th:text=\"${board.addr1}\"></td>\n" +
       //         "            <td th:text=\"${board.addr2}\"></td>\n" +
       //         "            <td th:text=\"${board.addrFull}\"></td>\n" +
        for(int i=0; i<colNames.length; i++){
            if(colNames[i].equals("isDel")|| colNames[i].equals("modifiedDate") || colNames[i].equals("createdDate")){
                continue;
            }else{
                result += "            <td th:text=\"${board."+colNames[i]+"}\"></td>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i<foreignCols.length; i++){
                result += "            <td th:text=\"${board."+toLowerFirst(foreignCols[i])+"?.id}\"></td>\n";
            }
        }
        return result;
    }

    private String tableColIndex() {
        String result = "";
        //"            <th>id</th> <th>zipcode</th> <th>addr1</th> <th>addr2</th> <th>addrFull</th>            <th>삭제여부</th>\n" +
        //        "            <th>수정일</th>\n" +
        //        "            <th>생성일</th>\n" +
        //        "            <th></th>\n" +
        //        "            <th></th>\n" +
        for(int i=0; i< colNames.length;i++){
            if(colNames[i].equals("isDel") || colNames[i].equals("modifiedDate") || colNames[i].equals("createdDate")){
                continue;
            }else{
                result += "            <th>"+colNames[i]+"</th>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i< foreignCols.length;i++){
                result += "            <th>"+toAllLowerCase(toUpp_Upp(foreignCols[i]+"_id"))+"</th>\n";

            }
        }
        result += "            <th>삭제여부</th>\n" +
                "            <th>수정일</th>\n" +
                "            <th>생성일</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n";

        return result;
    }

    public String makeSearchCondition() {
        String result =
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
        String packageStr = null;
        if(rootPackageStr!=null ) {
            packageStr = "" +"package" + " " + rootPackageStr + ".domain."+toAllLowerCase(domainStr)+";\n";
            result = packageStr + result;
        }

        return result;
    }

    public String makeInsert() {
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
                "        .font-12{\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "        ul>li{\n" +
                "            font-size:12px;\n" +
                "        }    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <div style=\"padding-bottom:5px;\" class=\"d-flex align-items-center\">\n" +
                "        <a class=\"btn btn-sm btn-link\" style=\"padding-top:9px;\" th:href=\"@{"+thymleafInitUrl+"}\"><i class=\"bi bi-chevron-double-left fa-3x\"></i></a><a th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\">\n" +
                "        <span style=\"font-size:25px;\" >"+toUpperFirst(domainStr)+" 테이블</span></a>\n" +
                "    </div>\n" +
                "    <br/>\n" +
                "    <form name=\"search_form\" th:action=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\" method=\"get\" role=\"form\" th:object=\"${condition}\" class=\"d-flex justify-content-evenly\">\n" +
                "        <table style=\"width:670px;\" class=\"border border-5 d-flex justify-content-evenly caption-top\">\n" +
                "\n" +
                "            <tbody>\n" +
                "            <tr class=\" d-flex align-items-center\">\n" +
                "                <td class=\"font-12\" style=\"width:40px;\">키워드</td>\n" +
                "                <td class=\"font-12 d-flex align-items-center\" style=\"width:190px;\">\n" +
                "                    <select id=\"field\" name=\"field\" style=\"width:80px;\" title=\"키워드 선택\" autocomplete=\"on\" >\n" +
                "                        <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n" +
                "                    </select>\n" +
                "                    &nbsp;\n" +
                "                    <input class=\"font-12\" style=\"width:110px;\" type=\"text\" title=\"키워드\" placeholder=\"키워드명 입력\" name=\"s\" th:field=\"*{s}\" autocomplete=\"on\" >\n" +
                "                </td>\n" +
                "                <td class=\"font-12\" style=\"width:70px;\">&nbsp;&nbsp;&nbsp;&nbsp;등록일자</td>\n" +
                "                <td class=\"font-12\" style=\"width:250px;\">&nbsp;\n" +
                "                    <input type=\"date\" placeholder=\"시작일\" class=\"ico_date\" name=\"sdate\"\n" +
                "                           id=\"datepicker1\" th:field=\"*{sdate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                    <span class=\"hypen\">~</span>\n" +
                "                    <input type=\"date\" placeholder=\"종료일\" class=\"ico_date\" name=\"edate\"\n" +
                "                           id=\"datepicker2\" th:field=\"*{edate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                </td>\n" +
                "                <td style=\"width:50px;\">\n" +
                "                    &nbsp;<button class=\"btn btn-success btn-sm font-12\">검색</button>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    &nbsp;<a class=\"btn btn-sm btn-primary font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert}\">쓰기</a>&nbsp;\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "\n" +
                "    </form>\n" +
                "    <br/>\n" +
                "\n" +
                "    <table class=\"table table-sm\" style=\"font-size: 12px;\">\n" +
                "        <thead>\n" +
                "        <tr>\n"
                +insertColumn()+
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "\n" +
                "\n" +
                "        <form  th:action=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert_2}\" th:object=\"${userForm}\" method=\"post\" >\n" +
                "\n" +
                "            <tr>\n" +
               insert2Column()+
                "                <td><input type=\"text\" style=\"width:30px;\" th:field=\"*{isDel}\" th:value=\"N\" readOnly  /></td>\n" +
                "                <td th:text=\"${#temporals.format(userForm.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "                <td th:text=\"${#temporals.format(userForm.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "                <td><button type=\"submit\" class=\"btn btn-sm btn-primary font-12\">입력</button></td>\n" +
                "                <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/}\">취소</a></td>\n" +
                "            </tr>\n" +
                "        </form>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td colspan=\"14\">\n" +
                "                <table>\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td text-align=\"right\">\n" +
                "                            <p class=\"font-12\">&#8251;&nbsp;[Writing notice. ... ]</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr th:each=\"board : ${boards}\">\n" +
                insert3Column()+
                "            <td th:text=\"${board.isDel}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update(id=${board.id})}\"  >수정</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/delete(id=${board.id})}\"  >삭제</a></td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    <!-- 페이징 -->\n" +
                "    <div th:if=\"${!boards.isEmpty()}\">\n" +
                "        <!-- 전역 변수 선언 -->\n" +
                "        <nav\n" +
                "                th:with=\"\n" +
                "                pageNumber = ${boards.pageable.pageNumber},\n" +
                "                pageSize = ${boards.pageable.pageSize},\n" +
                "                totalPages = ${boards.totalPages},\n" +
                "                startPage = ${T(java.lang.Math).floor(pageNumber / pageSize) * pageSize + 1},\n" +
                "                tempEndPage = ${startPage + pageSize - 1},\n" +
                "                endPage = (${tempEndPage < totalPages ? tempEndPage : totalPages})\"\n" +
                "                aria-label=\"Page navigation\"\n" +
                "        >\n" +
                "            <ul class=\"pagination \" style=\"justify-content:center;\">\n" +
                "                <!-- 처음으로 이동 -->\n" +
                "                <li th:classappend=\"${pageNumber < pageSize} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=0)}\">\n" +
                "                        <span>&laquo;</span>\n" +
                "                        <span class=\"sr-only\">First</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 이전으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.first} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.first} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber - 1})}\" aria-label=\"Previous\">\n" +
                "                        <span aria-hidden=\"true\">&lt;</span>\n" +
                "                        <span class=\"sr-only\">Previous</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 특정 페이지로 이동 -->\n" +
                "                <li th:each=\"page: ${#numbers.sequence(startPage, endPage)}\" th:classappend=\"${page == pageNumber + 1} ? 'active'\" class=\"page-item\">\n" +
                "                    <a th:text=\"${page}\" class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${page - 1})}\"></a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 다음으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.last} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.last} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber + 1})}\" aria-label=\"Next\">\n" +
                "                        <span aria-hidden=\"true\">&gt;</span>\n" +
                "                        <span class=\"sr-only\">Next</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 마지막으로 이동 -->\n" +
                "                <li th:classappend=\" ${T(java.lang.Math).floor(totalPages / pageSize) * pageSize - 1 <= startPage} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${totalPages - 1})}\">\n" +
                "                        <span>&raquo;</span>\n" +
                "                        <span class=\"sr-only\">Last</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </nav>\n" +
                "    </div> <!-- /페이징 -->\n" +
                "<br/><br/>\n" +
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
                "    <!--JQuery-->\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return result;
    }

    private String insert3Column() {
        String result = "";
        //"            <td th:text=\"${board.id}\"></td>\n" +
        //        "            <td th:text=\"${board.zipCode}\"></td>\n" +
        //        "            <td th:text=\"${board.addr1}\"></td>\n" +
        //        "            <td th:text=\"${board.addr2}\"></td>\n" +
        //        "            <td th:text=\"${board.addrFull}\"></td>\n" +
        //Add style. 스타일 추가.
        result= "            <td style=\"width:50px;\" th:text=\"${board."+colNames[0]+"}\"></td>\n";

        for(int i=1; i<colNames.length;i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("createdDate")||colNames[i].equals("modifiedDate")){

            }else{
                result += "            <td th:text=\"${board."+colNames[i]+"}\"></td>\n";
            }
        }
        if(foreignCols!=null) {
            for (int i = 0; i < foreignCols.length; i++) {
                result += "            <td style=\"width:50px;\" th:text=\"${board." + toLowerFirst(foreignCols[i])  + "?.id }\"></td>\n";
            }
        }
        return result;
    }

    private String insert2Column() {
        String result = "";
        //"                <td><input type=\"text\" th:field=\"*{id}\" size=\"2\" readonly/></td>\n" +
        //        "                <td><input type=\"text\" th:field=\"*{zipCode}\" /></td>\n" +
        //        "                <td><input type=\"text\" th:field=\"*{addr1}\" /></td>\n" +
        //        "                <td><input type=\"text\" th:field=\"*{addr2}\" /></td>\n" +
        //        "                <td><input type=\"text\" th:field=\"*{addrFull}\" /></td>\n" +
        result = "                <td><input type=\"text\" th:field=\"*{"+colNames[0]+"}\" style=\"width:50px;\" readonly/></td>\n";
        for(int i=1; i<colNames.length;i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("createdDate")||colNames[i].equals("modifiedDate")){

            }else{
                result += "                <td><input type=\"text\" th:field=\"*{"+colNames[i]+"}\" /></td>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i<foreignCols.length;i++){
                result += "                <td><input type=\"text\" th:field=\"${userForm."+toLowerFirst(foreignCols[i])+"Id}\" style=\"width:50px;\" /></td>\n";
            }
        }

        return result;
    }

    private String insertColumn() {
        String result = "";
        //"            <th>id</th>\n" +
        //        "            <th>zipcode</th>\n" +
        //        "            <th>addr1</th>\n" +
        //        "            <th>addr2</th>\n" +
        //        "            <th>addrFull</th>\n" +
        //        "            <th>isDel</th>\n" +
        //        "            <th>수정일</th>\n" +
        //        "            <th>생성일</th>\n" +
        //        "            <th></th>\n" +
        //        "            <th></th>\n" +
        for (int i=0; i<colNames.length; i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("modifiedDate")||colNames[i].equals("createdDate")){

            }else{
                result += "            <th>"+colNames[i]+"</th>\n";
            }
        }
        if(foreignCols !=null){
            for (int i=0; i<foreignCols.length; i++){
                result += "            <th>"+toAllLowerCase(toUpp_Upp(foreignCols[i]+"_id"))+"</th>\n";
            }
        }
        result += "          <th>삭제여부</th>\n" +
                "            <th>수정일</th>\n" +
                "            <th>생성일</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n";

        return result;
    }

    public String makeUpdate() {
        String result ="";
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
                "        .font-12{\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "        ul>li{\n" +
                "            font-size:12px;\n" +
                "        }    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <div style=\"padding-bottom:5px;\" class=\"d-flex align-items-center\">\n" +
                "        <a class=\"btn btn-sm btn-link\" style=\"padding-top:9px;\" th:href=\"@{"+thymleafInitUrl+"}\"><i class=\"bi bi-chevron-double-left fa-3x\"></i></a><a th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\">\n" +
                "        <span style=\"font-size:25px;\" >"+toUpperFirst(domainStr)+" 테이블</span></a>\n" +
                "    </div>\n" +
                "    <br/>\n" +
                "    <form name=\"search_form\" th:action=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"}\" method=\"get\" role=\"form\" th:object=\"${condition}\" class=\"d-flex justify-content-evenly\">\n" +
                "        <table style=\"width:670px;\" class=\"border border-5 d-flex justify-content-evenly caption-top\">\n" +
                "\n" +
                "            <tbody>\n" +
                "            <tr class=\" d-flex align-items-center\">\n" +
                "                <td class=\"font-12\" style=\"width:40px;\">키워드</td>\n" +
                "                <td class=\"font-12 d-flex align-items-center\" style=\"width:190px;\">\n" +
                "                    <select id=\"field\" name=\"field\" style=\"width:80px;\" title=\"키워드 선택\" autocomplete=\"on\" >\n" +
                "                        <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n" +
                "                    </select>\n" +
                "                    &nbsp;\n" +
                "                    <input class=\"font-12\" style=\"width:110px;\" type=\"text\" title=\"키워드\" placeholder=\"키워드명 입력\" name=\"s\" th:field=\"*{s}\" autocomplete=\"on\" >\n" +
                "                </td>\n" +
                "                <td class=\"font-12\" style=\"width:70px;\">&nbsp;&nbsp;&nbsp;&nbsp;등록일자</td>\n" +
                "                <td class=\"font-12\" style=\"width:250px;\">&nbsp;\n" +
                "                    <input type=\"date\" placeholder=\"시작일\" class=\"ico_date\" name=\"sdate\"\n" +
                "                           id=\"datepicker1\" th:field=\"*{sdate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                    <span class=\"hypen\">~</span>\n" +
                "                    <input type=\"date\" placeholder=\"종료일\" class=\"ico_date\" name=\"edate\"\n" +
                "                           id=\"datepicker2\" th:field=\"*{edate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                </td>\n" +
                "                <td style=\"width:50px;\">\n" +
                "                    &nbsp;<button class=\"btn btn-success btn-sm font-12\">검색</button>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    &nbsp;<a class=\"btn btn-sm btn-primary font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert}\">쓰기</a>&nbsp;\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "\n" +
                "    </form>\n" +
                "    <br/>\n" +
                "\n" +
                "    <table class=\"table table-sm\" style=\"font-size: 12px;\">\n" +
                "        <thead>\n" +
                "        <tr>\n" +
               updateColumn()+
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "\n" +
                "\n" +
                "        <form  th:action=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update_2}\" th:object=\"${userForm}\" method=\"post\" >\n" +
                "\n" +
                "            <tr>\n" +
                updateColumn2()+
                "                <td><input type=\"text\" style=\"width:30px;\" th:field=\"*{isDel}\" th:value=\"N\"/></td>\n" +
                "                <td th:text=\"${#temporals.format(userForm.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "                <td th:text=\"${#temporals.format(userForm.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "                <td><button type=\"submit\" class=\"btn btn-sm btn-primary font-12\">수정</button></td>\n" +
                "                <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/}\">취소</a></td>\n" +
                "            </tr>\n" +
                "        </form>\n" +
                "\n" +
                "        <tr>\n" +
                "            <td colspan=\"14\">\n" +
                "                <table>\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td text-align=\"right\">\n" +
                "                            <p class=\"font-12\">&#8251;&nbsp;[Writing notice. ... ]</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        <tr th:each=\"board : ${boards}\">\n" +
                updateColumn3()+
                "            <td th:text=\"${board.isDel}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update(id=${board.id})}\"  >수정</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/delete(id=${board.id})}\"  >삭제</a></td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    <!-- 페이징 -->\n" +
                "    <div th:if=\"${!boards.isEmpty()}\">\n" +
                "        <!-- 전역 변수 선언 -->\n" +
                "        <nav\n" +
                "                th:with=\"\n" +
                "                pageNumber = ${boards.pageable.pageNumber},\n" +
                "                pageSize = ${boards.pageable.pageSize},\n" +
                "                totalPages = ${boards.totalPages},\n" +
                "                startPage = ${T(java.lang.Math).floor(pageNumber / pageSize) * pageSize + 1},\n" +
                "                tempEndPage = ${startPage + pageSize - 1},\n" +
                "                endPage = (${tempEndPage < totalPages ? tempEndPage : totalPages})\"\n" +
                "                aria-label=\"Page navigation\"\n" +
                "        >\n" +
                "            <ul class=\"pagination \" style=\"justify-content:center;\">\n" +
                "                <!-- 처음으로 이동 -->\n" +
                "                <li th:classappend=\"${pageNumber < pageSize} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=0)}\">\n" +
                "                        <span>&laquo;</span>\n" +
                "                        <span class=\"sr-only\">First</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 이전으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.first} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.first} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber - 1})}\" aria-label=\"Previous\">\n" +
                "                        <span aria-hidden=\"true\">&lt;</span>\n" +
                "                        <span class=\"sr-only\">Previous</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 특정 페이지로 이동 -->\n" +
                "                <li th:each=\"page: ${#numbers.sequence(startPage, endPage)}\" th:classappend=\"${page == pageNumber + 1} ? 'active'\" class=\"page-item\">\n" +
                "                    <a th:text=\"${page}\" class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${page - 1})}\"></a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 다음으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.last} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.last} ? '#' : @{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${pageNumber + 1})}\" aria-label=\"Next\">\n" +
                "                        <span aria-hidden=\"true\">&gt;</span>\n" +
                "                        <span class=\"sr-only\">Next</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 마지막으로 이동 -->\n" +
                "                <li th:classappend=\" ${T(java.lang.Math).floor(totalPages / pageSize) * pageSize - 1 <= startPage} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"(page=${totalPages - 1})}\">\n" +
                "                        <span>&raquo;</span>\n" +
                "                        <span class=\"sr-only\">Last</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </nav>\n" +
                "    </div> <!-- /페이징 -->\n" +
                "<br/><br/>\n" +
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
                "    <!--JQuery-->\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return result;
    }

    private String updateColumn3() {
       //       "            <td style=\"width:50px;\" th:text=\"${id}\"></td>\n" +
       //       "            <td style=\"width:50px;\" th:text=\"${board.fvv?.id }\"></td>\n" +

        //Add style. 스타일 추가.
        String result= "            <td style=\"width:50px;\" th:text=\"${board."+colNames[0]+"}\"></td>\n";
        for(int i=1; i< colNames.length;i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("modifiedDate")||colNames[i].equals("createdDate")){

            }else{
                result += "            <td th:text=\"${board."+colNames[i]+"}\"></td>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i< foreignCols.length;i++){
                result += "            <td style=\"width:50px;\" th:text=\"${board."+toLowerFirst(foreignCols[i])+"?.id }\"></td>\n";
            }
        }
        return result;
    }

    private String updateColumn2() {
        //        "                <td><input type=\"text\" th:field=\"*{id}\" style=\"width:50px;\" readonly/></td>\n" +
        //        "                <td><input type=\"text\" th:field=\"{userForm.fvvId}\" style=\"width:50px;\" /></td>\n" +
        String result ="                <td><input type=\"text\" th:field=\"*{id}\" style=\"width:50px;\" readonly/></td>\n";

        for(int i=1; i<colNames.length;i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("modifiedDate")||colNames[i].equals("createdDate")){

            }else{
                result += "                <td><input type=\"text\" th:field=\"*{"+colNames[i]+"}\"/></td>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i<foreignCols.length;i++){
                result += "                <td><input type=\"text\" th:field=\"${userForm."+toLowerFirst(foreignCols[i])+"Id}\" style=\"width:50px;\" /></td>\n";
            }
        }

        return result;
    }

    private String updateColumn() {
        //"            <th>id</th>\n" +
        //        "            <th>fvv_id</th>\n" +
        //        "            <th>삭제여부</th>\n" +
        //        "            <th>수정일</th>\n" +
        //        "            <th>생성일</th>\n" +
        //        "            <th></th>\n" +
        //        "            <th></th>\n" +
        String result ="";
        for(int i=0; i< colNames.length; i++){
            if(colNames[i].equals("isDel")||colNames[i].equals("modifiedDate")||colNames[i].equals("createdDate")){
                continue;
            }else{
                result += "            <th>"+colNames[i]+"</th>\n";
            }
        }
        if(foreignCols!=null){
            for(int i=0; i< foreignCols.length; i++){
                result += "            <th>"+toAllLowerCase(toUpp_Upp(foreignCols[i]+"_id"))+"</th>\n";
            }
        }
        result += "            <th>삭제여부</th>\n" +
                "            <th>수정일</th>\n" +
                "            <th>생성일</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n";

        return result;
    }

    public String makeRootIndexController() {
        String result =  "" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "\n" +
                "@Controller\n" +
                "public class InstanceUrlController {\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrl+"\")\n" +
                "    public String index(){\n" +
                "        //firstInstance index의 처음 위치.\n" +
                "        return \"firstinstance/index\";\n" +
                "    }\n" +
                "\n" +
                "    // 개발중, 테스트용 url연결 만듬.\n" +
                "    @GetMapping(\"/\")\n" +
                "    public String index2(){\n" +
                "\n" +
                "        return \"redirect:"+thymleafInitUrl+"\";\n" +
                "    }\n" +
                "\n" +
                "}";

        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr = rootPackageStr+".firstinstance.controller.firstinstanceurl;\n";
            result = "package "+packageStr + result;
        }

        return result;
    }

    public String makeIndexController() {
        String result = "";

        String importForeignStr = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                importForeignStr += "import "+rootPackageStr+".domain."+toAllLowerCase(foreignCols[i])+"."+toUpperFirst(foreignCols[i])+";\n";
                importForeignStr += "import "+rootPackageStr+".domain."+toAllLowerCase(foreignCols[i])+"."+toUpperFirst(foreignCols[i])+"Service;\n";
            }
        }

        result ="" +
                "import lombok.RequiredArgsConstructor;\n" +
                importForeignStr +
                "// import Service, Entity, ApiDtoForm.\n"+
                "import "+rootPackageStr+".domain."+toAllLowerCase(domainStr)+"."+domainStr+";\n" +
                "import "+rootPackageStr+".domain."+toAllLowerCase(domainStr)+"."+domainStr+"ApiDto;\n" +
                "import "+rootPackageStr+".domain."+toAllLowerCase(domainStr)+"."+domainStr+"SearchCondition;\n" +
                "import "+rootPackageStr+".domain."+toAllLowerCase(domainStr)+"."+domainStr+"Service;\n" +
                "import "+rootPackageStr+".firstinstance.controller.firstinstanceurl.form."+domainStr+"ApiDtoForm;\n" +
                "\n" +
                "import org.springframework.data.domain.Page;\n" +
                "import org.springframework.data.domain.Pageable;\n" +
                "import org.springframework.data.web.PageableDefault;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.ui.Model;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestParam;\n" +
                "import java.time.format.DateTimeFormatter;\n" +
                "\n" +
                "import java.time.LocalDateTime;\n" +
                "\n" +
                "@RequiredArgsConstructor\n" +
                "@Controller\n" +
                "public class InstanceUrl"+toUpperFirst(domainStr)+"Controller {\n" +
                "\n" +
                "    private final "+toUpperFirst(domainStr)+"Service "+toLowerFirst(domainStr)+"Service;\n" +
                pFinalForignCol()+
                "\n" +
                "    @GetMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"\")\n" +
                "    public String index(Model model, "+toUpperFirst(domainStr)+"SearchCondition condition,\n" +
                "                        @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                        @PageableDefault(size= 10)Pageable pageable) throws Exception {\n" +
                "\n" +
                "        Page<"+toUpperFirst(domainStr)+"ApiDto> boards = "+toLowerFirst(domainStr)+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        return \"firstinstance/"+toLowerFirst(domainStr)+"/index\";\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\")\n" +
                "    public String insert(Model model, "+toUpperFirst(domainStr)+"SearchCondition condition,\n" +
                "                         @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                         @PageableDefault(size= 10)Pageable pageable) throws Exception{\n" +
                "\n" +
                "        Page<"+toUpperFirst(domainStr)+"ApiDto> boards = "+toLowerFirst(domainStr)+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        "+toUpperFirst(domainStr)+"ApiDtoForm userForm = new "+toUpperFirst(domainStr)+"ApiDtoForm();\n" +
                "        model.addAttribute(\"userForm\",userForm);\n" +
                "\n" +
                "        return \"firstinstance/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert_2\")\n" +
                "    public String insert_2(Model model, "+toUpperFirst(domainStr)+"ApiDtoForm userForm){\n" +
                "\n" +
                "        "+toUpperFirst(domainStr)+" "+toLowerFirst(domainStr)+" = null;\n" +
                 insert2ForeginCols()+
                "\n" +
                "        try {\n" +
                "            "+toLowerFirst(domainStr)+" = new "+toUpperFirst(domainStr)+"();\n" +
                //insert2ColumnCols()+
                insert2ColumnCols2()+
                insert2ForeginCols2()+
                "            "+toLowerFirst(domainStr)+".setModifiedDate(LocalDateTime.now());\n" +
                "            "+toLowerFirst(domainStr)+".setCreatedDate(LocalDateTime.now());\n" +
                "            "+toLowerFirst(domainStr)+".setIsDel(\"N\");\n" +
                "\n" +
                "            "+toLowerFirst(domainStr)+"Service.save("+toLowerFirst(domainStr)+");\n" +
                "\n" +
                "        } catch (Exception e) {\n" +
                "        return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "        }\n" +
                "        return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";}\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/delete\")\n" +
                "    public String delete(@RequestParam(value=\"id\")Long id, Model model) {\n" +
                "\n" +
                "        "+toUpperFirst(domainStr)+" "+toLowerFirst(domainStr)+" = null;\n" +
                "        try {\n" +
                "             "+toLowerFirst(domainStr)+" = "+toLowerFirst(domainStr)+"Service.findById(id);\n" +
                "        } catch (Exception e) {\n" +
                "            return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/\";\n" +
                "        }\n" +
                "\n" +
                "        "+toLowerFirst(domainStr)+".setIsDel(\"Y\");\n" +
                "        "+toLowerFirst(domainStr)+"Service.save("+toLowerFirst(domainStr)+");\n" +
                "\n" +
                "\n" +
                "        return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/\";\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update\")\n" +
                "    public String update(Model model, @RequestParam(value=\"id\")Long id, "+toUpperFirst(domainStr)+"SearchCondition condition,\n" +
                "                         @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                         @PageableDefault(size= 10)Pageable pageable) throws Exception{\n" +
                "        Page<"+toUpperFirst(domainStr)+"ApiDto> boards = "+toLowerFirst(domainStr)+"Service.searchAllV2(condition, pageable);\n" +
                "\n" +
                "\n" +
                "        model.addAttribute(\"boards\", boards);\n" +
                "        model.addAttribute(\"condition\", condition);\n" +
                "        model.addAttribute(\"page\", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.\n" +
                "\n" +
                "        "+toUpperFirst(domainStr)+"ApiDtoForm userForm = new "+toUpperFirst(domainStr)+"ApiDtoForm();\n" +
                "        "+toUpperFirst(domainStr)+" "+toLowerFirst(domainStr)+" = null;\n" +
                "\n" +
                "        try {\n" +
                "            "+toLowerFirst(domainStr)+" = "+toLowerFirst(domainStr)+"Service.findById(id);\n" +
                "        }catch(Exception e){\n" +
                "            return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "        }\n" +
                "\n" +
                "        userForm.setId("+toLowerFirst(domainStr)+".getId());\n" +
                updateSetColumn()+
                updateForeginColumn()+
                "        userForm.setCreatedDate("+toLowerFirst(domainStr)+".getCreatedDate());\n" +
                "        userForm.setModifiedDate("+toLowerFirst(domainStr)+".getModifiedDate());\n" +
                "\n" +
                "        model.addAttribute(\"userForm\",userForm);\n" +
                "        return \"firstinstance/"+toLowerFirst(domainStr)+"/update\";\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping(\""+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/update_2\")\n" +
                "    public String update_2(Model model, @RequestParam(value=\"id\")Long id,"+toUpperFirst(domainStr)+"ApiDtoForm userForm, "+toUpperFirst(domainStr)+"SearchCondition condition,\n" +
                "                           @RequestParam(value=\"page\", required=false) Integer page,\n" +
                "                           Pageable pageable) throws Exception {\n" +
                "\n" +
                "\n" +
                "        "+toUpperFirst(domainStr)+" "+toLowerFirst(domainStr)+" = null;\n" +
                update2ForeginColumn()+
                "        try{\n" +
                "            "+toLowerFirst(domainStr)+" = "+toLowerFirst(domainStr)+"Service.findById(id);\n" +
                "        }catch(Exception e){\n" +
                "            return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "        }\n" +
                "\n" +

                update2ForeginColumn2()+
                "        try{\n"+
                update2Column2()+
                "        "+toLowerFirst(domainStr)+".setModifiedDate(LocalDateTime.now());\n" +
                "\n" +
                "        "+toLowerFirst(domainStr)+"Service.save("+toLowerFirst(domainStr)+");\n" +
                "        }catch(Exception e){\n" +
                "            return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "        }\n" +
                "\n" +

                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "        return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n";

        String packageStr = null;
        if(rootPackageStr!=null ){
            packageStr = rootPackageStr+".firstinstance.controller.firstinstanceurl.domain."+toAllLowerCase(domainStr);
            result = "package "+packageStr+";\n"+result;
        }

        return result;
    }

    private String insert2ColumnCols2() {
        //DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //board2228.setTitle(userForm.getTitle());
        //if(userForm.getAdate() != null && !userForm.getAdate().equals("")){
        //    board2228.setAdate(LocalDateTime.parse(userForm.getAdate(),stdFormat));
        //}

        String result = "";
        result += "        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\");\n";
        System.out.println("colNames.length : " + colNames.length);
        System.out.println("colDates.length : " + colDates.length);
        for(int i=1; i<colNames.length;i++){
            if(colLongs!=null) {
                for(int j=0; j<colLongs.length;j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "        "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                    }
                }
            }
            if(colStrs!=null) {
                if(!colNames.equals("isDel")){
                    for(int j=0; j<colStrs.length;j++){
                        if(colNames[i].equals(colStrs[j])){
                            result += "        "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                        }
                    }
                }

            }
            if(colDates!=null) {
                    for(int j=0; j<colDates.length;j++){
                        if(colDates[j].equals("createdDate") || colDates[j].equals("modifiedDate")){
                            continue;
                        }else{
                            if(colNames[i].equals(colDates[j])){
                                result += "        if(userForm.get"+toUpperFirst(colNames[i])+"()!=null && !userForm.get"+toUpperFirst(colNames[i])+"().equals(\"\")){\n";
                                result += "            "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(LocalDateTime.parse(userForm.get"+toUpperFirst(colNames[i])+"(),stdFormat));\n";
                                result += "        }\n";
                            }
                        }

                    }
            }

        }
        return result;
    }

    private String update2Column2() {
        //addressStr.setZipCode(userForm.getZipCode());
        //addressStr.setAddr1(userForm.getAddr1());
        //addressStr.setAddr2(userForm.getAddr2());
        //addressStr.setAddrFull(addressStr.getZipCode() + " " + addressStr.getAddr1() + " " + addressStr.getAddr2());
        //addressStr.setIsDel(userForm.getIsDel());
        String result = "";
        result += "        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\");\n";
        System.out.println("colNames.length : " + colNames.length);
        System.out.println("colDates.length : " + colDates.length);
        for(int i=1; i<colNames.length;i++){
            if(colLongs!=null) {
                for(int j=0; j<colLongs.length;j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "        "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                    }
                }
            }
            if(colStrs!=null) {
                if(!colNames.equals("isDel")){
                    for(int j=0; j<colStrs.length;j++){
                        if(colNames[i].equals(colStrs[j])){
                            result += "        "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                        }
                    }
                }

            }
            if(colDates!=null) {
                for(int j=0; j<colDates.length;j++){
                    if(colDates[j].equals("createdDate") || colDates[j].equals("modifiedDate")){
                        continue;
                    }else{
                        if(colNames[i].equals(colDates[j])){
                            result += "        if(userForm.get"+toUpperFirst(colNames[i])+"()!=null && !userForm.get"+toUpperFirst(colNames[i])+"().equals(\"\")){\n";
                            result += "            "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(LocalDateTime.parse(userForm.get"+toUpperFirst(colNames[i])+"(),stdFormat));\n";
                            result += "        }\n";
                        }
                    }

                }
            }

        }
        return result;

    }

    private String updateSetColumn() {
        //"userForm.setId("+toLowerFirst(domainStr)+".getId());\n" +
        //userForm.setZipCode(addressStr.getZipCode());
        //userForm.setAddr1(addressStr.getAddr1());
        //userForm.setAddr2(addressStr.getAddr2());
        //userForm.setAddrFull(addressStr.getAddrFull());
        String result = "              DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\");\n";
        for(int i=0; i<colNames.length;i++){
            if(colLongs!=null) {
                for(int j=0; j<colLongs.length;j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "        userForm.set"+toUpperFirst(colNames[i])+"("+toLowerFirst(domainStr)+".get"+toUpperFirst(colNames[i])+"());\n";
                    }
                }
            }
            if(colStrs!=null) {
                if(!colNames.equals("isDel")){
                    for(int j=0; j<colStrs.length;j++){
                        if(colNames[i].equals(colStrs[j])){
                            result += "        userForm.set"+toUpperFirst(colNames[i])+"("+toLowerFirst(domainStr)+".get"+toUpperFirst(colNames[i])+"());\n";
                        }
                    }
                }

            }
            if(colDates!=null) {
                for(int j=0; j<colDates.length;j++){
                    if(colDates[j].equals("createdDate") || colDates[j].equals("modifiedDate")){
                        continue;
                    }else{
                        if(colNames[i].equals(colDates[j])){
                            result += "        if("+toLowerFirst(domainStr)+".get"+toUpperFirst(colNames[i])+"()!=null){\n";
                            result += "        userForm.set"+toUpperFirst(colNames[i])+"("+toLowerFirst(domainStr)+".get"+toUpperFirst(colNames[i])+"().format(stdFormat));\n";
                            result += "        }\n";
                        }
                    }

                }
            }

        }

        return result;
    }

    private String update2ForeginColumn2() {
          //      "        if(userForm.getAddressStrId()!=null){\n" +
          //      "         try{\n" +
          //      "             addressStr = addressStrService.findById(userForm.getAddressStrId());\n" +
          //      "             roleUSER.setAddressStr(addressStr);\n" +
          //      "         }catch(Exception e){\n" +
          //      "             return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
          //      "         }\n" +
          //      "        }\n" +
          //      "        if(userForm.getPhoneStrId()!=null){\n" +
          //      "            try{\n" +
          //      "                phoneStr = phoneStrService.findById(userForm.getPhoneStrId());\n" +
          //      "                roleUSER.setPhoneStr(phoneStr);\n" +
          //      "            }catch(Exception e){\n" +
          //      "                return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
          //      "            }\n" +
          //      "        }\n" +
        String result = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        if(userForm.get"+toUpperFirst(foreignCols[i])+"Id()!=null){\n" +
                        "            try{\n" +
                        "                "+toLowerFirst(foreignCols[i])+" = "+toLowerFirst(foreignCols[i])+"Service.findById(userForm.get"+toUpperFirst(foreignCols[i])+"Id());\n" +
                        "                "+toLowerFirst(domainStr)+".set"+toUpperFirst(foreignCols[i])+"("+toLowerFirst(foreignCols[i])+");\n" +
                        "            }catch(Exception e){\n" +
                        "                return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                        "            }\n" +
                        "        }\n";
            }
        }
        return result;
    }

    private String update2ForeginColumn() {
        //        "        AddressStr addressStr = null;\n" +
        //        "        PhoneStr phoneStr = null;\n" +
        String result = "";
        if(foreignCols !=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        "+toUpperFirst(foreignCols[i])+" "+toLowerFirst(foreignCols[i])+" = null;\n";
            }
        }
        return result;
    }

    private String updateForeginColumn() {
        //        "        if(roleUSER.getAddressStr()!=null) {\n" +
        //        "            userForm.setAddressStrId(roleUSER.getAddressStr().getId());\n" +
        //        "        }\n" +
        //        "        if(roleUSER.getPhoneStr()!=null) {\n" +
        //        "            userForm.setPhoneStrId(roleUSER.getPhoneStr().getId());\n" +
        //        "        }\n" +
        String result = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        if("+toLowerFirst(domainStr)+".get"+toUpperFirst(foreignCols[i])+"()!=null) {\n" +
                        "            userForm.set"+toUpperFirst(foreignCols[i])+"Id("+toLowerFirst(domainStr)+".get"+toUpperFirst(foreignCols[i])+"().getId());\n" +
                        "        }\n";
            }
        }
        result += "\n";
        return result;
    }

    private String insert2ColumnCols() {
        // "             User.setColumn1(userForm.getColumn1());\n"
        String result = "              DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\");\n";
        for (int i = 0; i < colNames.length; i++) {
            if(colNames[i].equals("id") || colNames[i].equals("createdDate") || colNames[i].equals("modifiedDate")){

            }else{
                if(colDates!=null){
                    for (int j = 0; j < colDates.length; j++) {
                        if(colNames[i].equals(colDates[j])){
                            //if(userForm.getAdate() != null && !userForm.getAdate().equals("")){
                            //board03.setADate(LocalDateTime.parse(userForm.getADate(),stdFormat));
                            result += "             " +
                                    "               if(userForm.get"+toUpperFirst(colNames[i])+"() != null && !userForm.get"+toUpperFirst(colNames[i])+"().equals(\"\")){\n" +
                                    "               "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(LocalDateTime.parse(userForm.get"+toUpperFirst(colNames[i])+"(),stdFormat));\n" +
                                    "               }";
                            continue;
                        }else{
                           // result += "             "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                        }
                    }
                    result += "             "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                }else{
                    result += "             "+toLowerFirst(domainStr)+".set"+toUpperFirst(colNames[i])+"(userForm.get"+toUpperFirst(colNames[i])+"());\n";
                }

            }
        }
        return result;
    }

    private String insert2ForeginCols2() {
        //"            if(addressStr !=null){roleUSER.setAddressStr(addressStr);}\n" +
        //"            if(phoneStr !=null){roleUSER.setPhoneStr(phoneStr);}\n" +
        String result = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "            if("+toLowerFirst(foreignCols[i])+" !=null){"+toLowerFirst(domainStr)+".set"+toUpperFirst(foreignCols[i])+"("+toLowerFirst(foreignCols[i])+");}\n";
            }
        }
        return result;
    }

    private String insert2ForeginCols() {
                //"        AddressStr addressStr = null;\n" +
                //"        PhoneStr phoneStr = null;\n" +
                //"\n" +
                //"        if(userForm.getAddressStrId()!=null) {\n" +
                //"            try {\n" +
                //"                  addressStr = addressStrService.findById(userForm.getAddressStrId());\n" +
                //"            } catch (Exception e) {\n" +
                //"                return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                //"            }\n" +
                //"        }\n" +
                //"\n" +
                //"        if(userForm.getPhoneStrId()!=null){\n" +
                //"            try {\n" +
                //"                phoneStr = phoneStrService.findById(userForm.getPhoneStrId());\n" +
                //"            } catch (Exception e) {\n" +
                //"                return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                //"            }\n" +
                //"        }\n" +
        String result = "";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        "+toUpperFirst(foreignCols[i])+" "+toLowerFirst(foreignCols[i])+" = null;\n";
            }
            result += "\n";
            for (int i = 0; i < foreignCols.length; i++) {
                result += "        if(userForm.get"+toUpperFirst(foreignCols[i])+"Id()!=null) {\n" +
                        "            try {\n" +
                        "                  "+toLowerFirst(foreignCols[i])+" = "+toLowerFirst(foreignCols[i])+"Service.findById(userForm.get"+toUpperFirst(foreignCols[i])+"Id());\n" +
                        "            } catch (Exception e) {\n" +
                        "                return \"redirect:"+thymleafInitUrl+"/"+toLowerFirst(domainStr)+"/insert\";\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n";
            }
            result += "\n";
        }
        return result;
    }

    private String pFinalForignCol() {
        //      "    private final AddressStrService addressStrService;\n" +
        //      "    private final PhoneStrService phoneStrService;\n" +
        String result = "";
        if(foreignCols!=null ){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "    private final "+toUpperFirst(foreignCols[i])+"Service "+toLowerFirst(foreignCols[i])+"Service;\n";
            }
        }
        return result;
    }

    public String makeApiDtoForm() {
        String result ="";
        result = "" +
                "import lombok.Data;\n" +
                "\n" +
                "import java.time.LocalDateTime;\n" +
                "\n" +
                "@Data\n" +
                "public class "+toUpperFirst(domainStr)+"ApiDtoForm {\n" +
                "\n" +
                "    private Long id;\n" +
                apiDtoForm1() +
                "\n" +
                "    private String isDel;\n" +
                "    private LocalDateTime modifiedDate;\n" +
                "    private LocalDateTime createdDate;\n" +
                apiDtoForm2()+
                "}";

        String packageStr = null;
        if(rootPackageStr!=null){
            packageStr = rootPackageStr+".firstinstance.controller.firstinstanceurl.form;\n";
            result = "package "+packageStr+"\n" + result;
        }

        return result;
    }

    private String apiDtoForm1() {
        String result = "";
        for (int i = 1; i < colNames.length; i++) {
            if(colNames[i].equals("isDel") || colNames[i].equals("modifiedDate") || colNames[i].equals("createdDate")){

            }else{
                for (int j = 0; j < colLongs.length; j++) {
                    if(colNames[i].equals(colLongs[j])){
                        result += "    private Long "+colNames[i]+";\n";
                    }
                }
                for(int j = 0; j < colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "    private String "+colNames[i]+";\n";
                    }
                }
                for(int j=0; j< colDates.length; j++){
                    if(colNames[i].equals(colDates[j])){
                        //result += "    private LocalDateTime "+colNames[i]+";\n";
                        result += "    private String "+colNames[i]+";\n";
                    }
                }
            }
        }
        return result;
    }

    private String apiDtoForm2() {
        String result ="";
        if(foreignCols!=null){
            for (int i = 0; i < foreignCols.length; i++) {
                result += "    private Long "+toLowerFirst(foreignCols[i])+"Id;\n";
            }
        }
        return result;
    }

    public String makeSearchCondition2() {
        /*
        package com.example.domain.servicework;
        import lombok.Data;

        import java.time.LocalDateTime;

        @Data
        public class ServiceWorkSearchCondition2 {

            private String id;
            private String coopName;
            private String coopComment;
            private String coopWorkName;
            private String wtag1;
            private String wtag2;
            private String startWorkDate;
            private String endWorkDate;
            private String searchTag1;
            private String searchTag2;
            private String searchTag3;
            private String searchTag4;
            private String searchTag5;
            private String viewCount;
            private String likeCount;
            private String starCount;
            private String starAll;
            private String starPairMan;
            private String isDel;
            private String modifiedDate;
            private String createdDate;
        }
        */

        String result="";
        result += "package "+rootPackageStr+".domain."+toAllLowerCase(domainStr)+";\n";
        result += "import lombok.Data;\n";
        result += "\n";
        result += "import java.time.LocalDateTime;\n";
        result += "\n";
        result += "@Data\n";
        result += "public class "+toUpperFirst(domainStr)+"SearchCondition2 {\n";
        result += "\n";
        for(int i=0; i<colNames.length;i++){
            if(colLongs!=null){
                for(int j=0; j<colLongs.length; j++){
                    if(colNames[i].equals(colLongs[j])){
                        result += "    private String "+colNames[i]+";\n";
                    }
                }
            }
            if(colStrs!=null){
                for(int j=0; j<colStrs.length; j++){
                    if(colNames[i].equals(colStrs[j])){
                        result += "    private String "+colNames[i]+";\n";
                    }
                }
            }
            if(colDates!=null){
                for(int j=0; j<colDates.length; j++){
                    if(colNames[i].equals(colDates[j])){
                        result += "    private String "+colNames[i]+";\n";
                    }
                }
            }

        }
        if(foreignCols!=null){
            for(int j=0; j<foreignCols.length; j++){
                    result += "    private String "+toLowerFirst(foreignCols[j])+"Id;\n";
            }
        }
        result += "   private String field;\n";
        result += "   private String s;\n";
        result += "   private String sdate;\n";
        result += "   private String edate;\n";
        result += "}";

        return result;
    }
}
