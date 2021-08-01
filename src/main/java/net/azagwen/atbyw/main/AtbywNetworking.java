package net.azagwen.atbyw.main;

import net.azagwen.atbyw.item.SimpleColoredItem;
import net.azagwen.atbyw.screen.TintingTableScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.DyeableItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class AtbywNetworking {
    public static final Identifier TINTING_COLOR_PACKET = AtbywMain.id("tinting_table_color_packet");
    public static final Identifier TINTING_MODE_PACKET = AtbywMain.id("tinting_table_mode_packet");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TINTING_COLOR_PACKET, (server, player, handler, buf, sender) -> {
            int color = buf.readVarInt();
            server.execute(() -> {
                if (player.currentScreenHandler instanceof TintingTableScreenHandler sc) {
                    sc.setColor(color);
                    if (sc.slots.get(1).hasStack()) {
                        var stack = sc.slots.get(1).getStack();
                        if (stack.getItem() instanceof DyeableItem dyeableItem) {
                            dyeableItem.setColor(stack, color);
                        }
                        if (stack.getItem() instanceof SimpleColoredItem coloredItem) {
                            coloredItem.setColor(stack, color);
                        }
                        sc.slots.get(1).setStack(stack);
                    }
                }
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(TINTING_MODE_PACKET, (server, player, handler, buf, sender) -> {
            int mode = buf.readVarInt();
            server.execute(() -> {
                if (player.currentScreenHandler instanceof TintingTableScreenHandler sc) {
                    sc.setMode(mode);
                }
            });
        });
    }

    public static void sendClientPacket(Identifier packetId, Supplier<PacketByteBuf> buf) {
        ClientPlayNetworking.send(packetId, buf.get());
    }
}
