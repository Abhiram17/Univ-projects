;*****************************************************************************
;Abhiram Hea, Panos Valavanis
;*****************************************************************************
;"Where to go for summer vacation"
;*****************************************************************************
;This Expert System will suggest an appropriate place for the user to
;vacation to based on his/her preferences
;*****************************************************************************
;To run the program, simply load it into the CLIPS IDE, execute the reset
;command, and then execute the run command
;*****************************************************************************

;the following class is used for the countries of the System
;the slots are searched and matched with the person preferences
(defclass COUNTRY (is-a USER)
  (role concrete)
  (slot country_name)
	(slot weather)
  (slot continent)
)

;the following class is used for the main user of the System
;the slots contain the preferences of the user
(defclass PERSON (is-a USER)
  (role concrete)
  (slot visit_country)
  (slot budget)
)

;epmty instances of both classes
(definstances EMPTY-INSTANCES
  (country of COUNTRY)
  (person of PERSON (visit_country "Don't go on vacation."))
)

;final rule to be executed, choosing the country to be suggested
(defrule output-Rule  (declare (salience -10))
  (object (is-a PERSON) (visit_country ?visit_country))
  =>(printout t ?visit_country "." crlf)
)

;rule to read the users budget
(defrule read-budget_preference
  ?response <- (object (is-a PERSON))
  =>(printout t "What is your budget? expensive, affordable" crlf)
  (send ?response put-budget (read) )
)

;rule to read users weather preferences
(defrule read-weather_preference
  ?response <- (object (is-a COUNTRY))
  =>(printout t "Do you prefer cool or warm weather?" crlf)
  (send ?response put-weather (read) )
)

;rule to read users continent preferences
(defrule read-continent_preference
  ?response <- (object (is-a COUNTRY))
  =>(printout t "Which continent/region do you prefer? america, asia, africa, europe, oceania" crlf)
  (send ?response put-continent (read) )
)

;rule to make greece the destination if all fields match
(defrule is-Greece
  ?response <- (object (is-a COUNTRY) (weather warm) (continent europe))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name Greece)
  =>(send ?response2 put-visit_country "You should go to Greece")
)

;rule to make germany the destination if all fields match
(defrule is-Germany
  ?response <- (object (is-a COUNTRY) (weather warm) (continent europe))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name Germany)
  =>(send ?response2 put-visit_country "You should go to Germany")
)

;rule to make england the destination if all fields match
(defrule is-England
  ?response <- (object (is-a COUNTRY) (weather cool) (continent europe))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name England)
  =>(send ?response2 put-visit_country "You should go to England")
)

;rule to make south africa the destination if all fields match
(defrule is-South_Africa
  ?response <- (object (is-a COUNTRY) (weather cool) (continent africa))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name SouthAfrica)
  =>(send ?response2 put-visit_country "You should go to South Africa")
)

;rule to make united states the destination if all fields match
(defrule is-UnitedStates
  ?response <- (object (is-a COUNTRY) (weather warm) (continent america))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name UnitedStates)
  =>(send ?response2 put-visit_country "You should go to the United States")
)

;rule to make thailand the destination if all fields match
(defrule is-Thailand
  ?response <- (object (is-a COUNTRY) (weather warm) (continent asia))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name Thailand)
  =>(send ?response2 put-visit_country "You should go to Thailand")
)

;rule to make south korea the destination if all fields match
(defrule is-SouthKorea
  ?response <- (object (is-a COUNTRY) (weather cool) (continent asia))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name SouthKorea)
  =>(send ?response2 put-visit_country "You should go to South Korea")
)

;rule to make china the destination if all fields match
(defrule is-China
  ?response <- (object (is-a COUNTRY) (weather warm) (continent asia))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name China)
  =>(send ?response2 put-visit_country "You should go to China")
)

;rule to make canada the destination if all fields match
(defrule is-Canada
  ?response <- (object (is-a COUNTRY) (weather cool) (continent america))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name Canada)
  =>(send ?response2 put-visit_country "You should go to Canada")
)

;rule to make australia the destination if all fields match
(defrule is-Australia
  ?response <- (object (is-a COUNTRY) (weather cool) (continent oceania))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name Australia)
  =>(send ?response2 put-visit_country "You should go to Australia")
)

;rule to make new zealand the destination if all fields match
(defrule is-NewZealand
  ?response <- (object (is-a COUNTRY) (weather warm) (continent oceania))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name NewZealand)
  =>(send ?response2 put-visit_country "You should go to New Zealand")
)

;rule to make tasmania the destination if all fields match
(defrule is-Tasmania
  ?response <- (object (is-a COUNTRY) (weather warm) (continent oceania))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name Tasmania)
  =>(send ?response2 put-visit_country "You should go to Tasmania")
)

;rule to make madagascar the destination if all fields match
(defrule is-Madagascar
  ?response <- (object (is-a COUNTRY) (weather warm) (continent africa))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name Madagascar)
  =>(send ?response2 put-visit_country "You should go to Madagascar")
)

;rule to make egypt the destination if all fields match
(defrule is-Egypt
  ?response <- (object (is-a COUNTRY) (weather warm) (continent africa))
  ?response2 <- (object (is-a PERSON) (budget expensive))
  =>(send ?response put-country_name Egypt)
  =>(send ?response2 put-visit_country "You should go to Egypt")
)

;rule to make kenya the destination if all fields match
(defrule is-Kenya
  ?response <- (object (is-a COUNTRY) (weather warm) (continent africa))
  ?response2 <- (object (is-a PERSON) (budget affordable))
  =>(send ?response put-country_name Kenya)
  =>(send ?response2 put-visit_country "You should go to Kenya")
)

;rule to have no location if inputs dont match
(defrule is-Nowhere
  ?response <- (object (is-a COUNTRY) )
  ?response2 <- (object (is-a PERSON) )
  =>(send ?response put-country_name Nowhere)
  =>(send ?response2 put-visit_country "Your preferences do not match any countries in our system")
)
