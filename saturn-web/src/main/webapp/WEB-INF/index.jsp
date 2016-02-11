<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Web Socket Test Page</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <link href="<spring:url value="/resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row">
        <form class="form-inline">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" placeholder="Jane Doe">
            </div>
            <div class="form-group">
                <label for="password">Email</label>
                <input type="text" class="form-control" id="password">
            </div>
            <a id="auth-btn" class="btn btn-default" href="#" role="button">Auth</a>
        </form>
    </div>
</div>
<script>
    var res = {
        url: {
            //auth:"<spring:url value="/auth"/>"
            auth:"ws://localhost:8080/auth"
        }
    };
</script>
<script src="<spring:url value="/resources/js/jquery-2.2.0.min.js"/>"></script>
<script src="<spring:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<spring:url value="/resources/js/sockjs-1.0.3.min.js"/>"></script>
<script src="<spring:url value="/resources/js/stomp.min.js"/>"></script>
<script src="<spring:url value="/resources/js/main.js"/>"></script>
</body>
</html>