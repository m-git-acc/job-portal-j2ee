<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.extra.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<jsp:include page="CheckCookie"></jsp:include>

<jsp:scriptlet>
    String email = (String) session.getAttribute("session_email");
</jsp:scriptlet>

<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Job Portal Website</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>
    <body>
        <div class="container-fluid">

            <jsp:scriptlet>
                if (email == null ? email == null : email.trim().equals("null")) {
            </jsp:scriptlet>
            <jsp:include page="header.jsp"></jsp:include>
            <jsp:scriptlet>
            } else {
            </jsp:scriptlet>
            <jsp:include page="profileheader.jsp"></jsp:include>
            <jsp:scriptlet>
                }
            </jsp:scriptlet>
            <jsp:include page="menubar.jsp"></jsp:include>

                <div class="row">
                    <div class="col-md-3">
                        <ul>
                            <li> <a href=""> Infosys </a> </li>
                            <li> <a href=""> Wipro </a> </li>
                            <li> <a href=""> IBM </a> </li>
                            <li> <a href=""> Google </a> </li>
                        </ul>
                    </div>
                    <div class="col-md-6" style="background-color: #f9f8f9; margin-top: 10px">
                    <jsp:scriptlet>
                    if(email == null ? email == null : email.trim().equals("null"))
                    {
                    </jsp:scriptlet>
                    <jsp:include page="contact-us-nouser.jsp"></jsp:include>
                    <jsp:scriptlet>
                    }
                    else
                    {
                    </jsp:scriptlet>
                    <jsp:include page="contact-us-user.jsp"></jsp:include>
                    <jsp:scriptlet>
                    }
                    </jsp:scriptlet>
                    </div>
                    <div class="col-md-3">
                        <ul>
                            <li> <a href=""> Infosys </a> </li>
                            <li> <a href=""> Wipro </a> </li>
                            <li> <a href=""> IBM </a> </li>
                            <li> <a href=""> Google </a> </li>
                        </ul>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d224345.89797297175!2d77.04417439181661!3d28.52755440779233!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x390cfd5b347eb62d%3A0x52c2b7494e204dce!2sNew%20Delhi%2C%20Delhi!5e0!3m2!1sen!2sin!4v1695228589896!5m2!1sen!2sin" loading="lazy" referrerpolicy="no-referrer-when-downgrade"  width="100%" height="300" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                    </div>
                    <div class="col-md-2"></div>
                </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>