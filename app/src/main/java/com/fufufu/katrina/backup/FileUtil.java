package com.fufufu.katrina.backup;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.budiyev.android.codescanner.BarcodeUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtil {
    private static void createNewFile(String str) {
        int iLastIndexOf = str.lastIndexOf(File.separator);
        if (iLastIndexOf > 0) {
            makeDir(str.substring(0, iLastIndexOf));
        }
        File file = new File(str);
        try {
            if (file.exists()) {
                return;
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String str) {
        createNewFile(str);
        StringBuilder sb = new StringBuilder();
        try (java.io.FileReader fileReader = new java.io.FileReader(new File(str))) {
            char[] cArr = new char[1024];
            int i;
            while ((i = fileReader.read(cArr)) > 0) {
                sb.append(new String(cArr, 0, i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void writeFile(String str, String str2) {
        createNewFile(str);
        try (FileWriter fileWriter = new FileWriter(new File(str), false)) {
            fileWriter.write(str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String str, String str2) {
        if (isExistFile(str)) {
            createNewFile(str2);
            try (FileInputStream fis = new FileInputStream(str);
                 FileOutputStream fos = new FileOutputStream(str2, false)) {
                byte[] bArr = new byte[1024];
                int i;
                while ((i = fis.read(bArr)) > 0) {
                    fos.write(bArr, 0, i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyDir(String str, String str2) {
        File[] fileArrListFiles = new File(str).listFiles();
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isFile()) {
                copyFile(file2.getPath(), String.valueOf(str2) + "/" + file2.getName());
            } else if (file2.isDirectory()) {
                copyDir(file2.getPath(), String.valueOf(str2) + "/" + file2.getName());
            }
        }
    }

    public static void moveFile(String str, String str2) {
        copyFile(str, str2);
        deleteFile(str);
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.isDirectory()) {
                        deleteFile(file2.getAbsolutePath());
                    }
                    if (file2.isFile()) {
                        file2.delete();
                    }
                }
            }
            file.delete();
        }
    }

    public static boolean isExistFile(String str) {
        return new File(str).exists();
    }

    public static void makeDir(String str) {
        if (isExistFile(str)) {
            return;
        }
        new File(str).mkdirs();
    }

    public static void listDir(String str, ArrayList<String> arrayList) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (!file.exists() || file.isFile() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length <= 0 || arrayList == null) {
            return;
        }
        arrayList.clear();
        for (File file2 : fileArrListFiles) {
            arrayList.add(file2.getAbsolutePath());
        }
    }

    public static boolean isDirectory(String str) {
        if (isExistFile(str)) {
            return new File(str).isDirectory();
        }
        return false;
    }

    public static boolean isFile(String str) {
        if (isExistFile(str)) {
            return new File(str).isFile();
        }
        return false;
    }

    public static long getFileLength(String str) {
        if (isExistFile(str)) {
            return new File(str).length();
        }
        return 0L;
    }

    public static String getExternalStorageDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getPackageDataDir(Context context) {
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getPublicDir(String str) {
        return Environment.getExternalStoragePublicDirectory(str).getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String convertUriToFilePath(Context context, Uri uri) {
        String path = null;
        Uri uri2;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] strArrSplit = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(strArrSplit[0])) {
                    path = Environment.getExternalStorageDirectory() + "/" + strArrSplit[1];
                } else {
                    path = null;
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(documentId) && documentId.startsWith("raw:")) {
                    return documentId.replaceFirst("raw:", "");
                }
                path = getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
            } else if (isMediaDocument(uri)) {
                String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = strArrSplit2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else {
                    uri2 = "audio".equals(str) ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null;
                }
                path = getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit2[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }
        if (path != null) {
            try {
                return URLDecoder.decode(path, "UTF-8");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        try (Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null)) {
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                return cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static void saveBitmap(Bitmap bitmap, String str) {
        createNewFile(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (IOException th2) {
                try { fileOutputStream.close(); } catch (IOException ignored) {}
                throw th2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getScaledBitmap(String str, int i) {
        int i2;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        int width = bitmapDecodeFile.getWidth();
        int height = bitmapDecodeFile.getHeight();
        if (width > height) {
            i2 = (int) (height * (i / width));
        } else {
            int i3 = (int) (width * (i / height));
            i2 = i;
            i = i3;
        }
        return Bitmap.createScaledBitmap(bitmapDecodeFile, i, i2, true);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = 1;
        if (i4 > i2 || i3 > i) {
            int i6 = i4 / 2;
            int i7 = i3 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeSampleBitmapFromPath(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static void resizeBitmapFileRetainRatio(String str, String str2, int i) {
        if (isExistFile(str)) {
            saveBitmap(getScaledBitmap(str, i), str2);
        }
    }

    public static void resizeBitmapFileToSquare(String str, String str2, int i) {
        if (isExistFile(str)) {
            saveBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(str), i, i, true), str2);
        }
    }

    public static void resizeBitmapFileToCircle(String str, String str2) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight());
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawCircle(bitmapDecodeFile.getWidth() / 2, bitmapDecodeFile.getHeight() / 2, bitmapDecodeFile.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmapDecodeFile, rect, rect, paint);
            saveBitmap(bitmapCreateBitmap, str2);
        }
    }

    public static void resizeBitmapFileWithRoundedBorder(String str, String str2, int i) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight());
            RectF rectF = new RectF(rect);
            float f = i;
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, f, f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmapDecodeFile, rect, rect, paint);
            saveBitmap(bitmapCreateBitmap, str2);
        }
    }

    public static void cropBitmapFileFromCenter(String str, String str2, int i, int i2) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            if (width >= i || height >= i2) {
                int i3 = width > i ? (width - i) / 2 : 0;
                int i4 = height > i2 ? (height - i2) / 2 : 0;
                if (i > width) {
                    i = width;
                }
                if (i2 > height) {
                    i2 = height;
                }
                saveBitmap(Bitmap.createBitmap(bitmapDecodeFile, i3, i4, i, i2), str2);
            }
        }
    }

    public static void rotateBitmapFile(String str, String str2, float f) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Matrix matrix = new Matrix();
            matrix.postRotate(f);
            saveBitmap(Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true), str2);
        }
    }

    public static void scaleBitmapFile(String str, String str2, float f, float f2) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Matrix matrix = new Matrix();
            matrix.postScale(f, f2);
            saveBitmap(Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true), str2);
        }
    }

    public static void skewBitmapFile(String str, String str2, float f, float f2) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Matrix matrix = new Matrix();
            matrix.postSkew(f, f2);
            saveBitmap(Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true), str2);
        }
    }

    public static void setBitmapFileColorFilter(String str, String str2, int i) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth() - 1, bitmapDecodeFile.getHeight() - 1);
            Paint paint = new Paint();
            paint.setColorFilter(new LightingColorFilter(i, 1));
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, paint);
            saveBitmap(bitmapCreateBitmap, str2);
        }
    }

    public static void setBitmapFileBrightness(String str, String str2, float f) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            ColorMatrix colorMatrix = new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), bitmapDecodeFile.getConfig());
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmapDecodeFile, 0.0f, 0.0f, paint);
            saveBitmap(bitmapCreateBitmap, str2);
        }
    }

    public static void setBitmapFileContrast(String str, String str2, float f) {
        if (isExistFile(str)) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            ColorMatrix colorMatrix = new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), bitmapDecodeFile.getConfig());
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmapDecodeFile, 0.0f, 0.0f, paint);
            saveBitmap(bitmapCreateBitmap, str2);
        }
    }

    public static int getJpegRotate(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, -1);
            if (attributeInt == 3) {
                return BarcodeUtils.ROTATION_180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return BarcodeUtils.ROTATION_270;
        } catch (IOException unused) {
            return 0;
        }
    }

    public static File createNewPictureFile(Context context) {
        return new File(String.valueOf(context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath()) + File.separator + (String.valueOf(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())) + ".jpg"));
    }
}