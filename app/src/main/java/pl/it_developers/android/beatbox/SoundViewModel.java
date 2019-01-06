package pl.it_developers.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SoundViewModel extends BaseObservable {
    private Sound sound;
    private BeatBox beatBox;

    public SoundViewModel(BeatBox beatBox) {
        this.beatBox = beatBox;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
        notifyChange();
    }

    public BeatBox getBeatBox() {
        return beatBox;
    }

    @Bindable
    public String getTitle() {
        return sound.getName();
    }
}
