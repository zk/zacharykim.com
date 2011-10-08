(ns zkimcom.feeds
  (:use [clojure.pprint])
  (:require [clojure.xml :as xml]
	    [clojure.zip :as zip]
	    [clojure.contrib.zip-filter.xml :as zf]
            [somnium.congomongo :as mongo]))

(require '[clojure.contrib.sql])

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

;; Google Reader
;;   Database

(defn reader-entry-exists? [entry]
  (let [{:keys [title href updated source-title source-href]} entry]
    (mongo/fetch-one :reader :where {:title title
                                     :href href
                                     :source-title source-title
                                     :source-href source-href})))

(defn insert-reader-entry [entry]
  (when (not (reader-entry-exists? entry))
    (mongo/insert! :reader (assoc entry
                             :updated (utc-to-timestamp (:updated entry))))))

(defn insert-reader-entries [entries]
  (doseq [e entries]
    (insert-reader-entry e)))

;;   Parsing

(defn get-reader-entries [feed-url]
  (map 
   (fn [entry]
     {:title (str (zf/xml1-> entry :title zf/text))
      :href (str (zf/xml1-> entry :link (zf/attr :href)))
      :updated (str (zf/xml1-> entry :updated zf/text))
      :source-title (str (let [source (zf/xml1-> entry :source)]
			   (zf/xml1-> source :title zf/text)))
      :source-href (str (let [source (zf/xml1-> entry :source)]
			  (zf/xml1-> source :link (zf/attr :href))))})
   (zf/xml-> (xml-source-to-zip feed-url) :entry)))


;; Twitter
;;   Database

(defn twitter-entry-exists? [entry]
  (let [{:keys [content pub-date href poster]} entry]
    (mongo/fetch-one :twitter :where {:content content
                                      :href href
                                      :poster poster})))

(defn insert-twitter-entry [entry]
  (when (not (twitter-entry-exists? entry))
    (let [{:keys [content pub-date href poster]} entry]
      (mongo/insert! :twitter {:content content
                               :pub-date pub-date
                               :href href
                               :poster poster}))))

(defn insert-twitter-entries [entries]
  (doseq [e entries]
    (insert-twitter-entry e)))

(defn get-twitter-entries [feed-url]
  (let [source-zip (xml-source-to-zip feed-url)
	poster (zf/xml1-> source-zip :channel :link zf/text)
        entries (zf/xml-> source-zip :channel :item)]
    (map
     (fn [entry]
       {:content (str (zf/xml1-> entry :description zf/text))
	:pub-date (str (zf/xml1-> entry :pubDate zf/text))
	:poster poster
	:href (str (zf/xml1-> entry :link zf/text))})
     entries)))

;; Delicious

(defn delicious-entry-exists? [entry]
  (let [{:keys [content pub-date href poster]} entry]
    (mongo/fetch-one :delicious :where {:content content
                                        :pub-date (utc-to-timestamp pub-date)
                                        :href href
                                        :poster poster})))

(defn insert-delicious-entry [entry]
  (when (not (delicious-entry-exists? entry))
    (mongo/insert! :delicious (assoc entry
                                :pub-date (utc-to-timestamp (:pub-date entry))))))

(defn insert-delicious-entries [entries]
  (doseq [e entries]
    (insert-delicious-entry e)))

(defn get-delicious-entries [feed-url]
  (let [source-zip (xml-source-to-zip feed-url)
	poster (zf/xml1-> source-zip :channel :link zf/text)
        entries (zf/xml-> source-zip :channel :item)]
    (map
     (fn [entry]
       {:content (str (zf/xml1-> entry :title zf/text))
	:pub-date (str (zf/xml1-> entry :pubDate zf/text))
	:poster poster
	:href (str (zf/xml1-> entry :link zf/text))
	:tags (zf/xml-> entry :category zf/text)})
     entries)))

