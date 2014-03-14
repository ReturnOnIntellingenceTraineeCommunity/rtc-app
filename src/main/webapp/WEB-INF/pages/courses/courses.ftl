<html>
<#import "/spring.ftl" as spring/>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<@spring.url'/resources/tag-it/js/tag-it.js'/>" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
    <link href="<@spring.url'/resources/tag-it/css/jquery.tagit.css'/>" rel="stylesheet" type="text/css">
    <link href="<@spring.url'/resources/tag-it/css/tagit.ui-zendesk.css'/>" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(document).ready(function() {
            $('#singleFieldTags').tagit({
                singleField: true,
                singleFieldNode: $('#mySingleField')
            });
        });
    </script>
</head>
<body>
    <div>
        <input name="tags" id="mySingleField" disabled="true">
        <ul id="singleFieldTags"></ul>
    </div>
    <table class="datatable">
        <tr>
            <th><@spring.message "courses.category"/></th>  <th><@spring.message "courses.name"/></th>  <th><@spring.message "courses.startDate"/></th>  <th><@spring.message "courses.author"/></th>  <th><@spring.message "coursesPage.action"/></th>
        </tr>
    <#list courses as course>
        <tr>
            <td>${course.type}</td> <td><a href="<@spring.url "/courses/${course.id}" />">${course.name}</a></td> <td>${course.startDate?date}</td> <td>${course.author.firstName}&nbsp;${course.author.lastName}</td> <td><a href="<@spring.url "/courses/delete/${course.id}" />"><@spring.message "coursesPage.action.delete"/></a> </td>
        </tr>
    </#list>
    </table>
    <a href="<@spring.url "/courses/create" />"><@spring.message "coursesPage.action.create"/></a>
</body>
</html>