package jss.bugtorch.mixins.early.minecraft.fix;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Random;

@Mixin(Entity.class)
public class MixinEntityItem {
    /**
     * @author Myask
     * @reason make items not bounce on stairs/cauldrons/etc. whose selection boxes are full, but collisions aren't
     * @param original o.return: whether the block has average selection bound box length >= 1
     * @return adjustment to ignore this for items
     */
    @ModifyExpressionValue(method = "func_145771_j",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;func_147469_q(III)Z", ordinal = 0))
    private boolean dontEjectBySelectionBox(boolean original) {
        return !((Object)this instanceof EntityItem);
    }
}
