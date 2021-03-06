<%@page import="common.StringUntil"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp"%>
<div class="leftpanel">
	<%@include file="/templates/public/inc/left_bar.jsp"%>
</div>
<div class="rightpanel">
	<div class="rightbody">
		<%
			if(request.getAttribute("objCat")!=null){
							Category objC=(Category)request.getAttribute("objCat");
		%>
		<h1 class="title">
			Tin tức >>
			<%=objC.getName()%></h1>
		<%
			}
		%>
		<%
			if(request.getAttribute("list")!=null){
								ArrayList<News> list=(ArrayList<News>)request.getAttribute("list");
								if(list.size()>0){
									for(News obj:list){
		%>
		<div class="items-new">
			<ul>
				<li>
					<h2>
						<a
							href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(obj.getName()) %>-<%=obj.getId() %>.html"
							title=""><%=obj.getName()%></a>
					</h2>
					<div class="item">
						<%
							if(!"".equals(obj.getPicture())){
						%>
						<a
							href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(obj.getName()) %>-<%=obj.getId() %>.html"
							title=""><img
							src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>"
							alt="" /></a>
						<%
							}else{
						%>
						<a
							href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(obj.getName()) %>-<%=obj.getId() %>.html"
							title=""><img
							src="<%=request.getContextPath()%>/templates/public/images/slide1.jpg"
							alt="" /></a>
						<%
							}
						%>
						<p><%=obj.getPreview()%></p>
						<div class="clr"></div>
					</div>
				</li>

			</ul>
			<%
				}}}
			%>
			<div class="paginator">
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
				<a <%=active%>
					<%
					if(request.getAttribute("objCat")!=null){
									Category objCat=(Category)request.getAttribute("objCat");
					%>
						href="<%=request.getContextPath()%>/danh-muc/<%=StringUntil.createSlug(objCat.getName())%>-<%=objCat.getId()%>/page/<%=i%>.html"><%=i%></a>
					<%} %>
				<%
					if(i!=sumPage){
				%>
				<span>|</span>
				<%
					} }
				%>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/templates/public/inc/footer.jsp"%>
