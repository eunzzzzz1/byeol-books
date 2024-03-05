// AJAX Post 방식 통신에 필요한 CSRF 토큰 정보
const token = $("meta[name='_csrf']").attr("content")
const header = $("meta[name='_csrf_header']").attr("content");
const name = $("#userName").val();

// 아이디
    // 형식 확인
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
function idDuplicationCheck(inputText) {
    var id = inputText.value;

    var request = $.ajax({
        url: "/user/idchecking",
        method: "POST",
        data: {
            "userId": id
        },

        // Request Header에 CSRF 토큰값을 설정한다.
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
    })

    request.done(function (data) {
        if(data===1) idWarnDiv.textContent = ' * 이미 존재하는 아이디입니다. ';
    });

}


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
    } else if(pwd.length<8 || pwd.length>20) {
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
    // alert(userEmail);
    emailHiddenBox.value = userEmail;
}

    // 이메일을 직접 입력했을 때 hidden input으로 넣는 스크립트
function getUserEmail() {
    // alert(userEmail);
    emailHiddenBox.value = emailLocalInputBox.value + emailDomainInputBox.value;
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

// 다음카카오 우편번호 API 스크립트
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("user_addr3").value = extraAddr;

            } else {
                document.getElementById("user_addr3").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('user_addr0').value = data.zonecode;
            document.getElementById("user_addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("user_addr2").focus();
        }
    }).open();
}