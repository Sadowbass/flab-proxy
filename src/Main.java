import invocationhandler.TransactionalInvocationHandler;
import repository.Repository;
import repository.StupRepository;
import service.Service;
import service.ServiceProxy;
import service.SomeService;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Repository<String> repository = new StupRepository();

        Service proxy = DefaultProxy(repository);
        proxy.add("a", "a");
        System.out.println(proxy.find("a"));
        proxy.add("b", "b");
        System.out.println(proxy.find("b"));
        proxy.add("a", "b");

        proxy = DynamicProxy(repository);
        proxy.add("a", "a");
    }

    private static Service DefaultProxy(Repository<String> repository) {
        return new ServiceProxy(new SomeService(repository));
    }

    private static Service DynamicProxy(Repository<String> repository) {
        Service someService = new SomeService(repository);

        return (Service) Proxy.newProxyInstance(
                someService.getClass().getClassLoader(),
                new Class[]{Service.class},
                new TransactionalInvocationHandler(someService)
        );
    }
}
