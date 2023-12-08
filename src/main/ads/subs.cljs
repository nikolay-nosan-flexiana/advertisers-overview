(ns ads.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :get-advertisers
 (fn [db _]
   (:advertisers db)))

(rf/reg-sub
 :get-error
 (fn [db _]
   (:error-message db)))

(rf/reg-sub
 :get-in-progress
 (fn [db _]
   (:in-progress db)))

(rf/reg-sub
 :current-route
 (fn [db _]
   (:current-route db)))

(rf/reg-sub
 :get-sort-params
 (fn [db _]
   (:sort-params db)))