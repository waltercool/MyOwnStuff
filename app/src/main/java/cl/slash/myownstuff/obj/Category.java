package cl.slash.myownstuff.obj;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waltercool on 7/8/14.
 */

@DatabaseTable(tableName = "category")
public class Category
{
    private List<Item> items;

    @DatabaseField(canBeNull = false)
    private String name;

    public Category(){}

    public Category(String name)
    {
        this.name = name;
        this.items = new ArrayList<Item>();
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }

    public void addItem(Item item)
    {
        this.items.add(item);
    }

    public String getName()
    {
        return name;
    }

    public List<Item> getItems()
    {
        return items;
    }
}
