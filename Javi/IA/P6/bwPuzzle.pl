try3([A, B, C | S], [A, B, C], S).
try1([A|S], [A], S).
try2([A, B|S],[A, B], S).

movimiento(estado([H_LEFT | T_LEFT], T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'iii'):-
	length([H_LEFT |T_LEFT], LENGTH),
	((LENGTH > 2) ->
	reverse([H_LEFT |T_LEFT], REVERSE_RT),
	try3(REVERSE_RT, CHAIN1, TRY_RT),
	reverse(CHAIN1, [H_AUX| T_AUX]),
	append([H_AUX], T_RIGTH, APPEND_RT),
	append(T_AUX, APPEND_RT, T_RIGTH_RT),
	reverse(TRY_RT, AUX),
	append(AUX, [], H_LEFT_RT)).


movimiento(estado([H_LEFT | T_LEFT], T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'ii'):-
	length([H_LEFT |T_LEFT], LENGTH),
	((LENGTH > 1) ->
	reverse([H_LEFT |T_LEFT], REVERSE_RT),
	try2(REVERSE_RT, CHAIN1, TRY_RT),
	reverse(CHAIN1, [H_AUX| T_AUX]),
	append([H_AUX], T_RIGTH, APPEND_RT),
	append(T_AUX, APPEND_RT, T_RIGTH_RT),
	reverse(TRY_RT, AUX),
	append(AUX, [], H_LEFT_RT)).


movimiento(estado([H_LEFT | T_LEFT], T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'i'):-
	length([H_LEFT |T_LEFT], LENGTH),
	((LENGTH > 0) ->
	reverse([H_LEFT |T_LEFT], REVERSE_RT),
	try1(REVERSE_RT, CHAIN1, TRY_RT),
	reverse(CHAIN1, [H_AUX| T_AUX]),
	append([H_AUX], T_RIGTH, APPEND_RT),
	append(T_AUX, APPEND_RT, T_RIGTH_RT),
	reverse(TRY_RT, AUX),
	append(AUX, [], H_LEFT_RT)).

movimiento(estado(T_LEFT, T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'ddd'):-
	length(T_RIGTH, LENGTH),
	((LENGTH > 2) ->
	try3(T_RIGTH, [A, B, C], AUX),
	append(T_LEFT, [C], AUX2),
	append(AUX2, [A,B], H_LEFT_RT),
        append(AUX, [], T_RIGTH_RT)).

movimiento(estado(T_LEFT, T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'dd'):-
	length(T_RIGTH, LENGTH),
	((LENGTH > 1) ->
	try2(T_RIGTH, [A, B], AUX),
	append(T_LEFT, [B], AUX2),
	append(AUX2, [A], H_LEFT_RT),
        append(AUX, [], T_RIGTH_RT)).

movimiento(estado(T_LEFT, T_RIGTH), estado(H_LEFT_RT,T_RIGTH_RT), 'd'):-
	length(T_RIGTH, LENGTH),
	((LENGTH > 0) ->
	try1(T_RIGTH, [A], AUX),
	append(T_LEFT, [A], AUX2),
	append(AUX2, [], H_LEFT_RT),
        append(AUX, [], T_RIGTH_RT)).
