<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String member_img=(String)session.getAttribute("member_img"); %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>navbar</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>   
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
<!-- =======================================================
    Theme Name: Mentor
    Theme URL: https://bootstrapmade.com/mentor-free-education-bootstrap-theme/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
<style>
#auto-atag:hover{color:#717f86;}
#search{height: 30px;
				padding-top: 5px;}

.my-photo {
   background-image: url(<%=request.getContextPath()%>/img/profie/<%=member_img%>);
}

.myp {
   paddin-top: 0;
   text-align: center;
}

.link:hover,.link:active{text-decoration: none;}

</style>
<script>

$(function(){
    var autocomplete_text = ["JAVA","C","C++"];
    	
         $("#autocomplete").autocomplete({
            source: autocomplete_text,
            open: function(){
                setTimeout(function () {
                    $('.ui-autocomplete').css('z-index', 99999999999999);
                }, 0);
            }
         });
     
         $('#search').click(function(){
        	 console.log("클릭!");
             var $value = $('#autocomplete');
          $.ajax({
                type:"POST",
                url:"<%=request.getContextPath()%>/search",
                data:{value:$('#autocomplete').val()},
                dataType:"html",
                success:function(responsedata){
                  console.log(responsedata);
                  location.href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE="+responsedata;
                },error:function(request,status,error){
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
             });
         });
});

</script>
</head>
<body>
   <!--Navigation bar-->
   <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
         <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
               data-target="#myNavbar">
               <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <div class="navbar-brand"><a class="link" href="<%=request.getContextPath()%>">Enough</a></div>
         </div>
         <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-left">
               <li class="dropdown"><a class="dropdown-toggle"
                  data-toggle="dropdown" href="#">카테고리 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <!-- href에는 각 서블릿 매핑 주소 넣어주기 -->
                     <li><a href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P01">Java</a></li>
                     <li><a href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P02">C</a></li>
                     <li><a href="<%=request.getContextPath()%>/view/category/studyList.do?STUDY_SUBJECT_CODE=P03">C++</a></li>
                  </ul>
               <li><a href="<%=request.getContextPath()%>/view/mento/MentoList.do">멘토소개</a></li>
               <li><a href="<%=request.getContextPath()%>/view/jobposting/saramin6.jsp">구인구직</a></li>
               <li><a href="<%=request.getContextPath()%>/view/form/chart.html">차트</a></li>
               <li>
               <a href="javascript:void(0)" id="auto-atag">
               <input type="text" id="autocomplete">
               <input type="button" class="btn btn-green" id="search" value="검색">
               </a>
               </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="memberLevel">

               <c:if test="${member_grade_code==null}">
                  <li><a
                     href="<%=request.getContextPath()%>/view/form/loginForm.jsp">Login</a></li>
                  <li><a
                     href="<%=request.getContextPath()%>/view/form/signUpForm.jsp">회원가입</a></li>
               </c:if>
               <!-- value="${sessionScope[name]}"
                     ${memberLevelCode=='M01'} -->
               <c:if test="${member_grade_code=='M01'}">
                  <li><a href="<%=request.getContextPath()%>/view/mento/mentoApply.jsp">멘토신청</a></li>
                  <li class="dropdown myp"><a class="dropdown-toggle myp"
                     data-toggle="dropdown" href="#"><div class="my-photo"></div>&nbsp;&nbsp;&nbsp;<span>${name}</span><span
                        class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a
                           href="<%=request.getContextPath()%>/myStudy.mypage">내 스터디</a></li>
                        <li><a
                           href="<%=request.getContextPath()%>/myWish.mypage">찜 스터디</a></li>
                        <li><a
                           href="<%=request.getContextPath()%>/myProfile.mypage"> 내 프로필</a></li>
                     </ul>
                  <li><a
                     href="<%=request.getContextPath()%>/view/form/logOut.jsp">로그아웃</a></li>
               </c:if>
               <c:if test="${member_grade_code=='M02'}">
                  <li><a href="<%=request.getContextPath()%>/view/category/studyAdd.jsp">스터디만들기</a></li>
                  <li class="dropdown"><a class="dropdown-toggle myp"
                     data-toggle="dropdown" href="#"><div class="my-photo"></div>${name}
                        <span class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/mentoMyStudy.mypage">생성한 스터디</a></li>
                        <li><a href="<%=request.getContextPath()%>/mentoStudyMember.mypage">스터디 회원 목록</a></li>
                        <li><a href="<%=request.getContextPath()%>/mentoProfile.mypage">내 프로필</a></li>
                     </ul>
                  <li><a
                     href="<%=request.getContextPath()%>/view/form/logOut.jsp">로그아웃</a></li>
               </c:if>
               <c:if test="${member_grade_code=='M00'}">

                  <li class="dropdown myp"><a class="dropdown-toggle myp"
                     data-toggle="dropdown" href="#"><div class="my-photo"></div>&nbsp;&nbsp;&nbsp;<span>${name}</span><span
                        class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="<%=request.getContextPath()%>/adminMemberList.admin">회원목록 </a></li>
                        <li><a href="<%=request.getContextPath()%>/adminMentoList.admin">멘토목록</a></li>
                        <li><a href="<%=request.getContextPath()%>/adminStudyList.admin">스터디목록</a></li>
                        <li><a
                           href="<%=request.getContextPath()%>/adminPage.admin">
                              내 프로필</a></li>
                     </ul>
                  <li><a
                     href="<%=request.getContextPath()%>/view/form/logOut.jsp">로그아웃</a></li>
               </c:if>

            </ul>
         </div>
      </div>
   </nav>
   <!--/ Navigation bar-->
</body>
</html>