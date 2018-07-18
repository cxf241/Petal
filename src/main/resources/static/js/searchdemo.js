(function(window) {

	'use strict';

	var closeCtrl = document.getElementById('searchCloseBtn'),
		searchContainer = document.querySelector('.search'),
		inputSearch = searchContainer.querySelector('.search__input');

	function init() {
		initEvents();
	}

	function initEvents() {
		inputSearch.addEventListener('focus', openSearch);
		closeCtrl.addEventListener('click', closeSearch);
		document.addEventListener('keyup', function(ev) {
			// escape key.
			if(ev.keyCode == 27) {
				closeSearch();
			}
		});
	}

	function openSearch() {
		searchContainer.classList.add('search--open');
		inputSearch.focus();

		var search1 = document.getElementById('main');
		search1.classList.add('main_hide2');

		var search4 = document.getElementById("loginScene");
		search4.classList.add('loginScene_hide2');

		var search5 = document.getElementById("search");
		search5.classList.remove('search--open_hide');

		var searchScene = document.getElementById('demo-2');
		searchScene.style.zIndex = 1;
		searchScene.style.opacity = 1;
		searchScene.style.filter = blur(0);

	}

	function closeSearch() {
		searchContainer.classList.remove('search--open');
		inputSearch.blur();
		inputSearch.value = '';

		var search1 = document.getElementById('main');
		search1.classList.remove('main_hide2');

		var search4 = document.getElementById('loginScene');
		search4.classList.remove('loginScene_hide2');
		var search5 = document.getElementById("search");
		search5.classList.add('search--open_hide');

	}

	init();

})(window);