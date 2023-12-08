(ns ads.data.advertisers)

(defn to-edn [json-string]
  (js->clj (.parse js/JSON json-string) :keywordize-keys true))

(def advertisers
  (to-edn "[{\"id\":\"1\",\"createdAt\":\"2018-04-15T06:27:46.058Z\",\"name\":\"Rowe, Cormier and Bruen\",\"campaignIds\":[4,7,8]},
            {\"id\":\"2\",\"createdAt\":\"2018-03-13T21:50:10.926Z\",\"name\":\"Homenick - Von\",\"campaignIds\":[1]},
            {\"id\":\"3\",\"createdAt\":\"2018-06-01T06:53:56.638Z\",\"name\":\"Emmerich and Sons\",\"campaignIds\":[]},
            {\"id\":\"4\",\"createdAt\":\"2018-01-29T09:49:18.811Z\",\"name\":\"Schaden, Jenkins and Swift\",\"campaignIds\":[9,5,2,3]},
            {\"id\":\"5\",\"createdAt\":\"2018-02-22T11:58:47.245Z\",\"name\":\"Dach - Kerluke\",\"campaignIds\":[12,53]},
            {\"id\":\"6\",\"createdAt\":\"2018-05-17T20:18:40.849Z\",\"name\":\"Williamson Inc\",\"campaignIds\":[9,2,6,7,8,4,42]},
            {\"id\":\"7\",\"createdAt\":\"2018-07-08T21:59:45.304Z\",\"name\":\"Beier - Hills\",\"campaignIds\":[42,96,87,46]},
            {\"id\":\"8\",\"createdAt\":\"2018-03-03T23:03:07.764Z\",\"name\":\"Hermann LLC\",\"campaignIds\":[13]},
            {\"id\":\"9\",\"createdAt\":\"2018-06-11T02:11:59.495Z\",\"name\":\"Abbott - Lang\",\"campaignIds\":[4,22]},
            {\"id\":\"10\",\"createdAt\":\"2018-06-12T17:31:55.034Z\",\"name\":\"Jacobson Inc\",\"campaignIds\":[]},
            {\"id\":\"11\",\"createdAt\":\"2018-04-04T08:17:46.319Z\",\"name\":\"Effertz - Rutherford\",\"campaignIds\":[3,45]},
            {\"id\":\"12\",\"createdAt\":\"2018-02-14T08:08:50.240Z\",\"name\":\"Zulauf - Willms\",\"campaignIds\":[7]},
            {\"id\":\"13\",\"createdAt\":\"2018-05-22T18:31:40.124Z\",\"name\":\"Kertzmann Inc\",\"campaignIds\":[99,12]},
            {\"id\":\"14\",\"createdAt\":\"2018-04-09T07:14:23.852Z\",\"name\":\"Ortiz, Heller and Tremblay\",\"campaignIds\":[8,4,5]},
            {\"id\":\"15\",\"createdAt\":\"2018-07-16T22:58:20.457Z\",\"name\":\"McClure - Harris\",\"campaignIds\":[47,29,39]}]"))

(def advertisers-stats
  (to-edn "[{\"advertiserId\":\"1\",\"impressions\":487983894,\"clicks\":948394},
            {\"advertiserId\":\"2\",\"impressions\":23059244,\"clicks\":948293},
            {\"advertiserId\":\"4\",\"impressions\":9580374,\"clicks\":48293},
            {\"advertiserId\":\"5\",\"impressions\":583957392,\"clicks\":89204},
            {\"advertiserId\":\"6\",\"impressions\":48499580374,\"clicks\":454429359},
            {\"advertiserId\":\"7\",\"impressions\":345789123049,\"clicks\":5438093},
            {\"advertiserId\":\"8\",\"impressions\":65442234,\"clicks\":94733},
            {\"advertiserId\":\"9\",\"impressions\":540374,\"clicks\":53345},
            {\"advertiserId\":\"11\",\"impressions\":3453374,\"clicks\":6933},
            {\"advertiserId\":\"12\",\"impressions\":8409483,\"clicks\":56493},
            {\"advertiserId\":\"13\",\"impressions\":3493044,\"clicks\":39298},
            {\"advertiserId\":\"14\",\"impressions\":59348324,\"clicks\":65491},
            {\"advertiserId\":\"15\",\"impressions\":459392,\"clicks\":345}]"))