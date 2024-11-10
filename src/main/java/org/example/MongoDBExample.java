package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class MongoDBExample {
    public static void main(String[] args) {
        // Создание подключения к MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://javacode:bestEducationEver@80.66.64.141:27017/estim?authSource=admin\n");

        // Получение доступа к базе данных
        MongoDatabase database = mongoClient.getDatabase("estim");

        MongoCollection<Document> collection = database.getCollection("estim");

        System.out.println("Connected to MongoDB!");

        Document document = collection.find().first();
        System.out.println(document.toJson());

//        for (Document doc : collection.find()) {
//            System.out.println(doc.toJson());
//        }

        // Закрытие подключения
        mongoClient.close();
    }
}
