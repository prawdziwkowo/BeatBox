package pl.it_developers.android.beatbox;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    private BeatBox beatBox;

    private Sound sound;
    private SoundViewModel subject;

    @Before
    public void setUp() throws Exception {
        beatBox = mock(BeatBox.class);
        sound = new Sound("assethPath");
        subject = new SoundViewModel(beatBox);
        subject.setSound(sound);
    }

    @Test
    public void exposesSoundNamesAsTitle() {
        assertThat(subject.getTitle(), is(sound.getName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked();
        verify(beatBox).play(sound);
    }
}