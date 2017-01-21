# Environnement de travail

Le projet a �t� d�velopp� sous Windows 10. L'IDE utilis� est eclipse Neon et les tests ont �t�s effectu�s avec une JVM en Java 8.

# Avancement dans le TP

La piste verte est compl�te (parser + �valuation)
La piste bleue est compl�te (parser + �valuation)

Ni la piste rouge ni la piste noire n'ont �t� impl�ment�es.

# Strat�gie de gestion des erreurs

Un travail a �t� effectu� concernant la gestion des erreurs, en essayant de rendre celles-ci les plus explicites possibles.
Ainsi, dans la mesure du possible, les erreurs sont g�r�es r�cursivement afin de permettre � l'utilisateur de mieux retrouver l'origine de celle-ci.

On peut distinguer 3 type d'erreurs :
   - Les erreurs du lexer : lorsqu'un charact�re est non reconnu (n'appartient pas � la grammaire) [UnexpectedCharacter]
   - Les erreurs du parser : il s'agit des erreurs de syntaxe [syntaxicError]
   - Les erreurs d'interpretation : lev�e au moment de l'�valuation des expressions [programmError]
   
Les jeux de tests utilis�s pour les tests des pistes vertes et bleues sont ceux donn�s dans le cadre du tp. De nouveaux jeux de tests ont �t�s impl�ment�s afin de tester les diff�rentes erreurs possibles au cours de l'execution du programme.
Ces nouveaux tests sont visibles dans le dossier test/src, et utilisent les fichiers calcs situ�s dans les sous-r�pertoires du dossier 'test'. Les jeux de tests s'appuient sur la classe Test fournie lors du TP (l�g�rement modifi�e afin de rajouter un compl�ment sur le message d'erreur d�sir�).

# Libert�s prises avec le TP

La grammaire donn�e dans le cadre du TP a �t� rspect�e le plus possible. Plusieurs am�liorations auraient �t�s possibles comme la gestion des commentaires par exemple. 
J'ai impl�ment� une subtilit� permettant le prise en compte des varaibles et des chiffres entre parenth�ses. Ce code est comment� et donc non actif par d�faut, il peut �tre d�comment� et test� : certaines erreurs n'existeront alors plus.