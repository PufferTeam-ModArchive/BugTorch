package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemSlab.class)
public abstract class MixinItemSlab extends ItemBlock {

    @Shadow
    private boolean field_150948_b;
    @Shadow
    private BlockSlab field_150949_c;
    @Shadow
    private BlockSlab field_150947_d;

    @Shadow
    public boolean func_150936_a(World p_150936_1_, int p_150936_2_, int p_150936_3_, int p_150936_4_, int p_150936_5_, EntityPlayer p_150936_6_, ItemStack p_150936_7_)
    {
        return false;
    }
    @Shadow
    private boolean func_150946_a(ItemStack p_150946_1_, EntityPlayer p_150946_2_, World p_150946_3_, int p_150946_4_, int p_150946_5_, int p_150946_6_, int p_150946_7_)
    {
        return false;
    }

    public MixinItemSlab(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    /**
     * @author John
     * @reason Fix Slab Ghost Right Click
     */
    @Overwrite
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (this.field_150948_b)
        {
            return super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
        }
        else if (p_77648_1_.stackSize == 0)
        {
            return false;
        }
        else if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
            Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
            int i1 = p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);
            int j1 = i1 & 7;
            boolean flag = (i1 & 8) != 0;

            if ((p_77648_7_ == 1 && !flag || p_77648_7_ == 0 && flag) && block == this.field_150949_c && j1 == p_77648_1_.getItemDamage())
            {
                if (p_77648_3_.checkNoEntityCollision(this.field_150947_d.getCollisionBoundingBoxFromPool(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_)) && p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, this.field_150947_d, j1, 3))
                {
                    p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), this.field_150947_d.stepSound.func_150496_b(), (this.field_150947_d.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150947_d.stepSound.getPitch() * 0.8F);
                    --p_77648_1_.stackSize;
                    return true;
                }
                return false;
            }
            else
            {
                return this.func_150946_a(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_) ? true : super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
            }
        }
    }

}
