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
a.selected{color:#aeb5be;border-bottom:3px solid #fff;}
.profileheader{padding:30px; border-bottom:1px solid #ccc}
.userphoto{background-image:url('<%=request.getContextPath()%>/img/profie/<%=member_img%>');}
.profilephoto{background-image:url('<%=request.getContextPath()%>/img/profie/<%=member_img%>');}
.edit-photo-button-label{margin-left: 15px;}
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
						<a class="tab" href="<%=request.getContextPath()%>/myStudy.mypage">내 스터디</a> 
						<a class="tab" href="<%=request.getContextPath()%>/myWish.mypage">찜한 스터디</a> 
						<a class="tab" href="<%=request.getContextPath()%>/myProfile.mypage">내 프로필</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>