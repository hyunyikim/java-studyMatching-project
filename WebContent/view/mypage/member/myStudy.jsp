<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
var email="${email}";
$(document).ready(function() {
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
      }
    $(".study-cancel").click(function(){
    	
    });
});

function studyCancel(study_num){
	console.log(study_num);
	$.ajax({
        url: "<%=request.getContextPath()%>/payDelete.mypage",
        type: "Post",
        data: {"study_num":study_num},
        dataType : "text",
        success: function(data) {         
      	  console.log(data);
      	  if(data=="true"){
      		  alert("수강이 취소 되었습니다.");
      	  }
      	location.href="<%=request.getContextPath()%>/myStudy.mypage";
        },
        error: function(msg, error) {
            alert(error);
        }
});}


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
					pageContext.include("/view/mypage/member/memMyPage.jsp");
				%>
				<!-- mypage Content -->
				<div class="myContent-box">
					<div class="myContent">
						<%
							if (result != "false") {
						%>
						<c:set var="myStudyList" value="${requestScope.myStudyList}"></c:set>

						<h3>myStudyList</h3><br>
						<table >
							<tr>
								<th>스터디번호</th>
								<th>스터디이름</th>
								<th>시작날짜</th>
								<th>종료날짜</th>
								<th>과목명</th>
								<th>레벨명</th>
								<th>지역명</th>
								<th>&nbsp;</th>
							</tr>
							<c:forEach var="study" items="${myStudyList}">
								<tr>
									<td>${study.study_num}</td>
									<td>${study.study_name}</td>
									<td>${study.start_date}</td>
									<td>${study.end_date}</td>
									<td>${study.subject_name}</td>
									<td>${study.level_name}</td>
									<td>${study.loc_name}</td>
									<td><input type="button" class="study-cancel btn btn-green" style ="margin:5px; "onclick="studyCancel(${study.study_num})" value="수강취소">
									</td>
								</tr>
							</c:forEach>
						</table>
						<%
							} else {
						%>
						<span class="title">아직 참여중인 스터디가 없습니다.</span>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div style="height: 151px;">&nbsp;</div>
		</div>
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