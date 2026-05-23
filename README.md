PARTIE 1 — Audit Architectural et Analyse Critique

1. Héritage et Principe de Liskov

Le fait que DemandeCredit hérite de CompteClient est une erreur de modélisation grave car

l’héritage représente une relation IS-A. Or,une demande de crédit n’est pas un compte client.

Une demande de crédit possède éventuellement un compte client

Elle est aussi liée à un client mais ne constitue pas une spécialisation d’un compte bancaire.

Le principe violé serait donc:

Principe de Substitution de Liskov qui dit que :  Une sous-classe doit pouvoir remplacer sa classe parent sans casser le comportement attendu.


2. Fuite d'Abstraction (Leaky Abstraction)

