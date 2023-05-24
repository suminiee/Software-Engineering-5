// 마이페이지 btn
const toggleBtn = document.querySelector('#toggle-button');
const menu = document.querySelector('.myPage__nav');
const closeBtn = document.querySelector("#close-button");

toggleBtn.addEventListener('click', () => {
  menu.classList.add('active');
});
closeBtn.addEventListener('click', () => {
  menu.classList.remove('active');
});


