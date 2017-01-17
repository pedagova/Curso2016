try([A, B, C | S], [A, B, C], S).
try1([A|S], [A], S).
try2([A, B|S],[A, B], S).
movimiento(estado([XL | XLS], [XR|XRS]), estado([YL | YLS],[YR|YRS])):-
	length([XL|XLS], K),
	((K > 2) ->
	reverse([XL|XLS], R),
	try(R, P1, P2),
	reverse(P1, [P1OK| P1OKS]),
	append([P1OK], [XR|XRS], AUX),
	append(P1OKS, AUX, [YR|YRS]),
	reverse(P2, AUX2),
	append(AUX2, [], [YL|YLS])).
