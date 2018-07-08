function Login() {
	var main = document.getElementById('main');
	main.classList.add('main_hide');
	var loginScene = document.getElementById('loginScene');
	loginScene.style.zIndex = 1;
	loginScene.style.opacity = 1;
	
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
}
