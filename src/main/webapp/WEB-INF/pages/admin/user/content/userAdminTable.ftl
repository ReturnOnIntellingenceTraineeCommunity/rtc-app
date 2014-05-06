
<table>
    <thead>

    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Register Date</th>
        <th>Role</th>
        <th>Action</th>
    </tr>

    </thead>
    <tbody>

    <#list users as x>
    <tr>
        <#if (x.name) ?? && (x.surname) ?? >
            <td>
                <a href="<@spring.url"/admin/user/userPage/${x.id}"/>">  ${x.name + " " + x.surname} </a></td>
        <#else>
            <td>None</td>
        </#if>

        <#if (x.email)??>
            <td>${x.email}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (x.phone)??>
            <td>${x.phone}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (x.regDate)??>
            <td>${x.regDate}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (x.role)??>
            <td>${x.role}</td>
        <#else>
            <td>None</td>
        </#if>

        <td><a href="<@spring.url "/admin/user/delete/${x.id}" />">Remove</a></td>

    </tr>
    </#list>
    </tbody>

</table>