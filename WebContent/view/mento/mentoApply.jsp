<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토 신청하기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
	href="<%=request.getContextPath()%>/css/photo.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/form.css">
<script type="text/javascript">
function fnChkByte(obj, maxByte) {
    var str = obj.value;
    var str_len = str.length;
    var rbyte = 0;
    var rlen = 0;
    var one_char = "";
    var str2 = "";
    for (var i = 0; i < str_len; i++) {
        one_char = str.charAt(i);
        if (escape(one_char).length > 4) {
            rbyte += 2; //한글2Byte
        } else {
            rbyte++; //영문 등 나머지 1Byte
        }
        if (rbyte <= maxByte) {
            rlen = i + 1; //return할 문자열 갯수
        }
    }
    if (rbyte > maxByte) {
    	 alert("한글 " + (maxByte/2) + "자 / 영문 " + maxByte + "자를 초과 입력할 수 없습니다.");
        str2 = str.substr(0, rlen); //문자열 자르기
        obj.value = str2;
        fnChkByte(obj, maxByte);
    } else {
        document.getElementById('byteInfo').innerText = rbyte;
    }
}
$(document).ready(function(){
	$(".cancel").click(function(){
		location.href="<%=request.getContextPath()%>/view/main.jsp";
	});
	$(".mentoCareer").click(function(){
		if(this.value=="경력 및 자기소개를 작성해 주세요."){
			this.value="";
		}
	});
});


</script>
<style type="text/css">
	.btn-box{text-align:center;}
	.btn{background-color: #F08B41; color:#fff; height: 40px; text-align: center; resize: none;}
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
			<div class="container">
				<div class="div-form">
					<h2>멘토 신청하기</h2>
					<!-- 폼 보내주는 서블릿 적어줘야됨 -->
					<form action="MentoApplyList.do" name="mentoApply" class="mentoApply" method="post">
						<div><textarea class="mentoCareer" name="mentoCareer" cols="60" rows="10" onKeyUp="fnChkByte(this,'3000')">경력 및 자기소개를 작성해 주세요.</textarea>
						<span id="byteInfo">0</span>/3000Byte</div>
						<div class="btn-box"><input type="submit" class="btn" value="신청">&nbsp;
						<input type="button" class="btn cancel" value="취소"></div>
					</form>
				</div>
			</div>
			<div style="height: 200px;">&nbsp;</div>
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