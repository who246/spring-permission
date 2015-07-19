<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>

<div class="panel">
     <ul class="nav nav-tabs">
       

        <li>
            <a href="<es:BackURL/>" class="btn btn-link">
                <i class="icon-reply"></i>
                              返回
            </a>
        </li>
    </ul> 

    <form:form id="editForm" method="post" commandName="m" cssClass="form-inline form-horizontal form-small">
        <es:showGlobalError commandName="m"/>
        <form:hidden path="id"/>
        <div id="baseinfo">
            <h4 class="hr">角色基本信息</h4>
            <div class="control-group span4">
                <form:label path="roleName" cssClass="control-label">角色名称</form:label>
                <div class="controls">
                 
               
                
                      <form:input path="roleName" cssClass="validate[required,minSize[5],maxSize[20]]"
                                placeholder="角色名称" 
                                />
                
                 
                </div>
            </div>
            <div class="control-group span4">
                <form:label path="roleCode" cssClass="control-label">角色代码</form:label>
                <div class="controls">
                 
               
                
                      <form:input path="roleCode" cssClass="validate[required,custom[onlyLetterNumber],minSize[5],maxSize[20]]"
                                placeholder="角色代码" 
                                />
                
                 
                </div>
            </div>
             
              <div class="control-group span4">
                <form:label path="description" cssClass="control-label">角色描述</form:label>
                <div class="controls">
                      <form:input path="description" cssClass="validate[maxSize[50]]"
                                placeholder="角色描述" 
                                />
                
                 
                </div>
            </div>

            <div class="control-group span4">
                <form:label path="createDate" cssClass="control-label">创建时间</form:label>
                <div class="controls input-append date">
                    <form:input path="createDate"
                                  data-format="yyyy-MM-dd hh:mm:ss"
                                  data-position="bottom-left"
                                  placeholder="默认当前时间"/>
                    <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
                </div>
            </div>
 

            <div class="clearfix"></div>
            <c:if test="${op eq '查看' || op eq '授权'}">
            <div id="resourcePermissionInfo" class="span10">
            <h4 class="hr">授权信息</h4>
            <div id="selectResourcePermission">
                <div class="muted font-12" style="margin: 10px auto;">
                                             请选择授权的权限
                </div>
                
                <div id="resourceInfo" class="span3">
                     
                    <div class="input-append" title="选择资源">
                        <input type="hidden" id="resourceId" name='menuIdStr' value="${selectTreeIds}">
                        <input type="text" id="resourceName" class="input-medium" value=""
                               readonly="readonly">
                        <a id="selectResourceTree" href="javascript:;">
                            <span class="add-on"><i class="icon-chevron-down"></i></span>
                        </a>
                    </div>
                </div>
               
            </div>
                  
              
        </div>
        <div  class="span10">
 
        </div>
            </c:if>
        </div>


        <c:if test="${op eq '新增'}">
            <c:set var="icon" value="icon-file-alt"/>
        </c:if>
        <c:if test="${op eq '修改'}">
            <c:set var="icon" value="icon-edit"/>
        </c:if>
        <c:if test="${op eq '删除'}">
            <c:set var="icon" value="icon-trash"/>
        </c:if>
        <c:if test="${op eq '授权'}">
        <c:set var="icon" value="icon-file-alt"/>
        </c:if>
        <div class="control-group left-group">
            <div>
                <button type="submit" class="btn btn-primary">
                    <i class="${icon}"></i>
                        ${op}
                </button>
                <a href="<es:BackURL/>" class="btn">
                    <i class="icon-reply"></i>
                    返回
                </a>
            </div>
        </div>

    </form:form>
</div>
<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/import-zTree-css.jspf"%>
<%@include file="/WEB-INF/jsp/common/import-zTree-js.jspf"%>
 
<script type="text/javascript">
    $(function () {

        <c:choose>
            <c:when test="${op eq '查看' }">
                //删除时不验证 并把表单readonly
           $.app.readonlyForm($("#editForm"), true);
                
            </c:when>
            <c:when test="${op eq '授权' }">
            $.app.readonlyForm($("#editForm"), false);
            </c:when>


            <c:otherwise>
           
                $("#editForm").validationEngine();
                <es:showFieldError commandName="m"/>
            </c:otherwise>
        </c:choose>

        <c:if test="${op eq '授权' || op eq '查看' }">
        
        var zNodes =[
                     <c:forEach items="${trees}" var="m">
                     { id:${m.id}, pId:${m.pId}, name:"${m.name}", iconSkin:"${m.iconSkin}", open: false, root : ${m.root},isParent:${m.isParent}},
                     </c:forEach>
                 ];
var selectIds ='${selectTreeIds}';
var resourceTreeId = $.zTree.initSelectTree({
    zNodes : zNodes,
    selectIds:selectIds,
    checkSelect:true,
    nodeType : "default",
    fullName:true,
    async : false,
    asyncLoadAll : true,
    onlyDisplayShow: true,
    lazy : false,
    select : {
        btn : $("#selectResourceTree,#resourceName"),
        id : "resourceId",
        name : "resourceName",
        includeRoot: false
    },
    autocomplete : {
        enable : false
    },
    setting :{
        check : {
            enable:true,
            chkStyle:"checkbox",
            //onlyCheckLeaf : false,
            //onlySelectLeaf : true
        }
    }
});
</c:if>
    });

</script>