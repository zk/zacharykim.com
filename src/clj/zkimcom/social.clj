(ns zkimcom.social
  (:require [zkimcom.feeds :as feeds]))

(def latest-social-content (atom []))

(defn update-latest-social-content! []
  (let [reader-entries (feeds/get-reader-entries feeds/reader-feed-url)
        twitter-entries (feeds/get-twitter-entries feeds/twitter-feed-url)]
    (swap! latest-social-content (fn [latest]
                                   (->> (concat latest
                                                reader-entries
                                                twitter-entries)
                                        distinct
                                        (sort-by :timestamp)
                                        (reverse)
                                        (take 20))))
    true))

(comment
  (defn start-updater! []
    (let [control (atom true)
          stop (fn []
                 (reset! control false))]
      (send-off (agent nil) (fn []
                              (while @control
                                (println "Updating social content")
                                (Thread/sleep 1000))))
      stop))

  (def s (start-updater!))

  (s))

(defn latest! [num]
  (take num @latest-social-content))



