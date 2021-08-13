package net.azagwen.atbyw.block;

import net.minecraft.util.StringIdentifiable;

public interface RedstonePipeComponent {

    enum ComponentType implements StringIdentifiable {
        SIMPLE("simple"),
        GATE("gate");

        private final String name;

        ComponentType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }
    }

    ComponentType getType();

    boolean isInverted();
}
