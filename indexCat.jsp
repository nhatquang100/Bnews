<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/addcat" class="button">
			<span>Thêm tin <img src="<%=request.getContextPath() %>/templates/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách danh mục tin</span></h2>
		
		<div class="module-table-body">
			<form action="" method="post">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Tên</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<%
						@SuppressWarnings("unchecked")
					
						String mgs=request.getParameter("mgs");
						if("1".equals(mgs)){
							out.print("<span>Thêm danh mục thành công</span>");
						}
						if("2".equals(mgs)){
							out.print("<span>Sửa danh mục thành công</span>");
						}
						if("3".equals(mgs)){
							out.print("<span>Xóa danh mục thành công</span>");
						}
						String err=request.getParameter("err");
						if("1".equals(err)){
							out.print("<span>Có lỗi khi sửa</span>");
						}
						if("2".equals(err)){
							out.print("<span>ID không tồn tại</span>");
						}
						if("3".equals(err)){
							out.print("<span>Xóa danh mục thất bại</span>");
						}
						ArrayList<Category> list = (ArrayList<Category>) request.getAttribute("list");
						if(list != null || list.size() != 0){
							for(Category item : list){
					%>
					<tr>
						<td class="align-center"><%=item.getId() %></td>
						<td><a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=item.getId()%>"><%=item.getName() %></a></td>
						<td align="center">
							<a href="<%=request.getContextPath()%>/admin/cats/edit?id=<%=item.getId()%>">Sửa <img src="<%=request.getContextPath() %>/templates/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%=request.getContextPath()%>/admin/delcat?id=<%=item.getId()%>">Xoá <img src="<%=request.getContextPath() %>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
					<%}}else{ %>
					<tr>
						<td colspan="3" class="align-center">Chưa có dữ liệu</td>
					</tr>
					<%} %>
				</tbody>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
		 <div class="pagination">           
			<div class="numbers">
				<span>Trang:</span> 
				<%	
					int current_page=(Integer)request.getAttribute("current_page");
					int sumPage=(Integer)request.getAttribute("sumPage");
					String active="";
						for(int i=1;i<=sumPage;i++){
							if(current_page==i){
								active="class='current'";
							}else{
								active="";
							}
				%>
				<a <%=active %> href="<%=request.getContextPath()%>/admin/cats?page=<%=i%>"><%=i %></a> 
				<%
					if(i!=sumPage){
				%>
						<span>|</span> 
				<%} }%> 
			</div> 
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 