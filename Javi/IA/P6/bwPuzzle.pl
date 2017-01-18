movimiento(estado(L, R), estado(FL, FR), 2,'iii'):-
	reverse(L, [A,B,C|S]),
	append([B, A, C], R, FR),
	reverse(S, TEMP),
	append(TEMP,[] , FL).

movimiento(estado(L, R), estado(FL, FR), 1,'ii'):-
	reverse(L, [A,B|S]),
	append([A, B], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

movimiento(estado(L, R), estado(FL, FR), 1,'i'):-
	reverse(L, [A|S]),
	append([A], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

movimiento(estado(L, [A, B, C| S]), estado(FL, FR),2, 'ddd'):-
	append(L, [C, A, B], FL),
	append(S, [] ,FR).

movimiento(estado(L, [A, B| S]), estado(FL, FR), 1,'dd'):-
	append(L, [B, A], FL),
	append(S, [] ,FR).

movimiento(estado(L, [A| S]), estado(FL, FR), 1,'d'):-
	append(L, [A], FL),
	append(S, [] ,FR).





/*
 * ESTADO INICIAL
 */

inicial(estado(['B','B','B'],['N','N','N'])).

/*
 * ESTADO FINAL
 */

objetivo(estado(['N','N','N'],['B','B','B'])).

/*
 * PREDICADOS AUXILIARES
 */

/*
 * ESQUEMAS DE BUSQUEDA 1: Dterminamos solución sin control de
 * repeticiones ni coste
 */

puede1(estado):-objetivo(estado, estado_siguiente, operador),
	puede1(estado_siguiente).

/*
 * ESQUEMA DE BUSQUEDA 2: Determina solución sin control de
 * repeticiones, sin coste y devolviendo el camino
 */

puede2(STATE,[]):-objetivo(STATE).
puede2(STATE,[H_OPERATOR|T_OPERATOR]):- movimiento(STATE, NEXT_STATE,H_OPERATOR), puede2(NEXT_STATE, T_OPERATOR).

/*
 * ESQUEMA DE BUSQUEDA 3: Determina si hay solución con control de
 * repeticiones, sin coste y devolviendo el camino
 */

puede3(STATE,_,[]):-objetivo(STATE).
puede3(STATE, VISITED, [H_OPERATOR | T_OPERATOR]):-
	movimiento(STATE, NEXT_STATE, H_OPERATOR),
	\+ member(NEXT_STATE, VISITED),
	puede(NEXT_STATE, [NEXT_STATE | VISITED], T_OPERATOR).

/*
 * ESQUEMA DE BUSQUEDA 4: Determina solución con control de
 * repeticiones, sin coste y predicado consulta
 */

puede4(STATE, VISITED, [], [STATE]):-
	objetivo(STATE), nl, write('Visited: '), write(VISITED).
puede4(STATE, VISITED, [H_OPERATOR | T_OPERATOR], [STATE | P_STATE]):-
	movimiento(STATE, NEXT_STATE, H_OPERATOR),
	\+ member(NEXT_STATE, VISITED),
	puede4(NEXT_STATE, [NEXT_STATE | VISITED], T_OPERATOR, P_STATE).

% QUERY

query:- inicial(STATE), puede(STATE,[STATE], OPERATORS, STATES), nl, write('SOLUTION FIND without state repetitions: '), nl, write(OPERATORS), nl, write('State path: '), nl, write(STATES).
