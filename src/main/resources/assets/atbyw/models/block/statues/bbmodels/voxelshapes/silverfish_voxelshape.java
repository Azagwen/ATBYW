Stream.of(
Block.makeCuboidShape(7.5, 0, 14.5, 8.5, 1, 16.5),
Block.makeCuboidShape(6.5, 0, -0.5, 9.5, 2, 1.5),
Block.makeCuboidShape(6, 0, 1.5, 10, 3, 3.5),
Block.makeCuboidShape(5, 0, 3.5, 11, 4, 6.5),
Block.makeCuboidShape(6.5, 0, 6.5, 9.5, 3, 9.5),
Block.makeCuboidShape(7, 0, 9.5, 9, 2, 12.5),
Block.makeCuboidShape(7, 0, 12.5, 9, 1, 14.5)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});