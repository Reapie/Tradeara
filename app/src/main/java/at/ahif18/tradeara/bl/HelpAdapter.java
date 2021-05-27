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
                new HelpText("Aktienverläufe","Klicken sie auf diesen Container um " +
                        "auf eine Hilfreiche Seite für Aktien zu bekommen",
                        "https://www.sparkasse.de/themen/wertpapiere-als-" +
                                "geldanlage/10-tipps-aktienanfaenger.html"),

                new HelpText("Arten von Aktien","Website für Verschiedene Arten von Aktien",
                                     "https://www.zinsenvergleich.at/aktie-ist-" +
                                             "nicht-gleich-aktie-verschiedene-arten-von-" +
                                             "aktien-im-ueberblick-2966/"),

                new HelpText("Wie enstehen Aktien","Klicken sie auf diesen Container " +
                        "einen Artikel von Enstehungen von Aktien zu sehen",
                        "https://www.diekleinanleger.com/folge-21-der-" +
                                "kurs-einer-aktie-aktien-teil-2/#:~:text=Der%20Kurs%20einer%20" +
                                "Aktie%20entsteht,der%20Kurs%20auf%2010%20%E2%82%AC.")

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

