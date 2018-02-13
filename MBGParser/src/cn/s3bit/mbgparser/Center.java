package cn.s3bit.mbgparser;

import java.util.List;

import cn.s3bit.mbgparser.event.Event;
import static cn.s3bit.mbgparser.MBGUtils.*;

public class Center {
	public Position<Float> Position;
    public Motion<Float> Motion;

    public List<Event> Events;

    public static Center parseFromContent(String contentRaw)
    {
        if (contentRaw.equalsIgnoreCase("False"))
            return null;
        else
        {
            Center center = new Center();

            MRef<String> content = new MRef<String>(contentRaw);
            center.Position.x = Float.parseFloat(readString(content));
            center.Position.y = Float.parseFloat(readString(content));

            center.Motion.speed = Float.parseFloat(readString(content));
            center.Motion.speedDirection = Float.parseFloat(readString(content));

            center.Motion.acceleration = Float.parseFloat(readString(content));
            center.Motion.accelerationDirection = Float.parseFloat(readString(content));

            center.Events = null;
            if (!contentRaw.equals(""))
                center.Events = Event.parseEvents(readString(content));

            return center;
        }
    }
}
