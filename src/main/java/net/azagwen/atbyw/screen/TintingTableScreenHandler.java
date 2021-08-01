package net.azagwen.atbyw.screen;

import net.azagwen.atbyw.block.entity.TintingTableFuels;
import net.azagwen.atbyw.block.entity.TintingTableMode;
import net.azagwen.atbyw.item.CanvasBlockItem;
import net.azagwen.atbyw.item.SimpleColoredItem;
import net.azagwen.atbyw.main.AtbywScreenHandlerType;
import net.azagwen.atbyw.main.AtbywStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class TintingTableScreenHandler extends ScreenHandler {
    private final Inventory dyeInventory;
    private final CraftingResultInventory output;
    protected final Inventory input;
    private final PropertyDelegate propertyDelegate;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;
    private final Slot ingredientSlot;
    private final Slot outputSlot;
    private final Slot redSlot;
    private final Slot greenSlot;
    private final Slot blueSlot;
    private int color;
    private int mode;

    public TintingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(5), ScreenHandlerContext.EMPTY);
    }

    public TintingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(AtbywScreenHandlerType.TINTING_TABLE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 5);
        checkDataCount(propertyDelegate, 5);
        this.input = new SimpleInventory(1) {
            public void markDirty() {
                super.markDirty();
                TintingTableScreenHandler.this.onContentChanged(this);
            }
        };
        this.dyeInventory = inventory;
        this.output = new CraftingResultInventory();
        this.propertyDelegate = propertyDelegate;
        this.context = context;
        this.player = playerInventory.player;
        this.ingredientSlot = this.addSlot(new IngredientSlot(this.input, 0, 142, 14));
        this.outputSlot = this.addSlot(new OutputSlot(this.output, 1, 142, 51));
        this.redSlot = this.addSlot(new DyeSlot(inventory, 2, 181, 84, TintingTableFuels.RED));
        this.greenSlot = this.addSlot(new DyeSlot(inventory, 3, 181, 111, TintingTableFuels.GREEN));
        this.blueSlot = this.addSlot(new DyeSlot(inventory, 4, 181, 138, TintingTableFuels.BLUE));
        this.addProperties(propertyDelegate);

        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, (x + (y * 9) + 9), (8 + (x * 18)), (84 + (y * 18))));
            }
        }

        for(int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInventory, x, (8 + (x * 18)), 142));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.dyeInventory.canPlayerUse(player);
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        this.input.setStack(0, ItemStack.EMPTY);
        if (stack.getItem() instanceof CanvasBlockItem) {
            player.increaseStat(AtbywStats.COLOR_CANVAS_BLOCK, 1);
        }
    }

    protected static void updateResult(TintingTableScreenHandler handler, World world, PlayerEntity player, Inventory inputInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient){
            var inputStack = inputInventory.getStack(0);
            var serverPlayerEntity = (ServerPlayerEntity) player;
            var outputStack = ItemStack.EMPTY;
            if (!inputStack.isEmpty()) {
                outputStack = inputStack.copy();
            }

            //the Color setter is located in the screen class of this block
            //(because I couldn't get it to set without doing this, thank you Mojank)

            resultInventory.setStack(0, outputStack);
            handler.setPreviousTrackedSlot(1, outputStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 1, outputStack));
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        this.context.run((world, blockPos) -> {
            updateResult(this, world, this.player, this.input, this.output);
        });
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, this.input);
        });
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        var slotStackCopy = ItemStack.EMPTY;
        var slot = this.slots.get(index);
        if (slot.hasStack()) {
            var slotStack = slot.getStack();
            slotStackCopy = slotStack.copy();
            if (index < 0 || index > 5) {
                if (this.redSlot.canInsert(slotStack)) {
                    if (!this.insertItem(slotStack, 2, 3, false) || this.ingredientSlot.canInsert(slotStack) && !this.insertItem(slotStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.greenSlot.canInsert(slotStack)) {
                    if (!this.insertItem(slotStack, 3, 4, false) || this.ingredientSlot.canInsert(slotStack) && !this.insertItem(slotStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.blueSlot.canInsert(slotStack)) {
                    if (!this.insertItem(slotStack, 4, 5, false) || this.ingredientSlot.canInsert(slotStack) && !this.insertItem(slotStack, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.ingredientSlot.canInsert(slotStack)) {
                    if (!this.insertItem(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 5 && index < 32) {
                    if (!this.insertItem(slotStack, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 32 && index < 41) {
                    if (!this.insertItem(slotStack, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(slotStack, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(slotStack, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(slotStack, slotStackCopy);
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (slotStack.getCount() == slotStackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, slotStack);
        }

        return slotStackCopy;
    }

    public int getRedAmount() {
        return this.propertyDelegate.get(0);
    }

    public int getGreenAmount() {
        return this.propertyDelegate.get(1);
    }

    public int getBlueAmount() {
        return this.propertyDelegate.get(2);
    }

    public void setMode(TintingTableMode mode) {
        this.mode = mode.getId();
        this.propertyDelegate.set(3, this.mode);
    }

    public TintingTableMode getMode() {
        return TintingTableMode.values()[this.mode];
    }

    public Color getColor() {
        return new Color(this.color);
    }

    public static boolean isValidIngredient(ItemStack stack) {
        return stack.getItem() instanceof SimpleColoredItem || stack.getItem() instanceof DyeableItem;
    }

    private static class IngredientSlot extends Slot {

        public IngredientSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return isValidIngredient(stack);
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }
    }

    private class OutputSlot extends Slot {

        public OutputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            TintingTableScreenHandler.this.onTakeOutput(player, stack);
        }
    }

    private static class DyeSlot extends Slot {
        public final TintingTableFuels dye;

        public DyeSlot(Inventory inventory, int index, int x, int y, TintingTableFuels dye) {
            super(inventory, index, x, y);
            this.dye = dye;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.isOf(dye.getItem());
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }
    }
}
