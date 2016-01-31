<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Collaborative Whiteboard App</h1>

	<table>
		<tr>
			<td id="output">
			<c:forEach items="${list}" var="post">
			<div>${post.text}</div>
			</c:forEach>
			</td>
			<td>
				<form id= "inputForm" name="inputForm">
					<table>

						<tr>
							<th>Message</th>
							<td><input type="text" id="txt"></td>
							<td><a id="btn" href="#">Send</a></td>
						</tr>

					</table>
				</form>
			</td>
		</tr>
	</table>

	<script type="text/javascript"
		src="<c:url value="/resources/js/websocket_message.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/message.js" />"></script>
</body>
</html>
