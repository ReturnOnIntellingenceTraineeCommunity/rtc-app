<html>

<head>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <link href="runnable.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Load jQuery and the validate plugin
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script> -->


</head>
<title> Profile View </title>

<body>
<link href="${pageContext.request.contextPath}/META-INF/resources/images/back.jpeg" rel="stylesheet">
<div id="content">
<style>
    body
    {
        background-image:url(back.jpeg);
        background-repeat: no-repeat;
        background-attachment:fixed;
        background-position:center;
        background-size: cover;
    }
    #content
    {
        background-color: rgba(255,255,255,0.7);
    }
</style>

<form action="" method="post" id="view-form" novalidate=" novalidate">
<div class="Profile view">

    <center><h1> Welcome to user profile view! </h1> </center>
    <h2><label> User name: </label> <br></h2>
    <h2><label> Last name:</label> <br></h2>
    <h2><label> E-mail: </label> <br></h2>

</div>

</form>
<form action="edit" method="post" id="view-form" >

    <button class="btn btn-primary"> Edit Page </button><br>


</form>
</div>
</body>
</html>
