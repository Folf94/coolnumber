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


public class Shop {
    private static String commandExample = "Примеры команд:" + "\n - ДОБАВИТЬ_МАГАЗИН Девяточка" + "\n - ДОБАВИТЬ_" +
            "ТОВАР Вафли 54" + "\n - ВЫСТАВИТЬ_ТОВАР Вафли Девяточка" + "\n - СТАТИСТИКА_ТОВАРОВ" + "\n - EXIT - " +
            "завершает работу программы";

    public static void getShop() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println(commandExample);

        for (; ; ) {
            System.out.println("Введите команду:");
            String[] input = ShopCases.split(scanner.nextLine().trim());

            String instruction = input[0];
            String object = input.length > 1 ? input[1] : "";

            switch (instruction) {
                case "ДОБАВИТЬ_МАГАЗИН":
                    ShopCases.addShop(object);
                    break;
                case "ДОБАВИТЬ_ТОВАР":
                    ShopCases.addProducts(object);
                    break;
                case "ВЫСТАВИТЬ_ТОВАР":
                    ShopCases.addProductsToShop(object);
                    break;
                case "СТАТИСТИКА_ТОВАРОВ":
                    ShopCases.printStatistic();
                    break;
                case "EXIT":
                    ShopCases.shutdownDB();
                    return;
                default:
                    printError();
            }
        }
    }

    private static void printError() {
        System.out.println("Неверный ввод!");
        System.out.println(commandExample);
    }


    static class ShopCases {

        private static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        private static MongoDatabase stores = mongoClient.getDatabase("stores");
        private static MongoCollection<Document> shops = stores.getCollection("shop");
        private static MongoCollection<Document> products = stores.getCollection("products");

        public static void addShop(String shopName) {
            if (shopName.isEmpty()) {
                throw new RuntimeException();
            }

            try {
                Document shop = new Document("name", shopName);
                shop.append("products", new ArrayList<String>());

                if (getShop(shopName) == null) {
                    ShopCases.shops.insertOne(shop);
                    System.out.println("Магазин " + shopName + " добавлен!");
                } else {
                    System.out.println("Магазин уже был добавлен!");
                }
            } catch (Exception e) {
                System.out.println("Неверный ввод!");
            }
        }

        public static void addProducts(String input) {
            try {
                String[] var = split(input);
                String name = var[0];
                Document product = new Document("name", name);
                product.append("price", Integer.parseInt(var[1]));

                if (getProduct(name) == null) {
                    products.insertOne(product);
                    System.out.println("Продукт " + name + " добавлен!");
                } else {
                    System.out.println("Товар уже был добавлен");
                }
            } catch (Exception e) {
                System.out.println("Неверный ввод!");
            }
        }

        public static void addProductsToShop(String input) {
            try {
                String[] var = split(input);
                String shopName = var[1];
                String productName = var[0];

                shops.updateOne(getShop(shopName), new Document("$addToSet", new Document("products",
                        getProduct(productName).get("name"))));
                System.out.println("Продукт " + productName + " добавлен в магазин " + shopName);
            } catch (Exception e) {
                System.out.println("Магазин или продукт не существуют! ");
            }
        }

        public static void printStatistic() {
            printInfo(getAggregate());
        }

        private static AggregateIterable<Document> getAggregate() {
            return products.aggregate(Arrays.asList(Aggregates.lookup("shop", "name", "products", "shop_list"),
                    Aggregates.unwind("$shop_list"), Aggregates.group("$shop_list.name", Accumulators.sum(
                            "count_products", 1), Accumulators.min("min_price", "$price"), Accumulators.max(
                                    "max_price", "$price"), Accumulators.avg("avg_price", "$price"))));
        }

        private static void printInfo(AggregateIterable<Document> documents) {
            for (Document document : documents) {
                String shopName = (String) document.get("_id");
                System.out.println("Магазин " + shopName);
                System.out.println("Количество товара: " + document.get("count_products"));
                System.out.println("Средняя цена товара: " + document.get("avg_price"));
                System.out.println("Самый дорогой товар:  " + document.get("max_price"));
                System.out.println("Самый дешевый товар:  " + document.get("min_price"));
                System.out.println("Количество товаров, дешевле 100 рублей: " + cheapestProductCount(shopName));
                System.out.println();
            }
        }

        private static long cheapestProductCount(String shopName) {
            Document shop = getShop(shopName);
            ArrayList<String> products = (ArrayList<String>) shop.get("products");
            return products.stream().filter(s -> (int) getProduct(s).get("price") < 100).count();
        }

        public static void shutdownDB() {
            stores.drop();
            mongoClient.close();
            System.out.println("Работа завершена!");
        }

        private static Document getShop(String name) {
            return shops.find(new Document("name", name)).first();
        }

        private static Document getProduct(String name) {
            return products.find(new Document("name", name)).first();
        }

        public static String[] split(String var) {
            return var.split(" ", 2);
        }
    }
}
