package net.torocraft.toroquest.civilization;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.torocraft.toroquest.civilization.player.PlayerCivilizationCapabilityImpl;
import net.torocraft.toroquest.util.ToroGuiUtils;

public class CivilizationOverlayHandler {

	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onPostRenderOverlay(RenderGameOverlayEvent.Pre event) {
		ScaledResolution resolution = event.getResolution();
		int width = resolution.getScaledWidth();
		int height = resolution.getScaledHeight();
		EntityPlayerSP player = mc.thePlayer;
		Province civ = PlayerCivilizationCapabilityImpl.get(player).getPlayerInCivilization();

		if (event.getType() == ElementType.HEALTH) {
			//			if (civ != null)
			/*
						if (civ != null) {
							System.out.println(civ.civilization.toString());
						} else {
							System.out.println("Civ NULL");
						}
			*/
			drawCivilizationOverlay(width, height, civ);
		}
	}

	private void drawCivilizationOverlay(int width, int height, Province civ) {
		int left = width / 2 - 8;
		int top = height - 48;

		if (civ == null || civ.civilization == null) {
			return;
		}

		ToroGuiUtils.drawOverlayIcon(mc, left, top + 4, 0, 1);
		if (civ.civilization.equals(CivilizationType.EARTH)) {
			ToroGuiUtils.drawOverlayIcon(mc, left, top, 0, 0);
		} else if (civ.civilization.equals(CivilizationType.WIND)) {
			ToroGuiUtils.drawOverlayIcon(mc, left, top, 1, 0);
		} else if (civ.civilization.equals(CivilizationType.FIRE)) {
			ToroGuiUtils.drawOverlayIcon(mc, left, top, 2, 0);
		} else if (civ.civilization.equals(CivilizationType.MOON)) {
			ToroGuiUtils.drawOverlayIcon(mc, left, top, 3, 0);
		} else if (civ.civilization.equals(CivilizationType.SUN)) {
			ToroGuiUtils.drawOverlayIcon(mc, left, top, 4, 0);
		}
	}

}
