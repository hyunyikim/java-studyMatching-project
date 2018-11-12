<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토 소개</title>
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/imagehover.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mentoDetail.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	var mtemail = '${mentodetail.mtemail}'; 
	var mem_email = '${sessionScope.email}';

	function commentList(){
		$.ajax({
			url:"<%=request.getContextPath()%>/CommentsList",
			data:{
				"mtemail":mtemail
			},
			dataType: "json",
			success:function(data){
				var a = '';
				var b = '';
				 $.each(data, function(index, value) {
				 $('#commentDiv').empty();
					a += '<div class="media mb-4" id="mentoCommentContent">';
					a += '<img style="width:50px; height:50px;" class="d-flex mr-3 rounded-circle" src="<%=request.getContextPath()%>/img/profie/'+value.pimg+'">';
					a += '<div class="media-body">';
					a += '<div><div id="commentH" style="display:inline-block; width:49%;">'
							+'<h5 class="mt-0" style="display:inline;">'+value.mname+' 님</h5></div>';
					if(mem_email == value.mbemail){
				 		a += '<div class="commentDeleteBtn" style="display:inline-block; width:49%; text-align:right;"><input type="button" id="';
				 		a += value.num;
				 		a += '" class="btn btn-green btn-flat commentDeleteBtn" onclick="commentDelete('+value.num+')" name="commentDeleteBtn" value="삭제"></div>';	
				 	}
						a+='</div>'+value.content+'</div></div><hr>';
					
				 });
			 	$("#commentDiv").html(a);
			}
		});
	}

	function commentInsert() {
		$.ajax({
			url : "<%=request.getContextPath()%>/CommentsAdd",
			type : 'POST',
			data : {
				"mtemail" : mtemail,
				"content" : $('#mentoCommentTxt').val()
			},
			success : function(data) {
				console.log(data);
				if (data == 1) {
					commentList(); //댓글 작성 후 댓글 목록 reload
					$('[name=content]').val('');
				} 
			}
		});
	}
	
	function commentDelete(num){
		$.ajax({
			url:"<%=request.getContextPath()%>/CommentsDelete",
			type : 'POST',
			data : {
				"comments_num" : num
			},
			success : function(data) {
				console.log("댓글 비동기 delete 의 결과 값 : " + data);
				if(data == 1){
					commentList();
				}
			}
		});
	}
	
	$(document).ready(function() {
		 commentList();  
		 $('#mentoCommentBtn').click(function(){
			 $('#mentoCommentBtn').click(function(){
		          if(mem_email==""||mem_email==null){
		             alert("로그인 후 이용해주세요.");
		          }else{
		             if($('#mentoCommentTxt').val()=="") {
		                alert("후기를 입력해주세요. ");
		            } else {
		               commentInsert();
		            }
		          }
			});
		 });
		 /* $('.commentDeleteBtn').click(function(){
			alert("commentDelete 버튼을 눌렀다.");
			 commentDelete(this); 
		 }); */
	});
	
</script>
</head>
<body>
<body>
	<%
		pageContext.include("/view/form/nav.jsp");
	%>

	<!-- Page Content -->
	<div class="container">
		<div class="row" id="studyDetailRow">
			<div class="col-lg-12">
				<c:set var="mentowwdetail" value="${requestScope.mentodetail}" />
				<div id="memtoImgDiv" style="text-align: center;">
					<img class="img-fluid rounded" src="<%=request.getContextPath()%>/img/profie/${mentodetail.pimg}"
						alt="" id="studyImg">
					<!-- 스터디 이미지는 서버에 저장된 경로로 불러오기  -->
				</div>
				<hr>
				<h2 id="studySubject">
					<br>${mentodetail.name}</h2>
				<hr>
				<p id="mentoIntroduce">
					멘토 이메일 : ${mentodetail.mtemail}<br> <br>
					${mentodetail.career}
				</p>
				<hr>
				<div class="card my-4" id="mentoCommentDiv">
					<h5 class="card-header" id="mentoCommenth">멘토 후기를 남겨주세요.</h5>
					<div class="card-body">
						<form
							action="<%=request.getContextPath()%>/view/mento/CommentsAdd.do"
							method="post" name="frmComments" id="frmComments">
							<input type="hidden" value="${mentodetail.mtemail}"
								name="mtemail">
							<div class="form-group">
								<textarea class="form-control" rows="3" id="mentoCommentTxt"
									name="content"></textarea>

								<input type="button" class="btn btn-green btn-flat"
									value="후기 작성" id="mentoCommentBtn">
							</div>
						</form>
					</div>
				</div>
				<!-- 코멘트 작성하기 -->
				<!-- 코멘트 부분  -->
				<div id="commentDiv"></div>
				<!-- 코멘트 부분  -->
			</div>
		</div>
	</div>
	<!-- /.container -->
	<%
		pageContext.include("/view/form/footer.jsp");
	%>
</body>
</html>