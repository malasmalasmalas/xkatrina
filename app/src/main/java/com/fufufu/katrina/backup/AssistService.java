package com.fufufu.katrina.backup;

import android.service.voice.VoiceInteractionService;
import android.util.Log;

public class AssistService extends VoiceInteractionService {
    private static AssistService currentInstance;

    public static AssistService getCurrentInstance() {
        return currentInstance;
    }

    public static void setCurrentInstance(AssistService assistService) {
        currentInstance = assistService;
    }

    public static boolean isServiceAvailable() {
        return currentInstance != null;
    }

    @Override // android.service.voice.VoiceInteractionService
    public void onReady() {
        try {
            super.onReady();
            AssistService assistService = this;
            setCurrentInstance(this);
        } catch (Exception e) {
            Log.e("AssistService", "Error during onReady()", e);
        }
    }

    @Override // android.service.voice.VoiceInteractionService
    public void onShutdown() {
        setCurrentInstance(null);
        super.onShutdown();
    }
}