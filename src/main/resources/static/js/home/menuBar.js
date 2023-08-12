  const divLst = document.getElementsByTagName('div');
  console.log(divLst);
  // 버튼가져오기
  const bentoBtn = document.getElementById('bentoBtn');
  bentoBtn.addEventListener('click', function() {
    // menu 속성 바꾸기
    const menu = document.getElementById('menu-list')
    if (menu.style.height) {
      menu.style.height = null;
    } else {
      menu.style.height = '300px';
    }
  })
  // menu안에 들어갈 item을 만들어서 menu에 추가해주기
  // for (let i = 0; i < 7; i++) {
  //   const item = document.createElement('div')
  // }


