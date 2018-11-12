<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<!-- =======================================================
    Theme Name: Mentor
    Theme URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
<style>
.footer-div{background-color:#1e1e1e;
   left:0px;
   bottom:0px;
   height:151px;
   width:100%;
   margin-top: 10px;
   }
.footer{text-align:center; padding:30px 50px; }
.footer li{position:relative; display:inline; padding:0 7px; white-space:nowrap;}
.footer li:before{content:' '; width:1px; height:10px; position:absolute; left:0; top:2px; background-color: #fff;}
.footer li:first-child:before{width:0;}
.footer address{padding-top:15px;}
</style>
</head>
<body >
<div class="footer-div">
	<!-- <div class="container"> -->
		<div class="row">
			<div class="footer">
				<ul class="clearfix">
					<li><a href="<%=request.getContextPath()%>/view/info/terms.jsp">사이트 이용약관</a></li>
					<li><a href="<%=request.getContextPath()%>/view/info/participant-terms.jsp">사이트 운영원칙</a></li>
					<li><a href="<%=request.getContextPath()%>/view/info/policy.jsp"><strong>개인정보 취급방침</strong></a></li>
					<li><a href="<%=request.getContextPath()%>/view/info/qna.jsp">자주묻는 질문</a></li>
				</ul>
				<address>
					<em>Copyright &copy;<a href="#"><strong>Enough</strong></a>All Rights Reserved.</em>
				</address>
			</div>
	<!-- 	</div> -->
	</div>
	</div>
</body>
</html>