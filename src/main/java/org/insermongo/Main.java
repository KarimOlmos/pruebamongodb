package org.insermongo;

import com.mongodb.ConnectionString;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Main {

    public static void main(String[] args) {
        // Conexión a MongoDB cambiar segun lugar
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/?authSource=admin");
        MongoClient mongoClient = MongoClients.create(connectionString);

        // Conexión a la base de datos
        MongoDatabase db = mongoClient.getDatabase("misdatos");

        // Obtenemos una colección para trabajar con ella
        MongoCollection<Document> collection = db.getCollection("datos");

        // Crear un documento
        Document document = new Document("name", "Marcos")
                .append("email", "correomarcos")
                .append("location", new Document("city", "Valencia").append("zip", "46020"));

        // Insertar el documento en la colección
        collection.insertOne(document);

        // Buscar todos los documentos de la colección
        MongoCursor<Document> resultDocument = collection.find().iterator();

        // Iterar sobre los resultados y mostrar cada documento
        while (resultDocument.hasNext()) {
            System.out.println(resultDocument.next().toString());
        }

        // Cerrar el cursor (importante para liberar recursos)
        resultDocument.close();
    }
}
