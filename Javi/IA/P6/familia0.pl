dd(juan, maria, rosa, m).
dd(juan, maria, luis, h).
dd(jose, laura, pilar, m).
dd(luis, pilar, miguel, h).
dd(miguel,isabel,jaime,h).
dd(pedro, rosa, pablo, h).
dd(pedro, rosa, begoña, m).

padre(X, Y):- dd(X, _, Y, _).
madre(X, Y):- dd(_, X, Y, _).
hijo(X, Y):- dd(Y, _, X, h).
hijo(X, Y):- dd(_, Y, X, h).
hija(X, Y):- dd(Y, _, X, m).
hija(X, Y):- dd(_, Y, X, m).

abuelo(X, Y):- padre(X, Z),
				padre(Z, Y).
abuelo(X, Y):- padre(X, Z),
				madre(Z, Y).
abuela(X, Y):- madre(X, Z),
				padre(Z, Y).
abuela(X, Y):- madre(X, Z),
				madre(Z, Y).
hermano(X, Y):- hijo(X, Z),
				hijo(Y, Z).
hermano(X, Y):- hijo(X, Z),
				hija(Y, Z).
hermana(X, Y):- hija(X, Z),
				hijo(Y, Z).
hermana(X, Y):- hija(X, Z),
				hija(Y, Z).		
primo(X, Y):- hijo(X, Z),
				hijo(Y, K),
				hermano(Z, K).
primo(X, Y):- hijo(X, Z),
				hija(Y, K),
				hermano(Z, K).
primo(X, Y):- hijo(X, Z),
				hijo(Y, K),
				hermana(Z, K).
primo(X, Y):- hijo(X, Z),
				hija(Y, K),
				hermana(Z, K).

prima(X, Y):- hija(X, Z),
				hijo(Y, K),
				hermano(Z, K).
prima(X, Y):- hija(X, Z),
				hija(Y, K),
				hermano(Z, K).
prima(X, Y):- hija(X, Z),
				hijo(Y, K),
				hermana(Z, K).
prima(X, Y):- hija(X, Z),
				hija(Y, K),
				hermana(Z, K).
ascendiente(X, Y):- padre(X, Y).
ascendiente(X, Y):- padre(X, Z),
					ascendiente(Z, Y).
					
ascendiente(X, Y):- madre(X, Y).
ascendiente(X, Y):- madre(X, Z),
					ascendiente(Z, Y).