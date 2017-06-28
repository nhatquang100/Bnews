<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/addnews" class="button">
			<span>Thêm tin <img src="<%=request.getContextPath()%>/templates/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách tin</span></h2>
		<%
		@SuppressWarnings("unchecked")
		String msg=request.getParameter("msg");
		if("1".equals(msg)){
			out.print("<span>Thêm tin thành công</span>");
		}
		if("2".equals(msg)){
			out.print("<span>Sửa thành công</span>");
		}
		if("3".equals(msg)){
			out.print("<span>Xóa thành công</span>");
		}
		String err=request.getParameter("err");
		if("2".equals(err)){
			out.print("<span>ID không tồn tại</span>");
		}
		if("1".equals(err)){
			out.print("<span>Có lỗi khi sửa</span>");
		}
		if("3".equals(err)){
			out.print("<span>Có lỗi khi Xóa</span>");
		}
		%>
		<div class="module-table-body">
			<form action="" method="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Tên</th>
						<th style="width:20%">Danh mục</th>
						<th style="width:16%; text-align: center;">Hình ảnh</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
				<%
					ArrayList<News> list=(ArrayList<News>)request.getAttribute("list");
					if(list!=null&&list.size()>0){
						for(News item:list){
				%>
					<tr>
						<td class="align-center"><%=item.getId()%></td>
						<td><a href=""><%=item.getName() %></a></td>
						<td><%=item.getCatname() %></td>
						<%
							if(!"".equals(item.getPicture())){
						%>
							<td align="center"><img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" class="hoa" /></td>
						<%}else{ %>
							<td align="center"><img src="<%=request.getContextPath()%>/templates/public/images/slide1.jpg" class="hoa" /></td>
						<%} %>
						<td align="center">
							<a href="<%=request.getContextPath()%>/admin/editnews?nid=<%=item.getId()%>">Sửa<img src="<%=request.getContextPath()%>/templates/admin/images/pencil.gif" alt="edit" /></a>
							<a href="<%=request.getContextPath()%>/admin/delnews?nid=<%=item.getId()%>">Xóa<img src="<%=request.getContextPath()%>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
						</td>
					</tr>
				 <%}}else{ %>
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
				<a <%=active %> href="<%=request.getContextPath()%>/admin/news?page=<%=i%>"><%=i %></a> 
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