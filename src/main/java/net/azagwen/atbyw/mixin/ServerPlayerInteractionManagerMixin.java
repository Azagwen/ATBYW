package net.azagwen.atbyw.mixin;

import com.google.gson.JsonSyntaxException;
import net.azagwen.atbyw.main.BlockToBlockOperation;
import net.azagwen.atbyw.main.DataResourceListener;
import net.azagwen.atbyw.main.ItemOperations;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.registry.Registry;
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
        var operations = DataResourceListener.BLOCK_TO_BLOCK_OPERATIONS;
        var heldItem = player.getMainHandStack();

        for (var operation : operations) {
            var expectedItem = operation.getUsedItem();

            if (expectedItem instanceof Item) {
                if (heldItem.getItem().equals(expectedItem)) {
                    executeOperation(player, world, hand, hitResult, operation);
                    cir.setReturnValue(ActionResult.success(world.isClient));
                }
            } else {
                var tag = ServerTagManagerHolder.getTagManager().getTag(Registry.ITEM_KEY, ((Identifier) expectedItem), (identifier) -> {
                    return new JsonSyntaxException("Unknown item tag '" + identifier + "'");
                });

                for (var item : tag.values()) {
                    if (heldItem.getItem().equals(item)) {
                        executeOperation(player, world, hand, hitResult, operation);
                        cir.setReturnValue(ActionResult.success(world.isClient));
                    }
                }
            }
        }
    }

    private void executeOperation(ServerPlayerEntity player, World world, Hand hand, BlockHitResult hitResult, BlockToBlockOperation operation) {
        var heldItem = player.getMainHandStack();
        var pos = hitResult.getBlockPos();
        var direction = hitResult.getSide();
        var client = MinecraftClient.getInstance();

        ItemOperations.replaceBlock(world, player, pos, direction, operation);
        client.getNetworkHandler().sendPacket(new PlayerInteractBlockC2SPacket(hand, hitResult));

        if (operation.getUsedItemDamage() != 0) {
            heldItem.damage(operation.getUsedItemDamage(), player, ((entity) -> entity.sendToolBreakStatus(hand)));
        }
        if (operation.getUsedItemDecrement() != 0) {
            heldItem.decrement(operation.getUsedItemDecrement());
        }
    }
}
