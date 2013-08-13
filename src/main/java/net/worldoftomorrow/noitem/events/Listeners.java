package net.worldoftomorrow.noitem.events;

import java.util.ArrayList;

import net.worldoftomorrow.noitem.Config;
import net.worldoftomorrow.noitem.NoItem;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class Listeners implements Listener {
	
	static ArrayList<Event> ev=null;
	public Listeners(){
		listenersReload();
	}
	
	public void listenersReload(){
		HandlerList.unregisterAll(NoItem.getInstance());
		NoItem.getInstance().getServer().getPluginManager().registerEvents(this, NoItem.getInstance());
		if(!Config.getBoolean("Events.PlayerPickupItem")) PlayerPickupItemEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerDropItem")) PlayerDropItemEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerItemHeld")) PlayerItemHeldEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerInteract")) PlayerInteractEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerInteractEntity")) PlayerInteractEntityEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.BlockBreak")) BlockBreakEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.BlockPlace")) BlockPlaceEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.InventoryClick")) InventoryClickEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.CraftItem")) CraftItemEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerDeath")) PlayerDeathEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerRespawn")) PlayerRespawnEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.EnchantItem")) EnchantItemEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.EntityDamageByEntity")) EntityDamageByEntityEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PlayerShearEntity")) PlayerShearEntityEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.BlockDispense")) BlockDispenseEvent.getHandlerList().unregister(this);
		if(!Config.getBoolean("Events.PrepareItemEnchant")) PrepareItemEnchantEvent.getHandlerList().unregister(this);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onItemPickup(PlayerPickupItemEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleItemPickup(event);
		Handlers.handleNoHavePickup(event);
		Handlers.handleNoHoldPickup(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onItemDrop(PlayerDropItemEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleItemDrop(event);
	}
	
	@EventHandler
	public void onItemHeld(PlayerItemHeldEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleItemHeld(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		//Handlers.handleInteract(event);
		Handlers.MihandlerInteractLR(event);
		//Handlers.handlerInteractLR(event);
		//Handlers.handleLRUseInteract(event);
		//Handlers.handleUseInteract(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleInteractEntity(event);
		Handlers.handleUseInteractEntity(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleBlockBreak(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handleBlockPlace(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getView().getPlayer().getWorld().getName())) return;
		Handlers.handleNoHoldInvClick(event);
		if(Config.getBoolean("Actions.BrewControl"))
			Handlers.handleNoBrewInvClick(event);
		Handlers.handleNoWearInvClick(event);
		if(Config.getBoolean("Actions.CookControl"))
			Handlers.handleNoCookInvClick(event);
	}

	@EventHandler(ignoreCancelled = true)
	public void onItemCraft(CraftItemEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getWhoClicked().getWorld().getName())) return;
		Handlers.handleItemCraft(event);
	}

	@EventHandler(ignoreCancelled = true)
	public void onDispense(BlockDispenseEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getBlock().getWorld().getName())) return;
		Handlers.handleBlockDispense(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEnchantment(PrepareItemEnchantEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getEnchantBlock().getWorld().getName())) return;
		Handlers.handlePrepareItemEnchant(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getEntity().getWorld().getName())) return;
		Handlers.handlePlayerDeath(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handlePlayerSpawn(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onItemEnchant(EnchantItemEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getEnchantBlock().getWorld().getName())) return;
		Handlers.handleEnchantItem(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getEntity().getWorld().getName())) return;
		Handlers.handlePlayerDamageEntity(event);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerShearEntity(PlayerShearEntityEvent event) {
		if(Config.getStringList("WorldsDisabled").contains(event.getPlayer().getWorld().getName())) return;
		Handlers.handlePlayerShearEntity(event);
	}
}
