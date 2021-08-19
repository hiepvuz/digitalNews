<%-- 
    Document   : SearchPage
    Created on : Jun 25, 2021, 4:52:25 PM
    Author     : Hiep Nino
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">

            <!--Header -->
            <jsp:include page="Header.jsp"/>
            <div class="content">

                <!--Left-->
                <div class="left">
                    <c:forEach items="${listArticle}" var="o">
                        <div class="findout">
                            <div class="title">
                                <a href="Detail?did=${o.id}">
                                    ${o.title}
                                </a>
                            </div>

                            <div class="image_search">
                                <img src="images/${o.image}" alt=""/>
                            </div>

                            <div class="shortDes">
                                ${o.shortDes}
                            </div>
                            <br>
                        </div>
                    </c:forEach>
                    <!--Page number-->
                    <div class="paging">
                        <c:forEach items="${lsPage}" var="i">
                            <a class="${index==i?"active":""}" href="search?index=${i}&txtSearch=${txtSearch}">${i}</a>
                        </c:forEach>
                    </div>
                </div>

                <!--Right-->
                <jsp:include page="Right.jsp"/>
            </div>
            <!-- Footer -->
            <div class="footer"></div>
        </div>
    </body>
</html>
