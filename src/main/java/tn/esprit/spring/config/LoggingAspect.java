package tn.esprit.spring.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {


	private static final Logger logger =  LogManager.getLogger("LoggingAspect");
 
	@Around("execution(* tn.esprit.spring.service.*.*(..))")
	public Object calculTimeProcess(ProceedingJoinPoint jointpoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = jointpoint.proceed();
		long fin = System.currentTimeMillis()-start;
		logger.info("La Method "+jointpoint.getSignature()+" durée d’exécution "+fin+"MS");
		return proceed;
	}
	
}
