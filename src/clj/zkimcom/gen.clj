(ns zkimcom.gen
  (:require [zkimcom.site :as site]
            [garden.core :refer [css]]))

(defn gen-site [{:keys [root-path
                        resources-root]}
                pages]
  (when-not root-path
    (throw (Exception. (str "root-path not specified."))))
  (doseq [[path html-str] pages]
    (spit
      (str root-path "/" path)
      html-str)))

(defn start-app []
  (gen-site
    {:root-path "static"}
    site/pages)
  (css
    {:output-to "static/css/site.css"}
    site/stylesheet)
  true)

(defn stop-app [f])
