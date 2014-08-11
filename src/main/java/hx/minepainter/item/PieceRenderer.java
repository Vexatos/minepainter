package hx.minepainter.item;

import org.lwjgl.opengl.GL11;

import hx.minepainter.ModMinePainter;
import hx.minepainter.sculpture.SculptureBlock;
import hx.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class PieceRenderer implements IItemRenderer {
	private RenderItem renderItem = new RenderItem();
	private ItemStack is = new ItemStack(ModMinePainter.sculpture.block);
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY ||
				type == ItemRenderType.ENTITY ||
				type == ItemRenderType.EQUIPPED ||
				type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return type == ItemRenderType.ENTITY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		SculptureBlock sculpture = ModMinePainter.sculpture.block;
		PieceItem piece = Utils.getItem(item); 
		sculpture.setCurrentBlock(piece.getEditBlock(item), piece.getEditMeta(item));
		
		if(type == ItemRenderType.INVENTORY)
		{
			RenderHelper.enableGUIStandardItemLighting();
			renderItem.renderItemIntoGUI(
					Minecraft.getMinecraft().fontRenderer,
					Minecraft.getMinecraft().renderEngine, is, 0, 0);
		}else if(type == ItemRenderType.ENTITY)
		{
			EntityItem eis = (EntityItem)data[1];
			GL11.glScalef(.5f,.5f,.5f);
			mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			RenderBlocks rb = (RenderBlocks) (data[0]);
			rb.renderBlockAsItem(sculpture, 0, 1f);

		}else
		{
			Minecraft.getMinecraft().entityRenderer.itemRenderer.renderItem((EntityLivingBase) data[1],
					is, 0, type);
		}
		
		sculpture.setCurrentBlock(null, 0);
	}

}