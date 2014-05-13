<@spring.adminViewAllTableGenetation "users">
    <#include "userAdminTable.ftl">
<div class="btn" type="submit" onclick="doAjaxPost()">BUTTONISHE</div>
</@spring.adminViewAllTableGenetation >
<br><br>
<div align = "right">
    <form name="createUser" action="createUser" method="get">
    <button type="submit">Create</button>
</form>
</div>


<script type="text/javascript">

    function doAjaxPost() {

        type: "POST"
        url:
        dataType: "json",
        success: function(response)
        {

        },
        error: function(e)
        {
            alert('ERROR '+e);
        }

    }

</script>

