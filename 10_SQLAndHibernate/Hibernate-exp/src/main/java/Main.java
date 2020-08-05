import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    public static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    public static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    public static void main(String[] args) {
        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 1);
        System.out.println(course.getName() +" кол-во студентов: " + course.getStudentsCount());
        sessionFactory.close();
    }
}
