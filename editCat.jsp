<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Sửa danh mục</span></h2>
		<%
			String err=request.getParameter("err");
			if("1".equals(err)){
				out.print("Sửa danh mục thất bại!!");
			}
		%>	
		 <div class="module-body">
			<form action="" enctype="" method="post" class="editdanhmuc">
			<%
				Category obj=(Category)request.getAttribute("obj");
			%>
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="tentin" value="<%if(obj!=null)out.print(obj.getName()); %>" class="input-medium" />
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
				$('.editdanhmuc').validate({
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