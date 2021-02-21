Stream.of(
Block.makeCuboidShape(7.5, 0, 11.5, 8.5, 2, 12.5),
Block.makeCuboidShape(6, 0, 3.5, 10, 3, 5.5),
Block.makeCuboidShape(5, 0, 5.5, 11, 4, 10.5),
Block.makeCuboidShape(6.5, 0, 10.5, 9.5, 3, 11.5)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});