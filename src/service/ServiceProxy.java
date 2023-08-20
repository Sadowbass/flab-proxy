package service;

public class ServiceProxy implements Service {

    private final Service service;

    public ServiceProxy(Service service) {
        this.service = service;
    }

    @Override
    public void add(String key, String value) {
        System.out.println("===============");
        System.out.println("tx start");

        try {
            service.add(key, value);
            System.out.println("tx commit");
        } catch (RuntimeException e) {
            System.out.println("tx rollback");
        }

        System.out.println("tx end");
        System.out.println("===============");
        System.out.println();
    }

    @Override
    public String find(String key) {
        return service.find(key);
    }
}
