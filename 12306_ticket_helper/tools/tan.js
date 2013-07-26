function fuck() {
	//处理显示时间的
	unsafeInvoke( function() {
		var a= (''
		///////////////////////////////////////////////////// add by qwop ////////////////////////////////// 
				+
				"<tr class='append_row'> <td colspan='20' >" + 
					"<input type='button' id='tan1' class='fish_button _plan' value='深圳->景德镇' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' id='tan1' class='fish_button _from' value='南京' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='马鞍山' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='芜湖' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='宣城' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='绩溪县' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='黄山' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='景德镇' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='乐平市' name='_from' title='自定义出发地、目的地【深圳】' />" +

				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-06 二六' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-07 二七' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-08 二八' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-09 除夕' title='自定义出发日期' />" +

					"<input type='button' class='fish_button _startdatepicker' value='2013-04-29' title='自定义出发日期' />" +
					"<input type='button' class='fish_button _startdatepicker' value='2013-04-30' title='自定义出发日期' />" +
					"<input type='button' class='fish_button _startdatepicker' value='2013-05-04' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-16 初七' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-17 初八' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-18 初九' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-19 初十' title='自定义出发日期' />" +
				"</td></tr>"

			);


		var it=$("form[name=querySingleForm] .cx_from:first");
		it.find("tr:last").after( a );
		// add by tan, show the information of text
		var _cacheFroms = {
			'南京'	: 'NJH',
			'马鞍山' : 'MAH',
			'芜湖' : 'WHH',
			'宣城' : 'ECH',
			'绩溪县' : 'JRH',
			'黄山' : 'HKH',
			'景德镇' : 'JCG',
			'乐平市' : 'LPG',
			'东莞东' : 'DMQ',
			'东莞' : 'DAQ',
			'深圳'	:'SZQ'
		};
		var exchange = true; // 深圳->景德镇
		var planValue = '深圳->景德镇';
		var dest = '深圳', destCode  = _cacheFroms[dest];  // 可以修改目的地

		$( "input._startdatepicker" ).live( 'click', function() {
			$("#startdatepicker").val( $( this ).val().match(/[\d-]+/)[0] );
		});
		
		
		$( "input._plan" ).val( exchange ? dest + '->景德镇' : '景德镇->' + dest ); 

		$( "input._plan" ).live("click", function () {
			exchange = !exchange;
			planValue = exchange ? dest + '->景德镇' : '景德镇->' + dest; 
			$( this ).val( planValue );
			_exchange();
		});

		
		function _exchange() {
			if ( exchange ) {  // 深圳->景德镇
				if ( destCode == $("#toStation").val() ) {
					$("#toStationText").val( $("#fromStationText").val() );
					$("#toStation").val( $("#fromStation").val() );

					$("#fromStationText").val( dest );
					$("#fromStation").val( destCode );
				}
			} else {   // 景德镇->深圳
				if ( destCode == $("#fromStation").val() ) {
					$("#fromStationText").val( $("#toStationText").val() );
					$("#fromStation").val( $("#toStation").val() );

					$("#toStationText").val( dest );
					$("#toStation").val( destCode );
				}
			
			}
		}

		$( "input._from" ).live("click", function () {
			var FROM_STATION_TEXT = $( this ).val(); // utility.getPref('_from_station_text');  // 出发站名称
			var FROM_STATION_TELECODE = _cacheFroms[$( this ).val()]; // utility.getPref('_from_station_telecode');  // 出发站电报码

			var TO_STATION_TEXT = dest; //  utility.getPref('_to_station_text');  // 到达站名称
			var TO_STATION_TELECODE = destCode; // utility.getPref('_to_station_telecode');  // 到达站电报码
	/*		
			var TO_STATION_TEXT = utility.getPref('_to_station_text');  // 到达站名称
			var TO_STATION_TELECODE = utility.getPref('_to_station_telecode');  // 到达站电报码
			var DEPART_DATE = utility.getPref('_depart_date');  // 出发日期
			var DEPART_TIME = utility.getPref('_depart_time'); // 出发时间
	*/

			if (FROM_STATION_TEXT) {
				$("#fromStationText").val(FROM_STATION_TEXT);
				$("#fromStation").val(FROM_STATION_TELECODE);

				$("#toStationText").val(TO_STATION_TEXT);
				$("#toStation").val(TO_STATION_TELECODE);


				/*
				$("#toStationText").val(TO_STATION_TEXT);
				$("#toStation").val(TO_STATION_TELECODE);

				$("#startdatepicker").val(DEPART_DATE);
				$("#startTime").val(DEPART_TIME); */
			}


			_exchange();

		});



	} ); // end of unsafeInvoke
}

function unsafeInvoke(callback) {
	/// <summary>非沙箱模式下的回调</summary>
	var cb = document.createElement("script");
	cb.type = "text/javascript";
	cb.textContent = buildCallback(callback);
	document.head.appendChild(cb);
}

function buildCallback(callback) {
	var content = "";
	if (!utility_emabed) {
		content += "window.helperVersion='" + version + "'; window.compVersion='" + compVersion + "'; if(typeof(window.utility)!='undefined' && navigator.userAgent.indexOf('Maxthon')==-1){ alert('我勒个去! 检测到您似乎同时运行了两只助手! 请转到『附加组件管理『（Firefox）或『扩展管理』（Chrome）中卸载老版本的助手！');}; \r\nwindow.utility=" + buildObjectJavascriptCode(utility) + "; window.utility.init();\r\n";
		utility_emabed = true;
	}
	content += "(" + buildObjectJavascriptCode(callback) + ")();";

	return content;
}

fuck();