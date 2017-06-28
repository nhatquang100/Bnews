<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm danh mục</span></h2>
		<%
			String err=request.getParameter("err");
			if("1".equals(err)){
				out.print("Thêm danh mục thất bại!!");
			}
		%>	
		 <div class="module-body">
			<form class="adddanhmuc" action="<%=request.getContextPath()%>/admin/addcat" enctype="" method="post">
			<%
				String name=(String) request.getParameter("name");
			%>
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="tentin" value="<%if(name!=null)out.print(name); %>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
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
				$('.adddanhmuc').validate({
					rules:{
						tentin:{
							required:true,
							minlength:2,
							maxlength:50,
						},
					},    
					messages:{
						tentin:{
							required:"hãy nhập tên danh mục!!",
							minlength:"Tên danh mục không được dưới 2 kí tự!!",
							maxlength:"Tên danh mục không dược quá 50 kí tự!!",
						},
					} 
				});
			});
		</script>
	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 