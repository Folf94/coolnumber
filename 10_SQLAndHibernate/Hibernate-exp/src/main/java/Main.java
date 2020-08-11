import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Main {
    public static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    public static Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    public static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    public static void main(String[] args) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<PurchaseList> purchaseList = session.createQuery("from PurchaseList")
                .getResultList();
        for (PurchaseList purchaseList1 : purchaseList) {

            DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class)
                    .add(Restrictions.eq("name", purchaseList1.getStudentName()));
            Student student = (Student) studentsCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();

            DetachedCriteria coursesCriteria = DetachedCriteria.forClass(Course.class)
                    .add(Restrictions.eq("name", purchaseList1.getCourseName()));
            Course course = (Course) coursesCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(
                    new LinkedPurchaseList.Key(student.getId(), course.getId()), student, course,
                    course.getPrice(), purchaseList1.getSubscriptionDate());
            session.save(linkedPurchaseList);

    }
        transaction.commit();
        sessionFactory.close();
        registry.close();
}
}
