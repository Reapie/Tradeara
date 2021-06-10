package at.ahif18.tradeara.bl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;

public class PrefManager {

    private final Context ctx;
    private final SharedPreferences sharedPreferences;
    private final String ACCOUNT_KEY;
    private final String ACCOUNT_NAME_KEY;
    private final MainActivity main;

    private String name;

    public PrefManager(Context ctx, MainActivity main) {
        this.ctx = ctx;
        ACCOUNT_KEY = this.ctx.getString(R.string.accountKey);
        ACCOUNT_NAME_KEY = this.ctx.getString(R.string.accountNameKey);
        sharedPreferences = this.ctx.getSharedPreferences("Account", Context.MODE_PRIVATE);
        this.main = main;
    }

    public void getOrCreate() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ACCOUNT_KEY, null);
        Account acc = gson.fromJson(json, Account.class);
        name = sharedPreferences.getString(ACCOUNT_NAME_KEY, "none");
        if (name.equals("none") || acc == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Name");
            // Set up the input
            final EditText input = new EditText(ctx);
            input.setPadding(50, 50, 50, 50);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setCancelable(false);
            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = input.getText().toString();
                    Account acc = new Account(name, main);
                    setAccount(acc);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    prefsEditor.putString(ACCOUNT_NAME_KEY, name);
                    prefsEditor.apply();
                    dialog.cancel();
                    main.setAccount(acc);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = "none";
                    Account acc = new Account(name, main);
                    setAccount(acc);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    prefsEditor.putString(ACCOUNT_NAME_KEY, name);
                    prefsEditor.apply();
                    dialog.cancel();
                    main.setAccount(acc);
                }
            });
            builder.show();
        } else {
            main.setAccount(acc);
        }
    }

    public void setAccount(Account acc) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(acc);
        System.out.println(json);
        prefsEditor.putString(ACCOUNT_KEY, json);
        prefsEditor.putString(ACCOUNT_NAME_KEY, acc.getName());
        prefsEditor.apply();
    }

}
