package fr.evercraft.everkits;

import java.util.Optional;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.serializer.TextSerializers;

import fr.evercraft.everapi.server.player.EPlayer;

public class ECListener {
	private EverKits plugin;
	
	public ECListener(final EverKits plugin) {
		this.plugin = plugin;
	}
}
