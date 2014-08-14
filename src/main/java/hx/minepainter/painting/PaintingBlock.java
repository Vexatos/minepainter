package hx.minepainter.painting;

import hx.minepainter.ModMinePainter;
import hx.utils.Utils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO add a block placer item for this! 
public class PaintingBlock extends BlockContainer{

	public PaintingBlock() {
		super(Material.cloth);
		this.setBlockTextureName("minepainter:palette");
	}

    @Override public void registerBlockIcons(IIconRegister register){}

	@Override public TileEntity createNewTileEntity(World var1, int var2) {
		return new PaintingEntity();
	}
	
	@Override public boolean isOpaqueCube()
	{
		return false;
	}

    @Override public boolean renderAsNormalBlock()
	{
		return false;
	}

    @Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }
    
    @Override public void setBlockBoundsBasedOnState(IBlockAccess iba,int x,int y,int z) {
    	PaintingPlacement placement = PaintingPlacement.of(iba.getBlockMetadata(x, y, z));
    	placement.setBlockBounds(this);
    }
    
    @Override public void setBlockBoundsForItemRender() {
    	this.setBlockBounds(0, 0, 0, 1, 1, 1);
    }
    
    @Override public int getRenderType(){
    	return -1;
    }
}
