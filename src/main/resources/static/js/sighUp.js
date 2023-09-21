function signUp(){
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;
    let passwordCheck = document.getElementById("passwordCheck").value;
    if(id === ''){
        alert("아이디를 입력해주세요.");
        return;
    }
    if(password === '' || passwordCheck === ''){
        alert("비밀번호를 입력해주세요");
    }
    if(password !== passwordCheck){
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }
    let url = "/signUpPage/signUp/";

    fetch(url, {
        method : "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id : id,
            password : password,
        }),
    }).then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.text();
    }).then((data) => {
        console.log(data);
        if(data == 'no'){
            alert("존재하는 아이디가 있습니다.");
        }
        if(data == 'yes'){
            alert("회원가입이 정상적으로 완료되었습니다.");
            location.href = '/loginPage/';
        }
    }).catch((error) => {
        console.error("에러: " + error);
    });
}