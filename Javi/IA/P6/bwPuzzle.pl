movimiento(estado(L, R), estado(FL, FR), 'iii'):-
	reverse(L, [A,B,C|S]),
	append([B, A, C], R, FR),
	reverse(S, TEMP),
	append(TEMP,[] , FL).

movimiento(estado(L, R), estado(FL, FR), 'ii'):-
	reverse(L, [A,B|S]),
	append([A, B], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

movimiento(estado(L, R), estado(FL, FR), 'i'):-
	reverse(L, [A|S]),
	append([A], R, FR),
	reverse(S, TEMP),
	append(TEMP, [] ,FL).

movimiento(estado(L, [A, B, C| S]), estado(FL, FR), 'ddd'):-
	append(L, [C, A, B], FL),
	append(S, [] ,FR).

movimiento(estado(L, [A, B| S]), estado(FL, FR), 'dd'):-
	append(L, [B, A], FL),
	append(S, [] ,FR).

movimiento(estado(L, [A| S]), estado(FL, FR), 'd'):-
	append(L, [A], FL),
	append(S, [] ,FR).

