package com.example.sharepreference;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.opengl.Matrix;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.net.ContentHandler;
import java.security.GeneralSecurityException;

public class ContentProvider extends android.content.ContentProvider {
    public static final String TAG= "ContentProvider";
    private static final String MY_SHARE_PREFERENCES = "MY_SHARE_PREFERENCES";

    private SharedPreferences mSharedPreferences;
    @Override
    public boolean onCreate() {
        try {
            MasterKey masterKey =
                    new MasterKey.Builder(getContext(),"Chung")
                            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                            .build();
            mSharedPreferences = EncryptedSharedPreferences.create(
                    getContext(),
                    MY_SHARE_PREFERENCES,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String[] columns = new String[]{"name", "address","email"};
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        MatrixCursor.RowBuilder builder = matrixCursor.newRow();

        String name = mSharedPreferences.getString("name","");
        String address = mSharedPreferences.getString("address","");
        String email = mSharedPreferences.getString("email","");
        builder.add(name).add(address).add(email);
        Log.d(TAG, "query: "+matrixCursor);
        return matrixCursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
