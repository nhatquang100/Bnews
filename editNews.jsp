<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Sửa tin tức</span></h2>
		 <%
		String err=request.getParameter("err");
		if("1".equals(err)){
			out.print("<span>Sửa thất bại!!</span>");
		}
		%>
		 <div class="module-body">
			<form class="editnews" action="" enctype="multipart/form-data" method="post">
			<%
				News obj=(News) request.getAttribute("obj");
			%>
				<p>
					<label>Tên tin</label>
					<input type="text" name="tentin" value="<%=obj.getName() %>" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="danhmuc" class="input-short">
					<%
						ArrayList<Category> list=(ArrayList<Category>)request.getAttribute("list");
						String selected="";
						if(list!=null&&list.size()>0){
							for(Category item:list){
								if(item.getId()== obj.getIdcat()){
									selected="selected='selected'";
								}else{
									selected="";
								}
					%>
						<option <%=selected%> value="<%=item.getId()%>"><%=item.getName()%></option>
					<%}}%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="hinhanh" value="" /><br/>
					<%
						if(!"".equals(obj.getPicture())){
					%>
					<img style="width:150px; height:100px"alt="<%=obj.getPicture()%>" src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>"/>
					<%} %>
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="mota" value="" rows="7" cols="90" class="ckeditor"><%=obj.getPreview()%></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="chitiet" value="" rows="7" cols="90" class="ckeditor"><%=obj.getDetail()%></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sửa" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->
		<style>.error{color:red}</style>
		<script type="text/javascript">
			$(document).ready(function (){ 
				$('.editnews').validate({
					ignore: [],
					rules:{
						tentin:{
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