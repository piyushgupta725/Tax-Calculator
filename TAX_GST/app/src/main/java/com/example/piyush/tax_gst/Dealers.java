package com.example.piyush.tax_gst;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Dealers {
    private Dealers(){ }
    public static final String CONTENT_AUTHORITY = "com.example.piyush.tax_gst";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TAX = "GST";
    public final static class TaxEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TAX);
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAX;
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TAX;
        public final static String TABLE_NAME ="T_detail";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEMS ="items";
        public final static String COLUMN_TAX = "TAX";
    }
}