package fr.evercraft.everkits;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import fr.evercraft.everapi.plugin.EChat;
import fr.evercraft.everapi.plugin.command.EParentCommand;
import fr.evercraft.everkits.ECMessage.ECMessages;

public class ECCommand extends EParentCommand<EverKits> {
	
	public ECCommand(final EverKits plugin) {
        super(plugin, "everkits");
    }
	
	@Override
	public boolean testPermission(final CommandSource source) {
		return source.hasPermission(EKPermissions.EVERKITS.get());
	}

	@Override
	public Text description(final CommandSource source) {
		return EChat.of(this.plugin.getService().replace(EKMessages.DESCRIPTION.get()));
	}

	@Override
	public boolean testPermissionHelp(final CommandSource source) {
		return source.hasPermission(EKPermissions.HELP.get());
	}
}
