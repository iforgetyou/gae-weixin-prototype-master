<%--
  Created by IntelliJ IDEA.
  User: zhangyan53
  Date: 2016/3/31
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<html>
<head>
    <title>Upload ImageCard</title>
</head>
<body>
<form action="<%= blobstoreService.createUploadUrl("/blob") %>" method="post" enctype="multipart/form-data">
    <p>
        type: <input type="text" name="type">
    </p>
    <p>
        tags: <input type="text" name="tags">
    </p>
    <p>
        <input type="file" name="files">
    </p>
    <input type="submit" value="Submit">
</form>
</body>
</html>
