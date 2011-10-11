(ns zkimcom.actions
  (:use [nsfw util render]
        [ring.util.response :only (file-response redirect status header)])
  (:require [zkimcom.templates :as tpls]))

(defn index [latest-social-content-atom]
  (fn [req]
    (render tpls/index (take 5 @latest-social-content-atom))))

(defn recent-projects [req]
  (redirect "/featured-work"))

(defn four-oh-four [req]
  (-> (file-response "resources/public/404.html")
      (header "Content-Type" "text/html")
      (status 404)))

(defn featured-work [req]
  (render tpls/featured-work))

