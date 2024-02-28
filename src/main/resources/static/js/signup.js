// 아이디
    // TODO 형식 확인
    /*
        - 소문자와 숫자만 가능
        - 20자 이내
     */
var idWarnDiv = document.getElementById('id_warn');
function idValidate(inputText) {
    var id = inputText.value;
    var regex = /^[a-z0-9]*$/;

    if(!regex.test(id)) {
        idWarnDiv.textContent = ' * 아이디는 영문 소문자와 숫자로만 구성해주세요. ';
    } else if(id.length<3 || id.length>20) {
        idWarnDiv.textContent = ' * 아이디는 3자 이상, 20자 이내로 입력해주세요. ';
    } else {
        idWarnDiv.textContent = '';
    }
}

    // TODO 중복확인 (Ajax 통신)

// 비밀번호
    // 형식 검사 (글자수, 대/소문자, 숫자, 특수문자 포함)
var passwordWarnDiv = document.getElementById('password_warn');
function passwordValidate(inputText) {
    var pwd = inputText.value;
    var num = pwd.search(/[0-9]/g); // 숫자 정규식
    var eng_lower = pwd.search(/[a-z]/g); // 영문 소문자 정규식
    var eng_upper = pwd.search(/[A-Z]/g); // 영문 대문자 정규식
    var spe = pwd.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi); // 특수문자 정규식

    if(pwd.search(/\s/) !== -1) {
        passwordWarnDiv.textContent = ' * 비밀번호는 공백을 포함할 수 없습니다.';
    } else if(pwd.length<=8 || pwd.length>=20) {
        passwordWarnDiv.textContent = ' * 비밀번호는 8~20자로 입력해주세요.';
    } else if(num < 0 || eng_lower < 0 || spe < 0 || eng_upper<0) {
        passwordWarnDiv.textContent = ' * 영문 대/소문자, 숫자, 특수문자를 혼합해 입력해주세요.';
    } else {
        passwordWarnDiv.textContent = '';
    }
}

// 이메일
    // V 이메일 도메인 select 선택 시, input에 들어가게 하기
    // V 유저 이메일을 hidden input에 넣기
var emailLocalInputBox = document.getElementById('email_local');
var emailDomainInputBox = document.getElementById('email_domain');
var domainSelectOption = document.getElementById("domain_select");
var emailHiddenBox = document.getElementById('user_email');

function changeDomainSelectOption() {
    if(domainSelectOption.options[domainSelectOption.selectedIndex].value === "enter") {
        emailDomainInputBox.readOnly = false;
        emailDomainInputBox.value = '@';
    } else {
        emailDomainInputBox.value = domainSelectOption.options[domainSelectOption.selectedIndex].value;
    }

    // 이메일을 select에서 선택했을 때 hidden input으로 넣는 스크립트
    var userEmail = emailLocalInputBox.value + emailDomainInputBox.value;
    alert(userEmail);
    emailHiddenBox.value = userEmail;
}

    // 이메일을 직접 입력했을 때 hidden input으로 넣는 스크립트
function getUserEmail() {
    var userEmail = emailLocalInputBox.value + emailDomainInputBox.value;
    alert(userEmail);
    emailHiddenBox.value = userEmail;
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


