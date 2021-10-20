import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseId linkedPurchaseId;

    public LinkedPurchaseId getLinkedPurchaseId() {
        return linkedPurchaseId;
    }


    public void setLinkedPurchaseId(int studentId, int courseId) {
        linkedPurchaseId = new LinkedPurchaseId(studentId, courseId);
    }

    @Embeddable
    public static class LinkedPurchaseId implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public LinkedPurchaseId() {
        }

        public LinkedPurchaseId(int studentId, int courseId) {
            this.studentId=studentId;
            this.courseId=courseId;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LinkedPurchaseId)) return false;
            LinkedPurchaseId linkedPurchase = (LinkedPurchaseId) o;
            return studentId == linkedPurchase.studentId &&
                    courseId == linkedPurchase.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}