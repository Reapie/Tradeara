package at.ahif18.tradeara.bl;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import at.ahif18.tradeara.data.Account;

public class PrefManager {

    private Context ctx;
    SharedPreferences sharedPreferences;

    public PrefManager(Context ctx) {
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences("Account", Context.MODE_PRIVATE);
    }

    public Account getOrCreate() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ctx.getString(R.string.accountKey), "");
        Account obj = gson.fromJson(json, Account.class);
        return obj;
    }


}
