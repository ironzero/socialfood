function writeSave() {
	if (document.writeform.writer.value == "") {
		alert("이름을 입력하세요");
		document.writeform.writer.focus();
		return false;
	}
	if (document.writeform.subject.value == "") {
		alert("제목을 입력하세요");
		document.writeform.subject.focus();
		return false;
	}
	if (document.writeform.content.value == "") {
		alert("내용을 입력하세요");
		document.writeform.content.focus();
		return false;
	}
	if (document.writeform.passwd.value == "") {
		alert("암호를 입력하세요");
		document.writeform.passwd.focus();
		return false;
	}

}
function deleteSave() {
	if (document.delForm.passwd.value == "") {
		alert("비밀번호를 입력하세요");
		document.delForm.passwd.focus();
		return false;
	}
}

// Search input Check
function searchCheck() {
	if (document.searchForm.search.value == "") {
		alert("검색어를 입력하세요");
		document.searchForm.search.focus();
		return false;
	}
}


// MemberJoinForm input Check
function joinCheck() {
	if (document.joinForm.id.value == "") {
		alert("ID 를 입력하세요.");
		document.joinForm.id.focus();
		return false;
	}
	if (document.joinForm.id.value.length < 3) {
		alert("ID 는 최소 3글자 이상입니다.");
		document.joinForm.id.focus();
		return false;
	}
	if (document.joinForm.idCheck.value == 0) {
		alert("ID 중복체크를 하지 않았습니다.");
		document.joinForm.id.focus();
		return false;
	}
	if (document.joinForm.passwd.value == "") {
		alert("패스워드를 입력하세요.");
		document.joinForm.passwd.focus();
		return false;
	}
	if (document.joinForm.passwd.value.length < 4) {
		alert("패스워드는 최소 4글자 이상입니다.");
		document.joinForm.passwd.focus();
		return false;
	}
	if (document.joinForm.passwdCheck.value == "") {
		alert("패스워드 확인하세요.");
		document.joinForm.passwdCheck.focus();
		return false;
	}
	if (document.joinForm.passwd.value != document.joinForm.passwdCheck.value) {
		alert("패스워드가 정확하지 않습니다. 다시 확인하세요.");
		document.joinForm.passwd.value = "";
		document.joinForm.passwdCheck.value = "";
		document.joinForm.passwd.focus();
		return false;
	}
	if (document.joinForm.name.value == "") {
		alert("이름을 입력하세요.");
		document.joinForm.name.focus();
		return false;
	}
	if (document.joinForm.nickname.value == "") {
		alert("닉네임을 입력하세요.");
		document.joinForm.nickname.focus();
		return false;
	}
	if (document.joinForm.nickCheck.value == 0) {
		alert("닉네임 중복체크를 하지 않았습니다.");
		document.joinForm.nickname.focus();
		return false;
	}
	if (document.joinForm.email1.value == "" || document.joinForm.email2.value == "") {
		alert("이메일을 입력하세요.");
		document.joinForm.email1.focus();
		return false;
	}
	if (document.joinForm.phone2.value == ""
			|| document.joinForm.phone3.value == "") {
		alert("핸드폰 번호를 입력하세요.");
		document.joinForm.phone1.focus();
		return false;
	}
	if (document.joinForm.locate.value == "") {
		alert("주거 지역을 입력하세요.");
		document.joinForm.locate.focus();
		return false;
	}
}



// ID Check
function check_ID(id) {
	sub = window.open('memberIdCheck.do?id='+id, 'sub', 'width=400, height=140, scrollbars=no, resizable=no');
}
function sub_idCheck() {
	if (document.subCheck.id.value == "") {
		alert("ID 를 입력하세요.");
		document.subCheck.id.focus();
		return false;
	}
}
function use_ID(id) {
	opener.document.joinForm.id.value = id;
	opener.document.joinForm.idCheck.value = 1;
	if (!window.closed) {
		window.close();
	}
}
function resetIdCheck() {
	document.joinForm.idCheck.value = "0";
}



// Nickname Check
function check_nick(nick) {
	sub = window.open('memberNickCheck.do?nickname='+nick, 'sub', 'width=400, height=140, scrollbars=no, resizable=no');
}
function sub_nickCheck() {
	if (document.subCheck.nickname.value == "") {
		alert("닉네임을 입력하세요.");
		document.subCheck.nickname.focus();
		return false;
	}
}
function use_nick(nick) {
	opener.document.joinForm.nickname.value = nick;
	opener.document.joinForm.nickCheck.value = 1;
	if (!window.closed) {
		window.close();
	}
}
function resetNickCheck() {
	document.joinForm.nickCheck.value = "0";
}

// Select Date function
function fill_select(f) {
	document.writeln("<SELECT name=\"years\">");
	for ( var x = 1900; x < 2030; x++) {
		if (x == 2013)
			document.writeln("<OPTION value=\"" + x + "\"selected>" + x);
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	}
	document.writeln("</SELECT>년");

	document.writeln("<SELECT name=\"months\">");
	for (x = 1; x <= 12; x++)
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	document.writeln("</SELECT>월<SELECT name=\"days\">");
	for (x = 1; x <= 31; x++)
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	document.writeln("</SELECT>일");
}
function fill_select(f, year, month, date) {
	document.writeln("<SELECT name=\"years\">");
	for ( var x = 1900; x < 2030; x++) {
		if (x == year)
			document.writeln("<OPTION value=\"" + x + "\"selected>" + x);
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	}
	document.writeln("</SELECT>년");

	document.writeln("<SELECT name=\"months\">");
	for (x = 1; x <= 12; x++) {
		if (x == month)
			document.writeln("<OPTION value=\"" + x + "\"selected>" + x);
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	}
	document.writeln("</SELECT>월<SELECT name=\"days\">");
	for (x = 1; x <= 31; x++) {
		if (x == date)
			document.writeln("<OPTION value=\"" + x + "\"selected>" + x);
		document.writeln("<OPTION value=\"" + x + "\">" + x);
	}
	document.writeln("</SELECT>일");
}

// only number input
function onlyNumberInput() {
	var code = window.event.keyCode;

	if ((code > 34 && code < 41) || (code > 47 && code < 58)
			|| (code > 95 && code < 106) || code == 8 || code == 9
			|| code == 13 || code == 46) {
		window.event.returnValue = true;
		return;
	}
	window.event.returnValue = false;
}
// only number, alpha input
function onlyAlphaNumberInput() {
	var code = window.event.keyCode;

	if ((code > 96  && code < 123) || (code > 64  && code < 91) || (code > 47 && code < 58)
		 || code == 8 || code == 9 || code == 13 || code == 46) {
		window.event.returnValue = true;
		return;
	}
	window.event.returnValue = false;
}

// email
function setEmailTail(emailValue) {
	document.all("email"); // 사용자 입력
	var emailTail = document.all("email2"); // Select box

	if (emailValue == "notSelected")
		return;
	else if (emailValue == "etc") {
		emailTail.readOnly = false;
		emailTail.value = "";
		emailTail.focus();
	} else {
		emailTail.readOnly = true;
		emailTail.value = emailValue;
	}
}


// delete member
function deleteMember(member) {
	if (confirm("ID : [" + member + "] 정말로 삭제하시겠습니까?")) {
		location.href="/socialfood/deleteMember.do?id=" + member;
	}
}
function deleteMember(member, colName, search) {
	if (confirm("ID : [" + member + "] 정말로 삭제하시겠습니까?")) {
		location.href="/socialfood/deleteMember.do?id=" + member + "&colName=" + colName + "&search=" + search;
	}
}
function deleteAccount(member) {
	if (confirm(member + "님 정말로 탈퇴를 하시겠습니까?")) {
		location.href = "/socialfood/deleteAccount.do?id=" + member;
	}
}

// my coupon list
function myCoupon(member) {
	sub = window.open('myCouponList.do?id=' + member, 'sub', 'width=900, height=600');
}

// update Member Info
function updateMemberInfo(member) {
	sub = window.open('updateMemberForm.do?id=' + member, 'sub', 'width=900, height=600');
}

function updateConfirm() {
	var ps1 = document.updateMemberForm.newPasswd1;
	var ps2 = document.updateMemberForm.newPasswd2;
	var db = document.updateMemberForm.dbPasswd;
	var cps = document.updateMemberForm.currPasswd;
	if (cps.value == "") {
		alert("현재 패스워드를 입력하세요.");
		return false;
	}
	if (ps1.value == "") {
		db.value = cps.value;
	} else if (ps1.value == ps2.value){
		db.value = ps1.value;
	} else if (ps1.value != ps2.value){
		alert("수정할 패스워드를 다시 확인하세요.");
		ps1.focus();
		return false;
	} 
	if ((ps1.value != "" || ps2.value != "") && ps1.value.length < 4) {
		alert("패스워드는 4자리 이상입니다.");
		ps1.focus();
		return false;
	}
	
	if (confirm('수정하시겠습니까?')){
		return true;
	}
	
	return false;
}
