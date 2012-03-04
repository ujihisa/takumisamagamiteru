(ns takumisamagamiteru.core
  (:require [swank.swank])
  (:require [clojure.string :as s])
  (:import [org.bukkit Bukkit])
  (:import [org.bukkit.entity Animals Arrow Blaze Boat CaveSpider Chicken
            ComplexEntityPart ComplexLivingEntity Cow Creature Creeper Egg
            EnderCrystal EnderDragon EnderDragonPart Enderman EnderPearl
            EnderSignal ExperienceOrb Explosive FallingSand Fireball Fish
            Flying Ghast Giant HumanEntity Item LightningStrike LivingEntity
            MagmaCube Minecart Monster MushroomCow NPC Painting Pig PigZombie
            Player PoweredMinecart Projectile Sheep Silverfish Skeleton Slime
            SmallFireball Snowball Snowman Spider Squid StorageMinecart
            ThrownPotion TNTPrimed Vehicle Villager WaterMob Weather Wolf
            Zombie])
  (:import [org.bukkit.event.entity CreatureSpawnEvent CreeperPowerEvent
            EntityChangeBlockEvent
            EntityCombustByBlockEvent EntityCombustByEntityEvent
            EntityCombustEvent EntityCreatePortalEvent EntityDamageByBlockEvent
            EntityDamageByEntityEvent
            EntityDamageEvent EntityDeathEvent EntityEvent EntityExplodeEvent
            EntityDamageEvent$DamageCause
            EntityInteractEvent EntityPortalEnterEvent
            EntityRegainHealthEvent EntityShootBowEvent EntityTameEvent
            ;EntityTargetEvent EntityTeleportEvent ExplosionPrimeEvent
            EntityTargetEvent ExplosionPrimeEvent
            FoodLevelChangeEvent ItemDespawnEvent ItemSpawnEvent PigZapEvent
            PlayerDeathEvent PotionSplashEvent ProjectileHitEvent
            SheepDyeWoolEvent SheepRegrowWoolEvent SlimeSplitEvent])
  (:require clj-http.client))

(defn broadcast [& strs]
  (.broadcastMessage (Bukkit/getServer) (apply str strs)))

(defn entity-target-event [evt]
  (when (instance? Creeper (.getEntity evt))
    (broadcast "Takumi is watching " (.. evt (getTarget) (getDisplayName)))))

(defonce swank* nil)
(defn on-enable [plugin]
  (when (nil? swank*)
    (def swank* (swank.swank/start-repl 4006)))
  (lingr "takumisamagamiteru plugin running..."))

;  (lingr "takumisamagamiteru plugin stopping...")
