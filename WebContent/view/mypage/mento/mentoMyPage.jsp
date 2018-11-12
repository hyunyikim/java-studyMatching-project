<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		var uri = window.location.pathname;
		console.log(uri);
		if ($(a).hasClass(uri) == true) {
			console.log($(a[this]));
			$(a[this]).css('border-bottom', '3px solid #fff');
		}
	});
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
		url('<%=request.getContextPath()%>/img/profie/${member_img}');
}

.profilephoto {
	background-image:
		url('<%=request.getContextPath()%>/img/profie/${member_img}');
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
						href="<%=request.getContextPath()%>/mentoMyStudy.mypage">생성한 스터디</a> <a class="tab"
						href="<%=request.getContextPath()%>/mentoStudyMember.mypage">스터디 회원</a> <a
						href="<%=request.getContextPath()%>/mentoProfile.mypage">내 프로필</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>