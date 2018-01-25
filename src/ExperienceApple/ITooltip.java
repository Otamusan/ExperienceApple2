package ExperienceApple;

import java.util.ArrayList;
import java.util.List;

public interface ITooltip {
	public List<String> Tooltip = new ArrayList<String>();

	default List<String> getTooltip() {
		return Tooltip;
	}

	default void addTooltip(String str) {
		Tooltip.add(str);
	}
}
