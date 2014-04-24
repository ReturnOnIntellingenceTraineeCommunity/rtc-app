<form name="user" id="user" action="<@spring.url "/user/save" />" method="post" style="padding-top: 50px; margin-left: 20%;">
    <h3><@spring.message "user.registrationTitle"/></h3>
    <hr width="72%">
<#include "userForm.ftl" />

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>

        <div class="span2" style="text-align: right">

            <input type="submit" class="btn" value="Register"/> or <a
                href="<@spring.url "/" />">Cancel</a>

        </div>
    </div>
</form>