// 禁用request-btn02按钮，因为没有实现登录功能
// document.getElementById("request-btn02").disabled = true;
let code = "";
let tokenPlus = "";
// 原生ajax方式发送请求
const getCode = () => {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/captcha/getBase64Bytes?timestamp=' + new Date().getTime())
    xhr.send()
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const base64Bytes = xhr.responseText;
            // console.log(base64Bytes);
            // document.getElementById("text-area").value = base64Bytes】
            const res = JSON.parse(base64Bytes);
            if (res.code !== 0) {
                alert("错误!!! " + res.message);
                console.log(res.message);
                return;
            }
            // const message = res.message;
            const data = res.data;
            const imgSource = data.split("?")
            const imgBase64Bytes = imgSource[0];
            code = imgSource[1]
            document.getElementById("img-code").src = "data:image/png;base64," + imgBase64Bytes;
            // document.getElementById("text-area").value = code;
        }
    }
}

const userLogin = () => {
    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/user/login');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.withCredentials = true; // 设定为 true，允许跨域请求带上 cookie

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const res = JSON.parse(xhr.responseText);
            const token = res.data;
            tokenPlus = token;
            document.getElementById("text-area").value =
                "RESPONSE\nCode: " + res.code + "\nMessage: " + res.message + "\nData: " + token;
        }
    };

    xhr.send('username=' + username + '&password=' + password);
}


document.getElementById("request-btn01").addEventListener("click", getCode)
document.getElementById("request-btn02").addEventListener("click", userLogin)
document.getElementById("request-btn03").addEventListener("click", getList)
document.addEventListener('keyup', function (event) {
    if (event.key === 'Enter') {
        console.log('回车键被释放');
        // 执行你希望在回车键释放时的操作
        const codeInput = document.getElementById("img-code-input").value;
        console.log("Input code: " + codeInput + " Real code: " + code);
        if (codeInput === "") {
            alert("请输入验证码!!!");
            return;
        }
        if (codeInput === code) {
            document.getElementById("text-area").value = "验证码正确";
            document.getElementById("request-btn02").disabled = false;
        } else {
            alert("验证码错误!!!");
            document.getElementById("img-code-input").value = "";
            document.getElementById("request-btn02").disabled = true;
        }
    }
});

async function getList() {
    // 从cookie中获取big-event-token
    console.log("cookie: " + document.cookie);
    const token = document.cookie.replace(/(?:(?:^|.*;\s*)big-event-token\s*\=\s*([^;]*).*$)|^.*$/, "$1");

    if (token === "") {
        alert("请先登录!!!");
        return;
    }
    // 设置请求头
    console.log("tokenPlus: " + tokenPlus);
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + tokenPlus;
    // 使用axios发送请求
    const ins = axios.create({
        baseURL: 'http://localhost:8080'
    });
    const res = await ins.get('/list');
    console.log(res.data);
    document.getElementById("text-area").value = JSON.stringify(res.data);
}