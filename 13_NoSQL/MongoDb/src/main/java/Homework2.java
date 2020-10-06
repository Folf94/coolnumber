import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Homework2 {
    private static MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
    private static MongoDatabase database = mongoClient.getDatabase("local");
    private static MongoCollection<Document> collection = database.getCollection("Students");

    public static void getStudents(){
        DB local = mongoClient.getDB("local");
        DBCollection students = local.getCollection("Students");

        List<Student> studentList = loadStudentList();
        loadToDB(studentList);
        System.out.println("Общее количество студентов: " + collection.countDocuments());

        int age = students.find(new BasicDBObject("age", new BasicDBObject("$gt", 40))).count();
        System.out.println("\nКоличество студентов старше 40 лет : " + age + " человек.");

        DBCursor youngStudent = students.find().sort(new BasicDBObject("age", 1)).limit(1);
        System.out.println("\nИмя самого молодого студента: " + Objects.requireNonNull(youngStudent.one()).get("name"));

        DBCursor oldStudent = students.find().sort(new BasicDBObject("age", -1)).limit(1);
        System.out.println("\nСписок куров самого старого студента: " + Objects.requireNonNull(oldStudent.one()).get("courses"));
    }


    private static ArrayList<Student> loadStudentList() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String studentsList = "src/main/resources/mongo.csv";
            List<String> lines = Files.readAllLines(Paths.get(studentsList));

            for (String line : lines) {
                String[] fragments = line.split(",", 3);
                String[] courses = fragments[2].replaceAll("([\"])", "").split(",");
                int age = Integer.parseInt(fragments[1]);
                List<String> courseList = new ArrayList<>(Arrays.asList(courses));
                list.add(new Student(fragments[0], age, courseList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private static void loadToDB(List<Student> studentsList){

        collection.drop();

        for (Student student : studentsList){
            Document newStudent = new Document()
                    .append("name", student.getName())
                    .append("age", student.getAge())
                    .append("courses", student.getCourseList());

            collection.insertOne(newStudent);
        }
    }
    private static class Student {

        private String name;
        private int age;
        private List<String> courseList;

        public Student(String name, int age, List<String> courseList){
            this.name = name;
            this.age = age;
            this.courseList = courseList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<String> courseList) {
            this.courseList = courseList;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", courseList=" + courseList +
                    '}';
        }
    }
}
