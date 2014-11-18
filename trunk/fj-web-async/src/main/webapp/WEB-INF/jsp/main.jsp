<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.forumj.common.Component"%>
<%@page import="org.forumj.common.Command"%>
<%@page import="org.forumj.common.FJRequestParameter"%>
<%@page import="org.forumj.common.FJUrl"%>
<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv='content-type' content='text/html; charset=UTF-8'>
<link rel="stylesheet" href="css/reset.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui-1.9.1.custom.min.css" type="text/css" />
<script type="text/javascript">
var layout="";
var logo="";
var topMenu="";
var content="";
var bottomMenu="";
var usersOnline="";
var footer="";
var errorDiv = ""; 
var currentComponent=<%=Component.DEFAUL_COMPONENT.getId()%>;;
var mainUrl="<%=request.getContextPath()%>/<%=FJUrl.MAIN%>";
var COMMAND_PARAMETER="<%=FJRequestParameter.COMMAND%>";
var COMPONENT_PARAMETER="<%=FJRequestParameter.COMPONENT%>";
var GET_LOGO_COMMAND="<%=Command.GET_LOGO.getCommand()%>";
var GET_MENU_COMMAND="<%=Command.GET_MENU.getCommand()%>";
var LOGOUT_COMMAND="<%=Command.LOGOUT.getCommand()%>";
var GET_LOGIN_COMMAND="<%=Command.GET_LOGIN.getCommand()%>";
var FORUM_INDEX_COMMAND="<%=Command.FORUM_INDEX.getCommand()%>";
var FORUM_THREAD_COMMAND="<%=Command.FORUM_THREAD.getCommand()%>";
var FORUM_INDEX_COMPONENT=<%=Component.FORUM_INDEX.getId()%>;
var FORUM_THREAD_COMPONENT=<%=Component.FORUM_THREAD.getId()%>;
</script>
<link rel="stylesheet" href="css/w2ui/w2ui-1.4.2.min.css" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="js/w2ui/w2ui-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jsmain_chek.js"></script>
<script type="text/javascript" src="js/component_logo.js"></script>
<script type="text/javascript" src="js/component_menu.js"></script>
<script type="text/javascript" src="js/component_forum_index.js"></script>
<script type="text/javascript" src="js/component_forum_thread.js"></script>
<script type="text/javascript" src="js/component_login.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/indicator.js"></script>
<script type="text/javascript" src="js/jsview_ok.js"></script>
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<title>Форум дилетантів</title>
</head>

<body class='mainBodyBG'>
<div id="layout" style="width: 100%; height: 100%;"></div>
<script type="text/javascript">
$(function () {
    var pstyle = 'border: 1px solid #dfdfdf; padding: 5px;';
    $('#layout').w2layout({
        name: 'layout',
        panels: [
            { type: 'top', size: 100, style: pstyle, content: 'top' },
            { type: 'left', size: 200, style: pstyle, content: 'left' },
            { type: 'main', style: pstyle, content: 'main' },
            { type: 'bottom', size: 100, style: pstyle, content: 'bottom' }
        ]
    });
});
w2ui['layout'].toggle('left');
</script>
</body>
</html>