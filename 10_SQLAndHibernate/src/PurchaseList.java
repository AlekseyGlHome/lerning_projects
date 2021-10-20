import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    private PurchaseID PurchaseKey;

    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public PurchaseID getPurchaseKey() {
        return PurchaseKey;
    }

    public void setPurchaseKey(PurchaseID purchaseKey) {
        this.PurchaseKey = purchaseKey;
    }


    @Embeddable
    public static class PurchaseID implements Serializable {

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PurchaseID)) return false;
            PurchaseID purchase = (PurchaseID) o;
            return Objects.equals(studentName, purchase.studentName) &&
                    Objects.equals(courseName, purchase.courseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, courseName);
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

    }


}
