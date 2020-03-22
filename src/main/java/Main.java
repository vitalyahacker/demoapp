import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String baseUrl = "https://1xbet.com";
    private static final String lang = "en";
    private static final String prefix = "line";
    private static final String game = "Basketball";
    private static final String url = String.format("%s/%s/%s/%s/", baseUrl, lang, prefix, game);

    public static void main(String[] args) throws Throwable {
        Document mainPage = Jsoup.connect(url).get();
        Elements links = mainPage.select("a[href]");
        List<String> matchPageLinks = links.stream()
                .map(element -> element.attributes().get("href"))
                .filter(link -> link.contains(game))
                .map(link -> link.replaceAll(String.format("%s/%s/", prefix, game), ""))
                .filter(link -> !link.isBlank())
                .collect(Collectors.toList());

        Document document = Jsoup.connect(url + matchPageLinks.get(10)).get();
        Elements betMiddle = document.select(".bet_middle");
        List<Element> collect = new ArrayList<>(betMiddle);
        System.out.println(links);

    }
}
