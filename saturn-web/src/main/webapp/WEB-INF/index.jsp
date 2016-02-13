<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.getServerName()}:${req.getServerPort()}/${req.getContextPath()}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Web Socket Test Page</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <link href="//<c:out value='${baseURL}' />resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="page-header">
        <h1>Websoket test page
            <small>Saturn test task</small>
        </h1>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">Connect</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <a id="btn-connect" class="btn btn-default" href="#" role="button">Connect</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Success send</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="text-send" class="col-sm-2 control-label">Send</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="3" id="text-send"></textarea>
                            </div>
                        </div>
                        <div>
                            <a id="btn-set-success" class="btn btn-default" href="#" role="button">Set Success content</a>
                            <a id="btn-set-fail" class="btn btn-default" href="#" role="button">Set Fail content</a>
                            <a id="btn-send" class="btn btn-default" href="#" role="button">Send</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">Receive log</div>
                <div class="panel-body">
                    <ul id="receive-log" class="list-group">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var res = {
        url: {
            auth: "ws://<c:out value='${baseURL}' />auth"
        }
    };
</script>
<script src="//<c:out value='${baseURL}' />resources/js/jquery-2.2.0.min.js"></script>
<script src="//<c:out value='${baseURL}' />resources/bootstrap/js/bootstrap.min.js"></script>
<script src="//<c:out value='${baseURL}' />resources/js/sockjs-1.0.3.min.js"></script>
<script src="//<c:out value='${baseURL}' />resources/js/stomp.min.js"></script>
<script src="//<c:out value='${baseURL}' />resources/js/main.js"></script>
</body>
</html>