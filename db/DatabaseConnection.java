package db;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseConnection {

    MongoCollection<Document> savesCollection;
    MongoCollection<Document> accountsCollection;


    private static DatabaseConnection instance = null;


    public static DatabaseConnection getInstance()
    {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    private DatabaseConnection(){
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        MongoCredential credential = MongoCredential.createCredential("sampleUser", "myDb",
//                "password".toCharArray());

        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:DFd1gCk0sPKUCHlb@cluster0.leds16l.mongodb.net/?retryWrites=true&w=majority");
        try{
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("EscapeFromKoc");
            savesCollection = database.getCollection("savesCollection");
            accountsCollection = database.getCollection("accountsCollection");


//            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
//            MongoCollection<Document> collection = database.getCollection("movies");
//            Document doc = collection.find(eq("title", "Back to the Future")).first();
        }catch(Exception e){
            e.printStackTrace();
        }




    }

    public List<String> getSaveIDs(String userID) {
        List<String> IDs = new ArrayList<String>();

        FindIterable<Document> iterDoc = savesCollection.find();
        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            Document doc =  ((Document) it.next());
            if(doc.get("userID").equals(userID)){
                String id = doc.getObjectId("_id").toString();
                IDs.add(id);
            }

        }
        return IDs;
    }

    public List<String> getSaveNames(String userID) {
        List<String> names = new ArrayList<String>();

        FindIterable<Document> iterDoc = savesCollection.find();
        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            Document doc =  ((Document) it.next());
            if(doc.get("userID").equals(userID)){
                String id = doc.getString("saveName");
                names.add(id);
            }

        }
        return names;
    }

    public void insert(Document doc) {
        savesCollection.insertOne(doc);
    }

    public boolean insertUser(Document doc) {
        FindIterable<Document> iterable = accountsCollection.find(eq("userName", doc.get("userName")));
        if(iterable.first() == null){
            accountsCollection.insertOne(doc);
            return true;
        }else{
            return false;
        }
    }

    public Document getSave(String id) {
        Bson filter = eq("_id", new ObjectId(id));
        FindIterable<Document> documents = savesCollection.find(filter);

        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            return (Document) cursor.next();
        }

        return null;
    }

    public HashMap<String, String> getUserInfo(String userName){
        HashMap<String,String> userInfo = new HashMap<String,String>();

        Bson filter = eq("userName", userName);
        FindIterable<Document> documents = accountsCollection.find(filter);

        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document userDoc = (Document) cursor.next();
            userInfo.put("username", userDoc.get("userName").toString());
            userInfo.put("encrypted password", userDoc.get("password").toString());
            userInfo.put("salt", userDoc.get("salt").toString());

            return userInfo;
        }

        return null;
    }
}