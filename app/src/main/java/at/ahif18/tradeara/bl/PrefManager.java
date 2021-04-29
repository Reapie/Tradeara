package at.ahif18.tradeara.bl;

import android.content.Context;
import android.content.SharedPreferences;

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
            acc = new Account();
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            json = gson.toJson(acc);
            prefsEditor.putString(ACCOUNT_KEY, json);
            prefsEditor.apply();
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
