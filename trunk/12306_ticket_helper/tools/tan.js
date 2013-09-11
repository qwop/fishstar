function bull_shit() {
	//处理显示时间的
	unsafeInvoke( function() {
		var a= (''
		///////////////////////////////////////////////////// add by qwop ////////////////////////////////// 
				+
				"<tr class='append_row'> <td colspan='20' >" + 
					"<input type='button' class='fish_button _plan' value='深圳->景德镇' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _plan' value='东莞东->蕲春' name='_from' title='自定义出发地、目的地【深圳】' />" +

				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-06 二六' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-07 二七' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-08 二八' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-09 除夕' title='自定义出发日期' />" +

					"<input type='button' class='fish_button _startdatepicker' value='2013-09-30' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-16 初七' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-17 初八' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-18 初九' title='自定义出发日期' />" +
				//	"<input type='button' class='fish_button _startdatepicker' value='2013-02-19 初十' title='自定义出发日期' />" +
				"</td></tr>"


					+
				"<tr class='append_row'> <td colspan='20' >" + 
					"<label for='t_change_type' id='t_change_type_for' >改<font color=red>起</font>点</label><input type='checkbox' id='t_change_type' checked title='自定义修改起点还是终点' />" +
					"<input type='button' class='fish_button _from' value='南京' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='马鞍山' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='芜湖' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='宣城' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='绩溪县' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='黄山' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='景德镇' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='乐平市' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='蕲春' name='_from' title='自定义出发地、目的地【深圳】' />" +
					"<input type='button' class='fish_button _from' value='东莞东' name='_from' title='自定义出发地、目的地【深圳】' />" +
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
			'蕲春' : 'QRN',

			'深圳'	:'SZQ'
		};
		
		
		var dest = '深圳';  // 可以修改目的地

		$( "input._startdatepicker" ).live( 'click', function() {
			$("#startdatepicker").val( $( this ).val().match(/[\d-]+/)[0] );
		});

		$( "#t_change_type" ).live( 'click', function() {
			if ( this.checked ) {
				$( '#t_change_type_for' ).html( "改<font color=red>起</font>点" );
			} else {
				$( '#t_change_type_for' ).html( "改<font color=red>终</font>点" );
			}
		});

		$( "input._plan" ).live("click", function () {
			var str = $( this ).val();
			var strs = str.split( '->' );
			if ( strs && strs.length == 2 ) {
				var from = strs[ 0 ];
				dest = strs[ 1 ]; 
				if ( dest.legnth == 0 || from.length == 0 ) { alert("按钮有误");  }

				$( this ).val( dest + "->" + from );
				var newFrom = dest;
				dest = from;
				_exchange( newFrom );
			}
		});

		
		function _exchange( newFrom ) {
			var 
				fromText = $("#fromStationText"),
				fromCode = $("#fromStation"),
				toText = $("#toStationText"),
				toCode = $("#toStation")
			;
			

			fromText.val( newFrom );
			fromCode.val( _cacheFroms[ newFrom ] );

			toText.val( dest );
			toCode.val( _cacheFroms[dest] );
			
			fromText.css( 'background-color', 'red' );
		}

		// 
		$( "input._from" ).live("click", function () {
			if ( $( "#t_change_type" ).attr( 'checked' ) )
			{
				$("#fromStationText").val( $( this ).val() );
				$("#fromStation").val( _cacheFroms[$( this ).val()] );
			} else {
				$("#toStationText").val( $( this ).val() );
				$("#toStation").val( _cacheFroms[$( this ).val()] );
			}
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

bull_shit();