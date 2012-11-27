(defproject zkimcom "0.1"
  :description
  "The Developer's Portfolio of Zachary Kim.  This site can be found at zacharykim.com."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/data.zip "0.1.0"]
                 [nsfw "0.2.4"]
                 [joda-time/joda-time "1.6"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [marginalia "0.3.2"]
                     [cljs "0.2.2"]
                     [lein-cljs "0.2.2"]]
  :source-paths ["src/clj"]
  :cljs {:source-path "src/cljs"
         :source-output-path "resources/public/js"
         :source-libs []}
  :min-lein-version "2.0.0")
