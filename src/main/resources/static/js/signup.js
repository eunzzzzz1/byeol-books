// 아이디
    // TODO 중복확인 (Ajax 통신)

// 비밀번호
    // TODO 형식 검사 (글자수, 대/소문자, 숫자, 특수문자 포함)
var passwordWarnDiv = document.getElementById('password_warn');
function passwordValidate(inputText) {
    var pwd = inputText.value;
    var num = pwd.search(/[0-9]/g); // 숫자 정규식
    var eng = pwd.search(/[a-z]/ig); // 영문 정규식
    var spe = pwd.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi); // 특수문자 정규식

    if(pwd.search(/\s/) !== -1) {
        passwordWarnDiv.textContent = ' * 비밀번호는 공백을 포함할 수 없습니다.';
    } else if(pwd.length<=8 || pwd.length>=20) {
        passwordWarnDiv.textContent = ' * 비밀번호는 8~20자로 입력해주세요.';
    } else if(num < 0 || eng < 0 || spe < 0) {
        passwordWarnDiv.textContent = ' * 영문, 숫자, 특수문자를 혼합해 입력해주세요.';
    } else {
        passwordWarnDiv.textContent = '';
    }
}

// 이메일
    // 이메일 도메인 select 선택 시, input에 들어가게 하기
var emailDomainInputBox = document.getElementById('email_domain');
var domainSelectOption = document.getElementById("domain_select");

function changeDomainSelectOption() {
    if(domainSelectOption.options[domainSelectOption.selectedIndex].value === "enter") {
        emailDomainInputBox.readOnly = false;
        emailDomainInputBox.value = null;
    } else {
        emailDomainInputBox.value = domainSelectOption.options[domainSelectOption.selectedIndex].value;
    }
}
    // TODO 이메일 형식 검사


// 닉네임
    // 닉네임 두 자 이상으로 형식검사
var nicknameWarnDiv = document.getElementById('nickname_warn');
function nicknameValidate(inputText) {
    if(0<inputText.value.length && inputText.value.length<2)
        nicknameWarnDiv.textContent = ' * 닉네임은 2자 이상으로 입력해주세요.';
    else
        nicknameWarnDiv.textContent = '';
}


