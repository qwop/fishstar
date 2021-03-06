package com.tan.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tan.util.StringUtil;

/**
 * 
 * @author Dolphin.
 *
 */
public final class ShowServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	
/*	private static Proxy PROXY ;
	
	static {
		try {
			PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName("proxy2.com.cn"), 80));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		//String prefix = request.getParameter("prefix");
		String encoding = request.getParameter("encoding");
		
		// get the special url, if the url is a number.
		url = StringUtil.url(url);
		
		ServletOutputStream out = response.getOutputStream();
		/**
		 * 1. string url format.
		 */
		// check for url.
		if (StringUtil.isEmpty(url)) {
			out.write("<h1 color='red'>URL is blank!</h1><a href='./index.jsp'>HOME</a>".getBytes());
			return;
		}
		
		// check url's suffix.
		if ( url.endsWith(".mp3") ||
				url.endsWith(".rmvb") ||
				url.endsWith(".rm") ||
				url.endsWith(".mp4") ||
				url.endsWith(".rar") ||
				url.endsWith(".zip") ||
				url.endsWith(".7z") ||
				url.endsWith(".wma") ||
				url.endsWith(".torrent") ||
				url.endsWith(".avi") ||
				url.endsWith(".asf") ||
				url.endsWith(".wmv")
			) {
			out.write(("<h1 color='red'>Please Download The " + url + "</h1><BR>Suffix can not be mp3,rmvb,rm,mp4,rar,zip,7z,wma,torrent,avi,asf<BR><a href='./index.jsp'>HOME</a>").getBytes());
			return;
		}
		// check url's preffix.
		url = url.toLowerCase();
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}
		
		URL u = null;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			out.write("<h1 color='red'>URL is blank!</h1><a href='./index.jsp'>HOME</a>".getBytes());
			return;
		}
		// connection.
		HttpURLConnection conn = (HttpURLConnection) u.openConnection(); // PROXY.
		int length  = conn.getContentLength();
		String contentType = conn.getContentType();
		
		
		
		/**
		 * 2. Check Encoding.
		 */	
		if (StringUtil.isEmpty(encoding)) {
			encoding = getEncoding(contentType);
		}
		
		conn.setConnectTimeout(10 * 1000); // Time out 10 seconds.
		conn.connect();
		InputStream in = conn.getInputStream();
		
		response.reset();
		// set response's header information.
		response.setCharacterEncoding(encoding);
		response.setContentType(contentType);
		response.setContentLength(length);
		
		if (null != in) {
			byte[] buf = new byte[2048]; // Buf.
			int len = -1;
			
			// Print the base information At the begin of the document.
			out.print("<BASE HREF=\"" + StringUtil.getSite(url) + "\"/>");
			
			while (-1 != (len = in.read(buf, 0, 2048)) ) {
				out.write(buf, 0, len);
			}
			
/*			// Arguments for find the base.
			//////////替换base. 替换标签 </head>
			boolean replacedBase = false;
			String data = null;
			int idx = -1;
			while (-1 != (len = in.read(buf, 0, 2048)) ) {
				if (replacedBase) {
					out.write(buf, 0, len);
				} else {
					data = new String(buf, 0,len, encoding).toLowerCase(Locale.ENGLISH);
					idx = data.indexOf("</head>");
					if (idx >= 0) {
						data = data.replace("</head>", "<base href=\"" + getSite(url) + "\" /></head>");
						out.write(data.getBytes(encoding));
						replacedBase = true;
					}else {
						out.write(buf, 0,len);
					}
				}
			}
			//////////替换base. 替换标签 </head>
*/			conn.disconnect();
			
			out.flush();
			out.close();
			in.close();
			// Finished.
			in = null;
			out = null;
			u = null;
			conn = null;
			buf = null;
//			data = null;
		}
		
		// Debug the information of the connection.
		//out.write("Length\t" + length + "<br>Encoding\t" + contentEncoding + "<br>type\t" + contentType+ "<a href='./index.jsp'>Home</a>");
	}
	
	private static String getEncoding( String value ) {
		if ( !StringUtil.isEmpty( value ) ) {
			value = value.toLowerCase();
			// 	text/html; charset=utf-8
			int idx = value.indexOf("charset=");
			if (idx >= 0) {
				return value.substring(idx + 8);
			}
		}
		return "utf-8";
	}



	/*private final boolean hasSuffix(HttpServletRequest request,
			HttpServletResponse response, String u) throws ServletException,
			IOException {
		if (!u.endsWith(".com") && !u.endsWith(".cn")
				&& !u.endsWith(".net") && !u.endsWith(".org")
				&& !u.endsWith(".cc") && !u.endsWith(".edu")
				&& !u.endsWith(".gov") && !u.endsWith(".int")
				&& !u.endsWith(".mil") && !u.endsWith(".biz")
				&& !u.endsWith(".info") && !u.endsWith(".pro")
				&& !u.endsWith(".name") && !u.endsWith(".museum")
				&& !u.endsWith(".coop") && !u.endsWith(".aero")
				&& !u.endsWith(".hk") && !u.endsWith(".xx")
				&& !u.endsWith(".in") && !u.endsWith(".jp")
				&& !u.endsWith(".kp") && !u.endsWith(".mo")
				&& !u.endsWith(".us") && !u.endsWith(".uk")
				&& !u.endsWith(".kr")) {
			request.setAttribute("error", "Your suffix name is wrong!!!");
			return false;
		}
		return true;
	}*/
}
