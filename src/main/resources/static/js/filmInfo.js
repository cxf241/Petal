var score = 3.0;
var collected = false;
function clickHeart() {
	$(".heart").toggleClass('checked');
	$(".heart").toggleClass('unchecked');
}

function clickStar1() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "很差";
	score = 1.0;
}

function clickStar2() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "较差";
    score = 2.0;
}

function clickStar3() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "还行";
    score = 3.0;
}

function clickStar4() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "推荐";
    score = 4.0;
}

function clickStar5() {
	var evaluation = document.getElementById('evaluation');
	evaluation.innerHTML = "力荐";
	score = 5.0;
}

function changeStar(data) {
    if(data != 0) {
        document.getElementById("star3").checked = false;
        switch (data){
            case 1:
                document.getElementById("star1").checked = true;
                clickStar1();
                break;
            case 2:
                document.getElementById("star2").checked = true;
                clickStar2();
                break;
            case 3:
                document.getElementById("star3").checked = true;
                clickStar3();
                break;
            case 4:
                document.getElementById("star4").checked = true;
                clickStar4();
                break;
            case 5:
                document.getElementById("star5").checked = true;
                clickStar5();
                break;
        }
    }
}

function disabledStar() {
    document.getElementById("star1").disabled = true;
    document.getElementById("star2").disabled = true;
    document.getElementById("star3").disabled = true;
    document.getElementById("star4").disabled = true;
    document.getElementById("star5").disabled = true;
}