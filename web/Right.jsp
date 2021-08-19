<%-- 
    Document   : Right
    Created on : Jun 24, 2021, 4:51:13 AM
    Author     : Hiep Nino
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="right">
            
            <!-- Latest News -->
            <div class="newsRight">
                <div class="titleRight">
                    Digital News
                </div>
                <div class="shortDes">
                    ${top1.shortDes}
                </div>
            </div>
            <!-- Search -->
            <div class="newsRight">
                <div class="titleRight">
                    Search
                </div>
                <form action="search?index=1" method="get">
                    <input class="searchBox" type="text"  name="txtSearch" size="20" value="${txtSearch}" required>
                    <input class="searchButton" type="submit" name="btnGo" value="Go">
                </form>
            </div>
            
            <!-- Top 5 Articles -->
            <div class="newsRight">
                <div class="titleRight">
                    Last Article
                </div>
                <c:forEach items="${top5}" var="a">
                    <div class="lastest55Articles">
                        <a href="Detail?did=${a.id}">${a.title}</a>
                    </div>
                </c:forEach>
            </div>
            
        </div>
    </body>
</html>
