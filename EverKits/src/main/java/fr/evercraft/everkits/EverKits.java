package fr.evercraft.everkits;

import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import fr.evercraft.everapi.event.ChatSystemEvent;
import fr.evercraft.everapi.plugin.EPlugin;
import fr.evercraft.everapi.services.ChatService;
import fr.evercraft.everkits.command.sub.ECReload;
@Plugin(id = "fr.evercraft.everkits", 
		name = "EverKits", 
		version = "1.2", 
		description = "Manage Kit",
		url = "http://evercraft.fr/",
		authors = {"rexbut"},
		dependencies = {
		    @Dependency(id = "fr.evercraft.everapi", version = "1.2")
		})
public class EverKits extends EPlugin {
	private ECConfig configs;
	
	private ECMessage messages;
	
	@Override
	protected void onPreEnable() {		
		this.configs = new ECConfig(this);
		
		this.messages = new ECMessage(this);
		
		this.getGame().getEventManager().registerListeners(this, new ECListener(this));
	}
	
	@Override
	protected void onCompleteEnable() {
		ECCommand command = new ECCommand(this);
		
		command.add(new ECReload(this, command));
	}

	protected void onReload(){
		this.reloadConfigurations();
	}
	
	protected void onDisable() {
	}
	}

	/*
	 * Accesseurs
	 */
	
	public ECMessage getMessages(){
		return this.messages;
	}
	
	public ECConfig getConfigs() {
		return this.configs;
	}
}
