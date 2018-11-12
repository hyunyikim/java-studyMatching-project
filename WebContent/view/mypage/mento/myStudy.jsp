<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
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
jQuery(document).ready(function() {
	var email="${email}";
	var memberGrade="${member_grade_code}"
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
		}
    if(!memberGrade=="M02"){
    	alert("멘토만 사용가능한 페이지 입니다.");
        location.href="<%=request.getContextPath()%>/view/main.jsp";
    }
    
    $('.mentoStudyUpdateBtn').click(function(){
    	var study_num = $(this).attr('id');
    	location.href="<%=request.getContextPath()%>/mentoMyStudyUpdateList.mypage?study_num="+study_num;
    });
    
    $('.mentoStudyDeleteBtn').click(function(){
    	var study_num = $(this).attr('id');
    	if(confirm("선택하신 스터디를 삭제하시겠습니까?") == true){
        	location.href="<%=request.getContextPath()%>/view/category/StudyDelete.do?study_num="+study_num;
    	} else {
    		return;
    	}
    });
    
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
.btn-green{margin:5px;}
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
				pageContext.include("/view/mypage/mento/mentoMyPage.jsp");
			%>
			<!-- mypage Content -->
			<div class="container" style="background-color: #fff; padding: 10px 5px;">
				
				<div class="myContent">
						<%
							if (result != "false") {
						%>
						<c:set var="myStudyList" value="${requestScope.myStudyList}"></c:set>
						
						<h3>myStudyList</h3><br>
						<table>
							<tr>
								<th>스터디이름</th>
								<th>소개글</th>
								<th>시작날짜</th>
								<th>종료날짜</th>
								<th>가격</th>
								<th>과목명</th>
								<th>레벨명</th>
								<th>지역명</th>
								<th>&nbsp;</th>
							</tr>
							<c:forEach var="study" items="${myStudyList}">
							<c:set var="study_num" value="${study.study_num}"/>
								<tr>
									<td>${study.study_name}</td>
									<td>${study.study_write}</td>
									<td>${study.start_date}</td>
									<td>${study.end_date}</td>
									<td>${study.price}</td>
									<td>${study.subject_name}</td>
									<td>${study.level_name}</td>
									<td>${study.loc_name}</td>
									<td><input type="button" class="btn btn-green mentoStudyUpdateBtn" id="${study.study_num}" value="수정"/>
									<input type="button" class="btn btn-green mentoStudyDeleteBtn" id="${study.study_num}" value="삭제"/>
									</td>
								</tr>
							</c:forEach>
						</table>
						<%
							} else {
						%>
						<span class="title">원하는 스터디를 추가해보세요. </span>
						<%
							}
						%>
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