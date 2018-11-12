<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
String msg = (String)request.getAttribute("msg");
String result = (String)request.getAttribute("result");
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
var email="${email}";
var memberGrade="${member_grade_code}"
jQuery(document).ready(function() {
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

/* $('#${mento.mento_email}').val() */

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
			<div class="myContent-box" style="width: 80pc !important;">
				<div class="myContent">
				 <%
                 	 if(result != "false"){
             	 %>
    			<c:set var="MentoReadyList" value="${requestScope.MentoReadyList}"></c:set>
				 <h3>MentoReadyList</h3>
                           <table> 
                               <tr>
                                  <th>멘토이메일</th> 
                                  <th>이름</th>
                                  <th>핸드폰번호</th>
                                  <th style="width: 30pc;">경력</th>
                                  <th>멘토코드명</th>
                               </tr>
				 <c:forEach var="mento" items="${MentoReadyList}">
					<tr>
						<td>${mento.mento_email}</td>
						<td>${mento.name}</td>
						<td>${mento.hp}</td>
						<td>${mento.career}</td>
						<td>${mento.staus_name}</td>
				<c:choose>
					<c:when test="${mento.staus_name =='멘토신청' }">
						<td><a href="mentoOk.admin?mento_email=${mento.mento_email}" class="btn btn-green btn-flat" style="margin:5px;">멘토수락</a></td>
						<td><a href="mentoDelete.admin?mento_email=${mento.mento_email}" class="btn btn-green btn-flat"style="margin:5px;">멘토거절</a></td>
					</c:when>
					<c:when test="${mento.staus_name =='멘토' }">
						<td><a href="mentoDrop.admin?mento_email=${mento.mento_email}" class="btn btn-green btn-flat"style="margin:5px;">멘토삭제</a></td>
					</c:when>
				</c:choose>	
				</tr>
				</c:forEach>
				</table>
				<%
                 }else{
                %>    
 				 
				<span class="title">아직 참여중인 멘토가 없습니다.</span>
				<%
                  }
               %>
				</div>
				</div>
			</div>
		</div>
		<div style="height: 151px;">&nbsp;</div></div>
	
	
	
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