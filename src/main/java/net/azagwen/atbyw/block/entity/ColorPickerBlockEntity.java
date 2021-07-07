package net.azagwen.atbyw.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class ColorPickerBlockEntity extends BlockEntity {
    private int red;
    private int green;
    private int blue;

    public ColorPickerBlockEntity(BlockPos pos, BlockState state) {
        super(AtbywBlockEntityTypes.COLOR_PICKER_ENTITY, pos, state);
    }

    public ColorPickerBlockEntity(BlockPos pos, BlockState state, Color color) {
        this(pos, state);
        this.setColor(color);
    }

    public Color getColor() {
        return new Color(this.red, this.green, this.blue);
    }

    public void setColor(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.red = nbt.getShort("color_red");
        this.green = nbt.getShort("color_green");
        this.blue = nbt.getShort("color_blue");
        System.out.println("{" + this.red + ", " + this.green + ", " + this.blue + "}");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("color_red", this.red);
        nbt.putInt("color_green", this.green);
        nbt.putInt("color_blue", this.blue);
        return nbt;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.writeNbt(new NbtCompound());
    }
}
