<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/adduser" class="button">
			<span>Thêm người dùng <img src="<%=request.getContextPath() %>/templates/admin/images/plus-small.gif" alt="Thêm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách người dùng</span></h2>
		
		<div class="module-table-body">
			<form action="" method="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>username</th>
						<th style="text-align: center;">Fullname</th>
						<th style="width:11%; text-align: center;">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<%
						@SuppressWarnings("unchecked")
						String msg=(String)request.getParameter("msg");
						if("1".equals(msg)){
							out.print("<span>Thêm thành công</span>");
						}
						if("2".equals(msg)){
							out.print("<span>Sửa thành công</span>");
						}
						if("3".equals(msg)){
							out.print("<span>Xóa thành công</span>");
						}
						String err=(String)request.getParameter("err");
						if("1".equals(err)){
							out.print("<span>Thêm thất bại</span>");
						}
						if("2".equals(err)){
							out.print("<span>Có lỗi khi sửa</span>");
						}
						if("3".equals(err)){
							out.print("<span>ID không tồn tại</span>");
						}
						if("4".equals(err)){
							out.print("<span>Xóa thất bại</span>");
						}
						if("5".equals(err)){
							out.print("<span>Không có quyền xóa</span>");
						}
						if("6".equals(err)){
							out.print("<span>Không có quyền sửa admin</span>");
						}
						ArrayList<User> list = (ArrayList<User>) request.getAttribute("listUser");
						if(list != null || list.size() != 0){
							for(User item : list){
					%>
					<%
						User userinfo=(User)session.getAttribute("userLogin");
					%>
					<tr>
						<td class="align-center"><%=item.getID()%></td>
						<td><a href="<%=request.getContextPath()%>/admin/edituser?id=<%=item.getID()%>"><%=item.getUsername() %></a></td>
						<td><a href="<%=request.getContextPath()%>/admin/edituser?id=<%=item.getID()%>"><%=item.getFullname() %></a></td>
						<%
							if("admin".equals(userinfo.getUsername())){
						%>
							<td align="center">
								<a href="<%=request.getContextPath()%>/admin/edituser?id=<%=item.getID()%>">Sửa <img src="<%=request.getContextPath() %>/templates/admin/images/pencil.gif" alt="edit" /></a>
								<a href="<%=request.getContextPath()%>/admin/deluser?id=<%=item.getID()%>">Xoá <img src="<%=request.getContextPath() %>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
							</td>
						<%}else{ %>
							<td align="center">
							<%
								if(userinfo.getID()==item.getID()){
							%>
								<a href="<%=request.getContextPath()%>/admin/edituser?id=<%=item.getID()%>">Sửa <img src="<%=request.getContextPath() %>/templates/admin/images/pencil.gif" alt="edit" /></a>
							<%} %>
							</td>
						<%} %>
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
								active="class='active'";
							}else{
								active="";
							}
				%>
				<a <%=active %> href="<%=request.getContextPath()%>/admin/users?page=<%=i%>"><%=i %></a> 
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