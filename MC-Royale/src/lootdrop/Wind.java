package lootdrop;

import org.bukkit.scheduler.BukkitRunnable;

import main.Session;

public class Wind extends BukkitRunnable {

	public float x;
	public float z;
	
	public Wind() {
		this.x = 0;
		this.z = 0;
	}
	
	@Override
	public void run() {
		x += Session.getSession().random.nextFloat() * ((Session.getSession().random.nextInt(2) == 0) ? 0.1 : -0.1);
    	z += Session.getSession().random.nextFloat() * ((Session.getSession().random.nextInt(2) == 0) ? 0.1 : -0.1);
	}
}