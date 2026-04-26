package com.fufufu.katrina.backup;

import androidx.exifinterface.media.ExifInterface;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileReader {
    public static ArrayList<HashMap<String, Object>> executeShellCommand(String str) {
        List<String> out = Shell.cmd(str).exec().getOut();
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (String str2 : out) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("path", str2.replace(" directory", "").replace(" regular file", "").replace(" regular empty file", "").replace(" symbolic link", "").replace("//", "/").replace("1 /", "/").replace("2 /", "/"));
            map.put("pick", "false");
            if (str2.contains(" symbolic link")) {
                map.put("type", "L");
            } else if (str2.contains(" directory")) {
                map.put("type", "D");
            } else if (str2.contains(" regular file")) {
                map.put("type", "F");
            } else if (str2.contains(" regular empty file")) {
                map.put("type", ExifInterface.LONGITUDE_EAST);
            } else {
                map.put("type", "?");
            }
            arrayList.add(map);
        }
        return arrayList;
    }
}