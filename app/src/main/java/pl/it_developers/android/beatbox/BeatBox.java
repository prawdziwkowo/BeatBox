package pl.it_developers.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager assetManager;
    private List<Sound> sounds = new ArrayList<>();

    private SoundPool soundPool;

    public BeatBox(@NonNull Context context) {
        assetManager = context.getAssets();
        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }

        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = assetManager.list(SOUND_FOLDER);

            Log.i(TAG, "Znaleziono " + soundNames.length + " dźwięków");
        } catch (IOException e) {
            Log.e(TAG, "Nie można wyświetlić listy dźwięków", e);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                sounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Nie mogę załadować pliku " + filename, e);
            }
        }
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor adf = assetManager.openFd(sound.getAssetPath());
        int soundId = soundPool.load(adf, 1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void release() {
        soundPool.release();
    }
}
