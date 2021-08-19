<%-- 
    Document   : HomePage
    Created on : May 15, 2021, 4:17:31 PM
    Author     : Hiep Nino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content_detail="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="left">
                    <div class="title">
                        ${top1.title}
                    </div>
                    <div class="image">
                        <img src="images/${top1.image}" alt=""/>
                    </div>
                    <div class="text">
                        ${top1.contentDetail}
                    </div>
                    <div class="signature">
                        By ${top1.author} | ${top1.timePost}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>

            <!--Footer-->
            <div class="footer"></div>
        </div>
    </body>
</html>
