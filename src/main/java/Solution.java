import interactor.WebPageParser;
import repository.WebPageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        System.out.print("Введите адрес сайта:");
        Scanner console = new Scanner(System.in);
        String url = console.nextLine();
        final WebPageRepository rep = new WebPageRepository();
        final WebPageParser page = new WebPageParser(rep);
        try {
            do {
                HashMap<String, Integer> resultParser = page.pars(url);
                print(resultParser);
                System.out.println("Введите адрес следующего сайта. Для выхода введите \"exit\"");
                url = console.nextLine();
            } while (!url.equals("exit"));
        } catch (Exception ex) {
            System.out.println("Что-то пошло не так!");
            rep.saveException(ex);
        }
    }

    private static void print(HashMap<String, Integer> hp) {
        for (Map.Entry<String, Integer> entry : hp.entrySet())
            System.out.println(entry.getKey() + " - " + entry.getValue());
    }
}









