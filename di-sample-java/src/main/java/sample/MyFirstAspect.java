package sample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.After;
import org.junit.Before;
import org.springframework.stereotype.Component;

import sample.di.business.valueobject.Product;

@Aspect
@Component
public class MyFirstAspect {

	@Before("execution(* findProduct(String))")
	public void before() {
		//メソッド開始時に動作するAdvice
		System.out.println("Hello Before! *** メソッドが呼ばれる前に出てくるよ！");
	}

	@After("execution(* findProduct(String))")
	public void after() {
		//メソッド終了後に動作するAdvice
		System.out.println("Hello After! *** メソッドが呼ばれた後に出てくるよ！");
	}

	@AfterReturning(value="execution(* findProduct(String))", returning="product")
	public void afterReturning(Product product) {
		//メソッド呼出が例外の送出なしに終了した際に動作するAdvice
		System.out.println("Hello AfterReturning! *** メソッドを読んだ後に出てくるよ！");
	}

	@Around("execution(* findProduct(String))")
	public Product around(ProceedingJoinPoint pjp) throws Throwable {
		//メソッド呼出の前後に動作するAdvice
		System.out.println("Hello Around! before *** メソッドを呼ぶ前に出てくるよ！");
		Product p = (Product)pjp.proceed();
		System.out.println("Hello Around! after *** メソッドを読んだ後に出てくるよ！");
		return p;
	}

	@AfterThrowing(value="execution(* findProduct(String))", throwing="ex")
	public void afterThrowing(Throwable ex) {
		//メソッド呼出が例外を創出した際に動作するAdvice
		System.out.println("Hello Throwing! *** 例外になったら出てくるよ");
	}
}
