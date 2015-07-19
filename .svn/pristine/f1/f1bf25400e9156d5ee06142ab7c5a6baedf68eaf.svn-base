<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>

<%-- <c:set var="organizationId" value="${empty organization ? 0 : organization.id}"/>
<c:set var="jobId" value="${empty job ? 0 : job.id}"/> --%>

<div data-table="table" class="panel">

    <ul class="nav nav-tabs">
        <li ${param['search.deleted_eq'] ne 'true' and param['search.status_eq'] ne 'blocked' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/user/list">
                <i class="icon-table"></i>
                所有用户列表
            </a>
        </li>

    </ul>

    <es:showMessage/>


    <div class="row-fluid tool ui-toolbar">
        <div class="span3">
            <div class="btn-group">
                <shiro:hasPermission name="/admin/sys/user/create">
                <a class="btn no-disabled btn-create">
                    <span class="icon-file-alt"></span>
                    新增
                </a>
               </shiro:hasPermission>
               <shiro:hasPermission name="/admin/sys/user/update">
                <a id="update" class="btn btn-update">
                    <span class="icon-edit"></span>
                    修改
                </a>
                </shiro:hasPermission>
                <shiro:hasPermission name="/admin/sys/user/delete">
                <a class="btn btn-delete">
                    <span class="icon-trash"></span>
                    删除
                </a>
                </shiro:hasPermission>
                 <shiro:hasPermission name="/admin/sys/user/auth">
               <a class="btn btn-auth">
               <span class="icon-tag"></span>
                   授角色            
                </a>
                 </shiro:hasPermission>
            </div>
            
        </div>
        <div class="span9">
            <%@include file="searchForm.jsp" %> 
        </div>
    </div>
    <table id="table" class="sort-table table table-bordered table-hover" data-prefix-url="${ctx}/admin/sys/user">
        <thead>
        <tr>
            
            <th style="width: 80px;">
                <a class="check-all" href="javascript:;">全选</a>
                |
                <a class="reverse-all" href="javascript:;">反选</a>
            </th>
            <th sort="id">编号</th>
            <th sort="username">用户名</th>
         
            <th sort="createDate">创建时间</th>
            
        </tr>
        <tbody>
        <c:forEach items="${page.content}" var="m">
            <tr>
                 

                <td class="check">
                    <input type="checkbox" name="ids" value="${m.id}" />
                </td>
                <td>
                    <shiro:hasPermission name="/admin/sys/user/detials">
                    <a href="${ctx}/admin/sys/user/detials?id=${m.id}">
                    </shiro:hasPermission>
                        ${m.id}
                    <shiro:hasPermission name="/admin/sys/user/detials">
                    </a>
                    </shiro:hasPermission>
                    
                </td>
                <td>${m.username}</td>

                <td><spring:eval expression="m.createDate"/></td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <es:page page="${page}"/>
</div>
<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/admin/import-sys-js.jspf"%>
<script type="text/javascript">
    $(function() {
        $.sys.user.initUserListButton();
        $(".btn-auth").off("click").on("click", function() {
    	    var table = $("#table");
    		var checkbox = $.table.getFirstSelectedCheckbox(table);
            if(!checkbox.length)  return;
            var id = checkbox.val();
            
            window.location.href =table.attr("data-prefix-url")+  "/auth?id="+id+"&BackURL=" + $.table.encodeTableURL(table);
    	});
       
    });
</script>

