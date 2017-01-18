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

