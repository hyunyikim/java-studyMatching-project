<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<title>mypage</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mypage.css">
<% 
   String hp = (String)request.getAttribute("hp"); 
   String name = (String)session.getAttribute("name");
   String member_img = (String)session.getAttribute("member_img");
%>
<script type="text/javascript">
var email="${email}";
jQuery(document).ready(function() {
    if(email== null||email=="") {
        alert("이용하시려면 로그인하셔야 합니다.");
        location.href="<%=request.getContextPath()%>/view/form/loginForm.jsp";
		}
         var file = document.querySelector('#getfile');

   		 file.onchange = function () { 
        var fileList = file.files ;
        
        // 읽기
        var reader = new FileReader();
        reader.readAsDataURL(fileList [0]);

        //로드 한 후
        reader.onload = function  () {
            //로컬 이미지를 보여주기
            var imglink="<img src='' id='img' style='height: 100%; width: 100%; border:0; border-radius: 50%; object-fit: contain'/>";
            $('#profilePhoto').html(imglink);
            document.querySelector('#img').src = reader.result;
        }; 
    };    

    $('#updateProfileForm').submit(
         function(){
            var regHp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
            var $hp = $('#hp');
            if(!regHp.test($hp.val())){
               alert('번호가 유효하지않습니다.');
               $hp.focus();
               return false;
            }else{
               alert("UpdateSuccess");
               return true;
            }
         }
         );
      
      $('#dropOut').click(function(){
         location.href="dropOut.mypage";
      });
    
});


</script>
<style>
.profile-form-control{
    display: inline-block;
    margin:10px 10px auto;
    width: 30%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.btn-flat{margin:10px;}
.edit-photo-button-label{ cursor:pointer; border:3px solid #F08B41;color:#F08B41; width: 150px; height: 50px; background:#fff; padding:10px;}
.profile-title{margin-top:30px; margin-bottom:15px;}
.exit{border:3px solid #717f86 !important; background-color:#EBEBEB;}
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
	<div class="page">
		<!-- mypage Header -->
		<%
		pageContext.include("/view/mypage/member/memMyPage.jsp");
	%>
		<!-- mypage Content -->
	<form method="post" action="updateProfile.mypage" id="updateProfileForm" enctype="multipart/form-data">
		<div class="myContent-box">
			<div class="myContent">
				<div class="profileheader">
					<div class="profilephoto" id="profilePhoto"></div>
					<label class="edit-photo-button-label" >
						<!-- react-text: 88 -->프로필 변경하기<!-- /react-text -->
						<input type="file" id="getfile" name="member_img" value="${member_img}" style="display:none"class="edit-photo-button" accept="image/*">
					</label>
				</div>
				<div class="profilecontent">
					<div class="profile-title"><h4>프로필</h4></div>
					<div class="profilecontent-text" style="margin-bottom:30px">
					<label style='display:block'><input class="profile-form-control" value="${name }"  placeholder="Name" type="text" autocomplete="off" id="name" name="name" /></label>
					<label style='display:block'><input class="profile-form-control" value="<%=hp %>" placeholder="Phone Number" type="text" autocomplete="off" id="hp" name="hp" /></label>
					</div>
					<div class="profilecontent-btn" style="margin-bottom:30px">
					<input type="submit" class="btn btn-green btn-flat" value="수정하기">
					
					<button type="button" class="btn btn-green btn-flat" data-toggle="modal" data-target="#myModal">회원탈퇴</button>
					<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">회원탈퇴메세지</h4>
        </div>
        <div class="modal-body">
          <p>정말로 탈퇴하시겠습니까?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-green btn-flat" id="dropOut">회원탈퇴</button>
          <button type="button" class="btn btn-green btn-flat" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
					
					<!-- <input type="button" class="btn btn-flat exit" value="회원탈퇴" id=dropOut>
					 --></div>
				</div>
			</div>
		</div>
		
		</form>
	</div>
	<div style="height: 151px;"> </div>
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