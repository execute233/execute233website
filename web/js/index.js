addAction();
setDate();
setTime();
newImg();

weather();
/*注册事件*/
function addAction() {
	$("#music").mouseenter(function () {
		$(this).text("被你发现了(,,#゜Д゜)")
	}).mouseleave(function () {
		$(this).text("")
	});
}
/*日期设置*/
function setDate() {
	var date=new Date();
	if (date.getDay()==1){
		dayName="一";
	} else if (date.getDay()==2) {
		dayName="二";
	} else if (date.getDay()==3) {
		dayName="三";
	} else if (date.getDay()==4) {
		dayName="四";
	} else if (date.getDay()==5) {
		dayName="五";
	} else if (date.getDay()==6) {
		dayName="六";
	} else if (date.getDay()==0) {
		dayName="日";
	}
	var dateString=date.getFullYear()+"\u00A0年\u00A0"+date.getMonth()+"\u00A0月\u00A0"+date.getDate()+"\u00A0日\u00A0星期"+dayName;
	$("#date").text(dateString);	
}
//时间设置
function setTime() {
	var date=new Date();
	var hours=date.getHours();
	if (hours.toString().length==1) {
		hours="0"+hours;
	}
	var minutes=date.getMinutes();
	if (minutes.toString().length==1) {
		minutes="0"+minutes;
	}
	var seconds=date.getSeconds();
	if (seconds.toString().length==1) {
		seconds="0"+seconds;
	}
	var time=hours+":"+minutes+":"+seconds;
	$("#time").text(time);
	setTimeout(setTime,1000);
}
//换背景
function newImg() {
	$.ajax({
		url: "api/background",
		type: "get",
		data: {},
		contentType: "text",
		success:function(url){
			$("html").css("background","#333 url(\""+url+"\") no-repeat fixed center");
		},
		error: function() {

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
		success:function(url){
			$("html").css("background","#333 url(\""+url+"\") no-repeat fixed center");
		},
		error: function() {

		}
	});
}
//换鼠标样式
function newmouse() {
	alert("还没做好...");
}
//天气设置
function weather() {
	$.ajax({
		url: "api/weather",
		type: "get",
		data: {},
		contentType: "text/json",
		success:function(json){
			var city=json.city;//城市名
			var wea=json.wea;//天气
			var tem_night=json.tem_night;//最低温度
			var tem_day=json.tem_day;//最高温度
			var result=city.toString()+"\u00A0"+wea.toString()+"\u00A0"+tem_night.toString()+"℃~"+tem_day.toString()+"℃";
			$("#todayWeather").text(result);
		},
		error: function() {

		}
	});
}
//复制到剪切板
function copyQQ() {
	copy("1647643661");
}
function copyWechat() {
	copy("execute233");
}
function copy(value) {
	const input=document.createElement('input');
	    document.body.appendChild(input);
	    input.setAttribute('value', value);
	    input.select();
	    if (document.execCommand('copy')) {
	        document.execCommand('copy');
	        alert("复制成功")
	    }
	 document.body.removeChild(input);
}