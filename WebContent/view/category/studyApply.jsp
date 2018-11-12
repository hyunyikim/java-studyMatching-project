<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 신청</title>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/imagehover.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script>
  </script>
  <style>
  	#studyApplyWell{
  	background-color: #5F6B7A;
  	color: #FFF;
  	}
  	#spanEnough{
  	color: #F08B41;
  	font-size: 20px;
  	}
  	#wellSpan{
  	padding-left: 70%;
  	}
  	#studyApplyContainer{
  	background-color: #FFF;
  	padding: 25px;
  	}
  	#studyApplyMentoImg{
  	width: 200px;
  	height: auto;
  	}
  	#studyApplyTr td{
  	vertical-align: middle;
  	}
  	#studyApplyTr td{
  	vertical-align: middle;
  	}
  </style>
</head>
<body>
 <!-- navbar -->
<% pageContext.include("/view/form/nav.jsp"); %>   
	<div class="container" id="studyApplyContainer">
	  <div class="well" id="studyApplyWell"><span id="spanEnough"><b>ENOUGH</b></span>
	  	<span id="wellSpan">
	  	 참여신청 &nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp; 참여신청 완료
	  	</span>
	  </div>
	  <br><br>
	   <table class="table">
    <thead>
      <tr>
        <th colspan="2">스터디</th>
        <th>일정</th>
      </tr>
    </thead>
    <tbody>
      <tr id="studyApplyTr">
      	<td><img id="studyApplyMentoImg" src="<%=request.getContextPath()%>/img/studyImg/${requestScope.study.study_img}"></td>
        <td><span style="color:#F08B41;">${requestScope.study.study_loc_code} | ${requestScope.study.study_level_code}</span><br><span>${requestScope.study.study_name}</span></td>
        <td> ${requestScope.study.start_date} ~  ${requestScope.study.end_date}</td>
      </tr>	
    </tbody>
  </table>
  <br>
  	<div style="text-align:right">선택하신 스터디 정보가 맞으시면 <b>결제 버튼</b>을 눌러주세요!&nbsp;&nbsp;&nbsp;&nbsp;
  	<button type="button" class="btn btn-green btn-flat" data-toggle="modal" data-target="#myModal">결제하기</button></div>
	
		<!--모달창 -->
	 <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="text-align:center">결제 내역</h4>
        </div>
        <div class="modal-body">
          <p style="text-align:center">결제 가격 :  ${requestScope.study.price}<br>결제하시겠습니까?</p>
        </div>
        <div class="modal-footer">
        <form name="studyApplyForm" action="PaymentAdd.do" method="post">
	  	<input type="hidden" value="${requestScope.study.study_num}" name="study_num">
	  	<input type="hidden" value="${requestScope.study.price}" name="price">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-default" id="studyPayBtn"  value="Ok">
          </form>
        </div>
      </div>
    </div>
  </div>

    
	</div>
<!-- footer -->
<% pageContext.include("/view/form/footer.jsp"); %>
</body>
</html>