package repository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class WebPageRepository {
    static String path = System.getProperty("user.dir");
    public void saveException(Exception ex) {
        String logFileName = "exceptions.txt";
        try {
            if (!Files.exists(Paths.get(path + "\\" + logFileName))) {
                Files.createFile(Paths.get(path + "\\" + logFileName));
            }
            FileWriter fw = new FileWriter(logFileName);
            Date date = new Date();
            fw.write(date + " " + ex);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document getHtmlPage(String url) throws Exception {
        System.out.println(path);
        String fileName = "";
        String htmlToSave = "";
        Document doc;
        final String httpUrl;
        final String httpsUrl;
        if (url.contains("http://") || url.contains("https://")) {
            httpUrl = url;
            httpsUrl = url;
        } else {
            httpUrl = "http://" + url;
            httpsUrl = "https://" + url;
        }
        try {
            doc = Jsoup.connect(httpsUrl)
                    .userAgent("Chrome/81.0.4044.138")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException exception) {
            doc = Jsoup.connect(httpUrl)
                    .userAgent("Chrome/81.0.4044.138")
                    .referrer("http://www.google.com")
                    .get();
        }
        htmlToSave = doc.html();
        if (url.contains("www")) {
            fileName = url.substring(url.indexOf(".") + 1, url.indexOf(".", url.indexOf(".") + 1));
        } else {
            fileName = url.substring(url.indexOf("//") + 1, url.indexOf("."));
        }
        try {
            String pathFile = path + "\\" + fileName + ".html";
            FileWriter fw = new FileWriter(pathFile);
            fw.write(htmlToSave);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Не удалось скачать/записать html-страницу в файл!");
            saveException(ex);
        }
        return doc;
    }
}