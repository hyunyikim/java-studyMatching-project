<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Study List</title>
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/imagehover.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/form.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/studyList.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

  <script type="text/javaScript">
  $(document).ready(function(){
	  
	    $("#locAll").click(function(){
	        if($("#locAll").prop("checked")){
	            $("input[name=loc]").prop("checked",true);
	            $('#locLabel').empty();
	            $('#locLabel').append("선택 해제");
	        }else{
	            $("input[name=loc]").prop("checked",false);
	            $('#locLabel').empty();
	            $('#locLabel').append("전체 선택");
	        }
	    });
	    $("#levelAll").click(function(){
	        if($("#levelAll").prop("checked")){
	            $("input[name=level]").prop("checked",true);
	            $('#levelLabel').empty();
	            $('#levelLabel').append("선택 해제");
	        }else{
	            $("input[name=level]").prop("checked",false);
	            $('#levelLabel').empty();
	            $('#levelLabel').append("전체 선택");
	        }
	    });
	    
	    $('#filterSubmit').click(function(){
	    	var isLocChk = $("input:checkbox[name='loc']").is(":checked");
	    	var isLevelChk = $("input:checkbox[name='level']").is(":checked");
	    	if(!isLocChk){
	    		alert("지역을 선택하세요.");
	    		return false;
	    	} else if(!isLevelChk){
	    		alert("레벨을 선택하세요.");
	    		return false;
	    	}
	    });
	});

  </script>
</head>
<body>
<!-- navbar -->
<% pageContext.include("/view/form/nav.jsp"); %>
  <!--dropdown checkbox-->
  <div class="filterSection">
    <div class="container">
      <h3 id="filterSubject">필터 검색</h3>
    </div>
      <div class="container" id="filterbackground">
      
      		<%-- <c:set var="hiddenSubjectCode" value="${requestScope.STUDY_SUBJECT_CODE}"/>
      		<c:out value="히든값이 찍히나 : ${requestScope.STUDY_SUBJECT_CODE}"/> --%>
      		
          <form name="study" action="SearchStudyCheck.do?STUDY_SUBJECT_CODE=${requestScope.study_subject_code}" method="post">
          		<input type="hidden" name="hiddenSubjectCode" id="hiddenSubjectCode" value="">
                <div class="dropdown">
                  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">지역을 선택하세요.&nbsp;&nbsp;
                  <span class="caret"></span></button>
                  <ul class="dropdown-menu">
                  	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="locAll">&nbsp;<label for="locAll" id="locLabel">전체 선택</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="SC" id="sc">&nbsp;<label for="sc">신촌</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="HD" id="hd">&nbsp;<label for="hd">홍대</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="GN" id="gn">&nbsp;<label for="gn">강남</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="NW" id="nw">&nbsp;<label for="nw">노원</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="GD" id="gd">&nbsp;<label for="gd">건대</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="BD" id="bd">&nbsp;<label for="bd">분당</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="AY" id="ay">&nbsp;<label for="ay">안양</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="loc" value="IS" id="is">&nbsp;<label for="is">일산</label></li>
                  </ul>
                </div>&nbsp;&nbsp;
                <div class="dropdown">
                  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">레벨을 선택하세요.&nbsp;&nbsp;
                  <span class="caret"></span></button>
                  <ul class="dropdown-menu">
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="levelAll">&nbsp;<label for="levelAll" id="levelLabel">전체선택</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="level" value="SL00" id="sl00">&nbsp;<label for="sl00">입문</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="level" value="SL01" id="sl01">&nbsp;<label for="sl01">초급</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="level" value="SL02" id="sl02">&nbsp;<label for="sl02">중급</label></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="level" value="SL03" id="sl03">&nbsp;<label for="sl03">고급</label></li>
                </div>&nbsp;&nbsp;
                <div id="filterbtn">
                <input type="submit" class="btn btn-green btn-flat" value="필터 검색" id="filterSubmit">
                </div>
          </form>
      </div>
      <div class="container" id="selectedValue">
      </div>
    </div>
  </div>
    <!--dropdown checkbox-->
  <!--Study-->
    <div class="container" id="studySection">
      <div class="row">
        <div class="header-section text-center">
          <h4>스터디가 검색되었습니다.</h4><br>
          <hr class="bottom-line">
        </div>
       
        <c:set var="studyList" value="${requestScope.studyList}"/>
		  <c:forEach var="study" items="${studyList}"> 
          <div class="col-lg-4 col-md-4 col-sm-4" class="div-study" style="height: 400px;  padding:5px 0;">
            <div class="pm-staff-profile-container">
           
              <div class="pm-staff-profile-image-wrapper text-center">
                <div class="pm-staff-profile-image">
                  <a href="<%=request.getContextPath()%>/view/category/studyDetail.do?study_num=${study.study_num}&nextPage=studyDetail"><img src="<%=request.getContextPath()%>/img/profie/${study.member_img}" alt="profile" class="img-circle img-thumbnail" id="img-circle2"/></a>
                </div><!--  a태그 안에 studyDetail 주소 입력 -->
              </div>

              <div class="pm-staff-profile-details text-center">
                <p class="pm-staff-profile-name"><a href="<%=request.getContextPath()%>/view/category/studyDetail.do?study_num=${study.study_num}">${study.study_name}</a></p>
                <hr class="bottom-line">
                <p class="pm-staff-profile-title">${study.start_date} 첫 시작 <br>${study.study_loc_code} | ${study.study_level_code}</p>
              </div>
            </div>
          </div>
		</c:forEach> 
      </div>
    </div>
  <!--/ Study-->
   <% pageContext.include("/view/form/footer.jsp"); %>
</body>
</html>