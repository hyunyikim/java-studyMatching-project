<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	String msg = (String)request.getAttribute("msg");
%>


<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>mypage</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/photo.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mypage.css">
<script type="text/javascript">
jQuery(document).ready(function() {
	var email="${email}";
	var memberGrade="${member_grade_code}"
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
		}
    if(!memberGrade=="M00"){
    	alert("관리자만 사용가능한 페이지 입니다.");
        location.href="<%=request.getContextPath()%>/view/main.jsp";
    }
    
    
    if("<%=msg%>" != "null"){
    	alert("<%=msg%>");
    }
    
    
    
});
</script>
<style>
a.selected {
	color: #aeb5be;
	border-bottom: 3px solid #fff;
}
table{width: 100%;}
th{border-bottom: 1px solid #ccc;}
th, td {
text-align: center !important; padding: 0 5px !important;
}
</style>
</head>
<body>
	<!-- navbar -->
	<div class="row">
		<div class="col-sm-12">
			<%
				pageContext.include("/view/form/nav.jsp");
			%>
		</div>
	</div>


	<div class="row">
		<div class="col-sm-12">
		<div class="page">
			<!-- mypage Header -->
		<%
		pageContext.include("/view/mypage/admin/adminMyPage.jsp");
	%>
			<!-- mypage Content -->
			<div class="myContent-box">
				<div class="myContent">
					<c:set var="MemberList" value="${requestScope.MemberList}"></c:set>
				 <h3>MemberList</h3>
                           <table> 
                               <tr>
                                  <th>이메일</th> 
                                  <th>이름</th>
                                  <th>핸드폰번호</th>
                                  <th>회원등급</th>
                               </tr>
				 <c:forEach var="member" items="${MemberList}">
					<tr>
						<td>${member.email}</td>
						<td>${member.name}</td>
						<td>${member.hp}</td>
						<td>${member.grade_name}</td>
						<td><a href="memberDrop.admin?email=${member.email}" class="btn btn-green btn-flat" style="margin:5px;">회원삭제</a></td>
					</tr>
				</c:forEach>
				</table> 
					<!-- <span class="title">원하는 스터디를 추가해 보세요.</span> -->
				</div>
			</div>
		</div>
		<div style="height: 151px;">&nbsp;</div></div>
	</div>
	
	
	<div class="row">
		<div class="col-sm-12">
			<!-- footer -->
			<%
				pageContext.include("/view/form/footer.jsp");
			%>
		</div>
	</div>
</body>
</html>