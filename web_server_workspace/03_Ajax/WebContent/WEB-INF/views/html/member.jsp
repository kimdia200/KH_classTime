<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Member> list = (List<Member>)request.getAttribute("list"); %>
    
<table>
<% for(Member m : list){ %>
	<tr>
		<td><img src="<%= request.getContextPath() %>/images/<%= m.getProfile() %>" alt="<%= m.getName() %>" /></td>
		<td><%= m.getId() %></td>
		<td><%= m.getName() %></td>
	</tr>
<% }; %>
</table>