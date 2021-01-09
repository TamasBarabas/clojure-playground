(ns clojure-noob.core
                                                                                          
;; Here are few tips to get you started:                                                                                               ~
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(println "Cleanliness is next to godliness")

;;Functions
(fn [x] (* x x))
((fn [x] (* x x)) 2)
(def square (fn [x] (* x x)))
(square 3)
(defn square2 [x] (* x x))
(square2 10)

(defn meditate [s calm]
  (println "Clojure Meditate v1.0")
  (if calm 
    (clojure.string/capitalize s)
    (str (clojure.string/upper-case s) "!")))

(meditate "haha" false)

(defn meditate2 [s calmness-level]
  (if (< calmness-level 5)
      (clojure.string/capitalize s)
      (if (and (> calmness-level 5) (< calmness-level 10))
        (clojure.string/upper-case s)
        (clojure.string/reverse s)))
)

(meditate2 "abrakadabra" 1)
(meditate2 "abrakadabra" 6)
(meditate2 "abrakadabra" 10 )



(def base-co2 382)
(def base-year 2006)

(defn co2-estimate 
  "Estimates co2 level for year `year`"
  [year]  
  (let [year-diff (- year base-year)]
    (+ base-co2 (* year-diff 2)))
)

(co2-estimate 2050)



(defn encode-letter
  [s w]
  (let [code (Math/pow (+ w (int (first (char-array s)))) 2)]
    (str "#" (int code))))

(encode-letter "a" 1)

(defn encode
  [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\w" (fn [s] (encode-letter s number-of-words)))))

(defn decode-letter
  [x y]
  (let [number (Integer/parseInt (subs x 1))
        letter (char (- (Math/sqrt number) y))]
    (str letter)))

(defn decode [s]
  (let [number-of-words (count (clojure.string/split s #" "))]
    (clojure.string/replace s #"\#\d+" (fn [s] (decode-letter s number-of-words)))))

(encode "Hello Wrold")
(decode *1)


(def fruit {:name "Kiwi", :color "green", :kcal 61})
(def fruit2 (hash-map :name "Kiwi", :color "green", :kcal 61))
(get fruit :name)
(get fruit :taste "default")
(fruit :name)
(:name fruit)
(:shape fruit "egg-like")
(assoc fruit :shape "egg-like") 
(assoc fruit :color "brown") 
(update fruit :kcal dec)
(update fruit :kcal - 3)
(dissoc fruit :kcal)

#{1 2 3 4 5 }
(hash-set :a :b :c :d)
(set [1 2 3 4 5 5 5 5 5])
(sorted-set 1 2 3 4 5 5 5 5 5)

[1 2 3]
(vector 1 2 3)
(vec #{1 2 3})
[nil :keyword "String" {:answers [:yep :nope]}]
(get [:a :b :c] 0)



(def fibonacci [0 1 1 2 3 5 8])

(let [size (count fibonacci)
      last-number (last fibonacci)
      second-to-last-number (fibonacci (- size 2))]
  (conj fibonacci (+ last-number second-to-last-number)))

(1 2 3 4) ;;list represent code
'(1 2 3 4) ;; list represent list
(+ 1 2 3 4) ;;list represent code
'(+ 1 2 3 4) ;; list represent list
(list :a :b :c)
(first '(:a :b :c :d))
(rest '(:a :b :c :d))
(nth '(:a :b :c :d) 2)
(cons -1 fibonacci)
(conj fibonacci 13 21)


(def language {:name "Clojure", :creator "Rich Hickey", :platforms ["Java" "JavaScript" ".NET"]})
(seq language)
(nth (seq language) 1)
(last language)
(first #{:a :b :c})
(rest #{:a :b :c})

(into [1 2 3 4] #{5 6 7 8}) 
(into #{1 2 3 4} [5 6 7 8])
(into {} [[:a 1] [:b 2] [:c 3]])
(into '() [1 2 3 4])
  
(concat '(1 2) '(3 4))
(into '(1 2) '(3 4))
(concat #{1 2 3} #{1 2 3 4}) ;; return a sequence
(sort [3 7 5 1 9]) ;;returns a sequence
(into [] (sort [3 7 5 1 9]))

get-in
update-in
assoc-in




(def memory-db (atom {}))
(defn read-db [] @memory-db)
(defn write-db [new-db] (reset! memory-db new-db))

(defn create-table [table-name]
  (let [new-table {:data [], :indexes {} }]
    (write-db (assoc (read-db) table-name new-table))))

(defn remove-table [table-name]
  (write-db (dissoc (read-db) table-name)))

(defn insert [table-name record id-key]
  (let [db (read-db)
        new-db (update-in db [table-name :data] conj record)
        index (-(count(get-in new-db [table-name :data])) 1)]
    (write-db (update-in new-db [table-name :indexes id-key] assoc (id-key record) index))))

(defn select-* [table-name] 
  (get-in (read-db) [table-name :data]))

(defn select-*-where [table-name field field-value]
  (get-in (read-db) [table-name :data (get-in (read-db) [table-name :indexes field field-value])]))

(read-db)
(create-table :a)
(create-table :b)
(create-table :c)
(remove-table :c)
(insert :c {:color "red", :shape "circle"} :color)
(select-* :c)
(select-*-where :c :color "red")


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





