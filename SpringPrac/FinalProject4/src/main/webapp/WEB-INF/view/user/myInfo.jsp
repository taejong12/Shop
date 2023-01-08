<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/view/include/link.jsp" %>
<link rel="stylesheet" href="/css/layout/nav.css" >
<link rel="stylesheet" href="/css/user/myInfo.css" >
<%@ include file="/WEB-INF/view/include/header.jsp" %>
	<div class="wrap">
		<section class="title">
			<h1>내 정보 수정</h1>
		</section>
	    <main>
		    <div class="info_box">
		    	
				<h2>닉네임</h2>
				<div class="user_nickname">${SPRING_SECURITY_CONTEXT.authentication.principal.user.nickname }</div>		    	
		    	<div class="user_username">${SPRING_SECURITY_CONTEXT.authentication.principal.user.username }</div>	
	    		<h2>비밀번호</h2>
		    	<div>
		    		<div class="input_box">
			    		<div>
			    			<span>현재 비밀번호</span>
			    			<input class="present_password" type="password" placeholder="현재 비밀번호" >
			    		</div>
			    		
			    		<div>
			    			<span>신규 비밀번호</span>
			    			<input class="new_password" type="password" placeholder="신규 비밀번호" maxlength="20" >
			    		</div>
			    		
			    		<div>
			    			<span>신규 비밀번호 확인</span>
			    			<input class="new_password_check" type="password" placeholder="신규 비밀번호 확인" maxlength="20" >
			    		</div>
		    		</div>
		    		
		    		<div class="btn_box">
			    		<button type="button" class="pwd_modify">변경</button>
		    		</div>
		    	</div>
		    	
		    
            	<h2>닉네임 변경</h2>
				
				<div>
		             <div class="input_box">
		            	<input type="text" class="nickname" name="nickname" placeholder="변경하실 닉네임을 입력해 주세요">
					</div>
					
					<div class="btn_box">
						<button type="button" class="nickname_modify">수정</button>
					</div>
				</div>
				
				
				<h2>휴대폰 번호 변경</h2>
				<div>
					<div class="input_box">
		            	<div>
		            		<input type="number" class="phone" name="phone" required placeholder="전화번호를 입력해 주세요" onkeypress="return lengthCheck(this, 11);" >
		            	</div>		            			         
		            	
		           	</div>	           			           
		           	<div class="btn_box">					
						<button type="button" class="phone_modify">변경</button>
		           	</div>	           	
				</div>
				
				<h2>이메일 변경</h2>
				<div>
					<div class="input_box">
		            	<div>
		            		<input type="text" class="email" name="email" required placeholder="변경할 이메일을 입력해 주세요" >
		            	</div>		            			         
		            	
		           	</div>	           			           
		           	<div class="btn_box">					
						<button type="button" class="email_modify">변경</button>
		           	</div>	           	
				</div>
			</div>
	    </main>
    </div>
    
    
   	<%@ include file="/WEB-INF/view/include/nav.jsp" %>
	<%@ include file="/WEB-INF/view/include/footer.jsp" %>
	
	<script type="text/javascript" src="/js/user/myInfo.js" ></script>
 	<script type="text/javascript" src="/js/util/util.js" ></script>
    
</body>
</html>
