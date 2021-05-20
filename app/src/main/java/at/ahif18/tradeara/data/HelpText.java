package at.ahif18.tradeara.data;

import java.time.MonthDay;

public class HelpText {
    private String title;
    private String descriptions;

    public HelpText(String title, String description) {
        this.title = title;
        this.descriptions = description;
    }

    public String getTitle() { return title; }

    public String getDescription() {return descriptions;}
}
