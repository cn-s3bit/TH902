package cn.s3bit.th902.gamecontents;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.DataInput;
import com.badlogic.gdx.utils.DataOutput;

public class ReplaySystem {
	
	public static class ReplayDataBlockOutput extends DataOutput {
		private final ByteArrayOutputStream mBuffer;

		public ReplayDataBlockOutput() {
			super(new ByteArrayOutputStream());
			this.mBuffer = (ByteArrayOutputStream) super.out;
		}
		
		public byte[] getData() {
			return this.mBuffer.toByteArray();
		}
		
	}

	public static class ReplayDataBlockInput extends DataInput {

		public ReplayDataBlockInput(byte[] data) {
			super(new ByteArrayInputStream(data));
		}
		
	}

	public static class ReplayDataBlock {
		private ReplayDataBlockInput mBlockInput = null;
		private ReplayDataBlockOutput mBlockOutput = new ReplayDataBlockOutput();
		private Boolean stateIsReader = null;
		
		public ReplayDataBlockInput reader() {
			if (stateIsReader == null || !stateIsReader)
				throw new IllegalStateException();
			return mBlockInput;
		}
		
		public ReplayDataBlockOutput writer() {
			if (stateIsReader != null && stateIsReader)
				throw new IllegalStateException();
			stateIsReader = false;
			return mBlockOutput;
		}
		
		public void loadData(byte[] data) {
			mBlockInput = new ReplayDataBlockInput(data);
			stateIsReader = true;
		}
	}
	
	public static ReplayDataBlock meta, playerAction, mbgRandom;
	public static boolean replayMode = false;
	
	public static void loadData(FileHandle handle) throws IOException {
		loadData(handle.readBytes());
	}
	
	public static void loadData(byte[] data) throws IOException {
		DataInput dataInput = new DataInput(new ByteArrayInputStream(data));
		meta = new ReplayDataBlock();
		meta.loadData(Base64.getDecoder().decode(dataInput.readString()));
		playerAction = new ReplayDataBlock();
		playerAction.loadData(Base64.getDecoder().decode(dataInput.readString()));
		mbgRandom = new ReplayDataBlock();
		mbgRandom.loadData(Base64.getDecoder().decode(dataInput.readString()));
		dataInput.close();
	}
	
	public static void startRecording() {
		meta = new ReplayDataBlock();
		playerAction = new ReplayDataBlock();
		mbgRandom = new ReplayDataBlock();
	}
	
	public static void writeToFile(FileHandle handle) throws IOException {
		DataOutput dataOutput = new DataOutput(handle.write(false));
		dataOutput.writeString(Base64.getEncoder().encodeToString(meta.writer().getData()));
		dataOutput.writeString(Base64.getEncoder().encodeToString(playerAction.writer().getData()));
		dataOutput.writeString(Base64.getEncoder().encodeToString(mbgRandom.writer().getData()));
		dataOutput.close();
	}
}
