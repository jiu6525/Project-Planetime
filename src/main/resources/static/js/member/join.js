   function validate() {
       var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식

       var id = document.getElementById("memberId");
       var pw = document.getElementById("memberPwd");
       var ck = document.getElementById("ckPwd");
//       var birth = document.getElementById("birth");
//       var gender = document.getElementById("gender");
//       var phone = document.getElementById("phone");


       if(!check(re,id,"아이디를 입력하세요")) {
           return false;
       }

//       if(!check(re,pw,"비밀번호를 입력하세요")) {
//           return false;
//       }

       if(pw.value != ck.value) {
           alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
           join.ck.value = "";
           join.ck.focus();
           return false;
       }

       if(join.name.value=="") {
           swal("이름을 입력해 주세요");
           join.name.focus();
           return false;
       }

       if(join.birth.value=="") {
           swal("생년월일을 입력해 주세요");
           join.birth.focus();
           return false;
       }

      if(join.gender.value=="") {
          swal("성별을 선택해 주세요");
          return false;
      }

      if(join.email.value=="") {
         swal("생년월일을 입력해 주세요");
         join.email.focus();
         return false;
     }


     if(join.phone.value=="") {
         swal("핸드폰 번호를 입력해 주세요");
         join.phone.focus();
         return false;
     }

        alert("회원가입이 완료되었습니다!");

   };

   function check(re, what, message) {
       if(re.test(what.value)) {
           return true;
       }
       swal(message);
       what.value = "";
       what.focus();
       return false;
   }