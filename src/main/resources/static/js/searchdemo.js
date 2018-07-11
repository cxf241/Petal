/**
 * main.js
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2016, Codrops
 * http://www.codrops.com
 */
;
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
		search1.classList.add('main_hide');

		//		var search1 = document.getElementById('main_top');
		//		search1.classList.add('main_top_hide');
		//		var search2 = document.getElementById('main_medium');
		//		search2.classList.add('main_medium_hide');
		//		var search3 = document.getElementById('main_bottom');
		//		search3.classList.add('main_bottom_hide');
		var search4 = document.getElementById("loginScene");
		search4.classList.add('loginScene_hide');
		
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

		//		var search1 = document.getElementById('main_top');
		//		search1.classList.remove('main_top_hide');
		//		var search2 = document.getElementById('main_medium');
		//		search2.classList.remove('main_medium_hide');
		//		var search3 = document.getElementById('main_bottom');
		//		search3.classList.remove('main_bottom_hide');

		var search1 = document.getElementById('main');
		search1.classList.remove('main_hide');

		var search4 = document.getElementById('loginScene');
		search4.classList.remove('loginScene_hide');
		var search5 = document.getElementById("search");
		search5.classList.add('search--open_hide');

	}

	init();

})(window);