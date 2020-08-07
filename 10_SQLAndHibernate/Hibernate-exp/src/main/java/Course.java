import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
   // @Column(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "students_count")
    private int studentsCount;

    private int price;
    @Column(name = "price_per_hour")
    private float pricePerHour;

}
