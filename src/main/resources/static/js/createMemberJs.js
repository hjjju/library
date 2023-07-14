function joinFormCheck(){

   var memberName = document.getElementById("name");
   var phone = document.getElementById("phone");


   if(memberName.value == ""){
    alert("이름을 입력하세요")
    memberName.focus();
    return false;
   }
   if(phone.value == ""){
       alert("전화번호를 입력하세요")
       phone.focus();
       return false;
    }

   var reg = /^[0-9]+/g; //숫자만 입력하는 정규식

    if (!reg.test(phone.value)) {
    alert("전화번호는 숫자만 입력할 수 있습니다.");
    phone   .focus();
    return false;
  }
   document.createMemberForm.submit(); //유효성 검사의 포인트

}