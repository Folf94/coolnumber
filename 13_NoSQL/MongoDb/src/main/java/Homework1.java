import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;

public class Homework1 {

    public  static void getBook(){
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collection = database.getCollection("Books");
        collection.drop();


        Document firstBook = new Document()
                .append("name", "Book One")
                .append("author", "One")
                .append("date", 2001);

        Document secondBook = new Document()
                .append("name", "Book Two")
                .append("author", "One")
                .append("date", 2002);

        Document thirdBook = new Document()
                .append("name", "Book Three")
                .append("author", "Two")
                .append("date", 2003);

        Document fourthBook = new Document()
                .append("name", "Book four")
                .append("author", "Three")
                .append("date", 2004);

        Document fifthBook = new Document()
                .append("name", "Book Five")
                .append("author", "Four")
                .append("date", 2005);

        collection.insertOne(firstBook);
        collection.insertOne(secondBook);
        collection.insertOne(thirdBook);
        collection.insertOne(fourthBook);
        collection.insertOne(fifthBook);

        BsonDocument secondQuery = BsonDocument.parse("{author: {$eq: \"One\"}}");

        collection.find().sort(new BasicDBObject("date", 1)).limit(1).forEach((Consumer<Document>) document -> {
            System.out.println("\nСамая старая книга:\n" + document);
        });

        System.out.println("\nКниги любимого писателя:");
        collection.find(secondQuery).forEach((Consumer<Document>) System.out::println);
    }
}