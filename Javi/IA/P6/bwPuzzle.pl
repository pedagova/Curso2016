move(state(L, R), state(FL, FR), 1,'iii'):-
	reverse(L, [A,B,C|S]),
	append([B, A, C], R, FR),
	reverse(S, TEMP),
	append(TEMP,[] , FL).

move(state(L, R), state(FL, FR), 1,'ii'):-
	reverse(L, [A,B|S]),
	append([A, B], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

move(state(L, R), state(FL, FR), 1,'i'):-
	reverse(L, [A|S]),
	append([A], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

move(state(L, [A, B, C| S]), state(FL, FR), 1, 'ddd'):-
	append(L, [C, A, B], FL),
	append(S, [] ,FR).

move(state(L, [A, B| S]), state(FL, FR), 1,'dd'):-
	append(L, [B, A], FL),
	append(S, [] ,FR).

move(state(L, [A| S]), state(FL, FR), 1,'d'):-
	append(L, [A], FL),
	append(S, [] ,FR).





/*
 * ESTADO INICIAL
 */

goal(state(['B','B','B'],['N','N','N'])).

/*
 * ESTADO FINAL
 */

begin(state(['N','N','N'],['B','B','B'])).

solution(STATE, VISITED, [], [STATE]):-
	goal(STATE),
	nl,
	write('Visited: '),
	showList(VISITED).

solution(STATE, VISITED, [H_OPERATOR | T_OPERATOR], [STATE | P_STATE]):-
	move(STATE, NEXT_STATE, C, H_OPERATOR),
	\+ member(NEXT_STATE, VISITED),
	solution(NEXT_STATE, [NEXT_STATE | VISITED], T_OPERATOR, P_STATE).

% QUERY

query:-	begin(STATE),
	solution(STATE,[STATE], OPERATORS, STATES),
	writeln('Operators without repetitions: '),
	showList(OPERATORS),
	writeln('State path: '),
	showList(STATES).

showList([]):- nl.
showList([HEAD | TAIL]):-
	tab(2),
	writeln(HEAD),
	showList(TAIL).







