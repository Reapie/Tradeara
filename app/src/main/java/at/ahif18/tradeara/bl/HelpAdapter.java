package at.ahif18.tradeara.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.components.Description;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.HelpText;

public class HelpAdapter extends RecyclerView.Adapter<HelpViewHolder> {
        private List<HelpText> descriptions = Arrays.asList(

                new HelpText("Begriffe","Hilfreiche Seite um Begriffe der Aktienwelt schnell zu verstehen",
                        "https://at.scalable.capital/boerse/boersenlexikon-33-wichtige-begriffe-kurz-erklaert"),

                new HelpText("Tipps zum Handeln mit Aktien",
                        "Webseite von finanzen.net für Tipps mit Handeln von Aktien",
                        "https://www.finanzen.net/ratgeber/wertpapiere/aktien-kaufen#:~:text=Tipp%201%3A%20Depot%20f%C3%BCr%20den,h%C3%A4ufig%20g%C3%BCnstigeren%20Online%2DBroker%20er%C3%B6ffnen."),

                new HelpText("Arten von Aktien","Webseite für das Kennenlernen von verschiedenen Aktien Charts",
                                     "https://www.xtb.com/de/Ausbildung/arten-von-charts#:~:text=Die%20drei%20bekanntesten%20Chartarten%20sind,%2DCharts%20(Kerzen%2DCharts)"),

                new HelpText("Aktienzertifikate",
                        "6 Schritte zum Erstellen einer Aktie",
                        "https://www.das-aktienregister.ch/aktienbuch/Erstellung-Erwerb-Ubertragung-Aktienzertifikate/"),

                new HelpText("Liquidität einer Aktie",
                        "Beitrag von der Börse Frankfurt für die" +
                                "Beschreibung einer Liquidität einer Aktie",
                        "https://www.boerse-frankfurt.de/wissen/handeln/liquiditaet"),

                new HelpText("Top/Flop","Diese Seite zeigt ihnen Aktien mit einer extrem guten oder schlechten Verlauf",
                        "https://www.onvista.de/aktien/top-flop/")

        );

    @NonNull
        @Override
        public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item,parent,false);

            TextView tvtitle = view.findViewById(R.id.tvTitle);
            TextView tvdescription = view.findViewById(R.id.tvDescription);
            TextView tvLink = view.findViewById(R.id.tvLink);

            return new HelpViewHolder(view,tvtitle,tvdescription,tvLink);
        }

        @Override
        public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {

            HelpText description = descriptions.get(position);
            int pos = 0;
            if(position != (descriptions.size() - 1))
                pos = position + 1;

            holder.getTitle().setText(description.getTitle());
            holder.getDescription().setText(description.getDescription());
            holder.getLink().setText(description.getLink());
        }

        @Override
        public int getItemCount() {
            return descriptions.size();
        }
    }

