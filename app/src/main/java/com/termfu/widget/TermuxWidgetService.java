package com.termfu.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.fufufu.katrina.backup.C0978R;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes91.dex */
public final class TermuxWidgetService extends RemoteViewsService {

    @SuppressLint({"SdCardPath"})
    public static final String SHORTCUTS_DIR_PATH = "/data/data/com.fufufu.katrina.backup/files/home/.shortcuts";
    public static final String SHORTCUT_ICONS_DIR_BASENAME = "icons";
    public static final File SHORTCUTS_DIR = new File("/data/data/com.fufufu.katrina.backup/files/home/.shortcuts");
    public static final FileFilter SHORTCUT_FILES_FILTER = new FileFilter() { // from class: com.termfu.widget.TermuxWidgetService.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.getName().startsWith(".")) {
                return false;
            }
            return (TermuxWidgetService.SHORTCUTS_DIR.equals(file.getParentFile()) && file.getName().equals(TermuxWidgetService.SHORTCUT_ICONS_DIR_BASENAME)) ? false : true;
        }
    };

    public static final class TermuxWidgetItem {
        public final String mFile;
        public final String mLabel;

        public TermuxWidgetItem(File file, int i) {
            String str;
            if (i > 0) {
                str = String.valueOf(file.getParentFile().getName()) + "/";
            } else {
                str = "";
            }
            this.mLabel = String.valueOf(str) + file.getName().replace('-', ' ');
            this.mFile = file.getAbsolutePath();
        }
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(getApplicationContext());
    }

    public static class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private final Context mContext;
        private final List<TermuxWidgetItem> mWidgetItems = new ArrayList();

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public boolean hasStableIds() {
            return true;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onCreate() {
        }

        public ListRemoteViewsFactory(Context context) {
            this.mContext = context;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onDestroy() {
            this.mWidgetItems.clear();
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getCount() {
            return this.mWidgetItems.size();
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getViewAt(int i) {
            TermuxWidgetItem termuxWidgetItem = this.mWidgetItems.get(i);
            RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), C0978R.layout.termfu_widget_item);
            remoteViews.setTextViewText(C0978R.id.widget_item, termuxWidgetItem.mLabel);
            remoteViews.setOnClickFillInIntent(C0978R.id.widget_item_layout, new Intent().putExtra(TermuxWidgetProvider.EXTRA_CLICKED_FILE, termuxWidgetItem.mFile));
            return remoteViews;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        @SuppressLint({"SdCardPath"})
        public void onDataSetChanged() {
            this.mWidgetItems.clear();
            TermuxWidgetService.SHORTCUTS_DIR.mkdirs();
            TermuxWidgetService.addFile(TermuxWidgetService.SHORTCUTS_DIR, this.mWidgetItems, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addFile(File file, List<TermuxWidgetItem> list, int i) {
        File[] fileArrListFiles;
        if (i <= 5 && (fileArrListFiles = file.listFiles(SHORTCUT_FILES_FILTER)) != null) {
            Arrays.sort(fileArrListFiles, new Comparator() { // from class: com.termfu.widget.TermuxWidgetService$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return TermuxWidgetService.lambda$0((File) obj, (File) obj2);
                }
            });
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    addFile(file2, list, i + 1);
                } else {
                    list.add(new TermuxWidgetItem(file2, i));
                }
            }
        }
    }

    static /* synthetic */ int lambda$0(File file, File file2) {
        if (file.isDirectory() != file2.isDirectory()) {
            return file.isDirectory() ? 1 : -1;
        }
        return NaturalOrderComparator.compare(file.getName(), file2.getName());
    }
}
