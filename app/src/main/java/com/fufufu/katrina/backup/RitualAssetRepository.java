package com.fufufu.katrina.backup;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class RitualAssetRepository {
    private static final Gson GSON = new Gson();
    private static ArrayList<HashMap<String, Object>> cachedPropData;
    private static ArrayList<HashMap<String, Object>> cachedDumpData;

    private RitualAssetRepository() {
    }

    public static synchronized ArrayList<HashMap<String, Object>> loadPropData(Context context) throws IOException {
        if (cachedPropData == null) {
            String fingerprintJson = tryReadAsset(context, "fingerprint.json");
            if (fingerprintJson != null && !fingerprintJson.trim().isEmpty()) {
                cachedPropData = convertFingerprintData(fingerprintJson);
            } else {
                cachedPropData = parseGroupedData(readAsset(context, "prop.json"));
            }
        }
        return new ArrayList<>(cachedPropData);
    }

    public static synchronized ArrayList<HashMap<String, Object>> loadDumpData(Context context) throws IOException {
        if (cachedDumpData == null) {
            cachedDumpData = parseGroupedData(readAsset(context, "dump.json"));
        }
        return new ArrayList<>(cachedDumpData);
    }

    private static ArrayList<HashMap<String, Object>> parseGroupedData(String json) {
        return GSON.fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>() { }.getType());
    }

    private static ArrayList<HashMap<String, Object>> convertFingerprintData(String json) {
        List<HashMap<String, Object>> entries = GSON.fromJson(json, new TypeToken<ArrayList<HashMap<String, Object>>>() { }.getType());
        LinkedHashMap<String, ArrayList<HashMap<String, Object>>> grouped = new LinkedHashMap<>();
        for (HashMap<String, Object> item : entries) {
            String brandRaw = asString(item.get("Brand"));
            String device = asString(item.get("Device"));
            String release = asString(item.get("Version"));
            String fingerprint = asString(item.get("Fingerprint"));
            if (brandRaw.isEmpty() || device.isEmpty() || fingerprint.isEmpty()) {
                continue;
            }
            ParsedFingerprint parsed = parseFingerprint(fingerprint, release, device);
            String merek = brandRaw.toUpperCase(Locale.ROOT);
            ArrayList<HashMap<String, Object>> data = grouped.get(merek);
            if (data == null) {
                data = new ArrayList<>();
                grouped.put(merek, data);
            }
            data.add(buildPropEntry(brandRaw, device, parsed));
        }
        ArrayList<HashMap<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, ArrayList<HashMap<String, Object>>> entry : grouped.entrySet()) {
            HashMap<String, Object> brandGroup = new HashMap<>();
            brandGroup.put("MEREK", entry.getKey());
            brandGroup.put("DATA", entry.getValue());
            result.add(brandGroup);
        }
        return result;
    }

    private static HashMap<String, Object> buildPropEntry(String brandRaw, String device, ParsedFingerprint parsed) {
        HashMap<String, Object> map = new HashMap<>();
        String manufacturer = normalizeManufacturer(brandRaw);
        String product = parsed.product.isEmpty() ? device : parsed.product;
        String model = device;
        String displayName = manufacturer + " " + device;
        String description = product + "-user " + parsed.release + " " + parsed.buildId + " " + parsed.incremental + " release-keys";
        map.put("DEVICENAME", displayName);
        map.put("MANUFACTURER", manufacturer);
        map.put("MODEL", model);
        map.put("BRAND", brandRaw.toLowerCase(Locale.ROOT));
        map.put("PRODUCT", product);
        map.put("DEVICE", device);
        map.put("RELEASE", parsed.release);
        map.put("BUILDID", parsed.buildId);
        map.put("INCREMENTAL", parsed.incremental);
        map.put("FINGERPRINT", parsed.fingerprint);
        map.put("DESCRIPTION", description);
        map.put("NAME", product);
        map.put("DISPLAY", parsed.buildId + "." + parsed.incremental);
        map.put("BOOT", parsed.incremental);
        map.put("BOARD", device);
        map.put("HARDWARE", device);
        map.put("HOST", manufacturer.toLowerCase(Locale.ROOT) + "-host");
        map.put("USER", "android-build");
        map.put("FLAVOR", product + "-user");
        map.put("SDK", parsed.sdk);
        map.put("DATE", "Mon Jan 01 00:00:00 KST 2024");
        map.put("UTC", "1704038400");
        return map;
    }

    private static ParsedFingerprint parseFingerprint(String fingerprint, String releaseFallback, String deviceFallback) {
        ParsedFingerprint parsed = new ParsedFingerprint();
        parsed.fingerprint = fingerprint;
        parsed.release = releaseFallback;
        parsed.product = deviceFallback;
        parsed.buildId = "UP1A.000000.000";
        parsed.incremental = "release";
        String[] slashParts = fingerprint.split("/");
        if (slashParts.length >= 3) {
            parsed.product = slashParts[1];
            String[] deviceAndRest = slashParts[2].split(":", 2);
            if (deviceAndRest.length == 2) {
                String[] versionParts = deviceAndRest[1].split("/");
                if (versionParts.length >= 3) {
                    parsed.release = versionParts[0];
                    parsed.buildId = versionParts[1];
                    parsed.incremental = versionParts[2].split(":")[0];
                }
            }
        }
        parsed.sdk = guessSdk(parsed.release);
        return parsed;
    }

    private static String guessSdk(String release) {
        if (release == null || release.isEmpty()) {
            return "29";
        }
        if (release.startsWith("16")) return "36";
        if (release.startsWith("15")) return "35";
        if (release.startsWith("14")) return "34";
        if (release.startsWith("13")) return "33";
        if (release.startsWith("12.1")) return "32";
        if (release.startsWith("12")) return "31";
        if (release.startsWith("11")) return "30";
        if (release.startsWith("10")) return "29";
        return "29";
    }

    private static String normalizeManufacturer(String brand) {
        if (brand == null || brand.isEmpty()) {
            return "Android";
        }
        if (brand.length() == 1) {
            return brand.toUpperCase(Locale.ROOT);
        }
        return brand.substring(0, 1).toUpperCase(Locale.ROOT) + brand.substring(1);
    }

    private static String readAsset(Context context, String name) throws IOException {
        try (InputStream in = context.getAssets().open(name)) {
            return SketchwareUtil.copyFromInputStream(in);
        }
    }

    private static String tryReadAsset(Context context, String name) throws IOException {
        try {
            return readAsset(context, name);
        } catch (IOException e) {
            return null;
        }
    }

    private static String asString(Object value) {
        return value == null ? "" : value.toString().trim();
    }

    private static final class ParsedFingerprint {
        String fingerprint;
        String release;
        String product;
        String buildId;
        String incremental;
        String sdk;
    }
}
