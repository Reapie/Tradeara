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
                new HelpText("Top/Flop","Diese Seite zeigt ihnen Aktien die eine extrem gute oder extrem schlechte Vergangenheit hatten",
                        "https://www.onvista.de/aktien/top-flop/"),

                new HelpText("Arten von Aktien","Webseite für das Kennenlernen von verschiedenen Aktien Charts",
                                     "https://www.xtb.com/de/Ausbildung/arten-von-charts#:~:text=Die%20drei%20bekanntesten%20Chartarten%20sind,%2DCharts%20(Kerzen%2DCharts)"),

                new HelpText("Wie enstehen Aktien",
                        "Einen Artikel von Enstehungen von Aktien sehen",
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

