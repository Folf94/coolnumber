import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String DIRECTORY = "images/";

    public static void main(String[] args) throws IOException {
        List<String> images = new ArrayList<>();
        Document document = Jsoup.connect("https://lenta.ru/").get();
        Elements img = document.select("img[src~=(?i)\\.(png|jpe?g)]");
        for (Element element : img) {
            String image = element.attr("abs:src");
            images.add(image);
            downloadImage(images);
        }
    }

    private static void downloadImage(List<String> images) {

        for (String path : images) {
            try (InputStream in = new URL(path).openStream()) {
                Files.copy(in, Paths.get(DIRECTORY + new File(path).getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Image saved");

    }


}
