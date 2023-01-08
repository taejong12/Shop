<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/link.jsp" %>
<link rel="stylesheet" href="/css/layout/nav.css">
<link rel="stylesheet" href="/css/home.css">
 
<%@ include file="/WEB-INF/view/include/header.jsp" %>
 	<!-- 콘텐츠 -->
    <div class="wrap">
        <main>
            <section class="category_box">
                <div class="box">
                    <ul class="category">
                    
                                <li value="일반증류주">
									<div>
										<div class="img_box">
                                           <img src="/img/jeungryu.png" alt="이미지">
										</div>
                                    </div>
                                    <div class="name" >일반증류주</div>
                                </li>
                    
                                <li value="증류식소주">
									<div>
										<div class="img_box">
                                           <img src="/img/soju.png" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">증류식소주</div>
                                </li>
                    
                                <li  value="청주">
									<div>
										<div class="img_box">
                                           <img src="/img/chungju.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">청주</div>
                                </li>
                    
                                <li value="탁주">
									<div>
										<div class="img_box">
                                           <img src="/img/tacju.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">탁주</div>
                                </li>
                                
                    
                                <li value="생탁주">
									<div>
										<div class="img_box">
                                           <img src="/img/saengtacju.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">생탁주</div>
                                </li>
                                
                                <li value="과실주">
									<div>
										<div class="img_box">
                                           <img src="/img/fruit.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">과실주</div>
                                </li>
                    
                                <li value="리큐르">
									<div>
										<div class="img_box">
                                           <img src="/img/liquor.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">리큐르</div>
                                </li>
                                
                    
                                <li value="약주">
									<div>
										<div class="img_box">
                                           <img src="/img/yackju.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">약주</div>
                                </li>
                    
                                <li value="브랜디">
									<div>
										<div class="img_box">
                                           <img src="/img/blandy.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">브랜디</div>
                                </li>    
                                <li value="기타주류">
									<div>
										<div class="img_box">
                                           <img src="/img/etcju.jpg" alt="이미지">
										</div>
                                    </div>
                                    <div class="name">기타주류</div>
                                </li>                                              
                            </ul>
                	</div>
            </section>
        </main>
    </div>
    <!-- 콘텐츠 -->
    
 
 
    <!-- 하단 메뉴 -->
	<%@ include file="/WEB-INF/view/include/nav.jsp" %>
    <!-- 하단 메뉴 -->
    <!-- 푸터 -->
    <%@ include file="/WEB-INF/view/include/footer.jsp" %>
    <!-- 푸터 -->
 
<script>
	$(".category li").click(function(){
		let value = $(this).attr("value");
		const index = $(this).index();
		
		location.href = "/store?al_kind="+value;
	})
</script>
 
 
</body>
 
</html>