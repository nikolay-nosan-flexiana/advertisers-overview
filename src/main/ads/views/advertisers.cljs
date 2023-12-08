(ns ads.views.advertisers 
  (:require ["@mui/x-data-grid" :refer [DataGrid]]
            [re-frame.core :as rf]
            [tick.core :as t]
            [tick.locale-en-us]))

(defn get-row-value [row]
  (-> row js->clj (get "value")))

(defn na-value-getter [row]
  (or (get-row-value row) "n/a"))

(def base-columns [{:field "name", :headerName "Advertiser", :width 300
                    :headerClassName "table-header"
                    :cellClassName "cell-style"}
                   {:field "createdAt", :headerName "Creation Date", :width 200
                    :valueGetter #(->> % get-row-value t/date-time (t/format (t/formatter "yyyy-MM-dd HH:mm")))}
                   {:field "campaignsCount", :headerName "# Campaigns", :width 200}])

(def stats-columns [{:field "impressions", :headerName "Impressions", :width 200
                     :valueGetter na-value-getter}
                    {:field "clicks", :headerName "Clicks", :width 200
                     :valueGetter na-value-getter}])

(defn ads-table []
  (let [advertisers @(rf/subscribe [:get-advertisers])
        error @(rf/subscribe [:get-error])
        in-progress @(rf/subscribe [:get-in-progress])
        rows-data (or advertisers [])
        [sort-field sort-type] @(rf/subscribe [:get-sort-params])]
  [:div {:style {:height 500} }
   [:div {:style {:color "#FF0000"}} (or error "")]
    [:> DataGrid {:rows rows-data
                  :columns (concat base-columns stats-columns)
                  :hideFooter true
                  :disableColumnMenu true
                  :loading in-progress
                  :initialState {:sorting {:sortModel [{:field sort-field, :sort sort-type}]}}
                  :sx {:border 1
                       :borderColor "#999999"
                       :borderRadius 0
                       :m 0}}]]))

(defn advertisers-ui
  []
    [:div {:class-name "main-container"}
     [:div {:class-name "page-header"} "Overview of Advertisers" ]
     [ads-table]])