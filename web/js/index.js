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

async function fetchData(url, asJson = true, options = {}) { // 获取数据
	let message = '';
	try {
		const response = await fetch(url, options);
		if (response.ok) {
			try {
				return { success: true, result: await (asJson ? response.json() : response.text()), message: '' };
			} catch (e) {
				message = '在解析 JSON 过程中发生异常，详细信息请见控制台！';
				console.error('在解析 JSON 过程中发生异常：', e);
			}
		} else {
			message = '在 Fetch 过程中接收到了不成功的状态码，详细信息请见控制台！';
			console.error('在 Fetch 过程中接收到了不成功的状态码，响应对象：', response);
		}
	} catch (e) {
		message = '在 Fetch 过程中发生异常，详细信息请见控制台！';
		console.error('在 Fetch 过程中发生异常：', e);
	}
	return { success: false, result: null, message };
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

{ // 换背景
	async function changeBackground() {
		const result = await fetchData('api/' + this.dataset.backgroundSubUrl, false);
		if (result.success) {
			console.log('换背景成功，新背景地址：', result.result);
			document.querySelector('html').style.background = `#333 url("${result.result}") no-repeat fixed center`;
		} else {
			console.error('换背景失败，错误信息：', result.message);
			Swal.fire('换背景失败', result.message, 'error');
		}
	}
	document.querySelectorAll('.changeBackground').forEach(e => e.addEventListener('click', changeBackground));
}


{
	(async function () { // 获取天气
		const result = await fetchData('api/weather');
		if (result.success) {
			const data = result.result;
			console.log('获取天气成功，天气信息：', data);
			document.querySelector("#todayWeather").innerText = `${data.city} ${data.weather} ${data.temperature_night}℃~${data.temperature_day}℃`;
		} else {
			console.error('获取天气失败，错误信息：', result.message);
			//Swal.fire('获取天气失败', result.message, 'error');
			document.querySelector("#todayWeather").innerText = '获取天气失败';
		}
	})();
}

{
	//换鼠标样式
	document.querySelector('#newMouse').addEventListener('click', () => Swal.fire("还没做好", '此功能仍在开发中……', 'warning'));

	// 复制到剪切板
	document.querySelector('#copyQQ').addEventListener('click', () => copy('1647643661'));
	document.querySelector('#copyWeChat').addEventListener('click', () => copy("execute233"));
}