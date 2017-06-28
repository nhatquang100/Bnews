<%@page import="common.StringUntil"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="/templates/public/inc/left_bar.jsp" %>  
</div>
<div class="rightpanel">
	<div class="rightbody">
	<%
				if(request.getAttribute("obj")!=null){
					News obj=(News)request.getAttribute("obj");
	%>
		<h1 class="title"><%=obj.getName() %></h1>
		<div class="items-new">
			<div class="new-detail">
				<p><%=obj.getDetail()%></p>
			</div>
		</div>
	<%} %>
		<h2 class="title" style="margin-top:30px;color:#BBB">Tin tức liên quan</h2>
		<div class="items-new">
			<ul>
				<%
					if(request.getAttribute("listtinlquan")!=null){
						ArrayList<News> list=(ArrayList<News>)request.getAttribute("listtinlquan");
							if(list.size()>0){
								for(News objN:list){
				%>
				<li>
					<h2>
						<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(objN.getName()) %>-<%=objN.getId() %>.html" title="<%=objN.getName()%>"><%=objN.getName()%></a>
					</h2>
					<div class="item">
						<%
							if(!"".equals(objN.getPicture())){
						%>
								<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(objN.getName()) %>-<%=objN.getId() %>.html"><img src="<%=request.getContextPath()%>/files/<%=objN.getPicture()%>" alt="<%=objN.getName()%>"></a>
						<%}else {%>
								<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlug(objN.getName()) %>-<%=objN.getId() %>.html"><img src="<%=request.getContextPath()%>/templates/public/images/slide1.jpg" alt="<%=objN.getName()%>"></a>
						<%} %>
						<p><%=objN.getDetail()%></p>
						<div class="clr"></div>
					</div>
				</li>
				<%}}} %>
			</ul>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/templates/public/inc/footer.jsp" %>  