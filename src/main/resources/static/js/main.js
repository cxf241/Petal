function Login() {
	var main = document.getElementById('main');
	main.classList.add('main_hide');
	var search = document.getElementById('demo-2');
	search.classList.add('search_hide');

	var loginScene = document.getElementById('loginScene');
	loginScene.style.zIndex = 1;
	loginScene.style.opacity = 1;

	toLogin();
}

function toLogin() {
	var loginLi = document.getElementById('loginLi');
	var registerLi = document.getElementById('registerLi');
	var login = document.getElementById('login');
	var register = document.getElementById('register');
	loginLi.classList.add('active');
	login.classList.add('active');
	login.classList.add('in');
	registerLi.classList.remove('active');
	register.classList.remove('active');
	register.classList.remove('in');
}

function Close() {
	var loginScene = document.getElementById('loginScene');
	loginScene.style.zIndex = -1;
	loginScene.style.opacity = 0;
	var main = document.getElementById('main');
	main.classList.remove('main_hide');
	var search = document.getElementById('demo-2');
	search.classList.remove('search_hide');
}

function doRegister() {
	var name = document.getElementById("registerName");
	var pwd1 = document.getElementById("registerPasswd");
	var pwd2 = document.getElementById("resurePasswd");
	var dname = document.getElementById("doubanName");
	if(name.value == "" || pwd1.value == "" || pwd2.value == "") {
		alert("请输入完整信息！");
		return;
	}
	if(pwd1.value != pwd2.value) {
		alert("两次密码不一致");
		pwd1.value = "";
		pwd2.value = "";
		return;
	}

	$.ajax({
		type: "POST",
		url: "/doRegister", //url
		data: {
			uname: name.value,
			upwd: pwd1.value
		},
		success: function(data) {
			if(data == true) {
				alert("注册成功！");
				name.value = "";
				pwd1.value = "";
				pwd2.value = "";
				toLogin(); //转到登录框
			} else {
				alert("用户已存在！");
				name.value = "";
				pwd1.value = "";
				pwd2.value = "";
			}
		}
	});
}

function doLogin() {
	var name = document.getElementById("loginName");
	var pwd = document.getElementById("loginPasswd");
	if(name.value == "" || pwd.value == "") {
		alert("请输入完整信息！");
		return;
	}

	$.post("/doLogin", {
			uname: name.value,
			upwd: pwd.value,
		},
		function(data, status) {
			if(data == true) {
				alert("登录成功！");
				name.value = "";
				pwd.value = "";
				window.location.href = "/main.html";
			} else {
				alert("用户不存在或用户与密码不匹配！");
				name.value = "";
				pwd.value = "";
			}
		});
}