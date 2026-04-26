package com.termfu.filepicker;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import android.webkit.MimeTypeMap;
import com.fufufu.katrina.backup.C0978R;
import com.termfu.app.TermuxService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes91.dex */
public class TermuxDocumentsProvider extends DocumentsProvider {
    private static final String ALL_MIME_TYPES = "*/*";
    private static final File BASE_DIR = new File(TermuxService.HOME_PATH);
    private static final String[] DEFAULT_ROOT_PROJECTION = {"root_id", "mime_types", "flags", "icon", "title", "summary", "document_id", "available_bytes"};
    private static final String[] DEFAULT_DOCUMENT_PROJECTION = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size"};

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryRoots(String[] strArr) throws FileNotFoundException {
        if (strArr == null) {
            strArr = DEFAULT_ROOT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        String string = getContext().getString(C0978R.string.termfu_application_name);
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        File file = BASE_DIR;
        rowBuilderNewRow.add("root_id", getDocIdForFile(file));
        rowBuilderNewRow.add("document_id", getDocIdForFile(file));
        rowBuilderNewRow.add("summary", null);
        rowBuilderNewRow.add("flags", 25);
        rowBuilderNewRow.add("title", string);
        rowBuilderNewRow.add("mime_types", ALL_MIME_TYPES);
        rowBuilderNewRow.add("available_bytes", Long.valueOf(file.getFreeSpace()));
        rowBuilderNewRow.add("icon", Integer.valueOf(C0978R.mipmap.termfu_ic_launcher));
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryDocument(String str, String[] strArr) throws FileNotFoundException {
        if (strArr == null) {
            strArr = DEFAULT_DOCUMENT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        includeFile(matrixCursor, str, null);
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public Cursor queryChildDocuments(String str, String[] strArr, String str2) throws FileNotFoundException {
        if (strArr == null) {
            strArr = DEFAULT_DOCUMENT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        for (File file : getFileForDocId(str).listFiles()) {
            includeFile(matrixCursor, null, file);
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return ParcelFileDescriptor.open(getFileForDocId(str), ParcelFileDescriptor.parseMode(str2));
    }

    @Override // android.provider.DocumentsProvider
    public AssetFileDescriptor openDocumentThumbnail(String str, Point point, CancellationSignal cancellationSignal) throws FileNotFoundException {
        File fileForDocId = getFileForDocId(str);
        return new AssetFileDescriptor(ParcelFileDescriptor.open(fileForDocId, 268435456), 0L, fileForDocId.length());
    }

    @Override // android.provider.DocumentsProvider
    public String createDocument(String str, String str2, String str3) throws FileNotFoundException {
        boolean zCreateNewFile;
        File file = new File(str, str3);
        int i = 2;
        while (file.exists()) {
            file = new File(str, String.valueOf(str3) + " (" + i + ")");
            i++;
        }
        try {
            if ("vnd.android.document/directory".equals(str2)) {
                zCreateNewFile = file.mkdir();
            } else {
                zCreateNewFile = file.createNewFile();
            }
            if (!zCreateNewFile) {
                throw new FileNotFoundException("Failed to create document with id " + file.getPath());
            }
            return file.getPath();
        } catch (IOException unused) {
            throw new FileNotFoundException("Failed to create document with id " + file.getPath());
        }
    }

    @Override // android.provider.DocumentsProvider
    public void deleteDocument(String str) throws FileNotFoundException {
        if (getFileForDocId(str).delete()) {
            return;
        }
        throw new FileNotFoundException("Failed to delete document with id " + str);
    }

    @Override // android.provider.DocumentsProvider
    public String getDocumentType(String str) throws FileNotFoundException {
        return getMimeType(getFileForDocId(str));
    }

    @Override // android.provider.DocumentsProvider
    public Cursor querySearchDocuments(String str, String str2, String[] strArr) throws FileNotFoundException {
        boolean zStartsWith;
        if (strArr == null) {
            strArr = DEFAULT_DOCUMENT_PROJECTION;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        File fileForDocId = getFileForDocId(str);
        LinkedList linkedList = new LinkedList();
        linkedList.add(fileForDocId);
        while (!linkedList.isEmpty() && matrixCursor.getCount() < 50) {
            File file = (File) linkedList.removeFirst();
            try {
                zStartsWith = file.getCanonicalPath().startsWith(TermuxService.HOME_PATH);
            } catch (IOException unused) {
                zStartsWith = true;
            }
            if (zStartsWith) {
                if (file.isDirectory()) {
                    Collections.addAll(linkedList, file.listFiles());
                } else if (file.getName().toLowerCase().contains(str2)) {
                    includeFile(matrixCursor, null, file);
                }
            }
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public boolean isChildDocument(String str, String str2) {
        return str2.startsWith(str);
    }

    private static String getDocIdForFile(File file) {
        return file.getAbsolutePath();
    }

    private static File getFileForDocId(String str) throws FileNotFoundException {
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        throw new FileNotFoundException(String.valueOf(file.getAbsolutePath()) + " not found");
    }

    private static String getMimeType(File file) {
        if (file.isDirectory()) {
            return "vnd.android.document/directory";
        }
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(iLastIndexOf + 1).toLowerCase());
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    private void includeFile(MatrixCursor matrixCursor, String str, File file) throws FileNotFoundException {
        if (str == null) {
            str = getDocIdForFile(file);
        } else {
            file = getFileForDocId(str);
        }
        int i = 0;
        if (file.isDirectory()) {
            if (file.canWrite()) {
                i = 8;
            }
        } else if (file.canWrite()) {
            i = 2;
        }
        if (file.getParentFile().canWrite()) {
            i |= 4;
        }
        String name = file.getName();
        String mimeType = getMimeType(file);
        if (mimeType.startsWith("image/")) {
            i |= 1;
        }
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        rowBuilderNewRow.add("document_id", str);
        rowBuilderNewRow.add("_display_name", name);
        rowBuilderNewRow.add("_size", Long.valueOf(file.length()));
        rowBuilderNewRow.add("mime_type", mimeType);
        rowBuilderNewRow.add("last_modified", Long.valueOf(file.lastModified()));
        rowBuilderNewRow.add("flags", Integer.valueOf(i));
        rowBuilderNewRow.add("icon", Integer.valueOf(C0978R.mipmap.termfu_ic_launcher));
    }
}
