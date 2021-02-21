Stream.of(
Block.makeCuboidShape(5.5, 12.25, 4.35, 7.5, 17.25, 5.35),
Block.makeCuboidShape(8.5, 12.25, 4.35, 10.5, 17.25, 5.35),
Block.makeCuboidShape(5.5, 8.25, 0.35, 10.5, 12.25, 5.35),
Block.makeCuboidShape(7.5, 9.75, -0.15, 8.5, 10.75, 0.85),
Block.makeCuboidShape(10, 0, 3.5, 12, 7, 5.5),
Block.makeCuboidShape(4, 0, 3.5, 6, 7, 5.5),
Block.makeCuboidShape(5, 2.4, 11, 11, 7.4, 14.5),
Block.makeCuboidShape(5, 3.75, 7.75, 11, 8.75, 11.25),
Block.makeCuboidShape(5, 5.1, 4.5, 11, 10.1, 8),
Block.makeCuboidShape(10, 0, 6, 12, 1, 13),
Block.makeCuboidShape(4, 0, 6, 6, 1, 13)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});