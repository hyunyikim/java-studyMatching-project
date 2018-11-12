<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="org.json.simple.JSONObject"%>

<%@page import="org.json.simple.parser.JSONParser"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>example</title>

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

<style>
.tsize {
	font-size: 13px;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	$(function() {

		$
				.ajax({

					url : "xmlToJson.jsp",

					success : function(data) {

						var jsonobj = JSON.parse(data);

						console.log(jsonobj);

						console.log(typeof (jsonobj));

						var start = "";

						$
								.each(
										jsonobj,
										function(key, obj) {

											var jobarr = obj.jobs.job;

											$
													.each(
															jobarr,
															function(index, obj) {

																console
																		.log(obj.company.name.content); // 회사명 

																console
																		.log(obj.position.title); // 잡 타이틀 

																console
																		.log(obj.position["required-education-level"].content); // 지원자격 

																console
																		.log(obj.position["job-type"].content); // 근무조건, 계약신분 

																console
																		.log(obj.salary.content); // 근무조건, 급여 

																console
																		.log(obj["expiration-timestamp"]); // 마감일자 

																start += "<div class='jobList' style='margin:20px 0; padding:10px 15px; background-color:#fff;'><div class='jobTitle'style='display:inline-block;'>";

																start += "<div class='companyname' style='margin:5px 0; font-size:12px;' >"
																		+ obj.company.name.content
																		+ "</div>";

																start += "<div class='tsize jobname' style='margin:4px 0;'><a href='"+obj.url+"'><strong>"
																		+ obj.position.title
																		+ "</strong></a></div></div>";

																start += "<div style='display:inline-block; float:right;'>";

																start += "<div class='tsize eligibility'>지원자격 "
																		+ obj.position["required-education-level"].content
																		+ "</div>";

																start += "<div class='tsize conditions'>근무조건 "
																		+ obj.position["job-type"].content
																		+ "</div>";

																start += "<div class='tsize salary'>급여 "
																		+ obj.salary.content
																		+ "</div>";

																var timestamp = obj["expiration-timestamp"] * 1000;

																var date = new Date(
																		timestamp);

																var formatdt = date
																		.getFullYear()
																		+ "-"
																		+ Number(date
																				.getMonth() + 1)
																		+ "-"
																		+ date
																				.getDate();

																start += "<div class='tsize closing-date'>마감일자 "
																		+ formatdt
																		+ "</div></div><div style='clear:right;'>&nbsp;</div></div>";

															}); // for-each 

											$('#jobTable').append(start);

										});

					},
					error : function(xhr) {

						alert(xhr.status);

					}

				}); // ajax 

	});
</script>

<style type="text/css">
tr {
	padding: 5px;
}
</style>

</head>

<body>

	<!-- navbar -->

	<%
		pageContext.include("/view/form/nav.jsp");
	%>

	<div class="container">

		<div class="title" style="text-align: center">

			<h2>구인 구직</h2>

			<p style="font-size: 12px;">클릭시 사람인 페이지로 넘어갑니다.</p>

		</div>

		<div id="jobTable"></div>

	</div>

	<!-- footer -->

	<%
		pageContext.include("/view/form/footer.jsp");
	%>

</body>

</html>