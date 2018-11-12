<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.MalformedURLException"%>
<%@ page import="java.net.URL"%>
<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.XML"%>

<%
	int INDENT_FACTOR = 4;
	String jsonobj;
	HttpURLConnection conn = (HttpURLConnection) new URL("http://api.saramin.co.kr/job-search?keywords=%EA%B0%9C%EB%B0%9C%EC%9E%90&count=100").openConnection();
	conn.connect();
	BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
	StringBuffer st = new StringBuffer();
	String line;
	while ((line = reader.readLine()) != null) {
		st.append(line);
	}

	JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
	String jsonPrettyPrintString = xmlJSONObj.toString(INDENT_FACTOR);  
	System.out.println(jsonPrettyPrintString); 
%>
<%=jsonPrettyPrintString %>

