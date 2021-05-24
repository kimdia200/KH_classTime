package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //bean등록
@Aspect // aspect클래스로 등록, 하위에 pointcut, advice를 가지고있음
public class LoggerAspect {
	
	@Pointcut("execution(* com.kh.spring.memo..selectMemoList(..))")
	public void loggerPointcut() {}
	
	@Pointcut("execution(* com.kh.spring.memo.controller..insertMemo(..))")
	public void insertMemoStopWatch() {}
	
	/**
	 * Around Advice
	 *  - JoinPoint 실행전, 실행후에 삽입되어 처리되는 advice(보조업무)
	 * 
	 */
	@Around("loggerPointcut()") //pointcut연결
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		//org.aspectj.lang.Signature
		Signature signature = joinPoint.getSignature();
		//참고! filter의 체인 실행전 실행후와 비슷하다~!
		
		//영역1. before, joinPoint.proceed()실행전 영역
		log.debug("----- {} start -----",signature);
		
		//주업무 joinPoint실행, 예외는 그냥 던져버렸음~!
		Object returnObj = joinPoint.proceed();
		
		//영역2. after, joinPoint.proceed()실행후 영역
		log.debug("----- {} end -----", signature);
		return returnObj;
	}
	
	//https://www.logicbig.com/how-to/code-snippets/jcode-spring-framework-stopwatch.html
	@Around("insertMemoStopWatch()")
	public Object stopWatch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Signature signature = joinPoint.getSignature();
		
		
		StopWatch sw = new StopWatch();
		sw.start();
		Object returnObj = joinPoint.proceed();
		sw.stop();
		
		log.debug("insertMemo ={} mil",sw.getTotalTimeMillis());
		return returnObj;
	}
}
