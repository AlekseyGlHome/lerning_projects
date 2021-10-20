import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MainApp {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        String hqlSelectId = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hqlSelectId).getResultList();
        ArrayList<Integer> idList = new ArrayList<>();

        for (PurchaseList p : purchaseList) {
            Query criteria = session.createQuery("FROM " + Student.class.getSimpleName() + " WHERE name = :nameStudent ");
            List<Student> student = criteria.setParameter("nameStudent", p.getPurchaseKey().getStudentName()).list();
            idList.add(student.get(0).getId());

            criteria = session.createQuery("FROM " + Course.class.getSimpleName() + " WHERE name = :nameCourse ");
            List<Course> course = criteria.setParameter("nameCourse", p.getPurchaseKey().getCourseName()).list();
            idList.add(course.get(0).getId());

            if (idList.size() == 2) {
                Transaction transaction = session.beginTransaction();
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                linkedPurchase.setLinkedPurchaseId(idList.get(0), idList.get(1));
                session.saveOrUpdate(linkedPurchase);
                session.flush();
                transaction.commit();
                idList.clear();
            }
        }

        sessionFactory.close();
    }


    private static void homeWork1() {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "test";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String select = "select courses.name, count(*)/max(MONTH(subscription_date)) as avg \n" +
                    "from subscriptions \n" +
                    "join courses on courses.id = subscriptions.course_id\n" +
                    "where subscriptions.subscription_date>='01.01.2018' and subscriptions.subscription_date<='31.12.2018' \n" +
                    "group by subscriptions.course_id\n" +
                    "order by courses.name\n";
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " = " + resultSet.getString("avg"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
