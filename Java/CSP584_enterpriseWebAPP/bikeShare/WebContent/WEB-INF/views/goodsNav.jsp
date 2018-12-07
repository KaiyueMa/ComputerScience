<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="templatemo-top-nav-container">
    <div class="row">
        <nav class="templatemo-top-nav col-lg-12 col-md-12">
            <ul class="text-uppercase">
                <li><a href="${pageContext.request.contextPath}/AdminGoodServlet?flag=goodshow">All Products</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminGoodServlet?flag=addGood">Add Product</a></li>
                <li><a href="${pageContext.request.contextPath}/AdminCategoryServlet?flag=ShowCategory">Categories</a></li>
                <%--<li><a href="login.html">。。。</a></li>--%>
            </ul>
        </nav>
    </div>
</div>

