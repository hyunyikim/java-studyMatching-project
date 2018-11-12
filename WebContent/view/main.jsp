<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
var SUBJECT_CODE="P01";
	$(document).ready(function(){
		studylist(SUBJECT_CODE);
		$(".studyAll").click(function(){
			console.log(SUBJECT_CODE);
			if(SUBJECT_CODE=="P01"){
				location.href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P01";
			}
			if(SUBJECT_CODE=="P02"){
				location.href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P02";
			}
			if(SUBJECT_CODE=="P03"){
				location.href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P03";
			}
		});
		
		$(".studyTab").click(function(){
			console.log($(this).text());
			if($(this).text()=="JAVA"){
				SUBJECT_CODE="P01";
				studylist(SUBJECT_CODE);
			}
			if($(this).text()=="C"){
				SUBJECT_CODE="P02";
				studylist(SUBJECT_CODE);
			}
			if($(this).text()=="C++"){
				SUBJECT_CODE="P03";
				studylist(SUBJECT_CODE);
			}
		});
	});
	
	
	
	function studylist(SUBJECT_CODE){
		$.ajax({
	              url: "<%=request.getContextPath()%>/view/testList.jsp",
	              type: "Post",
	              data: {"SUBJECT_CODE":SUBJECT_CODE},
	              dataType : "json",
	              success: function(data) {         
	            	  console.log(data);
	            	  $('.studyList').empty();
	            	  var html="<div class='row'>";
	                  $.each(data,function(index,obj){
	                	  if(index==3){return false;}
	                	  console.log(index+", "+obj.study_num);
	                	  html+="<div class='col-sm-4'>"
	                	  				+"<a href='<%=request.getContextPath()%>/view/category/studyDetail.do?study_num="
	                	  				+obj.study_num+"&nextPage=studyDetail'>"
	                	  				+"<div class='test-box' style='background-color: #fff; height: 491px;'>"
	                	  				+"<div class='study-img-box'><div class='study-img'>"
	                	  				+"<img class='studyimg' src='<%=request.getContextPath()%>/img/studyImg/"
										+ obj.study_img
										+ "'></div></div>"
										+ "<span class='test-box-title' style='display:block;text-align: center;'><h3>"
										+ obj.study_name
										+ "</h3></span><span class='test-box-content'>"
										+ obj.study_write
										+ "</span></div></div>";
										});
						html += "</div>";
						$('.studyList').html(html);
					},
					error : function(msg, error) {
						alert(error);
					}
				});
	}
</script>
<style type="text/css">
.page-heder {
	width: 100%;
	height: 403px;
	background-color: #F08B41;
}

.youtube-play {
	margin-top: 1px;
	margin-bottom: 1px;
}

.study-img-box {
	width: 100%;
	height: 180px;
	object-fit: contain;
}

.study-img {
	height: 100%;
	background-size: contain;
	background-color: #F08B41;
}

.studyimg {
	width: 100%;
	height: 180px;
	object-fit: contain;
}

.texts {
	font-size: 38px;
	margin: 70px 20px;
}

.test-box-content {
	display: block;
	margin: 20px 10px 0;
	text-align: center;
}

.bold {
	font-weight: 900;
}

.studyAll {
	cursor: pointer;
	width: 100px;
	height: 40px;
	padding-top: 5px;
	border: 3px solid #F08B41;
	background-color: #fff;
	text-align: center;
}

.studyTab, .studyTab:hover, .studyTab:focus {
	border: 0;
	background-color: #F8F8F8;
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


	<div class="row" style="margin-bottom: 100px;">
		<div class="col-sm-12">
			<div class="page">
				<!-- header youtub영상 -->
				<div class="page-heder">
					<div class="container">
						<div class="youtubeBox">
							<iframe width="100%" height="401" class="youtube-play"
								frameborder="0"
								src="https://www.youtube.com/embed/SESuctdE9vM?amp;autoplay=1"></iframe>
						</div>
					</div>
				</div>
				<!-- text content -->
				<div class="text-content">
					<div class="container">
						<div class="texts">
							<span class="bold" style="color: #F08B41;">ENOUGH는</span><br />
							<span class="discript">여러&nbsp;</span><span class="bold">프로그램밍&nbsp;언어</span><span
								class="discript">를&nbsp;함께&nbsp;배워나가는</span><br /> <span
								class="bold">오프라인&nbsp;스터디&nbsp;매칭&nbsp;플랫폼</span><span
								class="discript">입니다</span><br />
						</div>
					</div>
				</div>
				<!-- content 최근 생성된 스터디목록 -->
				<div class="page-content">
					<div class="container">
						<div class="studyList-tab">
							<div class="row">
								<div class="col-sm-3 bold" style="margin-bottom: 10px">
									<label style="font-size: 30px">카테고리</label><br>
									<button class="studyTab">JAVA</button>
									&nbsp;&nbsp;
									<button class="studyTab">C</button>
									&nbsp;&nbsp;
									<button class="studyTab">C++</button>
								</div>
								<div class="col-sm-6" style="border-bottom: 1px solid #ccc;">&nbsp;</div>
								<div class="col-sm-3">
									<label class="studyAll">전체보기</label>
								</div>
							</div>
						</div>
						<div class="studyList">
							<!--<c:if test="${empty list} }"></c:if>-->
						</div>
					</div>
				</div>
			</div>
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