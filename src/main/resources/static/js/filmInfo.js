function clickHeart() {
	$(".heart").toggleClass('checked');
	$(".heart").toggleClass('unchecked');
}

function clickStar1() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "很差";
}

function clickStar2() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "较差";
}

function clickStar3() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "还行";
}

function clickStar4() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "推荐";
}

function clickStar5() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "力荐";
}
