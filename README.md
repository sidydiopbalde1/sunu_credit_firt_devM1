PARTIE 1 — Audit Architectural et Analyse Critique

1. Héritage et Principe de Liskov

Le fait que DemandeCredit hérite de CompteClient est une erreur de modélisation grave car

l’héritage représente une relation IS-A. Or,une demande de crédit n’est pas un compte client.

Une demande de crédit possède éventuellement un compte client

Elle est aussi liée à un client mais ne constitue pas une spécialisation d’un compte bancaire.

Le principe violé serait donc:

Principe de Substitution de Liskov qui dit que :  Une sous-classe doit pouvoir remplacer sa classe parent sans casser le comportement attendu.


2. Fuite d'Abstraction (Leaky Abstraction)

public List<String> getDocumentsGarantie() {
    return this.documentsGarantie;
}

Cette méthode retourne directement la liste interne modifiable. Cela détruit l'encapsulation.

Un développeur externe peut modifier directement l’état interne de l’objet sans respecter les règles métier.

# Conséquences:
contournement des validations
incohérence du domaine
violation des invariants


3. Primitive Obsession

3.a Utilisation de double dans le domaine bancaire
Pourquoi c’est une faute professionnelle ?

Le type double utilise une représentation flottante binaire.

Cela peut provoque des erreurs d’arrondi, des imprécisions financières, des incohérences comptables

C’est inacceptable dans un système bancaire et cela peut provoquer des pertes financières.

3.b Utilisation de String pour le statut

# Problèmes
private String statut;

Cette approche est dangereuse car :

aucune sécurité de compilation
fautes de frappe possibles
transitions d’état non contrôlées

Le système dépend fortement :

de l’ordre des appels
du respect manuel des transitions

Rien n’empêche :

demande.setStatut("VALIDE");

sans analyse préalable.

# Approche DDD correcte

Utiliser :

 Enum métier
enum StatutDemande {
    NOUVEAU,
    EN_ANALYSE,
    VALIDE,
    REJETE
}

et :

 State Machine métier

avec méthodes explicites :

analyser()
valider()
rejeter()

Les transitions deviennent contrôlées.

4. Modèle Anémique vs Modèle Riche

# Problème actuel

Toute la logique métier est centralisée dans :

AnalyseCreditService

La classe DemandeCredit devient un simple sac de données :

getters/setters
aucun comportement métier
aucune protection des invariants

C’est un modèle anémique.

# Dans un modèle riche (DDD)

La logique doit être déplacée dans les objets métier.

Responsabilités à déplacer

Depuis AnalyseCreditService

Vérification des garanties

documentsGarantie.size() >= 2

doit appartenir à :

DemandeCredit

Exemple :

demande.verifierGaranties()

Calcul des frais
montant * 0.02

doit appartenir à :

Money / DemandeCredit

Exemple :

demande.calculerFraisDossier()

Validation du plafond
montantTotal <= 5000000

doit appartenir à :

DemandeCredit

Gestion des transitions d’état
setStatut(...)

remplacé par :

demande.valider()
demande.rejeter()
demande.mettreEnAnalyse()