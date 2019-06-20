package catalogue;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

public class CarCatalogueInfo {

    //DF -> document field
    static final String DF_PRODUCTION_YEAR = "production_year";
    static final String DF_MODEL = "model";
    static final String DF_AUTOMAKER = "automaker";
    static final String DF_SIZE_CLASS = "size_class";
    static final String DF_CAR_BODY = "car_body";

    public static final String [] DOCUMENT_FIELDS = {
            DF_PRODUCTION_YEAR,
            DF_MODEL,
            DF_AUTOMAKER,
            DF_SIZE_CLASS,
            DF_CAR_BODY
    };
    public static final int DOCUMENT_FIELDS_COUNT = DOCUMENT_FIELDS.length;

    private static final String DATABASE_PASSWORD = "<password>";
    private static final String DATABASE_NAME = "carcatalogue";
    private static final String COLLECTION_NAME = "cars";

    private MongoCollection carsCollection;

    public CarCatalogueInfo() {

        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://inittree:" + DATABASE_PASSWORD +
                    "@cluster0-ssynm.mongodb.net/" +
                    "test?retryWrites=true&w=majority"); //@TODO: transfer parameters to static level

            MongoClient mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

            this.carsCollection = database.getCollection(COLLECTION_NAME);
        }
        catch (MongoException e) {
            System.err.println("catalogue.CarCatalogueInfo:init ->" + e);
        }

    }

    boolean insertCar(Document car) {
        try {
            if (this.carsCollection.countDocuments(car) == 0) {
                this.carsCollection.insertOne(car);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (MongoException e) {
            System.err.println("catalogue.CarCatalogueInfo:insertCar ->" + e);
            return false;
        }
    }

    FindIterable<Document> findCar(Document carSearchQuery) {
        return this.carsCollection.find(carSearchQuery);
    }

    FindIterable<Document> getAllCars() {
        return this.findCar(new Document());
    }

    long getAllCarsCount() {
        return this.carsCollection.countDocuments(new Document());
    }

    boolean deleteCar(Document deletingCar) {
        try {
            this.carsCollection.deleteOne(deletingCar);
            return true;
        }
        catch (MongoException e) {
            System.err.println("catalogue.CarCatalogueInfo:deleteCar ->" + e);
            return false;
        }
    }

}
