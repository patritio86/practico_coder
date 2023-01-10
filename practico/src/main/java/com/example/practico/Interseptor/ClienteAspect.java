package com.example.practico.Interseptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ClienteAspect {
    @Pointcut("execution (* com.example.practico.Controladores.ClienteController.*(..)) ")
    public void puntoDeCorteClienteController() {

    }

    @Around(value = "puntoDeCorteClienteController()")
    public Object calcularTiempoDeEjecucion(ProceedingJoinPoint joinPoint) throws Throwable {
        long comienza = System.currentTimeMillis();
        Object proceder = joinPoint.proceed();
        long finaliza = System.currentTimeMillis();
        long tiempoDeEjecucion = finaliza - comienza;
        System.out.println(joinPoint.getSignature() + " Se ejecuto en :" + tiempoDeEjecucion + "ms");
        return proceder;
    }
}
