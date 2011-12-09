package com.tan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class StringUtil {
	final static int ENC = 764;
	static String[] greets = {
			/*				"远方的你是否无恙？在遥远的思念里，改变的是我的容颜，不变的是永远爱你的心！爱人，真心愿你新年快乐！",
							"日出+日落=朝朝暮暮，月亮+星星=无限思念，风花+雪月=柔情蜜意，流星+心语=祝福万千，祝你有个快乐的新年！",
							"我最亲爱的朋友：在新年来临之际，祝愿上帝保佑您！观音菩萨护住您！财神抱住您！爱神射住您！食神吻住您！",
							"我把新世纪的祝福和希望，悄悄地放在将融的雪被下，让它们沿着春天的秧苗生长，送给你满年的丰硕与芬芳！",
							"你在新年夜被通缉了，你的罪行是：1.对朋友太好，又够义气；2.青春的面孔，灿烂的笑容。本庭现判决如下：罚你终身做我的朋友，不得上诉！",
							"金钱是一种有用的东西，但是只有在你觉得知足的时候，它才会带给你快乐。所以你应该把多余的钱交给我，这样我们两个都会感到快乐了！",
							"今年过节不收礼，其实给点也可以。十块八块不嫌弃，十万八万过得去．你要真是没的送，短信一条也可以。新年快乐！",
							"一直很想跟你说，但不知你会不会觉得我太心急。我又怕被别人抢先一步，所以我决定鼓起勇气告诉你：新年快乐！",
							"祝您在新年的假期里：小酒喝到六亲不认，香烟抽到同归于尽，麻将搓到昼夜不分，跳舞跳到筋疲力尽，吹牛吹到自己不信。",
							"听说过年你要到我们家做客，我弟弟会代我去接你，为便于确认身份，请你左手拿两条上等烟，右手提两瓶茅台酒。",
							"祝你正财、偏财、横财，财源滚滚；亲情、友情、私情，情情如意；官运、财运、桃花运，运运亨通。",
			*/		
							"如果人生是一段旅途，快乐与悲伤就是那两条长长的铁轨，在我身后紧紧跟随。  ",
							"人不要等明天，因为没有人知道自己有没有明天。  ",
							"心中有所牵挂，生命才会坚强。  ",
							"大海的宽广在于汇集大大小小的川流，生命的汪洋在于包容深深浅浅的缘分。  ",
							"人生最重要的不是我们置身何处，而是我们将前往何处。  ",
							"人生有些事，错过一时，就错过一世。  ",
							"走的桥多，不一定走的路就多。吃的盐多，不一定吃的饭就多。走路的时候有伴就不觉得路远，吃饭的时候有伴就吃得香。  ",
							"都说人往高处走，可是高处不胜寒。水往低处流，谁知低处纳百川！  ",
							"Love means you don't have to say sorry,ever。  ",
							"说出来的不是苦，说不出的才叫苦。  ",
							"有一种缘，放手后成为风景。有一颗心，坚持中方显真诚。  ",
							"你懂了，我* 近天堂；你不懂，我成为经过。  ",
							"人生总有许多偶然和巧合，两条平行线，也可能会有交汇的一天。  ",
							"人生又有许多意外和错过，握在手里的风筝，也会突然断了线……  ",
							"在无尽的追寻中,你会有一个又一个巧合和偶然,也会有一个又一个意外和错过.  ",
							"现实的城市犹如雾中的风景,隐隐地散发着忧郁的美,承载着没有承诺的梦......  ",
							"世界上最永恒的幸福就是平凡，人生中最长久的拥有就是珍惜。  ",
							"每一棵大树的成长都要接受阳光，也包容风雨。  ",
							"要随波逐浪，不可随波逐流。  ",
							"没有幻想、没有期望，就如同鸟儿被捆住了翅膀。  ",
							"过多的幻想、过高的期望，就像鸟儿不知飞向何方。  ",
							"没见过草原，不知道天多高地多厚。  ",
							"没见过草原上的白云，不知道什么是空灵，什么是纯净。  ",
							"把脸一直向着阳光，这样就不会见到阴影。  ",
							"爱情无需刻意去把握，越是想抓牢自己的爱情，反而容易失去自我，事物原则，失去彼此之间应该保持的宽容可谅解，  ",
							"爱情也会因此而变成毫无美感的形式。爱情就像一捧沙，抓得越紧，流失得越多。  ",
							"错把绝不当勇气的人，人生路会越走越窄。  ",
							"一帆风顺的人，往往经受不住挫折。  ",
							"以克人之心克己，以容己之心容人。  ",
							"爱其实是一种习惯，你习惯生活中有他，他习惯生活中有你。拥有的时候不觉得什么，一旦失去，却仿佛失去了所有。  ",
							"每一种选择都有不同的结局，就如走不同的路就会有不同的风景。所以，如果想看灿烂的风景，不妨沉思片刻再做选择。  ",
							"前方无绝路，希望在转角。  ",
							"人不能不长大，长大就像是赶路，一路风景常换常新。  ",
							"对前途要看得乐观些，对人心要看得悲观些。  ",
							"明明灭灭的人生，我愿作一盏灯，温暖你的寒冷，关照你的一生。  ",
							"星星啊，我曾无故地爱你很久，后来，我发现你属于所有的眼睛，我不爱你了。后来我又发现谁也无法占有你，我更爱你了。  ",
							"没有水的地方就是沙漠，没有声音的地方就是寂寞。  ",
							"你来人间一趟，你要看看太阳。  ",
							"在对的时间，遇见对的人，是一生幸福；在对的时间，遇见错的人，是一场心伤；  ",
							"在错的时间，遇见错的人，是一段荒唐；在错的时间，遇见对的人，是一阵叹息。  ",
							"也许上帝当年从亚当身上抽出那根肋骨时，他就想到了。他想女人永远都可以要男人的命。  ",
							"她可以让你生，也可以让你死，因为她曾经是保护男人心脏的那根肋骨，她可以保护你的心脏，也可以刺穿男人的心脏。  ",
							"在什么都不确定的年代，我们总是爱得太早，放弃得太快，轻易付出承诺，又不想等待结果。  ",
							"戒指，不再是一生一世的承诺，终生相守的誓言，却成了纪念伤感的烙印。  ",
							"就像有人说过的：戒指好比爱情，戴在手上，也是戴在心上；伤在心上，便也伤在手上。不敢碰的，是那心里的伤；不原摘的，是那难舍的爱。  ",
							"记得有人说，通往心脏的血脉是在无名指上，你知道我多想在今生，倾尽所有，牢牢地栓住你的无名指啊！  ",
							"时间过了，爱情淡了，相爱的人也就散了。  ",
							"如果缘尽也硬要牵扯，原本的美好就会变成种束缚，变成个你我都困在其中的牢笼。  ",
							"我会无法呼吸，你舍得看到我不自由吗？  ",
							"云中有风，风中有我，我能有什么？  ",
							"云对风说，风对我说，我能对谁说？  ",
							"当窗外的风雨来了，我们有房屋，当心中的风雨来了呢？  ",
							"如果没有云，天空会不会寂寞？  ",
							"如果没有天空，云到哪里停泊？  ",
							"钢琴的爱人是手，泪的爱人是喜，爱人的爱人是一辈子，你的爱人是我。  ",
							"人生有无限的可能。  ",
							"我不在这里，请不要在我的坟边哭泣，因为我没有睡去。  ",
							"我是扬起千千遍遍的风，我是雪地里闪闪的白光，我是拂照在田野里的太阳，我是夜空里的星星……  ",
							"如果最亲近的人我们都无法相信，我们还怎么活下去？  ",
							"当我们眺望远方的时候，近处的风景便看不清了。  ",
							"快乐是一种心态，不是一种状态。  ",
							"有大海一样的胸怀，生活中还有什么事情会让你失去笑容呢？  ",
							"你改变不了环境，但你可以改变自己；  ",
							"你改变不了事实，但你可以改变态度；  ",
							"你改变不了过去，但你可以改变现在；  ",
							"你不能控制他人，但你可以掌握自己；  ",
							"你不能预知明天，但你可以把握今天；  ",
							"你不可以样样顺利，但你可以事事尽心；  ",
							"你不能延伸生命的长度，但你可以决定生命的宽度；  ",
							"放弃便是得到，forget it = for get it ！  ",
							"昨天是一张已注销的支票，明天是一张期票，今天是手上的现金。"
					};
	static int greetsLen = greets.length;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String encode = encode("www.wujie.net/news.htm");// "ʋʋʋ˒ʋʉʖʕʙ˒ʒʙʈ˓ʒʙʋʏ˒ʔʈʑ ";
//
//		String b = decode(encode);
//
//		System.out.println(encode);
//		System.out.println(b);
//		System.out.println(now());
		
//		System.out.println(greet());
		
		
	}

	public final static boolean isEmpty(final String value) {
		return null == value || value.trim().length() == 0;
	}

	public static String encode(String value) {
		char[] chars = value.toCharArray();

		StringBuilder b = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {

			b.append((char) (chars[i] ^ ENC));
		}
		return b.toString();
	}

	public static String decode(String encode) {
		char[] chars = encode.toCharArray();

		StringBuilder b = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			b.append((char) (chars[i] ^ ENC));
		}
		return b.toString();
	}

	/**
	 * "http://localhost/dm/fdafdsajlfkdsafdsa/k.jsp?p=2"; -->
	 * http://localhost/dm/fdafdsajlfkdsafdsa/ "http://www.baidu.com/"; -->
	 * http://www.baidu.com/
	 * 
	 * @param url
	 * @return
	 */
	public static String getSite(final String url) {
		int idx = url.lastIndexOf("/");
		if (idx > 7 && idx >= 0) {
			String last = url.substring(idx + 1);
			if (last.matches("[a-zA-Z0-9]+")) {
				last = null;
				return url + '/';
			}
			return url.substring(0, idx + 1);
		}
		if (url.length() - 1 == idx) {
			return url;
		}
		return url + '/';
	}

	public static String url(final String url) {
		int type = -1;
		try {
			type = Integer.parseInt(url);
		} catch (Throwable e) {
		}
		if (type == 0) {
			return "http://www.wujie.net/news.htm";
		} else if (type == 1) {
			return "http://www.soundofhope.org/default.asp";
		} else if (type == 2) {
			return "http://www.tiananmenmother.org/index.htm";
		} else if (type == 3) {
			return "http://www.kanzhongguo.com/";
		} else if (type == 4) {
			return "http://www.ntdtv.com/";
		} else if (type == 5) {
			return "http://www.dajiyuan.com/";
		} else if (type == 6) {
			return "http://www.rfa.org/mandarin";
		} else if (type == 7) {
			return "http://www.aboluowang.com/";
		} else if (type == 8) {
			return "http://www.youmaker.com/video/index.html";
		} else if (type == 9) {
			return "http://gardennetworks.com/";
		} else if (type == 10) {
			return "http://heartyit.com/chinese.html";
		} else if (type == 11) {
			return "http://www.huaxiabao.org/";
		}
		return url;
	}

	private static final DateFormat CHINA_FORMAT = new SimpleDateFormat(
			"yy-MM-dd kk:mm:ss", Locale.CHINA);
	static {
		CHINA_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}
	private static final Pattern TIME_PATTERN = Pattern
			.compile("^\\d{2}-\\d{2}-\\d{2} (\\d{2}):\\d{2}:\\d{2}$");

	public static String now() {
		return CHINA_FORMAT.format(new Date());
	}

	public static String greet() {
		String now = StringUtil.now();
		String year = now.substring(0, 2);
		String month = now.substring(3, 5);
		String date = now.substring(6, 8);
		String hour = now.substring(9, 11);
		// Matcher matcher = TIME_PATTERN.matcher(now);
		// if (matcher.find()) {
		// String hour = matcher.group(1);
		
		
		
		String greet = "";
		if (year.equals("11") ) { // && (month.equals("01") || month.equals("02"))) {
//			greet = oldGetGreet();
			greet =  getGreet();
		}

		if (hour != null) {
			int h = Integer.parseInt(hour);

			if (h >= 0 && h <= 4) {
				return "深夜了，注意休息!" + greet;
			} else if (h > 4 && h <= 6) {
				return "凌晨好!" + greet;
			} else if (h >= 7 && h <= 12) {
				return "早上好!" + greet;
			} else if (h == 12) {
				return "中午好，吃饭！" + greet;
			} else if (h > 12 && h <= 13) {
				return "中午了，可以听听歌，午休一下！" + greet;
			} else if (h > 13 && h <= 16) {
				return "下午好!" + greet;
			} else if (h > 16 && h <= 17) {
				return "傍晚好!快吃晚饭了!" + greet;
			} else if (h > 17 && h <= 19) {
				return "晚饭过后,可以看下新闻联播!" + greet;
			} else if (h > 19 && h <= 21) {
				return "晚上好,可以看下电视剧，电影!" + greet;
			} else if (h > 21 && h <= 22) {
				return "可以睡觉了，晚上!" + greet;
			} else if (h >= 22 && h <= 24) {
				return "很晚了可以休息了!" + greet;
			}
		}
		
		// }
		// matcher = null;
		return "";
	}

	private static String getGreet() {
		int idx = getGreetLenRandom();// System.err.println( "idx " + idx );
		return greets[ idx ];
	}

	public static int getGreetLenRandom() {
		String timeMillis = String.valueOf(System.currentTimeMillis());
		String lastTwoCharsStr = timeMillis.substring( timeMillis.length() - 2 );
		int value = -1;
		try {
			value = Integer.parseInt( lastTwoCharsStr) ;
		} catch ( Throwable e ) {
		}
		
		if ( value >= 0 && value < greetsLen - 1 ) {
			return value;
		} else {
			try {
				Thread.sleep( 11  );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getGreetLenRandom();
		}
	}

	private static String oldGetGreet() {
		String greet;
		String timeMillis = String.valueOf(System.currentTimeMillis());
		char random = timeMillis.charAt(timeMillis.length() - 1);
		switch (random) {
			case '0': { greet = greets[0]; break; }
			case '1': { greet = greets[1]; break; }
			case '2': { greet = greets[2]; break; }
			case '3': { greet = greets[3]; break; }
			case '4': { greet = greets[4]; break; }
			case '5': { greet = greets[5]; break; }
			case '6': { greet = greets[6]; break; }
			case '7': { greet = greets[7]; break; }
			case '8': { greet = greets[8]; break; }
			case '9': { 
				random = timeMillis.charAt(timeMillis.length() - 2);
				int value = Character.getNumericValue(random);
				if (value <= 5) {
					greet = greets[9];
				} else {
					 greet = greets[10];
				}
				 break; }
			default : greet = greets[10];
		}
		return greet;
	}

}
