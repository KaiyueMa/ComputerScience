<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav col-lg-12 col-md-12">
            <ul class="text-uppercase">
                <li><a href="${pageContext.request.contextPath}/AdminOrderServlet?flag=orderSend">Send</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminOrderServlet?flag=orderReceive">Receive</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminOrderServlet?flag=Ordercomplete">Complete</a></li>
                <%--<li><a href="login.html">Sign in form</a></li>--%>
            </ul>
        </nav>
    </div>
</div>
