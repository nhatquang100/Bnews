<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Đăng nhập</span></h2>
			 <%
		String err=request.getParameter("err");
		if("1".equals(err)){
			out.print("<span>Đăng nhập thất bại!!</span>");
		}
		%>
		 <div class="module-body">
			<form action="<%=request.getContextPath() %>/admin/login" enctype="" method="post">

				<p>
					<label>Username</label>
					<input type="text" name="username" value=""  class="input-medium"></input>
				</p>
				<p>
					<label>password</label>
					<input  type="password" name="password" value="" class="input-medium"></input>
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Login" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 