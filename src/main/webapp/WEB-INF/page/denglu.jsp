<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>登陆页面</title>
    <style type="text/css">
       .title{
             width:1000px;
              height:80px;
              margin-left:150px;
              border:1px solid #DBE0E3;
              margin:0px auto;
              border-top:none;
              border-left:none;
              border-right:none;
              background-color:#FFFFFF;
          }
          img{
              width:200px;
              height:50px;
              margin-top:15px;
               float:left;
           }
           .title span{
            font-size:25px;
            margin-top:37px;
            float:left;
            margin-left:12px;
            color:#666666;
         }
         .title ul{
            margin-left:830px;
            margin-top:-10px;
            float:left;
         }
        .title ul li{
           list-style:none;
           float:left;
           font-size:12px;
           padding:0px 10px;
        }
        .content{
          width:1000px;
          height:400px;
          border:0px solid red;
          margin-left:170px;
          margin-top:40px;
        }
        .main_left{
          border:0px solid blue;
          width:450px;
          height:300px;
          margin-top:70px;
          float:left;
        }
        .main_left img{
          width:450px;
          height:280px;
        }
        .main_right{
          border:1px solid #DEDEDE;
          width:492px;
          height:350px;
          float:left;
          margin-left:40px;
          margin-top:20px;
        }
        .first{
          width:500px;
          height:50px;
          border:0px solid yellow;
        }
        .first ul{
          float:left;
        
          height:45px;
          margin-top:0px;
          padding-left:0px;
        }
         .first ul li{
           float:left;
           list-style:none;
           padding:15px 44px;
            border:1px solid #DEDEDE;
           background-color:#F3F3F7;
         }
         .second{
           width:400px;
           height:250px;
           border:0px solid red;
           float:left;
           margin-left:50px;
           margin-top:10px;
           
         }
         table{
            margin-left:40px;
            margin-top:0px;
         }
         td{
             border:1px;
             padding:10px 5px;
         }
    </style>
    
  </head>
  
  <body>
       <div class="title" >
          <img alt="1" src="../img/4.png">
            <span>用户登录</span>
               <ul>
                  <li>返回首页</li>
                  <li>帮助</li>
               </ul>
         </div>
         <div  class="content">
            
                  
            <div class="main_left" >
                 <img alt="2" src="../img/6.png">
            </div>
            <div class="main_right">
                <div class="first">
                    <ul>
                        <li>QQ快速登陆</li>
                        <li>微信登陆</li>
                        <li>58账号登陆</li>
                    </ul>
                
                
                </div>
                <div class="second">
                 
                  <form action="/user/loginSubmit.action" method="post">

                     <table border="0px">
                           
                           <tr>
                              <td>账户名</td>
                              <td>
                                  <input type="text" size=30 style="height:40px; " name="username">
                              </td>
                           </tr>
                            <tr>
                              <td>密     码</td>
                              <td>
                                  <input type="password" size=30 style="height:40px;" name="password">
                              </td>
                           </tr>
                            <tr>
                              <td colspan="2" style="padding:0px 20px;">
                                  <input type="checkbox" style="margin-left:44px; ">
                                  <span style="font-size:13px;">下次自动登录</span>
                                  <span style="font-size:13px; margin-left:90px;">忘记密码</span>
                              </td>
                              
                           </tr>
                           <tr>
                           
                             <td colspan="2" align="center">
                                 <input type="submit" value="登录"
                                        style="color:white;background-color:#F78B13;
                                         padding:10px 104px;margin-left:50px;font-size:17px;
                                         font-weight:bold">
                             </td>
                           </tr>
                     </table>
                  </form>
                
                </div>
            
            </div>
        </div>
       
   </body>
</html>      
       
       
       
      
       
       
       
      

