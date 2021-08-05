package net.azagwen.atbyw.block.entity;

import net.azagwen.atbyw.main.AtbywNetworking;
import net.azagwen.atbyw.screen.TintingTableScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TintingTableBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
    private static final int[] DYE_SLOTS = new int[]{2, 3, 4};
    protected final PropertyDelegate propertyDelegate;
    private DefaultedList<ItemStack> inventory;
    private int redAmount;
    private int greenAmount;
    private int blueAmount;
    private int mode;
    private int color;

    public TintingTableBlockEntity(BlockPos pos, BlockState state) {
        super(AtbywBlockEntityTypes.TINTING_TABLE_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
        this.mode = TintingTableMode.HEX;
        this.color = Integer.decode("#FFFFFF");
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> TintingTableBlockEntity.this.redAmount;
                    case 1 -> TintingTableBlockEntity.this.greenAmount;
                    case 2 -> TintingTableBlockEntity.this.blueAmount;
                    case 3 -> TintingTableBlockEntity.this.mode;
                    case 4 -> TintingTableBlockEntity.this.color;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> TintingTableBlockEntity.this.redAmount = value;
                    case 1 -> TintingTableBlockEntity.this.greenAmount = value;
                    case 2 -> TintingTableBlockEntity.this.blueAmount = value;
                    case 3 -> TintingTableBlockEntity.this.mode = value;
                    case 4 -> TintingTableBlockEntity.this.color = value;
                }
                TintingTableBlockEntity.this.updateListeners();
            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, TintingTableBlockEntity blockEntity) {
        blockEntity.redAmount = blockEntity.tryRecharge(world, pos, state, blockEntity.redAmount, blockEntity.inventory.get(2), TintingTableFuels.RED);
        blockEntity.greenAmount = blockEntity.tryRecharge(world, pos, state, blockEntity.greenAmount, blockEntity.inventory.get(3), TintingTableFuels.GREEN);
        blockEntity.blueAmount = blockEntity.tryRecharge(world, pos, state, blockEntity.blueAmount, blockEntity.inventory.get(4), TintingTableFuels.BLUE);
        if (!blockEntity.getStack(0).isEmpty()) {
            var posX = pos.getX();
            var posY = pos.getY();
            var posZ = pos.getZ();
            var serverPlayer = (ServerPlayerEntity) world.getClosestPlayer(posX, posY, posZ, 1.0F, false);
            AtbywNetworking.sendServerPacket(serverPlayer, AtbywNetworking.TINTING_UPDATE_PACKET, PacketByteBufs::create);
        }
    }

    private int clampGauge(int gauge) {
        if (gauge < 0) {
            return 0;
        } else if (gauge > 256) {
            return 256;
        }
        return gauge;
    }

    private int tryRecharge(World world, BlockPos pos, BlockState state, int amount, ItemStack stack, TintingTableFuels fuel) {
        amount = this.clampGauge(amount);
        if (amount <= 192 && stack.isOf(fuel.getItem())) {
            stack.decrement(1);
            markDirty(world, pos, state);
            return amount + 64;
        }
        return amount;
    }

    public void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.tinting");
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        var inventoryIterator = this.inventory.iterator();
        var itemStack = ItemStack.EMPTY;
        do {
            if (!inventoryIterator.hasNext()) {
                return true;
            }
            itemStack = inventoryIterator.next();
        } while(itemStack.isEmpty());
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < this.inventory.size() ? this.inventory.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) {
            this.inventory.set(slot, stack);
        }

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return !(player.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) > 64.0D);
        }
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return switch (slot) {
            case 0 -> TintingTableScreenHandler.isValidIngredient(stack);
            case 2 -> stack.isOf(TintingTableFuels.RED.getItem());
            case 3 -> stack.isOf(TintingTableFuels.GREEN.getItem());
            case 4 -> stack.isOf(TintingTableFuels.BLUE.getItem());
            default -> false;
        };
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return side != Direction.UP ? DYE_SLOTS : new int[]{};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        this.redAmount = nbt.getByte("RedAmount");
        this.greenAmount = nbt.getByte("GreenAmount");
        this.blueAmount = nbt.getByte("BlueAmount");
        this.mode = nbt.getByte("Mode");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putByte("RedAmount", (byte) this.redAmount);
        nbt.putByte("GreenAmount", (byte) this.greenAmount);
        nbt.putByte("BlueAmount", (byte) this.blueAmount);
        nbt.putByte("Mode", (byte) this.mode);
        return nbt;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new TintingTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate, ScreenHandlerContext.create(world, pos));
    }
}
