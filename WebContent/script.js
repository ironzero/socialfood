function writeSave(){
	if(document.writeform.writer.value==""){
		alert("이름을 입력하세요");
		document.writeform.writer.focus();
		return false;
	}
	if(document.writeform.subject.value==""){
		alert("제목을 입력하세요");
		document.writeform.subject.focus();
		return false;
	}
	if(document.writeform.content.value==""){
		alert("내용을 입력하세요");
		document.writeform.content.focus();
		return false;
	}
	if(document.writeform.passwd.value==""){
		alert("암호를 입력하세요");
		document.writeform.passwd.focus();
		return false;
	}
	
}
function deleteSave(){
	if(document.delForm.passwd.value==""){
		alert("비밀번호를 입력하세요");
		document.delForm.passwd.focus();
		return false;
	}
}

function finding(){
	if(document.findForm.val.value==""){
		alert("찾고자 하는 값을 입력하세요");
		document.findForm.val.focus();
		return false;
	}
}
// ID Check
function check_ID() {
	var id = document.getElementById("id").value;
	
}

// Select Date function
