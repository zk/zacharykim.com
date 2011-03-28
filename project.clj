(defproject zkimcom "1.0.0-SNAPSHOT"
  :description
  "The Developer's Portfolio of Zachary Kim.  This site can be found at [zacharykim.com](http://zacharykim.com).

   Auto-generated documentation for this codebase can be found at http://zkim.github.com/zkimcom
   "
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring "0.3.3"]
                 [net.cgrand/moustache "1.0.0-SNAPSHOT"]
                 [nsfw "0.1-SNAPSHOT"]
                 [congomongo "0.1.3-SNAPSHOT"]
                 [joda-time/joda-time "1.6"]]
  :dev-dependencies [[swank-clojure "1.2.0"]
                     [marginalia "0.3.2"]
                     [cljs "0.2"]
                     [lein-cljs "0.2"]
                     [lein-ring "0.4.0"]]
  :source-path "src/clj"
  :cljs {:source-path "src/cljs"
         :source-output-path "resources/public/js"
         :source-libs []}
  :ring {:handler zkimcom.boot/entry-handler})
