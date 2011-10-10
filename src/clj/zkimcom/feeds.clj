(ns zkimcom.feeds
  (:use [clojure.pprint])
  (:require [clojure.xml :as xml]
	    [clojure.zip :as zip]
	    [clojure.data.zip.xml :as zf]))

;; Database

(def reader-feed-url "http://www.google.com/reader/public/atom/user/14149351528493136073/state/com.google/broadcast")
(def twitter-feed-url "http://twitter.com/statuses/user_timeline/14194705.rss")
(def delicious-feed-url "http://feeds.delicious.com/v2/rss/zkim?count=15")

(defn xml-source-to-zip [url]
  (zip/xml-zip (xml/parse url)))

(defn timestamp-to-mysql-format [ts]
  (.print (org.joda.time.format.DateTimeFormat/forPattern "yyyy-MM-dd HH:mm:ss") ts))

(defn utc-to-timestamp [#^String utc]
  (let [utc utc]
  (first 
   (filter 
    #(not (nil? %))
    (map #(try (% utc) (catch Exception e nil))
	 [(fn [utc] (.getMillis 
		     (.parseDateTime 
		      (org.joda.time.format.ISODateTimeFormat/dateTimeParser) utc)))
	  (fn [utc] (.getMillis 
		     (.parseDateTime 
		      (org.joda.time.format.DateTimeFormat/forPattern 
		       "EEE, dd MMM yyyy HH:mm:ss Z") utc)))])))))

;;   Parsing

(defn get-reader-entries [feed-url]
  (->> (zf/xml-> (xml-source-to-zip feed-url) :entry)
       (map 
        (fn [entry]
          {:title (str (zf/xml1-> entry :title zf/text))
           :href (str (zf/xml1-> entry :link (zf/attr :href)))
           :updated (str (zf/xml1-> entry :updated zf/text))
           :source-title (str (let [source (zf/xml1-> entry :source)]
                                (zf/xml1-> source :title zf/text)))
           :source-href (str (let [source (zf/xml1-> entry :source)]
                               (zf/xml1-> source :link (zf/attr :href))))
           :type :reader}))
       (map #(assoc % :timestamp (utc-to-timestamp (:updated %))))))


;; Twitter
;;   Database

(defn get-twitter-entries [feed-url]
  (let [source-zip (xml-source-to-zip feed-url)
	poster (zf/xml1-> source-zip :channel :link zf/text)
        entries (zf/xml-> source-zip :channel :item)]
    (->> entries
         (map
          (fn [entry]
            {:content (str (zf/xml1-> entry :description zf/text))
             :pub-date (str (zf/xml1-> entry :pubDate zf/text))
             :poster poster
             :href (str (zf/xml1-> entry :link zf/text))
             :type :twitter}))
         (map #(assoc % :timestamp (utc-to-timestamp (:pub-date %)))))))

