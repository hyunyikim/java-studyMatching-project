<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String member_img=(String)session.getAttribute("member_img"); %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
<style type="text/css">
a.selected {
	color: #aeb5be;
	border-bottom: 3px solid #fff;
}

.profileheader {
	padding: 30px;
	border-bottom: 1px solid #ccc
}

.userphoto {
	background-image:
		url('<%=request.getContextPath()%>/img/profie/<%=member_img%>');
}

.profilephoto {
	background-image:
		url('<%=request.getContextPath()%>/img/profie/<%=member_img%>');
}

.edit-photo-button-label {
	margin-left: 15px;
}
</style>
</head>
<body>
	<!-- mypage Header -->
	<div class="my-page">
		<div class="container my-page-header">
			<div class="userphoto"></div>
			<div class="tabs">
				<div class="username">
					<h3 style="color: #fff">${name}</h3>
				</div>
				<div id="tab-box">
					<a class="tab"
						href="<%=request.getContextPath()%>/adminMemberList.admin">회원목록</a> <a class="tab"
						href="<%=request.getContextPath()%>/adminMentoList.admin">멘토목록</a> 
						<a class="tab" href="<%=request.getContextPath()%>/adminStudyList.admin">스터디목록</a>
						<a class="tab" href="<%=request.getContextPath()%>/adminPage.admin">내 프로필</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>