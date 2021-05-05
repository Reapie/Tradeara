package at.ahif18.tradeara.bl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;

public class PrefManager {

    private final Context ctx;
    private final SharedPreferences sharedPreferences;
    private final String ACCOUNT_KEY;

    private String name;

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
            acc = new Account();
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Name");
            // Set up the input
            final EditText input = new EditText(ctx);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            // Set up the buttons
            Account finalAcc = acc;
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name = input.getText().toString();
                    finalAcc.setName(name);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name = "anonymous";
                    dialog.cancel();
                }
            });
            builder.show();
            setAccount(finalAcc);
        }
        return acc;
    }

    public void setAccount(Account acc) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(acc);
        System.out.println(json);
        prefsEditor.putString(ACCOUNT_KEY, json);
        prefsEditor.apply();
    }

}
