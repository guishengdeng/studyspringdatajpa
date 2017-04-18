<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:sidebar icon="fa fa-list">
    <gbck:singleMenu text="录入新的猫" link="demo/cats/new.do" icon="fa fa-plus" />
    <gbck:menuGroup text="按销售展示" icon="fa fa-list">
        <gbck:singleMenu text="正常的猫" link="demo/cats.do" />
        <gbck:singleMenu text="死掉的猫" link="demo/cats.do?status=DISABLE" />
    </gbck:menuGroup>
    <gbck:menuGroup text="按销售展示" icon="fa fa-shopping-basket">
        <gbck:singleMenu text="可销售的猫" link="demo/cats/listByStatus.do?saleStatus=FOR_SALE" />
        <gbck:singleMenu text="已经预订的猫" link="demo/cats/listByStatus.do?saleStatus=LOCK" />
        <gbck:singleMenu text="已售的猫" link="demo/cats/listByStatus.do?saleStatus=SOLD" />
    </gbck:menuGroup>
</gbck:sidebar>