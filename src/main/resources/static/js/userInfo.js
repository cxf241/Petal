function toModifyName() {
	var nameInput = document.getElementById('nameInput');
	nameInput.innerHTML = '<input class="form-control"  type="text" name="newName" />';
	var modifyNameBtn = document.getElementById('modifyNameBtn');
	modifyNameBtn.classList.remove('hidden');
}

function toModifyPasswd() {
	var toModifyPasswdBtn = document.getElementById('toModifyPasswdBtn');
	toModifyPasswdBtn.classList.add('hidden');
	var modifyPasswd = document.getElementById('modifyPasswd');
	modifyPasswd.classList.remove('hidden');
}
