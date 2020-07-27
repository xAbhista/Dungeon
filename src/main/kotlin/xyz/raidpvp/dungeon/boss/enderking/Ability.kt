package xyz.raidpvp.dungeon.boss.enderking

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class Ability : Listener {
    @EventHandler
    fun onDamage(e: EntityDamageByEntityEvent) {
        if (e.entity is Enderman && e.damager is Player) {
            if (e.entity.hasMetadata("Ender King")) {
                val player = e.damager as Player
                val endermans = e.entity as Enderman
                // Ability #1: Block Attack
                val rand = Random()
                val random = rand.nextInt(100)
                if (random < 5) { // 5 percent chance
                    e.isCancelled = true
                    player.playSound(player.location, Sound.valueOf("ANVIL_LAND"), 10f, 10f)
                    player.sendMessage("§c§lYour attack was blocked!")
                    endermans.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, Int.MAX_VALUE, 4, true, false))
                }

                // Ability #2: SpawnMinion(s)
                val random2 = rand.nextInt(100)
                if (random2 < 25) {
                    val helmet = ItemStack(Material.DIAMOND_HELMET)
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val chestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val leggings = ItemStack(Material.DIAMOND_LEGGINGS)
                    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val boots = ItemStack(Material.DIAMOND_BOOTS)
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)

                    val minion = e.damager.world.spawnEntity(e.damager.location.add(0.5, 0.0, 0.5), EntityType.ZOMBIE) as Zombie
                    minion.setMetadata("Minion")
                    minion.isBaby
                    minion.customName = "§7Ender §dKing's Minion"
                    minion.isCustomNameVisible = true
                    minion.target = player
                    minion.equipment.boots = boots
                    minion.equipment.helmet = helmet
                    minion.equipment.chestplate = chestplate
                    minion.equipment.leggings = leggings
                    minion.health = 200.0
                }

                // Ability #3: SpawnWarrior(s)

                val random3 = rand.nextInt(100)
                if (random3 < 10) {
                    val helmet = ItemStack(Material.DIAMOND_HELMET)
                    helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val chestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
                    chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val leggings = ItemStack(Material.DIAMOND_LEGGINGS)
                    leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                    val boots = ItemStack(Material.DIAMOND_BOOTS)
                    boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4)
                    val sword = ItemStack(Material.DIAMOND_SWORD)
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 2)

                    val warrior = e.damager.world.spawnEntity(e.damager.location.add(0.5, 0.0, 0.5), EntityType.PIG_ZOMBIE) as PigZombie
                    warrior.customName = "§7Ender §dKing's Warrior"
                    warrior.isCustomNameVisible = true
                    warrior.target = player
                    warrior.isAngry = true
                    warrior.equipment.boots = boots
                    warrior.equipment.chestplate = chestplate
                    warrior.equipment.leggings = leggings
                    warrior.equipment.boots = boots
                    warrior.equipment.itemInMainHand = sword
                    warrior.health = 450.0
                    warrior.setMetadata("Warrior")
                    warrior.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Int.MAX_VALUE , 2, true, false))
                    warrior.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Int.MAX_VALUE , 2, true, false))
                }

                // Ability #4: SpawnGuard(s)

                val random4 = rand.nextInt( 100)
                if (random4 < 2.5) {

                    val guard = e.damager.world.spawnEntity(e.damager.location.add(0.5, 0.0, 0.5), EntityType.IRON_GOLEM) as IronGolem
                    guard.customName = "§7Ender §dKing's Guard"
                    guard.isCustomNameVisible = true
                    guard.target = player
                    guard.health = 1000.0
                    guard.setMetadata("Guard")
                    guard.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, Int.MAX_VALUE , 2, true, false))
                    guard.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Int.MAX_VALUE , 2, true, false))
                }

                val random5 = rand.nextInt(100)
                if (random5 < 0.5) {
                    val boss = e.entity as Enderman
                    val player = e.damager as Player
                    boss.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 30, 5, true, true))
                    player.sendMessage("§cThe §7Ender §dKing §chas been regenerated!")
                }
            }
        }
    }
}

