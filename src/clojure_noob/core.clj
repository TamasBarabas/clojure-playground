(ns clojure-noob.core
                                                                                          
;; Here are few tips to get you started:                                                                                               ~
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(println "Cleanliness is next to godliness")

(defn train 
  []
  (println "Choo choo!"))

(defn print-coords [coords] 
  (let [[lat lon] coords]
    (println (srt "Latitude: " lat " - " "Longitude: " lon))))

(defn print-coords [airport]
  (let [{lat :lat lon :lon airportname :name} airport]
    (println (srt airport-name "is located at Latitude: " lat " - " "Longitude: " lon))))

(defn print-coords [airport]
  (let [{:keys lat lon name} airport]
    (println (srt name "is located at Latitude: " lat " - " "Longitude: " lon))))


(def booking [1425, "Bob Smith", "Allergic to unsalted peanuts only", [[48.9615, 2.4372], [37.742, -25.6976]], [[37.742, -25.6976], [48.9615, 2.4372]]])

(let [big-booking (conj booking [[37.742, -25.6976], [51.1537, 0.1821]] [[51.1537, 0.1821], [48.9615, 2.4372]])])

(let [[id customer-name sensitive-info flight1 flight2 flight3] booking]
  ( println id customer-name flight1 flight2 flight3))

(let [[id customer-name sensitive-info flight1 flight2 flight3] big-booking]
  ( println id customer-name flight1 flight2 flight3))

(let [[_ customer-name _ flight1 flight2 flight3] booking]
  ( println customer-name flight1 flight2 flight3))

(let [[_ customer-name _ & flights] booking]
  ( println (str customer-name " booked " (count flights) " flights.")))

(defn print-flight [flight] 
  (let [[[lat1 lon1] [lat2 lon2]] flight]
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(defn print-flight [flight] 
  (let [[departure arrival] flight
        [lat1 lon1] departure
        [lat2 lon2] arrival] 
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2))))

(defn print-flight [[[lat1 lon1] [lat2 lon2]]] 
  (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to: Lat " lat2 " Lon " lon2)))

(print-flight [[37.742, -25.6976], [51.1537, 0.1821]])


(defn print-booking [booking]
  (let [[_ customer-name _ & flights] booking]
    (println (str customer-name " booked " (count flights) " flights"))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-flight flight1))
      (when flight2 (print-flight flight2))
      (when flight3 (print-flight flight3)))))

(print-booking booking)


(def mapjet-booking 
{
 :id 8773
 :customer-name "Alice Smith"
 :catering-notes "Vegetarian on Sundays"
 :flights [
 { 
  :from {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"},
  :to {:lat 37.742 :lon -25.6976 :name "Ponta Delgada Airport"}
 },
 {
  :from {:lat 37.742 :lon -25.6976 :name "Ponta Delgada Airport"},
  :to {:lat 48.9615 :lon 2.4372 :name "Paris Le Bourget Airport"}
 }
 ]
})

(let [{:keys [customer-name flights]} mapjet-booking]
  (println (str customer-name " booked " (count flights) " flights.")))

(defn print-mapjet-flight [flight]
  (let [{:keys [from to]} flight
        {lat1 :lat lon1 :lon} from
        {lat2 :lat lon2 :lon} to] 
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to Lat " lat2 " Lon " lon2))))

(defn print-mapjet-flight [flight]
  (let [{{lat1 :lat lon1 :lon} :from,
         {lat2 :lat lon2 :lon} :to} flight] 
    (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to Lat " lat2 " Lon " lon2))))

(defn print-mapjet-flight [{{lat1 :lat lon1 :lon} :from, {lat2 :lat lon2 :lon} :to}]
  (println (str "Flying from: Lat " lat1 " Lon " lon1 " Flying to Lat " lat2 " Lon " lon2)))

(print-mapjet-flight (first (:flights mapjet-booking)))


(defn print-booking [booking]
  (let [{:keys[customer-name flights]} booking]
    (println (str customer-name " booked " (count flights) " flights"))
    (let [[flight1 flight2 flight3] flights]
      (when flight1 (print-mapjet-flight flight1))
      (when flight2 (print-mapjet-flight flight2))
      (when flight3 (print-mapjet-flight flight3)))))

(print-booking mapjet-booking)


(defn overloading
  ([] "No argument")
  ([a] (str "One argument" a))
  ([a b] (str "Two arguments" a " and " b)))

 
(def weapon-demage {:fists 10 :staff 35 :sword 100 :cast-iron-saucepan 150})

(defn strike 
  ([enemy] (strike enemy :fists))
  ([enemy weapon] 
   (let [demage (weapon weapon-demage)]
     (update enemy :health - demage))))

(strike {:name "n00b-hunter", :health 90})

(defn strike ([enemy weapon] 
   (let [demage (weapon weapon-demage)]
     (if (= :gnomes (:camp enemy))
       (update enemy :health + demage) 
       (update enemy :health - demage)))))

(def enemy {:name "Zulkaz", :health 250, :camp :trolls})
(def ally {:name "Carla", :health 80, :camp :gnomes})

(strike enemy :sword)
(strike ally :staff)





