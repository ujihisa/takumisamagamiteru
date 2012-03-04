(defproject takumisamagamiteru "2.0.0-SNAPSHOT"
  :description "A minecraft mod that alerts you if a creeper targets you!"
  :dependencies [[clj-http "0.3.1"]
                 [swank-clojure/swank-clojure "1.3.3"]
                 [org.clojure/clojure "1.3.0"]]
  :dev-dependencies [[org.bukkit/bukkit "1.2.3-R0"]
                     [clj-minecraft "1.0.0-SNAPSHOT"]]
  :javac-options {:destdir "classes/"}
  :java-source-path "javasrc")
