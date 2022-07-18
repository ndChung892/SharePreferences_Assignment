package com.example.sharepreference;

import android.content.ContentValues;
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

import java.io.IOException;
import java.net.ContentHandler;
import java.security.GeneralSecurityException;

public class ContentProvider extends android.content.ContentProvider {
    public static final String TAG= "ContentProvider";

    private MySharePreferences mySharePreferences;
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String[] columns = new String[]{"name", "address","email"};
        MatrixCursor matrixCursor = new MatrixCursor(columns);
        String name = mySharePreferences.getValue("name");
        String address = mySharePreferences.getValue("address");
        String email = mySharePreferences.getValue("email");
        matrixCursor.addRow(new Object[] {name, address, email});
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
