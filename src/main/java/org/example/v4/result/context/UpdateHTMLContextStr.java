package org.example.v4.result.context;

import org.example.v4.UtilStaticV4;

public class UpdateHTMLContextStr {

    public String domainStr;
    public String[] colStrs;
    public String[] colLongs;
    public String[] colDates;

    public String[] colNames;

    public String thymleafInitUrl;
    public String thymleafInitUrlDomain; //thymleafInitUrl + "/" +domainStr

    public String columnHtmlStr;
    public String columnHtmlStr2;
    public String columnHtmlStr3;

    public String updateHTMLStr;

    public UpdateHTMLContextStr(UtilStaticV4 usv) {
        this.domainStr = usv.domainStr;
        this.colStrs = usv.colStrs;
        this.colLongs = usv.colLongs;
        this.colDates = usv.colDates;
        this.colNames = usv.colNames;
        this.thymleafInitUrl = usv.thymleafInitUrl;
        this.thymleafInitUrlDomain = usv.thymleafInitUrl + "/" + domainStr;

        this.columnHtmlStr = initColumHtmlStr();
        this.columnHtmlStr2 = initColumnHtmlStr2(thymleafInitUrl);
        this.columnHtmlStr3 = initColumnHtmlStr3();
        this.updateHTMLStr = htmlUpTexting() + htmlIndexMiddleTexting()+ htmlDownTexting();

    }

    public String initColumnHtmlStr3(){
        String result = "";
        /*
                "       <td th:text=\"${board.id}\"></td>\n" +
                "       <td th:text=\"${board.addressStr?.id}\"></td>\n" +
                "       <td th:text=\"${board.phoneStr?.id}\"></td>\n" +
                 */
        for(int i=0; i<colNames.length;i++){
            result += "       <td th:text=\"${board."+colNames[i]+"}\"></td>\n";
        }
        return result;
    }


    public String initColumnHtmlStr2(String thymleafInitUrl) {
        String result = "";
       /*
                "      <td><input type=\"text\" th:field=\"*{id}\" size=\"2\" readonly/></td>\n" +
                "      <td><input type=\"text\" th:field=\"*{addressStrId}\" size=\"9\"/></td>\n" +
                "      <td><input type=\"text\" th:field=\"*{phoneStrId}\" size=\"20\"/></td>\n" +
                 */


        for(int i=0; i < colNames.length; i++){
            result +=  "      <td><input type=\"text\" th:field=\"*{"+colNames[i]+"}\"/></td>\n";
        }

        return result;
    }


    public String initColumHtmlStr() {
        String result = "";
        /*
                "            <th>AddressStrId</th>\n" +
                "            <th>PhoneStrId</th>\n" +
                "            <th>?????????</th>\n" +
                "            <th>?????????</th>\n" +
                "            <th></th>\n" +
                "            <th></th>\n" +
                */


        for(int i=0; i<colNames.length; i++) {
            if(colNames[i].equals("isDel")){
                result += "            <th>????????????</th>\n";
            }else{
                result += " <th>" + colNames[i] + "</th>";
            }
        }
        result = result + " <th>ModifedDate</th> <th>CreatedDate</th>";
        result = result+ "<th></th>\n <th></th>";
        return result;
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
                "    <title>??????, ??? ????????? ?????? ????????????. ?????? ???????????????.</title>\n" +
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
                "            <a class=\"navbar-brand\" href=\"#\">????????? ?????????</a>&nbsp;&nbsp;&nbsp; <a class=\"navbar-brand\">?????????, CRUD ?????????.</a>\n" +
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
                "<script>\n" +
                "    $(document).ready(function(){\n" +
                "        $(\"#field\").change(function(){\n" +
                "            localStorage.setItem(\"field\", this.value);\n" +
                "        });\n" +
                "        if(localStorage.getItem(\"field\")){\n" +
                "            $(\"#field\").val(localStorage.getItem(\"field\"));\n" +
                "        }\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "</script>" +
                "</body>\n" +
                "\n" +
                "</html>";

        return htmlDownText;
    }

    public String htmlIndexMiddleTexting(){
        String htmlIndexMiddleText = "" +
                "<div class=\"container\" style=\"margin-left:auto; margin-right:auto; width:100%;\">\n" +
                "\n" +
                "  <br/>\n" +
                "  <a class=\"btn btn-sm btn-link\" th:href=\"@{"+thymleafInitUrlDomain+"}\"><i class=\"bi bi-chevron-double-left fa-3x\"></i></a><a th:href=\"@{"+thymleafInitUrlDomain+"}\">\n" +
                "  <span style=\"font-size:20px;\">"+domainStr+" ?????????</span></a>\n" +
                "  <form name=\"search_form\" th:action=\"@{"+thymleafInitUrlDomain+"}\" method=\"get\" role=\"form\" th:object=\"${condition}\" class=\"d-flex justify-content-evenly\">\n" +
                "    <table style=\"width:670px;\" class=\"border border-5 d-flex justify-content-center caption-top\">\n" +
                "      <colgroup>\n" +
                "        <col style=\"width:10%;\">\n" +
                "        <col style=\"width:35%;\">\n" +
                "        <col style=\"width:10%;\">\n" +
                "        <col style=\"width:35%;\">\n" +
                "        <col style=\"width:auto;\">\n" +
                "        <col style=\"width:auto;\">\n" +
                "      </colgroup>\n" +
                "      <tbody>\n" +
                "      <tr>\n" +
                "        <th class=\"font-12\">?????????</th>\n" +
                "        <td class=\"font-12\">\n" +
                "          <select id=\"field\" name=\"field\" style=\"width:60px;\" title=\"????????? ??????\">\n" +
                "            <option th:value=\"id\" th:selected=\"${#strings.trim(param.field) eq 'id'}\">id</option>\n" +
                "          </select>\n" +
                "          <input class=\"font-12\" type=\"text\" title=\"?????????\" placeholder=\"???????????? ??????\" name=\"s\" th:field=\"*{s}\" autocomplete=\"on\"  style=\"vertical-align: top; width:100px;\">\n" +
                "        </td>\n" +
                "        <th scope=\"row\" class=\"font-12\">&nbsp;????????????</th>\n" +
                "        <td class=\"font-12\">\n" +
                "          <input type=\"date\" placeholder=\"?????????\" class=\"ico_date\" name=\"sdate\"\n" +
                "                 id=\"datepicker1\" th:field=\"*{sdate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "          <span class=\"hypen\">~</span>\n" +
                "          <input type=\"date\" placeholder=\"?????????\" class=\"ico_date\" name=\"edate\"\n" +
                "                 id=\"datepicker2\" th:field=\"*{edate}\" autocomplete=\"on\" style=\"width:100px;\">\n" +
                "        </td>\n" +
                "        <td>\n" +
                "          &nbsp;<button class=\"btn btn-success btn-sm\">??????</button>\n" +
                "        </td>\n" +
                "        <td>\n" +
                "          &nbsp;<a class=\"btn btn-sm btn-primary\" th:href=\"@{"+thymleafInitUrlDomain+"/insert}\">??????</a>&nbsp;\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "  </form>\n" +
                "\n" +
                "  <br/>\n" +
                "\n" +
                "<!--\n" +
                "\n" +
                "-->\n" +
                "  <table class=\"table table-sm\" style=\"font-size: 12px;\">\n" +
                "    <thead>\n" +
                "    <tr>\n" + columnHtmlStr +
                /*
                "      <th>id</th>\n" +
                "      <th>AddressStr</th>\n" +
                "      <th>PhoneStr</th>\n" +
                "      <th>?????????</th>\n" +
                "      <th>?????????</th>\n" +
                "      <th></th>\n" +
                "      <th></th>\n" +
                */
                "    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "\n" +
                "\n" +
                "     <form  th:action=\"@{"+thymleafInitUrlDomain+"/update_2}\" th:object=\"${userForm}\" method=\"post\" >\n" +
                "\n" +
                "    <tr>\n" + columnHtmlStr2 +
                /*
                "      <td><input type=\"text\" th:field=\"*{id}\" size=\"2\" readonly/></td>\n" +
                "      <td><input type=\"text\" th:field=\"*{addressStrId}\" size=\"9\"/></td>\n" +
                "      <td><input type=\"text\" th:field=\"*{phoneStrId}\" size=\"20\"/></td>\n" +
                 */
                "      <td th:text=\"${#temporals.format(userForm.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "      <td th:text=\"${#temporals.format(userForm.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "      <td><button type=\"submit\" class=\"btn btn-sm btn-primary font-12\">??????</button></td>\n" +
                "      <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrlDomain+"}\">??????</a></td>\n" +
                "    </tr>\n" +
                "    </form>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td colspan=\"14\">\n" +
                "        <table>\n" +
                "          <tbody>\n" +
                "            <tr>\n" +
                "              <td text-align=\"right\">\n" +
                "                                <p class=\"font-12\">&#8251;?????? id, isDel ????????????.</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </tbody>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "     <tr th:each=\"board : ${boards}\">\n" + columnHtmlStr3 +
                /*
                "       <td th:text=\"${board.id}\"></td>\n" +
                "       <td th:text=\"${board.addressStr?.id}\"></td>\n" +
                "       <td th:text=\"${board.phoneStr?.id}\"></td>\n" +
                 */
                "       <td th:text=\"${ #temporals.format(board.modifiedDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "       <td th:text=\"${ #temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}\"></td>\n" +
                "\n" +
                "       <td><a class=\"btn btn-sm btn-success font-12\" th:href=\"@{"+thymleafInitUrlDomain+"/update(id=${board.id})}\"  >??????</a></td>\n" +
                "       <td><a class=\"btn btn-sm btn-danger font-12\" th:href=\"@{"+thymleafInitUrlDomain+"/delete(id=${board.id})}\"  >??????</a></td>\n" +
                "     </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  <!-- ????????? -->\n" +
                "  <div th:if=\"${!boards.isEmpty()}\">\n" +
                "    <!-- ?????? ?????? ?????? -->\n" +
                "    <nav\n" +
                "            th:with=\"\n" +
                "                pageNumber = ${boards.pageable.pageNumber},\n" +
                "                pageSize = ${boards.pageable.pageSize},\n" +
                "                totalPages = ${boards.totalPages},\n" +
                "                startPage = ${T(Math).floor(pageNumber / pageSize) * pageSize + 1},\n" +
                "                tempEndPage = ${startPage + pageSize - 1},\n" +
                "                endPage = (${tempEndPage < totalPages ? tempEndPage : totalPages})\"\n" +
                "            aria-label=\"Page navigation\"\n" +
                "    >\n" +
                "      <ul class=\"pagination \" style=\"justify-content:center;\">\n" +
                "        <!-- ???????????? ?????? -->\n" +
                "        <li th:classappend=\"${pageNumber < pageSize} ? 'disabled'\" class=\"page-item\">\n" +
                "          <a class=\"page-link\" th:href=\"@{"+thymleafInitUrlDomain+"(page=0)}\">\n" +
                "            <span>&laquo;</span>\n" +
                "            <span class=\"sr-only\">First</span>\n" +
                "          </a>\n" +
                "        </li>\n" +
                "\n" +
                "        <!-- ???????????? ?????? -->\n" +
                "        <li th:classappend=\"${boards.first} ? 'disabled'\" class=\"page-item\">\n" +
                "          <a class=\"page-link\" th:href=\"${boards.first} ? '#' : @{"+thymleafInitUrlDomain+"(page=${pageNumber - 1})}\" aria-label=\"Previous\">\n" +
                "            <span aria-hidden=\"true\">&lt;</span>\n" +
                "            <span class=\"sr-only\">Previous</span>\n" +
                "          </a>\n" +
                "        </li>\n" +
                "\n" +
                "        <!-- ?????? ???????????? ?????? -->\n" +
                "        <li th:each=\"page: ${#numbers.sequence(startPage, endPage)}\" th:classappend=\"${page == pageNumber + 1} ? 'active'\" class=\"page-item\">\n" +
                "          <a th:text=\"${page}\" class=\"page-link\" th:href=\"@{"+thymleafInitUrlDomain+"(page=${page - 1})}\"></a>\n" +
                "        </li>\n" +
                "\n" +
                "        <!-- ???????????? ?????? -->\n" +
                "        <li th:classappend=\"${boards.last} ? 'disabled'\" class=\"page-item\">\n" +
                "          <a class=\"page-link\" th:href=\"${boards.last} ? '#' : @{"+thymleafInitUrlDomain+"(page=${pageNumber + 1})}\" aria-label=\"Next\">\n" +
                "            <span aria-hidden=\"true\">&gt;</span>\n" +
                "            <span class=\"sr-only\">Next</span>\n" +
                "          </a>\n" +
                "        </li>\n" +
                "\n" +
                "        <!-- ??????????????? ?????? -->\n" +
                "        <li th:classappend=\" ${T(Math).floor(totalPages / pageSize) * pageSize - 1 <= startPage} ? 'disabled'\" class=\"page-item\">\n" +
                "          <a class=\"page-link\" th:href=\"@{"+thymleafInitUrlDomain+"(page=${totalPages - 1})}\">\n" +
                "            <span>&raquo;</span>\n" +
                "            <span class=\"sr-only\">Last</span>\n" +
                "          </a>\n" +
                "        </li>\n" +
                "      </ul>\n" +
                "    </nav>\n" +
                "  </div> <!-- /????????? -->\n" +
                "</div>\n" +
                "  <!--\n" +
                "  </form>\n" +
                "-->\n" +
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
                "  <div class=\"container-fluid\">\n" +
                "    <div class=\"navbar-text\" href=\"#\">\n" +
                "      <i class=\"bi bi-emoji-smile\"></i>\n" +
                "      ????????? ?????????, UI ?????????????????????. 2021??? 6??? 21??? ~ 2021 7??? 9???., 2022??? 12??? 29??? ~ ... <br />?????? ??????????????????~. ???????????? ??? ??????????????? ???????????????. ?????? ???????????????~.\n" +
                "      ???????????????.<br />?????????: ?????????\n" +
                "      |\n" +
                "      Github?????? <a href=\"https://github.com/infott2t/smartFactory-ex\">@infott2t</a>\n" +
                "      <br/>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</nav>\n" +
                "\n" +
                "\n" +
                "</div>";

        return htmlIndexMiddleText;
    }

}
