(ns takumisamagamiteru.core
  (:require [cljminecraft.core :as c])
  (:import [org.bukkit Bukkit])
  (:import [org.bukkit.event Event Event$Type])
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
            EndermanPickupEvent EndermanPlaceEvent EntityChangeBlockEvent
            EntityCombustByBlockEvent EntityCombustByEntityEvent
            EntityCombustEvent EntityCreatePortalEvent EntityDamageByBlockEvent
            EntityDamageByEntityEvent EntityDamageByProjectileEvent
            EntityDamageEvent EntityDeathEvent EntityEvent EntityExplodeEvent
            EntityDamageEvent$DamageCause
            EntityInteractEvent EntityListener EntityPortalEnterEvent
            EntityRegainHealthEvent EntityShootBowEvent EntityTameEvent
            ;EntityTargetEvent EntityTeleportEvent ExplosionPrimeEvent
            EntityTargetEvent ExplosionPrimeEvent
            FoodLevelChangeEvent ItemDespawnEvent ItemSpawnEvent PigZapEvent
            PlayerDeathEvent PotionSplashEvent ProjectileHitEvent
            SheepDyeWoolEvent SheepRegrowWoolEvent SlimeSplitEvent]))

(defn broadcast [& strs]
  (.broadcastMessage (Bukkit/getServer) (apply str strs)))

(defn entity-target-event* [evt]
  (when (instance? Creeper (.getEntity evt))
    (broadcast "Takumi is watching " (.getDisplayName (.getTarget evt)))))

(defn entity-target-event []
  (c/auto-proxy [org.bukkit.event.entity.EntityListener] []
                (onEntityTarget [evt] (entity-target-event* evt))))

(def plugin-manager* (Bukkit/getPluginManager))
(def plugin* (.getPlugin plugin-manager* "takumisamagamiteru"))

(defn hehehe [f label]
  (let [listener (f)]
    (.registerEvent
      plugin-manager*
      (label c/event-types)
      listener
      (:Normal c/event-priorities)
      plugin*)))

(defn enable-plugin [plugin]
  (hehehe entity-target-event :ENTITY_TARGET)
  (c/log-info "takumisamagamiteru started"))

(defn disable-plugin [plugin]
  (c/log-info "takumisamagamiteru stopped"))

(defn restart []
  (.disablePlugin plugin-manager* plugin*)
  (.enablePlugin plugin-manager* plugin*))
