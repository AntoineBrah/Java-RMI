clear ; 
javac Commun/src/*.java ; 
javac -cp .:/Commun Serveur/src/*.java ; 
javac -cp .:/Commun Client/src/*.java ;

echo -e "\e[33mCompilation des classes Java effectuée."


