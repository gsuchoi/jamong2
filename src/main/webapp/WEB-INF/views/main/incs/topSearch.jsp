<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<!--[if IE 9]> <html class="no-js ie9 fixed-layout" lang="en"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-js " lang="en"> <!--<![endif]-->
<head>
<%@include file="head.jsp" %>
<script type="text/javascript">
	$(function(){
		var idx=0;
		var func=function(){
			$.ajax({
				url:"<c:url value='/main/showCategoryM.do'/>",
				type:"get",
				data:"no="+$(".categoryNo:eq(0)").val(),
				dataType:"json",
				success:function(res){
					if(res.length>0){
						if(idx==5) idx=0;
						$("#search").attr("placeholder",res[idx].categoryName);
						idx++;
					}
				},
				error:function(xhr, status, error){
					alert(status+":"+error);
				}
			});//ajax
		};
		
		setTimeout(func,0);
		var interval=setInterval(func,1800);
		
		$("#search").click(function(){
			clearInterval(interval);
			$("#search").attr("placeholder","");
		})
		
	})
</script>
<style type="text/css">
	.categoryNo{
		padding-left: 20px;
	}
	
	.calculateform{
		margin-top: -7px;
	}
	
	.center-pricing{
		margin-top: 45px;
	}
	
	section.section.transheader.bgcolor {
    	margin-bottom: -62px;
	}
	
</style>
<link href="<c:url value="/assets/js/lib/jeoncss/logoChange.css"/>" rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<header class="header site-header header-transparent affix-top">
			<div class="container">
				<nav class="navbar navbar-default yamm">
				    <div class="container-fluid">
				        <div class="navbar-header">
				            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				                <span class="sr-only">Toggle navigation</span>
				                <span class="icon-bar"></span>
				                <span class="icon-bar"></span>
				                <span class="icon-bar"></span>
				            </button>
				            <c:if test="${empty userName }">
								<a class="navbar-brand" href="<c:url value='/index.html'/>"><img src="<c:url value='/resources/images/logo.png'/>" alt="Linda"></a>
							</c:if>
							<c:if test="${!empty userName }">
								<figure class="snip1283" style="width:180px;height: 50px;background-color: #ffffff00;margin:auto;display: inline;text-align: center;">
								  <img src="<c:url value='/resources/images/logo.png'/>" alt="Linda" style="background-color: rgba( 255, 255, 255, 0 );width: auto;">
								  <figcaption>
								    <h5 style="cursor: context-menu;">${userName } 님</h5>
								    <a href="<c:url value="/mypage/mypage.do?userNo=${sessionScope.userNo }"/>" class="read-more" style="width: inherit;">MyPage</a>
								    <a href="<c:url value="/main/userlogin/logout.do"/>" class="read-more" style="width: inherit;">Logout</a>
								  </figcaption>
								</figure>
							</c:if>
							
				        </div>
					        <div id="navbar" class="navbar-collapse collapse">
					            <ul class="nav navbar-nav">
					            <c:import url="/main/showCategoryL.do"/>
	                            </ul>
	                            
					        </div><!--/.nav-collapse -->
				    </div><!--/.container-fluid -->
				</nav><!-- end nav -->
			</div><!-- end container -->
		</header><!-- end header -->
		<c:import url="/todayList.do"/>
	<section class="section transheader bgcolor">
		<form name="searchKeyword" class="calculateform" method="post" action="">
		        <div class="item-top form-inline">
		            <div class="form-group">
		                <div class="input-group2">
		                    <span class="input-addon">
								<i class="fa fa-search" style="text-align: center"></i>
							</span>
		                    <input type="text" class="form-control" id="search" name="searchKeyword">
		                    <input type="hidden" name="idx" id="idx">
		                </div>
		            </div>
		            <input type="submit" name="send" value="Search" class="btn btn-default">
		        </div>
		</form>
		<div class="center-pricing">
			<ul class="nav nav-tabs"  <c:if test="${!empty userId }">style="display: flex"</c:if>>
			<c:if test="${empty userName }">
			    <li><a href="<c:url value="/main/userlogin/login.do"/>" style="cursor: pointer;">로그인</a></li>
			    <li><a href="<c:url value="/main/userlogin/userRegist.do"/>" style="cursor: pointer;">회원가입</a></li>
			</c:if>
			</ul>
		</div>
	</section>