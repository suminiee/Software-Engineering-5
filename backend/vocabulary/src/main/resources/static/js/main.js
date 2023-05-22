function service1() {
  let signIn = "${signIn}";
  if (signIn == "") {
    alert("로그인 후 이용할 수 있습니다.");
    location.href="${cpath}/login.do"
  }
  else {
    location.href="${cpath}/service.do"
  }
}