<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<manage:page title="优惠券批量发送管理">
    <jsp:attribute name="css">
        <style type="text/css">
            .apply-to-product .operate-column{
                min-width: 20em;
            }
            .apply-to-product .ui.button {
                padding: 0.4em 0.3em;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">

    </jsp:attribute>
    <jsp:body>
        <div class="ui container">
            <div class="ui raised segment">
                <a class="ui teal ribbon label">导入商户手机号</a><br><br>

                <form class="ui form" method="post" enctype="multipart/form-data"
                      action="manage/voucher/upload.do">
                    <div class="ui raised upload">
                        <c:choose>
                            <c:when test="${param.status eq 'success'}">
                                <div class="ui visible success message">
                                    <div class="content">上传成功</div>
                                </div>
                            </c:when>
                            <c:when test="${param.status eq 'failed'}">
                                <div class="ui visible error message">
                                    <div class="content">上传失败</div>
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="field">
                            <label>选择上传文件</label>
                            <input type="file" class="file-uploader" name="data" accept=".xlsx"
                                   id="FileUploader"/>
                        </div>
                        <div class="field">
                            <label>选择优惠券类型</label>
                            <select class="ui search dropdown" name="voucherTypeId">
                                <c:forEach var="voucherType" items="${voucherTypes}">
                                    <option value="${voucherType.id }">${voucherType.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="field">
                            <label>每人发送数量</label>
                            <input type="text" name="dispatcherCnt"/>
                        </div>


                        <div class="file-uploader-wrap">
                            <button type="submit" class="ui primary button upload-btn">
                                <i class="icon upload"></i>
                                <span>提交</span>
                            </button>
                            <input type="reset" class="button ui" value="重置"/>　　　
                            <div class="field pull-right">
                                <br>
                                <span>下载模板:&nbsp;</span><span><a href="mobile.xlsx"><i class="file excel outline icon"></i>鼠标右键-》目标另存为</a></span>
                            </div>
                            　　
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </jsp:body>
</manage:page>