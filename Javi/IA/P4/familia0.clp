(deffacts inicio
    ;; grandpa example
    ;; grandma example
    ;;(dd uno dos tres m)
    ;;(dd tres cuatro cinco m) 
    
    ;;(dd juan maria rosa m)
    ;;(dd juan maria pepitoGrillo h)
    ;;(dd juan maria luis h)
    ;;(dd jose laura pilar m)
    ;;(dd luis pilar miguel h)
    ;;(dd miguel isabel jaime h))
	(dd pedri ana david h)
    (dd jose celia pedrito m)
    (dd pedro pili pedri h)
    (dd pedro pili jose h)
)
(defrule padre
    (dd ?x ? ?y ?)
    =>
    (assert (padre ?x ?y)))

(defrule madre
    (dd ? ?x ?y ?)
    => 
    (assert (madre ?x ?y)))

(defrule brother 
    (dd ?x ?y ?z h)
    (dd ?x ?y ?z1 ?) 
    (test (neq ?z ?z1))
    =>
    (assert (brother ?z ?z1)))

(defrule sister 
    (dd ?x ?y ?z m)
    (dd ?x ?y ?z1 ?) 
    (test (neq ?z ?z1))
    =>
    (assert (sister ?z ?z1)))

(defrule daughter 
    (dd ?x ?y ?z m)
    =>
    (assert (daughter ?y ?z))
    (assert (daugther ?x ?z)))

(defrule son 
    (dd ?x ?y ?z h)
    =>
    (assert (son ?y ?z))
    (assert (son ?x ?z)))

(defrule grandpaF
   (padre ?x ?y) 
    (padre ?y ?z)
    => 
    (assert(gandpa ?x ?z)))

(defrule grandpaM
   (padre ?x ?y)
   (madre ?y ?z) 
    => 
    (assert(gandpa ?x ?z)))

(defrule grandmaP
   (madre ?x ?y) 
    (padre ?y ?z)
    => 
    (assert(gandma ?x ?z)))

(defrule grandmaM
   (madre ?x ?y)
   (madre ?y ?z) 
    => 
    (assert(gandma ?x ?z)))

(defrule cousin1
    (son ?p ?y)
    (son ?t ?e)
    (brother ?p ?t) 
    =>
    (assert (cousinM ?y ?e)))
(defrule cousin2
    (son ?p ?y)
    (daugther ?t ?e)
    (brother ?p ?t) 
    =>
    (assert (cousinM ?y ?e)))

(defrule cousin7
    (son ?p ?y)
    (daughter ?t ?e)
    (sister ?p ?t) 
    =>
    (assert (cousinM ?y ?e)))

(defrule cousin8
    (son ?p ?y)
    (son ?t ?e)
    (sister ?p ?t) 
    =>
    (assert (cousinM ?y ?e)))

(defrule cousin3
    (daugther ?p ?y)
    (son ?t ?e)
    (brother ?p ?t) 
    =>
    (assert (cousinF ?y ?e)))

(defrule cousin4
    (daugther ?p ?y)
    (daughter ?t ?e)
    (brother ?p ?t) 
    =>
    (assert (cousinF ?y ?e)))
(defrule cousin5
    (daugther ?p ?y)
    (son ?t ?e)
    (sister ?p ?t) 
    =>
    (assert (cousinF ?y ?e)))

(defrule cousin6
    (daugther ?p ?y)
    (daughter ?t ?e)
    (sister ?p ?t) 
    =>
    (assert (cousinF ?y ?e)))

(defrule ascend 
    (padre ?x ?y)
    =>
    (assert (ascend ?x ?y)))
(defrule ascendM 
    (madre ?x ?y)
    =>
    (assert (ascend ?x ?y)))
(defrule ascendA 
    (ascend ?x ?y)
    (padre ?p ?x)
    =>
    (assert (ascend ?p ?y))) 
        
(defrule ascendAM
	(ascend ?x ?y)
    (madre ?m ?x)
     =>
    (assert (ascend ?m ?y)))


(reset)
(run)
(facts)
