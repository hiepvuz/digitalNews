<%-- 
    Document   : Detail
    Created on : Jun 25, 2021, 4:37:25 AM
    Author     : Hiep Nino
--%>

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
            <!--Header-->
            <jsp:include page="Header.jsp"/>

            <!--Content -->
            <div class="content">
                <!--Left-->
                <div class="left">

                    <div class="title">
                        ${detail.title}
                    </div>

                    <div class="image">
                        <img src="images/${detail.image}" alt="">
                    </div>

                    <div class="text">
                        ${detail.contentDetail}
                    </div>

                    <div class="signature">
                        By ${detail.author} | ${detail.timePost}
                    </div>
                </div>
                <!--Right-->
                <jsp:include page="Right.jsp"/>
            </div>

            <!--Footer-->
            <div class="footer"></div>
        </div>
    </body>
</html>
