package cl.slash.myownstuff.obj;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by waltercool on 7/8/14.
 */

@DatabaseTable(tableName = "item")
public class Item
{
    @DatabaseField(canBeNull = false)
    private String itemName;

    @DatabaseField
    private String desc;

    public Item(){}

    public Item(String itemName, String desc)
    {
        this.itemName = itemName;
        this.desc = desc;
    }


}
