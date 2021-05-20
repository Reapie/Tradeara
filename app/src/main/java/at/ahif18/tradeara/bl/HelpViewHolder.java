package at.ahif18.tradeara.bl;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.HelpText;

public class HelpViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

    private TextView title;
    private TextView HelpText;
    private TextView Link;


    public HelpViewHolder(@NonNull View itemView,TextView title, TextView tvDescription, TextView link) {
        super(itemView);
        this.title = title;
        this.HelpText = tvDescription;
        this.Link = link;
        itemView.setOnClickListener(this);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return HelpText;
    }

    public TextView getLink() {
        return Link;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setDescription(TextView HelpText) {
        this.HelpText = HelpText;
    }

    public void setLink(TextView link) {
        this.Link = link;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent ("android.intent.action.VIEW", Uri.parse(Link.getText().toString()));
        v.getContext().startActivity(intent);
    }
}
