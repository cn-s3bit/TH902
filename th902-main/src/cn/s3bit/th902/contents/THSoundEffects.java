package cn.s3bit.th902.contents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum THSoundEffects {
	Bonus("bonus"),
	Bonus2("bonus2"),
	Cancel("cancel00"),
	Cat("cat00"),
	Damage("damage00"),
	Enep0("enep00"),
	Enep1("enep01"),
	Extend("extend"),
	Graze("graze"),
	Gun("gun00"),
	Item("item00"),
	Kira0("kira00"),
	Kira1("kira01"),
	Kira2("kira02"),
	Laser0("lazer00"),
	Laser1("lazer01"),
	MorisaBomb("nep00"),
	OK("ok00"),
	Pause("pause"),
	Biu("pldead00"),
	PlShoot("plst00"),
	Power0("power0"),
	Power1("power1"),
	PowerUp("powerup"),
	Select("select00"),
	Tan0("tan00"),
	Tan1("tan01"),
	Tan2("tan02"),
	Timeout("timeout");
	static final String PATH_SE_PREFIX = "resources/SE/se_";
	public final Sound sound;
	private THSoundEffects(String filename) {
		sound = Gdx.audio.newSound(Gdx.files.internal(PATH_SE_PREFIX + filename + ".mp3"));
	}
}
