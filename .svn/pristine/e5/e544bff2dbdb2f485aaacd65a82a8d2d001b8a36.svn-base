<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>
<%@include file="/WEB-INF/jsp/common/import-zTree-css.jspf"%>


<ul class="nav nav-tabs">
    <li ${empty param['search.show_eq'] ? 'class="active"' : ''}>
        <a href="#">
            <i class="icon-table"></i>
            所有
            
        </a>
    </li>
  
</ul>

<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/import-zTree-js.jspf"%>
<script type="text/javascript">
   // var async = ${not empty param.async and param.async eq true};
    $(function() {
        var zNodes =[
            <c:forEach items="${trees}" var="m">
            { id:${m.id}, pId:${m.pId}, name:"${m.name}", iconSkin:"${m.iconSkin}", open: true, root : ${m.root},isParent:${m.isParent}},
            </c:forEach>
        ];
        var permission = {
        		<shiro:hasPermission name="/admin/sys/menu/create">
        		create: true,
        		</shiro:hasPermission>
        		<shiro:hasPermission name="/admin/sys/menu/update">
        		update: true,
        		</shiro:hasPermission>
        		<shiro:hasPermission name="/admin/sys/menu/delete">
        		remove : true,
        		</shiro:hasPermission>
        		<shiro:hasPermission name="/admin/sys/menu/updateName">
        		rename : true,
        		</shiro:hasPermission>
        		<shiro:hasPermission name="/admin/sys/menu/move">
        		move : true,
        		</shiro:hasPermission>
        		nouse : false
        };
       var  urlPre = "${ctx}/admin/sys/menu";
        $.zTree.initMovableTree({
            zNodes : zNodes,
            urlPrefix : urlPre,
            async : true,
            onlyDisplayShow:true,
            
            loadUrl:urlPre+"/common/treeAjax",
            renameUrl:urlPre+"/updateName?menuName={newName}&id={id}",
            removeUrl:urlPre+"/delete?ids={id}",
            detialsUrl:urlPre+"/detials?id={id}",
            updateUrl:urlPre+"/update?id={id}",
            addUrl:urlPre+"/create?pid={id}",
            moveUrl:urlPre+"/move?id={sourceId}&pid={targetId}&moveType={moveType}",
            obj:parent.frames['listFrame'],
            
            permission:permission,
            autocomplete : {
                enable : false
            }
//                 ,
//             setting : {
//                 callback : {
//                     onClick: function(event, treeId, treeNode, clickFlag) {
//                     	alert(config.readUrl);
//                       // parent.frames['listFrame'].location.href='${ctx}/admin/sys/resource/' + treeNode.id + "/update?async=" + async ;
//                     }
//                 }
//             }
        });

    });
</script>