<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%
    Throwable finalException = pageContext.getException();
    while (finalException != null){
        if(finalException.getCause() == null){
            request.setAttribute("_finalException_", finalException);
            break;
        } else {
            finalException = finalException.getCause();
        }
    }

%>
<depotnextdoor:page title="Sorry...">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="error-container">
                <div class="well">
                    <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												500
											</span>
                        Something Went Wrong
                    </h1>

                    <hr />
                    <h3 class="lighter smaller">
                        But we are working
                        <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                        on it!
                    </h3>

                    <div class="space"></div>

                    <div>
                        <h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

                        <ul class="list-unstyled spaced inline bigger-110 margin-15">
                            <li>
                                <i class="ace-icon fa fa-hand-o-right blue"></i>
                                Read the faq
                            </li>

                            <li>
                                <i class="ace-icon fa fa-hand-o-right blue"></i>
                                Give us more info on how this specific error occurred!
                            </li>
                        </ul>
                    </div>

                    <hr />
                    <div class="space"></div>
                    <div>
                        <div>错误发生页面：${pageContext.errorData.requestURI}</div>
                        <div>错误信息：${pageContext.exception}</div>
                        <div style='table-layout:fixed; word-break: break-all; word-wrap: break-word;'>
                            错误堆栈信息：<br/>
                            ${_finalException_}
                        </div
                    </div>
                    <div class="center">
                        <a href="javascript:history.back()" class="btn btn-grey">
                            <i class="ace-icon fa fa-arrow-left"></i>
                            Go Back
                        </a>

                        <a href="#" class="btn btn-primary">
                            <i class="ace-icon fa fa-tachometer"></i>
                            Dashboard
                        </a>
                    </div>
                </div>
            </div>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</depotnextdoor:page>
