package xyz.mrmelon54.WirelessRedstone.block.entity;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import xyz.mrmelon54.WirelessRedstone.WirelessRedstone;
import xyz.mrmelon54.WirelessRedstone.gui.WirelessFrequencyGuiDescription;

public class WirelessFrequencyBlockEntity<T extends WirelessFrequencyBlockEntity<T>> extends BlockEntity implements PropertyDelegateHolder, NamedScreenHandlerFactory {
    private int frequency;

    public WirelessFrequencyBlockEntity(BlockEntityType<T> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        frequency = 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("frequency", NbtType.INT)) frequency = nbt.getInt("frequency");
        else frequency = 0;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("frequency", frequency);
    }

    public int getFrequency() {
        return frequency;
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            if (index == 0) return getFrequency();
            return -1;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                frequency = value;
                if (world != null) WirelessRedstone.sendTickScheduleToReceivers(world);
            }
        }

        @Override
        public int size() {
            return 1;
        }
    };

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new WirelessFrequencyGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos));
    }
}
