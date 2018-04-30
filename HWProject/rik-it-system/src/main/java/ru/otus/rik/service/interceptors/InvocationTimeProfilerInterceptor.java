package ru.otus.rik.service.interceptors;

import ru.otus.rik.service.persistence.PersistenceService;
import ru.otus.rikapi.entities.InvocationStatisticsEntity;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

@InvocationTimeProfile
@Interceptor
public class InvocationTimeProfilerInterceptor implements Serializable {

    @EJB
    private PersistenceService persistenceService;

    public InvocationTimeProfilerInterceptor() {
    }

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = invocationContext.proceed();
        } finally {
            long endTime = System.currentTimeMillis() - startTime;
            Method method = invocationContext.getMethod();
            // TODO: fix SRP violation
            saveStatistics(method, endTime);
        }

        return result;
    }

    private void saveStatistics(Method method, long time) {
        InvocationStatisticsEntity invocationStatistics = new InvocationStatisticsEntity();
        invocationStatistics.setMethodName(method.toString());
        invocationStatistics.setInvocationTime(time);
        persistenceService.saveInvocationStatistics(invocationStatistics);
    }
}
