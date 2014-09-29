<div class= "row">
    <div class="col-md-6">
        <!--Gender-->

        <div class="form-group">
            <label class="control-label col-md-2"
                   for="user.gender"><@spring.message "user.gender"/></label>

            <div class="col-md-4" style="padding-top: 5px">
            <@spring.bind "user.gender"/>
                <input type="radio" name="gender" id="optionsRadios1"
                       value="Male" checked
                       style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
                <input type="radio" name="gender" id="optionsRadios2"
                       value="Female"
                       style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>
            </div>

        </div>

        <!--Last Name-->
    <@spring.formItem  "user.surname" "class=\"required\""/>
        <!--Middle Name -->
    <@spring.formItem  "user.middleName" />

        <!--First Name-->
    <@spring.formItem  "user.name" "class=\"required\""/>

        <!--Birth Date-->
    <@spring.formItem "user.birthDate"  'class="input-medium required"' "datepiker"/>

    </div>

    <div class="col-md-6">
        <img src = "<@spring.url'/resources/images/errorCat.jpg'/>" alt="..." class="round-image">

        <div class ="row">
        <#--<div class="col-md-2">-->
            <button type="button" class="btn btn-link">Upload</button>
        <#--</div>-->
        </div>

    </div>


</div>

<hr/>

<div class="row">

    <div class="col-md-6">
        <!--Email-->
    <@spring.formItem  "user.email" "class=required"/>

        <!--Authorities-->
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="selectedRole"><@spring.message "user.role"/></label>

            <div class="col-md-4">
                <select id="selectedRole" name="selectedRole" class="required">
                <#list roles as role>
                    <option value="${role}">${role.roleViewName}</option>
                </#list>
                </select>
            </div>
        </div>
    </div>


    <div class="col-md-6">
        <!--Password-->
    <@spring.formItem "user.password" "id=password class=required" "password"/>
        <div class="controls">
            <label for="check"></label>
            <input id="check"
                   onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
                   name="fff" type="checkbox" value="false"
                   style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
        </div>

    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--City-->
    <@spring.formItem  "user.city" />
    </div>
    <div class="col-md-6">
        <!--Phone-->
    <@spring.formItem  "user.phone" "class=\"required\""/>
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--University-->
    <@spring.formItem  "user.university" />
        <!--Speciality-->
    <@spring.formItem  "user.faculty" />

    </div>

    <div class="col-md-6">
        <!--Faculty-->
    <@spring.formItem  "user.speciality" />
    </div>
</div>

<hr/>

<div class="row">
    <div class="col-md-6">
        <!--Languages-->
    <@spring.formItem "user.programmingLanguages" />
    </div>

    <div class="col-md-6">
        <!--English-->
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="english"><@spring.message "user.english"/></label>

            <div class="col-md-4">
            <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;" class=\"required\"'/>
            </div>
        </div>
    </div>
</div>

<hr/>

<!--Note-->
<div class="row">
    <div class="col-md-12" >
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="note"><@spring.message "user.note"/></label>
            <div class="col-md-10">
            <@spring.formTextarea
            "user.note" 'rows="3" maxlength="255"
            id=\"note\"
            class=\"required field col-md-10\"' />
            </div>
        </div>
    </div>
</div>

<hr/>

<div class="controls">
    <label for="check"></label>
    <input id="check"
           name="fff" type="checkbox" value="false"
           style="margin: 0px 0px 0px;">  <@spring.message "user.active"/>
</div>

<hr/>

<@spring.formValidation formName="user" jsonRules="${validationRules}"/>
<script type="text/javascript" charset="utf8"
        src="<@spring.url'/resources/js/pages/userMailValidation.js'/>"></script>
<script>
    $(function () {
        addMailValidation("<@spring.url "/mailExist/" />", "${user.email!""}")
    });
</script>
