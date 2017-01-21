# Environnement de travail

Le projet a été développé sous Windows 10. L'IDE utilisé est eclipse Neon et les tests ont étés effectués avec une JVM en Java 8.

# Avancement dans le TP

La piste verte est complète (parser + évaluation)
La piste bleue est complète (parser + évaluation)

Ni la piste rouge ni la piste noire n'ont été implémentées.

# Stratégie de gestion des erreurs

Un travail a été effectué concernant la gestion des erreurs, en essayant de rendre celles-ci les plus explicites possibles.
Ainsi, dans la mesure du possible, les erreurs sont gérées récursivement afin de permettre à l'utilisateur de mieux retrouver l'origine de celle-ci.

On peut distinguer 3 type d'erreurs :
   - Les erreurs du lexer : lorsqu'un charactère est non reconnu (n'appartient pas à la grammaire) [UnexpectedCharacter]
   - Les erreurs du parser : il s'agit des erreurs de syntaxe [syntaxicError]
   - Les erreurs d'interpretation : levée au moment de l'évaluation des expressions [programmError]
   
Les jeux de tests utilisés pour les tests des pistes vertes et bleues sont ceux donnés dans le cadre du tp. De nouveaux jeux de tests ont étés implémentés afin de tester les différentes erreurs possibles au cours de l'execution du programme.
Ces nouveaux tests sont visibles dans le dossier test/src, et utilisent les fichiers calcs situés dans les sous-répertoires du dossier 'test'. Les jeux de tests s'appuient sur la classe Test fournie lors du TP (légèrement modifiée afin de rajouter un complément sur le message d'erreur désiré).

# Libertés prises avec le TP

La grammaire donnée dans le cadre du TP a été rspectée le plus possible. Plusieurs améliorations auraient étés possibles comme la gestion des commentaires par exemple. 
J'ai implémenté une subtilité permettant le prise en compte des varaibles et des chiffres entre parenthèses. Ce code est commenté et donc non actif par défaut, il peut être décommenté et testé : certaines erreurs n'existeront alors plus.