<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>멘토 소개</title>
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/imagehover.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mento.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>
<% pageContext.include("/view/form/nav.jsp"); %>
  <!--Mento-->
  <section id="courses" class="section-padding">
  
    <div class="container">
      <div class="row">
        <div class="header-section text-center">
          <h2>멘토 소개</h2>
          <p>각양각색의 코딩 멘토들과 함께 즐겁게 공부하세요!</p>
          <hr class="bottom-line">
        </div>
      </div>
    </div>
 
    <div class="container">
      <div class="row">
      <c:set var="mentoList" value="${requestScope.mentolist}"/>
		<c:forEach var="mento" items="${mentoList}"> 
	        <div class="col-sm-5 padleft-right" style="margin: 30px; text-align: center; background-color: #F08B41;">
	          <figure class="imghvr-fold-up">           
	          	 <img src="<%=request.getContextPath()%>/img/profie/${mento.pimg}" class="img-responsive" id="mentoProfileImg"><!--  이미지 경로 받아와야함  -->
	            <figcaption>
	              <h3>멘토 ${mento.name}</h3>
	              <p>${mento.career}</p>
	            </figcaption>
	            <a href="<%=request.getContextPath()%>/view/mento/MentoDetail.do?mtemail=${mento.mtemail}"></a><!--  멘토 상세 페이지로 정보 가지고 이동-->
	          </figure>
	        </div>
        </c:forEach> 
      </div>
    </div>
  </section>
  <!--/ Courses-->
<% pageContext.include("/view/form/footer.jsp"); %>
</body>
</html>