package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class ArmorListener implements Listener {

	@EventHandler
	public void onArmourEquip(InventoryClickEvent event) {
		if (event.getInventory().getType().equals(InventoryType.CRAFTING)) {

			Player p = Bukkit.getPlayer(event.getWhoClicked().getName());

			if (event.isShiftClick()) {
				if (event.getCurrentItem() != null) {
					int iid = event.getCurrentItem().getTypeId();

					if (Perms.NOWEAR.has(p, iid)) {
						event.setCancelled(true);
						StringHelper.notifyPlayer(p, EventTypes.WEAR, iid);
						StringHelper.notifyAdmin(p, EventTypes.WEAR,
								event.getCurrentItem());
					}
				}
			} else {
				if (event.getSlotType().equals(SlotType.ARMOR)) {
					if (event.getCursor() != null) {
						int iid = event.getCursor().getTypeId();

						if (Perms.NOWEAR.has(p, iid)) {
							StringHelper.notifyPlayer(p, EventTypes.WEAR, iid);
							StringHelper.notifyAdmin(p, EventTypes.WEAR,
									event.getCursor());
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
