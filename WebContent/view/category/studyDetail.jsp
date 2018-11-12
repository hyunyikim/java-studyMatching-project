<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Study Detail</title>
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<!-- <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" /> -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/imagehover.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/studyDetail.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/javaScript"
	href="<%=request.getContextPath()%>/js/bootstrap.min.js">
<script>
	$(function() {
		var email="${email}";
		var study_num = ${requestScope.study.study_num};
		console.log("스터디번호"+study_num);
		$.ajax({
			url:"<%=request.getContextPath()%>/WishList",
			data:{
				"study_num":study_num
			},
			dataType: "json",
			success:function(data){
				//console.log(data);
				if(data == true){
					$('#wishListBtn').val("true"); 
					$('#heartBtn').attr("class", "fa fa-heart");
				} else {
					$('#wishListBtn').val("false"); 
					$('#heartBtn').attr("class", "fa fa-heart-o");
				}
			}
		});

		$('#wishListBtn').click(function() {
			if(email== null||email=="") {
		        alert("로그인 후 이용해주세요.");
		        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
			} else {
				var her = $('#wishListBtn').val();
				if(her == "false"){ 
					$('#wishListBtn').val("true"); 
					$('#heartBtn').attr("class", "fa fa-heart");
					//console.log("비어있는 하트를 클릭했을 때 버튼의 value가 true로 바뀌고/ 꽉찬 하트로 바뀐다.");
					$.ajax({
						url:"<%=request.getContextPath()%>/WishListAdd",
						data:{
							"study_num":study_num
						},
						dataType: "html",
						success:function(data){
							console.log(data);
						}
					});		
				} else if (her == "true"){
					$('#wishListBtn').val("false"); 
					$('#heartBtn').attr("class", "fa fa-heart-o");
					//console.log("찬 하트를 클릭했을 때 버튼의 value가 false로 바뀌고/ 빈 하트로 바뀐다.");
					$.ajax({
						url:"<%=request.getContextPath()%>/WishListDelete",
						data:{
							"study_num":study_num
						},
						dataType: "html",
						success:function(data){
							console.log(data);
						}
					});		
				}
			}
		});
		
	});
</script>

</head>
<body>
	<%
		pageContext.include("/view/form/nav.jsp");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<!-- Blog Post -->
				<div class="card mb-4" id="studyDetailDiv">
					<div id="studyDetailImg">
						<img src="<%=request.getContextPath()%>/img/studyImg/${requestScope.study.study_img}"
							id="studyImg" style="width: 650px;">
					</div>
					<div class="header-section text-center">
						<h2 id="studySubject">${requestScope.study.study_name}</h2>
						<hr class="bottom-line">
						<p>${requestScope.study.study_write}</p>
					</div>
				</div>
			</div>
			<!-- Sidebar Widgets Column -->

			<div class="col-md-4" id="studyPayDiv">
				<!-- Side Widget -->
				<div class="card-my-4">
					<h5 class="card-header">
						<br>${requestScope.study.study_name}</h5>
					<hr class="bottom-line">
					<div class="card-body">
						${requestScope.study.start_date}&nbsp;&nbsp;첫 시작<br>${requestScope.study.study_loc_code}&nbsp;&nbsp;|&nbsp;&nbsp;${requestScope.study.study_level_code}<br>
						<br> ${requestScope.study.price}원 <br>
						<br>
						<br>
					</div>
					<button type="button" class="btn btn-green btn-flat"
						id="wishListBtn" value="false">
						찜하기&nbsp;&nbsp;<i class="fa fa-heart-o" aria-hidden="true"
							id="heartBtn"></i>
					</button>
					<!-- <i class="fa fa-heart" aria-hidden="true"></i> -->

					<a
						href="<%=request.getContextPath()%>/view/category/studyDetail.do?study_num=${study.study_num}&nextPage=studyApply"
						id="#nextBtn"><button type="button"
							class="btn btn-green btn-flat">참여 시작하기&nbsp;&nbsp;&rarr;</button></a>
				</div>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	<%
		pageContext.include("/view/form/footer.jsp");
	%>
</body>
</html>
