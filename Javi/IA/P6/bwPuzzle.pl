try3([A, B, C | S], [A, B, C], S).
try1([A|S], [A], S).
try2([A, B|S],[A, B], S).
movimiento(estado([XL | XLS], [XR|XRS]), estado([YL | YLS],[YR|YRS])):-
	length([XL|XLS], K),
	((K > 2) ->
	reverse([XL|XLS], R),
	try3(R, P1, P2),
	reverse(P1, [P1OK| P1OKS]),
	append([P1OK], [XR|XRS], AUX),
	append(P1OKS, AUX, [YR|YRS]),
	reverse(P2, AUX2),
	append(AUX2, [], [YL|YLS])).


/*
 * OPERADORES: movimiento(Estado, EstadoSiguiente)
 */
movimiento(estado([H_LEFT | T_LEFT], [H_RIGTH | T_RIGTH]), estado([H_LEFT_RT | T_LEFT_RT],[H_RIGTH_RT|T_RIGTH_RT])):-
	length([H_LEFT |T_LEFT], LENGTH),
	((LENGTH > 2) ->
	reverse([H_LEFT |T_LEFT], REVERSE_RT),
	try(REVERSE_RT, CHAIN1, TRY_RT),
	reverse(CHAIN1, [H_AUX| T_AUX]),
	append([H_AUX], [H_RIGTH | T_RIGTH], APPEND_RT),
	append(T_AUX, APPEND_RT, [H_RIGTH_RT | T_RIGTH_RT]),
	reverse(TRY_RT, AUX),
	append(AUX, [], [H_LEFT_RT|T_LEFT_RT])).
