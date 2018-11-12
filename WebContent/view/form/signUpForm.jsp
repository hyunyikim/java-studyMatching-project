<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Sign Up</title>
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/imagehover.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/form.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	    
      $('#signUpForm').submit(
         function(){
         if($('#signUpPwd').val()!=$('#pwdchk').val()){
           alert("비밀번호가 동일하지않습니다.");   
        $('#signUpPwd').focus();
        return false;
        }else{
        if($('#signUpPwd').val().length < 4){
           alert("비밀번호를 4글자 이상 입력하세요.");
           $('#signUpPwd').focus();
           return false;
        }else{
           var regHp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
           var $hp = $('#hp');
           if(!regHp.test($hp.val())){
              alert('번호가 유효하지않습니다.');
              $hp.focus();
              return false;               
           }else{
           return true;    
           }
           }
        }         
        });
        
      $('#signUpPwd').keyup(function(){
           $('font[name=check]').text('');
          }); //#user_pass.keyup

          $('#pwdchk').keyup(function(){
           if($('#signUpPwd').val()!=$('#pwdchk').val()){
            $('font[name=check]').text('');
            $('font[name=check]').html("암호가 틀렸습니다.");
           }else{
            $('font[name=check]').text('');
            $('font[name=check]').html("암호가 일치합니다.");
            
           }
          });  
  

   $('#emailCheck').click(function(){
       var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
       var $email = $('#signUpEmail');
       $.ajax(
          {
          url:"emailCheck.do",
          data:{email:$('#signUpEmail').val()},
          dataType:"html", 
          success:function(responsedata){
             console.log(">"+responsedata+"<");
             if(responsedata.trim() == "false"){
              $('#Message').html("중복된 email입니다.");
             }else{
              if(!regEmail.test($email.val())){
                 alert('이메일 주소가 유효하지않습니다.');
                 $email.focus();
                 return false;
              }else{
             $('#Message').html("사용가능한 email입니다.");
              }       
             }
             }
          });
  });
});
 
  </script>
</head>
<body>
<!-- navbar -->
<% pageContext.include("/view/form/nav.jsp"); %>
  <div class="div-form">
  	<h2>회원가입</h2>
    <div class="form-group">
      <form name="signUpForm" id="signUpForm" action="join.do">
        <div class="form-group has-feedback">
          <!----- email -------------->
          <input class="form-control" placeholder="Email" type="text" autocomplete="off" id="signUpEmail" name="email" style="width:375px; display:inline;"/>
          <button type="button" class="btn btn-green btn-flat" id="emailCheck">Email Check</button>
          <div id="Message"></div>
          <span style="display:none;font-weight:bold; position:absolute;color: red;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginid"></span>
        </div>

        <div class="form-group has-feedback">
          <!----- password -------------->
          <input class="form-control" placeholder="Password" type="password" autocomplete="off" id="signUpPwd" name="pwd" />
          <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
        </div>
        <div class="form-group has-feedback">
          <!----- password Check -------------->
          <input class="form-control" placeholder="Password Check" type="password" autocomplete="off" id="pwdchk" name="pwdchk" />
          <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
       		<font name="check" color="red"></font>
        </div>
        <div class="form-group has-feedback">
          <!----- name -------------->
          <input class="form-control" placeholder="Name" type="text" autocomplete="off" id="name" name="name" />
          <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
        </div>

        <div class="form-group has-feedback">
          <!----- hp -------------->
          <input class="form-control" placeholder="Phone Number" type="text" autocomplete="off" id="hp" name="hp" />
          <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
        </div>

        <div class="row">
          <div class="col-xs-12">
            <input type="submit" class="btn btn-green btn-block btn-flat" value="Sign Up">
          </div>
        </div>
      </form>
    </div>
  </div>
  <!-- footer -->
<% pageContext.include("/view/form/footer.jsp"); %>
</body>
</html>