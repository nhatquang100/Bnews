<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm người dùng</span></h2>
		<%
			String err=request.getParameter("err");
			if("1".equals(err)){
				out.print("<h3 style='color:red'>Username đã tồn tại!!</h3>");
			}
		%>	
		 <div class="module-body">
			<form action="<%=request.getContextPath()%>/admin/adduser" enctype="" method="post" class="adduser">
				<p>
					<label>User name</label>
					<input type="text" name="name" value="" class="input-medium" />
				</p>
				<p>
					<label>Password</label>
					<input type="text" name="pass" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname</label>
					<input type="text" name="fullname" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->
		 <style>.error{color:pink}</style>
		<script type="text/javascript">
			$(document).ready(function (){ 
				$('.adduser').validate({
					rules:{
						name:{
							required:true,
							minlength:2,
						},
						pass:{
							required:true,
							minlength:6,
							
						},
						fullname:{
							required:true,
							
						},
					},    
					messages:{
						name:{
							required:"hãy nhập tên đăng nhập!!",
							minlength:"tên đăng nhập không được dưới 2 kí tự!!",
						},
						pass:{
							required:"hãy nhập pass word!!",
							minlength:"password phải trên 5 kí tự!!",
						},
						fullname:{
							required:"hãy nhập họ tên đầy đủ!!",
						},
					} 
				});
			});
		</script>
	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 