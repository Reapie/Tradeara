package at.ahif18.tradeara.bl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.EditText;

import com.google.gson.Gson;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;

public class PrefManager {

    private final Context ctx;
    private final SharedPreferences sharedPreferences;
    private final String ACCOUNT_KEY;

    public PrefManager(Context ctx) {
        this.ctx = ctx;
        ACCOUNT_KEY = this.ctx.getString(R.string.accountKey);
        sharedPreferences = this.ctx.getSharedPreferences("Account", Context.MODE_PRIVATE);
    }

    public Account getOrCreate() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ACCOUNT_KEY, null);
        Account acc = gson.fromJson(json, Account.class);
        if (acc == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Name");
            // Set up the input
            final EditText input = new EditText(ctx);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            final String[] name = new String[1];
            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name[0] = input.getText().toString();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name[0] = "anonymous";
                    dialog.cancel();
                }
            });
            acc = new Account(name[0]);
            builder.show();
            setAccount(acc);
        }
        return acc;
    }

    public void setAccount(Account acc) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(acc);
        prefsEditor.putString(ACCOUNT_KEY, json);
        prefsEditor.apply();
    }

}
