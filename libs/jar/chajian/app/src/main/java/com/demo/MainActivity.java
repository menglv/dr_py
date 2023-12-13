package com.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);

        TextView text = new TextView(this);
        text.setText(w());
        copyUrl(getApplicationContext(), w());
        text.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        layout.addView(text);

        setContentView(layout);
    }
    private void copyUrl(Context context, String url) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, url);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clipData);
        }
    }
    public String w() {
        StringBuilder sb = new StringBuilder();
        String[] e = g(getApplicationContext());
        for (String str : e) {
            sb.append("");
            sb.append(str);
        }
        return sb.toString();
    }
    public static String[] g(Context context) {
        Signature[] signatureArr;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = "com.mjxq.app";
            @SuppressLint("WrongConstant")
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, Build.VERSION.SDK_INT >= 28 ? 134217728 : 64);
            signatureArr = Build.VERSION.SDK_INT >= 28 ? packageInfo.signingInfo.hasMultipleSigners() ? packageInfo.signingInfo.getApkContentsSigners() : packageInfo.signingInfo.getSigningCertificateHistory() : packageInfo.signatures;

        } catch (Exception unused) {
            signatureArr = new Signature[0];
        }
        for (Signature signature : signatureArr) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                String encodeToString = Base64.encodeToString(instance.digest(), 0);
                arrayList.add(encodeToString);
            } catch (Exception unused2) {
            }
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (String str : arrayList) {
            boolean z = true;
            if (str.isEmpty()) {
                z = false;
            }
            if (z) {
                arrayList2.add(str);
            }
        }
        return arrayList2.toArray(new String[0]);
    }

}