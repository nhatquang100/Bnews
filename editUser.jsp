<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		<%
		String err=request.getParameter("err");
		if("1".equals(err)){
			out.print("<span>Sửa thất bại!!</span>");
		}
		%>
		 <h2><span>Sửa người dùng</span></h2>	
		 <div class="module-body">
		 <%		
				User objUser=(User)request.getAttribute("objUser");
		%>
			<form action="" enctype="" method="post" class="adduser">
				<p>
					<label>User name:<span style="color:green; font-size: 15px;font-weight: bold"><%if(objUser!=null)out.print(objUser.getUsername());%></span></label>
					
				</p>
				<p>
					<label>Password</label>
					<input type="password" name="pass" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname</label>
					<input type="text" name="fullname" value="<%if(objUser!=null)out.print(objUser.getFullname());%>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="Sua" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->
		 <style>
		 		.error{
		 			color:pink;
		 		}
		 </style>
		<script type="text/javascript">
			$(document).ready(function (){ 
				$('.adduser').validate({
					rules:{
						tentin:{
							required:true,
							minlength:2,
							
						},
					},    
					messages:{
						tentin:{
							required:"hãy nhập tên danh mục!!",
						},
					} 
				});
			});
		</script>
	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 