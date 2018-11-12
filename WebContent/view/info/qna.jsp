<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Accordion - Collapse content</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
      $(".answer").hide();
      //content 클래스를 가진 div를 표시/숨김(토글)
      $(".question").click(function() {
         $(this).next(".answer").slideToggle(500);
      });
   });
</script>
<style type="text/css">
.category {
   font: bold;
}

.question {
   margin: 3px;
   color: #fff;
   padding: 3px 10px;
   cursor: pointer;
   position: relative;
   background-color: #F08B41;
   height: 40px;
    padding-top: 8px;
    border-radius: 10px;
}

.answer {
   padding: 5px 10px;
   background-color: #fafafa;
}

p {
   padding: 5px 0;
}
</style>
</head>
<body>
   <!-- navbar -->
   <%
      pageContext.include("/view/form/nav.jsp");
   %>
   <div class="container">
      <p class="question">
         <span class="category">[취소 및 환불]</span><span class="question-content">환불
            요청은 어떻게 하나요?</span>
      </p>
      <div class="answer">
         <span class="answer-content">환불/교환문의는 카카오톡 플러스친구 @스터디서치를 통해
            안내드리고 있습니다. 친구추가 후 1:1메시지를 통해 '성함', '연락처' 및 환불/교환문의를 남겨주세요 </span>
      </div>
      <p class="question">
         <span class="category">[취소 및 환불]</span><span class="question-content">신청
            취소는 어떻게 할 수 있나요?</span>
      </p>
      <div class="answer">
         <span class="answer-content">리더가 회원님의 스터디 신청을 수락 또는 거절하기 전까지,
            신청 취소는 자유롭게 가능합니다.<br> <br> 다음의 방법으로 신청 취소를 하실 수 있습니다.<br>
            메인 → 채팅 탭 → 해당 스터디 리더와의 채팅창 → 우측 상단 메뉴 → 신청 취소
         </span><img class="help-image"
            src="https://cdn.studysearch.co.kr/uploads/images/faq/4/1522049942">
      </div>
      <p class="question">
         <span class="category">[취소 및 환불]</span><span class="question-content">환불
            금액은 언제 입금되나요?</span>
      </p>
      <div class="answer">
         <span class="answer-content">스터디 환불은 신청자의 환불 신청일에 따라 환불규정이
            적용되며, <br> 스터디서치 상담원이 확인 후 즉시 환불안내를 진행합니다 :)<br> <br>
            카드 환불/승인취소의 경우 결제사와 카드사의 행정업무 부분으로, <br> 사정상 최대 7 영업일이 소요될 수
            있으며, <br> 정확한 환불/승인취소 일자는 상품 구매자인 회원님께서 직접 카드사를 통해 문의하셔야 확인이
            가능합니다.<br> <br> 가상계좌 환불은 말씀해주신 계좌로 입급되며,<br> 결제사의
            행정업무 부분으로 사정상 최대 3영업일이 소요될 수 있습니다.<br>
         </span>
      </div>
      <p class="question">
         <span class="category">[서비스 사용]</span><span class="question-content">후기는
            어떻게 작성하나요?</span>
      </p>
      <div class="answer">
         <span class="answer-content">스터디서치에 있는 후기는 스터디서치 커뮤니티의 리더와
            참가자가 남긴 것으로, 모두 스터디 참가 후 작성된 것입니다.<br> <br> 정해진 회차의 스터디가
            종료되면, 상대방이 나에 대해 후기를 보냅니다. 상대방이 보낸 후기를 읽으려면 회원님도 상대방에 대한 후기를 작성해주세요.
            작성 후 나에 대한 후기를 확인하실 수 있습니다. <br> <br> 내가 보낸 후기와 나에 대한 후기는
            프로필 페이지에서 확인할 수 있습니다.
         </span><img class="help-image"
            src="https://cdn.studysearch.co.kr/uploads/images/faq/11/1522049969">
      </div>
      <p class="question">
         <span class="category">[서비스 사용]</span><span class="question-content">새
            메시지가 왔다는 문자가 왔는데 어디서 확인하나요?</span>
      </p>
      <div class="answer">
         <span class="answer-content">다음의 방법으로 확인하실 수 있습니다.<br> <br>
            <b>1) 안드로이드 애플리케이션</b><br> 안드로이드 어플 → 메인 → 채팅 탭 → 해당 스터디 리더와의
            채팅창<br> <br> <b>2) 홈페이지</b><br> PC 인터넷을 통해 스터디서치 접속!
            → 상단의 메시지 아이콘 클릭<br> <br> <b>3) 모바일웹</b><br> 스마트폰
            인터넷을 통해 스터디서치 접속! → 좌측 상단의 메뉴바 클릭 → 메시지 메뉴 클릭
         </span><img class="help-image"
            src="https://cdn.studysearch.co.kr/uploads/images/faq/12/1522050009">
      </div>
      <p class="question"><span class="category">[서비스 사용]</span><span
               class="question-content">영수증 발급이 가능한가요?</span></p>
      <div class="answer">
         <span class="answer-content">영수증
                  발급 가능해요 :)<br> 참가자분의 성함과, 참여하시는 스터디명, 영수증 수령을 원하시는 메일주소를<br>
                  고객문의 또는 카카오톡 플러스친구 @스터디서치로 보내주시기 바랍니다 :)<br> 영수증은 결제사를 통해
                  참가자분의 메일로 발송됩니다
               </span>
      </div>
      <p class="question"><span class="category">[스터디 진행]</span><span
               class="question-content">스터디 신청 후 뭘 해야하나요?</span></p>
      <div class="answer">
         <span class="answer-content">스터디에
                  신청해주셨군요!!!! 감사합니다 :)<br> 스터디서치에서는 회원님께서 스터디에 신청하시고,<br>
                  스터디 리더가 수락작업을 진행할때까지는 결제가 진행되지 않아요 ㅎㅎ<br> <br> 스터디 리더가
                  신청을 수락하면 스터디에 결제가 진행됨과 동시에 스터디서치 서비스 1:1메세지함을 통해 스터디 진행에 대한 간단한
                  안내문이 발송됩니다!<br>안내문은 꼭!!!! 확인 부탁드리구요 ㅎㅎ<br> 이후 리더의 안내에 따라
                  스터디에 참석하셔서 다른 회원분들과 즐거운 경험 즐기시길 기대하시길 바랍니다!
               </span>
      </div>
      <p class="question"><span class="category">[스터디 진행]</span><span
               class="question-content">스터디에는 어떤 사람들이 참가하나요?</span></p>
      <div class="answer">
         <span class="answer-content">스터디서치에서
                  운영하는 모든 스터디는 성인 회원들을 대상으로 진행합니다.<br> <br> 스터디에 참여하시는 분들은
                  대학생부터 직장인, 가정주부등 다양해요 ㅎㅎ<br> 스터디에 참여하셔서 여러 분야에 몸을 담고있는 참가자분들과
                  함께 외국어로 이야기 나눠보시겠어요?<br> <br> 참가하시는 분들은 해외여행, 워홀, 취미생활
                  등의 다양한 목적을 가지고 계셔서<br> 내가 살아보지 못한 삶에 대한 많은 이야기를 간접적으로나마 경험하실
                  수 있을거에요!!<br>
               </span>
      </div>
      <p class="question"><span class="category">[결제]</span><span
               class="question-content">현장에서 현금으로 드리면 안되나요?</span></p>
      <div class="answer">
         <span class="answer-content">현장
                  결제는 불가합니다. <br>
               <br>리더와 스터디원 사이의 금전적인 분쟁을 방지하기 위해, 결제는 스터디서치가 중개자로서 대행합니다.
               </span>
      </div>
      <p class="question"><span class="category">[결제]</span><span
               class="question-content">결제수단 추가나 삭제는 어떻게 하나요?</span></p>
      <div class="answer">
         <span class="answer-content">결제수단
                  추가는 결제 단계에서 "카드 추가"를 눌러서 진행해주시면 됩니다. 결제수단 삭제는 help@mpmg.kr로 아이디와
                  삭제를 원하시는 결제수단을 보내주시면 바로 삭제 도와드리겠습니다. </span>
      </div>
   </div>
   <!-- footer -->
   <%
      pageContext.include("/view/form/footer.jsp");
   %>
</body>
</html>