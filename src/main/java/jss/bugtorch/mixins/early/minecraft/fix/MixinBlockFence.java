package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.block.BlockFence;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLead;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockFence.class)
public class MixinBlockFence {

    @Overwrite
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if(!worldIn.isRemote) {
            return ItemLead.func_150909_a(player, worldIn, x, y, z);
        }
        return false;
    }
}
