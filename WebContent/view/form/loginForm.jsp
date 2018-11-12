<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/imagehover.min.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/form.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript">
    function userLogin(){
    	$('#loginForm').submit();
    }
  </script>
</head>
<body>
<!-- navbar -->
<% pageContext.include("/view/form/nav.jsp"); %>
  <div class="div-form">
  	<h2>로그인</h2>
    <div class="form-group">
      <form name="loginForm" id="loginForm" action="login.do" method="post">
        <div class="form-group has-feedback">
          <!----- email -------------->
          <input class="form-control" placeholder="Email" type="text" autocomplete="off" id="email" name="email"/>
          <span style="display:none;font-weight:bold; position:absolute;color: red;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginid"></span>
        </div>
        <div class="form-group has-feedback">
          <!----- password -------------->
          <input class="form-control" placeholder="Password" type="password" autocomplete="off" id="pwd" name="pwd" />
          <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
        </div>
        <div class="row">
          <div class="col-xs-12">
            <button type="button" class="btn btn-green btn-block btn-flat" onclick="userLogin()">Log In</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <!-- footer -->
<% pageContext.include("/view/form/footer.jsp"); %>
</body>
</html>