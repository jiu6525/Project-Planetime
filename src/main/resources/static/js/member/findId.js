/* 아이디 찾기 */
//아이디 & 스토어 값 저장하기 위한 변수
	// 아이디 값 받고 출력하는 ajax
	function findId_click(){
		var name=$('#name').val()
		var phone=$('#email').val()

		$.ajax({
			url:"./findId",
			type:"POST",
			data:{"name":name, "phone":phone} ,
			success:function(data){
				if(data == 0){
					$('#idValue').text("회원 정보를 확인해주세요!");
					$('#name').val('');
					$('#email').val('');
				} else {
					$('#idValue').text(data);
					$('#name').val('');
					$('#email').val('');

				}
			},
			 error:function(){
	                alert("일치하는 회원이 없습니다!");
	            }
		});
	};

const modal = document.getElementById("modal")
const btnModal = document.getElementById("findId")

btnModal.addEventListener("click", e => {
    modal.style.display = "flex"
})


const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modal.style.display = "none"
})

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modal.style.display = "none"
    }
})
