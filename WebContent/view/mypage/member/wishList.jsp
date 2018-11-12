<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<%
	String result = (String) request.getAttribute("result");
%>

<script type="text/javascript">
var email="${email}";
jQuery(document).ready(function() {
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
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
					pageContext.include("/view/mypage/member/memMyPage.jsp");
				%>

				<!-- mypage Content -->
				<div class="myContent-box">
					<div class="myContent">
						<%
							if (result != "false") {
						%>
						<c:set var="wishlist" value="${requestScope.myWishList}"></c:set>
						<h3>mywishList</h3>
						<table>
							<tr>
								<th>스터디이름</th>
								<th>시작날짜</th>
								<th>종료날짜</th>
								<th>과목명</th>
								<th>레벨명</th>
								<th>지역명</th>
								<th>&nbsp;</th>
							</tr>

							<c:forEach var="wish" items="${myWishList}">
								<tr>
									<td>${wish.study_name}</td>
									<td>${wish.start_date}</td>
									<td>${wish.end_date}</td>
									<td>${wish.subject_name}</td>
									<td>${wish.level_name}</td>
									<td>${wish.loc_name}</td>
									<!-- 취소버튼 클릭시 스터디 탈퇴 및 환불 관련 서블릿 만들고 매핑해주기
                                    		위에 j쿼리 수정 필요-->
									<td><a href="wishDelete.mypage?study_num=${wish.study_num}"  style ="margin:5px; " class="btn btn-green btn-flat">삭제</a></td></td>
								</tr>
							</c:forEach>

						</table>
						<%
							} else {
						%>
						<span class="title">원하는 스터디를 추가해 보세요.</span>
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