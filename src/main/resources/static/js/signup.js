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




