<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm tin tức</span></h2>
		<%
		String err=request.getParameter("err");
		if("1".equals(err)){
			out.print("<span>Có lỗi khi thêm!!</span>");
		}
			 String name=request.getParameter("tentin");
			 String catID=request.getParameter("danhmuc");
			 String preview=request.getParameter("mota");
			 String detail=request.getParameter("chitiet");
		%>		
		 <div class="module-body">
			<form class="addnews" action="<%=request.getContextPath()%>/admin/addnews" enctype="multipart/form-data" method="post">
				<p>
					<label>Tên tin</label>
					<input type="text" name="tentin" value="" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="danhmuc" class="input-short">
					<%
						ArrayList<Category> list=(ArrayList<Category>)request.getAttribute("list");
						if(list!=null&&list.size()>0){
							for(Category item:list){
					%>
						<option <%if(catID!=null&&catID.equals(item.getId()))out.print("selected");%>value="<%=item.getId()%>"><%=item.getName()%></option>
					<%}}%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="hinhanh" value="" />
				</p>
				<p>
					<label>Mô tả</label>
					<textarea class="ckeditor" name="mota" value="" rows="7" cols="90" ></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea class="ckeditor" name="chitiet" value="" rows="7" cols="90" ></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->
		<style>.error{color:red}</style>
		<script type="text/javascript">
			$(document).ready(function (){ 
				$('.addnews').validate({
					ignore: [],
					rules:{
						tentin:{
							required:true,
							minlength:6,
							maxlength:50,
						},
						hinhanh:{
							required:true,
							
						},
						mota:{
							required:true,
							
						},
						chitiet:{
							required:true,
							
						},
					},    
					messages:{
						tentin:{
							required:"hãy nhập tên tin!!",
							minlength:"tên tin không được dưới 6 kí tự!!",
							maxlength:"Tên tin không được quá 50 kí tự!!",
						},
						hinhanh:{
							required:"hãy chọn hình ảnh!!",
						},
						mota:{
							required:"hãy nhập mô tả!!",
						},
						chitiet:{
							required:"hãy nhập chi tiết!!",
						},
					} 
				});
				CKEDITOR.on('instanceReady', function () {
					$.each(CKEDITOR.instances, function (instance) {
						CKEDITOR.instances[instance].document.on("keyup", CK_jQ);
						CKEDITOR.instances[instance].document.on("paste", CK_jQ);
						CKEDITOR.instances[instance].document.on("keypress", CK_jQ);
						CKEDITOR.instances[instance].document.on("blur", CK_jQ);
						CKEDITOR.instances[instance].document.on("change", CK_jQ);
					});
				});
				function CK_jQ() {
					for (instance in CKEDITOR.instances) {
						CKEDITOR.instances[instance].updateElement();
					}
				};
			});
		</script>
	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 