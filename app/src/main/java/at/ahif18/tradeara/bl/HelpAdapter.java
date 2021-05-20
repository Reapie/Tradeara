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
                new HelpText("Aktienverläufe","Klicken sie auf diesen Container um auf eine Hilfreiche Seite für Aktien zu bekommen","https://nolur.com/)")
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
        }

        @Override
        public int getItemCount() {
            return descriptions.size();
        }
    }

