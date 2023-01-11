package org.example.v4.result.screen;

import org.example.v4.UtilStaticV4;

public class HTMLContextStr {

    public String domainStr;
    public String[] colStrs;
    public String[] colLongs;
    public String[] colDates;

    public String[] colNames;

    public String thymleafInitUrl;
    public String thymleafInitUrlDomain; //thymleafInitUrl + "/" +domainStr

    public String columnHtmlStr;
    public String columnHtmlStr2;

    public HTMLContextStr(UtilStaticV4 usv) {
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.colNames = usv.colNames;
        this.thymleafInitUrl = usv.thymleafInitUrl;
        this.thymleafInitUrlDomain = usv.thymleafInitUrl + "/" + domainStr;

        this.columnHtmlStr = initColumHtmlStr();
        this.columnHtmlStr2 = initColumnHtmlStr2(thymleafInitUrl);

    }



    public String initColumnHtmlStr2(String thymleafInitUrl) {
        /*
                "        <tr th:each=\"board : ${boards}\">\n" +
                "            <td th:text=\"${board.id}\"></td>\n" +
                "            <td th:text=\"${board.addressStr?.id}\"></td>\n" +
                "            <td th:text=\"${board.phoneStr?.id}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{/administer/instanceurl/roleclass/user/update(id=${board.id})}\"  >수정</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{/administer/instanceurl/roleclass/user/delete(id=${board.id})}\"  >삭제</a></td>\n" +
               */
        columnHtmlStr2 = "        <tr th:each=\"board : ${boards}\">\n";

        for(int i=0; i < colNames.length; i++){
            columnHtmlStr2 +=  "<td th:text=\"${board."+colNames[i]+"}\"></td>\n";
        }
        columnHtmlStr2 += "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{"+thymleafInitUrl+"/update(id=${board.id})}\"  >Update</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrl+"/delete(id=${board.id})}\"  >Delete</a></td>\n" +
                "        </tr>\n";
        return columnHtmlStr2;
    }


    public String initColumHtmlStr() {
        /*
                "            <th>AddressStrId</th>\n" +
                "            <th>PhoneStrId</th>\n" +
                "            <th>수정일</th>\n" +
                "            <th>생성일</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n" +
                */


        for(int i=0; i<colNames.length; i++) {

            columnHtmlStr += " <th>" + colNames[i] + "</th>";
        }
        columnHtmlStr = columnHtmlStr+ "<th></th>\n <th></th>";
        return columnHtmlStr;
    }


    public String htmlUpTexting() {
        String htmlUpText = "" +
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
                "    <link rel=\"stylesheet\" href=\"/css/nav.css\">\n" +
                "    <title>오늘, 일 하시는 것은 어떤가요. 좋은 하루되세요.</title>\n" +
                "    <style>\n" +
                "        .navbar-brand {\n" +
                "            font-size: 1rem;\n" +
                "        }\n" +
                "        .card {\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .nav_bottom {\n" +
                "            margin-bottom: 40px;\n" +
                "        }\n" +
                "        .font-12 {\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "        ul>li{\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "    <script th:inline=\"javascript\">\n" +
                "        /*<![CDATA[*/\n" +
                "        let result = [[${workPlanList}]]\n" +
                "        /*]]>*/\n" +
                "    </script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"fixed-bottom\">\n" +
                "    <nav class=\"navbar navbar-expand  nav1\">\n" +
                "        <div class=\"container-fluid\">\n" +
                "            <a class=\"navbar-brand\" href=\"#\">스마트 팩토리</a>&nbsp;&nbsp;&nbsp; <a class=\"navbar-brand\">테스트, CRUD 페이지.</a>\n" +
                "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\n" +
                "                    aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "                <span class=\"navbar-toggler-icon\" style=\"color:black;margin-top:5px;\"><i class=\"bi bi-justify\"></i></span>\n" +
                "            </button>\n" +
                "            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "</div>";

        return htmlUpText;
    }

    public String htmlDownTexting(){
        String htmlDownText = "<!-- Optional JavaScript; choose one of the two! -->\n" +
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
                "<script src=\"/js/jquery-3.6.3.min.js\"></script>\n" +
                " \n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return htmlDownText;
    }

    public String htmlIndexMiddleTexting(){
        String htmlIndexMiddleText = "" +
                "<div class=\"container\" style=\"margin-left:auto; margin-right:auto; width:100%;\">\n" +
                "\n" +
                "    <br/>\n" +
                "    <a class=\"btn btn-sm btn-link\" th:href=\"@{"+thymleafInitUrl+"}\"><i class=\"bi bi-chevron-double-left fa-3x\"></i></a><a th:href=\"@{"+thymleafInitUrlDomain+"}\">\n" +
                "    <span style=\"font-size:20px;\">"+domainStr+" Domain</span></a>\n" +
                "    <form name=\"search_form\" th:action=\"@{"+thymleafInitUrlDomain+"}\" method=\"get\" role=\"form\" th:object=\"${condition}\" class=\"d-flex justify-content-evenly\">\n" +
                "        <table style=\"width:670px;\" class=\"border border-5 d-flex justify-content-center caption-top\">\n" +
                "            <colgroup>\n" +
                "                <col style=\"width:10%;\">\n" +
                "                <col style=\"width:35%;\">\n" +
                "                <col style=\"width:10%;\">\n" +
                "                <col style=\"width:35%;\">\n" +
                "                <col style=\"width:auto;\">\n" +
                "                <col style=\"width:auto;\">\n" +
                "            </colgroup>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <th class=\"font-12\">키워드</th>\n" +
                "                    <td class=\"font-12\">\n" +
                "                        <select id=\"field\" name=\"field\" style=\"width:60px;\" title=\"키워드 선택\">\n" +
                "                            <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n" +

                "                        </select>\n" +
                "                        <input class=\"font-12\" type=\"text\" title=\"키워드\" placeholder=\"Search Keyword\" name=\"s\" th:field=\"*{s}\" autocomplete=\"on\"  style=\"vertical-align: top; width:100px;\">\n" +
                "                    </td>\n" +
                "                    <th scope=\"row\" class=\"font-12\">&nbsp;Date</th>\n" +
                "                    <td class=\"font-12\">\n" +
                "                        <input type=\"text\" placeholder=\"StartDate\" class=\"ico_date\" name=\"sdate\"\n" +
                "                               id=\"datepicker1\" th:field=\"*{sdate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                        <span class=\"hypen\">~</span>\n" +
                "                        <input type=\"text\" placeholder=\"EndDate\" class=\"ico_date\" name=\"edate\"\n" +
                "                               id=\"datepicker2\" th:field=\"*{edate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        &nbsp;<button class=\"btn btn-success btn-sm\">Search</button>\n" +
                "                    </td>\n" +
                "                    <td>\n" +
                "                        &nbsp;<a class=\"btn btn-sm btn-primary\" th:href=\"@{"+thymleafInitUrlDomain+"/insert}\">Insert</a>&nbsp;\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "\n" +
                "    </form>\n" +
                "    <br/>\n" +
                "\n" +
                "    <table class=\"table table-sm\" style=\"font-size: 12px;margin-left:auto; margin-right:auto;\">\n" +
                "        <thead>\n" +
                "        <tr>\n" +
                "            <th>id</th>\n" + columnHtmlStr +
                /*
                "            <th>AddressStrId</th>\n" +
                "            <th>PhoneStrId</th>\n" +
                "            <th>수정일</th>\n" +
                "            <th>생성일</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n" +
                */

                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                /*
                "        <tr th:each=\"board : ${boards}\">\n" +
                "            <td th:text=\"${board.id}\"></td>\n" +
                "            <td th:text=\"${board.addressStr?.id}\"></td>\n" +
                "            <td th:text=\"${board.phoneStr?.id}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "            <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "            <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{/administer/instanceurl/roleclass/user/update(id=${board.id})}\"  >수정</a></td>\n" +
                "            <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{/administer/instanceurl/roleclass/user/delete(id=${board.id})}\"  >삭제</a></td>\n" +
               */
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
                "                startPage = ${T(Math).floor(pageNumber / pageSize) * pageSize + 1},\n" +
                "                tempEndPage = ${startPage + pageSize - 1},\n" +
                "                endPage = (${tempEndPage < totalPages ? tempEndPage : totalPages})\"\n" +
                "                aria-label=\"Page navigation\"\n" +
                "        >\n" +
                "            <ul class=\"pagination \" style=\"justify-content:center;\">\n" +
                "                <!-- 처음으로 이동 -->\n" +
                "                <li th:classappend=\"${pageNumber < pageSize} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{/administer/instanceurl/roleclass/user(page=0)}\">\n" +
                "                        <span>&laquo;</span>\n" +
                "                        <span class=\"sr-only\">First</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 이전으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.first} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.first} ? '#' : @{/administer/instanceurl/roleclass/user(page=${pageNumber - 1})}\" aria-label=\"Previous\">\n" +
                "                        <span aria-hidden=\"true\">&lt;</span>\n" +
                "                        <span class=\"sr-only\">Previous</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 특정 페이지로 이동 -->\n" +
                "                <li th:each=\"page: ${#numbers.sequence(startPage, endPage)}\" th:classappend=\"${page == pageNumber + 1} ? 'active'\" class=\"page-item\">\n" +
                "                    <a th:text=\"${page}\" class=\"page-link\" th:href=\"@{/administer/instanceurl/roleclass/user(page=${page - 1})}\"></a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 다음으로 이동 -->\n" +
                "                <li th:classappend=\"${boards.last} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"${boards.last} ? '#' : @{/administer/instanceurl/roleclass/user(page=${pageNumber + 1})}\" aria-label=\"Next\">\n" +
                "                        <span aria-hidden=\"true\">&gt;</span>\n" +
                "                        <span class=\"sr-only\">Next</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "\n" +
                "                <!-- 마지막으로 이동 -->\n" +
                "                <li th:classappend=\" ${T(Math).floor(totalPages / pageSize) * pageSize - 1 <= startPage} ? 'disabled'\" class=\"page-item\">\n" +
                "                    <a class=\"page-link\" th:href=\"@{/administer/instanceurl/roleclass/user(page=${totalPages - 1})}\">\n" +
                "                        <span>&raquo;</span>\n" +
                "                        <span class=\"sr-only\">Last</span>\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </nav>\n" +
                "    </div> <!-- /페이징 -->\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "\n" +
                "<nav class=\"navbar nav_bottom\">\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <div class=\"navbar-text\" href=\"#\">\n" +
                "            <i class=\"bi bi-emoji-smile\"></i>\n" +
                "            스마트 팩토리, UI 만들어봤습니다. 2021년 6월 21일 ~ 2021 7월 9일., 2022년 12월 29일 ~ ... <br />한번 만들어보세요~. 일하기가 더 좋아졌으면 좋겠습니다. 좋은 개발되세요~.\n" +
                "            감사합니다.<br />작성자: 최현일\n" +
                "            |\n" +
                "            Github주소 <a href=\"https://github.com/infott2t/smartFactory-ex\">@infott2t</a>\n" +
                "            <br/>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "\n" +
                "\n" +
                "</div>";

        return htmlIndexMiddleText;
    }

}
