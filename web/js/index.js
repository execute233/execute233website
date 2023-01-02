async function copy(text) { // 复制功能
	try {
		await navigator.clipboard.writeText(text);
		console.log('Copied: ', text);
		Swal.fire({
			title: '复制成功',
			text: `已复制到剪贴板！`,
			icon: 'success',
			footer: `复制的内容：${text}`
		});
	} catch (err) {
		console.error('Failed to copy: ', err);
		Swal.fire('复制失败', `请手动复制：${text}`, 'error');
	}
}

function updateTime() { // 更新时间
	const date = new Date(),
		hours = date.getHours(),
		minutes = date.getMinutes(),
		seconds = date.getSeconds();
	document.querySelector("#time").innerText = `${hours < 10 ? '0' + hours : hours}:${minutes < 10 ? '0' + minutes : minutes}:${seconds < 10 ? '0' + seconds : seconds}`;
}
{
	console.log('更新时间 Interval ID: ', setInterval(updateTime, 500));
	updateTime();
}



{ // 注册搞怪事件
	const music = document.querySelector('#music');
	music.addEventListener('mouseenter', function () {
		this.innerText = '被你发现了(,,#゜Д゜)';
	});
	music.addEventListener('mouseleave', function () {
		this.innerText = '';
	});
}

{ // 日期设置
	const date = new Date();
	let dayName = '未知';
	switch (date.getDay()) {
		case 1:
			dayName = "一";
			break;
		case 2:
			dayName = "二";
			break;
		case 3:
			dayName = "三";
			break;
		case 4:
			dayName = "四";
			break;
		case 5:
			dayName = "五";
			break;
		case 6:
			dayName = "六";
			break;
		case 0:
			dayName = "日";
			break;
	}
	document.querySelector("#date").innerText = `${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}日 星期${dayName}`;
}

/* 用啥 Ajax 啊？用啥 jQuery 啊？还不赶紧用 Fetch 重写？@execute233
//换背景
function newImg() {
	$.ajax({
		url: "api/background",
		type: "get",
		data: {},
		contentType: "text",
		success: function (url) {
			$("html").css("background", "#333 url(\"" + url + "\") no-repeat fixed center");
		},
		error: function () {

		}
	});
}
//换一张二次元图片
function new2Img() {
	$.ajax({
		url: "/api/background2",
		type: "get",
		data: {},
		contentType: "text/text",
		success: function (url) {
			$("html").css("background", "#333 url(\"" + url + "\") no-repeat fixed center");
		},
		error: function () {

		}
	});
}

//天气设置
function weather() {
	$.ajax({
		url: "api/weather",
		type: "get",
		data: {},
		contentType: "text/json",
		success: function (json) {
			var city = json.city;//城市名
			var wea = json.wea;//天气
			var tem_night = json.tem_night;//最低温度
			var tem_day = json.tem_day;//最高温度
			var result = city.toString() + "\u00A0" + wea.toString() + "\u00A0" + tem_night.toString() + "℃~" + tem_day.toString() + "℃";
			$("#todayWeather").text(result);
		},
		error: function () {

		}
	});
}
*/

{
	//换鼠标样式
	document.querySelector('#newMouse').addEventListener('click', () => Swal.fire("还没做好", '此功能仍在开发中……', 'warning'));

	// 复制到剪切板
	document.querySelector('#copyQQ').addEventListener('click', () => copy('1647643661'));
	document.querySelector('#copyWeChat').addEventListener('click', () => copy("execute233"));
}