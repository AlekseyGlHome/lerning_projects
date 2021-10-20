import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> students = database.getCollection("students");
        students.drop();
        CSVReader reader = new CSVReader(new FileReader("main/resources/mongo.csv"), ',', '"', '/', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String[] courses = nextLine[2].split(",");

            Document student = new Document()
                    .append("Name", nextLine[0])
                    .append("Age", Integer.valueOf(nextLine[1]))
                    .append("Courses", Arrays.asList(courses));
            students.insertOne(student);
        }

        System.out.println(" - общее количество студентов в баз "+students.countDocuments());
        BsonDocument query = BsonDocument.parse("{Age: {$gt: 40}}");
        System.out.println(" - количество студентов старше 40 лет " + students.countDocuments(query));
        query = BsonDocument.parse("{Age:1}");
        Document doc =students.find().sort(query).limit(1).iterator().next();
        System.out.println(" - имя самого молодого студента");
        System.out.println("name: "+doc.get("Name")+" age: "+doc.get("Age"));
        query = BsonDocument.parse("{Age:-1}");
        doc =students.find().sort(query).limit(1).iterator().next();
        System.out.println(" - список курсов самого старого студента");
        System.out.println("name: "+doc.get("Name")+" age: "+ doc.get("Age")+" courses: "+ doc.get("Courses"));
    }



}
