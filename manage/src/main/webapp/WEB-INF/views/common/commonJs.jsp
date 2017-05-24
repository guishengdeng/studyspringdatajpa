<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static-resources/jquery-1.12.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static-resources/semantic.js"></script>
<script type="text/javascript" src="http://s.gridy.com/background/superfish/js/superfish.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static-resources/jquery-ui.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static-resources/dist/jstree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static-resources/dist/jstree.min.js"></script>


<script type="text/javascript">
    Date.prototype.Format = function(fmt)
    {
        var o = {
            "M+" : this.getMonth()+1,
            "d+" : this.getDate(),
            "h+" : this.getHours(),
            "m+" : this.getMinutes(),
            "s+" : this.getSeconds(),
            "q+" : Math.floor((this.getMonth()+3)/3),
            "S"  : this.getMilliseconds()
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
</script>
