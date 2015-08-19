(ns zkimcom.site
  (:require [hiccup.core :as hc]
            [hiccup.page :as hp]
            [garden.core :refer [css]]))

(def stylesheet
  [[:html :body {:height "100%" :width "100%"}]
   [:body {:font-family "'Lato', sans-serif"}
    [:.logo {:max-width "100%"
             :display 'block
             :margin-left 'auto
             :margin-right 'auto
             :margin-top "70px"
             :margin-bottom "40px"

             :border-radius "50%"
             :background-color "#79D6FD"
             :padding "10px"}]
    [:.heading {:font-weight 300
                :font-size "33px"
                :line-height "130%"
                :margin-top 0
                :margin-bottom "20px"}
     [:strong {:font-weight 500}]]
    [:.redact {:text-decoration 'line-through
               :color "#aaa"
               :white-space 'nowrap}]
    [:.above-fold
     {:padding-top "50px"
      :min-height "100%"}]
    [:.callout {:margin-bottom "60px"}]
    [:.contact
     {:text-align 'left}
     [:.contact-logo
      {:height "30px"
       :margin-left 0
       :margin "0 30px"}]]

    [:.nav
     {:margin-bottom "25px"}
     [:ul
      {:padding-left 0}
      [:li {:display 'inline-block
            :margin-right "20px"}
       [:a {:font-size "18px"
            :color "#ccc"}]]]]]])

(def index
  (hp/html5
    [:head
     [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"}]
     [:link {:rel "stylesheet" :href "http://fonts.googleapis.com/css?family=Lato:400,700,300"}]
     [:link {:rel "stylesheet" :href "css/site.css"}]]
    [:body
     [:div.section.above-fold
      [:div.container
       [:div.row
        [:div.col-sm-12.col-md-10.col-md-offset-1
         [:div.row
          [:div.col-sm-4
           [:img.logo {:src "https://pbs.twimg.com/profile_images/532617885617627136/KEYfBh1r.png"}]]
          [:div.col-sm-8
           [:div.nav
            [:ul
             [:li [:a {:href "#"} "About"]]
             [:li [:a {:href "#"} "Featured Work"]]
             [:li [:a {:href "#"} "Speaking"]]]]
           [:div.callout
            [:h1.heading "Hey there. "]
            [:h1.heading
             "This is the professional portfolio of " [:strong "Zachary Kim"] ", an Engineer and Entrepreneur located in "
             [:span.redact "Honolulu, HI,"]
             " "
             [:span.redact "Denver, CO,"]
             " "
             [:span.redact "Chicago, IL,"]
             " "
             "San Francisco, CA"
             "."]]
           [:div.contact
            [:img.contact-logo {:src "images/email-logo.png"}]
            [:img.contact-logo {:src "https://cdn4.iconfinder.com/data/icons/iconsimple-logotypes/512/github-512.png"}]
            [:img.contact-logo {:src "images/twitter-logo.png"}]
            [:img.contact-logo {:src "images/linkedin-logo.png"}]
            ]]]]]]]
     [:div.section.about
      [:div.container
       [:div.col-sm-12.col-md-10.col-md-offset-1
        [:h1 "HI"]]]]
     [:script {:type "text/javascript" :src "cljs/app.js"}]]))

(def pages
  {"index.html" index})
