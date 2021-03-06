package net.torocraft.toroquest.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.torocraft.toroquest.ToroQuest;
import net.torocraft.toroquest.material.ToolMaterials;
@Mod.EventBusSubscriber
public class ItemObsidianSword extends ItemSword {

	public static ItemObsidianSword INSTANCE;

	public static final String NAME = "obsidian_sword";

	@SubscribeEvent
	public static void init(final RegistryEvent.Register<Item> event) {
		INSTANCE = new ItemObsidianSword();
		INSTANCE.setRegistryName(new ResourceLocation(ToroQuest.MODID, NAME));
		event.getRegistry().register(INSTANCE);
	}

	public static void registerRenders() {
		ModelResourceLocation model = new ModelResourceLocation(ToroQuest.MODID + ":" + NAME, "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(INSTANCE, 0, model);
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		renderItem.getItemModelMesher().register(INSTANCE, 0, new ModelResourceLocation(ToroQuest.MODID + ":" + NAME, "inventory"));
	}

	public ItemObsidianSword() {
		super(ToolMaterials.OBSIDIAN_MATERIAL);
		setUnlocalizedName(NAME);
	}

}
