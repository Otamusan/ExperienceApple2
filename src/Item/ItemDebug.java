package Item;

import Item.Tools.ItemMultiTool;
import Item.Tools.ToolFunction;

public class ItemDebug extends ItemMultiTool {

	public ItemDebug() {
		super(ToolMaterial.DIAMOND, new ToolFunction(true, true, true, true));
	}
}
