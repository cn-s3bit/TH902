package cn.s3bit.th902.tests;

import cn.s3bit.th902.gamecontents.Entity;
import cn.s3bit.th902.gamecontents.components.Component;

public final class TestEntityComponent {
	
	public static class TestComponent extends Component {

		@Override
		public void Update() {
			
		}
		
	}
	
	public static class TestComponentExtended extends TestComponent {
		
	}

	public static void main(String[] args) {
		Entity entity = Entity.Create();
		System.out.println(null == entity.GetComponent(TestComponentExtended.class));
		System.out.println(null == entity.GetComponent(TestComponent.class));
		entity.AddComponent(new TestComponentExtended());
		System.out.println(null != entity.GetComponent(TestComponentExtended.class));
		System.out.println(null != entity.GetComponent(TestComponent.class));
	}

}
