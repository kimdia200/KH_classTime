<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="sock.js" name="title" 	/>
</jsp:include>

	<div class="input-group mb-3">
    <input type="text" id="message" class="form-control" placeholder="Message">
    <div class="input-group-append" style="padding: 0px;">
        <button id="sendBtn" class="btn btn-outline-secondary" type="button">Send</button>
    </div>
    </div>
    <div>
        <ul class="list-group list-group-flush" id="data"></ul>
    </div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.js" integrity="sha512-3/5zbNJKTwZiPFIUPL9Q6woFGvOluvYq2/rJ+C4sZUTXKhVoY3e6mSTf5RJG01lYX3atqeslmWTsxCXb147x2w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
	/**
	* HTML5 API를 지원하지 않는 브라우저에서도 양방향 통신을 사용하기위해 sock.js를 사용한다.
	* 
	* - http로 최초연결시도후, websock이 사용한 경우 ws protocol로 upgrade.
	* - 구버전의 브라우져의 경우, xhr-stream/xhr-polling 중 적합한 방식으로 양방향 통신 사용.
	* - polling방식은 굉장히 좋지 않지만 지원하지 않는 브라우저에서 사용하기 위한 최선의 선택
	*/
	
		const ws = new SockJS("http://localhost:9090/spring/websooocket");
		const $data = $("#data");
		ws.onopen = e =>{
			console.log("onopen : ",e);
		};
		ws.onmessage = e =>{
			console.log("onmessage : ",e);	
			const {data} = e;
			$data.append("<li class='list-group-item'>" + data + "</li>");
		};
		ws.onerror = e =>{
			console.log("onerror : ",e);	
		};
		ws.onclose = e =>{
			console.log("onclose : ",e);	
		};

		const sendMessage = () => {
			const $message = $("#message");
			if($message.val()){
				ws.send($message.val());
				$message.val("").focus();
			}
		};

		$("#sendBtn").click(sendMessage);
		$("#message").keyup(e => e.keyCode == 13 && sendMessage());
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
