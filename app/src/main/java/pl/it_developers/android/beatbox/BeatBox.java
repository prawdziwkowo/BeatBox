package pl.it_developers.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";

    private AssetManager assetManager;
    private List<Sound> sounds = new ArrayList<>();

    public BeatBox(Context context) {
        assetManager = context.getAssets();
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = assetManager.list(SOUND_FOLDER);
            for (String filename : soundNames) {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                sounds.add(sound);
            }

            Log.i(TAG, "Znaleziono " + soundNames.length + " dźwięków");
        } catch (IOException e) {
            Log.e(TAG, "Nie można wyświetlić listy dźwięków", e);
        }
    }

    public List<Sound> getSounds() {
        return sounds;
    }
}
