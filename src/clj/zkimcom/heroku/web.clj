(ns zkimcom.heroku.web
  (:require [nsfw.server :as server]
            [zkimcom.boot :as boot]
            [zkimcom.feeds :as feeds]))


(def reader-feed-url
  "http://www.google.com/reader/public/atom/user/14149351528493136073/state/com.google/broadcast")
(def twitter-feed-url
  "http://twitter.com/statuses/user_timeline/14194705.rss")

(def latest-social-content (atom []))

(defn update-social [latest-social-content-atom
                     reader-feed-url
                     twitter-feed-url]
  (let [rdr-entries (feeds/get-reader-entries reader-feed-url)
        twt-entries (feeds/get-twitter-entries twitter-feed-url)]
    (swap! latest-social-content-atom
           (fn [latest]
             (->> (concat latest
                          rdr-entries
                          twt-entries)
                  (sort-by :timestamp)
                  (reverse)
                  (take 5))))))

(defn start-updater! []
  (let [control (atom true)
        f (fn []
            (while @control
              (println "Updating social content.")
              (update-social latest-social-content
                             reader-feed-url
                             twitter-feed-url)
              (Thread/sleep (* 1000 60 5)))
            (println "Exited updater"))
        t (Thread. f)]
    (.start t)
    (fn []
      (reset! control false))))



(defonce s (server/make (var boot/entry-handler)))

(server/start s :port (Integer/parseInt (or (System/getenv "PORT") "8080")) :max-threads 20)

(start-updater!)
