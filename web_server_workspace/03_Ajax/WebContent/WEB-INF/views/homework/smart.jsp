<%@page import="com.kh.homework.model.vo.Smart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Smart> list = (List<Smart>)request.getAttribute("list"); %>

<% if(list!=null){
	for(Smart s : list) {%>
		
<% }}%>