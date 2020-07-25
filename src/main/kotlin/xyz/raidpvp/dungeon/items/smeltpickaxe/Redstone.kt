package dungeon.RpgItems.SmeltPickaxe

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class Redstone : Listener {

    @EventHandler
    fun onInteract(e: BlockBreakEvent) {
        val block = e.block
        val player = e.player

        if (block.type != Material.REDSTONE_ORE || player.itemInHand == null)
            return

        if (player.itemInHand.type != Material.DIAMOND_PICKAXE ||
                !player.itemInHand.hasItemMeta() || player.itemInHand.itemMeta.displayName == null ||
                player.itemInHand.itemMeta.displayName != "§e§lSmelt Pickaxe"
        )
            return

        e.isCancelled = true

        val blocks = getBlocksOfSC(block)
        val iterator = blocks.listIterator(blocks.size)

        while (iterator.hasPrevious()) {
            val listElement = iterator.previous()
            listElement.type = Material.AIR
        }

        player.inventory.addItem(ItemStack(Material.REDSTONE, blocks.size))


    }

    private fun getBlocksOfSC(block: Block): List<Block> {
        val blocks = ArrayList<Block>()
        val loc = block.location.clone().add(0.0, 1.0, 0.0)

        blocks.add(block)

        while (loc.block.type == Material.REDSTONE_ORE) {
            blocks.add(loc.block)
            loc.add(0.0, 1.0, 0.0)
        }

        return blocks

    }

}