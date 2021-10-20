import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

public class Main {
    private final static String COMMAND_ADD_STORE = "ДОБАВИТЬ_МАГАЗИН";
    private final static String COMMAND_ADD_PRODUCT = "ДОБАВИТЬ_ТОВАР";
    private final static String ADD_ITEM_TO_STORE = "ВЫСТАВИТЬ_ТОВАР";
    private final static String INFORMATION_ABOUT_PRODUCTS_BY_STORES = "СТАТИСТИКА_ТОВАРОВ";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> stores = database.getCollection("stores");
        MongoCollection<Document> products = database.getCollection("products");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Используйте следующие команды:\nДОБАВИТЬ_МАГАЗИН имя_магазина\n" +
                "ДОБАВИТЬ_ТОВАР наименование_товара цена_товара\nВЫСТАВИТЬ_ТОВАР наименование имя_магазина\nСТАТИСТИКА_ТОВАРОВ");
        while (true) {
            String command = scanner.nextLine();

            if (checkCommand(command,COMMAND_ADD_STORE,2)) {
                addStore(command, stores);
            } else
            if (checkCommand(command,COMMAND_ADD_PRODUCT,3)) {
                addProduct(command, products);
            } else
            if (checkCommand(command,ADD_ITEM_TO_STORE,3)) {
                addingProductToStore(command, products, stores);
            } else
            if (checkCommand(command,INFORMATION_ABOUT_PRODUCTS_BY_STORES,1)) {
                productStatistics(products, stores);
            } else {
                System.out.println("(!!!) Некорректная команда");
            }
        }
    }

    private static boolean checkCommand(String command,String originalCmd,int size){
        return (command.contains(originalCmd) && (command.trim().split(" ").length == size));
    }

    private static void addProduct(String command, MongoCollection<Document> collectionProducts) {
        String[] cmdArr = command.replaceAll(COMMAND_ADD_PRODUCT, "").trim().split(" ");
        Document document = new Document().append("Product", cmdArr[0]).append("Price", Integer.valueOf(cmdArr[1]));
        collectionProducts.insertOne(document);
        System.out.println("Продукт \"" + cmdArr[0] + "\" по цене " + cmdArr[1] + " руб. добавлен");
    }

    private static void addStore(String command, MongoCollection<Document> collectionStore) {
        command = command.replaceAll(COMMAND_ADD_STORE, "").trim();
        Document document = new Document().append("Store", command).append("Products", new ArrayList<String>());
        collectionStore.insertOne(document);
        System.out.println("Магазин \"" + command + "\" добавлен");
    }

    private static void addingProductToStore(String command, MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
        String[] cmdArray = command.replaceAll(ADD_ITEM_TO_STORE, "").trim().split(" ");
        if (findProductInCollection(cmdArray[0], collectionProducts) && findStoreInCollection(cmdArray[1], collectionStore)) {
            Document document = collectionStore.find(eq("Store", cmdArray[1])).first();
            ArrayList<String> productsList = (ArrayList<String>) document.get("Products");
            if ((productsList.size() == 0) || (!productsList.contains(cmdArray[0]))) {
                productsList.add(cmdArray[0]);
            }
            collectionStore.findOneAndUpdate(eq("Store", cmdArray[1]), new Document("$set", new Document("Products", productsList)));
            System.out.println("Продукт \"" + cmdArray[0] + "\" добавлен в магазин \"" + cmdArray[1] + "\"");
        }
    }

    private static void productStatistics(MongoCollection<Document> collectionProducts, MongoCollection<Document> collectionStore) {
        AggregateIterable<Document> priceStatistics = collectionStore.aggregate(Arrays.asList(
                Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.group("$Store", Accumulators.sum("ProductsSum", 1),
                        Accumulators.avg("AvgPrice", "$Products.Price"),
                        Accumulators.max("MaxPrice", "$Products.Price"),
                        Accumulators.min("MinPrice", "$Products.Price"))
        ));
        AggregateIterable<Document> listOfProductsUpTo100 = collectionStore.aggregate(Arrays.asList(
                Aggregates.lookup(collectionProducts.getNamespace().getCollectionName(), "Products", "Product", "Products"),
                Aggregates.unwind("$Products"),
                Aggregates.match(lt("Products.Price", 100)),
                Aggregates.group("$Store", Accumulators.sum("LowProductPrice", 1))
        ));
        System.out.println("Статистика товаров для каждого магазина: ");

        priceStatistics.forEach((Block<Document>) doc->{
            System.out.println("---- Магазин: \""+doc.get("_id")+"\"");
            System.out.println("Общее количество наименование товаров: "+doc.get("ProductsSum"));
            System.out.println("Средняя цена товаров: "+doc.get("AvgPrice"));
            System.out.println("Самый дорогой товар: "+doc.get("MaxPrice"));
            System.out.println("Самый дешовый товар: "+doc.get("MinPrice"));
        });


        listOfProductsUpTo100.forEach((Block<Document>) doc -> System.out.println("Количество товаров дешевле 100 рублей: Магазин: \""+
                doc.get("_id")+"\": " +doc.get("LowProductPrice")));
    }

    public static boolean findProductInCollection(String productName, MongoCollection<Document> collectionProducts){
        if (collectionProducts.find(eq("Product", productName)).first() != null) {
            return true;
        }
        System.out.println("(!!) Добавьте сначала товар");
       return false;
    }

    public static boolean findStoreInCollection (String storeName, MongoCollection<Document> collectionStores){
        if (collectionStores.find(eq("Store", storeName)).first() != null) {
            return true;
        }
        System.out.println("(!!!) Добавьте сначала магазин");
        return false;
    }

}
