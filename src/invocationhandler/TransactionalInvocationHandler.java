package invocationhandler;

import customannotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionalInvocationHandler implements InvocationHandler {

    private final Object target;

    public TransactionalInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method implementMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (isTransactional(implementMethod.getDeclaredAnnotations())) {
            return transaction(method, args);
        } else {
            return method.invoke(target, args);
        }
    }

    private boolean isTransactional(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Transactional.class) {
                return true;
            }
        }

        return false;
    }

    private Object transaction(Method method, Object[] args) {
        System.out.println("===============");
        System.out.println("tx start");

        Object result = null;
        try {
            result = method.invoke(target, args);
            System.out.println("tx commit");
        } catch (RuntimeException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("tx rollback");
        }

        System.out.println("tx end");
        System.out.println("===============");
        System.out.println();

        return result;
    }
}
