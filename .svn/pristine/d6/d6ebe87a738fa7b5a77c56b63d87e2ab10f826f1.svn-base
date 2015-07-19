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
        
        <form:hidden path="salt"/>
        <div id="baseinfo">
            <h4 class="hr">用户基本信息</h4>
            <div class="control-group span4">
                <form:label path="username" cssClass="control-label">用户名</form:label>
                <div class="controls">
            
               
                
                      <form:input path="username"  
                                
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
                  
                </div>
            </div>
 
        

            <div class="clearfix"></div>
                    <h4 class="hr">角色信息</h4>
        <div class="control-group">
            <label class="control-label">角色列表</label>
            <div class="controls">
                <div class="auth">
                    <div class="left">
                        <div class="title muted">未选择的角色列表</div>
                        <div class="list">
                            <ul>
                               <c:forEach items="${roleList}" var="r">
                                   
                                        <li class="ui-state-default" data-value="${r.id}" title="${r.description}">
                                            ${r.roleName}[${r.roleCode}]
                                        </li>
                                  
                                </c:forEach> 
                            </ul>
                        </div>
                    </div>
                    <div class="btns">
                        <a class="btn btn-link btn-move-all-right" data-toggle="tooltip" data-placement="bottom" title="全部右移">
                            <i class="icon-double-angle-right"></i>
                        </a>
                        <a class="btn btn-link btn-move-all-left" data-toggle="tooltip" data-placement="bottom" title="全部左移">
                            <i class="icon-double-angle-left"></i>
                        </a>
                    </div>
                    <div class="right" id="roleIds_msg" data-prompt-position="topLeft">
                        <div class="title muted">已选择的角色列表</div>
                        <div class="list">
                            <ul data-input-id="roleIds">
                                <c:forEach items="${m.roles}" var="r">
                                    
                                        <li class="ui-state-default" data-value="${r.id}" title="${r.description}">
                                            ${r.roleName}[${r.roleCode}]
                                        </li>
                               
                                </c:forEach> 
                            </ul>
                        </div>
                        <input type="hidden" name="roleIds" id="roleIds"   class="validate[required]"/>
                    </div>

                </div>
            </div>
        </div>
        </div>


        
            <c:set var="icon" value="icon-file-alt"/>
       

        <div class="control-group left-group">
            <div>
                <button type="submit" class="btn btn-primary">
                    <i class="${icon}"></i>
                                                              授权
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
<%@include file="/WEB-INF/jsp/common/admin/import-sys-js.jspf"%>
<script type="text/javascript">
    $(function () {

       
                //删除时不验证 并把表单readonly
                $.app.readonlyForm($("#editForm"), false);
                
                $("#editForm").validationEngine({prettySelect:true, useSuffix : "_msg"});
             

                $.sys.auth.initSelectRoleForm();
    

    });

</script>