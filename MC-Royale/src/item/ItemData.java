package item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import main.Session;

public class ItemData {

	private final ItemStack itemStack;
	
	private ItemMeta meta;
	private PersistentDataContainer data;
	
	public ItemData(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	private void getDatas() {
		this.meta = this.itemStack.getItemMeta();
		if(this.meta != null) this.data = this.meta.getPersistentDataContainer();
	}
	
	private void saveDatas() {
		this.itemStack.setItemMeta(this.meta);
	}
	
	//String
	public void setString(String key, String value) {
		this.getDatas();
		this.data.set(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING, value);
		this.saveDatas();
	}
	public String getString(String key) {
		this.getDatas();
		return this.data.get(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING);
	}
	public boolean hasString(String key) {
		this.getDatas();
		return this.data.has(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING);
	}
	
	//Boolean
	public void setBoolean(String key, boolean value) {
		this.getDatas();
		this.data.set(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING, String.valueOf(value));
		this.saveDatas();
	}
	public boolean getBoolean(String key) {
		this.getDatas();
		return Boolean.valueOf(data.get(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING));
	}
	public boolean hasBoolean(String key) {
		this.getDatas();
		return this.data.has(new NamespacedKey(Session.getSession(), key), PersistentDataType.STRING);
	}
	
	//Integer
	public void setInteger(String key, int value) {
		this.getDatas();
		this.data.set(new NamespacedKey(Session.getSession(), key), PersistentDataType.INTEGER, value);
		this.saveDatas();
	}
	public int getInteger(String key) {
		this.getDatas();
		return this.data.get(new NamespacedKey(Session.getSession(), key), PersistentDataType.INTEGER);
	}
	public boolean hasInteger(String key) {
		this.getDatas();
		return this.data.has(new NamespacedKey(Session.getSession(), key), PersistentDataType.INTEGER);
	}
	
	//Double
	public void setDouble(String key, double value) {
		this.getDatas();
		this.data.set(new NamespacedKey(Session.getSession(), key), PersistentDataType.DOUBLE, value);
		this.saveDatas();
	}
	public double getDouble(String key) {
		this.getDatas();
		return this.data.get(new NamespacedKey(Session.getSession(), key), PersistentDataType.DOUBLE);
	}
	public boolean hasDouble(String key) {
		this.getDatas();
		return this.data.has(new NamespacedKey(Session.getSession(), key), PersistentDataType.DOUBLE);
	}
	
	//Float
	public void setFloat(String key, float value) {
		this.getDatas();
		this.data.set(new NamespacedKey(Session.getSession(), key), PersistentDataType.FLOAT, value);
		this.saveDatas();
	}
	public float getFloat(String key) {
		this.getDatas();
		return this.data.get(new NamespacedKey(Session.getSession(), key), PersistentDataType.FLOAT);
	}
	public boolean hasFloat(String key) {
		this.getDatas();
		return this.data.has(new NamespacedKey(Session.getSession(), key), PersistentDataType.FLOAT);
	}
}
