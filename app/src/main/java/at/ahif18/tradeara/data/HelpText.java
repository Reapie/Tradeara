package at.ahif18.tradeara.data;

import java.time.MonthDay;

public class HelpText {
    private String title;
    private String descriptions;
    private String link;

    public HelpText(String title, String description,String link) {
        this.title = title;
        this.descriptions = description;
        this.link = link;
    }

    public String getTitle() { return title; }

    public String getDescription() {return descriptions;}
    public String getLink() {return link;}


}
