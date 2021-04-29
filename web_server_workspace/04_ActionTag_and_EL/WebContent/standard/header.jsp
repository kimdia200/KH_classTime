<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title }</title>
</head>
<style>
	header, section, footer{
		border:1px solid black;
	}
	section {
		height: 500px;
	}
</style>
<body>
	<header><h1>${param.title }</h1></header>
	<section>