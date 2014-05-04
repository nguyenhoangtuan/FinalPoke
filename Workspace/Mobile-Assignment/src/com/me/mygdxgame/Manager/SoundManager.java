package com.me.mygdxgame.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.me.mygdxgame.MyGdxGame;
import com.me.mygdxgame.Manager.LRU.CacheEntryRemovedListener;
import com.me.mygdxgame.Manager.SoundManager.MySound;

/**
 * Retrieve From
 * https://code.google.com/p/steigert-libgdx/source/browse/tags/post-20120709/
 * tyrian-game/src/com/blogspot/steigert/tyrian/services/SoundManager.java
 * 
 */
public class SoundManager implements CacheEntryRemovedListener<MySound, Sound>,
		Disposable {

	public enum MySound {
		BUTTONCLICK("data/Sound/buttonSound.mp3"),
		STEP("data/Sound/stepSound.wav");

		private final String fileName;

		private MySound(String fileName) {
			this.fileName = fileName;
		}

		public String getFileName() {
			return fileName;
		}
	}

	/**
	 * The volume to be set on the sound.
	 */
	private float volume = 1f;

	/**
	 * Whether the sound is enabled.
	 */
	private boolean enabled = true;

	/**
	 * The sound cache.
	 */
	private final LRU<MySound, Sound> soundCache;

	/**
	 * Constructor
	 */
	public SoundManager() {
		soundCache = new LRU<SoundManager.MySound, Sound>(10);
		soundCache.setEntryRemovedListener(this);
	}

	/**
	 * Play the chosen sound and stop whatever music is playing
	 * @param sound
	 */
	public void play(MySound sound) {
		if (!enabled)
			return;

		Sound soundToPlay = soundCache.get(sound);
		
		if (soundToPlay == null) {
			FileHandle soundFile = Gdx.files.internal(sound.getFileName());
			soundToPlay = Gdx.audio.newSound(soundFile);
			soundCache.add(sound, soundToPlay);
		}

		Gdx.app.log(MyGdxGame.LOG, "Playing sound: " + sound.name());
		soundToPlay.play(volume);
	}

	/**
	 * Adjusting sound volume
	 * @param volume
	 */
	public void setVolume(float volume) {
		Gdx.app.log(MyGdxGame.LOG, "Adjusting sound volume to: " + volume);

		if (volume < 0 || volume > 1f) {
			throw new IllegalArgumentException(
					"The volume must be inside the range: [0,1]");
		}
		this.volume = volume;
	}

	/**
	 * Dispose available sound
	 */
	@Override
	public void dispose() {
		Gdx.app.log(MyGdxGame.LOG, "Disposing sound manager");
		for (Sound sound : soundCache.retrieveAll()) {
			sound.stop();
			sound.dispose();
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Notify and remove current sound in cache
	 */
	@Override
	public void notifyEntryRemoved(MySound key, Sound value) {
		Gdx.app.log(MyGdxGame.LOG, "Disposing sound: " + key.name());
		value.dispose();
	}
}