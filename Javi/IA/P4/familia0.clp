(deffacts inicio
    ;; grandpa example
    ;; grandma example
    (dd uno dos tres m)
    (dd tres cuatro cinco m)
    
    
  
    
    (dd juan maria rosa m)
    (dd juan maria pepitoGrillo h)
    (dd juan maria luis h)
    (dd jose laura pilar m)
    (dd luis pilar miguel h)
    (dd miguel isabel jaime h))

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

(defrule grandpa 
    (dd ?x ?y ?z ?)
    (dd ?z ?z1 ?s ?)
    =>
    (assert (grandpa ?s ?x)))

(defrule grandma 
    (dd ?x ?y ?z ?)
    (dd ?z ?z1 ?s ?)
    =>
    (assert (grandma ?s ?y)))

(defrule cousin_h 
    (dd ?x ?y ?z ?)
    (dd ?z ?z1 ?s ?)
    =>
    (assert (cousin_h ?s ?y)))

(defrule cousin_m 
    (dd ?x ?y ?z ?)
    (dd ?z ?z1 ?s ?)
    =>
    (assert (cousin_m ?s ?y)))
(reset)
(run)
(facts)