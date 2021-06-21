package net.azagwen.atbyw.mixin;

import net.azagwen.atbyw.main.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    private final ServerPlayerInteractionManager self = (ServerPlayerInteractionManager) (Object) this;

    @Inject(at = @At("TAIL"), method = "interactBlock", cancellable = true)
    public void useOnBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        var operations = DataResourceListener.BTB_OPS;
        var heldItem = player.getMainHandStack();
        var direction = hitResult.getSide();
        var pos = hitResult.getBlockPos();

        for (var operation : operations.cellSet()) {
            var itemUsed = operation.getRowKey();

            if (heldItem.getItem().equals(itemUsed)) {
                var itemDecrement = operation.getValue().decrement();
                var itemDamage = operation.getValue().damage();

                ItemOperations.replaceBlock(world, player, pos, direction, operation);
                cir.setReturnValue(ActionResult.success(world.isClient));

                if (!player.isCreative()) {
                    heldItem.damage(itemDamage, player, ((entity) -> entity.sendToolBreakStatus(hand)));
                    heldItem.decrement(itemDecrement);
                }
            }
        }
    }
}
