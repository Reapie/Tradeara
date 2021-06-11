package at.ahif18.tradeara.bl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.data.Account;

public class PrefManager {

    private final Context ctx;
    private final SharedPreferences sharedPreferences;
    private final String ACCOUNT_KEY;
    private final String ACCOUNT_NAME_KEY;
    private final MainActivity main;
    private final ObjectMapper mapper = new ObjectMapper()
;
    private String name;

    public PrefManager(Context ctx, MainActivity main) {
        this.ctx = ctx;
        ACCOUNT_KEY = this.ctx.getString(R.string.accountKey);
        ACCOUNT_NAME_KEY = this.ctx.getString(R.string.accountNameKey);
        sharedPreferences = this.ctx.getSharedPreferences("Account", Context.MODE_PRIVATE);
        this.main = main;
    }

    public void getOrCreate() {
        String jsonS = sharedPreferences.getString(ACCOUNT_KEY, "");
        System.out.println("----------Converting-From-JSON------------");
        System.out.println(jsonS);
        Account acc = null;
        try {
            acc = mapper.readValue(jsonS, Account.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = sharedPreferences.getString(ACCOUNT_NAME_KEY, "none");
        if (jsonS.equals("{}") || name.equals("none") || acc == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Name");
            // Set up the input
            final EditText input = new EditText(ctx);
            input.setPadding(50, 50, 50, 50);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setCancelable(false);
            // Set up the buttons
            builder.setPositiveButton("OK", (dialog, which) -> {
                String name = input.getText().toString();
                Account acc1 = new Account(name, main);
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                prefsEditor.putString(ACCOUNT_NAME_KEY, name);
                prefsEditor.apply();
                dialog.cancel();
                setAccount(acc1);
                main.setAccount(acc1);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                String name = "none";
                Account acc12 = new Account(name, main);
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                prefsEditor.putString(ACCOUNT_NAME_KEY, name);
                prefsEditor.apply();
                dialog.cancel();
                setAccount(acc12);
                main.setAccount(acc12);
            });
            builder.show();
        } else {
            main.setAccount(acc);
        }
    }

    public void setAccount(Account acc) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = "test";
        System.out.println("----------------------");
        try {
            json = mapper.writeValueAsString(acc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        prefsEditor.putString(ACCOUNT_KEY, json);
        prefsEditor.putString(ACCOUNT_NAME_KEY, acc.getName());
        prefsEditor.apply();
    }

}
