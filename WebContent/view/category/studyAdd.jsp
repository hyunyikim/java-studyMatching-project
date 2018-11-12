<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 신청</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		var dateFormat = "yy-mm-dd", from = $("#start_date").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 1,
			dateFormat : "yy-mm-dd"
		}).on("change", function() {
			to.datepicker("option", "minDate", getDate(this));
		}), to = $("#end_date").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 1,
			dateFormat : "yy-mm-dd"
		}).on("change", function() {
			from.datepicker("option", "maxDate", getDate(this));
		});

		function getDate(element) {
			var date;
			try {
				date = $.datepicker.parseDate(dateFormat, element.value);
			} catch (error) {
				date = null;
			}
			return date;
		}

		$('#studyAddBtn').click(function() {
			if ($('.studyAddText').val() == "" || $('#study_img').val() == "") {
				alert("폼을 모두 입력하세요.");
			} else if (isNaN(($('#price')).val())) {
				alert("가격을 올바르게 입력해주세요.");
				$('#price').val("");
				$('#price').focus();
			} else {
				$('#studyAddFrm').submit();
			}
		});
	});
</script>
<style>
#studyApplyWell {
	background-color: #5F6B7A;
	color: #FFF;
}

#spanEnough {
	color: #F08B41;
	font-size: 20px;
}

#wellSpan2 {
	padding-left: 80%;
}

#studyApplyContainer {
	background-color: #FFF;
	padding: 25px;
}

#studyApplyMentoImg {
	width: 200px;
	height: auto;
}

#studyApplyTr td {
	vertical-align: middle;
}

#studyApplyTr td {
	vertical-align: middle;
}

.studyAddText {
	margin-bottom: 10px;
}

#start_date, #end_date {
	display: inline;
	width: 49.5%;
}

#studyAddDateDiv {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container" id="studyApplyContainer">

		<div class="well" id="studyApplyWell">
			<span id="spanEnough"><b>ENOUGH</b></span> <span id="wellSpan2">
				스터디 만들기 </span>
		</div>
		<br> <br>

		<form action="<%=request.getContextPath()%>/view/category/StudyAdd.do"
			method="post" name="studyAddFrm" enctype="multipart/form-data"
			id="studyAddFrm">
			<input type="hidden" name="mento_email"
				value="${session.mento_email}" /> <input
				class="form-control studyAddText" placeholder="스터디 제목을 입력하세요."
				type="text" id="study_name" name="study_name" />
			<textarea class="form-control studyAddText"
				placeholder="스터디 소개글을 입력하세요." cols="3" rows="3" id="study_write"
				name="study_write"></textarea>
			<div id="studyAddDateDiv">
				<input class="form-control studyAddText"
					placeholder="스터디 시작날짜를 입력하세요." type="text" id="start_date"
					name="start_date" /> <input class="form-control studyAddText"
					placeholder="스터디 종료날짜를 입력하세요." type="text" id="end_date"
					name="end_date" />
			</div>
			<input class="form-control studyAddText" placeholder="스터디 가격을 입력하세요."
				type="text" id="price" name="price" /> <select
				name="study_subject_code" id="study_subject_code"
				class="form-control studyAddText">
				<option>스터디 과목을 선택하세요.</option>
				<option value="P01">JAVA</option>
				<option value="P02">C</option>
				<option value="P03">C++</option>
			</select> <select name="study_level_code" id="study_level_code"
				class="form-control studyAddText">
				<option>스터디 레벨을 선택하세요.</option>
				<option value="SL00">입문</option>
				<option value="SL01">초급</option>
				<option value="SL02">중급</option>
				<option value="SL03">고급</option>
			</select> <select name="study_loc_code" id="study_loc_code"
				class="form-control studyAddText">
				<option>스터디 지역을 선택하세요.</option>
				<option value="SC">신촌</option>
				<option value="HD">홍대</option>
				<option value="GN">강남</option>
				<option value="NW">노원</option>
				<option value="GD">건대</option>
				<option value="BD">분당</option>
				<option value="AY">안양</option>
				<option value="IS">일산</option>
			</select> <input type="file" class="form-control studyAddText"
				name="study_img" id="study_img"> <br>
			<div style="text-align: right">
				입력하신 스터디 정보가 맞으시면 <b>스터디 만들기 버튼</b>을 눌러주세요!&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-green btn-flat" id="studyAddBtn"
					value="스터디 만들기">
			</div>
		</form>
	</div>
</body>
</html>