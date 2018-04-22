package cn.s3bit.th902;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class MusicManager {
	public static Music music = null;
	public static void PlayBGM(FileHandle handle, boolean loop) {
		if (music != null) {
			music.stop();
			music.dispose();
		}
		music = Gdx.audio.newMusic(handle);
		music.setLooping(loop);
		music.play();
	}
	public static void PauseBGM() {
		if (music != null) music.pause();
	}
	public static void StopBGM() {
		if (music != null) music.stop();
	}
}
