<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String result = (String) request.getAttribute("result");
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
var memberGrade="${member_grade_code}";


jQuery(document).ready(function() {
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
		}
    if(!memberGrade=="M02"){
    	alert("멘토만 사용가능한 페이지 입니다.");
        location.href="<%=request.getContextPath()%>/view/main.jsp";
    }
    
    $('#studySelect').change(function(){
    	var study_num = $('#studySelect option:selected').val();
    	$.ajax({
    		url : "<%=request.getContextPath()%>/MentoStudyMember",
			type : 'get',
			data : {
				'study_num' : study_num
			},
			dataType:"json",
			success:function(data){
				//console.log(data);
				//console.log(typeof(data));
				if(typeof data === 'boolean'){
					$('#studyMemberListTable').html("아직 등록한 회원이 없습니다.");
				} else {
					var a = '';
					a += '<table><tr><th>회원 이메일</th><th>회원 이름</th><th>핸드폰 번호</th></tr>';
					$(data).each(function(index, obj){
						a += '<tr><td>'+obj.email+'</td><td>'+obj.name+'</td><td>'+obj.hp+'</td></tr>';
					});
					a += '</table>';
					$('#studyMemberListTable').html(a);
				}
			}
    	}); 
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
				<div class="myContent-box">
					<div class="myContent">
						<%
							if (result != "false") {
						%>
						<h3>myStudyMemberList</h3>
						<c:set var="studyList" value="${requestScope.studyList}"></c:set>
						<label for="studySelect">스터디를 선택하세요.</label>
						<select id="studySelect">
							<option value="">스터디 제목</option>
							<c:forEach var="study" items="${studyList}">
								<option value="${study.study_num}">${study.study_name}</option>
							</c:forEach>
						</select>
						<div id="studyMemberListTable"></div>
						<%
							} else {
						%>
						<span class="title">아직 생성한 스터디가 없습니다. </span>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div style="height: 151px;">&nbsp;</div>
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