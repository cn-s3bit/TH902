package cn.s3bit.th902.gamecontents.components;

import com.badlogic.gdx.math.Vector2;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.utils.ImmutableWrapper;

/**
 * @author Obsidianss
 * Stores position, rotation and scale.
 */
public class Transform extends Component {
	/**
	 * Position. Should be the center of the entity.
	 */
	public final Vector2 position;
	/**
	 * Rotation, in degrees.
	 */
	public float rotation;
	public final Vector2 scale;
	public final ImmutableWrapper<Vector2> immutablePosition, immutableScale;
	/**
	 * Constructor method.
	 * @param pos Initial position
	 * @param rot Initial rotation
	 * @param sca Initial scale
	 */
	public Transform(Vector2 pos, float rot, Vector2 sca) {
		position = pos;
		rotation = rot;
		scale = sca;
		immutablePosition = ImmutableWrapper.wrap(position);
		immutableScale = ImmutableWrapper.wrap(scale);
	}
	/**
	 * Constructor method. Rotation is set to 0.
	 * @param pos Initial position
	 * @param sca Initial scale
	 */
	public Transform(Vector2 pos, Vector2 sca) {
		this(pos, 0f, sca);
	}
	/**
	 * Constructor method. Scale is set to 1 * 1.
	 * @param pos Initial position
	 * @param rot Initial rotation
	 */
	public Transform(Vector2 pos, float rot) {
		this(pos, rot, new Vector2(1, 1));
	}
	/**
	 * Constructor method. Rotation is set to 0 and scale is set to 1 * 1.
	 * @param pos Initial position
	 */
	public Transform(Vector2 pos) {
		this(pos, 0f, new Vector2(1, 1));
	}
	

	@Override
	public void Initialize(Entity entity) {
		
	}

	@Override
	public void Update() {
		
	}
}
