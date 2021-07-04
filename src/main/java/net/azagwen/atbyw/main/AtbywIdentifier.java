package net.azagwen.atbyw.main;

import net.minecraft.util.Identifier;

public class AtbywIdentifier extends Identifier {

    public AtbywIdentifier(String path) {
        super(AtbywMain.atbywNamespace, path);
    }
}
